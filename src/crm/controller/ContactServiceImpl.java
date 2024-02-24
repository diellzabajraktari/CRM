package crm.controller;
import crm.enums.Gender;
import crm.enums.IdType;
import crm.enums.State;
import crm.model.Business;
import crm.model.Contact;
import crm.model.Individual;
import crm.service.TelecomService;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ContactServiceImpl implements TelecomService<Contact> {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/crm";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static final String INSERT_CONTACT_SQL = "INSERT INTO Contact (ID, IdType, Name, Lastname, Customername, Gender, DOB, CreatedDate, State) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_CONTACT_SQL = "UPDATE Contact SET IdType=?, Name=?, Lastname=?, Customername=?, Gender=?, DOB=?, CreatedDate=?, State=? WHERE ID=?";
    private static final String DELETE_CONTACT_SQL = "DELETE FROM Contact WHERE ID=?";
    private static final String FIND_CONTACT_BY_ID_SQL = "SELECT * FROM Contact WHERE ID=?";
    private static final String FIND_ALL_CONTACTS_SQL = "SELECT * FROM Contact";

    private Connection connection;

    public ContactServiceImpl() {
        try {
            this.connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            System.out.println("Connected to database with URL: " + JDBC_URL);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }

    @Override
    public void create(Contact contact) {
        if (contact instanceof Individual) {
            createIndividualContact((Individual) contact);
        } else if (contact instanceof Business) {
            createBusinessContact((Business) contact);
        }
    }

    @Override
    public void delete(int id) {
        Contact contact = findById(id);
        if (contact != null) {
            if (contact instanceof Individual) {
               // deleteIndividualContact(id);
            } else if (contact instanceof Business) {
               // deleteBusinessContact(id);
            }
        }
    }

    @Override
    public Contact findById(int id) {
        // Use appropriate SQL query based on the type of contact
        // Execute the query and create Individual/Business contact accordingly
        return null;
    }

    @Override
    public List<Contact> findAll() {
        List<Contact> contacts = new ArrayList<>();
        // Use appropriate SQL query based on the type of contact
        // Execute the query and create Individual/Business contacts accordingly
        return contacts;
    }

    @Override
    public void update(Contact contact, int ID) {
        if (contact instanceof Individual) {
           // updateIndividualContact((Individual) contact);
        } else if (contact instanceof Business) {
           // updateBusinessContact((Business) contact);
        }
    }

    // Methods specific to Individual
    private void createIndividualContact(Individual individual) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_CONTACT_SQL)) {
            statement.setInt(1, individual.getId());
            statement.setString(2, IdType.CU.toString());
            statement.setString(3, individual.getName());
            statement.setString(4, individual.getLastName());
            statement.setNull(5, Types.VARCHAR); // Business contacts don't have a customer name
            statement.setString(6, individual.getGender().toString());
            statement.setDate(7, Date.valueOf(individual.getDOB()));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = individual.getCreatedDate().atStartOfDay().format(formatter);
            Timestamp timestamp = Timestamp.valueOf(formattedDateTime);

            statement.setTimestamp(8, timestamp);
            statement.setString(9, individual.getState().name());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }
//
//    private void updateIndividualContact(Individual individual) {
//        try (PreparedStatement statement = connection.prepareStatement(UPDATE_CONTACT_SQL)) {
//            statement.setString(1, IdType.CUCustomer.toString());
//            statement.setString(2, individual.getName());
//            statement.setString(3, individual.getLastName());
//            statement.setNull(4, Types.VARCHAR); // Business contacts don't have a customer name
//            statement.setString(5, individual.getGender().toString());
//            statement.setDate(6, Date.valueOf(individual.getDOB()));
//            statement.setTimestamp(7, Timestamp.valueOf(individual.getCreatedDate().toString()));
//            statement.setString(8, individual.getState().name());
//            statement.setInt(9, individual.getId());
//
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // Handle the exception appropriately
//        }
//    }

//    private void deleteIndividualContact(int id) {
//        // Implement the deletion logic for Individual contact
//        try (PreparedStatement statement = connection.prepareStatement(DELETE_CONTACT_SQL)) {
//            statement.setInt(1, id);
//
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // Handle the exception appropriately
//        }
//    }

    // Methods specific to Business
    private void createBusinessContact(Business business) {
        // Implement the creation logic for Business contact
        try (PreparedStatement statement = connection.prepareStatement(INSERT_CONTACT_SQL)) {
            statement.setInt(1, business.getId());
            statement.setString(2, IdType.CU.toString());
            statement.setNull(3, Types.VARCHAR); // Individual contacts don't have a name
            statement.setNull(4, Types.VARCHAR); // Individual contacts don't have a last name
            statement.setString(5, business.getCustomerName());
            statement.setNull(6, Types.VARCHAR); // Individual contacts don't have a gender
            statement.setNull(7, Types.DATE);    // Individual contacts don't have a DOB
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = business.getCreatedDate().atStartOfDay().format(formatter);
            Timestamp timestamp = Timestamp.valueOf(formattedDateTime);

            statement.setTimestamp(8, timestamp);
            statement.setString(9, business.getState().name());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }

//    private void updateBusinessContact(Business business) {
//        // Implement the update logic for Business contact
//        try (PreparedStatement statement = connection.prepareStatement(UPDATE_CONTACT_SQL)) {
//            statement.setString(1, IdType.CUCustomer.toString());
//            statement.setNull(2, Types.VARCHAR); // Individual contacts don't have a name
//            statement.setNull(3, Types.VARCHAR); // Individual contacts don't have a last name
//            statement.setString(4, business.getCustomerName());
//            statement.setNull(5, Types.VARCHAR); // Individual contacts don't have a gender
//            statement.setNull(6, Types.DATE);    // Individual contacts don't have a DOB
//            statement.setTimestamp(7, Timestamp.valueOf(business.getCreatedDate().toString()));
//            statement.setString(8, business.getState().name());
//            statement.setInt(9, business.getId());
//
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // Handle the exception appropriately
//        }
//    }

//    private void deleteBusinessContact(int id) {
//        // Implement the deletion logic for Business contact
//        try (PreparedStatement statement = connection.prepareStatement(DELETE_CONTACT_SQL)) {
//            statement.setInt(1, id);
//
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // Handle the exception appropriately
//        }
//    }

    public static void main(String[] args) {
        ContactServiceImpl c = new ContactServiceImpl();
        LocalDate dob = LocalDate.of(2000, 4, 3);
        LocalDate createdDate = LocalDate.of(2022, 3, 3);
        Individual individualContact = new Individual(1, IdType.CU, createdDate, State.ACTIVE, "John", "Doe", Gender.M, dob);
        Business businessContact = new Business(2, IdType.CU, createdDate, State.ACTIVE, "ABC GROUP");

        // Use appropriate methods to create/update/delete based on the type
        c.create(individualContact);
        c.create(businessContact);
    }
}

