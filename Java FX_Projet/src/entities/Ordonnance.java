/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author MATHIS
 */
public class Ordonnance {
    private int id_ordonnance;
    private Patient pat;
    private Consultation cons;
    

    public Ordonnance() {
    }

    public Ordonnance(int id_ordonnance, Patient pat,Consultation cons) {
        this.id_ordonnance = id_ordonnance;
        this.pat=pat;
        this.cons=cons;
    }

    public Ordonnance(Patient pat,Consultation cons) {
        this.pat=pat;
        this.cons=cons;
    }

    public Patient getPat() {
        return pat;
    }

    public void setPat(Patient pat) {
        this.pat = pat;
    }

    public Consultation getCons() {
        return cons;
    }

    public void setCons(Consultation cons) {
        this.cons = cons;
    }

    public int getId_ordonnance() {
        return id_ordonnance;
    }

    public void setId_ordonnance(int id_ordonnance) {
        this.id_ordonnance = id_ordonnance;
    }
    
    
}
