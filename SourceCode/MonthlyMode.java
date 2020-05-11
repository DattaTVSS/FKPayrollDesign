package payRoll;

import org.joda.time.DateTime;

public class MonthlyMode implements PaymentMode {

    public double salary;

    public MonthlyMode(double salary) {
        this.salary = salary;
    }

    @Override
    public double getAmnt(Paycheck paycheck) {
    	return salary;
    }

    @Override
    public boolean isPayDate(DateTime date) {
    	return date.plusMonths(1).getMonthOfYear() == date.plusDays(1).getMonthOfYear();
  	}
}