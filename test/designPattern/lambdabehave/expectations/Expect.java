package designPattern.lambdabehave.expectations;

import java.util.Collection;
//期望链的开始
public final class Expect {

    public BoundExpectation that(Object value) {
        return new BoundExpectation(value);
    }

    public CollectionExpectation that(Collection<?> collection) {
        return new CollectionExpectation(collection);
    }

}
