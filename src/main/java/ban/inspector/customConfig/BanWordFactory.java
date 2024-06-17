package ban.inspector.customConfig;

import ban.inspector.utils.BanWordUtil;

public interface BanWordFactory {

    BanWordFactoryImpl.BanWordFactoryBuilder add(BanWordUtil banWordUtil);

}
