package userInterface;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class Validator {
    public static boolean isValidInt(TextField in, String str){
        int val;
        try {
            val = Integer.parseInt(str);
            in.setStyle("-fx-border-color:green");
            return true;
        }catch (NumberFormatException e){
            in.setStyle("-fx-border-color:red");
            return false;
        }
    }
    public static boolean isValidInt(String str){
        int val;
        try {
            val = Integer.parseInt(str);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    public static boolean isValidFloat(TextField in, String str){
        float val;
        try {
            val = Float.parseFloat(str);
            in.setStyle("-fx-border-color:green");
            return true;
        }catch (NumberFormatException e){
            in.setStyle("-fx-border-color:red");
            return false;
        }
    }

    public static boolean isValidString(TextField in, String str){
        if (str.trim().isEmpty()){
            in.setStyle("-fx-border-color:red");
            return false;
        }else{
            in.setStyle("-fx-border-color:green");
            return true;
        }
    }

    public static boolean isValidString(TextArea in, String str){
        if (str.trim().isEmpty()){
            in.setStyle("-fx-border-color:red");
            return false;
        }else{
            in.setStyle("-fx-border-color:green");
            return true;
        }
    }

    public static boolean isOnlyString(TextField in, String str){
        if (!in.getText().matches("^[a-zA-z ]*$")){
            in.setStyle("-fx-border-color:red");
            return false;
        }else{
            in.setStyle("-fx-border-color:green");
            return true;
        }
    }

    public static boolean isOnlyStringNoSpace(TextField in, String str){
        if (!in.getText().matches("^[a-zA-z]*$")){
            in.setStyle("-fx-border-color:red");
            return false;
        }else{
            in.setStyle("-fx-border-color:green");
            return true;
        }
    }

    public static boolean isValidPhoneNumber(TextField in, String str){
        if (!in.getText().matches("^[0-9]{8,10}$")){
            in.setStyle("-fx-border-color:red");
            return false;
        }else{
            in.setStyle("-fx-border-color:green");
            return true;
        }
    }

    public static boolean isValidDate(DatePicker in, LocalDate date){
        if (in.getValue() == null){
            in.setStyle("-fx-border-color:red");
            return false;
        }else{
            in.setStyle("-fx-border-color:green");
            return true;
        }
    }

    //userName.getText().matches("^[a-zA-z]*$"))


}
