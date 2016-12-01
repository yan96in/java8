package collector;

public class StringCombiner {

    private final String delim;
    private final String prefix;
    private final String suffix;
    private final StringBuilder builder;

    public StringCombiner(String delim, String prefix, String suffix) {
        this.delim = delim;
        this.prefix = prefix;
        this.suffix = suffix;
        builder = new StringBuilder();
    }
    //add方法返回连接元素后的结果
    public StringCombiner add(String element) {
    if (areAtStart()) {
        builder.append(prefix);
    } else {
        builder.append(delim);
    }
    builder.append(element);
    return this;
}

    private boolean areAtStart() {
        return builder.length() == 0;
    }
    //merger方法连接两个StringCombiner对象
    public StringCombiner merge(StringCombiner other) {
	if (other.builder.length() > 0) {
        if (areAtStart()) {
        	builder.append(prefix);
        } else {
        	builder.append(delim);
        }
        builder.append(other.builder, prefix.length(), other.builder.length());
    }
    return this;
}
    
    @Override
    public String toString() {
        if (areAtStart()) {
            builder.append(prefix);
        }
        builder.append(suffix);
        return builder.toString();
    }

}
