/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dto.ConsultationDTO;
import java.sql.Date;



/**
 *
 * @author MATHIS
 */
public class Consultation extends Rdv {
    private final String TYPE="CONSULTATION";
    private Patient patient;
    private Medecin medecin;
    private Specialite specialite;
    private Constante constante;
    private Ordonnance ord;
    
    public Consultation() {
        this.type_rdv=TYPE;
    }

    public Consultation(int id_rdv, Date date, String type_rdv, String etat, String statut, Ordonnance ord,Patient patient, Medecin medecin, Specialite specialite, Constante constante) {
        super(id_rdv, date, type_rdv, etat,statut);
        this.patient = patient;
        this.medecin = medecin;
        this.specialite = specialite;
        this.constante=constante;
        this.type_rdv=TYPE;
        this.statut= statut;
        this.ord=ord;
    }

    public Consultation(Date date, String type_rdv, String etat, String statut,Ordonnance ord, Patient patient, Medecin medecin, Specialite specialite,Constante constante) {
        super(date, type_rdv, etat, statut);
        this.patient = patient;
        this.medecin = medecin;
        this.specialite = specialite;
        this.constante=constante;
        this.type_rdv=TYPE;
        this.ord=ord;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    @Override
    public String getType_rdv() {
        return type_rdv;
    }
    
    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }
    
    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public Constante getConstante() {
        return constante;
    }

    public void setConstante(Constante constante) {
        this.constante = constante;
    }

    public Ordonnance getOrd() {
        return ord;
    }

    public void setOrd(Ordonnance ord) {
        this.ord = ord;
    }
    
    public void toConsultation(ConsultationDTO consDto){
        this.id_rdv=consDto.getId_consultation();
        this.date=consDto.getDate();
        this.etat=consDto.getEtat();
        this.patient=consDto.getPat();
        this.specialite=consDto.getSpec();
        this.medecin=consDto.getMed();
        this.constante=consDto.getConstante();
        this.statut=consDto.getStatut();
        this.type_rdv=consDto.getType_rdv();
    }
}
