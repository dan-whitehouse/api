package org.ricone.api.security;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TokenDecoder 
{
	public static DecodedToken decodeToken(String token) throws JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper map = new ObjectMapper();	
		String[] base64EncodedSegments = StringUtils.split(token, "\\.");
		DecodedToken dt = map.readValue(base64Decode(base64EncodedSegments[1]), DecodedToken.class);
		dt.setTokenString(token);
		return dt;		
	}
	
	private static String base64Decode(String input)
	{
		String result = null;
		Base64 decoder = new Base64(true);
		byte[] decodedBytes = decoder.decode(input);
		result = new String(decodedBytes);
		return result;
	}
	
}
