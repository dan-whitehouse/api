package org.ricone.api.cache;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.ricone.api.component.config.model.Profile;
import org.ricone.api.config.ConfigProperties;
import org.ricone.api.exception.ConfigException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FileManager 
{
	public static void writeProfileToFile(Profile profile) throws IOException, ConfigException
	{
		String path = ConfigProperties.getInstance().getProperty("cache.tmp.profile.path") + profile.getId() + ".json";
		System.out.println(path);
		File file = new File(path);
		if (!file.exists()) 
		{
		   file.createNewFile();
		}
		
		BufferedWriter writer = null;
		try
		{
			FileWriter fw = new FileWriter(file);
			writer = new BufferedWriter(fw);

			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(profile);
			System.out.println(jsonInString);
		    writer.write(jsonInString);

		    System.out.println("File written Successfully");
		}
		finally
		{
			 try
			 {
				 if(writer != null)
				 {
					 writer.close();
				 }
			 }
			 catch(Exception ex)
			 {
				 System.out.println("Error in closing the BufferedWriter " + ex);
			 }
		}
	}
	
	public static Profile loadProfileFromFile(String profileId) throws IOException
	{
		List<String> lines = Files.readAllLines(Paths.get("cache/tmp/profile/" + profileId + ".json"));
		ObjectMapper mapper = new ObjectMapper();
		Profile profile =  mapper.readValue(StringUtils.join(lines, " "), Profile.class);
		return profile;
	}
	
	public static List<Profile> loadProfilesFromFile() throws IOException, ConfigException
	{
		String dir = ConfigProperties.getInstance().getProperty("cache.tmp.profile.path");
		Path path = Paths.get(dir);
		List<Path> result = new ArrayList<>();
		
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, "*.{json}")) 
		{
			List<Profile> profiles = new ArrayList<Profile>();
			for (Path entry: stream) 
			{
			   List<String> lines = Files.readAllLines(Paths.get(entry.toUri()));
			   ObjectMapper mapper = new ObjectMapper();
			   Profile profile =  mapper.readValue(StringUtils.join(lines, " "), Profile.class);
			   profiles.add(profile);
			   result.add(entry);
			}
			return profiles;
		} 
		catch (DirectoryIteratorException ex) 
		{
			throw ex.getCause();
		}
	}
	
}
