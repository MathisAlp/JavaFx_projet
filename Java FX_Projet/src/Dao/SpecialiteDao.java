/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import entities.Specialite;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MATHIS
 */
public class SpecialiteDao implements IDao<Specialite> {
    DataBase database = new DataBase();
    private final String SQL_ALL="SELECT * FROM specialite";
    
    private final String SQL_SELECT_BY_ID="SELECT * FROM specialite WHERE id_specialite = ?";
    
    @Override
    public int insert(Specialite ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Specialite ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Specialite> findAll() {
        List<Specialite> specs = new ArrayList();
        database.openConnexion();
        database.initPrepareStatement(SQL_ALL);
        ResultSet rs =database.executeSelect(SQL_ALL);
        try {
            while(rs.next()){
                Specialite spec = new Specialite(
                rs.getInt("id_specialite"),
                rs.getString("libelle"));
                specs.add(spec);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SpecialiteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return specs;
    }

    @Override
    public Specialite findById(int id) {
        Specialite spec =null;
        database.openConnexion(); 
        try {
            database.initPrepareStatement(SQL_SELECT_BY_ID);
            database.getPs().setInt(1, id);
            ResultSet rs = database.executeSelect(SQL_SELECT_BY_ID);
            if(rs.next()){
                spec = new Specialite(
                rs.getInt("id_specialite"),
                rs.getString("libelle"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SpecialiteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return spec;
    }
    
}
