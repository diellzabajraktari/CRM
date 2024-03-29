package crm.model;

import crm.enums.Gender;
import crm.enums.IdType;
import crm.enums.State;
import crm.model.Contact;

import java.time.LocalDate;

public class Individual extends Contact {
    private int id;
    private IdType idType;
    private LocalDate createdDate;
    private State state;
    private final String name;
    private final String lastName;
    private final Gender gender;
    private final LocalDate DOB;
    public Individual(int id, IdType idType, LocalDate createdDate, State state, String name, String lastName, Gender gender, LocalDate DOB) {
        super(id, idType, createdDate, state);
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.DOB = DOB;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public IdType getIdType() {
        return idType;
    }

    public void setIdType(IdType idType) {
        this.idType = idType;
    }

    @Override
    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
