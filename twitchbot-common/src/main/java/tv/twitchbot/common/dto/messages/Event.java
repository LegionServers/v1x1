package tv.twitchbot.common.dto.messages;

import tv.twitchbot.common.dto.core.Module;
import tv.twitchbot.common.dto.messages.events.ChatMessageEvent;
import tv.twitchbot.common.dto.proto.messages.EventOuterClass;
import tv.twitchbot.common.dto.proto.messages.MessageOuterClass;

/**
 * Created by cobi on 10/4/16.
 */
public abstract class Event extends Message {
    public static Event fromProto(Module module, EventOuterClass.Event event) {
        switch(event.getType()) {
            case CHAT_MESSAGE: return ChatMessageEvent.fromProto(module, event.getExtension(EventOuterClass.ChatMessageEvent.data));
            default: throw new IllegalStateException("Unknown event type " + event.getType().name());
        }
    }

    public Event(Module from) {
        super(from);
    }

    protected EventOuterClass.Event.Builder toProtoEvent() {
        return EventOuterClass.Event.newBuilder();
    }

    protected MessageOuterClass.Message.Builder toProtoMessage() {
        return super.toProtoMessage()
                .setType(MessageOuterClass.Message.MessageType.EVENT)
                .setExtension(EventOuterClass.Event.data, toProtoEvent().build());
    }
}
