/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Service.Service;
import dto.RdvDTO;
import entities.Constante;
import entities.Medecin;
import entities.Ordonnance;
import entities.Patient;
import entities.Rdv;
import entities.Specialite;
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
public class RdvDao implements IDao<Rdv> {
    private final String SQL_UPDATE_STATUT_ANNULER ="UPDATE `rdv` SET `etat` = 'REFUSÉ' "
            + "WHERE rdv.id_rdv = ?";
    
    private final String SQL_SELECT_RDV_MEDECIN= "SELECT * FROM rdv r, user m "
            + " WHERE r.medecin_id=m.id AND r.type='TYPE_CONSULTATION'"
            + " AND r.medecin_id LIKE '?'";
    
    private final String SQL_SELECT_RDV_PATIENT="SELECT * FROM rdv r, user p"
            + " WHERE r.patient_id=p.id AND r.patient_id LIKE ? ";
    
    private final String SQL_SELECT_RDV_PATIENT_VALIDE="SELECT * FROM rdv WHERE etat='VALIDE' AND"
            + " statut='FAIT' AND patient_id=?";
    
    private final String SQL_All="SELECT * FROM rdv WHERE etat='EN ATTENTE' ";
    
    DataBase database = new DataBase();

    @Override
    public int insert(Rdv ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Rdv ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<RdvDTO> findAllDto() {
        List<RdvDTO> rdvs = new ArrayList();
        database.openConnexion();
        database.initPrepareStatement(SQL_All);
        ResultSet rs=database.executeSelect(SQL_All);
        try {
            while(rs.next()){
                Service service = new Service();
                RdvDTO rdv = new RdvDTO();
                rdv.setId_rdv(rs.getInt("id_rdv"));
                rdv.setType_rdv(rs.getString("type_rdv"));
                rdv.setEtat(rs.getString("etat"));
                rdv.setDate(rs.getDate("date"));
                //patient
                Patient pat = service.searchPatientById(rs.getInt("patient_id"));
                rdv.setPatient(pat);
                //Specialite
                Specialite spec = service.findSpecialiteById(rs.getInt("specialite_id"));
                rdv.setSpec(spec);
                //Type Prestation
                TypePrestation typePres =service.findTypePrestationById(rs.getInt("typepres_id"));
                rdv.setTypePres(typePres);
                
                rdvs.add(rdv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RdvDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return rdvs;
    }

    @Override
    public Rdv findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Rdv> findRdvByPatient(int id){
        List<Rdv> rdvs = new ArrayList();
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_SELECT_RDV_PATIENT);
            database.getPs().setInt(1, id);
            ResultSet rs= database.executeSelect(SQL_SELECT_RDV_PATIENT);
            while(rs.next()){
                Rdv rdv = new Rdv();
                rdv.setId_rdv(rs.getInt("id_rdv"));
                rdv.setDate(rs.getDate("date"));
                rdv.setEtat(rs.getString("etat"));
                rdv.setType_rdv(rs.getString("type_rdv"));
                rdv.setStatut(rs.getString("statut"));
                rdvs.add(rdv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RdvDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return rdvs;
    }

    @Override
    public List<Rdv> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<RdvDTO> findRdvValideByPatient(int id){
        List<RdvDTO> rdvs = new ArrayList();
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_SELECT_RDV_PATIENT_VALIDE);
            database.getPs().setInt(1,id);
            ResultSet rs= database.executeSelect(SQL_SELECT_RDV_PATIENT_VALIDE);
            while(rs.next()){
                Service service = new Service();
                RdvDTO rdv = new RdvDTO();
                rdv.setId_rdv(rs.getInt("id_rdv"));
                rdv.setType_rdv(rs.getString("type_rdv"));
                rdv.setEtat(rs.getString("etat"));
                rdv.setDate(rs.getDate("date"));
                rdv.setResultat(rs.getString("resultat"));
                //Ordonnance
                Ordonnance ord=service.searchOrdonnanceById(rs.getInt("ordonnance_id"));
                rdv.setOrd(ord);
                //patient
                Patient pat = service.searchPatientById(rs.getInt("patient_id"));
                rdv.setPatient(pat);
                //Specialite
                Specialite spec = service.findSpecialiteById(rs.getInt("specialite_id"));
                rdv.setSpec(spec);
                //Type Prestation
                TypePrestation typePres =service.findTypePrestationById(rs.getInt("typepres_id"));
                rdv.setTypePres(typePres);
                //Medecin
                Medecin med= service.findMedecinById(rs.getInt("medecin_id"));
                rdv.setMed(med);
                //Constante
                Constante constante=service.searchConstanteById(rs.getInt("constante_id"));
                rdv.setConstante(constante);
                rdvs.add(rdv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RdvDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return rdvs;
    }
    
}
