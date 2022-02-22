/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Patient;
import entities.Prestation;
import entities.TypePrestation;
import java.sql.Date;

/**
 *
 * @author MATHIS
 */
public class PrestationDTO {
    private int id_rdv;
    private Date date;
    private String type_rdv;
    private String etat;
    private String statut;
    private String resultat;
    private Patient pat;
    private TypePrestation typePres;

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
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

    public int getId_rdv() {
        return id_rdv;
    }

    public void setId_rdv(int id_rdv) {
        this.id_rdv = id_rdv;
    }
    
    public TypePrestation getTypePres() {
        return typePres;
    }

    public void setTypePres(TypePrestation typePres) {
        this.typePres = typePres;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
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
    
    public PrestationDTO() {
    }
    
    public void toDTO(Prestation prestation){
        this.date=prestation.getDate();
        this.typePres=prestation.getTypePres();
        this.type_rdv=prestation.getType_rdv();
        this.etat=prestation.getEtat();
        this.id_rdv=prestation.getId_rdv();
        this.pat=prestation.getPatient();
        this.statut=prestation.getStatut();
        this.resultat=prestation.getResultat();
    }
}
