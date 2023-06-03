package userInterface;

import com.pharmacy.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerController extends SwitchScene implements Initializable {
    @FXML
    private Button button;
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, String> docNameColumn;
    @FXML
    private TableColumn<Customer, String> emailColumn;
    @FXML
    private TableColumn<Customer, String> hospNameColumn;
    @FXML
    private TableColumn<Customer, String> nameColumn;
    @FXML
    private TableColumn<Customer, String> phoneColumn;
    @FXML
    private TableColumn<Customer, String> presciptionColumn;
    @FXML
    private TableColumn<Customer, Character> sexColumn;

    @FXML
    private Text profileName;

    ObservableList<Customer> list = FXCollections.observableArrayList(

    );
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //docNameColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("doctorName"));
        //emailColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("email"));
        //hospNameColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("hospitalName"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("fullName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("phoneNumber"));
        presciptionColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("prescription"));
        sexColumn.setCellValueFactory(new PropertyValueFactory<Customer,Character>("sex"));
        String pfName = "@" + LoginController.getProfName();
        profileName.setText(pfName);
        customerTable.setItems(list);
        File f = new File("Customer.json");
        if(f.exists()){
            try {
                readFromJson();
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }
        }


    }

    public void readFromJson() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        Object object = jsonParser.parse(new FileReader("Customer.json"));
        JSONArray jsonArray1 = (JSONArray) object;
        JSONObject obj  = new JSONObject();
        int size = jsonArray1.size();
        for (int i = 0; i < size; i++) {
            Customer cust = new Customer();
            JSONObject tes = (JSONObject) jsonArray1.get(i);
            //System.out.println(tes.get("Manufaturer"));
            //cust.setDoctorName(tes.get("DrugManufacturer").toString());
            cust.setPrescription(tes.get("Prescription").toString());
            //System.out.println(tes.get("ExpireDate"));
            //cust.setHospitalName(tes.get("ExpireDate").toString());
            cust.setFullName(tes.get("CustomerName").toString());
            //System.out.println(tes.get("Amount"));
            //cust.setEmail(tes.get("Amount").toString());
            cust.setPhoneNumber(tes.get("CustomerPhone").toString());
            //cust.setSex(tes.get("Sex").toString().charAt(0));

            customerTable.getItems().add(cust);
            System.out.println("-------------------------------");

        }

    }
}
