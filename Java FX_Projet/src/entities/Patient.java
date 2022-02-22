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
public class Patient extends User {
    private int code;
    private String sexe;
    private String antecedant;
    private int tel;

    public Patient() {
        
    }

    public Patient(int id, String nom, String prenom, String login, String password, Role role, int code, String sexe, String antecedant, int tel) {
        super(id, nom, prenom, login, password, role);
        this.code = code;
        this.sexe = sexe;
        this.antecedant = antecedant;
        this.tel = tel;
    }

    public Patient(String nom, String prenom, String login, String password, Role role,int code, String sexe, String antecedant, int tel ) {
        super(nom, prenom, login, password, role);
        this.code = code;
        this.sexe = sexe;
        this.antecedant = antecedant;
        this.tel = tel;
    }
    
    

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getAntecedant() {
        return antecedant;
    }

    public void setAntecedant(String antecedant) {
        this.antecedant = antecedant;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString(){
        return this.nom+" "+this.prenom;
    }
}
