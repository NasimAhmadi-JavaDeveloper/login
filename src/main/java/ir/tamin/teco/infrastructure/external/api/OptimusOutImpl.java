package ir.tamin.teco.infrastructure.external.api;

import ir.tamin.teco.application.port.out.OptimusOut;
import ir.tamin.teco.domain.service.ConfigService;
import ir.tamin.teco.infrastructure.external.api.model.request.OptimusServiceLoginRequest;
import ir.tamin.teco.infrastructure.external.api.model.response.OptimusServiceLoginResponse;
import ir.tamin.teco.shared.model.enums.ConfigKey;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OptimusOutImpl implements OptimusOut {

  private final ConfigService configService;
  private final RestTemplate restTemplate;

  @Override
  @Cacheable("optimusServiceToken")
  public String getServiceToken() {
    final String baseUrl = configService.getString(ConfigKey.OPTIMUS_BASE_URL);
    final String authUrl = configService.getString(ConfigKey.OPTIMUS_AUTH_URL);
    final String Url = baseUrl + authUrl;

    final String username = configService.getString(ConfigKey.OPTIMUS_USERNAME);
    OptimusServiceLoginRequest request = new OptimusServiceLoginRequest()
        .setUsername(username);
    // blah blah blah

    final OptimusServiceLoginResponse optimusServiceLoginResponse = restTemplate.postForObject(Url, request,
        OptimusServiceLoginResponse.class);
    //restTemplate.exchange(....

    if (optimusServiceLoginResponse == null) {
      //THROW, OR USE LIKE EXCHANGE LIKE OLD CODE AND CHECK STATUS....
    }

    return optimusServiceLoginResponse.getToken();
  }
}