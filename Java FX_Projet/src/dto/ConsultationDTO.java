/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Constante;
import entities.Consultation;
import entities.Medecin;
import entities.Patient;
import entities.Specialite;
import java.sql.Date;

/**
 *
 * @author MATHIS
 */
public class ConsultationDTO {
    private int id_consultation;
    private Date date;
    private String etat;
    private String type_rdv;
    private Medecin med;
    private Specialite spec;
    private Patient pat;
    private String statut;
    private Constante constante;

    public Constante getConstante() {
        return constante;
    }

    public void setConstante(Constante constante) {
        this.constante = constante;
    }
    
    public ConsultationDTO() {
    }

    public Medecin getMed() {
        return med;
    }

    public void setMed(Medecin med) {
        this.med = med;
    }

    public Specialite getSpec() {
        return spec;
    }

    public void setSpec(Specialite spec) {
        this.spec = spec;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getId_consultation() {
        return id_consultation;
    }

    public void setId_consultation(int id_consultation) {
        this.id_consultation = id_consultation;
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

    public Patient getPat() {
        return pat;
    }

    public void setPat(Patient pat) {
        this.pat = pat;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
    
    public void toDto(Consultation consultation){
        this.id_consultation= consultation.getId_rdv(); 
        this.date= consultation.getDate();
        this.etat= consultation.getEtat();
        this.med=consultation.getMedecin();
        this.spec=consultation.getSpecialite();
        this.type_rdv=consultation.getType_rdv();
        this.pat=consultation.getPatient();
        this.statut=consultation.getStatut();
    }

    @Override
    public String toString() {
        return "ConsultationDTO{" + "id_consultation=" + id_consultation + ", date=" + date + ", etat=" + etat + ", type_rdv=" + type_rdv + '}';
    }
    
}
