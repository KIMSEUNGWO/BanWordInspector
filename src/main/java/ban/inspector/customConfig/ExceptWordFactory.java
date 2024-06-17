package ban.inspector.customConfig;


import ban.inspector.utils.ExceptWordUtil;


public interface ExceptWordFactory {

    ExceptWordFactoryImpl.ExceptWordFactoryBuilder add(ExceptWordUtil exceptWordUtil);

}
