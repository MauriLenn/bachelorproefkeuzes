/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachelorproefkeuzes;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Lennert
 */
public class Bachelorproef {
    private int id; //omdat we met een database werken
    private SimpleStringProperty titel;
    private SimpleStringProperty beschrijving;

    public Bachelorproef(String titel, String beschrijving) {
        this.titel = new SimpleStringProperty(titel);
        this.beschrijving = new SimpleStringProperty(beschrijving);
    }

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public String getTitel() {
        return titel.get();
    }
    
    public SimpleStringProperty titelProperty(){
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = new SimpleStringProperty(titel);
    }

    public String getBeschrijving() {
        return beschrijving.get();
    }
    
    public SimpleStringProperty beschrijvingProperty(){
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = new SimpleStringProperty(beschrijving);
    }

    void setTitel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
