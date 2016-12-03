/**
 * 
 */
package designPattern.command;

/**
 * @author yan96in
 *
 */
public class Save implements Action {
	private final Editor editor;
	
	public Save(Editor editor){
		this.editor=editor;
	}
	@Override
	public void perform() {
		editor.save();
	}

}
