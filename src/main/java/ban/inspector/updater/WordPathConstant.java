package ban.inspector.updater;

public abstract class WordPathConstant {

    private WordPathConstant() {}

    public static final String BAN_WORD_PATH = "static/BanWords.json";
    public static final String EXCEPT_WORD_PATH = "static/ExceptWords.json";

    public static final String REMOTE_BAN_WORD_PATH = "https://raw.githubusercontent.com/KIMSEUNGWO/WordInspector_Version/main/BanWords.json";
    public static final String REMOTE_EXCEPT_WORD_PATH = "https://raw.githubusercontent.com/KIMSEUNGWO/WordInspector_Version/main/ExceptWords.json";
}
