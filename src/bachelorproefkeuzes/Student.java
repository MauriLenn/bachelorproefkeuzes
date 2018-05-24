/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachelorproefkeuzes;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Lennert
 */
public class Student {
    private SimpleIntegerProperty id; //omdat we met een database werken
    private SimpleStringProperty naam;
    private SimpleStringProperty paswoord;

    public Student(String naam, String paswoord) {
        this.naam = new SimpleStringProperty(naam);
        this.paswoord= new SimpleStringProperty(paswoord);
    }

    public int getId() {
        return id.get();
    }
    
    public SimpleIntegerProperty idProperty(){
        return id;
    }

    protected void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public String getNaam() {
        return naam.get();
    }
    
    public SimpleStringProperty naamProperty(){
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = new SimpleStringProperty(naam);
    }

    public String getPaswoord() {
        return paswoord.get();
    }
    
    public SimpleStringProperty paswoordProperty(){
        return paswoord;
    }

    public void setPaswoord(String paswoord) {
        this.paswoord = new SimpleStringProperty(paswoord);
    } 
}

