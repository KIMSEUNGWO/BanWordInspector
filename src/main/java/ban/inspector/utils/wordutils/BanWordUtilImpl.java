package ban.inspector.utils.wordutils;

import org.springframework.stereotype.Component;

@Component
public class BanWordUtilImpl extends BanWordUtil {

    @Override
    protected String setWordForm(String word) {
        return word;
    }
}
