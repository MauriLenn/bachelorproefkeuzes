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
public class Bachelorproef {
    private SimpleIntegerProperty id; //omdat we met een database werken
    private SimpleStringProperty titel;
    private SimpleStringProperty beschrijving;

    /**
     *
     * @param titel
     * @param beschrijving
     */
    public Bachelorproef(String titel, String beschrijving) {
        this.titel = new SimpleStringProperty(titel);
        this.beschrijving = new SimpleStringProperty(beschrijving);
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
    public String getTitel() {
        return titel.get();
    }
    
    /**
     *
     * @return
     */
    public SimpleStringProperty titelProperty(){
        return titel;
    }

    /**
     *
     * @param titel
     */
    public void setTitel(String titel) {
        this.titel = new SimpleStringProperty(titel);
    }

    /**
     *
     * @return
     */
    public String getBeschrijving() {
        return beschrijving.get();
    }
    
    /**
     *
     * @return
     */
    public SimpleStringProperty beschrijvingProperty(){
        return beschrijving;
    }

    /**
     *
     * @param beschrijving
     */
    public void setBeschrijving(String beschrijving) {
        this.beschrijving = new SimpleStringProperty(beschrijving);
    } 
}
