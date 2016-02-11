package towersOfHanoi;

public class Hanoi {
	public static void main(String[] args) {
		toH(3,"A","B","C");
	}
	public static void toH(int disks, String from, String other, String to){
		if(disks == 1){System.out.println(disks-1 + ": " + from + "-" + to);return;}
		toH(disks-1,from,to,other);
		//toH(1,from,other,to);
		System.out.println(disks-1 + ": " + from + "-" + to);
		toH(disks-1,other,from,to);
	}
}
