import by.gsu.pms.PoolConnection;
import by.gsu.pms.RunnerLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Runner {

    private static final Logger logger = LoggerFactory.getLogger(Runner.class.getName());

    public static void main(String[] args) {

        final String SELECT_ENGLISH_WORLD_TRANSLATION = "SELECT `translation` FROM `dictionary` WHERE `word` = ?";
        final String SELECT_RUSSIAN_WORLD_TRANSLATION = "SELECT `word` FROM `dictionary` WHERE `translation` = ?";

        final String ENGLISH_WORDS_REGEX = "^[a-zA-Z]+$";
        final String RUSSIAN_WORDS_REGEX = "^[а-яА-ЯёЁ]+$";

        ResourceBundle rb = ResourceBundle.getBundle("jdbc");
        PoolConnection.init(rb.getString("db.name"), rb.getString("db.user"), rb.getString("db.password"));

        logger.info("Enter a word for translation:");

        try (Scanner sc = new Scanner(System.in);
             Connection connection = PoolConnection.getConnection()) {

            String searchingWord = sc.next();

            if (searchingWord.matches(ENGLISH_WORDS_REGEX)) {
                RunnerLogic.translate(searchingWord, connection, SELECT_ENGLISH_WORLD_TRANSLATION);
            } else if (searchingWord.matches(RUSSIAN_WORDS_REGEX)) {
                RunnerLogic.translate(searchingWord, connection, SELECT_RUSSIAN_WORLD_TRANSLATION);
            } else {
                throw new IllegalArgumentException("Enter a correct word");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
