package crm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class TelecomServiceImpl<T> implements TelecomService<T> {
        private static final String JDBC_URL = "jdbc:mysql://localhost:3306/crm";
        private static final String USER = "root";
        private static final String PASSWORD = "";
        public Connection connection;

        public TelecomServiceImpl() {
            try {
                this.connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
