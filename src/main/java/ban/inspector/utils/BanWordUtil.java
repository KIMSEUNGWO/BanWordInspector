package ban.inspector.utils;

import org.springframework.stereotype.Component;

@Component
public class BanWordUtil extends AbstractWordUtil {

    @Override
    protected AbstractWordUtil getInstance() {
        return new BanWordUtil();
    }
}
