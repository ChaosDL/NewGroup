package mathObjects;

import java.util.ArrayList;
import java.util.Arrays;

public class Equation{

	private ArrayList<Term> leftSide;
	private ArrayList<Term> rightSide;
	private boolean cancelRight;
	private ArrayList<Double> solution;
	//TODO: Make getters for all of the above

	public Equation(ArrayList<Term> leftSide, ArrayList<Term> rightSide){
		this.leftSide = leftSide;
		this.rightSide = rightSide;
		//sets values of leftSide and rightSide only
	}

	public boolean isLinear(){
		if(!isSameVariable(leftSide, rightSide) || !checkExponent(leftSide, rightSide, 1)){
			return false;
		}
		return true;
		//returns 'true' if this equation is linear
		//and every term is either constant or has the same variable
	}
	private boolean isSameVariable(ArrayList<Term> side1, ArrayList<Term> side2){
		ArrayList<Term> temp = new ArrayList<Term>();
		temp.addAll(side1);
		temp.addAll(side2);
		String variable =  "";
		for(int i = 0; i < temp.size(); i++){
			Term t = temp.get(i);
			if(!(t.isConstant()) && variable.equals("")){
				variable = t.getVariable();
			}
			else if(!variable.equals(t.getVariable()) && !(t.isConstant())){
				return false;
			}
		}
		return true;
	}
	private boolean checkExponent(ArrayList<Term> side1, ArrayList<Term> side2, int expVal){
		ArrayList<Term> temp = new ArrayList<Term>();
		temp.addAll(side1);
		temp.addAll(side2);
		int highest = 0;
		for(int i =0; i < temp.size(); i++){
			Term t = temp.get(i);
			if(!(t.isConstant())){
				if(t.getExponent() > highest){
					highest = t.getExponent();
				}
				if(t.getExponent() < 0){
					return false;
				}
			}
		}
		if(highest != expVal){
			return false;
		}
		return true;
	}
	public boolean isQuadratic(){
		if(!isSameVariable(leftSide, rightSide) || !checkExponent(leftSide, rightSide, 2)){
			return false;
		}
		return true;
		//returns 'true' if this equation is quadratic
		//and every term is either constant or has the same variable
	}

	public boolean isSolveable(){
		if(!isLinear() && !isQuadratic()){
			return false;
		}
		return true;
		//returns 'true' if this equation is linear or quadratic, 'false' otherwise 
		//(because in this project we are not programming a means of solving anything other than linear and quadratic equations)
	}

	public boolean cancelRight(){
		Term highestLeft = getHighestDegreeTerm(leftSide);
		Term highestRight = getHighestDegreeTerm(rightSide);
		if(highestRight.getDegree() > highestLeft.getDegree() && highestRight.isPositive())return false;
		if(highestRight.getDegree() < highestLeft.getDegree() && highestRight.isPositive())return true;
		if(highestRight.getDegree() < highestLeft.getDegree() && !highestLeft.isPositive())return false;
		if(highestRight.getDegree() == highestLeft.getDegree() && highestLeft.getCoefficient() >= highestRight.getCoefficient())return true;
		if(highestRight.getDegree() < highestLeft.getDegree() && highestRight.getCoefficient() == 0)return true;
		else return false;
		//sets the value of cancelRight and
		//returns 'true' if the equation should be solved by subtracting terms from the right side, false if it is better to subtract terms on the left side
	}

	public String toString(){
		return getSideString(leftSide) + " = " + getSideString(rightSide);
		/**
		 *Take your time on this method!
		 *There are many things to consider:
		 *Every terms should be separated by a '+' UNLESS it has a negative coefficient. 
		 *When a term is negative, the negative sign will appear as a minus.
		 */
	}


	public static Term getHighestDegreeTerm(ArrayList<Term> side){
		int highest = side.get(0).getExponent();
		int index = 0;
		for(int i =0; i<side.size(); i++){
			if(side.get(i).getExponent() > highest){
				highest = side.get(i).getExponent();
				index = i;
			}
		}
		return side.get(index);
		//returns the term in the ArrayList that is the highest degree.
		//example
		//3x^2 + 4x^3 - 12x + x^2
		//will return 4x^3 
	}

	public String getSideString(ArrayList<Term> side){
		String s = "";
		try{
			s=side.get(0).toString();
		}
		catch(Exception e){
			s = "0";
		}
		for(int i = 1; i <side.size(); i++){
			if(side.get(i).isPositive())s+=" + " + side.get(i);//.toString();
			else s+= " - " + side.get(i).toString().replaceFirst("-", "");
		}
		return s;
	}

