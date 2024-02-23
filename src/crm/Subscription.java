package crm;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Subscription {
    private int id; //unique
    private String phoneNumber;
    private LocalDate CreatedDate;
    private State state;

    private int contactId;
    private Set<Service> services;

    public Subscription(int id, String phoneNumber, LocalDate createdDate, State state, int contactId) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        CreatedDate = createdDate;
        this.state = state;
        this.contactId = contactId;
        this.services = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        CreatedDate = createdDate;
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

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }
    public void addService(Service service){
        services.add(service);
    }

    public void removeService(Service service){
        services.remove(service);
    }
}
