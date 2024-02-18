package crm;

import java.time.LocalDate;

public abstract class Contact {
    private int id; //unique
    private IdType idType;
    private LocalDate createdDate;
    private State state;

    public Contact(int id, IdType idType, LocalDate createdDate, State state) {
        this.id = id;
        this.idType = idType;
        this.createdDate = createdDate;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public IdType getIdType() {
        return idType;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public State getState() {
        return state;
    }
}
