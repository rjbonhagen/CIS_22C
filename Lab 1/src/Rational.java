
public class Rational {
	int n;
	int d;
	
	Rational() {
		n = 1;
		d = 1;
	}

	Rational(int n, int d) {
			this.n = n;
			this.d = d;

			validState();
			normalize();
	}
	
	
	public int getNumerator() {
		validState();
		return n;

	}
	
	public int getDenominator() {
		validState();
		return d;
	}
	
	public Rational negate() {
		validState();
		Rational negated = new Rational(n, d);
		negated.n = -(negated.n);
		return negated;

	}
	
	public Rational invert() {
		validState();
		Rational inverted = new Rational(n, d);
		inverted.n = d;
		inverted.d = n;
		inverted.validState();
		inverted.normalize();
		return inverted;
    }

    public Rational add(Rational other) {
    	validState();
    	other.validState();
    	
    	int commonDenominator = lcm(this.d, other.d);
    	int thisNumerator = this.n * (commonDenominator / this.d);
    	int otherNumerator = other.n * (commonDenominator / other.d);
    	
    	
    	Rational result = new Rational();
    	result.n = thisNumerator + otherNumerator;
    	result.d = commonDenominator;
    	result.normalize();
    	
        return result;
    }    

    public Rational subtract(Rational other) {               
    	return add(other.negate());
    }

    public Rational multiply(Rational other) {       
    	Rational result = new Rational();
    	result.n = this.n * other.n;
    	result.d = this.d * other.d;
    	result.normalize();
    	return result;
     
    }
 
    public Rational divide(Rational other) {               
    	Rational result = new Rational();
    	result.n = this.n * other.d;
    	result.d = this.d * other.n;
    	result.validState();
    	result.normalize();
    	return result;
    }
    
    public String toString() {
    	return n + "/" + d;
    }
 
	private boolean validState() {
		if (d == 0) {
			throw new ZeroDenominatorException("Cannot divide by zero");
		}
		else return true;
	}
	
	private void normalize() {
		//negative in denominator goes to numerator
		// simplify with gcd
		int gcd = gcd(n, d);	
		n = n/gcd;
		d = d/gcd;
		
		// double negative turns positive
		// negative sign flipped to numerator
		if (d < 0) {
			n = -(n);
			d = -(d);
		}
	}
	
	private static int gcd(int a, int b) {
		if (b==0) return a;
		return gcd(b,a%b);
	}
	private static int lcm(int a, int b) {
	    return (a * b) / gcd(a, b);
	}
}






	