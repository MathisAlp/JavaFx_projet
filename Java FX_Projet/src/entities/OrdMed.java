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
public class OrdMed {
    private int id_ordMed;
    private String posologie;
    private int nombre;
    private Ordonnance ord;
    private Medicament med;

    public OrdMed() {
    }
    
    public int getId_ordMed() {
        return id_ordMed;
    }

    public void setId_ordMed(int id_ordMed) {
        this.id_ordMed = id_ordMed;
    }
    
    
    public String getPosologie() {
        return posologie;
    }

    public void setPosologie(String posologie) {
        this.posologie = posologie;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public Ordonnance getOrd() {
        return ord;
    }

    public void setOrd(Ordonnance ord) {
        this.ord = ord;
    }

    public Medicament getMed() {
        return med;
    }

    public void setMed(Medicament med) {
        this.med = med;
    }
    
    
}
