package arrayReview;

public class sevenandeight {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static int[][] posToNed2D(int[][] array, int row, int coloumn){
		if(row>0){
			array[row-1][coloumn] *= -1;
		}
		if(row<array.length-1){
			array[row+1][coloumn] *= -1;
		}
		if(coloumn>0){
			array[row][coloumn-1] *= -1;
		}
		if(coloumn<array[row].length-1){
			array[row][coloumn+1] *= -1;
		}
		return array;
	}
	public static int[] rowMajorOrder(int[][] array){
		int[] oned= new int[array.length*array.length];
		int count = 0;
		for(int i=0; i<array.length;i++){
			for(int j=0;j<array[i].length;j++){
				oned[count++] =array[i][j];
			}
		}
		return oned;
	}

}
