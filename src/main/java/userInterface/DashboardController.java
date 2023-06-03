package userInterface;

import com.pharmacy.Admin;
import com.pharmacy.Employee;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import userInterface.LoginController;
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
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class DashboardController extends SwitchScene implements Initializable {
    @FXML
    private TableView<Admin> adminTable;

    @FXML
    private Button button;

    @FXML
    private String pfName;

    @FXML
    private Text profileName;

    @FXML
    private TextField email;

    @FXML
    private TableColumn<Admin, String> emailColumn;

    @FXML
    private TextField fullName;

    @FXML
    private TableColumn<Admin, String> passColumn;

    @FXML
    private PasswordField password;

    @FXML
    private Label totalCustomer;

    @FXML
    private Label totalEmployee;

    @FXML
    private Label totalMedicine;

    @FXML
    private Label totalSale;

    @FXML
    private Label totalSuppliers;

    @FXML
    private Label totalTransaction;

    @FXML
    private TextField userName;

    @FXML
    private TableColumn<Admin, String> usrNameColumn;

    ObservableList<Admin> list = FXCollections.observableArrayList(

    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usrNameColumn.setCellValueFactory(new PropertyValueFactory<Admin,String>("fullName"));
        usrNameColumn.setCellValueFactory(new PropertyValueFactory<Admin,String>("userName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Admin,String>("email"));
        passColumn.setCellValueFactory(new PropertyValueFactory<Admin,String>("password"));
        pfName = "@"+LoginController.getProfName() ;
        profileName.setText(pfName);
        adminTable.setItems(list);
        File f = new File("Employee.json");
        if(f.exists()){
            try {
                readFromJson();
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }
        }

        totalEmployee.setText(String.valueOf(totalEmployee("Employee.json")));
        totalCustomer.setText(String.valueOf(totalEmployee("Customer.json")));
        totalMedicine.setText(String.valueOf(totalEmployee("Medicine.json")));
        try {
            totalSale.setText(totalSale());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        totalSuppliers.setText(String.valueOf(totalEmployee("Supplier.json")));
        totalTransaction.setText(String.valueOf(totalEmployee("Transaction.json")));


    }
    public String totalSale() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        Object object = jsonParser.parse(new FileReader("Transaction.json"));
        JSONArray jsonArray1 = (JSONArray) object;
        JSONObject obj  = new JSONObject();
        int size = jsonArray1.size();
        double price = 0;
        for (int i = 0; i < size; i++) {
            JSONObject tes = (JSONObject) jsonArray1.get(i);
            price += Double.parseDouble(tes.get("Price").toString());
        }
        String pr = String.valueOf(price) +" $";
        return pr;
    }
    Admin admin = new Admin();
    @FXML
    void addAdminButton(ActionEvent event){
        boolean usrNameValid,fulNameValid,emailValid,passValid = false;

        //User Name validation
        if (Validator.isOnlyStringNoSpace(userName,userName.getText()) && Validator.isValidString(userName,userName.getText())){
            admin.setUserName(userName.getText());
            usrNameValid = true;
            System.out.println("yea it is validd");
        }else {
            System.out.println("nooo");
            usrNameValid = false;
        }

        //Full Name validation
        if (Validator.isOnlyString(fullName,fullName.getText()) && Validator.isValidString(fullName,fullName.getText())){
            admin.setFullName(fullName.getText());
            fulNameValid = true;
            System.out.println("yea it is validd");
        }else {
            fulNameValid = false;
            System.out.println("nooo");
        }

        //Password validation
        if (Validator.isValidString(password,password.getText())){
            admin.setPassword(password.getText());
            passValid = true;
            System.out.println("yea it is validd");
        }else {
            passValid = false;
            System.out.println("nooo");
        }
        //Email validation
        if (Validator.isValidString(email,email.getText())){
            admin.setEmail(email.getText());
            emailValid = true;
            System.out.println("yea it is validd");
        }else {
            emailValid = false;
            System.out.println("nooo ");
        }
        boolean allValid = usrNameValid && fulNameValid && emailValid && passValid;
        if(allValid) {
            fullName.setText("");
            fullName.setStyle("-fx-border-color: ");
            password.setText("");
            password.setStyle("-fx-border-color: ");
            email.setText("");
            email.setStyle("-fx-border-color: ");
            userName.setText("");
            userName.setStyle("-fx-border-color: ");
            writeToJsonfile();
            Stage stage = (Stage)button.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Dashboard.fxml"));
            Scene root = null;
            try {
                root = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setTitle("Dashboard");
            stage.setResizable(false);
            stage.setScene(root);
        }
    }

    public void writeToJsonfile(){
        JSONObject obj  = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        obj.put("Username",admin.getUserName());
        obj.put("Password",admin.getPassword());
        obj.put("FullName",admin.getFullName());
        obj.put("Email",admin.getEmail());
        jsonArray.add(obj);
        try{
            File f = new File("Admin.json");
            if(!f.exists()){
                FileWriter file = new FileWriter("Admin.json");
                file.write(jsonArray.toJSONString());
                file.close();
            }else{
                JSONParser jsonParser = new JSONParser();
                Object object = jsonParser.parse(new FileReader("Admin.json"));
                JSONArray jsonArray1 = (JSONArray) object;
                jsonArray1.add(obj);
                FileWriter file = new FileWriter("Admin.json");
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

    public void readFromJson() throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();
        Object object = jsonParser.parse(new FileReader("Admin.json"));
        JSONArray jsonArray1 = (JSONArray) object;
        JSONObject obj  = new JSONObject();
        int size = jsonArray1.size();
        for (int i = 0; i < size; i++) {
            Admin adm = new Admin();
            JSONObject tes = (JSONObject) jsonArray1.get(i);
            System.out.println(tes.get("Email"));
            adm.setEmail(tes.get("Email").toString());
            System.out.println(tes.get("Username"));
            adm.setUserName(tes.get("Username").toString());
            System.out.println(tes.get("FullName"));
            adm.setFullName(tes.get("FullName").toString());
            System.out.println(tes.get("Password"));
            adm.setPassword(tes.get("Password").toString());
            adminTable.getItems().add(adm);
            System.out.println("-------------------------------");

        }

    }

    public int totalEmployee(String fileName) {
        File f = new File(fileName);
        if(f.exists()) {
            JSONParser jsonParser = new JSONParser();
            Object object = null;
            try {
                object = jsonParser.parse(new FileReader(fileName));
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }
            JSONArray jsonArray1 = (JSONArray) object;
            return jsonArray1.size();
        }else{
            return 0;
        }
    }


    @FXML
    void deleteAdminButton(ActionEvent event) throws IOException, ParseException {
        Admin d = adminTable.getSelectionModel().getSelectedItem();
        if(d!=null){
            if(displaly("Delete", "Are You Sure?")){
                JSONParser jsonParser = new JSONParser();
                Object object = jsonParser.parse(new FileReader("Admin.json"));
                JSONArray jsonArray1 = (JSONArray) object;
                JSONObject obj  = new JSONObject();
                int size = jsonArray1.size();
                for (int i = 0; i < size; i++) {
                    JSONObject tes = (JSONObject) jsonArray1.get(i);
                    if(tes.get("Username").equals(d.getUserName())){
                        jsonArray1.remove(i);
                        FileWriter file = new FileWriter("Admin.json");
                        file.write(jsonArray1.toJSONString());
                        file.flush();
                        file.close();
                        break;
                    }

                }
                System.out.println(d.getUserName());
                Stage stage = (Stage)button.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Dashboard.fxml"));
                Scene root = new Scene(fxmlLoader.load());
                stage.setTitle("Dashboard");
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
