package userInterface;
import com.pharmacy.Employee;
import com.pharmacy.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class LoginController {
    @FXML
    private PasswordField adminPassword;
    @FXML
    private Button button;
    @FXML
    private TextField adminUserName;
    @FXML
    private PasswordField emploPassword;
    @FXML
    private TextField emploUserName;
    @FXML
    private Text test;
    JSONArray jsonArray = new JSONArray();
    public static String profName;

    @FXML
    void onLoginButtonClick(ActionEvent event) {

        JSONArray jsonArray = new JSONArray();

        Object ob = null;
        JSONParser jp = new JSONParser();
        try{
            FileReader file = new FileReader("Admin.json");
            ob= jp.parse(file);
            file.close();
            jsonArray = (JSONArray) ob;
        } catch (IOException | ParseException e) {throw new RuntimeException(e);}


        int size = jsonArray.size();
        String usrName = adminUserName.getText();
        String pas = adminPassword.getText();
        for (int i = 0; i < size; i++) {
            JSONObject obj= (JSONObject) jsonArray.get(i);
            if(usrName.equals(obj.get("Username")) && pas.equals(obj.get("Password"))){
                System.out.println("It matched");
                profName = obj.get("Username").toString();
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
                break;
            }else if(i== size-1){
                adminUserName.setStyle("-fx-border-color: red");
                adminPassword.setStyle("-fx-border-color: red");
                System.out.println("nooooo");
            }
        }

    }
    public static String getProfName() {
        return profName;
    }
    @FXML
    void onEmpLoginButtonClick(ActionEvent event) {
        JSONArray jsonArray = new JSONArray();

        Object ob = null;
        JSONParser jp = new JSONParser();
        try{
            FileReader file = new FileReader("Employee.json");
            ob= jp.parse(file);
            file.close();
            jsonArray = (JSONArray) ob;
        } catch (IOException | ParseException e) {throw new RuntimeException(e);}


        int size = jsonArray.size();
        String usrName = emploUserName.getText();
        String pas = emploPassword.getText();
        for (int i = 0; i < size; i++) {
            JSONObject obj= (JSONObject) jsonArray.get(i);
            if(usrName.equals(obj.get("Username")) && pas.equals(obj.get("Password"))){
                System.out.println("It matched");
                profName = obj.get("Username").toString();
                Stage stage = (Stage)button.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Employee-Customer.fxml"));
                Scene root = null;
                try {
                    root = new Scene(fxmlLoader.load());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.setTitle("Dashboard");
                stage.setResizable(false);
                stage.setScene(root);
                break;
            }else if(i== size-1){
                emploUserName.setStyle("-fx-border-color: red");
                emploPassword.setStyle("-fx-border-color: red");
                System.out.println("nooooo");
            }
        }
    }
}
