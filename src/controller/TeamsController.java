package controller;

import javafx.application.Platform;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import au.edu.uts.ap.javafx.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import model.*;

public class TeamsController extends Controller<Teams> {

    @FXML private TableView<Team> teamsTv;
    @FXML private TableColumn<Team, String> numPlayersColumn;
    @FXML private TableColumn<Team, String> avgPlayerCreditColumn;
    @FXML private TableColumn<Team, String> avgAgeColumn;
    @FXML private Button addButton;
    @FXML private Button manageButton;
    @FXML private Button deleteButton;
    @FXML private Button closeButton;

    public final Teams getTeamsMethod() {return model;}

    @FXML
    private void initialize() { 
        teamsTv.setItems(getTeamsMethod().currentTeams());

        numPlayersColumn.setCellValueFactory(cellData -> 
        cellData.getValue().countPlayerProperty().asString());
        avgPlayerCreditColumn.setCellValueFactory(cellData ->
        cellData.getValue().countAvgCreditProperty().asString());
        avgAgeColumn.setCellValueFactory(cellData -> 
        cellData.getValue().countAvgAgeProperty().asString());

        manageButton.setDisable(true);
        deleteButton.setDisable(true);

        teamsTv.getSelectionModel().selectedItemProperty().addListener((observable, oldTeam, newTeam) -> addButton.setDisable(newTeam != null));
        teamsTv.getSelectionModel().selectedItemProperty().addListener((observable, oldTeam, newTeam) -> manageButton.setDisable(newTeam == null));
        teamsTv.getSelectionModel().selectedItemProperty().addListener((observable, oldTeam, newTeam) -> deleteButton.setDisable(newTeam == null));

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
            Platform.runLater(() -> {
                    teamsTv.refresh();
                });
            }
        }, 0, 200);
    }

    private Team getSelectedTeam() {
        return teamsTv.getSelectionModel().getSelectedItem();
    }

    @FXML 
    private void handleAddTeam(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        stage.getIcons().add(new Image("view/nba.png"));
        ViewLoader.showStage(getTeamsMethod(), "/view/AddTeam.fxml", "Adding New Team", stage);
    }

    @FXML 
    private void manageTeam(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/view/nba.png"));
        ViewLoader.showStage(getSelectedTeam(), "/view/ManageTeamView.fxml", "Managing Team:" + getSelectedTeam().getName(), stage);
    }

    @FXML 
    private void deleteTeam() {
        model.remove(getSelectedTeam());
    }
    

    @FXML private void close(){ stage.close(); }
}

