/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Consultation;
import entities.Patient;

/**
 *
 * @author MATHIS
 */
public class MedicamentDTO {
    private int id_medDto;
    private String nom;
    private int code;
    private int nombre;
    private String posologie;
    private Patient pat;
    private Consultation cons;

    public MedicamentDTO() {
    }

    public int getId_medDto() {
        return id_medDto;
    }

    public void setId_medDto(int id_medDto) {
        this.id_medDto = id_medDto;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public String getPosologie() {
        return posologie;
    }

    public void setPosologie(String posologie) {
        this.posologie = posologie;
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    
}
