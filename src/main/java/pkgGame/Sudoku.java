package pkgGame;

import pkgHelper.LatinSquare;

public class Sudoku extends LatinSquare {
	//for some reason imported LatinSquare not coming through so all methods from that class error
	private int iSize;
	private int iSqrtSize;
	
	public Sudoku(int iSize) throws Exception{
		this.iSize = iSize;

		double sqrt = Math.sqrt(iSize);
		if (sqrt == Math.floor(sqrt) && !Double.isInfinite(sqrt)){
			this.iSqrtSize = (int)sqrt;
		} 
		else{
			throw new Exception("Invalid size");
		}
	}
	
	public Sudoku(int[][] puzzle) throws Exception{
		super(puzzle);
		double SQRT = puzzle.length;
		this.iSize = puzzle.length;
		if(Math.floor(SQRT)==SQRT && Double.isInfinite(SQRT) == false){
			this.iSqrtSize = (int)SQRT;
		}
		else{
			throw new Exception("Size Invalid");
		}
	}
	
	public boolean hasDuplicates()
	{
		if (super.hasDuplicates())
			return true;
		
		for(int x = 0; x < this.getPuzzle().length; x++) {
			if (super.hasDuplicates(getRegion2(x))) {
				return true;
			}
		}
		return false;
	}

	public int[][] getPuzzle() {
		return super.getLatinSquare();
	}
	
	public int[] getRegion(int col, int row) {

		int a = (col / iSqrtSize)+((row / iSqrtSize)*iSqrtSize);

		return getRegion2(a);
	}
	
	public int[] getRegion2(int r) {

		int[] reg = new int[super.getLatinSquare().length];

		int x = (r % iSqrtSize) * iSqrtSize;
		int y = (r / iSqrtSize) * iSqrtSize;
		int jMax = x + iSqrtSize;
		int iMax = y + iSqrtSize;
		int iCnt = 0;

		for (; y < iMax; y++) {
			for (x = (r % iSqrtSize) * iSqrtSize; x < jMax; x++) {
				reg[iCnt++] = super.getLatinSquare()[y][x];
			}
		}

		return reg;
	}
	
	public boolean isSudoku(){
		this.setskipZero(false);
		
		if (hasDuplicates())
			return false;
		
		if (!super.isLatinSquare())
			return false;
		
		for (int c = 1; c < super.getLatinSquare().length; c++) {

			if (!hasAllValues(getRow(0), getRegion2(c))) {
				return false;
			}
		}

		if(ContainsZero()) {
			return false;
		}
		return true;
	}
	
	public boolean isIncompleteSudoku() {
		this.setskipZero(true);
		
		if (hasDuplicates())
			return false;
		if (!ContainsZero()) {
			return false;
		}
		return true;
	}
}
