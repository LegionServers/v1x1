package tv.twitchbot.common.dto.core;

import tv.twitchbot.common.dto.proto.core.BotOuterClass;
import tv.twitchbot.common.dto.proto.core.PlatformOuterClass;

/**
 * Represents a {@link Bot} on the Discord platform
 * @author Cobi
 */
public class DiscordBot extends Bot {
    public static DiscordBot fromProto(String name, BotOuterClass.DiscordBot discordBot) {
        return new DiscordBot(name);
    }

    public DiscordBot(String name) {
        super(name);
    }

    @Override
    protected BotOuterClass.Bot.Builder toProtoBuilder() {
        return super.toProtoBuilder()
                .setType(PlatformOuterClass.Platform.DISCORD)
                .setExtension(BotOuterClass.DiscordBot.data, toProtoDiscordBot());
    }

    private BotOuterClass.DiscordBot toProtoDiscordBot() {
        return BotOuterClass.DiscordBot.newBuilder()
                .build();
    }
}
