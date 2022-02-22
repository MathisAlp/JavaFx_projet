/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Service.Service;
import entities.Constante;
import entities.Consultation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MATHIS
 */
public class ConstanteDao implements IDao<Constante> {
    
    public final String SQL_UPDATE="UPDATE `constante` SET `Tention` = ?, `Poids` = ?, `Taille` = ?, `Température` =? "
            + " WHERE `constante`.`id_constante` = ?";
    
    private final String SQL_INSERT="INSERT INTO `constante` ( `Tention`, `Poids`, `Taille`, `Température`,`consultation_id`) "
            + " VALUES (?,?,?,?,?)";
    
    private final String SQL_FIND_BY_ID="SELECT * FROM constante WHERE id_constante=?";
    DataBase database = new DataBase();

    @Override
    public int insert(Constante constante) {
        int id_constante=0;
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_INSERT);
            database.getPs().setString(1, constante.getTention());
            database.getPs().setInt(2,constante.getPoids());
            database.getPs().setInt(3,constante.getTaille());
            database.getPs().setInt(4, constante.getTemperature());
            database.getPs().setInt(5,constante.getConsultation().getId_rdv());
            database.executeUpdate(SQL_INSERT);
            ResultSet rs= database.getPs().getGeneratedKeys() ;
            if(rs.next()){
                id_constante=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConstanteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return id_constante;
    }

    @Override
    public int update(Constante constante) {
        int nbreLigne=0;
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_UPDATE);
            database.getPs().setString(1, constante.getTention());
            database.getPs().setInt(2,constante.getPoids());
            database.getPs().setInt(3,constante.getTaille());
            database.getPs().setInt(4, constante.getTemperature());
            database.getPs().setInt(5,constante.getId_constante());
            nbreLigne= database.executeUpdate(SQL_UPDATE);
        } catch (SQLException ex) {
            Logger.getLogger(ConstanteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return nbreLigne;
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Constante> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Constante findById(int id) {
        Constante constante=null;
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_FIND_BY_ID);
            database.getPs().setInt(1, id);
            ResultSet rs= database.executeSelect(SQL_FIND_BY_ID);
            if(rs.next()){
                Service serv = new Service();
                constante = new Constante();
                constante.setId_constante(rs.getInt("id_constante"));
                constante.setPoids(rs.getInt("Poids"));
                constante.setTaille(rs.getInt("Taille"));
                constante.setTemperature(rs.getInt("Température"));
                constante.setTention(rs.getString("Tention"));
                Consultation cons=serv.findConsultationById(rs.getInt("consultation_id"));
                constante.setConsultation(cons);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConstanteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return constante;
    }
    
}
