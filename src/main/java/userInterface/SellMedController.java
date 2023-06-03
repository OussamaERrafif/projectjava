package userInterface;

import com.pharmacy.Customer;
import com.pharmacy.Transaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class SellMedController extends SwitchScene implements Initializable {
    @FXML
    private TextField amount;
    @FXML
    private Text amountErr;
    @FXML
    private Button button;

    @FXML
    private TextField custName;

    @FXML
    private TextField custPhone;


    @FXML
    private ChoiceBox<String> medSelectOption;

    //ArrayList test =
    @FXML
    private Text profileName;
    private String pfName;

    public static void main(String[] args) throws IOException, ParseException {


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pfName = "@"+LoginController.getProfName() ;
        profileName.setText(pfName);
        ted();
    }
    public void ted(){
        ArrayList listOfMed = new ArrayList<String>();
        JSONParser jsonParser = new JSONParser();
        Object object = null;
        try {
            object = jsonParser.parse(new FileReader("Medicine.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        JSONArray jsonArray1 = (JSONArray) object;
        JSONObject obj = new JSONObject();
        int size = jsonArray1.size();
        for (int i = 0; i < size; i++) {
            JSONObject tes = (JSONObject) jsonArray1.get(i);
            listOfMed.add(tes.get("DrugName"));

        }
        medSelectOption.getItems().addAll(listOfMed);
        System.out.println(listOfMed);
    }

    Customer cust = new Customer();
    Transaction trans = new Transaction();
    @FXML
    void sellDrugButton(ActionEvent event) throws IOException, ParseException {
        //Customer name validation
        boolean custNameValid,medLlistValid,phoneValid,amountValid = false;
        if (Validator.isValidString(custName,custName.getText())){
            cust.setFullName(custName.getText());
            custNameValid = true;
            System.out.println("yea it is validd");
        }else {
            custNameValid = false;
            System.out.println("nooo");
        }
        //Drug list validation
        if(medSelectOption.getValue() != null){
            cust.setPrescription(medSelectOption.getValue().toString());
            medLlistValid = true;
            medSelectOption.setStyle("-fx-border-color:");
        }else{
            medLlistValid = false;
            medSelectOption.setStyle("-fx-border-color:red");
        }
        //Phone number validation
        if (Validator.isValidPhoneNumber(custPhone,custPhone.getText())){
            cust.setPhoneNumber(custPhone.getText());
            phoneValid = true;
            System.out.println("yea it is validd");
        }else {
            phoneValid = false;
            System.out.println("nooo");
        }
        //Amount Validation
        if (Validator.isValidInt(amount,amount.getText()) && isAmountLow(medSelectOption.getValue().toString(), Integer.parseInt(amount.getText().toString()))){
            trans.setQuantity(parseInt(amount.getText()));
            amountValid = true;
            System.out.println("yea it is validd");
        }else {
            amountValid = false;
            System.out.println("nooo");
        }
        if(!isAmountLow(medSelectOption.getValue().toString(), Integer.parseInt(amount.getText().toString()))){
            amount.setStyle("-fx-border-color:red");
            amountErr.setText("Amount too high");
        }else{
            amountErr.setText("");
        }

        boolean allValid = custNameValid && medLlistValid && phoneValid && amountValid && isAmountLow(medSelectOption.getValue().toString(), Integer.parseInt(amount.getText().toString()));
        if(allValid){
            amountManger(medSelectOption.getValue().toString(), Integer.parseInt(amount.getText().toString()));
            amount.setText("");
            amount.setStyle("-fx-border-color: ");
            custPhone.setText("");
            custPhone.setStyle("-fx-border-color: ");
            custName.setText("");
            custName.setStyle("-fx-border-color: ");
            writeToJsonfile();
            try {
                writeToJsonfile2();
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void writeToJsonfile(){
        JSONObject obj  = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        obj.put("CustomerName",cust.getFullName());
        obj.put("CustomerPhone",cust.getPhoneNumber());
        obj.put("Prescription",cust.getPrescription());
        jsonArray.add(obj);
        try{
            File f = new File("Customer.json");
            if(!f.exists()){
                FileWriter file = new FileWriter("Customer.json");
                file.write(jsonArray.toJSONString());
                file.close();
            }else{
                JSONParser jsonParser = new JSONParser();
                Object object = jsonParser.parse(new FileReader("Customer.json"));
                JSONArray jsonArray1 = (JSONArray) object;
                jsonArray1.add(obj);
                FileWriter file = new FileWriter("Customer.json");
                file.write(jsonArray1.toJSONString());
                file.flush();
                file.close();
            }

        }catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println(jsonArray);
    }

    public void writeToJsonfile2() throws IOException, ParseException {
        JSONObject obj  = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        obj.put("DrugId",dataExtractor("DrugId", medSelectOption.getValue().toString()));
        obj.put("Date",trans.getDate());
        obj.put("Price",dataExtractor("Price", medSelectOption.getValue().toString()));
        obj.put("DrugManufacturer",dataExtractor("DrugManufacturer", medSelectOption.getValue().toString()));
        obj.put("DrugName",dataExtractor("DrugName", medSelectOption.getValue().toString()));
        obj.put("Quantity",trans.getQuantity());
        jsonArray.add(obj);
        try{
            File f = new File("Transaction.json");
            if(!f.exists()){
                FileWriter file = new FileWriter("Transaction.json");
                file.write(jsonArray.toJSONString());
                file.close();
            }else{
                JSONParser jsonParser = new JSONParser();
                Object object = jsonParser.parse(new FileReader("Transaction.json"));
                JSONArray jsonArray1 = (JSONArray) object;
                jsonArray1.add(obj);
                FileWriter file = new FileWriter("Transaction.json");
                file.write(jsonArray1.toJSONString());
                file.flush();
                file.close();
            }

        }catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println(jsonArray);
    }

    public String dataExtractor(String key, String val) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        Object object = jsonParser.parse(new FileReader("Medicine.json"));
        JSONArray jsonArray1 = (JSONArray) object;
        JSONObject obj  = new JSONObject();
        int size = jsonArray1.size();
        String value="";
        for (int i = 0; i < size; i++) {
            JSONObject tes = (JSONObject) jsonArray1.get(i);
            if(tes.get("DrugName").equals(val)){
                value = tes.get(key).toString();
                break;
            }

        }
        return value;
    }

    public void amountManger(String val,int num) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        Object object = jsonParser.parse(new FileReader("Medicine.json"));
        JSONArray jsonArray1 = (JSONArray) object;
        JSONObject obj  = new JSONObject();
        int size = jsonArray1.size();
        for (int i = 0; i < size; i++) {
            JSONObject tes = (JSONObject) jsonArray1.get(i);
            if(tes.get("DrugName").equals(val)){
                int befAmou = parseInt(tes.get("Amount").toString());
                int newAmou = befAmou - num;
                if(befAmou >num){
                    tes.put("Amount",newAmou);
                    FileWriter file = new FileWriter("Medicine.json");
                    file.write(jsonArray1.toJSONString());
                    file.flush();
                    file.close();
                    break;
                }

            }

        }
    }

    public boolean isAmountLow(String val,int num) throws IOException, ParseException {
        boolean amountLow = false;
        JSONParser jsonParser = new JSONParser();
        Object object = jsonParser.parse(new FileReader("Medicine.json"));
        JSONArray jsonArray1 = (JSONArray) object;
        JSONObject obj  = new JSONObject();
        int size = jsonArray1.size();
        for (int i = 0; i < size; i++) {
            JSONObject tes = (JSONObject) jsonArray1.get(i);
            if(tes.get("DrugName").equals(val)){
                int befAmou = parseInt(tes.get("Amount").toString());
                int newAmou = befAmou - num;
                if(befAmou >=num){
                    amountLow = true;
                    break;
                } else{
                    amountLow = false;
                }

            }

        }
        return amountLow;
    }
}
