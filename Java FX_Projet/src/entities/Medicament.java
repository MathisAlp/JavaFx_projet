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
public class Medicament {
    private int id_medicament;
    private int code;
    private String nom;
    
    public Medicament(){
        
    }

    public Medicament(int id_medicament, int code, String nom) {
        this.id_medicament = id_medicament;
        this.code = code;
        this.nom = nom;
    }

    public Medicament(int code, String nom) {
        this.code = code;
        this.nom = nom;
    }
    
    public int getId_medicament() {
        return id_medicament;
    }

    public void setId_medicament(int id_medicament) {
        this.id_medicament = id_medicament;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.nom;
    }
    
    
    
}
