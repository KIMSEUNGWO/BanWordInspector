package ban.inspector.inspector;

import ban.inspector.config.InspectConfig;
import ban.inspector.config.innerConfig.InnerInspectConfig;
import ban.inspector.domain.Word;
import ban.inspector.factory.*;
import ban.inspector.utils.AhoCorasickWordUtil;
import ban.inspector.utils.wordutils.BanWordUtil;
import ban.inspector.utils.wordutils.ExceptWordUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


class InspectorTest {

    private final Inspector inspector;

    public InspectorTest() {
        BanWordUtil banWordUtil = new BanWordUtil(new AhoCorasickWordUtil());
        ExceptWordUtil exceptWordUtil = new ExceptWordUtil(new AhoCorasickWordUtil());
        WordFactoryBuilder<BanWordUtil> banFac = new BanWordFactoryImpl(banWordUtil);
        WordFactoryBuilder<ExceptWordUtil> exceptFac = new ExceptWordFactoryImpl(exceptWordUtil);

        InspectConfig inspectConfig = new TestInspectConfig();
        inspectConfig.addBanWords(banFac);
        inspectConfig.addExceptWords(exceptFac);


        InnerInspectConfig config = new InnerInspectConfig(banFac, exceptFac);
        config.setInspectConfig(inspectConfig);
        banFac.build();
        exceptFac.build();


        this.inspector = new InspectorImpl(config);
    }

    public static class TestInspectConfig implements InspectConfig {

        @Override
        public void addBanWords(WordFactory factory) {
            List<String> banWords = List.of("바나나", "사과", "오렌지", "수박", "멜론");
            factory.add(banWords);
        }

        @Override
        public void addExceptWords(WordFactory factory) {
            List<String> exceptWords = List.of("바나나주스", "사과주스", "포도주스");
            factory.add(exceptWords);
        }
    }


    @ParameterizedTest
    @ValueSource(strings = {
        "이런 쓰레기같은 상황에서는 어떻게 해야 할까?",
        "왜 이렇게 이상한 일이 매번 일어나는 거야?",
        "너무 짜증나는 상황이야.",
        "이런 거 보면 화가 나.",
        "진짜 이딴 일이 왜 발생하는지 이해할 수 없어.",
        "정말 화가 난다.",
        "왜 이런 쓰레기같은 일이 또 나를 찾는 거지?",
        "이딴 일이 또 발생하면 정말 화가 날 거야.",
        "너무 기분 나쁜 상황이야.",
        "왜 이런 불공평한 일이 일어나는지 이해할 수 없어.",
        "이런 쓰레기같은 상황에서는 어떻게 해야 할지 모르겠어.",
        "왜 이런 짜증나는 일이 일어나는지 모르겠어.",
        "진짜 이딴 상황은 왜 매번 나를 찾는 거지?",
        "왜 이런 쓰레기같은 생각을 하는지 이해할 수 없어.",
        "너무 화가 나서 어떻게 해야 할지 몰라.",
        "이런 빌어먹을 일이 왜 나를 따라 다니는 거야?",
        "왜 이런 쓰레기같은 일이 또 나를 찾는 거야?",
        "이딴 상황을 어떻게 무시할 수 있어?",
        "이런 쓰레기같은 생각은 어디서 나오는 거야?",
        "왜 이런 짜증나는 상황에서 또 빠져나올 수가 없는 거야?",
        "이딴 상황에서 어떻게 살아야 할까?",
        "너무 화가 나서 속이 메스꺼워.",
        "이딴 생각은 왜 자꾸 떠오르는 거야?",
        "이딴 상황에서 나는 어떻게 해야 할지 모르겠어.",
        "자꾸 화가 나.",
    })
    void 금지어가_존재하지않으면_빈_리스트를_반환한다(String word) {
        // when
        List<Word> response = inspector.inspect(word);

        // then
        assertThat(response).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {"원숭이바나나", "사과나무", "오렌지컬러", "단수박", "바 나나", "사     과", "    수 박 "})
    void 금지어가_포함되면_리스트로_반환된다(String word) {
        // when
        List<Word> response = inspector.inspect(word);

        assertThat(response).isNotEmpty();

    }

    @Test
    void 금지어가_포함되면_리스트로_반환된다() {
        // given
        String word = "사 과바 나나 수수박";

        // when
        List<Word> response = inspector.inspect(word);

        // then
        assertThat(response)
            .isNotEmpty()
            .extracting(Word::word, Word::startIndex, Word::endIndex)
            .containsExactly(
                tuple("사과", 0, 3),
                tuple("바나나", 3, 7),
                tuple("수박", 9, 11)
            );

    }

    @Test
    void 금지어가_포함되면_리스트로_반환된다2() {
        // given
        String word = "사   과 수수박";

        // when
        List<Word> response = inspector.inspect(word);

        // then
        assertThat(response)
            .hasSize(2)
            .extracting(Word::word, Word::startIndex, Word::endIndex)
            .containsExactly(
                tuple("사과", 0, 5),
                tuple("수박", 7, 9)
            );

    }

    @Test
    void 예외단어는_금지어대상에서_제외한다() {
        // given
        String word = "사과주스바나나주스 수수박";

        // when
        List<Word> response = inspector.inspect(word);

        // then
        assertThat(response)
            .hasSize(1)
            .extracting(Word::word, Word::startIndex, Word::endIndex)
            .containsExactly(
                tuple("수박", 11, 13)
            );

    }

    @Test
    void 예외단어는_띄어쓰기를_무시해야한다() {
        // given
        String word = "사과 주스";

        // when
        List<Word> response = inspector.inspect(word);

        // then
        assertThat(response).hasSize(0);

    }

    @Test
    @DisplayName("문자열이 Null이어도 예외가 발생하지 않아야한다.")
    void 빈문자열테스트() {
        // given
        String word = null;

        // when
        List<Word> response = inspector.inspect(word);

        // then
        assertThat(response).isEmpty();
    }


    @ParameterizedTest
    @DisplayName("블라인드는 금지어의 길이와 상관없이 하나의 문자열로 대체된다.")
    @ValueSource(strings = {"바나나", "사과", "오렌지", "수박", "멜론", "수          박", "바       나      나", "사 과"})
    void 금지어_블라인드테스트(String word) {
        // when
        String mask = inspector.mask(word);

        // then
        assertThat(mask).isEqualTo("?");
    }

    @Test
    @DisplayName("대체될 문자열은 사용자가 임의로 선택할 수 있다.")
    void 금지어_블라인드테스트2() {
        String word = "사 과사과";
        String replace = "!@#!@#!@#!@#";

        String mask = inspector.mask(word, replace);
        assertThat(mask).isEqualTo(replace.repeat(2));
    }

    @Test
    @DisplayName("예외단어는 블라인드되면 안된다.")
    void 금지어_블라인드테스트3() {
        String word = "사 과사과주스";
        String replace = "!@#!@#!@#!@#";

        String mask = inspector.mask(word, replace);
        assertThat(mask).isEqualTo(replace + "사과주스");
    }


}