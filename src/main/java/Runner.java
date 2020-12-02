import by.gsu.pms.Constants;
import by.gsu.pms.PoolConnection;
import by.gsu.pms.RunnerLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;

class CreateTables {
    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle("jdbc");
        PoolConnection.init(rb.getString("db.name"), rb.getString("db.user"), rb.getString("db.password"));
        try (Connection connection = PoolConnection.getConnection()) {
            RunnerLogic.createTables(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

public class Runner {

    private static final Logger logger = LoggerFactory.getLogger(Runner.class.getName());

    public static void main(String[] args) {

        final String ENGLISH_WORDS_REGEX = "^[a-zA-Z]+$";
        final String RUSSIAN_WORDS_REGEX = "^[а-яА-ЯёЁ]+$";

        ResourceBundle rb = ResourceBundle.getBundle("jdbc");
        PoolConnection.init(rb.getString("db.name"), rb.getString("db.user"), rb.getString("db.password"));

        try (Scanner sc = new Scanner(System.in);
             Connection connection = PoolConnection.getConnection()) {

            String searchingWord;

            while (true) {
                logger.info("Enter a word for translation or exit to stop it:");
                searchingWord = sc.next();

                if (searchingWord.equals("exit")) {
                    return;
                }

                if (searchingWord.matches(ENGLISH_WORDS_REGEX)) {
                    RunnerLogic.translate(searchingWord, connection, Constants.SELECT_ENGLISH_WORLD_TRANSLATION);
                } else if (searchingWord.matches(RUSSIAN_WORDS_REGEX)) {
                    RunnerLogic.translate(searchingWord, connection, Constants.SELECT_RUSSIAN_WORLD_TRANSLATION);
                } else {
                    logger.warn("The word is incorrect");
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
