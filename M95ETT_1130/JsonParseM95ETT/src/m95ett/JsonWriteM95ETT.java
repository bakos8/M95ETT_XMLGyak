package m95ett;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonWriteM95ETT {
	
	public static void main(String[] args) throws IOException {
		
		JSONObject employeeDetails = new JSONObject();
		
        employeeDetails.put("nev", "Bakos Dominik Dávid");
        employeeDetails.put("neptunkod", "M95ETT");
        employeeDetails.put("szak", "PTI");
        
        JSONArray employeeList = new JSONArray();
        employeeList.add(employeeDetails);
        
        System.out.println(employeeList);
        
        FileWriter file = new FileWriter("JSONm95ETT_out.json");
        
        file.write(employeeList.toJSONString()); 
        file.flush();
	}

}
