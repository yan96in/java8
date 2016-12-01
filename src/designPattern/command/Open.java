/**
 * 
 */
package designPattern.command;

/**
 * @author yan96in
 *
 */
public class Open implements Action {
	private final Editor editor;
	
	public Open(Editor editor){
		this.editor=editor;
	}
	@Override
	public void perform() {
		editor.open();
	}

}
