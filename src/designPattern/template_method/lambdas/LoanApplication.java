/**
 * 
 */
package designPattern.template_method.lambdas;

import designPattern.template_method.ApplicationDenied;

/**
 * @author yan96in
 * 员工申请贷款
 */
public class LoanApplication {
	private final Criteria identity;
	private final Criteria creditHistory;
	private final Criteria incomeHistory;

	public LoanApplication(Criteria identity, Criteria creditHistory, Criteria incomeHistory) {
		this.identity = identity;
		this.creditHistory = creditHistory;
		this.incomeHistory = incomeHistory;
	}

	public void checkLoanApplications() throws ApplicationDenied {
		identity.check();
		creditHistory.check();
		incomeHistory.check();
		reportFindings();
	}

	private void reportFindings() {
	}
}
