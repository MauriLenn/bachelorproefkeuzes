/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bachelorproefkeuzes;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLDocumentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label label;

    @FXML
    private TextField titel;

    @FXML
    private TextArea beschrijvingen;

    @FXML
    private Button voegBPtoe;
    
    @FXML
    private TableView<Bachelorproef> bachelorproeven;

    @FXML
    private TableColumn<Bachelorproef, String> titel_column;

    @FXML
    private TableColumn<Bachelorproef, String> beschrijving_column;
    
    @FXML
    private TableColumn<Bachelorproef, Number> id_column;
    
    private BachelorproevenDB model;

    @FXML
    void initialize() {
        model = new BachelorproevenDB();
        voegBPtoe.setOnAction(event -> voegBPToe());
        vulTabel();
    }
    
    public void voegBPToe(){
        Bachelorproef nieuw = new Bachelorproef(titel.getText(), 
                                                beschrijvingen.getText());
        model.voegToe(nieuw);
        ObservableList<Bachelorproef> alles = model.getProeven();
        voegBPtoe.setText(alles.size() + " proeven");
        vulTabel();
    }
    
    public void vulTabel(){
        titel_column.setCellValueFactory(cel -> cel.getValue().titelProperty());
        beschrijving_column.setCellValueFactory(cel -> cel.getValue().beschrijvingProperty());
        id_column.setCellValueFactory(cel -> cel.getValue().idProperty());
        bachelorproeven.setItems(model.getProeven());
    }
}

