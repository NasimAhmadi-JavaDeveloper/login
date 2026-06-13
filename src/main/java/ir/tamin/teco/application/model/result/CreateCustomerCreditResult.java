package ir.tamin.teco.application.model.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerCreditResult {

    private Long id;

    private String personType;

    private String firstName;

    private String lastName;

    private String nationalCode;

    private String birthDate;

    private String fatherName;

    private String managerNationalCode;

    private String phone;

    private BranchResult.create branch;//TODO??

    private String accountNo;

    private String debtReason;

    private Long amount;

    private Long discountAmount;

    private String tetaTrackingCode;

    private Long tetaUserId;

    private String status;

}