package ban.inspector.inspector;

import ban.inspector.config.InspectConfig;
import ban.inspector.config.InnerInspectConfig;
import ban.inspector.updater.WordChecker;
import ban.inspector.updater.WordCheckerImpl;
import ban.inspector.dto.Word;
import ban.inspector.factory.*;
import ban.inspector.updater.WordUpdater;
import ban.inspector.updater.WordUpdaterImpl;
import ban.inspector.utils.wordutils.BanWordUtil;
import ban.inspector.utils.wordutils.ExceptWordUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


class InspectorTest {

    private Inspector inspector;

    public InspectorTest() {
        BanWordUtil banWordUtil = new BanWordUtil();
        ExceptWordUtil exceptWordUtil = new ExceptWordUtil();
        BanWordFactory banFac = new BanWordFactoryImpl(banWordUtil);
        ExceptWordFactory exceptFac = new ExceptWordFactoryImpl(exceptWordUtil);

        InspectConfig inspectConfig = new TestInspectConfig();
        inspectConfig.addBanWords(banFac);
        inspectConfig.addExceptWords(exceptFac);

        WordUpdater wordUpdater = new WordUpdaterImpl();
        WordChecker wordChecker = new WordCheckerImpl(wordUpdater);

        InnerInspectConfig config = new InnerInspectConfig(banFac, exceptFac, wordChecker);
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
            .extracting(Word::getWord, Word::getStartIndex, Word::getEndIndex)
            .containsExactly(
                tuple("사 과", 0, 3),
                tuple("바 나나", 3, 7),
                tuple("수박", 9, 11)
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
            .extracting(Word::getWord, Word::getStartIndex, Word::getEndIndex)
            .containsExactly(
                tuple("수박", 11, 13)
            );

    }

    @Test
    void 예외단어는_띄어쓰기를_무시하지않는다() {
        // given
        String word = "사과 주스";

        // when
        List<Word> response = inspector.inspect(word);

        // then
        assertThat(response)
            .hasSize(1)
            .extracting(Word::getWord, Word::getStartIndex, Word::getEndIndex)
            .containsExactly(
                tuple("사과", 0, 2)
            );

    }

    @Test
    void test() {
        // given
        String word = "멜론";

        // when
        List<Word> response = inspector.inspect(word);

        // then
        assertThat(response)
            .hasSize(1)
            .extracting(Word::getWord, Word::getStartIndex, Word::getEndIndex)
            .containsExactly(
                tuple("멜론", 0, 2)
            );

    }


}