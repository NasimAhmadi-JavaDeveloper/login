package ir.tamin.teco.application.handler;

import ir.tamin.teco.application.model.mapper.CustomerDomainMapper;
import ir.tamin.teco.application.model.result.GetCustomerCreditResult;
import ir.tamin.teco.application.usecase.GetCustomerCreditUseCase;
import ir.tamin.teco.domain.exception.CustomerCreditNotFoundException;
import ir.tamin.teco.domain.model.CustomerCredit;
import ir.tamin.teco.domain.repository.CustomerCreditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCustomerCreditHandler implements GetCustomerCreditUseCase {

    private final CustomerCreditRepository customerCreditRepository;
    private final CustomerDomainMapper customerMapper;

    @Override
    public GetCustomerCreditResult handle(Long id) {
        CustomerCredit customerCredit =
                customerCreditRepository.findById(id)
                        .orElseThrow(() -> new CustomerCreditNotFoundException("not found"));//TODO

        return customerMapper.toGetResult(customerCredit);
    }
}
