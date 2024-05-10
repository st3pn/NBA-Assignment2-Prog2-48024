package controller;

import au.edu.uts.ap.javafx.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import model.*;

public class SeasonController extends Controller<Season>  {

   @FXML private ImageView imageView;

    public final Season getSeason() { return model; }

   @FXML private void initialize() {
         URL url = getClass().getResource("/view/NBA_Back.jpg");
         Image image = new Image(url.toString());
         imageView.setImage(image);
         System.out.println(url.toString());
   }
   
    @FXML private void close() { stage.close();}

    @FXML
     private void viewSeasonRound(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/view/nba.png"));
        ViewLoader.showStage(getSeason(), "/view/SeasonRoundView.fxml", "Season Rounds", stage);
     }

   @FXML 
   private void viewCurrentRoundTeams(ActionEvent event) throws Exception {
      Stage stage = new Stage();
      stage.getIcons().add(new Image("/view/nba.png"));
      ViewLoader.showStage(getSeason(), "/view/CurrentRoundTeams.fxml", "Tournament", stage);
   }

   @FXML 
   private void handleGame(ActionEvent event) throws Exception {
      boolean hasGames = !getSeason().getCurrentSchedule().isEmpty();
      if (hasGames) {
         Stage stage = new Stage();
         stage.getIcons().add(new Image("/view/nba.png"));
         ViewLoader.showStage(new InputException(getSeason().playGame()), "/view/error.fxml", "All Games Played!", stage);
      } else {
         Stage stage = new Stage();
         stage.getIcons().add(new Image("/view/nba.png"));         
         ViewLoader.showStage(new InputException("No Games to play!\nPlease add game to this round"), "/view/error.fxml", "All Games Played!", stage);
      }
   }

   @FXML 
   private void viewResult(ActionEvent event) throws Exception {
      Stage stage = new Stage();
      stage.getIcons().add(new Image("/view/nba.png"));
      ViewLoader.showStage(getSeason(), "/view/RecordView.fxml", "Season Rounds", stage);
   }
}

