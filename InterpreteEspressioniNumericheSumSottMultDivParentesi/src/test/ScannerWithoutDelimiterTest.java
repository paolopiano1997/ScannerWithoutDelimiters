package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import interpreter.MyArithmeticExpressionInterpreter;
import util.EmptyLineException;
import util.MyScanner;
import util.ScannerWithoutDelimiter;

public class ScannerWithoutDelimiterTest {
	
	private MyScanner scanner;
	private MyArithmeticExpressionInterpreter interpreter;
	private String[] stringhe= {"((4-e))*2", "((4-2)*2", "(   (4  -1 )*2 )  :2", " (  20-1) *2 r","((( 5-2 )* ( 10:5) + 3)*10)  :10",""};
	

	@Test(expected = IllegalArgumentException.class)
	public void testTokenNotExpectedInTheMiddle() throws EmptyLineException {
		scanner = new ScannerWithoutDelimiter(stringhe[0]);
		interpreter = new MyArithmeticExpressionInterpreter(scanner);
		interpreter.parseExp();
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testMissingPar() throws EmptyLineException {
		scanner = new ScannerWithoutDelimiter(stringhe[1]);
		interpreter = new MyArithmeticExpressionInterpreter(scanner);
		interpreter.parseExp();
	}
	

	@Test
	public void testTuttoOkConESenzaSpazi() throws EmptyLineException {
		scanner = new ScannerWithoutDelimiter(stringhe[2]);
		interpreter = new MyArithmeticExpressionInterpreter(scanner);
		assertEquals(interpreter.parseExp(),3);
	}
	
	
	@Test
	public void testCarattereFinaleNonRiconosciutoDeveDareIlRisultatoEFermarsiSenzaErrore() throws EmptyLineException {
		scanner = new ScannerWithoutDelimiter(stringhe[3]);
		interpreter = new MyArithmeticExpressionInterpreter(scanner);
		assertEquals(interpreter.parseExp(),38);
	}
	
	@Test
	public void testTuttoOkPiuLungoConESenzaSpazi() throws EmptyLineException {
		scanner = new ScannerWithoutDelimiter(stringhe[4]);
		interpreter = new MyArithmeticExpressionInterpreter(scanner);
		assertEquals(interpreter.parseExp(),9);
	}
	
	@Test(expected = EmptyLineException.class)
	public void testEmptyLine() throws EmptyLineException{
		scanner=new ScannerWithoutDelimiter(stringhe[5]);
		interpreter = new MyArithmeticExpressionInterpreter(scanner);
		interpreter.parseExp();
	}

}
