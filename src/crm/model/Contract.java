package crm.model;

import crm.enums.ContractType;
import crm.enums.State;

import java.time.LocalDate;

public class Contract {
    private final int id; //unique
    private ContractType contractType;
    private final LocalDate createdDate;
    private State state;
    private final int CID;
    private final int contactID;
    public Contract(int id, ContractType contractType, LocalDate createdDate, State state, int CID, int contactID) {
        this.id = id;
        this.contractType = contractType;
        this.createdDate = createdDate;
        this.state = state;
        this.CID = CID;
        this.contactID = contactID;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public int getCID() {
        return CID;
    }

    public int getContactID() {
        return contactID;
    }
}
