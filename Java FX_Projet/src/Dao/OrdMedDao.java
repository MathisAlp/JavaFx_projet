/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Service.Service;
import entities.Medicament;
import entities.OrdMed;
import entities.Ordonnance;
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
public class OrdMedDao implements IDao<OrdMed>{
    DataBase database = new DataBase();
    
    private final String SQL_INSERT ="INSERT INTO `ordmed` (`posologie`, `nombre`, `ordonnance_id`, `medicament_id`) "
                                        + " VALUES (?, ?, ?, ?)";
    
    private final String SQL_SELECT_MED_BY_ORD = "SELECT * FROM ordMed WHERE ordonnance_id=?";

    @Override
    public int insert(OrdMed ordMed) {
        int id=0;
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_INSERT);
            database.getPs().setString(1, ordMed.getPosologie());
            database.getPs().setInt(2, ordMed.getNombre());
            database.getPs().setInt(3, ordMed.getOrd().getId_ordonnance());
            database.getPs().setInt(4, ordMed.getMed().getId_medicament());
            database.executeUpdate(SQL_INSERT);
            ResultSet rs= database.getPs().getGeneratedKeys();
            if(rs.next()){
                id=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdMedDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return id;
    }

    @Override
    public int update(OrdMed ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrdMed> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrdMed findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<OrdMed> findMedicamentByOrdonnance(int id){
        List<OrdMed> ordMeds = new ArrayList();
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_SELECT_MED_BY_ORD);
            database.getPs().setInt(1,id);
            ResultSet rs=database.executeSelect(SQL_SELECT_MED_BY_ORD);
            while(rs.next()){
                Service serv = new Service();
                OrdMed ordMed= new OrdMed();
                ordMed.setId_ordMed(rs.getInt("id_ordMed"));
                ordMed.setNombre(rs.getInt("nombre"));
                ordMed.setPosologie(rs.getString("posologie"));
                Medicament med = serv.searchMedicamentById(rs.getInt("medicament_id"));
                ordMed.setMed(med);
                Ordonnance ord = serv.searchOrdonnanceById(rs.getInt("ordonnance_id"));
                ordMed.setOrd(ord);
                ordMeds.add(ordMed);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdMedDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return ordMeds;
    }
}
