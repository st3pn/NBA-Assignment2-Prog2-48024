package controller;

import au.edu.uts.ap.javafx.*;
import javafx.application.Platform;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import model.*;


public class ManageTeamController extends Controller<Team> {

    private Validator validator = new Validator();
    @FXML private TextField nameTF;
    @FXML private TableView<Player> playersTv;
    @FXML private Button addPlayerButton;
    @FXML private Button updatePlayerButton;
    @FXML private Button deletePlayerButton;
    @FXML private Button closeButton;

    public final Team getTeam() { return model;}

    private String getErrorMessages() {
        validator.clear();
        ArrayList<String> errorsList = new ArrayList<>();
        validator.generateErrors(getName());
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
        
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
            Platform.runLater(() -> {
                    playersTv.refresh();
                });
            }
        }, 0, 200);

    }
    
    @FXML
    private void handleAddPlayer(ActionEvent event) throws Exception {
        Player newPlayer = new Player("", -1.0, -1, -1);
        newPlayer.setTeam(getTeam());
        Stage stage = new Stage();
        stage.getIcons().add(new Image("view/nba.png"));
        getTeam().getPlayers().addPlayer(newPlayer);
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
    private void close(ActionEvent event) throws Exception {
        boolean valid = validator.isValid(getName());
        if (valid) {
            getTeam().setName(getName());
            stage.close();
        } else {
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/view/error.png"));
            ViewLoader.showStage(new InputException(getErrorMessages()), "/view/error.fxml", "Input Exceptions", stage);
        }
    }
}
