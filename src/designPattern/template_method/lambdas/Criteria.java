/**
 * 
 */
package designPattern.template_method.lambdas;

import designPattern.template_method.ApplicationDenied;

/**
 * @author yan96in
 *
 */
public interface Criteria {
	public void check() throws ApplicationDenied;
}
