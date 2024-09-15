package ban.inspector.factory;

import ban.inspector.utils.wordutils.BanWordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BanWordFactoryImpl extends AbstractWordFactory<BanWordUtil> {

    @Autowired
    public BanWordFactoryImpl(BanWordUtil banWordUtil) {
        super(banWordUtil);
    }

}
