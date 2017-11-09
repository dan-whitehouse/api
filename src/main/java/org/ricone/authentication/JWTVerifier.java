package org.ricone.authentication;

import io.jsonwebtoken.Jwts;
import org.ricone.config.AppCache;
import org.ricone.exception.ConfigException;

public class JWTVerifier 
{
	public JWTVerifier()
	{		
	}

	static boolean verify(DecodedToken token) throws ConfigException
	{
		String key = AppCache.getInstance().get(token.getApplication_id()).getProviderSecret();
		try 
		{
			Jwts.parser().setSigningKey(key.getBytes()).requireIssuer("http://auth.ricone.org/").parseClaimsJws(token.getTokenString());	
			return true;
		} 
		catch (Exception e)
		{
			return false;
		}	
	}
}
