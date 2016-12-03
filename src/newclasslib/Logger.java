package newclasslib;

import java.util.function.Supplier;

public class Logger {

	private boolean debug = true;

	public boolean isDebugEnabled() {
		return debug;
	}

	public void debug(String message) {
		if (isDebugEnabled()) {
			System.out.println(message);
		}
	}

	public void example() {
		Logger logger = new Logger();
		// 使用isDebugEnabled方法降低日志性能开销
		if (logger.isDebugEnabled()) {
			logger.debug("Look at this: " + expensiveOperation());
		}
	}

	private String expensiveOperation() {
		return "";
	}
	//启用Lambda表达式实现的日志记录器
	public void debug(Supplier<String> message) {
		if (isDebugEnabled()) {
			debug(message.get());//调用内置的Supplier接口中的get方法，相当于调用传入的Lambda表达式
			//Predicate中的是test方法，Function中的是apply
		}
	}

	public void exampleWithLambda() {
		Logger logger = new Logger();
		// 使用Lambda表达式简化日志代码
		logger.debug(() -> "Look at this: " + expensiveOperation());
	}

}
