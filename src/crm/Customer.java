package crm;

import java.time.LocalDate;
import java.sql.Date;

public class Customer {
    private int id; //unique
    private CustomerType customerType;
    private Date createdDate;
    private State state;
    private int contactId;
    public Customer(int id, CustomerType customerType, Date createdDate, State state, int contactId) {
        this.id = id;
        this.customerType = customerType;
        this.createdDate = createdDate;
        this.state = state;
        this.contactId = contactId;
    }

    public int getId() {
        return id;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public State getState() {
        return state;
    }

    public int getContactId() {
        return contactId;
    }
}
