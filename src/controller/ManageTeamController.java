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


public class ManageTeamController extends Controller<Team> {
    
    @FXML private TextField nameTF;
    @FXML private TableView<Player> playersTv;
    @FXML private Button addPlayerButton;
    @FXML private Button updatePlayerButton;
    @FXML private Button deletePlayerButton;
    @FXML private Button closeButton;

    public final Team getTeam() { return model;}

    private String getName() {
        return nameTF.getText();
    }

    @FXML
    private void initialize() {
        playersTv.setItems(getTeam().getCurrentPlayers());
        nameTF.setText(getTeam().getName());

        updatePlayerButton.setDisable(true);
        deletePlayerButton.setDisable(true);

        playersTv.getSelectionModel().selectedItemProperty().addListener((observable, oldTeam, newTeam) -> addPlayerButton.setDisable(newTeam != null));
        playersTv.getSelectionModel().selectedItemProperty().addListener((observable, oldTeam, newTeam) -> updatePlayerButton.setDisable(newTeam == null));
        playersTv.getSelectionModel().selectedItemProperty().addListener((observable, oldTeam, newTeam) -> deletePlayerButton.setDisable(newTeam == null));
    }
    
    @FXML
    private void handleAddPlayer(ActionEvent event) throws Exception {
        Player newPlayer = new Player("", -1.0, -1, -1);
        Stage stage = new Stage();
        stage.getIcons().add(new Image("view/nba.png"));
        model.getPlayers().addPlayer(newPlayer);
        playersTv.setItems(getTeam().getCurrentPlayers());
        ViewLoader.showStage(newPlayer, "/view/PlayerUpdateView.fxml", "Adding New Player", stage);
    }

    @FXML
    private void handleDeletePlayer() {
        ObservableList<Player> selectedPlayers = playersTv.getSelectionModel().getSelectedItems();
        ArrayList<Player> selectedPlayersList = new ArrayList<>(selectedPlayers);
        getTeam().getPlayers().remove(selectedPlayersList); 
    }

    @FXML 
    private void handleUpdatePlayer(ActionEvent event) throws Exception {
        Player player = playersTv.getSelectionModel().getSelectedItem();
        Stage stage = new Stage();
        stage.getIcons().add(new Image("view/nba.png"));
        playersTv.setItems(getTeam().getCurrentPlayers());
        ViewLoader.showStage(player, "/view/PlayerUpdateView.fxml", "Updating Player: " + player.getName(), stage);
    }

    @FXML 
    private void close() {
        getTeam().setName(getName());
        stage.close();
    }
}
