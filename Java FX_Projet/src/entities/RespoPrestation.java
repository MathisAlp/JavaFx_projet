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
public class RespoPrestation extends User {

    public RespoPrestation() {
    }

    public RespoPrestation(int id, String nom, String prenom, String login, String password, Role role) {
        super(id, nom, prenom, login, password, role);
    }

    public RespoPrestation(String nom, String prenom, String login, String password, Role role) {
        super(nom, prenom, login, password, role);
    }
    
    
}
