package org.ricone.api.security;

import org.ricone.api.cache.AppCache;
import org.ricone.api.exception.ConfigException;
import io.jsonwebtoken.Jwts;

public class JWTVerifier 
{
	public JWTVerifier()
	{		
	}

	static boolean verify(DecodedToken token) throws ConfigException
	{
		//String key =  "thequickbrownfoxjumpedoverthelazydog"; //CastleLearningOnline
		//String key = ConfigService.getInstance().getApp().getProviderSecret();
		String key = AppCache.getInstance().get(token.getApplication_id()).getProviderSecret();
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
