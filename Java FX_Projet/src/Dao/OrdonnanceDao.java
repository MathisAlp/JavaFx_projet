/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Service.Service;
import entities.Consultation;
import entities.Ordonnance;
import entities.Patient;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MATHIS
 */
public class OrdonnanceDao implements IDao<Ordonnance> {
    DataBase database= new DataBase();
    private final String SQL_INSERT="INSERT INTO `ordonnance` (`consultation_id`, `patient_id`) VALUES (?,?)";
    private final String SQL_FIND_BY_ID="SELECT * FROM ordonnance WHERE id_ordonnance =?";
    
    @Override
    public int insert(Ordonnance ord) {
        int idOrd=0;
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_INSERT);
            database.getPs().setInt(1,ord.getCons().getId_rdv());
            database.getPs().setInt(2,ord.getPat().getId());
            database.executeUpdate(SQL_INSERT);
            ResultSet rs = database.getPs().getGeneratedKeys();
            if(rs.next()){
                idOrd=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdonnanceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return idOrd;
    }

    @Override
    public int update(Ordonnance ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ordonnance> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ordonnance findById(int id) {
        Ordonnance ord = null;
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_FIND_BY_ID);
            database.getPs().setInt(1, id);
            ResultSet rs=database.executeSelect(SQL_FIND_BY_ID);
            if(rs.next()){
                Service serv = new Service();
                ord = new Ordonnance();
                ord.setId_ordonnance(rs.getInt("id_ordonnance"));
                Patient pat=serv.searchPatientById(rs.getInt("patient_id"));
                ord.setPat(pat);
                Consultation cons=serv.findConsultationById(rs.getInt("consultation_id"));
                ord.setCons(cons);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdonnanceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return ord;
    }
    
}
