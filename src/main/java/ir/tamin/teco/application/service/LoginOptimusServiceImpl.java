package ir.tamin.teco.application.service;

import ir.tamin.teco.application.port.out.OptimusOut;
import ir.tamin.teco.domain.exception.OptimusAuthenticationException;
import ir.tamin.teco.application.port.in.ServiceTokenService;
import ir.tamin.teco.infrastructure.external.optimus.model.response.OptimusServiceLoginResponse;
import ir.tamin.teco.infrastructure.external.optimus.model.response.OptimusServiceLoginResponse.Result;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginOptimusServiceImpl implements ServiceTokenService {

  private final OptimusOut optimusOut;

  @Override

  @Cacheable("optimusServiceToken")
  public String getServiceToken() {
    return Optional.ofNullable(optimusOut.login())
            .map(OptimusServiceLoginResponse::result)
            .map(Result::token)
            .orElseThrow(() -> new OptimusAuthenticationException("Optimus token is missing in response"));
  }
}
