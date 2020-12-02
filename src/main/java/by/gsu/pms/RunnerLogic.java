package by.gsu.pms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RunnerLogic {

    private static final Logger logger = LoggerFactory.getLogger(RunnerLogic.class.getName());

    private RunnerLogic() {
    }

    public static void translate(String word, Connection connection, String query) throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            final int WORD_INDEX = 1;
            ps.setString(WORD_INDEX, word);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.isBeforeFirst()) {
                    final int WORD_MEANING_INDEX = 1;
                    logger.info("Translation:");
                    while (rs.next()) {
                        logger.info(rs.getString(WORD_MEANING_INDEX));
                    }
                } else {
                    logger.info("The word is missing from the dictionary");
                }
            }
        }
    }
}
