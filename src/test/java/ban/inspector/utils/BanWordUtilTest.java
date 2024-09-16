package ban.inspector.utils;

import ban.inspector.dto.Word;
import ban.inspector.utils.wordutils.BanWordUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class BanWordUtilTest {

    private static final BanWordUtil banWordUtil = new BanWordUtil(new AhoCorasickWordUtil());

    @BeforeAll
    public static void setUp() {
        List<String> banWords = List.of("졸라", "어미", "애미", "니애미", "여러자테스트임");
        banWords.forEach(banWordUtil::addWord);
        banWordUtil.build();
    }


    @ParameterizedTest
    @ValueSource(strings =
        {"졸라", "123졸라", "애애애애애애애애애애애애애애미", "졸       라", "     졸     라", "졸라안녕하세요"})
    void 금지어인_단어(String word) {
        List<Word> banList = banWordUtil.filter(word);
        assertThat(banList).isNotEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "졸누라", "123123", "미애", "어어무미미", "안녕하세요", "조오오옹ㄹ라"})
    void 금지어가_아닌_단어(String word) {
        List<Word> banList = banWordUtil.filter(word);
        assertThat(banList).isEmpty();
    }

    @Test
    public void 금지어_인덱스_테스트() {
        // given
        String word = "졸라 안녕하세요";

        // when
        List<Word> banList = banWordUtil.filter(word);

        // then
        assertThat(banList).hasSize(1);
        assertThat(banList.get(0))
            .extracting(Word::word, Word::startIndex, Word::endIndex)
            .containsExactly("졸라", 0, 2);

    }
    @Test
    public void 금지어_인덱스_테스트_2() {
        // given
        String word = "애애애애애애미";

        // when
        List<Word> banList = banWordUtil.filter(word);

        // then
        assertThat(banList).hasSize(1);
        assertThat(banList.get(0))
            .extracting(Word::word, Word::startIndex, Word::endIndex)
            .containsExactly("애미", 5, 7);

    }

    @Test
    @DisplayName("(2) 금지어를 검색할때 띄어쓰기를 포함한 인덱스를 계산한다.")
    public void 금지어_인덱스_테스트3() {
        // given
        String word = "졸   라배고프다";

        // when
        List<Word> banList = banWordUtil.filter(word);

        // then
        assertThat(banList.get(0))
            .extracting(Word::word, Word::startIndex, Word::endIndex)
            .containsExactly("졸라", 0, 5);

    }

    @Test
    @DisplayName("(1) 금지어가 다수일 경우 금지어목록을 반환한다.")
    public void 다수의_금지어_테스트1() {
        // given
        String word = "졸라졸라졸라여러자테스트임";

        // when
        List<Word> banList = banWordUtil.filter(word);

        // then
        assertThat(banList)
            .hasSize(4)
            .extracting(Word::word, Word::startIndex, Word::endIndex)
            .containsExactly(
                tuple("졸라", 0, 2),
                tuple("졸라", 2, 4),
                tuple("졸라", 4, 6),
                tuple("여러자테스트임", 6, 13)
            );

    }
    @Test
    @DisplayName("(2) 금지어가 다수일 경우 금지어목록을 반환한다.")
    public void 다수의_금지어_테스트2() {
        // given
        String word = "졸 라안녕하세요 어미님";

        // when
        List<Word> banList = banWordUtil.filter(word);

        // then
        assertThat(banList)
            .hasSize(2)
            .extracting(Word::word, Word::startIndex, Word::endIndex)
            .containsExactly(
                tuple("졸라", 0, 3),
                tuple("어미", 9, 11)
            );

    }

    @Test
    @DisplayName("문자열이 Null 이어도 예외가 발생하지 않아야한다.")
    void 문자열이_null이어도_예외가_발생하지_않아야한다() {
        // given
        String word = null;

        // when
        List<Word> filter = banWordUtil.filter(word);

        // then
        assertThat(filter).isEmpty();

    }
}