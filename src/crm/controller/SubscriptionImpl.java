package crm.controller;

import crm.enums.State;
import crm.model.Subscription;
import crm.service.TelecomServiceImpl;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionImpl extends TelecomServiceImpl<Subscription> {


    @Override
    public void create(Subscription subscription) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO SUBSCRIPTION(ID, PhoneNumber, CreatedDate, State, CID) VALUES(?, ?, ?, ?, ?)")) {
            statement.setInt(1, subscription.getId());
            statement.setString(2, subscription.getPhoneNumber());
            statement.setDate(3, java.sql.Date.valueOf(subscription.getCreatedDate()));
            statement.setString(4, subscription.getState().toString());
            statement.setInt(5, subscription.getContactId());
            statement.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Subscription subscription, int ID) {
        try(PreparedStatement statement = connection.prepareStatement("UPDATE Subscription SET PhoneNumber = ?, CreatedDate = ?, State = ?, ContactId=? WHERE ID =?")) {

            statement.setString(1,subscription.getPhoneNumber());
            statement.setDate(2, Date.valueOf(subscription.getCreatedDate()));
            statement.setString(4, subscription.getState().toString());
            statement.setInt(5, subscription.getContactId());
            statement.setInt(6, ID);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try(PreparedStatement statement = connection.prepareStatement("DELETE FROM Subscription WHERE ID=?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Subscription findById(int id) {
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM SUBSCRIPTION WHERE ID = ?")) {
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                    int subscriptionId = resultSet.getInt("ID");
                    String phoneNumber = resultSet.getString("PhoneNumber");
                    LocalDate createdDate = resultSet.getDate("CreatedDate").toLocalDate();
                    State state = State.valueOf(resultSet.getString("State"));
                    int contactId = resultSet.getInt("ContactId");
                    return new Subscription(subscriptionId, phoneNumber, Date.valueOf(createdDate).toLocalDate(), state, contactId);
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Subscription> findAll() {
        List<Subscription> subscriptions = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM SUBSCRIPTION")) {
            try(ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                    int subscriptionId = resultSet.getInt("ID");
                    String phoneNumber = resultSet.getString("PhoneNumber");
                    LocalDate createdDate = resultSet.getDate("CreatedDate").toLocalDate();
                    State state = State.valueOf(resultSet.getString("State"));
                    int contactId = resultSet.getInt("CID");
                    subscriptions.add(new Subscription(subscriptionId, phoneNumber, Date.valueOf(createdDate).toLocalDate(), state, contactId));
                }
                return subscriptions;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
//    public void addService(int ID,Service service){
//        try(PreparedStatement statement = connection.prepareStatement("INSERT INTO SubscriptionServiceType (SubscriptionId, ServiceTypeID)")) {
//            statement.setInt(1,ID);
//            statement.setInt(2,service.getId());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//    public Set<Service> getServicesForSubscription(int subscriptionId) {
//        Set<Service> services = new HashSet<>();
//        String query = "SELECT s.* FROM Service s " +
//                "JOIN SubscriptionServiceType ss ON s.ID = ss.ServiceTypeID " +
//                "WHERE ss.SubscriptionID = ?";
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setInt(1, subscriptionId);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                while (resultSet.next()) {
//                    int serviceId = resultSet.getInt("ID");
//                    String serviceTypeStr = resultSet.getString("ServiceType");
//                    ServiceType serviceType = null;
//                    if ("DATA".equals(serviceTypeStr)) {
//                        serviceType = new SMS();
//                    } else if ("Voice".equals(serviceTypeStr)) {
//                        // Similarly for other service types
//                        // serviceType = new Voice();
//                    } else {
//                        // Handle unrecognized service types
//                    }
//
//                    // Retrieve other service attributes from ResultSet
//                    LocalDate createdDate = resultSet.getDate("CreatedDate").toLocalDate();
//                    State state = State.valueOf(resultSet.getString("State"));
//
//                    // Create Service object with retrieved attributes
//                    Service service = new Service(serviceId, serviceType, createdDate, state);
//                    services.add(service);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return services;
//    }

    public static void main(String[] args){
//        SubscriptionImpl smpl = new SubscriptionImpl();
//        Date currentDate = Date.valueOf(LocalDate.now());
//
//        Subscription sub1 = new Subscription(123,"+38344123456",currentDate,State.ACTIVE,123);
//        smpl.create(sub1);
    }
}
