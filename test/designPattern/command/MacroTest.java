/**
 * 
 */
package designPattern.command;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author yan96in
 *
 */
public class MacroTest {

	 @Test
	    public void classBasedCommand() {
	        MockEditor editor = new MockEditor();
	 //使用命令者模式构建宏
	Macro macro = new Macro();
	macro.record(new Open(editor));
	//使用Lambda表达式构建宏，此时不需要命令类。
	macro.record(() -> editor.save());
	//使用方法引用
	macro.record(editor::close);
	macro.run();

	        editor.check();
	    }

}
