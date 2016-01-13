package Objects;

import java.util.ArrayList;

public class Person {
	private String firstName;
	private String middleName;
	private String lastName;
	public static int MAX_ALLOWED_BOOKS = 3;
	private ArrayList<Book> checkedOutBooks = new ArrayList<Book>();
	boolean male;
	Balance balance;
	public Balance getBalance() {
		return balance;
	}
	public boolean isMale() {
		return male;
	}
	public ArrayList<Book> getCheckedOutBooks() {
		return checkedOutBooks;
	}
	//constructor
	public Person(String firstName, String lastName, boolean genderIsMale){
		this.firstName = firstName;
		middleName = "";//default
		this.lastName = lastName;
		male = genderIsMale;
		balance = new Balance();
		//field vs local
	}
	public String getFirstName() {
		return firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public Person(String firstName, String middleName, String lastName, boolean genderIsMale){
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		male = genderIsMale;
		balance = new Balance();
	}
	
	public String toString(){
		return firstName + " " + middleName + " " + lastName;
	}
	public void checkOutBook(Book bk){
		bk.setCheckedOut(true);
		bk.setCheckOutDate(System.currentTimeMillis());
		bk.setDueDate(System.currentTimeMillis()+30000);
		checkedOutBooks.add(bk);
	}
	public void returnBook(Book bk){
		bk.setCheckedOut(false);
		bk.updateCondition(System.currentTimeMillis());
		if(System.currentTimeMillis() > bk.getDueDate()){
			balance.subtractLateFees(System.currentTimeMillis() - bk.getDueDate());
		}
		bk.setCheckOutDate(0);
		bk.setDueDate(0);
		checkedOutBooks.remove(bk);
	}
	public void renewBook(Book bk){
		bk.setDueDate(System.currentTimeMillis()+30000);
	}
	public String getGenderPosessivePronoun(){
		if(male){
			return "his";
		}
		else{
			return "her";
		}
	}
	public String getLibraryDescription(){
		return firstName + " " + lastName + " is doing stuff. balance is " + balance.getAmount();
	}
}
