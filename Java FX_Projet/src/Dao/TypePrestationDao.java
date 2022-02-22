/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import entities.TypePrestation;
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
public class TypePrestationDao implements IDao<TypePrestation> {
    DataBase database= new DataBase();
    
    private final String SQL_ALL="SELECT * FROM typeprestation" ;
    private final String SQL_SELECT_BY_ID="SELECT * FROM typeprestation WHERE id_typePres=?";
    
    @Override
    public int insert(TypePrestation ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(TypePrestation ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TypePrestation> findAll() {
        List<TypePrestation> tpres = new ArrayList();
        database.openConnexion();
        database.initPrepareStatement(SQL_ALL);
        ResultSet rs = database.executeSelect(SQL_ALL);
        try {
            while(rs.next()){
               TypePrestation tpre = new TypePrestation(
               rs.getInt("id_typePres"),
               rs.getString("libelle"));
               tpres.add(tpre);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypePrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return tpres;
    }

    @Override
    public TypePrestation findById(int id) {
        TypePrestation typePres = null;
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_SELECT_BY_ID);
            database.getPs().setInt(1, id);
            ResultSet rs = database.executeSelect(SQL_SELECT_BY_ID);
            if(rs.next()){
                typePres=new TypePrestation(rs.getInt("id_typePres"), rs.getString("libelle"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypePrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return typePres;
    }
    
}
