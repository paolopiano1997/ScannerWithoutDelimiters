package util;



public class ScannerWithoutDelimiter implements MyScanner{
	private String str;
	private int startIndex = 0;
	private int index = 0;
	
	/**
	 * 
	 * Constructs a ScannerWithoutDelimiter for the String 'str'
	 * @param str A String to be parsed
	 */
	public ScannerWithoutDelimiter(String str) {
		this.str=str;
	}
	
	
	/**
	 * Returns the next token of the String 'str'. Supports the presence or not of delimiters (white spaces or tabs).
	 * @return Returns next token not necessarily with delimiters
	 * 
	 */
	@Override
	public MyToken getNextToken() {
		if(index>=str.length()&&startIndex==index)
			return null;
		if(index==str.length()&&startIndex!=index) {
			return new MyToken(str.substring(startIndex,index));
		}
		try {
			Integer.parseInt(""+str.charAt(index));
		}catch (NumberFormatException e) {
			//return no number
			MyToken res = null;
			if(startIndex!=index) { //No number now, but number(s) before this token
				res = new MyToken(str.substring(startIndex,index));
				startIndex=index;
			}
			else if(str.charAt(index)==' ') {
				index++;
				startIndex=index;
				res = getNextToken();
			}
			else{ //No number now and before this token
				res = new MyToken("" + str.charAt(index));
				index++;
				startIndex=index;
			}
			return res;
		}
		//return number (recursive)
		index++;
		return getNextToken();
	}

	
}
