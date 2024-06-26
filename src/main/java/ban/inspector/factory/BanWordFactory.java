package ban.inspector.factory;

import ban.inspector.dto.Word;

import java.util.List;

public interface BanWordFactory extends WordFactory{

    List<Word> filter(String word);
    void build();

}
