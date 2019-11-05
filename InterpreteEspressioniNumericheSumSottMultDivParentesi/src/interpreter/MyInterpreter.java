package interpreter;

import util.MyToken;
import util.ScannerWithoutDelimiter;

public class MyInterpreter {
	private ScannerWithoutDelimiter scanner;
	private MyToken currentToken;
	
	public MyInterpreter(ScannerWithoutDelimiter scanner) {
		this.scanner=scanner;
		currentToken = scanner.getNextToken();
	}
	
	public int parseExp() {
		System.out.println("LEVEL 3 - EXP: Invoco level 2 - TERM, currentToken="+ currentToken);
		int t1 = parseTerm();
		while(currentToken!=null) {
			if(currentToken.equals("+")) {
				System.out.println("LEVEL 3 - EXP: Current token +");
				currentToken = scanner.getNextToken();
				int t2 = parseTerm();
				t1 = t1 + t2;
			}
			else if(currentToken.equals("-")) {
				System.out.println("LEVEL 3 - EXP: Current token -");
				currentToken = scanner.getNextToken();
				int t2 = parseTerm();
				t1 = t1- t2;
			}
			else return t1;
		}
		return t1;
	}
	
	private int parseTerm() {
		System.out.println("LEVEL 2 - TERM: Invoco level 3 - FACTOR, currentToken="+ currentToken);
		int f1 = parseFactor();
		while(currentToken!=null) {
			if(currentToken.equals("*")) {
				System.out.println("LEVEL 2 - TERM: Current token *");
				currentToken = scanner.getNextToken();
				int f2 = parseFactor();
				f1 = f1 * f2;
			}
			else if(currentToken.equals(":")) {
				System.out.println("LEVEL 2 - TERM: Current token /");
				currentToken = scanner.getNextToken();
				int f2 = parseFactor();
				f1 = f1 / f2;
			}
			else return f1;
		}
		return f1;
	}
	
	private int parseFactor() {
		if(currentToken!=null && currentToken.equals("(")) {
			System.out.println("LEVEL 1 - FACTOR: Current token (");
			currentToken=scanner.getNextToken();
			System.out.println("Chiamo Level 1 - EXP");
			int innerExp = parseExp();
			if(currentToken.equals(")")) {
				System.out.println("LEVEL 1 - FACTOR: Current token )");
				currentToken = scanner.getNextToken();
				return innerExp;
			}
			else throw new IllegalArgumentException("Missing )");	
		}
		else if(currentToken!=null && currentToken.isNumber()){
			System.out.println("LEVEL 1 - FACTOR: Current token number: " + currentToken);
			int value = currentToken.getAsInt();
			currentToken=scanner.getNextToken();
			return value;
		}
		else throw new IllegalArgumentException("Unrecognised");
	}

}
