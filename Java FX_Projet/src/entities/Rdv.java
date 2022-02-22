/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dto.RdvDTO;
import java.sql.Date;



/**
 *
 * @author MATHIS
 */
public class Rdv {
    protected int id_rdv;
    protected Date date;
    protected String type_rdv;
    protected String etat;
    protected String statut;

    public Rdv() {
    }

    public Rdv(int id_rdv, Date date, String type_rdv, String etat, String statut) {
        this.id_rdv = id_rdv;
        this.date = date;
        this.type_rdv = type_rdv;
        this.etat = etat;
        this.statut=statut;
    }

    public Rdv(Date date, String type_rdv, String etat, String statut) {
        this.date = date;
        this.type_rdv = type_rdv;
        this.etat = etat;
        this.statut=statut;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
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

    public int getId_rdv() {
        return id_rdv;
    }

    public void setId_rdv(int id_rdv) {
        this.id_rdv = id_rdv;
    }

    @Override
    public String toString() {
        return "Rdv{" + "date=" + date + ", type_rdv=" + type_rdv + ", etat=" + etat + '}';
    }
    
    public void toRdv(RdvDTO rdvDto){
        this.id_rdv = rdvDto.getId_rdv();
        this.date=rdvDto.getDate();
        this.etat=rdvDto.getEtat();
        this.statut=rdvDto.getStatut();
        this.type_rdv=rdvDto.getType_rdv();
    }
    
}
