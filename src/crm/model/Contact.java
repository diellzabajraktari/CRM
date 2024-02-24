package crm.model;

import crm.enums.IdType;
import crm.enums.State;

import java.time.LocalDate;

public abstract class Contact {
    private final int id; //unique
    private final IdType idType;
    private final LocalDate createdDate;
    private final State state;

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
