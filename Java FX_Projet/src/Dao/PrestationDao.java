/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Service.Service;
import dto.PrestationDTO;
import entities.Patient;
import entities.Prestation;
import entities.TypePrestation;
import java.sql.Date;
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
public class PrestationDao implements IDao<Prestation> {
    
    private final String SQL_INSERT="INSERT INTO `rdv` "
            + "(`date`, `type_rdv`,`typepres_id`,`etat`,`statut`, `patient_id`) "
            + "VALUES (?, 'PRESTATION',?,'EN ATTENTE','NON FAIT', ?)";
    
    private final String SQL_UPDATE_STATUT_ANNULER ="UPDATE `rdv` SET `etat` = 'REFUSE' "
            + "WHERE rdv.id_rdv = ?";
    
    private final String SQL_SELECT_PRESTATION_PATIENT= "SELECT * FROM rdv r, user p "
            + " WHERE p.id = r.patient_id AND r.etat='VALIDE' AND r.type_rdv='PRESTATION' "
            + " AND p.id LIKE ? ";
    
    private final String SQL_SELECT_ALL_PRESTATION="SELECT * FROM rdv WHERE type_rdv='PRESTATION'"
            + " AND etat= 'VALIDE' ";
    
    private final String SQL_SELECT_PRESTATION_BY_ID="SELECT * FROM rdv "
            + " WHERE type_rdv='PRESTATION' AND etat='VALIDE' AND id_rdv LIKE ? ";
    
    private final String SQL_SELECT_RDV_PRESTATION_BY_ID="SELECT * FROM rdv "
            + " WHERE type_rdv='PRESTATION' AND id_rdv LIKE ? ";
    
    private final String SQL_SELECT_PRESTATION_BY_DATE="SELECT * FROM rdv "
            + " WHERE type_rdv='PRESTATION' AND etat='VALIDE' AND date LIKE ?";
    
    private final String SQL_VALIDER_RDV_PRES="UPDATE `rdv` SET `etat` = 'VALIDE' WHERE `rdv`.`id_rdv` = ?";
    
    private final String SQL_VALIDER_PRESTATION="UPDATE `rdv` SET `statut` = 'FAIT', `resultat` = ?"
            + " WHERE `rdv`.`id_rdv` = ?";
    
    DataBase database = new DataBase();

