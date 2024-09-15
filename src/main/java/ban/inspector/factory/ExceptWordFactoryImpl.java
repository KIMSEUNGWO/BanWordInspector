package ban.inspector.factory;

import ban.inspector.utils.wordutils.ExceptWordUtil;
import org.springframework.stereotype.Component;


@Component
public class ExceptWordFactoryImpl extends AbstractWordFactory<ExceptWordUtil> {

    public ExceptWordFactoryImpl(ExceptWordUtil exceptWordUtil) {
        super(exceptWordUtil);
    }

}
