package ir.tamin.teco.application.model.command;

import lombok.*;

@Value
public class CreateCustomerCreditCommand {

    UserCommand.create userCreateCommand;

    String personType;

    String firstName;

    String lastName;

    String nationalCode;

    String birthDate;

    String fatherName;

    String managerNationalCode;

    String phone;

    String unitCode;

    String accountNo;

    String debtReason;

    Long amount;

    Long discountAmount;

}