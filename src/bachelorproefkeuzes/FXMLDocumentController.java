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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
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
    private AnchorPane anchorpane_student;
    
    @FXML
    private Label label_student_BPselected;
    
    @FXML
    private Label label_student_BPwarning;
    
    @FXML
    private Label label_student_BP;

    @FXML
    private Label label_student_beschrijving;
    
    @FXML
    private TableView<Bachelorproef> tableview_student_BPaanvraag;

    @FXML
    private TableColumn<Bachelorproef, String> tablecolumn_AanvraagBP_titel;

    @FXML
    private TableColumn<Bachelorproef, String> tablecolumn_AanvraagBP_beschrijving;

    @FXML
    private Button button_student_BPaanvragen;
    
    @FXML
    private Button button_student_uitloggen;
    
    @FXML
    private Label label_student_studentID;

    @FXML
    private Label label_student_name;
    
    @FXML
    private TextField textfield_student_Wveranderen;

    @FXML
    private TextField textfield_student_HWveranderen;

    @FXML
    private Button button_student_Wveranderen;
    
    @FXML
    private AnchorPane anchorpane_login;

    @FXML
    private TextField textfield_login_studentID;

    @FXML
    private TextField textfield_login_paswoord;

    @FXML
    private Button button_login_login;

    @FXML
    private Label label_login_Wfout;

    @FXML
    private Button button_login_Wvergeten;

    @FXML
    private CheckBox checkbox_admin;
    
    @FXML
    private AnchorPane anchorpane_admin;
    
    @FXML
    private Button button_admin_Uitloggen;
    
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
    private AnchorPane anchorpane_admin_student;
    
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
        button_login_login.setOnAction(event -> login());
        button_login_Wvergeten.setOnAction(event -> wachtwoordVergeten());
        button_student_uitloggen.setOnAction(event -> studentUitloggen());
        button_admin_Uitloggen.setOnAction(event -> adminUitloggen());
        button_student_Wveranderen.setOnAction(event -> wachtwoordVeranderen());
        button_student_BPaanvragen.setOnAction(event -> aanvraagBP());
        
        
        vulTabellen();
        
        tableview_student_BPaanvraag.getSelectionModel().selectedItemProperty()
                    .addListener((observable,oud,nieuw) -> toonGeselecteerdeBP(nieuw));
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
    
    public void vulBPaanvraagTabel() {
        tablecolumn_AanvraagBP_titel.setCellValueFactory(cel -> cel.getValue().titelProperty());
        tablecolumn_AanvraagBP_beschrijving.setCellValueFactory(cel -> cel.getValue().beschrijvingProperty());
        tableview_student_BPaanvraag.setItems(modelBP.getBeschikbareProeven());
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
        String paswoord= textfield_wachtwoord_deleteS.getText();
        if(paswoord.equals(modelStudent.getWachtwoord(studentID))){
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
    
    public void login() {
        String text = textfield_login_studentID.getText();
        Integer studentID = Integer.parseInt(text); 
        String paswoord = textfield_login_paswoord.getText();
        if( paswoord.equals(modelStudent.getWachtwoord(studentID)) ){
               if(checkbox_admin.isSelected()){
                   anchorpane_login.setVisible(false);
                   anchorpane_admin.setVisible(true);
                   textfield_login_paswoord.clear();
               } else {
                   anchorpane_login.setVisible(false);
                   anchorpane_student.setVisible(true);
                   vulBPaanvraagTabel();
                   label_student_studentID.setText(textfield_login_studentID.getText());
                   label_student_name.setText(modelStudent.getNaam(studentID));
                   
                   label_student_BP.setText(modelBP.getBPkeuze(studentID));
                   label_student_beschrijving.setText(modelBP.getBPbeschrijvingKeuze(studentID));
                   
                   textfield_login_paswoord.clear();
               }
        
        } else {
            label_login_Wfout.setText("het wachtwoord is fout of de student ID bestaat niet");
            textfield_login_paswoord.clear();
        }
    }
    
    public void wachtwoordVergeten() {
        String text = textfield_login_studentID.getText();
        Integer studentID = Integer.parseInt(text);
        String paswoord = modelStudent.getWachtwoord(studentID);
        button_login_Wvergeten.setText(paswoord);
    }
    
    public void wachtwoordVeranderen() {
        String paswoord = textfield_student_Wveranderen.getText();
        String herhalingPaswoord = textfield_student_HWveranderen.getText();
        String text = label_student_studentID.getText();
        Integer studentID = Integer.parseInt(text);
        if(paswoord.equals(herhalingPaswoord)){
            modelStudent.wachtwoordVeranderen(studentID, paswoord);
            textfield_student_Wveranderen.clear();
            textfield_student_HWveranderen.clear();
        } else {
            textfield_student_Wveranderen.setStyle("-fx-text-inner-color: red;");
            textfield_student_HWveranderen.setStyle("-fx-text-inner-color: red;");
        }
    }
    
    public void aanvraagBP() {
        if(label_student_BP.getText()== null && label_student_beschrijving.getText() == null){
            Integer bpID = modelBP.getID(label_student_BPselected.getText());
            String text = label_student_studentID.getText();
            Integer studentID = Integer.parseInt(text);
            
            modelStudent.voegKeuzeToe(studentID, bpID);
            label_student_BP.setText(modelBP.getBPkeuze(studentID));
            label_student_beschrijving.setText(modelBP.getBPbeschrijvingKeuze(studentID));   
        } else {
            label_student_BPwarning.setText("u heeft al een bachelorproef!");    
        }
    }
    
    private void toonGeselecteerdeBP(Bachelorproef nieuw) {
        String titel = nieuw.getTitel();
        label_student_BPselected.setText(titel);
    }
    
    public void gaNaarStudentScherm() {
       anchorpane_menu.setVisible(false);
       anchorpane_admin_student.setVisible(true);
    }
    public void gaNaarBachelorproefScherm() {
        anchorpane_menu.setVisible(false);
        anchorpane_bp.setVisible(true);
    }
    public void gaNaarMenu_Student() {
        anchorpane_admin_student.setVisible(false);
        anchorpane_menu.setVisible(true);    
    }
    public void gaNaarMenu_BP() {
        anchorpane_bp.setVisible(false);
        anchorpane_menu.setVisible(true);
    }     
    public void studentUitloggen() {
        anchorpane_student.setVisible(false);
        anchorpane_login.setVisible(true);
        label_student_BPwarning.setText("");
        label_student_BPselected.setText("");
    }
    public void adminUitloggen() {
        anchorpane_admin.setVisible(false);
        anchorpane_login.setVisible(true);
    } 

    
}

