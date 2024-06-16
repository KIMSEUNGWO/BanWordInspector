package ban.inspector;

public class RemoveNotKorean {

    public static String removeNotKorean(String word) {
        return word.replaceAll("[^가-힣]", "");
    }
}
