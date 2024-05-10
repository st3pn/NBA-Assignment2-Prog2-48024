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

public class ExploreTeamsController extends Controller<Teams> {
    
    @FXML
    private Button teamsMenuButton;
    @FXML
    private Button viewPlayersButton;
    @FXML 
    private Button closeButton;
    @FXML 
    private GridPane buttonGrid;

    public final Teams getTeamsController() { return this.model;}

    @FXML 
    private void openTeamsMenu() {

        try {
            Stage stage = new Stage();
            stage.setMinHeight(420);
            stage.setMinWidth(600);
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(getTeamsController(), "/view/TeamsTable.fxml", "Teams View", stage);
        } catch (IOException ex) {
            Logger.getLogger(ExploreTeamsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void viewPlayers(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/view/nba.png"));
        ViewLoader.showStage(getTeamsController(), "/view/PlayersView.fxml", "Players", stage);
    }

    @FXML 
    private void close() {
        stage.close();
    }
}

