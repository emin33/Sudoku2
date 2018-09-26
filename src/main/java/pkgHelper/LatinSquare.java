package pkgHelper;

import java.util.Arrays;

import org.apache.commons.lang.ArrayUtils;

public class LatinSquare {
	private int[][] LatinSquare;
	private boolean skipZero;

	private int[] RemoveZeros(int[] arr) {
		while (ArrayUtils.contains(arr, 0))
			arr = ArrayUtils.removeElement(arr, 0);
		
		return arr;
	}
	
	public LatinSquare()
	{
		super();
		this.skipZero = false;
	}
	
	public LatinSquare(int[][] latinSquare) {
		super();
		LatinSquare = latinSquare;
	}
	
	public int[][] getLatinSquare() {
		return LatinSquare;
	}
	
	public void setLatinSquare(int[][] latinSquare) {
		LatinSquare = latinSquare;
	}
	
	public boolean hasDuplicates(int[] arr) {

		boolean hasDuplicates = false;
		int[] sortedArray = Arrays.copyOf(arr, arr.length);

		if (skipZero)
			sortedArray = RemoveZeros(sortedArray);

		Arrays.sort(sortedArray);

		for (int i = 0; i < sortedArray.length - 1; i++) {
			if (sortedArray[i] == sortedArray[i + 1]) {
				hasDuplicates = true;
				break;
			}
		}
		return hasDuplicates;
	}
	
	protected boolean hasDuplicates()
	{
		for (int i = 0; i < LatinSquare.length; i++) {
			if (hasDuplicates(getRow(i)))
				return true;
		}

		for (int j = 0; j < LatinSquare.length; j++) {
			if (hasDuplicates(getColumn(j)))
				return true;
		}
		
		return false;
	}
	
	public boolean doesElementExist(int[] arr, int iValue)
	{
		boolean doesElementExist = false;
		for(int i: arr)
		{
			if(i==iValue) {
				doesElementExist = true;
				break;
			}
		}
		
		return doesElementExist;
	}
	
	public boolean hasAllValues(int[] arr1,int[] arr2) {
		for(int x = 0; x < arr2.length; x++) {
			for(int y = 0; y < arr1.length; y++) {
				if(arr1[y] == arr2[y]) {
					return true;
				}
			}
		}
		return false;
	}
	
	public int[] getColumn(int col) {
		int[] Column = new int[LatinSquare.length];
		for(int row = 0; row < LatinSquare.length; row++) {
			Column[row] = LatinSquare[row][col];
		}
		return Column;
	}
	
	public int[] getRow(int row) {
		int[] theRow = new int[LatinSquare.length];
		theRow = LatinSquare[row];
		return theRow;
	}
	
	public boolean isLatinSquare() {
		boolean isLatinSquare = true;
		if (hasDuplicates())
			return false;
		
		
		for (int i = 1; i < LatinSquare.length; i++) {

			if (!hasAllValues(getRow(0), getRow(i))) {
				return false;
			}
		}

		for (int j = 1; j < LatinSquare.length; j++) {

			if (!hasAllValues(getColumn(0), getColumn(j))) {
				return false;
			}
		}

		return isLatinSquare;
	}
	
	public boolean ContainsZero() {
		for(int x = 0; x < LatinSquare.length; x++) {
			for(int y = 0; y < LatinSquare.length; y++) {
				if(LatinSquare[x][y] == 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void setskipZero(boolean bIgnoreZero) {
		this.skipZero = skipZero;
	}
	
	public boolean isskipZero() {
		return skipZero;
	}
}

