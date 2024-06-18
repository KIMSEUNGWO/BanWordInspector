package ban.inspector.customConfig;


import ban.inspector.utils.ExceptWordUtil;


public interface ExceptWordFactory extends Iterable<ExceptWordUtil> {

    WordFactoryBuilder add(ExceptWordUtil exceptWordUtil);

}
