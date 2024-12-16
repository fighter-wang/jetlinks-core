package org.jetlinks.core.lang;

import org.jetlinks.core.utils.RecyclerUtils;

class AppendSeparatedCharSequenceN extends AbstractSeparatedCharSequence {

    private final AbstractSeparatedCharSequence source;
    private final CharSequence[] appendN;

    AppendSeparatedCharSequenceN(AbstractSeparatedCharSequence source,
                                 CharSequence[] appendN) {
        this.source = source;
        this.appendN = appendN;
    }

    @Override
    public SeparatedCharSequence internInner() {
        source.internInner();
        for (int i = 0; i < appendN.length; i++) {
            if (appendN[i] instanceof SeparatedCharSequence) {
                appendN[i] = ((SeparatedCharSequence) appendN[i]).intern();
            } else {
                appendN[i] = RecyclerUtils.intern(appendN[i]);
            }
        }
        return this;
    }

    @Override
    public char separator() {
        return source.separator();
    }

    @Override
    protected int size0() {
        return source.size() + appendN.length;
    }

    @Override
    protected CharSequence get0(int index) {
        int size = source.size();

        if (index >= size) {
            return appendN[index - size];
        }

        return source.get(index);
    }
}
