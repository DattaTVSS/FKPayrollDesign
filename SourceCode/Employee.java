package payRoll;

import org.joda.time.DateTime;

public class Employee {
    private int empId;
    public String name;
    public EmpType eTyp; 
    public PaymentMode classification;
    // public boolean intoCommission = false;
    // public CommissionMode cmode = null;
    // public boolean inUnion;
    // public Union member = null;

    public Employee(int empId, String name,EmpType em,double wage) {
        this.empId = empId;
        this.name = name;
        this.eTyp = em;
        inUnion = false;
        if(em == EmpType.HOURLY){
        	HourlyMode h = new HourlyMode(wage);
        	this.classification = h;
        }
        else if(em == EmpType.MONTHLY){
        	MonthlyMode m = new MonthlyMode(wage);
        	this.classification = m;
        }
        else if(em == EmpType.COMMISSION){
        	CommissionMode cm = new CommissionMode(wage);
        	this.classification = cm;
        }
    }

    public int getid(){
    	return empId;
    }

    public void addtoUnion(Union u){
    	this.inUnion = true;
    	this.member = u;
    }

    public void addtoCommission(double cmrate){
    	this.intoCommission = true;
    	CommissionMode cm = new CommissionMode(cmrate);
        this.cmode = cm;
    }

    public double payment(Paycheck paycheck) {
		double totalPay = classification.getAmnt(paycheck);
		double deductions = 0;
		if(inUnion)
			deductions = member.calculateDeductions(paycheck);
		double netPay = totalPay - deductions;
		return netPay;
    }


    public boolean isPayDate(DateTime payDate) {
    	return classification.isPayDate(payDate);
    }

}