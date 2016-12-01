/**
 * 
 */
package designPattern.template_method.traditional;

import designPattern.template_method.ApplicationDenied;

/**
 * @author yan96in
 *
 */
public class PersonalLoanApplication extends LoanApplication{

	@Override
	protected void checkIncomeHistory() throws ApplicationDenied {
		
	}

	@Override
	protected void checkCreditHistory() {
		
	}

	@Override
	protected void checkIdentity() {
		
	}

}
