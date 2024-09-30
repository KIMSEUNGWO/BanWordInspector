package ban.inspector.utils;

import ban.inspector.domain.Word;
import ban.inspector.utils.wordutils.ExceptWordUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ExceptWordUtilTest {

    private static final ExceptWordUtil exceptWordUtil = new ExceptWordUtil(new AhoCorasickWordUtil());

    @BeforeAll
    public static void setUp() {
        List<String> exceptWords = List.of("고르곤졸라", "어미새");
        exceptWords.forEach(exceptWordUtil::addWord);
        exceptWordUtil.build();
    }

    @Test
    @DisplayName("금지어가 허용단어에 존재하면 금지어목록에서 제거한다.")
    void 허용단어가_존재하면_제거() {
        // given
        String word = "고르곤졸라가 맛있어요";

        List<Word> banWords = new ArrayList<>();
        banWords.add(new Word("졸라", 3, 5));

        // when
        List<Word> filter = exceptWordUtil.filter(word, banWords);

        // then
        Assertions.assertThat(filter).hasSize(0);
    }

    @Test
    @DisplayName("허용단어에 포함되어있지않으면 목록에서 제거하지않는다.")
    void 허용단어에_포함되어있지않으면_제거하지않는다() {
        // given
        String word = "고르곤졸라가 졸라맛있어요";

        List<Word> banWords = new ArrayList<>();
        banWords.add(new Word("졸라", 3, 5));
        banWords.add(new Word("졸라", 7, 9));

        // when
        List<Word> filter = exceptWordUtil.filter(word, banWords);

        // then
        Assertions.assertThat(filter).hasSize(1);
    }

    @Test
    @DisplayName("문자열이 존재하지않아도 예외가 발생하지 않아야한다.")
    void 리스트가_비어있어도_예외가발생하지_않아야한다() {
        // given
        String word = null;

        List<Word> banWords = List.of();

        // when
        List<Word> filter = exceptWordUtil.filter(word, banWords);

        // then
        Assertions.assertThat(filter).isEmpty();
    }

}