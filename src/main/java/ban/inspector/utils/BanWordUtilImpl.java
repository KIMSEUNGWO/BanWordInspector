package ban.inspector.utils;


public class BanWordUtilImpl extends BanWordUtil {


    @Override
    protected String setWordForm(String word) {
        return word.replaceAll("[^가-힣]", "");
    }
}
