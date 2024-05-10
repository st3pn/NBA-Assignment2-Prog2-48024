package controller;

import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import au.edu.uts.ap.javafx.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import model.*;

public class ViewPlayersController extends Controller<Teams> {

    @FXML private TextField levelFilterTf;
    @FXML private TextField nameFilterTf;
    @FXML private TextField ageFromTf;
    @FXML private TextField ageToTf;
    @FXML private TableView<Player> allPlayersTv;

    public final Teams getTeams() { return model; }

    private String getName() {
        return nameFilterTf.getText();
    }

    private String getLevel() {
        return levelFilterTf.getText();
    }

    private int getAgeFrom() {
        return Integer.valueOf(ageFromTf.getText());
    }

    private int getAgeTo() {
        return Integer.valueOf(ageToTf.getText());
    }

    @FXML private void initialize() {
        allPlayersTv.setItems(getTeams().allPlayersList());

    }

    @FXML private void close() { stage.close();}



}

