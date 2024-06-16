package ban.inspector.utils;

import ban.inspector.dto.WordDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static ban.inspector.RemoveNotKorean.*;
import static org.assertj.core.api.Assertions.*;

class BanWordUtilTest {

    private static final BanWordUtil banWordUtil = new BanWordUtilImpl();

    @BeforeAll
    public static void setUp() {
        List<String> banWords = List.of("졸라", "어미", "애미", "니애미");
        banWords.forEach(banWordUtil::addWord);
    }


    @ParameterizedTest
    @ValueSource(strings =
        {"졸라", "123졸라", "졸123라", "졸ㅁㅁㅁㅁㅁㅁㅁ라", "어ㅁㅁㅁㅁㅁㅇㅏ미", "@#$#$%ㅉㅁㄴㅇㄹㅁ어미", "어미ㅁㄴㄹㅁㄸㄸㅇㅊ!@#12ㅂ3456",
        "졸!@#ㄴㅇㄹㅈ3ㄱ23ㄱ라", "애애애애애애애애애애애애애애미", "졸       라", "     졸   123ㅁㅉㄴㅏㅑㅕㅏ123!@#&* 라", "졸라안녕하세요"})
    void 금지어인_단어(String word) {
        String newWord = removeNotKorean(word);
        List<WordDto> banList = banWordUtil.has(newWord);
        assertThat(banList).hasSize(1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "졸누라", "123123", "미애", "어어무미미", "안녕하세요", "조오오옹ㄹ라"})
    void 금지어가_아닌_단어(String word) {
        String newWord = removeNotKorean(word);
        List<WordDto> banList = banWordUtil.has(newWord);
        assertThat(banList).isEmpty();
    }

    @Test
    public void 금지어_인덱스_테스트() {
        // given
        String word = "졸라 안녕하세요";
        String newWord = removeNotKorean(word);

        // when
        List<WordDto> banList = banWordUtil.has(newWord);

        // then
        assertThat(banList).hasSize(1);
        assertThat(banList.get(0))
            .extracting(WordDto::getWord, WordDto::getStartIndex, WordDto::getEndIndex)
            .containsExactly("졸라", 0, 2);

    }
    @Test
    public void 금지어_인덱스_테스트_2() {
        // given
        String word = "애애애애애애미";
        String newWord = removeNotKorean(word);

        // when
        List<WordDto> banList = banWordUtil.has(newWord);

        // then
        assertThat(banList).hasSize(1);
        assertThat(banList.get(0))
            .extracting(WordDto::getWord, WordDto::getStartIndex, WordDto::getEndIndex)
            .containsExactly("애미", 5, 7);

    }
    @Test
    @DisplayName("금지어를 검색할때 완전한 한글을 제외하고 인덱스를 계산한다.")
    public void 금지어_인덱스_테스트2() {
        // given
        String word = "졸ㅁㅏ!@#라 안녕하세요";
        String newWord = removeNotKorean(word);

        // when
        List<WordDto> banList = banWordUtil.has(newWord);

        // then
        assertThat(banList.get(0))
            .extracting(WordDto::getWord, WordDto::getStartIndex, WordDto::getEndIndex)
            .containsExactly("졸라", 0, 2);

    }

    @Test
    @DisplayName("(1) 금지어가 다수일 경우 금지어목록을 반환한다.")
    public void 다수의_금지어_테스트1() {
        // given
        String word = "졸라졸라졸라";
        String newWord = removeNotKorean(word);

        // when
        List<WordDto> banList = banWordUtil.has(newWord);

        // then
        assertThat(banList)
            .hasSize(3)
            .extracting(WordDto::getWord, WordDto::getStartIndex, WordDto::getEndIndex)
            .containsExactly(
                tuple("졸라", 0, 2),
                tuple("졸라", 2, 4),
                tuple("졸라", 4, 6)
            );

    }
    @Test
    @DisplayName("(2) 금지어가 다수일 경우 금지어목록을 반환한다.")
    public void 다수의_금지어_테스트2() {
        // given
        String word = "졸라안녕하세요 어미님";
        String newWord = removeNotKorean(word);

        // when
        List<WordDto> banList = banWordUtil.has(newWord);

        // then
        assertThat(banList)
            .hasSize(2)
            .extracting(WordDto::getWord, WordDto::getStartIndex, WordDto::getEndIndex)
            .containsExactly(
                tuple("졸라", 0, 2),
                tuple("어미", 7, 9)
            );

    }
}