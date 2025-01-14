package org.jetlinks.core.metadata.types;

import lombok.Getter;
import lombok.Setter;
import org.hswebframework.web.i18n.LocaleUtils;

@Getter
@Setter
@SuppressWarnings("all")
public class FloatType extends NumberType<Float> {
    public static final String ID = "float";
    public static final FloatType GLOBAL = new FloatType();

    private static final int SCALE = Integer.getInteger("jetlinks.type.float.scale", 2);

    @Override
    protected Float castNumber(Number number) {
        return number.floatValue();
    }

    @Override
    public int defaultScale() {
        return SCALE;
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String getName() {
        return LocaleUtils.resolveMessage("message.metadata.type.float", LocaleUtils.current(), "单精度浮点数");
    }


}
