package algos;

import java.util.Arrays;

public class MergeSort {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {-3,0,5,2,1,3,6,8,6,-5};
		array = mergeSort(array);
		System.out.println(Arrays.toString(array));
	}
	public static int[] merge(int[] array1, int[] array2){
		int[] merged = new int[array1.length + array2.length];
		int j=0,k = 0;
		for(int i = 0; i < merged.length;i++){
			merged[i] = (j<array1.length && k<array2.length)?
					((array1[j] < array2[k])?array1[j++]:array2[k++])
					:
					(j<array1.length)?array1[j++]:array2[k++];
		}
		return merged;
	}
	
	public static int[] mergeSort(int[] array){
		if(array.length == 1)return array;
		int[] fHalf = new int[array.length/2];
		int[] sHalf = new int[array.length-fHalf.length];
		int k = 0;
		for(int i = 0; i<array.length;i++){
			if(k<fHalf.length)fHalf[i] = array[k++];
			else sHalf[i-fHalf.length] = array[k++];
		}
		return merge(mergeSort(fHalf),mergeSort(sHalf));
	}
}
