package payRoll;

import org.joda.time.DateTime;

public interface PaymentMode {
 	double getAmnt(Paycheck paycheck);

 	boolean isPayDate(DateTime payDate);
}