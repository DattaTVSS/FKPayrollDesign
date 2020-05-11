package payRoll;

import java.util.HashMap;
import org.joda.time.DateTime;

public class HourlyMode implements PaymentMode {

	private double hourlyRate = 0;
    private HashMap<DateTime,Double> timecards = new HashMap<DateTime,Double>();

    public HourlyMode(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void changerate(double newRate){
    	this.hourlyRate = newRate;
    }

    public void addTimeCard(DateTime date, double workingHours) {
        timecards.put(date, workingHours);
    }

    @Override
    public double getAmnt(Paycheck paycheck) {
	    double salary = 0;
	    for(DateTime date = paycheck.startDate; date.isBefore(paycheck.payDate.plusDays(1));date = date.plusDays(1)) {
	        if(timecards.get(date) != null) {
	         	double hours = timecards.get(date);
	          	salary += hourlyRate * hours;
	          	if (hours > 8.0)
	            	salary += hourlyRate * (hours - 8) / 2.0;
	        }
	    }
	    timecards.clear();
	    return salary;
    }

    @Override
	public boolean isPayDate(DateTime payDate) {
    	return payDate.getDayOfWeek() == 5;
  	}
}