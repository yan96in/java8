package designPattern.lambdabehave;

import designPattern.lambdabehave.expectations.Expect;

public final class Description {

	private final String suite;

	Description(String suite) {
		this.suite = suite;
	}

	// 将用Lambda表达式表示的规则传入should方法
	public void should(String description, Specification specification) {
		try {
			Expect expect = new Expect();
			specification.specifyBehaviour(expect);
			Runner.current.recordSuccess(suite, description);
		} catch (AssertionError cause) {
			Runner.current.recordFailure(suite, description, cause);
		} catch (Throwable cause) {
			Runner.current.recordError(suite, description, cause);
		}
	}

}
