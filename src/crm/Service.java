package crm;

import java.time.LocalDate;

public class Service {
    private final int id; //unique
    private final ServiceType serviceType;
    private final LocalDate createdDate;
    private final State state;

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
}
