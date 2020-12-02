package by.gsu.pms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class RunnerLogic {

    private static final Logger logger = LoggerFactory.getLogger(RunnerLogic.class.getName());

    private RunnerLogic() {
    }

    public static void createTables(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(Constants.CREATE_ENGLISH_WORDS_TABLE);
            statement.execute(Constants.CREATE_RUSSIAN_WORDS_TABLE);
            statement.execute(Constants.CREATE_DICTIONARY_TABLE);
        }
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
