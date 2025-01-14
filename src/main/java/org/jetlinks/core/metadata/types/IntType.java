package org.jetlinks.core.metadata.types;

import lombok.Getter;
import lombok.Setter;
import org.hswebframework.web.i18n.LocaleUtils;

@Getter
@Setter
public class IntType extends NumberType<Integer> {
    public static final String ID = "int";

    public static final IntType GLOBAL = new IntType();
    private static final int SCALE = Integer.getInteger("jetlinks.type.int.scale", 0);

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String getName() {
        return LocaleUtils.resolveMessage("message.metadata.type.int", LocaleUtils.current(), "整型");
    }

    @Override
    protected Integer castNumber(Number number) {
        return number.intValue();
    }

    @Override
    public int defaultScale() {
        return SCALE;
    }
}
