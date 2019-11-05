package application;

import interpreter.MyArithmeticExpressionInterpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import util.EmptyLineException;
import util.MyScanner;
import util.ScannerWithoutDelimiter;

public class Main {
	public static void main(String args[]) {
		String line;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			while((line=br.readLine())!=null) {
				MyScanner scanner = new ScannerWithoutDelimiter(line);
				MyArithmeticExpressionInterpreter interpreter = new MyArithmeticExpressionInterpreter(scanner);
				try{
					int result = interpreter.parseExp();
					System.out.println(interpreter.getOptimizedString() + "=" + result);
				}catch (IllegalArgumentException e) {
					System.out.println("Syntax error: " + e.getMessage());
				}catch(EmptyLineException e){
					System.out.println("Empty line");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
