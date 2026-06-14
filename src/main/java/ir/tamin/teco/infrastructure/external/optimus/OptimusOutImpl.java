package ir.tamin.teco.infrastructure.external.optimus;

import ir.tamin.teco.application.port.out.OptimusOut;
import ir.tamin.teco.domain.exception.OptimusAuthenticationException;
import ir.tamin.teco.domain.exception.OptimusServiceUnavailableException;
import ir.tamin.teco.application.port.in.ConfigService;
import ir.tamin.teco.infrastructure.external.optimus.model.request.OptimusServiceLoginRequest;
import ir.tamin.teco.infrastructure.external.optimus.model.response.OptimusServiceLoginResponse;
import ir.tamin.teco.domain.enums.ConfigKey;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OptimusOutImpl implements OptimusOut {

  private final RestTemplate restTemplate;
  private final ConfigService configService;

  @Override
  public OptimusServiceLoginResponse login() {

    final String baseUrl = configService.getString(ConfigKey.OPTIMUS_BASE_URL);
    final String authUrl = configService.getString(ConfigKey.OPTIMUS_AUTH_URL);
    final String url = baseUrl + authUrl;
    final String username = configService.getString(ConfigKey.OPTIMUS_USERNAME);
    final String password = configService.getString(ConfigKey.OPTIMUS_PASSWORD);
    final String appKey = configService.getString(ConfigKey.OPTIMUS_APP_KEY);
    final String service = configService.getString(ConfigKey.OPTIMUS_SERVICE_NAME);

    OptimusServiceLoginRequest request =
        new OptimusServiceLoginRequest()
            .setUrl(url)
            .setUsername(username)
            .setPassword(password)
            .setAppKey(appKey)
            .setService(service);

    //MAYBE HEADERS NEEDED?
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<OptimusServiceLoginRequest> requestEntity = new HttpEntity<>(request, headers);

    final ResponseEntity<OptimusServiceLoginResponse> response =
        restTemplate.postForEntity(url, requestEntity, OptimusServiceLoginResponse.class);

    if (response.getBody() == null) {
      throw new OptimusServiceUnavailableException("Optimus returned empty response");//TODO Advice
    }

    if (response.getStatusCode() != HttpStatus.OK) {
      throw new OptimusAuthenticationException(
          "Optimus authentication failed. HTTP status: " + response.getStatusCode());//TODO Advice
    }

    OptimusServiceLoginResponse body = response.getBody();

    if (body.result() == null || body.result().token() == null) {
      throw new OptimusAuthenticationException("Optimus token is missing in response");//TODO Advice
    }

    return body;
  }
}