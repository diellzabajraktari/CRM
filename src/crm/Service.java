package crm;

import java.time.LocalDate;

public class Service {
    private int id; //unique
    private ServiceType serviceType;
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
}
