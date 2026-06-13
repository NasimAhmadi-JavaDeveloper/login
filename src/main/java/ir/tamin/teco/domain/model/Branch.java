package ir.tamin.teco.domain.model;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Branch {

    private String brhCode;

    private String brhName;

    private String brhKind;

  public static Branch fromCode(final String unitCode) {
    return Branch.builder().brhCode(unitCode).build();
  }
}