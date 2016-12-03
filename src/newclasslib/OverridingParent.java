package newclasslib;

public class OverridingParent extends ParentImpl {
	// 重写welcome默认实现的父类
	@Override
	public void welcome() {
		message("Class Parent: Hi!");
	}

}