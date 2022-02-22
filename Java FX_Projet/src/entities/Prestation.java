/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;



/**
 *
 * @author MATHIS
 */
public class Prestation extends Rdv {
    private final String TYPE="TYPE_PRESTATION";
    private String resultat;
    private TypePrestation typePres; 
    private Patient patient;
    
    public Prestation() {
        this.type_rdv=TYPE;
    }

    public Prestation(int id_rdv, Date date, String type_rdv, String etat, String statut,String resultat, TypePrestation typePres, Patient patient) {
        super(id_rdv, date, type_rdv, etat, statut);
        this.typePres = typePres;
        this.patient = patient;
        this.type_rdv=TYPE;
        this.statut=statut;
        
    }

    public Prestation(Date date, String type_rdv, String etat, String statut,String resultat, TypePrestation typePres, Patient patient) {
        super(date, type_rdv, etat,statut);
        this.typePres = typePres;
        this.patient = patient;
        this.resultat=resultat;
        this.type_rdv=TYPE;
        this.statut=statut;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }
    

    public String getTYPE() {
        return TYPE;
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

    @Override
    public String getType_rdv() {
        return type_rdv;
    }

    @Override
    public void setType_rdv(String type_rdv) {
        this.type_rdv = type_rdv;
    }
    
    
    
}
