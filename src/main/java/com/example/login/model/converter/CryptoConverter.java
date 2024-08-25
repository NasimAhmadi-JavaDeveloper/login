package com.example.login.model.converter;

import com.example.login.exception.ExceptionSpec;
import com.example.login.exception.LogicalException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

@Converter
@Component
public class CryptoConverter implements AttributeConverter<String, String> {

  @Value("${crypto.converter.algorithm}")
  private String algorithm;

  private byte[] keyCrypto;

  @PostConstruct
  public void init() {
    this.keyCrypto = "MySuperSecretKey".getBytes(StandardCharsets.UTF_8);
  }

  @Override
  public String convertToDatabaseColumn(String sensitive) {
    Key key = new SecretKeySpec(keyCrypto, "AES");
    try {
      final Cipher c = Cipher.getInstance(algorithm);
      c.init(Cipher.ENCRYPT_MODE, key);
      return new String(Base64.getEncoder().encode(c.doFinal(sensitive.getBytes(StandardCharsets.UTF_8))),
          StandardCharsets.UTF_8);

    } catch (Exception e) {
      throw new LogicalException(ExceptionSpec.CRYPTO_CONVERTER);
    }
  }

  @Override
  public String convertToEntityAttribute(String sensitive) {
    Key key = new SecretKeySpec(keyCrypto, "AES");
    try {
      final Cipher c = Cipher.getInstance(algorithm);
      c.init(Cipher.DECRYPT_MODE, key);
      return new String(c.doFinal(Base64.getDecoder().decode(sensitive.getBytes(StandardCharsets.UTF_8))),
          StandardCharsets.UTF_8);
    } catch (Exception e) {
      throw new LogicalException(ExceptionSpec.CRYPTO_CONVERTER);
    }
  }
}
