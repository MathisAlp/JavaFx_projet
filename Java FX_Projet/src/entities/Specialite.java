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
public class Specialite {
    private int id_specialite;
    private String libelle;

    public Specialite() {
    }

    public Specialite(int id_specialite, String libelle) {
        this.id_specialite = id_specialite;
        this.libelle = libelle;
    }

    public Specialite(String libelle) {
        this.libelle = libelle;
    }

    public int getId_specialite() {
        return id_specialite;
    }

    public void setId_specialite(int id_specialite) {
        this.id_specialite = id_specialite;
    }
    
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    @Override
    public String toString(){
        return libelle;
    }
    
}
