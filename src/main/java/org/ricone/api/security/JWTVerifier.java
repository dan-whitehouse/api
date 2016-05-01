package org.ricone.api.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;

public class JWTVerifier 
{
	public JWTVerifier()
	{		
	}

	public boolean verify(String token) 
	{
		ObjectMapper map = new ObjectMapper();	
		String key = "";
		try 
		{
			DecodedToken dt = map.readValue(Jwts.parser().parsePlaintextJws(token).getBody(), DecodedToken.class);
			key = "";
			//TODO - Use: dt.getApplication_id(); to get key
		} 
		catch (Exception e) 
		{
			return false;
		}	
		return Jwts.parser().setSigningKey(key).requireIssuer("http://security.oneapidev.org/").isSigned(token);
	}	
}
