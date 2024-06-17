package ban.inspector.customConfig;

import ban.inspector.utils.BanWordUtil;

import java.util.List;

public interface BanWordFactory {

    BanWordFactoryImpl.BanWordFactoryBuilder add(BanWordUtil banWordUtil);

    List<BanWordUtil> getUtils();

}
