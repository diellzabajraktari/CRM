package crm;

import crm.controller.CustomerImpl;
import crm.enums.CustomerType;
import crm.enums.State;
import crm.model.Customer;

import java.sql.Date;
import java.time.LocalDate;

public class TestImplementations {
    public static void main(String[] args) {
        CustomerImpl customer = new CustomerImpl();
        Customer customer1 = new Customer(103, CustomerType.INDIVIDUAL, Date.valueOf("2024-02-25"), State.ACTIVE, 1003);
        customer.create(customer1);

    }
}
