package Objects;

public class Balance {
	
	private double amount;
	private long lastWorked;
	public Balance(){
		amount = 0;
		lastWorked = 0;
	}
	public double getAmount() {
		return ((int)(amount*100))/100;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public long getLastWorked() {
		return lastWorked;
	}
	public void setLastWorked(long lastWorked) {
		this.lastWorked = lastWorked;
	}
	public void subtractLateFees(long timeOverdue){
		amount -= (int)timeOverdue/1000;
	}
	public boolean canWork(long time){
		if(time - lastWorked >= 10000){
			return true;
		}
		else{
			return false;
		}
	}
	public String earnMoney(long time){
		if(canWork(time)){
			amount += 5;
			lastWorked = time;
			return "did some work at the library and earned $5";
		}
		else{
			return "can not do a double shift! Wait until the first job is done!";
		}
		
	}
	
}
