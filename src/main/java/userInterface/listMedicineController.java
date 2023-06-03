package userInterface;

import com.pharmacy.Drug;

import com.pharmacy.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;


public class listMedicineController extends SwitchScene implements Initializable {
    @FXML
    private DatePicker expDate;
    //@FXML
    //private TextField manuName;
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
    @FXML
    private Button button;
    @FXML
    private String pfName;
    @FXML
    private ChoiceBox<String> manuNameSelectOption;
    @FXML
    private Text profileName;

    @FXML
    private TableView<Drug> medTable;
    @FXML
    private TableColumn<Drug, String> descColumn;
    @FXML
    private TableColumn<Drug, String> exDateColumn;
    @FXML
    private TableColumn<Drug, Integer> idColumn;
    @FXML
    private TableColumn<Drug, String> manuColumn;
    @FXML
    private TableColumn<Drug, String> medNameColumn;
    @FXML
    private TableColumn<Drug, Double> priceColumn;
    @FXML
    private TableColumn<Drug, Integer> quantityColumn;


    ObservableList<Drug> list = FXCollections.observableArrayList(
            //new Drug("sfsdfsd","sfsdfdsf","122421","sdfsdfsdfdsfdsfs",12,56),
    );
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        descColumn.setCellValueFactory(new PropertyValueFactory<Drug,String>("description"));
        exDateColumn.setCellValueFactory(new PropertyValueFactory<Drug,String>("expireDate"));
        idColumn.setCellValueFactory(new PropertyValueFactory<Drug,Integer>("id"));
        manuColumn.setCellValueFactory(new PropertyValueFactory<Drug,String>("manufacturer"));
        medNameColumn.setCellValueFactory(new PropertyValueFactory<Drug,String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Drug,Double>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Drug,Integer>("amount"));
        pfName = "@"+LoginController.getProfName() ;
        profileName.setText(pfName);
        medTable.setItems(list);
        File f = new File("Medicine.json");
        if(f.exists()){
            try {
                readFromJson();
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }
        }
        ted();


    }
    @FXML
    void editDrug(ActionEvent event) {

    }
    public void readFromJson() throws IOException, ParseException {
        Drug.count = 0;
        JSONParser jsonParser = new JSONParser();
        Object object = jsonParser.parse(new FileReader("Medicine.json"));
        JSONArray jsonArray1 = (JSONArray) object;
        JSONObject obj  = new JSONObject();
        int size = jsonArray1.size();
        for (int i = 0; i < size; i++) {
            Drug drug = new Drug();
            JSONObject tes = (JSONObject) jsonArray1.get(i);
            System.out.println(tes.get("Manufaturer"));
            drug.setManufacturer(tes.get("DrugManufacturer").toString());
            System.out.println(tes.get("DrugName"));
            drug.setName(tes.get("DrugName").toString());
            System.out.println(tes.get("ExpireDate"));
            drug.setExpireDate(tes.get("ExpireDate").toString());
            System.out.println(tes.get("DrugDescription"));
            drug.setDescription(tes.get("DrugDescription").toString());
            System.out.println(tes.get("Amount"));
            drug.setAmount(Integer.parseInt(tes.get("Amount").toString()));
            System.out.println(tes.get("Price"));
            drug.setPrice(Double.parseDouble(tes.get("Price").toString()));
            System.out.println(tes.get("Id"));

            medTable.getItems().add(drug);
            System.out.println("-------------------------------");

        }

    }

    // To add new Medicine
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
            drugManuValid = false;*/
        if(manuNameSelectOption.getValue() != null){
            drug.setManufacturer(manuNameSelectOption.getValue().toString());
            drugManuValid = true;
            manuNameSelectOption.setStyle("-fx-border-color:");
        }else{
            drugManuValid = false;
            manuNameSelectOption.setStyle("-fx-border-color:red");
        }

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
            drug.setPrice(Double.parseDouble(medPrice.getText()));
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
            //manuName.setText("");
            //manuName.setStyle("-fx-border-color: ");
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
            Stage stage = (Stage)button.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("List-Medicine.fxml"));
            Scene root = null;
            try {
                root = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setTitle("Medicine");
            stage.setResizable(false);
            stage.setScene(root);


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

    @FXML
    void deleteDrugButton(ActionEvent event) throws IOException, ParseException {
            Drug d = medTable.getSelectionModel().getSelectedItem();
            if(d!=null){
                if(displaly("Delete", "Are You Sure?")){
                    JSONParser jsonParser = new JSONParser();
                    Object object = jsonParser.parse(new FileReader("Medicine.json"));
                    JSONArray jsonArray1 = (JSONArray) object;
                    JSONObject obj  = new JSONObject();
                    int size = jsonArray1.size();
                    for (int i = 0; i < size; i++) {
                        JSONObject tes = (JSONObject) jsonArray1.get(i);
                        if(tes.get("DrugName").equals(d.getName())){
                            jsonArray1.remove(i);
                            FileWriter file = new FileWriter("Medicine.json");
                            file.write(jsonArray1.toJSONString());
                            file.flush();
                            file.close();
                            break;
                        }

                    }
                    System.out.println(d.getName());
                    Stage stage = (Stage)button.getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("List-Medicine.fxml"));
                    Scene root = new Scene(fxmlLoader.load());
                    stage.setTitle("Medicine");
                    stage.setResizable(false);
                    stage.setScene(root);
                }


            }



    }
    public static boolean displaly(String title, String message){
        AtomicBoolean answer = new AtomicBoolean(false);
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        Label l1 = new Label();
        l1.setText(message);
        Button yes = new Button("Yes");
        Button no = new Button("No");
        yes.setOnAction(e->{
            answer.set(true);
            window.close();
        });
        no.setOnAction(e->{
            answer.set(false);
            window.close();
        });
        VBox vb = new VBox(5);
        HBox hb = new HBox(15);
        hb.setStyle("-fx-padding:40px");
        vb.getChildren().addAll(l1);
        hb.getChildren().addAll(l1,yes,no);
        Scene scene = new Scene(hb);
        window.setResizable(false);
        window.setScene(scene);
        window.showAndWait();


        return answer.get();
    }

}
