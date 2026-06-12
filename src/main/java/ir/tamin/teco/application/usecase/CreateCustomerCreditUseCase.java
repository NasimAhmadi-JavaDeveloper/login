package ir.tamin.teco.application.usecase;

import ir.tamin.teco.application.model.command.CreateCustomerCreditCommand;
import ir.tamin.teco.application.model.result.CreateCustomerCreditResult;

public interface CreateCustomerCreditUseCase {

    CreateCustomerCreditResult handle(CreateCustomerCreditCommand command);

}