package m95ett;


import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class ObjectM95ETT {
	
	public static void main(String[] args) throws IOException, ParseException {
		
		JSONObject student = new JSONObject();
		
		student.put("nev", "Bakos Dominik Dávid");
		student.put("neptunkod", "M95ETT");
		student.put("szak", "PTI");
		 
		System.out.println(student.toString());		 

	}
}