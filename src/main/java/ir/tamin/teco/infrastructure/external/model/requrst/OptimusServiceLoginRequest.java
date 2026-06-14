package ir.tamin.teco.infrastructure.external.model.requrst;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OptimusServiceLoginRequest {
    String url;
    String username;
    String password;
    String appKey;
    String service;
}