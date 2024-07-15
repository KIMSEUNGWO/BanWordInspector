package ban.inspector.updater;


public enum WordType {

    BAN("static/BanWords.json"),
    EXCEPT("static/ExceptWords.json");

    private static final String GIT_URI = "https://raw.githubusercontent.com/KIMSEUNGWO/BanWordInspector_Version/main/";
    private final String localWords;

    WordType(String localWords) {
        this.localWords = localWords;
    }

    public String getLocalWords() {
        return localWords;
    }
}
