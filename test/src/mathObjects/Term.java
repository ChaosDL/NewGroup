package mathObjects;

public class Term{
	double coefficient;
	String variable;
	int exponent;
	boolean constant;//this is true if a Term has no variable expression
	//TODO: Write getters for all of the above fields

	public Term(double coefficient, String var, int exp){
		this.coefficient = coefficient;
		this.variable = var;
		this.exponent = exp;
		if(coefficient == 0){
			variable = "";
			exponent = 0;
		}
		if(var.equals("")){
			constant = true;
		}
		else{constant = false;}
		//set the value of all the fields
		//if variable is non-empty, sets constant to 'false'
	}

	/**
	 *a constructor for constant: 
	 */
	public Term(double constant){
		coefficient = constant;
		variable = "";
		exponent = 0;
		this.constant = true;
		//sets coefficient to constant, variable to "", exponent to "0" and constant to 'true')
	}
	/**
	 *Write getters for each field
	 *Note that the getter for the boolean should be called 'isConstant'
	 */ 

	public Term getAddInverse(){
		return new Term(-coefficient, variable, exponent);
		//returns the additive inverse of this Term
	}

	public int getDegree(){
		return exponent;
		//returns the exponent
	}

	public boolean isPositive(){
		if(coefficient >= 0){
			return true;
		}
		else{return false;}
		//returns true if the coefficient is positive (or zero), false otherwise
	}	

	public String toString(){
		String str = "";
		if(!constant && (coefficient == -1.0 || coefficient == 1.0)){
			if(coefficient == -1.0)str += "-";
		}
		else{
			str += coefficient;
		}
		//		String cox = Double.toString(coefficient);
		//		String co = (cox).substring(0,(cox).indexOf("."));
		//		if(coefficient == 1.0)co = "";
		//		if(coefficient == -1.0)co = "-";
		//		
		//		String expo = Integer.toString(exponent);
		str += variable;
		if(exponent == 1 || exponent == 0)str += "";
		else{
			str += "^" + exponent;
		}
		return str;
		/**
		 *Some tips to consider:
		 *doubles always print with trailing zeros (i.e. 2.0) This is not desireable
		 *If a term has a coefficient of 1 or -1, the 1 should not be shown. 
		 *If a term has an exponent of 1, the 1 should not be shown
		 *For exponents, use '^'. The GUI will change it into superscript.
		 */
	}
	/**

	 * NEW STUFF FOR 4.1

	 * */

	/**

	 * 

	 * 

	 * @param s

	 * @param t

	 * @return 'true' if s and t are like terms (same variable and exponent)

	 */

	public static boolean areLikeTerms(Term s, Term t){
		if(s.getVariable().equals(t.getVariable()) && s.getExponent() == t.getExponent()){
			return true;
		}
		else return false;
	}



	/**

	 * returns a new Term with same variable and exponent as s and t but combined coefficient

	 * @param s

	 * @param t

	 * @return

	 */

	public static Term combine(Term s, Term t){
	
		return new Term(s.getCoefficient() + t.getCoefficient(), s.getVariable(), s.getExponent());
	}



	public void setCoefficient(double d) {
		coefficient = d;
	}

	public double getCoefficient() {
		return coefficient;
	}

	public String getVariable() {
		return variable;
	}

	public int getExponent() {
		return exponent;
	}

	public boolean isConstant() {
		return constant;
	}

}
