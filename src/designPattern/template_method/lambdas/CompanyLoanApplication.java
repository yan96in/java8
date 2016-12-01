/**
 * 
 */
package designPattern.template_method.lambdas;

/**
 * @author yan96in
 *
 */
public class CompanyLoanApplication extends LoanApplication {
	public CompanyLoanApplication(Company company) {
		super(company::checkIdentity, 
				company::checkHistoricalDebt, 
				company::checkProfitAndLoss);
	}

}
