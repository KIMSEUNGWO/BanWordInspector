package ban.inspector.utils;

import org.springframework.stereotype.Component;

@Component
public class ExceptWordUtilImpl extends AbstractWordUtil {

    @Override
    protected AbstractWordUtil getInstance() {
        return new ExceptWordUtilImpl();
    }
}