/**
 * 
 */
package designPattern.observer;

import javax.xml.stream.events.Namespace;

/**
 * @author yan96in
 *
 */
public class Nasa implements LandingObserver {

	@Override
	public void observerLanding(String name) {
		if(name.contains("Appllo")){
			System.out.println("We made it!");
		}
	}

}
