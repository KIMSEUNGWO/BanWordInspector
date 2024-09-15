package ban.inspector.factory;

import ban.inspector.utils.wordutils.AbstractWordUtil;

public interface WordFactoryBuilder<T extends AbstractWordUtil> extends WordFactory {

    T build();

}
