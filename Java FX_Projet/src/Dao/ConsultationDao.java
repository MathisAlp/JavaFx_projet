/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Service.Service;
import dto.ConsultationDTO;
import entities.Constante;
import entities.Consultation;
import entities.Medecin;
import entities.Ordonnance;
import entities.Patient;
import entities.Specialite;
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
public class ConsultationDao implements IDao<Consultation> {
    private final String SQL_INSERT= "INSERT INTO `rdv` "
            + "(`date`, `type_rdv`, `etat`,`statut`, `patient_id`,`specialite_id`) "
            + "VALUES (?, 'CONSULTATION','EN ATTENTE','NON FAIT', ?, ?)";
    
    private final String SQL_FIND_BY_ID="SELECT * FROM rdv WHERE type_rdv='CONSULTATION' AND id_rdv=?";
    
    private final String SQL_SELECT_CONSULTATION_MEDECIN= "SELECT * FROM rdv r, user m "
            + " WHERE m.id= r.medecin_id AND r.type_rdv= 'CONSULTATION' "
            + " AND r.etat='VALIDE'AND r.statut='FAIT' AND m.id LIKE ?";
    
    private final String SQL_SELECT_RDV_CONSULTATION_MEDECIN= "SELECT * FROM rdv r, user m "
            + " WHERE m.id= r.medecin_id AND r.type_rdv= 'CONSULTATION' "
            + " AND r.etat='VALIDE' AND r.statut='NON FAIT' AND m.id LIKE ?";
    
    private final String SQL_SELECT_CONSULTATION_PATIENT= "SELECT * FROM rdv r, user p "
            + " WHERE p.id = r.patient_id AND r.type_rdv='CONSULTATION' "
            + " AND r.etat='VALIDE' AND p.id =? ";
    
    private final String SQL_SELECT_RDV_CONSULTATION_BY_ID="SELECT * FROM rdv "
            + " WHERE type_rdv='CONSULTATION' AND id_rdv LIKE ? ";
    
    private final String SQL_VALIDER_RDV_CONS ="UPDATE `rdv` SET `etat` = 'VALIDE', `medecin_id` = ? WHERE `rdv`.`id_rdv` = ?";
    
    private final String SQL_VALIDER_CONS ="UPDATE `rdv` SET `etat` = 'VALIDE', `statut` ='FAIT',`ordonnance_id` =?, "
            + " constante_id=? WHERE `rdv`.`id_rdv` = ?";
    
    private final String SQL_UPDATE_STATUT_ANNULER ="UPDATE `rdv` SET `etat` = 'REFUSE' "
            + " WHERE rdv.id_rdv = ?";
    
    private final String SQL_SELECT_RDV_CONSULTATION_BY_MEDECIN_AND_DATE="SELECT * FROM rdv r, user m "
            + " WHERE r.medecin_id = m.id AND r.type_rdv= 'CONSULTATION' AND r.etat='VALIDE' AND r.statut='NON FAIT'"
            + " AND date=? AND r.medecin_id LIKE ?";
    
    DataBase database = new DataBase();

    @Override
    public int insert(Consultation consultation) {
        int idConsultation = 0;
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_INSERT);
            database.getPs().setDate(1, consultation.getDate());
            database.getPs().setInt(2, consultation.getPatient().getId());
            database.getPs().setInt(3, consultation.getSpecialite().getId_specialite());
            database.executeUpdate(SQL_INSERT);
            ResultSet rs = database.getPs().getGeneratedKeys();
            if(rs.next()){
                idConsultation= rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return idConsultation;
         
    }

