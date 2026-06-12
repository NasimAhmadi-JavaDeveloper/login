package ir.tamin.teco.domain.model;

import ir.tamin.teco.domain.model.enums.PersonType;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCredit {

    private Long id;

    private PersonType personType;

    private String firstName;

    private String lastName;

    private String nationalCode;

    private String birthDate;

    private String fatherName;

    private String managerNationalCode;

    private String phone;

    private Branch branch;

    private String accountNo;

    private String debtReason;

    private Long amount;

    private Long discountAmount;

    private String tetaTrackingCode;//TODO ?

    private Long tetaUserId;//TODO ?

    private String status;//TODO ?

    private LocalDateTime creationDate;//TODO ? base

    private String creationUser;//TODO ? base

    private LocalDateTime modifyDate;//TODO ? base

    private String modifyUser; //TODO ? base

}