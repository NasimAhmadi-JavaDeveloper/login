package ir.tamin.teco.infrastructure.external.api.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Accessors(chain = true)
@FieldDefaults(level =  AccessLevel.PRIVATE)
public class OptimusServiceLoginRequest {

  String username;
}
