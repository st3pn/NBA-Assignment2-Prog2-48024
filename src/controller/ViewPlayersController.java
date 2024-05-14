package controller;

import javafx.collections.FXCollections;
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

    private ObservableList<Player> playersToDisplay;

    public ViewPlayersController() {
        playersToDisplay = FXCollections.<Player>observableArrayList();
    }

    public final Teams getTeams() { return model; }

    private String getName() {
        return nameFilterTf.getText();
    }

    private String getLevel() {
        return levelFilterTf.getText();
    }

    private Integer getAgeFrom() {
        if (ageFromTf.getText().isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(ageFromTf.getText());
        }
    }

    private Integer getAgeTo() {
        if (ageToTf.getText().isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(ageToTf.getText());
        }
    }

    @FXML private void initialize() {
        setPlayers();
        allPlayersTv.setItems(playersToDisplay);
        
        nameFilterTf.textProperty().addListener(Event -> setPlayers());
        levelFilterTf.textProperty().addListener(Event -> setPlayers());
        ageFromTf.textProperty().addListener(Event -> setPlayers());
        ageToTf.textProperty().addListener(Event -> setPlayers());
    }

    private void setPlayers() {
        playersToDisplay.clear();
        for (Team team : getTeams().currentTeams()) {
            team.filterList(getName(), getLevel(), getAgeFrom(), getAgeTo());
            playersToDisplay.addAll(team.getPlayers().getFilteredPlayersList());
        }
    }
    
    @FXML private void close() { stage.close();}
}

