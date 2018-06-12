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

    /**
     *
     * @param student
     * @param bachelorproef
     * @param punten
     */
    public Keuze(Integer student, Integer bachelorproef, Integer punten) {
        this.student = new SimpleIntegerProperty(student);
        this.bachelorproef = new SimpleIntegerProperty(bachelorproef);
        this.punten = new SimpleIntegerProperty(punten);
    }
    
    /**
     *
     * @return
     */
    public int getStudent() {
        return student.get();
    }
    
    /**
     *
     * @return
     */
    public SimpleIntegerProperty studentProperty(){
        return student;
    }

    /**
     *
     * @param student
     */
    protected void setStudent(int student) {
        this.student = new SimpleIntegerProperty(student);
    }
    
    /**
     *
     * @return
     */
    public int getBachelorproef() {
        return bachelorproef.get();
    }
    
    /**
     *
     * @return
     */
    public SimpleIntegerProperty bachelorproefProperty(){
        return bachelorproef;
    }

    /**
     *
     * @param bachelorproef
     */
    protected void setBachelorproef(int bachelorproef) {
        this.bachelorproef = new SimpleIntegerProperty(bachelorproef);
    }
    
    /**
     *
     * @return
     */
    public int getPunten() {
        return punten.get();
    }
    
    /**
     *
     * @return
     */
    public SimpleIntegerProperty puntenProperty(){
        return punten;
    }

    /**
     *
     * @param punten
     */
    public void setPunten(int punten) {
        this.punten = new SimpleIntegerProperty(punten);
    }
}
