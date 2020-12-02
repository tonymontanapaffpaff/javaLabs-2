package by.gsu.pms;

public class Constants {

    private Constants() {
    }

    public static final String CREATE_ENGLISH_WORDS_TABLE = "CREATE TABLE `english_words` (\n" +
            " `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            " `word` varchar(30) NOT NULL,\n" +
            " PRIMARY KEY (`id`),\n" +
            " KEY `word` (`word`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8";

    public static final String CREATE_RUSSIAN_WORDS_TABLE = "CREATE TABLE `russian_words` (\n" +
            " `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            " `word` varchar(30) NOT NULL,\n" +
            " PRIMARY KEY (`id`),\n" +
            " KEY `word` (`word`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8\n";

    public static final String CREATE_DICTIONARY_TABLE = "CREATE TABLE `dictionary` (\n" +
            " `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            " `word` varchar(30) NOT NULL,\n" +
            " `translation` varchar(30) NOT NULL,\n" +
            " PRIMARY KEY (`id`),\n" +
            " KEY `word` (`word`),\n" +
            " KEY `translation` (`translation`),\n" +
            " CONSTRAINT `dictionary_ibfk_1` FOREIGN KEY (`word`) REFERENCES `english_words` (`word`),\n" +
            " CONSTRAINT `dictionary_ibfk_2` FOREIGN KEY (`translation`) REFERENCES `russian_words` (`word`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8";

    public static final String SELECT_ENGLISH_WORLD_TRANSLATION = "SELECT `translation` FROM `dictionary`" +
            " WHERE `word` = ?";
    public static final String SELECT_RUSSIAN_WORLD_TRANSLATION = "SELECT `word`FROM `dictionary`" +
            " WHERE `translation` = ?";
}
