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
import javafx.scene.layout.AnchorPane;

public class FXMLDocumentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private AnchorPane anchorpane_tableview_bp;
    
    @FXML
    private AnchorPane anchorpane_bp;

    @FXML
    private Label label;

    @FXML
    private TextField titel;

    @FXML
    private TextArea beschrijvingen;

    @FXML
    private Button voegBPtoe;
    
    @FXML
    private Button button_GoToMenu_bp;

    @FXML
    private AnchorPane anchorpane_student;
    
    @FXML
    private TextField textfield_naamStudent;
    
    @FXML
    private TextField textfield_wachtwoord;

    @FXML
    private TextField textfield_herhaalWachtwoord;


    @FXML
    private Button voegStudenttoe;

    @FXML
    private Label label_foutWachtwoord;

    @FXML
    private Button button_goToMenu_student;

    @FXML
    private AnchorPane anchorpane_menu;

    @FXML
    private Button button_goToStudent;

    @FXML
    private Button button_goToBP;
    
    @FXML
    private TableView<Bachelorproef> bachelorproeven;

    @FXML
    private TableColumn<Bachelorproef, String> titel_column;

    @FXML
    private TableColumn<Bachelorproef, String> beschrijving_column;
    
    @FXML
    private TableView<?> studenten;

    @FXML
    private TableColumn<?, ?> column_id;

    @FXML
    private TableColumn<?, ?> column_naam;
    
    private BachelorproevenDB model;

    @FXML
    void initialize() {
        model = new BachelorproevenDB();
        voegBPtoe.setOnAction(event -> voegBPToe());
        button_goToStudent.setOnAction(event -> gaNaarStudentenScherm());
        button_goToMenu_student.setOnAction(event -> gaNaarMenu_Student() );
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
        bachelorproeven.setItems(model.getProeven());
    }

    public void gaNaarStudentenScherm() {
       anchorpane_menu.setVisible(false);
       anchorpane_student.setVisible(true);
    }

    public void gaNaarMenu_Student() {
        if(textfield_wachtwoord.getText().equals(textfield_herhaalWachtwoord.getText())){
            anchorpane_student.setVisible(false);
            anchorpane_menu.setVisible(true);
        } else {
            label_foutWachtwoord.setText("wachtwoorden komen niet overeen");
        }
    }
}

