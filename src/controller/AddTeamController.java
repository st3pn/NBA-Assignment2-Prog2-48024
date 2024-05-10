package controller;

import au.edu.uts.ap.javafx.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import model.*;

public class AddTeamController extends Controller<Teams> {

    public Validator validator = new Validator();

    @FXML private TextField nameTF;
    @FXML private Button addButton;

    private final Teams getTeams() {
        return model;
    }

    private String getName() {
        return nameTF.getText();
    }

    @FXML 
    private void initialize() {
    }

    private String setErrorMessage() {
        validator.clear();
        validator.generateErrors(getName());
        String errorsString = validator.errors().toString();
        errorsString =  errorsString.substring(1, errorsString.length()-1);
        errorsString = errorsString.replaceAll("!", "!\n");
        errorsString = errorsString.replaceAll(",","");
        errorsString = errorsString.replaceAll("\\s+", " ");
        return errorsString;
    }
    
    @FXML 
    private void handleAdd(ActionEvent event) throws Exception {
        boolean valid = validator.isValid(getName());
        if (valid) {
            Team team = new Team(getName());
            getTeams().addTeam(team);
            stage.close();
        } else {
            Stage errorStage = new Stage();
            errorStage.getIcons().add(new Image("/view/error.png"));
            ViewLoader.showStage(new InputException(setErrorMessage()), "/view/error.fxml", "Input Exception", errorStage);
        }
    }   
}
