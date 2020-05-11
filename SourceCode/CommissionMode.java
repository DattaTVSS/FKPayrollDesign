package payRoll;

import java.util.HashMap;
import org.joda.time.DateTime;

public class CommissionMode implements PaymentMode{

    public double commissionrate;
    private HashMap<DateTime, Double> salesReceipts = new HashMap<DateTime, Double>();

    public CommissionMode(double commissionrate) {
        this.commissionrate = commissionrate;
    }

    public void addSalesReceipt(DateTime date, double amount) {
        salesReceipts.put(date, amount);
    }

    @Override
    public double getAmnt(Paycheck paycheck) {
        double salary = 0;
        for(DateTime date = paycheck.startDate; date.isBefore(paycheck.payDate.plusDays(1));date = date.plusDays(1)) {
            if (salesReceipts.get(date) != null) {
                double amount = salesReceipts.get(date);
                double commission = this.commissionrate * amount / 100.0;
                salary += commission;
            }
        }
        return salary;
    }

    @Override
    public boolean isPayDate(DateTime payDate) {
        return payDate.getDayOfWeek() == 5 && payDate.getWeekOfWeekyear() % 2 == 0;
    }
}
