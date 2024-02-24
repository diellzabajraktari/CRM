package crm.model;

import crm.enums.CustomerType;
import crm.enums.State;

import java.sql.Date;

public class Customer {
    private final int id; //unique
    private final CustomerType customerType;
    private final Date createdDate;
    private State state;
    private final int contactId;
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
    public void setState(State state) {
        this.state = state;
    }

    public int getContactId() {
        return contactId;
    }
}
