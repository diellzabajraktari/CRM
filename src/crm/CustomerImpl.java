package crm;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class CustomerImpl implements TelecomService<Customer> {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/crm";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private Connection connection;
    public CustomerImpl() {
        try {
            this.connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Customer customer) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO CUSTOMER(ID, CustomerType, CreatedDate, State, ContactId) VALUES(?, ?, ?, ?, ?)")) {
            statement.setInt(1, customer.getId());
            statement.setString(2, customer.getCustomerType().toString());
            statement.setDate(3, customer.getCreatedDate());
            statement.setString(4, customer.getState().toString());
            statement.setInt(5, customer.getContactId());
            statement.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Customer customer, int ID) {
        try(PreparedStatement statement = connection.prepareStatement("UPDATE Customer SET ID=?, CustomerType = ?, CreatedDate = ?, State = ?, ContactId=? WHERE ID =?")) {
            statement.setInt(1, customer.getId());
            statement.setString(2, customer.getCustomerType().toString());
            statement.setDate(3, customer.getCreatedDate());
            statement.setString(4, customer.getState().toString());
            statement.setInt(5, customer.getContactId());
            statement.setInt(6, ID);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try(PreparedStatement statement = connection.prepareStatement("DELETE FROM CUSTOMER WHERE ID=?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer findById(int id) {
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM CUSTOMER WHERE ID = ?")) {
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()) {
                int customerId = resultSet.getInt("ID");
                CustomerType customerType = resultSet.getDate("CreatedDate");
                return null;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    public static void main(String[] args) {
        CustomerImpl c = new CustomerImpl();
        LocalDate currentDate = LocalDate.now();
        Date sqlCurrentDate = Date.valueOf(currentDate);
       // Customer customer1 = new Customer(1, CustomerType.INDIVIDUAL, sqlCurrentDate, State.ACTIVE, 1);
      //  c.create(customer1);
//        Customer customer2 = new Customer(2, CustomerType.BUSINESS, sqlCurrentDate, State.INACTIVE, 1);
//        c.update(customer2, 1);
        c.delete(2);
    }
}
