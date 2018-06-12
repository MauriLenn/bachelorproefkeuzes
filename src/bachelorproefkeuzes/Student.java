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

    /**
     *
     * @param naam
     * @param paswoord
     */
    public Student(String naam, String paswoord) {
        this.naam = new SimpleStringProperty(naam);
        this.paswoord= new SimpleStringProperty(paswoord);
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id.get();
    }
    
    /**
     *
     * @return
     */
    public SimpleIntegerProperty idProperty(){
        return id;
    }

    /**
     *
     * @param id
     */
    protected void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    /**
     *
     * @return
     */
    public String getNaam() {
        return naam.get();
    }
    
    /**
     *
     * @return
     */
    public SimpleStringProperty naamProperty(){
        return naam;
    }

    /**
     *
     * @param naam
     */
    public void setNaam(String naam) {
        this.naam = new SimpleStringProperty(naam);
    }

    /**
     *
     * @return
     */
    public String getPaswoord() {
        return paswoord.get();
    }
    
    /**
     *
     * @return
     */
    public SimpleStringProperty paswoordProperty(){
        return paswoord;
    }

    /**
     *
     * @param paswoord
     */
    public void setPaswoord(String paswoord) {
        this.paswoord = new SimpleStringProperty(paswoord);
    } 
}

