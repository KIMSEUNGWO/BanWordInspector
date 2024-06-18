package ban.inspector.customConfig;

import ban.inspector.utils.BanWordUtil;

public interface BanWordFactory extends Iterable<BanWordUtil> {

    BanWordFactoryImpl.BanWordFactoryBuilder add(BanWordUtil banWordUtil);

}
