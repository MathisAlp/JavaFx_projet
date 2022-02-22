/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Dao.ConstanteDao;
import Dao.ConsultationDao;
import Dao.MedecinDao;
import Dao.MedicamentDao;
import Dao.OrdMedDao;
import Dao.OrdonnanceDao;
import Dao.PatientDao;
import Dao.PrestationDao;
import Dao.RdvDao;
import Dao.RoleDao;
import Dao.SpecialiteDao;
import Dao.TypePrestationDao;
import Dao.UserDao;
import dto.ConsultationDTO;
import dto.PrestationDTO;
import dto.RdvDTO;
import entities.Constante;
import entities.Consultation;
import entities.Medecin;
import entities.Medicament;
import entities.OrdMed;
import entities.Ordonnance;
import entities.Patient;
import entities.Prestation;
import entities.Rdv;
import entities.Role;
import entities.Specialite;
import entities.TypePrestation;
import entities.User;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author MATHIS
 */
public class Service implements IService {
    
    UserDao daoUser=new UserDao();
    PatientDao daoPatient = new PatientDao();
    PrestationDao daoPrestation = new PrestationDao();
    ConsultationDao daoConsultation= new ConsultationDao();
    RdvDao daoRdv= new RdvDao();
    ConstanteDao daoConstante= new ConstanteDao();
    TypePrestationDao daoTpres = new TypePrestationDao();
    SpecialiteDao daoSpec = new SpecialiteDao();
    MedecinDao daoMedecin = new MedecinDao();
    RoleDao daoRole = new RoleDao();
    MedicamentDao daoMed= new MedicamentDao();
    OrdonnanceDao daoOrd = new OrdonnanceDao();
    OrdMedDao daoOrdMed = new OrdMedDao();
    
    //gerer prestation
    @Override
    public List<PrestationDTO> searchAllPrestation() {
       return daoPrestation.findAllPrestationDto();
    }

    @Override
    public List<PrestationDTO> searchPrestationByDate(Date date) {
        return daoPrestation.findPrestationByDate(date);
    }

    @Override
    public int addPrestation(Prestation prestation) {
       return daoPrestation.insert(prestation);
    }

    @Override
    public Prestation searchPrestationById(int id_prestation) {
        return daoPrestation.findById(id_prestation);
    }

    @Override
    public Boolean annulerPrestation(PrestationDTO prestation) {
        return daoPrestation.annulerPrestationDto(prestation) !=0;
    }
    
    @Override
    public List<PrestationDTO> searchPrestationByPatient(int id) {
       return daoPrestation.findPrestationByPatient(id);
    }
    
    @Override
    public Boolean validerRdvPrestation(Prestation prestation) {
        return daoPrestation.validateRdvPrestation(prestation) !=0;
    }
    
    public Boolean validerPrestation(PrestationDTO prestation) {
        return daoPrestation.validatePrestation(prestation) !=0;
    }
    
    public Prestation findRdvPrestationById(int id){
        return daoPrestation.findRdvPresById(id);
    }
    
    //Gerer consultation
    public List<ConsultationDTO> searchRdvConsultationByMedecinAndDate(Date date, int id) {
        return daoConsultation.findRdvConsultationByMedecinAndDate(date, id);
    }

    @Override
    public int addConsultation(Consultation consultation) {
        return daoConsultation.insert(consultation);
    }
    
    @Override
    public List<ConsultationDTO> searchConsultationByPatient(int id){
        return daoConsultation.findConsultationByPatient(id);
    }
    
    public List<ConsultationDTO> searchRdvConsultationByMedecin(int id){
        return daoConsultation.findRdvConsultationByMedecin(id);
    }
    
    @Override
    public List<Consultation> searchConsultationByMedecin(Medecin medecin) {
        return daoConsultation.findConsultationByMedecin(medecin);
    }
    
    @Override
    public Boolean validerRdvConsultation(Consultation consultation) {
        return daoConsultation.validateRdvConsultation(consultation)!=0;
    }
    
    public Boolean validerConsultation(Consultation consultation) {
        return daoConsultation.validateConsultation(consultation)!=0;
    }
    
    @Override
    public Boolean annulerConsultation(int id) {
        return daoConsultation.annulerConsultation(id)!=0;
    }
    
    public Consultation findRdvConsultationById(int id){
        return daoConsultation.findRdvConsById(id);
    }
    
    public Consultation findConsultationById(int id){
        return daoConsultation.findById(id);
    }
    
    //gerer rdv
    @Override
    public List<Rdv> searchRdvByPatient(int id){
        return daoRdv.findRdvByPatient(id);
    }
    
    public List<RdvDTO> searchAllRdv(){
        return daoRdv.findAllDto();
    }
    
    public List<RdvDTO> searchRdvValideByPatient(int id){
        return daoRdv.findRdvValideByPatient(id);
    }
    
    //autre
    public Medicament searchMedicamentById(int id){
        return daoMed.findById(id);
    }
    
    public Constante searchConstanteById(int id){
        return daoConstante.findById(id);
    }
    
    public Ordonnance searchOrdonnanceById(int id){
        return daoOrd.findById(id);
    }
    
    public List<TypePrestation> searchAllTypePrestation(){
        return daoTpres.findAll();
    }
    
    public List<Medicament> searchAllMedicament(){
        return daoMed.findAll();
    }
    
    public List<Specialite> searchAllSpecialite(){
        return daoSpec.findAll();
    }
    
    public Specialite findSpecialiteById(int id){
        return daoSpec.findById(id);
    }
    
    public TypePrestation findTypePrestationById(int id){
        return daoTpres.findById(id);
    }
    
    public Role findRoleById(int id){
        return daoRole.findById(id);
    }
    
    public int addOrdonnance(Ordonnance ord){
        return daoOrd.insert(ord);
    }
    
    public List<OrdMed> searchMedicamentByOrdonnance(int id){
        return daoOrdMed.findMedicamentByOrdonnance(id);
    }
    
    //Securité
    public Medecin findMedecinById(int id){
        return daoMedecin.findById(id);
    }
    
    @Override
    public User login(String login, String Password) {
        return daoUser.findUserByloginAndPassword(login, Password);
    }

    @Override
    public int addUser(User user) {
        return daoUser.insert(user);
    }
    public List<Patient> searchAllPatient(){
        return daoPatient.findAll();
    }
    
    @Override
    public int addPatient(Patient patient) {
        return daoPatient.insert(patient);
    }
    
    @Override
    public Patient searchPatientByName(String nom, String prenom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Patient searchPatientById(int id){
        return daoPatient.findById(id);
    }
    
    public List<Medecin> searchMedecinBySpecialite(int id){
        return daoUser.findMedecinBySpecialite(id);
    }
    
    //constante
    @Override
    public int addConstante(Constante constante) {
        return daoConstante.insert(constante);
    }

    @Override
    public Boolean updateConstante(Constante constante) {
        return daoConstante.insert(constante)!=0;
    }

    public int addOrdMed(OrdMed ordMed){
        return daoOrdMed.insert(ordMed);
    }

    

    

    

    

    
    
}
