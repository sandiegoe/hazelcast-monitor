package io.github.daniloarcidiacono.hazelcast.monitor.agent.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.github.daniloarcidiacono.hazelcast.monitor.agent.dto.request.PullSubscriptionRequest;
import io.github.daniloarcidiacono.hazelcast.monitor.agent.dto.request.SubscribeRequest;
import io.github.daniloarcidiacono.hazelcast.monitor.agent.dto.request.UnsubscribeRequest;
import io.github.daniloarcidiacono.hazelcast.monitor.agent.dto.request.UpdateSubscriptionRequest;
import io.github.daniloarcidiacono.hazelcast.monitor.agent.dto.response.SubscribeResponse;
import io.github.daniloarcidiacono.hazelcast.monitor.agent.dto.response.SubscriptionNoticeResponse;
import io.github.daniloarcidiacono.hazelcast.monitor.agent.dto.response.UpdateSubscriptionResponse;
import io.github.daniloarcidiacono.typescriptmapper.core.annotation.TypescriptDTO;
import io.github.daniloarcidiacono.typescriptmapper.core.annotation.TypescriptField;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "messageType",
    include = JsonTypeInfo.As.PROPERTY
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = SubscribeRequest.class, name = SubscribeRequest.MESSAGE_TYPE),
    @JsonSubTypes.Type(value = UpdateSubscriptionRequest.class, name = UpdateSubscriptionRequest.MESSAGE_TYPE),
    @JsonSubTypes.Type(value = UnsubscribeRequest.class, name = UnsubscribeRequest.MESSAGE_TYPE),
    @JsonSubTypes.Type(value = PullSubscriptionRequest.class, name = PullSubscriptionRequest.MESSAGE_TYPE),

    @JsonSubTypes.Type(value = SubscribeResponse.class, name = SubscribeResponse.MESSAGE_TYPE),
    @JsonSubTypes.Type(value = UpdateSubscriptionResponse.class, name = UpdateSubscriptionResponse.MESSAGE_TYPE),
    @JsonSubTypes.Type(value = SubscriptionNoticeResponse.class, name = SubscriptionNoticeResponse.MESSAGE_TYPE),

    @JsonSubTypes.Type(value = ErrorMessage.class, name = ErrorMessage.MESSAGE_TYPE)
})
@TypescriptDTO
public abstract class AbstractMessage {
    private String messageType;
    
    @TypescriptField(required = false)
    private Long messageId;

    @JsonCreator
    public AbstractMessage(@JsonProperty("messageType") final String messageType) {
        this.messageType = messageType;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }
}
