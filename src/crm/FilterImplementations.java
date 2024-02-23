package crm;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FilterImplementations{
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/crm";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    public Connection connection;

    public FilterImplementations() {
        try {
            this.connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Product> getProductsCheaperThan5() {
        List<Product> products = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCTS WHERE PRICE < 5")) {
            try(ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                    String name = resultSet.getString("Name");
                    double price = resultSet.getDouble("Price");
                    LocalDateTime fromDateTime = resultSet.getTimestamp("fromDateTime").toLocalDateTime();
                    LocalDateTime toDateTime = resultSet.getTimestamp("toDateTime").toLocalDateTime();
                    Product product = new Product(name, price, fromDateTime, toDateTime);
                    products.add(product);
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> productsThatWillExpire(int x) {
        List<Product> products = new ArrayList<>();
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime expirationDate = currentDate.plusDays(x);
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCTS WHERE toDateTime < ?")) {
            statement.setTimestamp(1, Timestamp.valueOf(expirationDate));
            try(ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                    String name = resultSet.getString("Name");
                    double price = resultSet.getDouble("Price");
                    LocalDateTime fromDateTime = resultSet.getTimestamp("fromDateTime").toLocalDateTime();
                    LocalDateTime toDateTime = resultSet.getTimestamp("toDateTime").toLocalDateTime();
                    Product product = new Product(name, price, fromDateTime, toDateTime);
                    products.add(product);
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void getSubscribersForProduct(Product product) {
        List<Subscription> purchasedSubscribers = product.getPurchasedSubscribers();
        for(Subscription subscriber: purchasedSubscribers) {
            System.out.print("Subscriber ID: " + subscriber.getId() + " ;");
            System.out.print("Subscriber Phone Number: " + subscriber.getPhoneNumber() + ";") ;
            System.out.println("List of services: ");
            List<ServiceType> services = product.getServiceTypes();
            int i = 0;
            for(ServiceType service: services) {
                System.out.printf("%d. %s",++i, service.getServiceType());
            }
        }
    }
}
