package payRoll;

import java.util.HashMap;
import org.joda.time.DateTime;

public class Payroll{
	private static HashMap<Integer,Employee> emps = new HashMap<Integer,Employee> ();
	private static HashMap<Integer,Employee> union_mems = new HashMap<Integer,Employee>();

	public void addEmployee(Employee e){
		if(e != null)
			emps.put(e.getid(),e);
	}

	public void removeEmployee(int id){
		if(emps.containsKey(id))
			emps.remove(id);
	}

	public void posttimecard(int id,double hours,DateTime dt){
		Employee e = emps.get(id);
		if(e != null){
			HourlyMode h = (HourlyMode)e.classification;
			if(e.eTyp == EmpType.HOURLY)
				h.addTimeCard(dt,hours);
			else
				System.out.println("Employee "+id+" is not an Hourly Employee");
		}
	}

	public void postsalesreceipt(int id,double salevalue,DateTime dt){
		Employee e = emps.get(id);
		if(e != null){
			if(e.eTyp == EmpType.COMMISSION){
				CommissionMode c = (CommissionMode)e.classification;
				c.addSalesReceipt(dt,salevalue);
			}
			else if(e.intoCommission){
				CommissionMode c = (CommissionMode)e.cmode;
				c.addSalesReceipt(dt,salevalue);
			}
			else 
				System.out.println("Employee "+id+" is not a Commissioned Employee");
		}
	}


}