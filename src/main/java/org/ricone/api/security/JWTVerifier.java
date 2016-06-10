package org.ricone.api.security;
import org.ricone.api.component.config.ConfigService;
import org.ricone.api.exception.ConfigException;

import io.jsonwebtoken.Jwts;

public class JWTVerifier 
{
	public JWTVerifier()
	{		
	}

	public static boolean verify(DecodedToken token) throws ConfigException 
	{
		//String key = ConfigService.getInstance().getApp(token.getApplication_id()).getProviderSecret(); 
		String key =  "thequickbrownfoxjumpedoverthelazydog"; //CastleLearningOnline
		try 
		{
			Jwts.parser().setSigningKey(key.getBytes()).requireIssuer("http://auth.ricone.org/").parseClaimsJws(token.getTokenString());	
			return true;
		} 
		catch (Exception e) //Failed to verify, return false
		{
			return false;
		}	
	}
}
