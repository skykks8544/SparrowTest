/* NUM02-J. Ensure that division and modulo operations do not result in divide-by-zero errors */
/* DIVIDE_BY_ZERO */
package cert;

public class NUM02_J {

	private int checkRange(int n) {
		if (n < -10 || n > 10) throw new IllegalArgumentException("range error");
		else return n;
	}

	private int computeDivider(int d) {
	  return d+2;	
	}
	
	public int testComplex1() {
		int divider = computeDivider(-2);
		int checked = checkRange(divider);
		return 10 / checked;  //@violation DIVIDE_BY_ZERO
	}

	private int checkRangeSafe(int n) {
		if (n == 0 || (n < -10 || n > 10)) throw new IllegalArgumentException("range error");
		else return n;
	}
	
	public int testComplexSafe() {
		int divider = computeDivider(-2);
		int checked = checkRangeSafe(divider);
		return 10 / checked; /* SAFE */
	}
}
