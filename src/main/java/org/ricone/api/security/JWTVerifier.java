package org.ricone.api.security;
import io.jsonwebtoken.Jwts;

public class JWTVerifier 
{
	public JWTVerifier()
	{		
	}

	public boolean verify(String token) 
	{
		String key = "thequickbrownfoxjumpedoverthelazydog"; //CastleLearningOnline - Test Config
		try 
		{
			Jwts.parser().setSigningKey(key.getBytes()).requireIssuer("http://auth.ricone.org/").parseClaimsJws(token);	
			return true;
		} 
		catch (Exception e) 
		{
			System.out.println("catch - " + e.getMessage());
			return false;
		}	
	}	
}
