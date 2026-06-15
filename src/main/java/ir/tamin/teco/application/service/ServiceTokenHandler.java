package ir.tamin.teco.application.service;


import ir.tamin.teco.application.model.result.ServiceTokenResult;
import ir.tamin.teco.application.port.in.ServiceTokenService;
import ir.tamin.teco.application.port.out.OptimusOut;
import ir.tamin.teco.domain.exception.OptimusAuthenticationException;
import ir.tamin.teco.infrastructure.external.optimus.model.response.OptimusServiceLoginResponse;
import ir.tamin.teco.infrastructure.external.optimus.model.response.OptimusServiceLoginResponse.Result;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceTokenHandler implements ServiceTokenService {

  private final OptimusOut optimusOut;

  @Override
  public ServiceTokenResult getServiceToken() {
    return Optional.ofNullable(optimusOut.login())
        .map(OptimusServiceLoginResponse::result)
        .map(Result::token)
        .map(ServiceTokenResult::new)
        .orElseThrow(() -> new OptimusAuthenticationException("Optimus token is missing in response"));
  }
}
