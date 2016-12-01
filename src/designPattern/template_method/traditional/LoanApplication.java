/**
 * 
 */
package designPattern.template_method.traditional;

import designPattern.template_method.ApplicationDenied;

/**
 * @author yan96in
 *
 */
public abstract class LoanApplication {
	public void checkLoanApplication() throws ApplicationDenied{
		checkIdentity();//检查身份
		checkCreditHistory();//检查信用
		checkIncomeHistory();//检查收入
		reportFindings();//生成
	}

	/**
	 * 
	 */
	private void reportFindings() {
		
	}

	/**
	 * 抽象方法不能用private修饰
	 */
	protected abstract void checkIncomeHistory() throws ApplicationDenied;

	/**
	 * 
	 */
	protected abstract void checkCreditHistory();

	/**
	 * 
	 */
	protected abstract void checkIdentity();
	
}
