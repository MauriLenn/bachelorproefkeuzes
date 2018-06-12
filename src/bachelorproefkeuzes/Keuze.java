/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachelorproefkeuzes;

import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Lennert
 */
public class Keuze {
    private SimpleIntegerProperty student;
    private SimpleIntegerProperty bachelorproef;
    private SimpleIntegerProperty punten;

    public Keuze(Integer student, Integer bachelorproef, Integer punten) {
        this.student = new SimpleIntegerProperty(student);
        this.bachelorproef = new SimpleIntegerProperty(bachelorproef);
        this.punten = new SimpleIntegerProperty(punten);
    }
    
    public int getStudent() {
        return student.get();
    }
    
    public SimpleIntegerProperty studentProperty(){
        return student;
    }

    protected void setStudent(int student) {
        this.student = new SimpleIntegerProperty(student);
    }
    
    public int getBachelorproef() {
        return bachelorproef.get();
    }
    
    public SimpleIntegerProperty bachelorproefProperty(){
        return bachelorproef;
    }

    protected void setBachelorproef(int bachelorproef) {
        this.bachelorproef = new SimpleIntegerProperty(bachelorproef);
    }
    
    public int getPunten() {
        return punten.get();
    }
    
    public SimpleIntegerProperty puntenProperty(){
        return punten;
    }

    public void setPunten(int punten) {
        this.punten = new SimpleIntegerProperty(punten);
    }
}
