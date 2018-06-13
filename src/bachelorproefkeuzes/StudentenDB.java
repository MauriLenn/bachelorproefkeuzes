/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachelorproefkeuzes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Lennert
 */
public class StudentenDB {
    private Connection connectie;
    
    /**
     *
     */
    public StudentenDB(){
        try {
            connectie = DriverManager.getConnection( "jdbc:mysql://localhost:3306/bpkeuzes",
                    "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(BachelorproevenDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     * @param student
     */
    public void voegToe (Student student){
        String sql = "insert into student (naam,paswoord)" + "values (?,?)";
        PreparedStatement stmt;
        try {
            stmt = connectie.prepareStatement(sql,
                            PreparedStatement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, student.getNaam());
            stmt.setString(2, student.getPaswoord());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(BachelorproevenDB.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    /**
     *
     * @param studentID
     * @param nieuwPaswoord
     */
    public void wachtwoordVeranderen(Integer studentID, String nieuwPaswoord){
        try {
            String sql = "update student set paswoord = ? where id = ?";
            PreparedStatement stmt =
                    connectie.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, nieuwPaswoord);
            stmt.setInt(2, studentID);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(StudentenDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     * @param studentID
     */
    public void verwijderStudent(Integer studentID){
        try {
            String sql = "delete from student where id = ?";
            PreparedStatement stmt =
                    connectie.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, studentID);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(StudentenDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     *
     * @return
     */
    public ObservableList<Student> getProeven(){
        
        try {
            String sql = "select * from student order by id";
            PreparedStatement stmt =
                    connectie.prepareStatement(sql);
            ResultSet results = stmt.executeQuery();
            ObservableList<Student> lijst;
            lijst = FXCollections.observableArrayList();
            while(results.next()){
                String naam = results.getString("naam");
                String paswoord = results.getString("paswoord");
                int id = results.getInt("id");
                Student student = new Student(naam,paswoord);
                student.setId(id);
                lijst.add(student);
            }
            results.close();
            stmt.close();
            return lijst;
        } catch (SQLException ex) {
            Logger.getLogger(BachelorproevenDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
   
    /**
     *
     * @param studentID
     * @return
     */
    public String getWachtwoord(Integer studentID){
        try {
            String sql = "select paswoord from student where id = ?";
            PreparedStatement stmt =
                    connectie.prepareStatement(sql);
            stmt.setInt(1, studentID);
            ResultSet results = stmt.executeQuery();
            while(results.next()){
                String wachtwoord = results.getString("paswoord");
                return wachtwoord;
            }    
            results.close();
            stmt.close();
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(StudentenDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
    }
   
    /**
     *
     * @param studentID
     * @return
     */
    public String getNaam(Integer studentID){
        try {
            String sql = "select naam from student where id = ?";
            PreparedStatement stmt =
                    connectie.prepareStatement(sql);
            stmt.setInt(1, studentID);
            ResultSet results = stmt.executeQuery();
            while(results.next()){
                String naam = results.getString("naam");
                return naam;
            }    
            results.close();
            stmt.close();
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(StudentenDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
    }
    
    /**
     *
     * @param studentID
     * @param bpID
     */
    public void voegKeuzeToe (Integer studentID, Integer bpID){
        String sql = "insert into keuzes (student,bachelorproef,punten)" + "values (?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = connectie.prepareStatement(sql,
                            PreparedStatement.RETURN_GENERATED_KEYS);
            
            stmt.setInt(1, studentID);
            stmt.setInt(2, bpID);
            stmt.setInt(3, 0); // 0 staat voor aanvraag nog niet behandeld
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(BachelorproevenDB.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    /**
     *
     * @param k
     */
    public void puntenToekennen(Keuze k){
         try {
            String sql = "update keuzes set punten = ? where student = ? and bachelorproef = ?";
            PreparedStatement stmt =
                    connectie.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, k.getPunten());
            stmt.setInt(2, k.getStudent());
            stmt.setInt(3, k.getBachelorproef());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(StudentenDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * @param studentID
     * @param bpID 
     */ 
    public void keuzeVerwijderen(Integer studentID, Integer bpID){
        try {
            String sql = "delete from keuzes where student = ? and bachelorproef = ?";
            PreparedStatement stmt =
                    connectie.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, studentID);
            stmt.setInt(2, bpID);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(StudentenDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     *
     * @return
     */
    public ObservableList<Keuze> getKeuzes(){
        
        try {
            String sql = "select * from keuzes";
            PreparedStatement stmt =
                    connectie.prepareStatement(sql);
            ResultSet results = stmt.executeQuery();
            ObservableList<Keuze> lijst;
            lijst = FXCollections.observableArrayList();
            while(results.next()){
                int student = results.getInt("student");
                int bachelorproef = results.getInt("bachelorproef");
                int punten = results.getInt("punten");
                Keuze keuze = new Keuze(student,bachelorproef,punten);
                lijst.add(keuze);
            }
            results.close();
            stmt.close();
            return lijst;
        } catch (SQLException ex) {
            Logger.getLogger(BachelorproevenDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
     * 
     * @return 
     */
    public int getLaagstePunt(){
        try {
            String sql = "select min(punten) as min_punten from keuzes";
            PreparedStatement stmt =
                    connectie.prepareStatement(sql);
            ResultSet results = stmt.executeQuery();
            while(results.next()){
                int punt = results.getInt("min_punten");
                return punt;
            }    
            results.close();
            stmt.close();
            return -1;
        } catch (SQLException ex) {
            Logger.getLogger(StudentenDB.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } 
    }
    /**
     * 
     * @return 
     */
    public int getHoogstePunt(){
        try {
            String sql = "select max(punten) as max_punten from keuzes";
            PreparedStatement stmt =
                    connectie.prepareStatement(sql);
            ResultSet results = stmt.executeQuery();
            while(results.next()){
                int punt = results.getInt("max_punten");
                return punt;
            }    
            results.close();
            stmt.close();
            return -1;
        } catch (SQLException ex) {
            Logger.getLogger(StudentenDB.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } 
    }
    /**
     * 
     * @return 
     */
    public int getGemiddeldePunt(){
        try {
            String sql = "select avg(punten) as avg_punten from keuzes";
            PreparedStatement stmt =
                    connectie.prepareStatement(sql);
            ResultSet results = stmt.executeQuery();
            while(results.next()){
                int punt = results.getInt("avg_punten");
                return punt;
            }    
            results.close();
            stmt.close();
            return -1;
        } catch (SQLException ex) {
            Logger.getLogger(StudentenDB.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } 
    }
    
}
