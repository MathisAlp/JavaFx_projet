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
public class Medecin extends User {
    private Specialite spec;

    public Medecin() {
        
    }

    public Medecin(int id, String nom, String prenom, String login, String password, Role role,Specialite spec) {
        super(id, nom, prenom, login, password, role);
        this.spec = spec;
    }

    public Medecin(String nom, String prenom, String login, String password, Role role, Specialite spec) {
        super(nom, prenom, login, password, role);
        this.spec = spec;
    }
    
    public Specialite getSpec() {
        return spec;
    }

    public void setSpec(Specialite spec) {
        this.spec = spec;
    }

    @Override
    public String toString() {
        return this.nom +" "+ this.prenom; //To change body of generated methods, choose Tools | Templates.
    }

    
      
    
}
