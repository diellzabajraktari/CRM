package crm.enums;

public enum IdType {
    CU("Customer"),
    CO("Contract"),
    SU("Subscription");

    private final String description;

    IdType(String description) {
        this.description = description;
    }

    @Override
    public String toString()
    {
        return description;
    }

}
