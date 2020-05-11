package payRoll;

import org.joda.time.DateTime;

public class Paycheck {

  	public DateTime payDate;
  	public DateTime startDate;

  	public Paycheck(DateTime startDate, DateTime payDate) {
    	this.startDate = startDate;
    	this.payDate = payDate;
  	}
}