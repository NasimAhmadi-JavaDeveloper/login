package ir.tamin.teco.application.usecase;

import ir.tamin.teco.application.model.result.GetCustomerCreditResult;

public interface GetCustomerCreditUseCase {

    GetCustomerCreditResult handle(Long id);

}