    @Override
    public int update(Consultation consultation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consultation> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    public Consultation findRdvConsById(int id_rdv) {
       Consultation consultation = null;
       database.openConnexion();
        try {
            database.initPrepareStatement(SQL_SELECT_RDV_CONSULTATION_BY_ID);
            database.getPs().setInt(1, id_rdv);
            ResultSet rs=database.executeSelect(SQL_SELECT_RDV_CONSULTATION_BY_ID);
            if(rs.next()){
                consultation = new Consultation ();
                Service service = new Service();
                consultation.setId_rdv(rs.getInt("id_rdv"));
                consultation.setDate(rs.getDate("date"));
                consultation.setType_rdv(rs.getString("type_rdv"));
                consultation.setEtat(rs.getString("etat"));
                //patient
                Patient pat = service.searchPatientById(rs.getInt("patient_id"));
                consultation.setPatient(pat);
                //spec
                Specialite spec = service.findSpecialiteById(rs.getInt("specialite_id"));
                consultation.setSpecialite(spec);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       database.closeConnexion();
       return consultation;
    }
    
    public List<ConsultationDTO> findConsultationByPatient(int id){
        List<ConsultationDTO> consultations = new ArrayList();
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_SELECT_CONSULTATION_PATIENT);
            database.getPs().setInt(1,id);
            ResultSet rs= database.executeSelect(SQL_SELECT_CONSULTATION_PATIENT);
            while(rs.next()){
                Service service = new Service();
                ConsultationDTO consultation = new ConsultationDTO ();
                consultation.setId_consultation(rs.getInt("id_rdv"));
                consultation.setDate(rs.getDate("date"));
                consultation.setType_rdv(rs.getString("type_rdv"));
                consultation.setEtat(rs.getString("etat"));
                consultation.setStatut(rs.getString("statut"));
                //spec
                Specialite spec = service.findSpecialiteById(rs.getInt("specialite_id"));
                consultation.setSpec(spec);
                //medecin
                Medecin med = service.findMedecinById(rs.getInt("medecin_id"));
                consultation.setMed(med);
                //patient
                Patient pat = service.searchPatientById(rs.getInt("patient_id"));
                consultation.setPat(pat);
                consultations.add(consultation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return consultations;
    }
    
    public List<Consultation> findConsultationByMedecin(Medecin medecin){
        List<Consultation> consultations = new ArrayList();
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_SELECT_CONSULTATION_MEDECIN);
            database.getPs().setInt(1, medecin.getId());
            ResultSet rs= database.executeSelect(SQL_SELECT_CONSULTATION_MEDECIN);
            while(rs.next()){
                Service service = new Service();
                Consultation consultation = new Consultation ();
                consultation.setId_rdv(rs.getInt("id_rdv"));
                consultation.setDate(rs.getDate("date"));
                consultation.setType_rdv(rs.getString("type_rdv"));
                consultation.setEtat(rs.getString("etat"));
                consultation.setStatut(rs.getString("statut"));
                //constante
                Constante cons = service.searchConstanteById(rs.getInt("constante_id"));
                consultation.setConstante(cons);
                //Ordonnance
                Ordonnance ord = service.searchOrdonnanceById(rs.getInt("ordonnance_id"));
                consultation.setOrd(ord);
                //spec
                Specialite spec = service.findSpecialiteById(rs.getInt("specialite_id"));
                consultation.setSpecialite(spec);
                //medecin
                Medecin med = service.findMedecinById(rs.getInt("medecin_id"));
                consultation.setMedecin(med);
                //patient
                Patient pat = service.searchPatientById(rs.getInt("patient_id"));
                consultation.setPatient(pat);
                consultations.add(consultation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return consultations;
    }
    
    public List<ConsultationDTO> findRdvConsultationByMedecin(int id_medecin){
        List<ConsultationDTO> consultations = new ArrayList();
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_SELECT_RDV_CONSULTATION_MEDECIN);
            database.getPs().setInt(1, id_medecin);
            ResultSet rs= database.executeSelect(SQL_SELECT_RDV_CONSULTATION_MEDECIN);
            while(rs.next()){
                Service service = new Service();
                ConsultationDTO consultationDto = new ConsultationDTO ();
                consultationDto.setId_consultation(rs.getInt("id_rdv"));
                consultationDto.setDate(rs.getDate("date"));
                consultationDto.setType_rdv(rs.getString("type_rdv"));
                consultationDto.setEtat(rs.getString("etat"));
                consultationDto.setStatut(rs.getString("statut"));
                //spec
                Specialite spec = service.findSpecialiteById(rs.getInt("specialite_id"));
                consultationDto.setSpec(spec);
                //medecin
                Medecin med = service.findMedecinById(rs.getInt("medecin_id"));
                consultationDto.setMed(med);
                //patient
                Patient pat = service.searchPatientById(rs.getInt("patient_id"));
                consultationDto.setPat(pat);
                consultations.add(consultationDto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return consultations;
    }
    
    public int validateRdvConsultation(Consultation consultation){
        int nbreLigne= 0;
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_VALIDER_RDV_CONS);
            database.getPs().setInt(1, consultation.getMedecin().getId());
            database.getPs().setInt(2, consultation.getId_rdv());
            nbreLigne = database.executeUpdate(SQL_VALIDER_RDV_CONS);  
        } catch (SQLException ex){
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return nbreLigne;
    }
    public int validateConsultation(Consultation consultation){
        int nbreLigne= 0;
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_VALIDER_CONS);
            database.getPs().setInt(1, consultation.getOrd().getId_ordonnance());
            database.getPs().setInt(2, consultation.getConstante().getId_constante());
            database.getPs().setInt(3, consultation.getId_rdv());
            nbreLigne = database.executeUpdate(SQL_VALIDER_CONS);  
        } catch (SQLException ex){
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return nbreLigne;
    }
    
    public List<ConsultationDTO> findRdvConsultationByMedecinAndDate (Date date, int id){
        List<ConsultationDTO> consultations = new ArrayList();
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_SELECT_RDV_CONSULTATION_BY_MEDECIN_AND_DATE);
            database.getPs().setDate(1, date);
            database.getPs().setInt(2,id); 
            ResultSet rs=database.executeSelect(SQL_SELECT_RDV_CONSULTATION_BY_MEDECIN_AND_DATE);
            if(rs.next()){
                Service service = new Service();
                ConsultationDTO consultation = new ConsultationDTO ();
                consultation.setId_consultation(rs.getInt("id_rdv"));
                consultation.setDate(rs.getDate("date"));
                consultation.setType_rdv(rs.getString("type_rdv"));
                consultation.setEtat(rs.getString("etat"));
                consultation.setStatut(rs.getString("statut"));
                //spec
                Specialite spec = service.findSpecialiteById(rs.getInt("specialite_id"));
                consultation.setSpec(spec);
                //medecin
                Medecin med = service.findMedecinById(rs.getInt("medecin_id"));
                consultation.setMed(med);
                //patient
                Patient pat = service.searchPatientById(rs.getInt("patient_id"));
                consultation.setPat(pat);
                consultations.add(consultation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return consultations;
    }
    
    
    public int annulerConsultation(int id){
        int nbreLigne= 0;
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_UPDATE_STATUT_ANNULER);
            database.getPs().setInt(1, id);
            nbreLigne = database.executeUpdate(SQL_UPDATE_STATUT_ANNULER);  
        } catch (SQLException ex){
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return nbreLigne;
    }

    @Override
    public Consultation findById(int id) {
        Consultation consultation = null;
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_FIND_BY_ID);
            database.getPs().setInt(1, id);
            ResultSet rs=database.executeSelect(SQL_FIND_BY_ID);
            if(rs.next()){
                Service service = new Service();
                consultation = new Consultation ();
                consultation.setId_rdv(rs.getInt("id_rdv"));
                consultation.setDate(rs.getDate("date"));
                consultation.setType_rdv(rs.getString("type_rdv"));
                consultation.setEtat(rs.getString("etat"));
                consultation.setStatut(rs.getString("statut"));
                //spec
                Specialite spec = service.findSpecialiteById(rs.getInt("specialite_id"));
                consultation.setSpecialite(spec);
                //medecin
                Medecin med = service.findMedecinById(rs.getInt("medecin_id"));
                consultation.setMedecin(med);
                //patient
                Patient pat = service.searchPatientById(rs.getInt("patient_id"));
                consultation.setPatient(pat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return consultation;
    }
    
}
