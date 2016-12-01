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

	Macro macro = new Macro();
	macro.record(new Open(editor));
	
	macro.record(() -> editor.save());
	
	macro.record(editor::close);
	macro.run();

	        editor.check();
	    }

}