	/**

	 * NEW STUFF FOR 4.1

	 */

	/**

	 * adds the additiveInverse of everything on the sideBeingCancelled

	 * to both sides of the Equation

	 * @param sideBeingCanceled

	 */

	public void toZeroOnOneSide(ArrayList<Term> sideBeingCanceled){
		if(sideBeingCanceled.get(0).getCoefficient() == 0)return;
		ArrayList<Term> addIn = new ArrayList<Term>();
		for(Term t: sideBeingCanceled){
			addIn.add(t.getAddInverse());
		}
		for(Term u: addIn){
			leftSide.add(u);
			rightSide.add(u);
		}
	}



	/**

	 * 

	 * @param side - simplifies the specified side of the equation

	 * Notes: This method should check every Term on the specified side of the equation 

	 * with every other Term to check if they are like terms (use Term.areLikeTerms(Term s, Term t)

	 * If they are, it should reassign the current Term to the combined result (use Term.combine(Term s, Term t)

	 * and remove the one with which it combined.

	 * Finally, if the resulting term has a coefficient of zero should be removed.

	 * 

	 * For example, Suppose side contains 5x + 3 -5x. Call the three terms a, b and c, respectively

	 * 1. It checks to see if 5x and 3 (a and b) are like terms, they are not

	 * 2. It checks to see if 5x and -5x (a and c) are like terms, they are

	 * 3. Since 5x and -5x are like terms, a = Term.cobine(a, c) then leftSide.remove(c)

	 * 4. Now side contains 0x + 3. Since term a has a coefficient of zero, remove it.

	 * 5. Now side contains 3. 

	 * 

	 * ONE MORE NOTE: Since you will be removing items from side, use a standard for loop

	 * and remember that when something is moved, everything "slides over"

	 */

	public void simplify(ArrayList<Term> side){
		 for(int i = side.size()-1; i >=0; i--){
			 for(int j = i-1; j >= 0; j--){
				 if(Term.areLikeTerms(side.get(i), side.get(j))){
					 side.set(j, Term.combine(side.get(i), side.get(j)));
					 side.remove(i);
					 if(side.get(j).getCoefficient() == 0){
						 side.remove(j);
						 i--;
						 j--;
					 }
					 break;
				 }
			 }
		 }
	}


	/**

	 * 

	 * @param sideWithVariable - we can assume the side with a variable is of the form ax + b

	 * @return the solution

	 * 

	 * Example: 3x + 21 = 0

	 * 1. Identify the constant term in the sideWithVariable (21)

	 * 2. Identify the variable term in the sideWithVariable (3x)

	 * 3. Add the additive inverse of the constant Term to both sides of the equation (3x = -21)

	 * 4. Multiple both sides by the additive inverse of the coefficient of the variable term (.33333333)

	 */

	public void solveLinear(ArrayList<Term> sideWithVariable){

		/*
		simplify(sideWithVariable);
		Term x = sideWithVariable.get(0);
		Term c = sideWithVariable.get(1);
		String var;
		double xSolved;
		if(x.isConstant()){
			xSolved = (x.getAddInverse().getCoefficient())/(c.getCoefficient());
			var = c.getVariable();
		}//use multiply scalar to simplify this
		else{
			xSolved = (c.getAddInverse().getCoefficient())/(x.getCoefficient());
			var = x.getVariable();
		}
		leftSide = new ArrayList<Term>();
		rightSide = new ArrayList<Term>();
		if(cancelRight){
			leftSide.add(new Term(1, var, 1));
			rightSide.add(new Term(xSolved));
		}
		else{
			rightSide.add(new Term(1, var, 1));
			leftSide.add(new Term(xSolved));
		}
		solution = new ArrayList<Double>();
		solution.add(xSolved);
		*/
		simplify(sideWithVariable);
		Term x = sideWithVariable.get(0);
		Term c = new Term(0);
		if(sideWithVariable.size() == 2){
			c = sideWithVariable.get(1);
		}
		double xSolved;
		if(x.isConstant()){
			multiplyScalar(sideWithVariable, 1/c.getCoefficient());
			if(cancelRight){
				rightSide.add(x.getAddInverse());
				leftSide.remove(x);
			}
			else{
				leftSide.add(x.getAddInverse());
				rightSide.remove(x);
			}
		}//use multiply scalar to simplify this
		else{
			multiplyScalar(sideWithVariable, 1/x.getCoefficient());
			if(cancelRight){
				rightSide.add(c.getAddInverse());
				System.out.println(c.getAddInverse());
				leftSide.remove(c);
			}
			else{
				leftSide.add(c.getAddInverse());
				rightSide.remove(c);
			}
		}
		simplify(rightSide);
		simplify(leftSide);
	}

