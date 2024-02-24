package crm.model;

import crm.enums.ContractType;
import crm.enums.State;

import java.time.LocalDate;

public class Contract {
    private final int id; //unique
    private ContractType contractType;
    private final LocalDate createdDate;
    private State state;
    public Contract(int id, ContractType contractType, LocalDate createdDate, State state) {
        this.id = id;
        this.contractType = contractType;
        this.createdDate = createdDate;
        this.state = state;
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
}
