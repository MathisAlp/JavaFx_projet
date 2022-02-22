/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import entities.Medicament;
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
public class MedicamentDao implements IDao<Medicament> {
    
    private final String SQL_SELECT_ALL_MED="SELECT * FROM medicament";
    private final String SQL_FIND_BY_ID="SELECT * FROM medicament WHERE id_medicament=?";
    
    DataBase database = new DataBase();
    @Override
    public int insert(Medicament ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Medicament ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Medicament> findAll() {
        List<Medicament> medocs = new ArrayList();
        database.openConnexion();
        database.initPrepareStatement(SQL_SELECT_ALL_MED);
        ResultSet rs = database.executeSelect(SQL_SELECT_ALL_MED);
        try {
            while(rs.next()){
                Medicament medoc = new Medicament();
                medoc.setId_medicament(rs.getInt("id_medicament"));
                medoc.setNom(rs.getString("nom"));
                medoc.setCode(rs.getInt("code"));
                medocs.add(medoc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicamentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return medocs;
    }

    @Override
    public Medicament findById(int id) {
        Medicament med = null;
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_FIND_BY_ID);
            database.getPs().setInt(1, id);
            ResultSet rs= database.executeSelect(SQL_FIND_BY_ID);
            if(rs.next()){
                med = new Medicament(rs.getInt("id_medicament"),rs.getInt("code"),rs.getString("nom"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicamentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return med;
    }
    
}
