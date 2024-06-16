package ban.inspector.utils;

import org.springframework.stereotype.Component;


@Component
public class BanWordUtilImpl extends AbstractBanWordUtil {

    @Override
    protected WordData getInstance() {
        return new BanWordUtilImpl();
    }

}
