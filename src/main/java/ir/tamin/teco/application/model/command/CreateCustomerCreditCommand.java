package ir.tamin.teco.application.model.command;

import lombok.Value;

@Value
public class CreateCustomerCreditCommand {

    //UserCommand.create userCreateCommand;

    Long id;

    String personType;

    String firstName;

    String lastName;

    String nationalCode;

    String birthDate;

    String fatherName;

    String managerNationalCode;

    String phone;

    BranchCommand.create branchCreateCommand;

    String accountNo;

    String debtReason;

    Long amount;

    Long discountAmount;

    String tetaTrackingCode;

    Long tetaUserId;

    String status;

}