	public ArrayList<Term> solveQuadratic (ArrayList<Term> sideWithVariable){
		/*ArrayList<Integer> discrimAndCoeffs = getDiscriminate(sideWithVariable);
		int factorOne = 0;
		int factorTwo = 0;
		int[] answer = new int[2];
		if(discrimAndCoeffs.get(0) > -1){
			int b = discrimAndCoeffs.get(2);
			int ac = discrimAndCoeffs.get(1) * discrimAndCoeffs.get(3);
			ArrayList<Integer> factors = getFactor(ac);
			for(int  i =0; i<factors.size(); i++){
				for(int j=0; j<factors.size(); j++){
					if((factors.get(i) + factors.get(j) == b)&&(factors.get(i)*factors.get(j) == ac)){
						factorOne = factors.get(i);
						factorTwo = factors.get(j);
					}
				}
			}
			if(discrimAndCoeffs.get(1) == 1){
				answer[0] = factorOne;
				answer[1] = factorTwo;
			}
			else{
				
			}
		}
		return answer;*/
		//change so that equation is refactored into 4 terms, b term split up
		//write method to find gcf, incorporate into getFactor,
		//write method to arrange in standard form
		ArrayList<Term> theAnswersBois = new ArrayList<Term>();
		standardForm(sideWithVariable);
		Term exA = sideWithVariable.get(0);
		Term exB = new Term(0);
		Term exC = new Term(0);
		if(sideWithVariable.size() > 1)exB = sideWithVariable.get(1);
		if(sideWithVariable.size() > 2)exC = sideWithVariable.get(2);
		double d = getDiscriminate(sideWithVariable);
		if(d > 0){
			if((int)Math.sqrt((int)d)*(int)Math.sqrt((int)d) == (int)d){
				int[] factorsB = findPairs((int)(exA.getCoefficient() * exC.getCoefficient()), sideWithVariable);
				System.out.println(Arrays.toString(factorsB));
				sideWithVariable.remove(exB);
				sideWithVariable.add(1, new Term(factorsB[1], "x", 1));
				sideWithVariable.add(1, new Term(factorsB[0], "x", 1));
				theAnswersBois = greatestCF(sideWithVariable);
			}
			else{
				theAnswersBois.add(exA);
				theAnswersBois.add(exB);
				theAnswersBois.add(new Term(d));
			}
			
		}
		return theAnswersBois;
	}
	private ArrayList<Term> greatestCF(ArrayList<Term> sideWithVariable){
		ArrayList<Term> GCFS = new ArrayList<Term>();
		int firstFact = gcf((int)Math.abs(sideWithVariable.get(0).getCoefficient()), (int)Math.abs(sideWithVariable.get(1).getCoefficient()));
		int secondFact = gcf((int)Math.abs(sideWithVariable.get(2).getCoefficient()), (int)Math.abs(sideWithVariable.get(3).getCoefficient()));
		if(sideWithVariable.get(2).getCoefficient() < 0){
			secondFact *= -1;
		}
		int thirdFact = gcf((int)Math.abs(sideWithVariable.get(0).getCoefficient()), (int)Math.abs(sideWithVariable.get(2).getCoefficient()));
		int fourthFact = gcf((int)Math.abs(sideWithVariable.get(1).getCoefficient()), (int)Math.abs(sideWithVariable.get(3).getCoefficient()));
		if(sideWithVariable.get(2).getCoefficient() < 0){
			fourthFact *= -1;
		}
		GCFS.add(new Term(firstFact, "x", 1));
		GCFS.add(new Term(secondFact));
		GCFS.add(new Term(thirdFact, "x", 1));
		GCFS.add(new Term(fourthFact));
		return GCFS;
	}
	private int gcf(int a, int b)
	{
	    while (b > 0)
	    {
	        int temp = b;
	        b = a % b; // % is remainder
	        a = temp;
	    }
	    return a;
	}

