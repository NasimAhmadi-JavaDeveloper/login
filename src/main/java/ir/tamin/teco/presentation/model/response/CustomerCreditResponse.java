package ir.tamin.teco.presentation.model.response;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreditResponse {

    private Long id;

    private String personType;

    private String firstName;

    private String lastName;

    private String nationalCode;

    private String birthDate;

    private String fatherName;

    private String managerNationalCode;

    private String phone;

    //private Branch branch;//todo?

    private String accountNo;

    private String debtReason;

    private Long amount;

    private Long discountAmount;

    private String tetaTrackingCode;

    private Long tetaUserId;

    private String status;

}