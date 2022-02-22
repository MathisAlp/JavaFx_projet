/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import entities.Medecin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MATHIS
 */
public class MedecinDao implements IDao<Medecin> {
    private DataBase database = new DataBase();
    private final String SQL_SELECT_BY_ID="SELECT * FROM user WHERE role_id = 5 AND id=?";
    
    @Override
    public int insert(Medecin ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Medecin ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Medecin> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Medecin findById(int id) {
        Medecin med = null;
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_SELECT_BY_ID);
            database.getPs().setInt(1,id);
            ResultSet rs=database.executeSelect(SQL_SELECT_BY_ID);
            if(rs.next()){
                med = new Medecin();
                med.setId(rs.getInt("id"));
                med.setNom(rs.getString("nom"));
                med.setPrenom(rs.getString("prenom"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return med;
    }
    
}
