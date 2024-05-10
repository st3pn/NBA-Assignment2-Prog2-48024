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

public class RecordController extends Controller<Season>{

    @FXML private TableView<Record> recordsTv;
    @FXML private TableColumn<Record, Integer> roundColumn;

    public final Season getSeason() { return model; }

    @FXML
    private void initialize() {
        recordsTv.setItems(getSeason().record());
    } 

    @FXML private void close() { stage.close();}
}







