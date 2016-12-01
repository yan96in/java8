package designPattern.lambdabehave;

import designPattern.lambdabehave.expectations.Expect;
//每条规则都是一个实现该接口的Lambda表达式
public interface Specification {
    public void specifyBehaviour(Expect expect);
}