	private int gcf(int[] input)
	{
	    int result = input[0];
	    for(int i = 1; i < input.length; i++) result = gcf(result, input[i]);
	    return result;
	}
	//only call when simplified to 2 or 3 terms
	private double getDiscriminate(ArrayList<Term> sideWithVariable){
/*		ArrayList<Integer> discrim = new ArrayList<Integer>();//discriminate will be i=0, a,b,c is 1,2,3
		//write a for each loop to find a, b, and c
		Term exA = sideWithVariable.get(0);
		Term exB = sideWithVariable.get(1);
		double a = exA.getCoefficient();
		double b = exB.getCoefficient();
		double c = sideWithVariable.get(2).getCoefficient();
//		double temp;
//		if(exA.getExponent() < exB.getExponent()){
//			temp = a;
//			a = b;
//			b = temp;
//		}
//		if(sideWithVariable.size() == 3){
//			Term exC = sideWithVariable.get(2);
//			c = exC.getCoefficient();
//			if(exA.getExponent() < exC.getExponent()){
//				temp = a;
//				a = c;
//				c = temp;
//			}
//			discrim.add((int)((b*b) - 4*a*c));
//		}
//		else{
//			discrim.add((int)(b*b));
//		}
		discrim.add((int)((b*b) - 4*a*c));
		discrim.add((int)a);
		discrim.add((int)b);
		discrim.add((int)c);
		return discrim;*/
		//assume standard form
		Term exA = sideWithVariable.get(0);
		Term exB = new Term(0);
		Term exC = new Term(0);
		if(sideWithVariable.size() > 1)exB = sideWithVariable.get(1);
		if(sideWithVariable.size() > 2)exC = sideWithVariable.get(2);
		double a = exA.getCoefficient();
		double b = exB.getCoefficient();
		double c = exC.getCoefficient();
		return (b*b - 4*a*c);
	}
	private void standardForm(ArrayList<Term> sideWithVariable){
		Term highestT = getHighestDegreeTerm(sideWithVariable);
		Term lowestT = getLowestDegreeTerm(sideWithVariable);
		if(sideWithVariable.indexOf(highestT) != sideWithVariable.indexOf(lowestT)){
			sideWithVariable.remove(lowestT);
			sideWithVariable.add(lowestT);
		}
		sideWithVariable.remove(highestT);
		sideWithVariable.add(0, highestT);
	}
	private Term getLowestDegreeTerm(ArrayList<Term> side){
		int expLow = 2;
		int indLow = 0;
		for(int i = 0; i < side.size(); i++){
			if(side.get(i).getExponent() <= expLow){
				expLow = side.get(i).getExponent();
				indLow = i;
			}
		}
		return side.get(indLow);
	}
	private ArrayList<Integer> getFactor(int num){
		ArrayList<Integer> factors = new ArrayList<Integer>();
		for(int i = 1; i <= Math.abs(num); i++){
			if(num%i == 0){
				factors.add(i);
				factors.add(-i);
			}
		}
		return factors;
		
	}
	private int[] findPairs(int num, ArrayList<Term> sideWithVariable){
		int[] factorsArray = new int[2];
		Term exA = sideWithVariable.get(0);
		Term exB = new Term(0);
		Term exC = new Term(0);
		if(sideWithVariable.size() > 1)exB = sideWithVariable.get(1);
		if(sideWithVariable.size() > 2)exC = sideWithVariable.get(2);
		ArrayList<Integer> factors = getFactor(num);
		for(int  i =0; i<factors.size(); i++){
			for(int j=0; j<factors.size(); j++){
				if((factors.get(i) + factors.get(j) == ((int)exB.getCoefficient()))&&(factors.get(i)*factors.get(j) == (num))){
					factorsArray[0] = factors.get(i);
					factorsArray[1] = factors.get(j);
				}
			}
		}
		return factorsArray;
		
	}
	/**

	 * 

	 * @param side

	 * @param scalar

	 * multiplies all Terms on the given side by the given scalar

	 * (Hint: use setCoefficient(double))

	 */

	public void multiplyScalar(ArrayList<Term> side, double scalar){
		for(Term b: side){
			b.setCoefficient(b.getCoefficient() * scalar);
		}

	}

	public ArrayList<Term> getLeftSide() {
		return leftSide;
	}

	public ArrayList<Term> getRightSide() {
		return rightSide;
	}

	public boolean isCancelRight() {
		return cancelRight;
	}

	public ArrayList<Double> getSolution() {
		return solution;
	}
	public void setCancelRight(boolean cancelRight) {
		this.cancelRight = cancelRight;
	}

}
