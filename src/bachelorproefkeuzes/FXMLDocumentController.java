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
    private TextField textfield_BP_verwijderen;

    @FXML
    private Button button_BP_verwijderen;

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
    private Button button_haalWop;

    @FXML
    private AnchorPane anchorpane_student;
    
    @FXML
    private TextField textfield_studentID_deleteS;

    @FXML
    private TextField textfield_wachtwoord_deleteS;
    
    @FXML
    private Button button_verwijderStudent;
    
    @FXML
    private TextField textfield_naamStudent;
    
    @FXML
    private TextField textfield_wachtwoord;

    @FXML
    private TextField textfield_herhaalWachtwoord;
    
    @FXML
     private TextField textfield_studentID_getW;
    
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
    private TableView<Student> studenten;

    @FXML
    private TableColumn<Student, Number> column_id;

    @FXML
    private TableColumn<Student, String> column_naam;
    
    private BachelorproevenDB modelBP;
    private StudentenDB modelStudent;

    @FXML
    void initialize() {
        modelBP = new BachelorproevenDB();
        modelStudent = new StudentenDB();
        
        voegBPtoe.setOnAction(event -> voegBPToe());
        voegStudenttoe.setOnAction(event -> voegStudentToe());
        
        button_goToStudent.setOnAction(event -> gaNaarStudentScherm());
        button_goToBP.setOnAction(event -> gaNaarBachelorproefScherm());
        button_goToMenu_student.setOnAction(event -> gaNaarMenu_Student());
        button_GoToMenu_bp.setOnAction(event -> gaNaarMenu_BP());
        button_haalWop.setOnAction(event -> haalWachtwoordOp());
        button_verwijderStudent.setOnAction(event -> verwijderStudent());
        button_BP_verwijderen.setOnAction(event -> verwijderBP());
        
        
        vulTabellen();
    }
    
    public void voegBPToe(){
        if(titel.getText().equals(modelBP.getTitelBP(titel.getText()))){
            titel.setText("//deze BP bestaat al");
        }
        else{
        Bachelorproef nieuw = new Bachelorproef(titel.getText(), 
                                                beschrijvingen.getText());
        modelBP.voegToe(nieuw);
        ObservableList<Bachelorproef> alles = modelBP.getProeven();
        voegBPtoe.setText(alles.size() + " proeven");
        titel.clear();
        beschrijvingen.clear();
        vulTabellen();
        }
    }
    public void voegStudentToe() {     
        if(textfield_wachtwoord.getText().equals(textfield_herhaalWachtwoord.getText())){
            label_foutWachtwoord.setText("");
            Student nieuw = new Student(textfield_naamStudent.getText(),textfield_wachtwoord.getText());
            modelStudent.voegToe(nieuw);
            ObservableList<Student> alles = modelStudent.getProeven();
            voegStudenttoe.setText(alles.size() + " studenten");
            textfield_naamStudent.clear();
            textfield_wachtwoord.clear();
            vulTabellen();
        } else {
            label_foutWachtwoord.setText("wachtwoorden komen niet overeen");
            textfield_wachtwoord.clear();
            textfield_herhaalWachtwoord.clear();
        }    
    } 
    
    public void vulTabellen(){
        titel_column.setCellValueFactory(cel -> cel.getValue().titelProperty());
        beschrijving_column.setCellValueFactory(cel -> cel.getValue().beschrijvingProperty());
        bachelorproeven.setItems(modelBP.getProeven());
        
        column_id.setCellValueFactory(cel -> cel.getValue().idProperty());
        column_naam.setCellValueFactory(cel -> cel.getValue().naamProperty() );
        studenten.setItems(modelStudent.getProeven());
    }
    
    public void haalWachtwoordOp() {
        String text = textfield_studentID_getW.getText();
        Integer studentID = Integer.parseInt(text);
        String wachtwoord = modelStudent.getWachtwoord(studentID);
        button_haalWop.setText(wachtwoord);
    }
    
    public void verwijderStudent() {
        String text = textfield_studentID_deleteS.getText();
        Integer studentID = Integer.parseInt(text);
        String wachtwoord = textfield_wachtwoord_deleteS.getText();
        if(wachtwoord.equals(modelStudent.getWachtwoord(studentID))){
            modelStudent.verwijderStudent(studentID);
            vulTabellen();
            textfield_studentID_deleteS.clear();
            textfield_wachtwoord_deleteS.clear();
        } else {
            
        }      
    }
    
    public void verwijderBP() {
        String titelBP = textfield_BP_verwijderen.getText();
        modelBP.verwijderBP(titelBP);
        vulTabellen();
        textfield_BP_verwijderen.clear();
    }
    
    public void gaNaarStudentScherm() {
       anchorpane_menu.setVisible(false);
       anchorpane_student.setVisible(true);
    }
    public void gaNaarBachelorproefScherm() {
        anchorpane_menu.setVisible(false);
        anchorpane_bp.setVisible(true);
    }
    public void gaNaarMenu_Student() {
        anchorpane_student.setVisible(false);
        anchorpane_menu.setVisible(true);    
    }
    public void gaNaarMenu_BP() {
        anchorpane_bp.setVisible(false);
        anchorpane_menu.setVisible(true);
    }  

}

