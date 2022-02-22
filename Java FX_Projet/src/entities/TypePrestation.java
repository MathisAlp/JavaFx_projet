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
public class TypePrestation {
    private int id_typePres;
    private String libelle;

    public TypePrestation() {
    }

    public TypePrestation(int id_typePres, String libelle) {
        this.id_typePres = id_typePres;
        this.libelle = libelle;
    }

    public TypePrestation(String libelle) {
        this.libelle = libelle;
    }

    public int getId_typePres() {
        return id_typePres;
    }

    public void setId_typePres(int id_typePres) {
        this.id_typePres = id_typePres;
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
