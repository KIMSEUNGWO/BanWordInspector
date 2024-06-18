package ban.inspector.customConfig;

import ban.inspector.utils.BanWordUtil;

public interface BanWordFactory extends Iterable<BanWordUtil> {

    WordFactoryBuilder add(BanWordUtil banWordUtil);

}
