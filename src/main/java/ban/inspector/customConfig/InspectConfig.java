package ban.inspector.customConfig;

public interface InspectConfig {

    void addBanWordInspector(BanWordFactory factory);
    void addExceptWordInspector(ExceptWordFactory factory);
}
