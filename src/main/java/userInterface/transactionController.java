package userInterface;

import com.pharmacy.Customer;
import com.pharmacy.HelloApplication;
import com.pharmacy.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class transactionController extends SwitchScene implements Initializable {
    @FXML
    private TableColumn<Transaction, Integer> amountColumn;

    @FXML
    private Button button;

    @FXML
    private TableColumn<Transaction, String> dateColumn;

    @FXML
    private TableColumn<Transaction, Integer> idColumn;

    @FXML
    private TableColumn<Transaction, String> manuColumn;

    @FXML
    private TableColumn<Transaction, String> medNameColumn;

    @FXML
    private TableColumn<Transaction, Double> priceColumn;

    @FXML
    private Text profileName;

    @FXML
    private TableView<Transaction> transTable;

    ObservableList<Transaction> list = FXCollections.observableArrayList(

    );
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        priceColumn.setCellValueFactory(new PropertyValueFactory<Transaction,Double>("price"));
        medNameColumn.setCellValueFactory(new PropertyValueFactory<Transaction,String>("drugName"));
        manuColumn.setCellValueFactory(new PropertyValueFactory<Transaction,String>("manuName"));
        idColumn.setCellValueFactory(new PropertyValueFactory<Transaction,Integer>("id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Transaction,String>("date"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<Transaction,Integer>("quantity"));
        String pfName = "@" + LoginController.getProfName();
        profileName.setText(pfName);
        //transTable.setItems(list);
        File f = new File("Transaction.json");
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
        Object object = jsonParser.parse(new FileReader("Transaction.json"));
        JSONArray jsonArray1 = (JSONArray) object;
        JSONObject obj  = new JSONObject();
        int size = jsonArray1.size();
        for (int i = 0; i < size; i++) {
            Transaction trans = new Transaction();
            JSONObject tes = (JSONObject) jsonArray1.get(i);
            System.out.println(tes.get("Quantity"));
            trans.setQuantity(Integer.parseInt(tes.get("Quantity").toString()));
            System.out.println(tes.get("DrugName"));
            trans.setDrugName(tes.get("DrugName").toString());
            System.out.println(tes.get("DrugManufacturer"));
            trans.setManuName(tes.get("DrugManufacturer").toString());
            System.out.println(tes.get("Amount"));
            trans.setPrice(Double.parseDouble(tes.get("Price").toString()));
            System.out.println(tes.get("Date"));
            trans.setId(Integer.parseInt(tes.get("DrugId").toString()));
            System.out.println(tes.get("DrugId"));

            transTable.getItems().add(trans);
            System.out.println("-------------------------------");

        }

    }

}
