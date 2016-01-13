package Objects;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;

public class Bookshelf {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Person author1 = new Person("noah", "webster", true);
		Person author2 = new Person("anthony", "burgess", true);
		Person author3 = new Person ("philip", "k", "dick", true);
		Book book1 = new Book("Dictionary", 1001, author1);
		Book book2 = new Book("Clockwork Orange", 749, author2);
		Book book3 = new Book("Do Androids Dream of Electric Sheep", 500, author3);
		book1.setJacketColor(Color.BLUE);
		book2.setJacketColor(Color.ORANGE);
		book3.setJacketColor(Color.GRAY);
		ArrayList<Book> shelf = new ArrayList<Book>();
		shelf.add(book1);
		shelf.add(book2);
		shelf.add(book3);
		shelf.add(new Book("The Man in the High Castle", 600, author3));
		Book book5 = new Book("The Minority Report", 589, author3);
		shelf.add(0, book5);
		shelf.get(3).setJacketColor(Color.GREEN);
		shelf.get(4).setJacketColor(Color.YELLOW);
		Collections.sort(shelf, new Comparator<Book>(){
		    public int compare(Book a, Book b) {

		        return a.getHeight() - b.getHeight();
		    }
		});
		printAll(shelf);
		ArrayList<Person> libraryCardHolders = new ArrayList<Person>();
		libraryCardHolders.add(new Person("john", "doe", false));
		libraryCardHolders.add(new Person("max", "boss", true));
		libraryCardHolders.add(new Person("michael", "kart", true));
		Library lib = new Library(shelf, libraryCardHolders);//use "books" or "shelf" whatever your ArrayList is 

		lib.setSize(new Dimension(500,500));

		lib.setVisible(true);

		lib.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public static void sortByAuthor(final boolean ascending, ArrayList<Book> list){
		Collections.sort(list, new Comparator<Book>(){
		    public int compare(Book a, Book b) {
		    	String aLast = a.getAuthor().getLastName();
		    	String bLast = b.getAuthor().getLastName();
		    	if(ascending)return aLast.compareTo(bLast);
		    	return bLast.compareTo(aLast);
		    }
		});
	}
	public static void sortByHeight(final boolean ascending, ArrayList<Book> list){
		Collections.sort(list, new Comparator<Book>(){
		    public int compare(Book a, Book b) {
		    	if(ascending)return a.getHeight() - b.getHeight();
		    	return b.getHeight() - a.getHeight();
		    }
		});
	}
	public static void sortByTitle(final boolean ascending, ArrayList<Book> list){
		Collections.sort(list, new Comparator<Book>(){
		    public int compare(Book a, Book b) {
		    	String aTitle = a.getTitle();
		    	String bTitle = b.getTitle();
		    	if(ascending)return aTitle.compareTo(bTitle);
		    	return bTitle.compareTo(aTitle);
		    }
		});
	}


	public static void sortByPageNumber(final boolean ascending, ArrayList<Book> list){
		Collections.sort(list, new Comparator<Book>(){
		    public int compare(Book a, Book b) {
		    	if(ascending)return a.getNumberofPages() - b.getNumberofPages();
		    	return b.getNumberofPages() - a.getNumberofPages();
		    }
		});
	}



	private static void printAll(ArrayList list){
		for(int i = 0; i < list.size(); i++){
			System.out.println(list.get(i));
		}
	}
	private static void arrayListStuff(ArrayList<Book> shelf){
//		/*
//		 * <Book> is generic type, save ourselves trouble 
//		 * of type casting
//		 * indexed
//		 * can't make an ArrayList of primitives (no int, boolean, etc)
//		 * if you wish to make an ArrayList of ints,
//		 * you use the wrapper class
//		 * int is Integer, double is Double, etc
//		 */
//		//no specified index, goes to first unused
//		//get something from an ArrayList
//		//using a for-each loop
//		for(Book b: shelf){
//			System.out.println(b);
//		}
//		//adding an Object at specified indices
//		
//		//shelf.remove(book1);
//		/*
//		for(Book b: shelf){
//			if((b.toString()).contains("the")){
//				shelf.remove(b);
//			}
//		}
//		*/
//		/*
//		for(int i = 0; i <shelf.size(); i++){
//			while(i < shelf.size() && shelf.get(i).toString().contains("The")){
//				shelf.remove(i);
//			}
//		}*/
//		//length -> size()\
//		System.out.println("The size of the shelf is " + shelf.size() + " books");
//		//standard for loop
//		for(int i = 0; i <shelf.size(); i++){
//			System.out.println(shelf.get(i));
//		}
//		if(shelf.contains(book2)){
//			System.out.println(book2.getTitle() + " is book# " + shelf.indexOf(book2) + " on the shelf.");	
//		}
//		ArrayList<Book> creepyBooks = new ArrayList<Book>();
//		for(Book b:shelf){
//			if(b.getAuthor().toString().equals("philip k dick")){
//				creepyBooks.add(b);
//				b.setOnFire();
//			}
//		}
//		printAll(creepyBooks);
	}

}
