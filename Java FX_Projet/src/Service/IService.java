/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import dto.ConsultationDTO;
import dto.PrestationDTO;
import entities.Constante;
import entities.Consultation;
import entities.Medecin;
import entities.Patient;
import entities.Prestation;
import entities.Rdv;
import entities.User;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author MATHIS
 */
public interface IService {
    //Gerer prestation
    public List<PrestationDTO> searchAllPrestation();
    public List<PrestationDTO> searchPrestationByDate(Date date);
    public int addPrestation(Prestation prestation);
    public List<PrestationDTO> searchPrestationByPatient(int id);
    public Prestation searchPrestationById(int id_prestation);
    public Boolean validerRdvPrestation(Prestation prestation);
    public Boolean annulerPrestation(PrestationDTO prestation);
    
    //Gerer les consultations
    public int addConsultation(Consultation consultation);
    public List<ConsultationDTO> searchConsultationByPatient(int id);
    public List<Consultation> searchConsultationByMedecin(Medecin medecin);
    public Boolean validerRdvConsultation (Consultation consultation);
    public Boolean annulerConsultation(int id);
    
    //Gerer rdv
    public Patient searchPatientByName(String nom, String prenom);
    public List<Rdv> searchRdvByPatient(int id);
    
    //securité
    public User login(String login, String Password);
    public int addUser(User user);
    
    //user
    public int addPatient(Patient patient);
    
    //constante
    public int addConstante(Constante constante);
    public Boolean updateConstante(Constante constante);
    
}