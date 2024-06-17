package ban.inspector.customConfig;


import ban.inspector.utils.ExceptWordUtil;

import java.util.List;

public interface ExceptWordFactory {

    ExceptWordFactoryImpl.ExceptWordFactoryBuilder add(ExceptWordUtil exceptWordUtil);

    List<ExceptWordUtil> getUtils();
}
