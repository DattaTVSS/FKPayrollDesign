package payRoll;

import java.util.HashMap;
import org.joda.time.DateTime;

public class Union{

    private HashMap<DateTime, Double> charges = new HashMap<DateTime, Double>();

    public double getServiceCharge(DateTime date) {
        return charges.get(date);
    }

    public void addServiceCharge(DateTime date,double charge) {
        charges.put(date,charge);
    }

    // @Override
    public double calculateDeductions(Paycheck paycheck) {
        double deductions = 0;
        for(DateTime date = paycheck.startDate; date.isBefore(paycheck.payDate.plusDays(1));date = date.plusDays(1)) {
            if (charges.get(date) != null) {
                deductions += charges.get(date);
            }
        }
        return deductions;
    }
}