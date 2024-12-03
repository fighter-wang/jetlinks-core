package org.jetlinks.core.metadata.types;

import lombok.Getter;
import lombok.Setter;
import org.jetlinks.core.config.ConfigKey;
import org.jetlinks.core.metadata.Converter;
import org.jetlinks.core.metadata.DataType;
import org.jetlinks.core.metadata.ValidateResult;

@Getter
@Setter
public class StringType extends AbstractType<StringType> implements DataType, Converter<String> {
    public static final String ID = "string";
    public static final StringType GLOBAL = new StringType();
    public static final ConfigKey<Integer> MAX_LENGTH = ConfigKey.of("maxLength");

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String getName() {
        return "字符串";
    }

    @Override
    public ValidateResult validate(Object value) {
        return ValidateResult.success(String.valueOf(value));
    }

    @Override
    public String format(Object value) {
        int maxLength = this.getExpand(MAX_LENGTH).orElse(0);
        if (maxLength > 0) {
            return String.valueOf(value).substring(0, maxLength);
        }
        return String.valueOf(value);
    }

    @Override
    public String convert(Object value) {
        return value == null ? null : String.valueOf(value);
    }
}
