/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Constante;
import entities.Medecin;
import entities.Ordonnance;
import entities.Patient;
import entities.Rdv;
import entities.Specialite;
import entities.TypePrestation;
import java.sql.Date;

/**
 *
 * @author MATHIS
 */
public class RdvDTO {
    private int id_rdv;
    private Date date;
    private String type_rdv;
    private String etat;
    private Patient patient;
    private Medecin med;
    private Constante constante;
    private Ordonnance ord;
    private TypePrestation typePres ;
    private Specialite spec;
    private String statut;
    private String resultat;

    public Ordonnance getOrd() {
        return ord;
    }

    public void setOrd(Ordonnance ord) {
        this.ord = ord;
    }

    public Constante getConstante() {
        return constante;
    }

    public void setConstante(Constante constante) {
        this.constante = constante;
    }
    
    public Medecin getMed() {
        return med;
    }

    public void setMed(Medecin med) {
        this.med = med;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public int getId_rdv() {
        return id_rdv;
    }

    public void setId_rdv(int id_rdv) {
        this.id_rdv = id_rdv;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType_rdv() {
        return type_rdv;
    }

    public void setType_rdv(String type_rdv) {
        this.type_rdv = type_rdv;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public TypePrestation getTypePres() {
        return typePres;
    }

    public void setTypePres(TypePrestation typePres) {
        this.typePres = typePres;
    }

    public Specialite getSpec() {
        return spec;
    }

    public void setSpec(Specialite spec) {
        this.spec = spec;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
    
    public void toDTO(Rdv rdv){
        this.id_rdv=rdv.getId_rdv();
        this.date=rdv.getDate();
        this.etat=rdv.getEtat();
        this.type_rdv=rdv.getType_rdv();
    }
}
