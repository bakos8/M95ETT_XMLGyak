package m95ett;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReadM95ETT {
	
	
	public static void main(String[] args) throws IOException, ParseException {
		
		JSONParser jsonParser = new JSONParser();

		FileReader toParse = new FileReader("JSONM95ETT.json");
		
		 Object obj = jsonParser.parse(toParse);
		 
         System.out.println(obj);
		
	}
}
