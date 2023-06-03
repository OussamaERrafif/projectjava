package userInterface;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) throws IOException, ParseException {

       /* JSONParser jsonParser = new JSONParser();
        Object object = jsonParser.parse(new FileReader("User.json"));
        JSONArray jsonArray1 = (JSONArray) object;
        JSONObject obj  = new JSONObject();
        int size = jsonArray1.size();
        for (int i = 0; i < size; i++) {
            JSONObject tes = (JSONObject) jsonArray1.get(i);
            if(tes.get("Username").equals("nigga")){
                tes.put("Username","Abenezer");
                FileWriter file = new FileWriter("User.json");
                file.write(jsonArray1.toJSONString());
                file.flush();
                file.close();
                System.out.println(tes.get("Username"));
                break;
            }

        }*/
        ArrayList listOfMed = new ArrayList<String>();
        JSONParser jsonParser = new JSONParser();
        Object object = jsonParser.parse(new FileReader("Employee.json"));
        JSONArray jsonArray1 = (JSONArray) object;
        JSONObject obj  = new JSONObject();
        int size = jsonArray1.size();
        for (int i = 0; i < size; i++) {
            JSONObject tes = (JSONObject) jsonArray1.get(i);
            listOfMed.add(tes.get("Username"));

        }



        System.out.println(listOfMed);
    }

}
