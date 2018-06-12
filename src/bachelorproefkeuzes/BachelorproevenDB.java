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
public class BachelorproevenDB {
    //hier komen de query's
    
    private Connection connectie;
    
    public BachelorproevenDB(){
        try {
            connectie = DriverManager.getConnection( "jdbc:mysql://localhost:3306/bpkeuzes",
                    "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(BachelorproevenDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void voegToe(Bachelorproef bp){
        String sql = "insert into bp (titel,beschrijving)" + "values (?,?)"; //op vraagteken moet nog concrete data worden ingevoegd ergens anders
        // en om te vermijden da een tabel wordt verwijderd door iemand
        
        PreparedStatement stmt;
        try {
            stmt = connectie.prepareStatement(sql,
                            PreparedStatement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, bp.getTitel());
            stmt.setString(2,bp.getBeschrijving());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(BachelorproevenDB.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    
    public ObservableList<Bachelorproef> getProeven(){
        
        try {
            String sql = "select * from bp order by titel";
            PreparedStatement stmt =
                    connectie.prepareStatement(sql);
            ResultSet results = stmt.executeQuery();
            ObservableList<Bachelorproef> lijst;
            lijst = FXCollections.observableArrayList();
            while(results.next()){
                String titel = results.getString("titel");
                String beschr = results.getString("beschrijving");
                int id = results.getInt("id");
                Bachelorproef bp = new Bachelorproef(titel,beschr);
                bp.setId(id);
                lijst.add(bp);
            }
            results.close();
            stmt.close();
            return lijst;
        } catch (SQLException ex) {
            Logger.getLogger(BachelorproevenDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ObservableList<Bachelorproef> getBeschikbareProeven(){
        
        try {
            String sql = "select * from bp left join keuzes on keuzes.bachelorproef = bp.id where keuzes.bachelorproef is null";
            PreparedStatement stmt =
                    connectie.prepareStatement(sql);
            ResultSet results = stmt.executeQuery();
            ObservableList<Bachelorproef> lijst;
            lijst = FXCollections.observableArrayList();
            while(results.next()){
                String titel = results.getString("titel");
                String beschr = results.getString("beschrijving");
                int id = results.getInt("id");
                Bachelorproef bp = new Bachelorproef(titel,beschr);
                bp.setId(id);
                lijst.add(bp);
            }
            results.close();
            stmt.close();
            return lijst;
        } catch (SQLException ex) {
            Logger.getLogger(BachelorproevenDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public String getTitelBP(String huidigeTitelBP){
        try {
            String sql = "select titel from bp where titel = ?";
            PreparedStatement stmt =
                    connectie.prepareStatement(sql);
            stmt.setString(1,huidigeTitelBP);
            ResultSet results = stmt.executeQuery();
            while(results.next()){
                String titelBP = results.getString("titel");
                return titelBP;
            }    
            results.close();
            stmt.close();
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(StudentenDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
    }
    
    public String getBeschrijvingBP(int id){
        try {
            String sql = "select beschrijving from bp where id = ?";
            PreparedStatement stmt =
                    connectie.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet results = stmt.executeQuery();
            while(results.next()){
                String beschrijvingBP = results.getString("beschrijving");
                return beschrijvingBP;
            }    
            results.close();
            stmt.close();
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(StudentenDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
    }
    
    public String getTitelBP_id(int id){
        try {
            String sql = "select titel from bp where id = ?";
            PreparedStatement stmt =
                    connectie.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet results = stmt.executeQuery();
            while(results.next()){
                String titelBP = results.getString("titel");
                return titelBP;
            }    
            results.close();
            stmt.close();
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(StudentenDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
    }
    
    public Integer getID(String bpTitel){
        try {
            String sql = "select id from bp where titel = ?";
            PreparedStatement stmt =
                    connectie.prepareStatement(sql);
            stmt.setString(1,bpTitel);
            ResultSet results = stmt.executeQuery();
            while(results.next()){
                Integer bpID = results.getInt("id");
                return bpID;
            }    
            results.close();
            stmt.close();
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(StudentenDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
    }
    
    
    
    public void verwijderBP(String naamBP){
        try {
            String sql = "delete from bp where titel = ?";
            PreparedStatement stmt =
                    connectie.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, naamBP);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(StudentenDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
    
    public String getBPkeuze(Integer studentID){
        try {
            String sql = "select titel from bp where id = (select bachelorproef from keuzes where student = ?)";
            PreparedStatement stmt =
                    connectie.prepareStatement(sql);
            stmt.setInt(1,studentID);
            ResultSet results = stmt.executeQuery();
            while(results.next()){
                String titelBP = results.getString("titel");
                return titelBP;
            }    
            results.close();
            stmt.close();
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(StudentenDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
    }
    
    public String getBPbeschrijvingKeuze(Integer studentID){
        try {
            String sql = "select beschrijving from bp where id = (select bachelorproef from keuzes where student = ?)";
            PreparedStatement stmt =
                    connectie.prepareStatement(sql);
            stmt.setInt(1,studentID);
            ResultSet results = stmt.executeQuery();
            while(results.next()){
                String titelBP = results.getString("beschrijving");
                return titelBP;
            }    
            results.close();
            stmt.close();
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(StudentenDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
    }
    
    
}
