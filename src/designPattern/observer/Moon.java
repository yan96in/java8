/**
 * 
 */
package designPattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yan96in
 *
 */
public class Moon {
	private final List<LandingObserver> observers = new ArrayList<>();

	public void land(String name) {
		for (LandingObserver observer : observers) {
			observer.observerLanding(name);
		}
	}

	public void startSpying(LandingObserver observer) {
		observers.add(observer);
	}

	public static void main(String[] args) {
		classBasedExample();
		lambdaBasedExample();
	}

	// 使用类的方式构建
	private static void classBasedExample() {
		Moon moon = new Moon();
		moon.startSpying(new Nasa());
		moon.startSpying(new Aliens());

		moon.land("An asteroid");
		moon.land("Apollo 11");
	}

	// 使用Lambda表达式构建
	private static void lambdaBasedExample() {
		Moon moon = new Moon();
		moon.startSpying(name -> {
			if (name.contains("Apollo"))
				System.out.println("We made it!");
		});
		moon.startSpying(name -> {
			if (name.contains("Apollo"))
				System.out.println("They're distracted, lets invade earth!");
		});

		moon.land("An asteroid");
		moon.land("Apollo 11");
	}
}
