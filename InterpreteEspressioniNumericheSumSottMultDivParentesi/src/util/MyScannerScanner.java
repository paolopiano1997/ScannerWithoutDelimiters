package util;

import java.util.Scanner;
import java.util.regex.Pattern;

public class MyScannerScanner implements MyScanner{
	private Scanner scanner;
	
	public MyScannerScanner(String str) {
		scanner = new Scanner(str);
	}
	/*
	 * CASI POSSIBILI:
	 * - Operatore: 1 carattere, lo mangio e lascio andare
	 * - Numero: guardo anche il prossimo carattere, se numero continuo, altrimenti mangio e lascio andare
	 * - spazio: mangio e lascio andare in ogni caso
	 * 
	 * */
	
	@Override
	public MyToken getNextToken() {
		String number= scanner.findWithinHorizon(Pattern.compile("\\G\\s\\d+"),0);
		if(number!=null) {
			return new MyToken(number.trim());
		}
		else {
			String other = scanner.findWithinHorizon(Pattern.compile("\\G\\s*\\[\\+\\(\\)*-/]"),0);
			if(other!=null)
				return new MyToken(other.trim());
		}
		return null;
	}
	
	

}
