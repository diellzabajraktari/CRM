package crm;

import java.time.LocalDate;

public class Individual extends Contact {
    private String name;
    private String lastName;
    private Gender gender;
    private LocalDate DOB;
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
}
