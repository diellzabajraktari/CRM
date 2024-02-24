package crm.model;

import crm.enums.State;
import crm.service.servicetype.ServiceType;

import java.time.LocalDate;

public class Service {
    private final int id; //unique
    private final ServiceType serviceType;
    private LocalDate createdDate;
    private State state;

    public Service(int id, ServiceType serviceType, LocalDate createdDate, State state) {
        this.id = id;
        this.serviceType = serviceType;
        this.createdDate = createdDate;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
