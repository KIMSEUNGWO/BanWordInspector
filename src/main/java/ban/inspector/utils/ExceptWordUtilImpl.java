package ban.inspector.utils;



public class ExceptWordUtilImpl extends WordUtilImpl implements ExceptWordUtil {

    @Override
    public WordUtil getInstance() {
        return new ExceptWordUtilImpl();
    }
}
