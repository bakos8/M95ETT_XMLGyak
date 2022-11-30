package m95ett;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

public class ListM95ETT {
	
public static void main(String[] args) throws IOException, ParseException {
		
	 List<String> list = new ArrayList<String>();
     list.add("Bakos Dominik Dávid");
     list.add("M95ETT");
     list.add("PTI");
     // this method converts a list to JSON Array
     String jsonStr = JSONArray.toJSONString(list);
     System.out.println(jsonStr);
     
	}
}
