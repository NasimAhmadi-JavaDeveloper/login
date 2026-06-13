package ir.tamin.teco.presentation.controller.rest.customer;

import ir.tamin.teco.application.model.command.CreateCustomerCreditCommand;
import ir.tamin.teco.application.model.result.CreateCustomerCreditResult;
import ir.tamin.teco.application.usecase.CreateCustomerCreditUseCase;
import ir.tamin.teco.application.model.result.GetCustomerCreditResult;
import ir.tamin.teco.application.usecase.GetCustomerCreditUseCase;
import ir.tamin.teco.presentation.model.mapper.CustomerCreditWebMapper;
import ir.tamin.teco.presentation.model.request.CreateCustomerCreditRequest;
import ir.tamin.teco.presentation.model.response.CustomerCreditResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer-credit")
public class CustomerCreditController {

    private final CustomerCreditWebMapper webMapper;
    private final GetCustomerCreditUseCase getUseCase;
    private final CreateCustomerCreditUseCase createUseCase;

    @PostMapping
    public CustomerCreditResponse create(@RequestBody CreateCustomerCreditRequest request) {

        CreateCustomerCreditCommand command = webMapper.toCommand(request);

        CreateCustomerCreditResult result = createUseCase.handle(command);

        return webMapper.toCreateResponse(result);
    }

    @GetMapping("/{id}")
    public CustomerCreditResponse getById(@PathVariable Long id) {

        GetCustomerCreditResult result = getUseCase.handle(id);

        return webMapper.toGetResponse(result);
    }
}
