package newclasslib;

public class MusicalCarriage implements Carriage, Jukebox {
	// 实现rock方法
	@Override
	public String rock() {
		return Carriage.super.rock();//增强的super语法
	}

}