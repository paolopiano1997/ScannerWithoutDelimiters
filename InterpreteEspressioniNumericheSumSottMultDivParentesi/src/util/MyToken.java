package util;

public class MyToken {
	private String token;
	
	
	/**
	 * Constructs a token with the String 'token'
	 * @param token The Token to create
	 */
	public MyToken(String token) {
		this.token=token;
	}
	
	
	/**
	 * Returns Integer token's value. This method does not control token's value, so
	 * 'isNumber()' must be invoked first.
	 * @return Integer token's value
	 * @see #isNumber()
	 */
	public int getAsInt() {
		return Integer.parseInt(token);
	}
	
	
	/**
	 * Returns a boolean that indicates if the Token is an Integer
	 * @return A boolean that indicates if the Token is an Integer
	 */
	public boolean isNumber() {
		try{
			Integer.parseInt(token);
		}catch(NumberFormatException e) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return token;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if(obj instanceof MyToken) {
		MyToken other = (MyToken) obj;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		}
		if(obj instanceof String)
			return this.token.equals((String)obj);
		return true;
	}
	
	
}
