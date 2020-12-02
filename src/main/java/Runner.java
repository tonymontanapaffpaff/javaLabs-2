import by.gsu.pms.utils.PoolConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class Runner {

    private static final Logger logger = LoggerFactory.getLogger(Runner.class.getName());

    public static void main(String[] args) {
        try (Connection connection = PoolConnection.getConnection()) {

        } catch (SQLException e) {

        }
    }
}
