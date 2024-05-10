package controller;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import au.edu.uts.ap.javafx.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import model.*;
import javafx.util.Duration;
import java.util.*;

public class PlayerUpdateController extends Controller<Player> {
   
    @FXML private TextField nameTf;
    @FXML private TextField creditTf;
    @FXML private TextField ageTf;
    @FXML private TextField noTf;
    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private Button closeButton;

    public Validator validator = new Validator();

    public final Player getPlayer() { return model;} 

    private String getName() {
        return nameTf.getText();
    }

    private String getCredit() {
        return creditTf.getText();
    }

    private String getAge() {
        return ageTf.getText();
    }

    private String getNo() {
        return noTf.getText();
    }

    private String getErrorMessages() {
        validator.clear();
        ArrayList<String> errorsList = new ArrayList<>();
        validator.generateErrors(getName(), getCredit(), getAge(), getNo());
        for (String error : validator.errors()) {
            errorsList.add(error);
        }
        String errorsString = errorsList.toString();
        errorsString =  errorsString.substring(1, errorsString.length()-1);
        errorsString = errorsString.replaceAll(",","");
        errorsString = errorsString.replaceAll("\\s+", " ");
        errorsString = errorsString.replaceAll("!", "!\n");
        return errorsString;
    }

    @FXML private void initialize() {
        if (getPlayer().getName().equals("")) {
            creditTf.setText(getPlayer().getCredit().toString());
            ageTf.setText(getPlayer().getAge().toString());
            noTf.setText(getPlayer().getNo().toString());
            updateButton.setDisable(true);
        } else {
            nameTf.setText(getPlayer().getName());
            creditTf.setText(getPlayer().getCredit().toString());
            ageTf.setText(getPlayer().getAge().toString());
            noTf.setText(getPlayer().getNo().toString());
            addButton.setDisable(true);
        }
    }

    @FXML 
    private void handleAdd(ActionEvent event) throws Exception {
        boolean valid = validator.isValid(getName(), getCredit(), getAge(), getNo());
        if (valid) {
            getPlayer().update(getName(), Double.parseDouble(getCredit()), Integer.valueOf(getAge()), Integer.valueOf(getNo()));
            stage.close();
            System.out.println(getPlayer().getCredit());
        } else {
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/view/error.png"));
            ViewLoader.showStage(new InputException(getErrorMessages()), "/view/error.fxml", "Input Exceptions", stage);
        }
    }

    @FXML 
    private void handleUpdate() throws Exception {
        boolean valid = validator.isValid(getName(), getCredit(), getAge(), getNo());
        if (valid) {
            getPlayer().update(getName(), Double.parseDouble(getCredit()), Integer.valueOf(getAge()), Integer.valueOf(getNo())); 
            stage.close();
        } else {
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/view/error.png"));
            ViewLoader.showStage(new InputException(getErrorMessages()), "/view/error.fxml", "Input Exceptions", stage);
        }
    }

    @FXML private void close() { stage.close();}

}
