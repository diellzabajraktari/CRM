package crm.controller;

import crm.model.Customer;
import crm.enums.CustomerType;
import crm.enums.State;
import crm.service.TelecomServiceImpl;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerImpl extends TelecomServiceImpl<Customer> {


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
        try(PreparedStatement statement = connection.prepareStatement("UPDATE Customer SET CustomerType = ?, CreatedDate = ?, State = ?, ContactId=? WHERE ID =?")) {
            statement.setString(1, customer.getCustomerType().toString());
            statement.setDate(2, customer.getCreatedDate());
            statement.setString(3, customer.getState().toString());
            statement.setInt(4, customer.getContactId());
            statement.setInt(5, ID);
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
                while(resultSet.next()) {
                    int customerId = resultSet.getInt("ID");
                    CustomerType customerType = CustomerType.valueOf(resultSet.getString("CustomerType"));
                    LocalDate createdDate = resultSet.getDate("CreatedDate").toLocalDate();
                    State state = State.valueOf(resultSet.getString("State"));
                    int contactId = resultSet.getInt("ContactId");
                    return new Customer(customerId, customerType, Date.valueOf(createdDate), state, contactId);
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM CUSTOMER")) {
            try(ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                    int customerId = resultSet.getInt("ID");
                    CustomerType customerType = CustomerType.valueOf(resultSet.getString("CustomerType"));
                    LocalDate createdDate = resultSet.getDate("CreatedDate").toLocalDate();
                    State state = State.valueOf(resultSet.getString("State"));
                    int contactId = resultSet.getInt("ContactId");
                    customers.add(new Customer(customerId, customerType, Date.valueOf(createdDate), state, contactId));
                }
                return customers;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        CustomerImpl c = new CustomerImpl();
        LocalDate currentDate = LocalDate.now();
        Date sqlCurrentDate = Date.valueOf(currentDate);
      // Customer customer1 = new Customer(1, CustomerType.INDIVIDUAL, sqlCurrentDate, State.ACTIVE, 1);
       // c.create(customer1);
        Customer customer2 = new Customer(2, CustomerType.BUSINESS, sqlCurrentDate, State.INACTIVE, 1);
       // c.update(customer2, 1);
     //   c.delete(2);
       // c.create(customer2);


        c.update(customer2, 1);
//        List<Customer> customers = c.findAll();
//        for(Customer customer: customers) {
//            System.out.println(customer.getId() + " " + customer.getCustomerType() + " " + customer.getContactId() + " " + customer.getCreatedDate());
//        }
    }
}
