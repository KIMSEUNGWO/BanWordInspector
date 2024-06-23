package ban.inspector.utils;

import org.springframework.stereotype.Component;

@Component
public class BanWordUtilImpl extends BanWordUtil {

    @Override
    protected String setWordForm(String word) {
        return word;
    }
}
