package ban.inspector.utils;


public class BanWordUtilImpl extends AbstractWordUtil implements BanWordUtil {

    @Override
    public WordUtil getInstance() {
        return new BanWordUtilImpl();
    }
}
