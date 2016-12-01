package lambda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import javax.swing.JButton;
import javax.swing.text.DateFormatter;

public class Lambda {
	JButton button;

	void function() {//需要显式声明event，大量的样板代码
		button.addActionListener(new ActionListener() {//ActionListener是函数接口

			@Override
			public void actionPerformed(ActionEvent event) {
				System.out.println("button clicked");
			}
		});
		// java8
		button.addActionListener(event -> System.out.println("button clicked"));

		// lambda表达式的不同类型
		Runnable noArguments = () -> System.out.println("Hello Word");//空括号表示没有参数，一个方法
		ActionListener oneArgument = event -> System.out.println("button clicked");//和一开始的例子一样，是1个参数
		Runnable multiStatement = () -> {//表达式主体还可以是一段代码块，直接用大括号扩起来
			System.out.println("hello");
			System.out.println("world");
		};
		BinaryOperator<Long> add = (x, y) -> x + y;//包含多个参数和方法，变量add的类型不是两个数字的和，而是一个函数
		BinaryOperator<Long> addExplicit = (Long x, Long y) -> x + y;	//最好显式声明参数类型，lambda表达式的类型依赖于上下文环境。
		//类似的有数组和null，只有将null赋值给一个变量，才能知道它的类型
		final String[] array ={"hello","world"};//右边并没有声明类型，系统是根据上下文环境推断的类型信息

		// 匿名内部类中使用final局部变量,java8中放松了限制，但依然要求其是事实上的final
		/* final */ 
		String name = "yan96in";
		button.addActionListener(new ActionListener() {// 函数接口
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("hi " + name);
			}
		});

		// lambda表达式中引用既成事实的final变量
		button.addActionListener(event -> System.out.println("hi " + name));
	}

	// 函数接口与类型推断,根据上下文判断的
	//java7引入的菱形操作符，根据类型做判断
	Map<String,Integer> oldWordCounts=new HashMap<>();
	//或者根据方法签名判断
	private void useHashMap(Map<String,String> values){};
	void fun(){
		useHashMap(new HashMap());
	}
	//java7可以省略构造函数的范型类型，java8可以省略Lambda表达式中的所以参数类型。
	
	//几个比较重要的类型，（还有Lambda的类型推断）
	Predicate<Integer> atLeast5 = x -> x > 5;//还会根据返回值是不是boolean的来推断，（参考Predicate源码）
	Consumer c;// void
	Function f;// <T,R> 返回R类型
	Supplier s;// 返回T
	UnaryOperator u;// T
	BinaryOperator<Long> addLong = (x, y) -> x + y;//略显复杂的类型推断
	 //BinaryOperator add = (x, y) -> x + y;//如果没有范型，代码则通不过编译
	
	//exercise
    public final static ThreadLocal<DateFormatter> formatter = ThreadLocal.withInitial(() -> new DateFormatter(new SimpleDateFormat("dd-MMM-yyyy")));

	
}
