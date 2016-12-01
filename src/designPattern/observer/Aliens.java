/**
 * 
 */
package designPattern.observer;

/**
 * @author yan96in
 *
 */
public class Aliens implements LandingObserver{

	@Override
	public void observerLanding(String name) {
		if(name.contains("Applolo")){
			System.out.println("They're distracted,lets invade earth!");
		}
	}
	
}
