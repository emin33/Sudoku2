package pkgGame;

import pkgHelper.LatinSquare;

public class Sudoku extends LatinSquare {
	private int iSize;
	private int iSqrtSize;
	
	public Sudoku(int[][] puzzle) throws Exception {
		super(puzzle);
		double SQRT = puzzle.length;
		this.iSize = puzzle.length;
		if(Math.floor(SQRT)==SQRT && Double.isInfinite(SQRT) == false) {
			this.iSqrtSize = (int)SQRT;
		}
		
		else {
			throw new Exception("Size Invalid");
		}
	}
	
	public boolean hasDuplicates()
	{
		if (super.hasDuplicates())
			return true;
		
		for (int k = 0; k < this.getPuzzle().length; k++) {
			if (super.hasDuplicates(getRegion(k))) {
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
	
	public boolean isSudoku() {

		this.setbIgnoreZero(false);
		
		if (hasDuplicates())
			return false;
		
		if (!super.isLatinSquare())
			return false;
		
		for (int c = 1; c < super.getLatinSquare().length; c++) {

			if (!hasAllValues(getRow(0), getRegion(c))) {
				return false;
			}
		}

		if (ContainsZero()) {
			return false;
		}

		return true;
	}
	
	public boolean isIncompleteSudoku() {

		this.setbIgnoreZero(true);
		
		if (hasDuplicates())
			return false;

		if (!ContainsZero()) {
			return false;
		}
		return true;

	}
}
