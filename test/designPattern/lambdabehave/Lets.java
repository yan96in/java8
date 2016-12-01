package designPattern.lambdabehave;

public final class Lets {
//从describe方法开始定义规则
	public static void describe(String name, Suite behavior) {
		Description description = new Description(name);
		behavior.specifySuite(description);
	}

}
