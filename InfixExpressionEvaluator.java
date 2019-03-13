package stacks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*******************************************
 * InfixExpressionEvaluator Class
 * 
 * 	Takes a mathmatical infix expression input by user
 * 	and calculates the result, then prints to console.
 *  
 * @author markgroves
 * @version 10/06/18
 * CMSC 256 Fall 18 
 * Project 2
 * 
 *******************************************/
public class InfixExpressionEvaluator {

	//____________________________________________________________________________________
	//	Main method
	//	
	//	Reads in two arguments from command line and passes them into infix
	//		evaluator. Then prints the result of the computation
	//____________________________________________________________________________________

	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(System.in);
		if (args.length == 0) { // checks that file is passed through command line
			
			System.out.print("File not found. Please enter file name: ");
			String str = sc.nextLine();
			File file = new File(str);
			Scanner in = new Scanner(file);
			
			String infix = in.nextLine().toLowerCase();
			String var = in.nextLine().toLowerCase();
			
			String i = variables(infix,var);	// replace all variables in equation with respective values
			double result = evaluateInfix(i); // evaluates equation and returns the resultant

			System.out.print(result); 
			sc.close();
			in.close();
		}
		else {
			
			File line1 = new File(args[0]);
			Scanner in = new Scanner(line1);
			String infix = in.nextLine().toLowerCase();	// first line of input, containg equation with variables
			in.close();
			
			File line2 = new File(args[1]);
			Scanner in2 = new Scanner(line2);
			String var = in2.nextLine().toLowerCase();		//second line of input, with values assigned to variables
			in2.close();
			
			String i = variables(infix,var);	// replace all variables in equation with respective values
			
			double result = evaluateInfix(i); // evaluates equation and returns the resultant

			System.out.print(result); 
		}

	} // end main

	//____________________________________________________________________________________
	//	variables method
	//	
	//	Input: String of a infix expression, string of values to any variables in first expression
	//	Output: String of modified infix with values replacing their respective variables
	//		**values must be 0-9
	//	
	//	This function prepares the input for infix evaluation by 
	//		substituting the proper values in
	//____________________________________________________________________________________
	
	public static String variables(String a, String b) {

		String result = "";	// to contain modified infix with values

		for (int i = 0; i < a.length(); i++) {

			// if variable is recognized, locate variable in second line 
			//	and concantinate value that it holds to result
			if (Character.isLetter(a.charAt(i))) {

				int j = b.indexOf(a.charAt(i)); 
				result += b.charAt(j+2);
			}
			
			// if infix already contains number values, concantinate them to result
			else if (Character.isDigit(a.charAt(i))) {
				result += a.charAt(i);	
			}

			// keep operators and parenthesis in position
			else {
				result += a.charAt(i);
			}
		}
		return result;
	} // end variables 

	//---------------------------------------------------------------------------------------	
	//	evaluateInfix method
	//	
	//	Input: String of a infix expression
	//	Output: double value of the result from the expression input
	//	
	//	function: pushes values to a seperate Stack from the operators, then properly 
	//	evaluates the function based of the precedence of operators
	//	
	//	ex (3*2) + 4  
	//_______________________________________________________________________________________

	public static double evaluateInfix(String infix) {

		MyStack<Character> operatorStack = new MyStack<Character>(); // operator stack
		MyStack<Double> valueStack = new MyStack<Double>();		   // value stack
 

		//loops through each token and pushes to designated stack
		for (int i = 0; i < infix.length(); i++) {

			char nextCharacter = infix.charAt(i); // current character analyzed to see which stack to be pushed

			// if number, push to valueStack
			if (Character.isDigit(nextCharacter)) { 
				valueStack.push((double)Character.getNumericValue(nextCharacter));
			}

			
			else if (nextCharacter == '^') {
				operatorStack.push (nextCharacter);
			}

			else if (nextCharacter == '+' || 
					 nextCharacter == '-' || 
					 nextCharacter == '*' ||
					 nextCharacter == '/' ) {

				// Evaluates top 2 values if current operator has a higher priority than previous
				//    and pushes result to value stack
				while (!operatorStack.isEmpty () && 
						(getPrecedence (nextCharacter) <= getPrecedence (operatorStack.peek())))
				{
					
					char topOp = operatorStack.pop();
					double operandTwo = valueStack.pop();
					double operandOne = valueStack.pop();
					double result = apply(topOp, operandOne, operandTwo);
					valueStack.push(result);
				}
				operatorStack.push (nextCharacter);
			} 

			
			else if (nextCharacter == '(')
				operatorStack.push (nextCharacter);

			else if (nextCharacter == ')') { // stack is not empty if infix expression is valid
				try {
					double b = valueStack.pop();
					char operator = operatorStack.pop();
					double a = valueStack.pop();
					double result = 0; 

					if(operator == '*')
						result = a*b;
					
					else if(operator == '/')
						if (b == 0) { // terminate program if divide by 0
							System.out.print("Cannot divide by 0. Program terminating...");
							System.exit(0);} 
						else
							result = a/b;
					
					else if(operator == '+')
						result = a+b;
					
					else if(operator == '-')
						result = a-b;
					
					else if(operator == '^')
						result = Math.pow(a, b);
					
					valueStack.push(result); // push result of calculation to value stack
					operatorStack.pop();	// removes '(' after evaluation
					
				}// end try block
				
				catch (Exception e) {
					e.printStackTrace();
					break;
				}
			} // end if ')' 


		}// end for loop


		// loops through stack of operators until empty.
		// evaluates remaining values and repeats until no operators remain.
		//	Final value of infix is pushed to valueStack
			while (!operatorStack.isEmpty()) {
				char topOperator = operatorStack.pop();
				double operandTwo = valueStack.pop();
				double operandOne = valueStack.pop();
				double result = apply(topOperator, operandOne, operandTwo);
				valueStack.push(result);
			}

		double result = valueStack.pop(); // return value if no other operators exist

		if(valueStack.isEmpty() && operatorStack.isEmpty())
			return result;
		else 
			System.out.print("Leftover values could not be evaluated. Program terminating...");
		System.exit(0);

		return result;

	} // end infixEvaluator

	//-------------------------------------------------------------------------------------	
	//	apply method
	//	
	//	Input: char operator for expression, 2 doubles representing the operands
	//	Output: double of resulting evaluation
	//	
	//	function: based off of the value of the operator, values 'a' and 'b' are evaluated
	//		and the result is pushed into the valueStack
	//____________________________________________________________________________________


	public static double apply(char operator, double a, double b){
		double result = 0;

		if (operator == '+') 
			result =  a + b;

		else if (operator == '-') 
			result =  a - b;

		else if (operator == '*') 
			result =  a * b;

		else if (operator == '/') 
			if (b == 0) {	// if attempting to divide by '0' the program will terminate
				System.out.print("Cannot divide by 0. Program terminating...");
				System.exit(0);}

			else 
				result =  a / b;

		else if (operator == '^') {

			if (b == 0) { result =  1; }
			else if (b == 1) { result =  a; }
			else { result = Math.pow(a, b); }
		}
		return result;

	} // end apply	

	//-------------------------------------------------------------------------------------	
	//	getPrecedence method
	//	
	//	Input: char operator 
	//	Output: integer precedence value of input operator
	//	
	//	function: for purpose of comparing two operators to each other to see which should be 
	//		used first when evaluating two values. The lower the integer a operators precedence
	//		is, the higher priority it is given when evaluating
	//____________________________________________________________________________________

	private static int getPrecedence (char operator)
	{
		if (operator == '^') 
			return 1;
		else if(operator == '*' || operator == '/')
			return 2;
		else if(operator == '+' || operator == '-')
			return 3;
		else
			return 0;
	} // end getPrecedence

} // end Class
