package userInterface;

import com.pharmacy.Drug;
import com.pharmacy.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.security.cert.CertPathValidatorResult;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import userInterface.Validator;

public class addMedicineController extends SwitchScene implements Initializable {

    @FXML
    private DatePicker expDate;
    @FXML
    private ChoiceBox<?> manuNameSelectOption;
    @FXML
    private TextField manuName;
    //@FXML
    //private Button medAddButton;
    @FXML
    private TextField medAmount;
    @FXML
    private TextArea medDescription;
    @FXML
    private TextField medName;
    @FXML
    private TextField medPrice;
    @FXML
    private Label priceError;
    @FXML
    private Label amountError;

    Drug drug  = new Drug();
    @FXML
    void addMedicine(ActionEvent event) {
        boolean drugNameValid,drugManuValid,exDateValid,descrValid,amountValid,priceValid = false;
        //Medicine name validation
        if (Validator.isValidString(medName,medName.getText())){
            drug.setName(medName.getText());
            drugNameValid = true;
            System.out.println("yea it is validd");
        }else {
            drugNameValid = false;
            System.out.println("nooo");
        }
        //Manufacture name validation
       /* if (Validator.isValidString(manuName,manuName.getText())){
            drug.setManufacturer(manuName.getText());
            drugManuValid = true;
            System.out.println("yea it is validd");
        }else
            drugManuValid = false; */
        drug.setManufacturer(manuNameSelectOption.getValue().toString());
        drugManuValid = true;
        //Description validation
        if (Validator.isValidString(medDescription,medDescription.getText())){
            drug.setDescription(medDescription.getText());
            descrValid = true;
            System.out.println("yea it is validd");
        }else {
            descrValid = false;
            System.out.println("nooo");
        }
        //Amount Validation
        if (Validator.isValidInt(medAmount,medAmount.getText())){
            drug.setAmount(Integer.parseInt(medAmount.getText()));
            amountValid = true;
            System.out.println("yea it is validd");
            amountError.setText("");
        }else {
            amountValid = false;
            System.out.println("nooo");
            amountError.setText("Only integer Numbers allowed");
        }
        //Price Validation
        if (Validator.isValidFloat(medPrice,medPrice.getText())){
            drug.setPrice(Float.parseFloat( medPrice.getText()));
            priceValid = true;
            System.out.println("yea it is validd");
            priceError.setText("");
        }else {
            priceValid = false;
            System.out.println("nooo");
            priceError.setText("Only Numbers allowed");
        }
        //Expire Date validation
        if (Validator.isValidDate(expDate,expDate.getValue())){
            LocalDate exDate = expDate.getValue();
            String formatedDate = exDate.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
            drug.setExpireDate(formatedDate);
            exDateValid = true;
            System.out.println(formatedDate);
        }else {
            exDateValid = false;
            System.out.println("nooo");
        }

        boolean allValid = drugNameValid && drugManuValid && exDateValid && descrValid && amountValid && priceValid;
        if(allValid){
            //succesMessge.setText("Successfully Added An Drug");
            //employeeTable.getItems().add(employee);
            manuName.setText("");
            manuName.setStyle("-fx-border-color: ");
            //expDate.set("");
            expDate.setStyle("-fx-border-color: ");
            medAmount.setText("");
            medAmount.setStyle("-fx-border-color: ");
            medDescription.setText("");
            medDescription.setStyle("-fx-border-color: ");
            medName.setText("");
            medName.setStyle("-fx-border-color: ");
            medPrice.setText("");
            medPrice.setStyle("-fx-border-color: ");

            writeToJsonfile();


        }



    }

    public void writeToJsonfile(){
        JSONObject obj  = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        obj.put("DrugId",drug.getId());
        obj.put("DrugName",drug.getName());
        obj.put("DrugManufacturer",drug.getManufacturer());
        obj.put("ExpireDate",drug.getExpireDate());
        obj.put("DrugDescription",drug.getDescription());
        obj.put("Amount",drug.getAmount());
        obj.put("Price",drug.getPrice());
        jsonArray.add(obj);
        try{
            File f = new File("Medicine.json");
            if(!f.exists()){
                FileWriter file = new FileWriter("Medicine.json");
                file.write(jsonArray.toJSONString());
                file.close();
            }else{
                JSONParser jsonParser = new JSONParser();
                Object object = jsonParser.parse(new FileReader("Medicine.json"));
                JSONArray jsonArray1 = (JSONArray) object;
                jsonArray1.add(obj);
                FileWriter file = new FileWriter("Medicine.json");
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

    public void ted(){
        ArrayList listOfMed = new ArrayList<String>();
        JSONParser jsonParser = new JSONParser();
        Object object = null;
        try {
            object = jsonParser.parse(new FileReader("Supplier.json"));
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
            listOfMed.add(tes.get("SupplierName"));

        }
        manuNameSelectOption.getItems().addAll(listOfMed);
        System.out.println(listOfMed);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ted();
    }
}
