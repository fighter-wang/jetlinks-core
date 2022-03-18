package org.jetlinks.core.trace.data;

import com.google.common.collect.Maps;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.sdk.trace.data.EventData;
import lombok.Getter;
import lombok.Setter;
import org.jetlinks.core.utils.SerializeUtils;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Map;

@Getter
@Setter
public class SpanEventDataInfo implements Externalizable {
    private static final long serialVersionUID = -1;

    private String name;

    private long timeNanos;

    private Map<String, Object> attributes;

    public static SpanEventDataInfo of(EventData eventData) {
        return new SpanEventDataInfo().with(eventData);
    }

    public SpanEventDataInfo with(EventData eventData) {
        this.name = eventData.getName();
        this.timeNanos = eventData.getEpochNanos();
        Attributes attr = eventData.getAttributes();
        if (!attr.isEmpty()) {
            attributes = Maps.newHashMapWithExpectedSize(attr.size());
            attr.forEach((k, v) -> attributes.put(k.getKey(), v));
        }
        return this;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name);
        out.writeLong(timeNanos);
        SerializeUtils.writeKeyValue(attributes, out);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = in.readUTF();
        this.timeNanos = in.readLong();
        this.attributes = SerializeUtils.<Object>readMap(in, Maps::newHashMapWithExpectedSize);
    }
}