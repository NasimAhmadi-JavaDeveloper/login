package ir.tamin.teco.domain.service;

import ir.tamin.teco.domain.model.CustomerCredit;
import ir.tamin.teco.domain.model.User;

public interface CustomerCreditService {

    CustomerCredit create(CustomerCredit customerCredit, User user, String branchCode);
}
