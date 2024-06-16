package ban.inspector.utils;



public class ExceptWordUtilImpl extends AbstractWordUtil implements ExceptWordUtil {

    @Override
    public WordUtil getInstance() {
        return new ExceptWordUtilImpl();
    }
}
