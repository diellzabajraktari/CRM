package crm.model;

import crm.enums.ContractType;
import crm.enums.State;

import java.time.LocalDate;

public class Contract {
    private int id; //unique
    private ContractType contractType;
    private LocalDate createdDate;
    private State state;
}
