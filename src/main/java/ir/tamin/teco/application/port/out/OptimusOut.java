package ir.tamin.teco.application.port.out;

import ir.tamin.teco.infrastructure.external.model.response.OptimusServiceLoginResponse;

public interface OptimusOut {

  OptimusServiceLoginResponse login();

}