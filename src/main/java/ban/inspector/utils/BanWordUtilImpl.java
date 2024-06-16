package ban.inspector.utils;


public class BanWordUtilImpl extends WordUtilImpl implements BanWordUtil {

    @Override
    public WordUtil getInstance() {
        return new BanWordUtilImpl();
    }
}
