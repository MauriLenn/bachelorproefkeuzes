/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachelorproefkeuzes;

/**
 *
 * @author Lennert
 */
public class Bachelorproef {
    private int id; //omdat we met een database werken
    private String titel;
    private String beschrijving;

    public Bachelorproef(String titel, String beschrijving) {
        this.titel = titel;
        this.beschrijving = beschrijving;
    }

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }
    
    
}
