package ir.tamin.teco.controller.rest;

import ir.tamin.teco.model.dto.ResServiceToken;
import ir.tamin.teco.service.optimus.OptimusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/service-token")
public class ServiceTokenController {

  private final OptimusService optimusService;

  @GetMapping
  public ResponseEntity<ResServiceToken> getToken() {

    return ResponseEntity.ok(optimusService.getServiceToken());
  }
}
