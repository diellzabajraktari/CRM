package crm;

import java.time.LocalDate;

public class Business extends Contact {
    private String customerName;

    public Business(int id, IdType idType, LocalDate createdDate, State state, String customerName) {
        super(id, idType, createdDate, state);
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }
}
