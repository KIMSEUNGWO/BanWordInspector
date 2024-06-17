package ban.inspector.service;

import ban.inspector.dto.Word;
import ban.inspector.inspector.Inspector;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class InspectorTest {

    @Autowired
    private Inspector inspector;

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

}