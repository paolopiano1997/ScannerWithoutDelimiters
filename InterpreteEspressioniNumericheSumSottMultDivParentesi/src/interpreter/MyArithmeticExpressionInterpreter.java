package interpreter;

import util.EmptyLineException;
import util.MyScanner;
import util.MyToken;

public class MyArithmeticExpressionInterpreter {
	private MyScanner scanner;
	private MyToken currentToken;
	
	private int count = 0;
	
	private String optimizedString = "";
	
	
	/**
	 * Constructs an interpreter that interprets arithmetic expressions
	 * @param scanner Support scanner to create Token
	 */
	public MyArithmeticExpressionInterpreter(MyScanner scanner) {
		this.scanner=scanner;
		currentToken = scanner.getNextToken();
	}
	
	
	/**
	 * Returns the evaluation of the arithmetic expression contained in the scanner
	 * @return Evaluation of the arithmetic expression
	 * @throws EmptyLineException Represents an empty line
	 */
	public int parseExp() throws EmptyLineException {
		System.out.println("LEVEL 3 - EXP: Invoco level 2 - TERM, currentToken="+ currentToken);
		int t1 = parseTerm();
		while(currentToken!=null) {
			if(currentToken.equals("+") || currentToken.equals("-")) {
				optimize();
				String op=currentToken.toString();
				System.out.println("LEVEL 3 - EXP: Current token '" + currentToken +"'");
				currentToken = scanner.getNextToken();
				int t2 = parseTerm();
				t1 = calculate(t1,t2,op);
			}
			else return t1;
		}
		return t1;
	}
	
	
	private int parseTerm() throws EmptyLineException {
		System.out.println("LEVEL 2 - TERM: Invoco level 3 - FACTOR, currentToken="+ currentToken);
		int f1 = parseFactor();
		while(currentToken!=null) {
			if(currentToken.equals("*") || currentToken.equals(":")) {
				optimize();
				String op=currentToken.toString();
				System.out.println("LEVEL 2 - TERM: Current token '" + currentToken + "'");
				currentToken = scanner.getNextToken();
				int f2 = parseFactor();
				f1 = calculate(f1,f2,op);
			}
			else return f1;
		}
		return f1;
	}

	private int parseFactor() throws EmptyLineException {
		if(currentToken!=null && currentToken.equals("(")) {
			optimize();
			System.out.println("LEVEL 1 - FACTOR: Current token '('");
			currentToken=scanner.getNextToken();
			System.out.println("LEVEL 1 - FACTOR: Chiamo Level 3 - EXP");
			int innerExp = parseExp();
			if(currentToken!=null && currentToken.equals(")")) {
				optimize();
				System.out.println("LEVEL 1 - FACTOR: Current token ')'");
				currentToken = scanner.getNextToken();
				return innerExp;
			}
			else throw new IllegalArgumentException("Missing ')'");
		}
		else if(currentToken!=null && currentToken.isNumber()){
			optimize();
			System.out.println("LEVEL 1 - FACTOR: Current token number: " + currentToken);
			int value = currentToken.getAsInt();
			currentToken=scanner.getNextToken();
			return value;
		}
		else if(count==0)
			throw new EmptyLineException();
		else throw new IllegalArgumentException(currentToken != null ? ("Unexpected '" + currentToken +"' at token n° " + (count+1)) : ("Missing token after token n° " + count));
	}


	private int calculate(int first, int second,String op) {
		if(op.equals("+"))
			return first+second;
		else if( op.equals("-"))
			return first-second;
		else if( op.equals("*"))
			return first*second;
		else if(op.equals(":"))
			return first/second;
		else throw new IllegalArgumentException("Op error");
	}
	
	private void optimize() {
		count++;
		optimizedString+=currentToken;
	}
	
	
	/**
	 * Returns the optimized String (eliminates useless blank spaces between Tokens)
	 * @return the optimized String
	 */
	public String getOptimizedString() {
		return this.optimizedString;
	}

}
