package ban.inspector.factory;

import ban.inspector.utils.wordutils.BanWordUtil;

public interface BanWordFactory extends WordFactory{

    BanWordUtil build();

}
