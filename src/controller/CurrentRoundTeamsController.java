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

public class CurrentRoundTeamsController extends Controller<Season> {
    
    @FXML private TableView<Game> roundTv;
    @FXML private TableColumn<Game, String> team1Column;
    @FXML private TableColumn<Game, String> team2Column;
    @FXML private Label roundLabel;
    @FXML private TableColumn<Game, String> vsColumn;

    public final Season getSeason() { return model;}

    @FXML private void initialize() {

        roundLabel.setText("Round: " + (getSeason().round() + 1));
        roundTv.setItems(getSeason().getCurrentSchedule());
        team1Column.setCellValueFactory(cellData ->
        cellData.getValue().team1());
        team2Column.setCellValueFactory(cellData ->
        cellData.getValue().team2());

        vsColumn.setCellFactory(tv -> new TableCell<Game, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty? "" : "VS"); 
            }
        });
    }

    @FXML private void close() { stage.close(); }


}







