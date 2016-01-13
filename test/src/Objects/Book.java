package Objects;

import java.awt.Color;

public class Book {

	private String title;
	private int numberOfPages;
	private Person author;//custom class in this package
	private Color jacketColor;//class for java.awt
	private boolean wasLitOnFire;
	private int height;
	private int thickness;
	boolean checkedOut;
	long checkOutDate;
	long dueDate;
	static public String[] conditions = {"perfect condition", "Almost perfect", "pretty good", "ok", "worn", "very worn", "terrible"};
	String description;
	int accumulatedUse;
	public boolean isCheckedOut() {
		return checkedOut;
	}

	public void setCheckedOut(boolean checkedOut) {
		this.checkedOut = checkedOut;
	}

	public long getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(long checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public long getDueDate() {
		return dueDate;
	}

	public void setDueDate(long dueDate) {
		this.dueDate = dueDate;
	}

	public int getHeight() {
		return height;
	}

	public int getThickness() {
		return thickness;
	}

	public Book(String title, int numPages, Person author){
		this.title  = title;
		this.numberOfPages = numPages;
		this.author = author;
		jacketColor = Color.gray;
		wasLitOnFire = false;
		height = (int)(Math.random() *100) + 150;
		thickness = (int)(numberOfPages/10);
		checkedOut = false;
		checkOutDate = 0;
		dueDate = 0;
		accumulatedUse = 0;
		description = conditions[0];
	}
	
	public static String[] getConditions() {
		return conditions;
	}

	public String getDescription() {
		return description;
	}

	public int getAccumulatedUse() {
		return accumulatedUse;
	}
	public void updateCondition(long timeOfReturn){
		if(timeOfReturn < checkOutDate){return;}
		accumulatedUse += ((timeOfReturn - checkOutDate)/1000) + 30;
		int x = (int)accumulatedUse/30;
		if(x > 6){
			description = conditions[6];
		}
		else{
			description = conditions[x];
		}
	}

	public long getSecondsRemaining(){
		long x = (dueDate - System.currentTimeMillis()) / 1000;
		return x;	
	}
	
	public Color getJacketColor() {
		return jacketColor;
	}

	public void setJacketColor(Color jacketColor) {
		this.jacketColor = jacketColor;
	}

	public String toString(){
		if(wasLitOnFire){
			return "\"" + title + "\", by an author you can't make out"; 
		}
		
		return "\"" + title + "\", by " + author + ". " + numberOfPages + " pages"; 
	}
	//getters
	public String getTitle(){
		return title;
	}
	
	public int getNumberOfPages() {
		return numberOfPages;
	}

	public Person getAuthor() {
		return author;
	}

	public int getNumberofPages(){
		return numberOfPages;
	}
	public void setOnFire(){
		jacketColor = Color.black;
		title = "The title of this book is too charred to make out";
		wasLitOnFire = true;
	}
}
