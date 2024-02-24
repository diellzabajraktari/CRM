package crm;

import crm.enums.State;
import crm.model.Product;
import crm.model.Subscription;
import crm.service.servicetype.ServiceType;

import java.sql.*;
import java.time.LocalDate;
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
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM Product WHERE PRICE < 5")) {
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
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCT WHERE toDateTime < ?")) {
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

    public static void main(String[] args) {
        FilterImplementations f = new FilterImplementations();
        List<Product> products = f.getProductsCheaperThan5();
        printProduct(products);
        List<Product> products1 = f.productsThatWillExpire(10);
        printProduct(products1);

        String dateString = "2024-02-24";
        LocalDate localDate = LocalDate.parse(dateString);
        Subscription subcriber = new Subscription('1', "+38344111333", localDate, State.ACTIVE, 1005);
        products.get(0).purchase(subcriber);
        f.getSubscribersForProduct(products.get(0));
    }

    static void printProduct(List<Product> products) {
        for(Product product: products) {
            System.out.println(product.getName() + " " + product.getPrice() + " " + product.getFromDateTime() + " " + product.getToDateTime());
        }
    }
}
