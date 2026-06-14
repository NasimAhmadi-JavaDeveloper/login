package ir.tamin.teco.infrastructure.external.optimus.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OptimusServiceLoginResponse(@JsonProperty("tracking_code") String trackingCode, Result result) {

  public record Result(
      String token,
      User user,
      Integer expireIn) {

  }


  public record User(
      String username,
      Integer tenantId,
      Integer id,
      String role,
      Integer application) {

  }

}