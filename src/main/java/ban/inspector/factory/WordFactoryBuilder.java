package ban.inspector.factory;

public interface WordFactoryBuilder<T> extends WordFactory {

    T build();

}