    @Override
    public int insert(Prestation prestation) {
        int idPrestation = 0;
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_INSERT);
            database.getPs().setDate(1, (Date) prestation.getDate());
            database.getPs().setInt(2, prestation.getTypePres().getId_typePres());
            database.getPs().setInt(3, prestation.getPatient().getId());
            database.executeUpdate(SQL_INSERT);
            ResultSet rs = database.getPs().getGeneratedKeys();
            if(rs.next()){
                idPrestation= rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return idPrestation;
         
    }

 
    public int annulerPrestationDto(PrestationDTO prestationDto) {
        int nbreLigne=0;
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_UPDATE_STATUT_ANNULER);
            database.getPs().setInt(1, prestationDto.getId_rdv());
            nbreLigne= database.executeUpdate(SQL_UPDATE_STATUT_ANNULER);
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return nbreLigne;
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Prestation> findAll(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public List<PrestationDTO> findAllPrestationDto() {
        List<PrestationDTO> prestations = new ArrayList();
        database.openConnexion();
        database.initPrepareStatement(SQL_SELECT_ALL_PRESTATION);
        ResultSet rs= database.executeSelect(SQL_SELECT_ALL_PRESTATION);
        try {
            while(rs.next()){
                PrestationDTO prestation= new PrestationDTO();
                Service service = new Service();
                prestation.setId_rdv(rs.getInt("id_rdv"));
                prestation.setDate(rs.getDate("date"));
                prestation.setType_rdv(rs.getString("type_rdv"));
                prestation.setEtat(rs.getString("etat")); 
                prestation.setStatut(rs.getString("statut"));
                prestation.setResultat(rs.getString("resultat"));
                //type prestation
                TypePrestation typPres=service.findTypePrestationById(rs.getInt("typepres_id"));
                prestation.setTypePres(typPres);
                //patient
                Patient pat=service.searchPatientById(rs.getInt("patient_id"));
                prestation.setPat(pat);
                
                prestations.add(prestation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return prestations;
    }

    @Override
    public Prestation findById(int id) {
        Prestation prestation = null;
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_SELECT_PRESTATION_BY_ID);
            database.getPs().setInt(1, id);
            ResultSet rs=  database.executeSelect(SQL_SELECT_PRESTATION_BY_ID);
            if(rs.next()){
                prestation = new Prestation();
                Service service = new Service();
                prestation.setId_rdv(rs.getInt("id"));
                prestation.setDate(rs.getDate("date"));
                prestation.setType_rdv(rs.getString("type_rdv"));
                prestation.setEtat(rs.getString("etat"));
                prestation.setStatut(rs.getString("statut"));
                prestation.setResultat(rs.getString("resultat"));
                //type prestation
                TypePrestation typPres=service.findTypePrestationById(rs.getInt("typepres_id"));
                prestation.setTypePres(typPres);
                //patient
                Patient pat=service.searchPatientById(rs.getInt("patient_id"));
                prestation.setPatient(pat);
            }  
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return prestation;
    }
    
    public Prestation findRdvPresById(int id) {
        Prestation prestation = null;
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_SELECT_RDV_PRESTATION_BY_ID);
            database.getPs().setInt(1, id);
            ResultSet rs=  database.executeSelect(SQL_SELECT_RDV_PRESTATION_BY_ID);
            if(rs.next()){
                prestation = new Prestation();
                Service service = new Service();
                prestation.setId_rdv(rs.getInt("id_rdv"));
                prestation.setDate(rs.getDate("date"));
                prestation.setType_rdv(rs.getString("type_rdv"));
                prestation.setEtat(rs.getString("etat"));
                prestation.setStatut(rs.getString("statut"));
                //type prestation
                TypePrestation typPres=service.findTypePrestationById(rs.getInt("typepres_id"));
                prestation.setTypePres(typPres);
                //patient
                Patient pat=service.searchPatientById(rs.getInt("patient_id"));
                prestation.setPatient(pat);
            }  
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return prestation;
    }
    public List<PrestationDTO> findPrestationByPatient(int id){
        List<PrestationDTO> prestations= new ArrayList();
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_SELECT_PRESTATION_PATIENT);
            database.getPs().setInt(1, id);
            ResultSet rs= database.executeSelect(SQL_SELECT_PRESTATION_PATIENT);
            while(rs.next()){
                Service service = new Service();
                
                PrestationDTO prestation= new PrestationDTO();
                prestation.setId_rdv(rs.getInt("id"));
                prestation.setDate(rs.getDate("date"));
                prestation.setType_rdv(rs.getString("type_rdv"));
                prestation.setEtat(rs.getString("etat"));
                prestation.setStatut(rs.getString("statut"));
                prestation.setResultat(rs.getString("resultat"));
                //type prestation
                TypePrestation typPres=service.findTypePrestationById(rs.getInt("typepres_id"));
                prestation.setTypePres(typPres);
                //patient
                Patient pat=service.searchPatientById(rs.getInt("patient_id"));
                prestation.setPat(pat);
                
                prestations.add(prestation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return prestations;
    }
    
    public int validatePrestation(PrestationDTO prestation){
        int nbreLigne= 0;
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_VALIDER_PRESTATION);
            database.getPs().setString(1, prestation.getResultat());
            database.getPs().setInt(2, prestation.getId_rdv());
            nbreLigne = database.executeUpdate(SQL_VALIDER_PRESTATION);  
        } catch (SQLException ex){
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return nbreLigne;
    }
    
    public int validateRdvPrestation(Prestation prestation){
        int nbreLigne= 0;
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_VALIDER_RDV_PRES);
            database.getPs().setInt(1, prestation.getId_rdv());
            nbreLigne = database.executeUpdate(SQL_VALIDER_RDV_PRES);  
        } catch (SQLException ex){
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return nbreLigne;
    }
    
    public List<PrestationDTO> findPrestationByDate(Date date){
        List<PrestationDTO> prestations = new ArrayList();
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_SELECT_PRESTATION_BY_DATE);
            database.getPs().setDate(1,date);
            ResultSet rs = database.executeSelect(SQL_SELECT_PRESTATION_BY_DATE);
            while(rs.next()){
                PrestationDTO prestation= new PrestationDTO();
                Service service = new Service();
                prestation.setId_rdv(rs.getInt("id_rdv"));
                prestation.setDate(rs.getDate("date"));
                prestation.setType_rdv(rs.getString("type_rdv"));
                prestation.setEtat(rs.getString("etat"));
                prestation.setStatut(rs.getString("statut"));
                prestation.setResultat(rs.getString("resultat"));
                //type prestation
                TypePrestation typPres=service.findTypePrestationById(rs.getInt("typepres_id"));
                prestation.setTypePres(typPres);
                //patient
                Patient pat=service.searchPatientById(rs.getInt("patient_id"));
                prestation.setPat(pat);
                
                prestations.add(prestation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return prestations;
    }

    @Override
    public int update(Prestation ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
