package newclasslib;

import org.junit.Test;

import newclasslib.Child;
import newclasslib.ChildImpl;
import newclasslib.OverridingChild;
import newclasslib.OverridingParent;
import newclasslib.Parent;
import newclasslib.ParentImpl;

import static org.junit.Assert.assertEquals;

public class TestDefaultSubClassing {

	@Test
	public void parentDefaultUsed() {
		Parent parent = new ParentImpl();
		parent.welcome();
		assertEquals("Parent: Hi!", parent.getLastMessage());
	}

	// 调用了Child接口的客户代码
	@Test
	public void childOverrideDefault() {
		Child child = new ChildImpl();
		child.welcome();
		assertEquals("Child: Hi!", child.getLastMessage());
	}

	// 调用的是类中的具体方法，而不是默认方法
	@Test
	public void concreteBeatsDefault() {
		Parent parent = new OverridingParent();
		parent.welcome();
		assertEquals("Class Parent: Hi!", parent.getLastMessage());
	}

	// 类中重写的方法优先级高于接口中定义的默认方法
	@Test
	public void concreteBeatsCloserDefault() {
		Child child = new OverridingChild();
		child.welcome();
		assertEquals("Class Parent: Hi!", child.getLastMessage());
	}

}
