package solid;

import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicInteger;


class MetricDataGraph {
	//简化前
	public void updateUserTime(int value) {
	};

	public void updateSystmeTime(int value) {
	};

	public void updateIoTime(int value) {
	};
	//简化后
	class TimeSeries{
		//...略
	}
	public void addTimeSeries(TimeSeries values) {
	};
}


public class OpenClosedPrinciple {
	//ThreadLocal日期格式化器
	public void asHigherOrderFunctions() {
		// 实现
		ThreadLocal<DateFormat> localFormatter = ThreadLocal.withInitial(() -> new SimpleDateFormat());// 高阶函数withInitial
		// 使用
		DateFormat formatter = localFormatter.get();
		// 或者这样实现
		AtomicInteger threadId = new AtomicInteger();
		ThreadLocal<Integer> localId = ThreadLocal.withInitial(() -> threadId.getAndIncrement());
		// 使用
		int idForThisThread = localId.get();
	}

}
