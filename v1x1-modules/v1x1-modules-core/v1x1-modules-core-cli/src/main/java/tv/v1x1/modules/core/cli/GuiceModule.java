package tv.v1x1.modules.core.cli;

import com.google.inject.AbstractModule;
import tv.v1x1.modules.core.cli.commands.TwitchIdsCommand;

/**
 * Created by cobi on 3/4/2017.
 */
public class GuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(TwitchIdsCommand.class);
    }
}
