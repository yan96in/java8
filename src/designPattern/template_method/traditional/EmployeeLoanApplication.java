/**
 * 
 */
package designPattern.template_method.traditional;

/**
 * @author yan96in
 *
 */
public class EmployeeLoanApplication extends PersonalLoanApplication{
	@Override
    protected void checkIncomeHistory() {
        // 这是自己人!
    }
}
