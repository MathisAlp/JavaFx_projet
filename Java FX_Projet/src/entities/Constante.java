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
public class Constante {
    private int id_constante;
    private String tention;
    private int poids;
    private int taille;
    private int temperature;
    private Consultation consultation;

    public Constante() {
    }

    public Constante(int id_constante, String tention,int taille, int poids, int temperature, Consultation cons) {
        this.id_constante = id_constante;
        this.tention = tention;
        this.poids = poids;
        this.temperature = temperature;
        this.consultation=cons;
        this.taille=taille;
    }

    public Constante(String tention,int taille, int poids, int temperature,Consultation cons) {
        this.tention = tention;
        this.poids = poids;
        this.temperature = temperature;
        this.consultation=cons;
        this.taille=taille;
    }

    public int getId_constante() {
        return id_constante;
    }

    public void setId_constante(int id_constante) {
        this.id_constante = id_constante;
    }

    public String getTention() {
        return tention;
    }

    public void setTention(String tention) {
        this.tention = tention;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }
    
}
