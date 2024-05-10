package controller;

import au.edu.uts.ap.javafx.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import model.*;

public class TeamsRoundController extends Controller<Season> {

    @FXML private Label roundHeader;
    @FXML private ListView<Team> teamsLv;
    @FXML private TableView<Game> gamesTv;
    @FXML private Button arrangeButton;
    @FXML private TableColumn<Game, String> team1Column;
    @FXML private TableColumn<Game, String> team2Column;
    @FXML private Button addButton;

    public final Season getSeason() { return model; }

    @FXML private void initialize() {

        roundHeader.setText("Round: " + (getSeason().round() + 1));
        arrangeButton.setDisable(true);

        Label placeholder = new Label("All teams added to round");
        teamsLv.setPlaceholder(placeholder);
        teamsLv.setItems(getSeason().getCurrentTeams());
        teamsLv.getSelectionModel().selectedItemProperty().addListener((observer, oldValue, newValue) -> {
            boolean exactlyTwoSelected = teamsLv.getSelectionModel().getSelectedItems().size() == 2;
            addButton.setDisable(!exactlyTwoSelected);
        });
        teamsLv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        gamesTv.setItems(getSeason().getCurrentSchedule());

        team1Column.setCellValueFactory(cellData ->
        cellData.getValue().team1());

        team2Column.setCellValueFactory(cellData ->
        cellData.getValue().team2());

    }

    private ObservableList<Team> getSelectedTeams() {
        return teamsLv.getSelectionModel().getSelectedItems();
    }

    @FXML private void addToRound(ActionEvent event) throws Exception {
        getSeason().addTeams(getSelectedTeams());
        arrangeButton.setDisable(getSeason().getCurrentTeams().size() != 0);
    }  

    @FXML 
    private void arrangeSeason() {
        stage.close();
    }
}







