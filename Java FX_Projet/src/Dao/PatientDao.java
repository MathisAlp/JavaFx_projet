/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import entities.Patient;
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
public class PatientDao implements IDao<Patient>  {
    
    private final String SQL_INSERT="INSERT INTO `user`"
            + " (`nom`, `prenom`, `login`, `password`, `code`, `sexe`, `antecedant`, `role_id`,`tel`)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, '2',?)";
    
    private final String SQL_SELECT_ALL_PATIENT="SELECT * FROM user WHERE role_id=2 " ;
    
    private  final String SQL_SELECT_PATIENT_BY_ID="SELECT * FROM user "
            + " WHERE role_id = 2 AND user.id LIKE ?";
    
    DataBase database= new DataBase();

    @Override
    public int insert(Patient patient) {
        int idPatient=0;
        try {
            database.openConnexion();
            database.initPrepareStatement(SQL_INSERT);
            database.getPs().setString(1, patient.getNom());
            database.getPs().setString(2, patient.getPrenom());
            database.getPs().setString(3, patient.getLogin());
            database.getPs().setString(4, patient.getPassword());
            database.getPs().setInt(5, patient.getCode());
            database.getPs().setString(6, patient.getSexe());
            database.getPs().setString(7, patient.getAntecedant());
            database.getPs().setInt(8, patient.getTel());
            database.executeUpdate(SQL_INSERT);
            ResultSet rs= database.getPs().getGeneratedKeys();
            if(rs.next()){
                idPatient= rs.getInt(1);
            }   
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnexion();
        }
        return idPatient;
    }

    @Override
    public int update(Patient ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> patients = new ArrayList();
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_SELECT_ALL_PATIENT);
            ResultSet rs = database.executeSelect(SQL_SELECT_ALL_PATIENT);
            while(rs.next()){
                Patient patient = new Patient();
                patient.setCode(rs.getInt("code"));
                patient.setAntecedant(rs.getString("antecedant"));
                patient.setId(rs.getInt("id"));
                patient.setNom(rs.getString("nom"));
                patient.setPrenom(rs.getString("prenom"));
                patient.setSexe(rs.getString("sexe"));
                patient.setTel(rs.getInt("tel"));
                patients.add(patient);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return patients;
    }

    @Override
    public Patient findById(int id) {
        Patient patient = null;
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_SELECT_PATIENT_BY_ID);
            database.getPs().setInt(1, id);
            ResultSet rs=database.executeSelect(SQL_SELECT_PATIENT_BY_ID);
            if(rs.next()){
                patient = new Patient();
                patient.setCode(rs.getInt("code"));
                patient.setAntecedant(rs.getString("antecedant"));
                patient.setId(rs.getInt("id"));
                patient.setNom(rs.getString("nom"));
                patient.setPrenom(rs.getString("prenom"));
                patient.setSexe(rs.getString("sexe"));
                patient.setTel(rs.getInt("tel"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return patient;
    }
    
}
