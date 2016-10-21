package tv.twitchbot.common.dto.messages;

import tv.twitchbot.common.dto.core.Module;
import tv.twitchbot.common.dto.core.UUID;
import tv.twitchbot.common.dto.messages.requests.ModuleShutdownRequest;
import tv.twitchbot.common.dto.messages.requests.SendMessageRequest;
import tv.twitchbot.common.dto.proto.messages.MessageOuterClass;
import tv.twitchbot.common.dto.proto.messages.RequestOuterClass;

/**
 * Created by cobi on 10/4/16.
 */
public abstract class Request extends Message {
    public static Request fromProto(final Module module, final UUID uuid, final long timestamp, final RequestOuterClass.Request request) {
        final String responseQueueName = request.getResponseQueueName();
        switch(request.getType()) {
            case MODULE_SHUTDOWN: return ModuleShutdownRequest.fromProto(module, uuid, timestamp, responseQueueName, request.getExtension(RequestOuterClass.ModuleShutdownRequest.data));
            case SEND_MESSAGE: return SendMessageRequest.fromProto(module, uuid, timestamp, responseQueueName, request.getExtension(RequestOuterClass.SendMessageRequest.data));
            default: throw new IllegalStateException("Unknown request type " + request.getType().name());
        }
    }

    private final String responseQueueName;

    public Request(final Module from, final String responseQueueName) {
        super(from);
        this.responseQueueName = responseQueueName;
    }

    public Request(final Module from, final UUID messageId, final long timestamp, final String responseQueueName) {
        super(from, messageId, timestamp);
        this.responseQueueName = responseQueueName;
    }

    public String getResponseQueueName() {
        return responseQueueName;
    }

    protected RequestOuterClass.Request.Builder toProtoRequest() {
        return RequestOuterClass.Request.newBuilder()
                .setResponseQueueName(responseQueueName);
    }

    @Override
    protected MessageOuterClass.Message.Builder toProtoMessage() {
        return super.toProtoMessage()
                .setType(MessageOuterClass.Message.MessageType.REQUEST)
                .setExtension(RequestOuterClass.Request.data, toProtoRequest().build());
    }
}
