/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Service.Service;
import dto.ConsultationDTO;
import dto.MedicamentDTO;
import entities.Constante;
import entities.Consultation;
import entities.Medicament;
import entities.OrdMed;
import entities.Ordonnance;
import entities.Patient;
import entities.Prestation;
import entities.Specialite;
import entities.TypePrestation;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author MATHIS
 */
public class ListRdvMedecinController implements Initializable {
    private final Service service= new Service();
    private ObservableList<ConsultationDTO> obvRdvCons;
    private final ObservableList<MedicamentDTO> obvTableViewMed=FXCollections
            .observableArrayList() ;
    private ObservableList<Medicament> obvMedicament;
    private ObservableList<Specialite> obvSpec;
    private ObservableList<TypePrestation> obvTypePres;
    
    @FXML
    private TableView<ConsultationDTO> tlbvRdvCons;
    @FXML
    private TableColumn<ConsultationDTO, Date> tblcDate;
    @FXML
    private TableColumn<ConsultationDTO, String> tblcPatient;
    @FXML
    private DatePicker dateSelected;
    @FXML
    private Pane paneDoConsultation;
    @FXML
    private Text txtNomPatient;
    @FXML
    private Text txtAntecedent;
    @FXML
    private Text txtSexePatient;
    @FXML
    private Text txtPrenomPatient;
    @FXML
    private ComboBox<Medicament> cboMedicament;
    @FXML
    private TextField txtFposologie;
    @FXML
    private TableView<MedicamentDTO> tblvMedicament;
    @FXML
    private TableColumn<MedicamentDTO, String> tblcNom;
    @FXML
    private TableColumn<MedicamentDTO, Integer> tblcNombre;
    @FXML
    private TableColumn<MedicamentDTO, String> tlbcPosologie;
    @FXML
    private TextField txtFnbreBoite;
    @FXML
    private TextField txtFkg;
    @FXML
    private TextField txtFtemperature;
    @FXML
    private TextField txtFtaille;
    @FXML
    private ComboBox<String> cboRdv;
    @FXML
    private ComboBox<Specialite> cboSpecialite;
    @FXML
    private ComboBox<TypePrestation> cboTypePres;
    @FXML
    private DatePicker dateS;
    @FXML
    private TextField txtFtension;
    @FXML
    private Text txtError;
    @FXML
    private Text txtError1;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTableViewRdvCons();
        paneDoConsultation.setVisible(false);
        txtError.setVisible(false);
        txtError1.setVisible(false);
    }    

    @FXML
    private void handleSearch(MouseEvent event) {
        int id= ConnexionController.getCtrl().getUser().getId();
        List<ConsultationDTO> listRdvCons = service.searchRdvConsultationByMedecinAndDate(Date.valueOf(dateSelected.getValue()), id);
        obvRdvCons=FXCollections.observableArrayList(listRdvCons);
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcPatient.setCellValueFactory(new PropertyValueFactory<>("pat"));
        tlbvRdvCons.setItems(obvRdvCons);
    }

    @FXML
    private void handleOpenPane(ActionEvent event) {
        if(tlbvRdvCons.getSelectionModel().isEmpty()){
            txtError.setVisible(true);
        }else{
            txtError.setVisible(true);
            paneDoConsultation.setVisible(true);
            ConsultationDTO cons = tlbvRdvCons.getSelectionModel().getSelectedItem();
            txtNomPatient.setText("Nom : "+cons.getPat().getNom());
            txtPrenomPatient.setText("Prénom : "+cons.getPat().getPrenom());
            txtSexePatient.setText("Sexe : "+cons.getPat().getSexe());
            txtAntecedent.setText("Antécédant : "+cons.getPat().getAntecedant());
            loadComboBoxMed(cboMedicament);
            loadComboBoxSpecialite(cboSpecialite);
            cboSpecialite.setVisible(false);
            loadComboBoxTypePres(cboTypePres);
            cboTypePres.setVisible(false);
            loadComboBoxRdv(cboRdv);
        }
    }

    @FXML
    private void handleReset(MouseEvent event) {
        dateSelected.getEditor().clear();
        loadTableViewRdvCons();
    }

    @FXML
    private void handleAddMedicament(ActionEvent event) {
        MedicamentDTO medDto = new MedicamentDTO();
        medDto.setId_medDto(cboMedicament.getSelectionModel().getSelectedItem().getId_medicament());
        medDto.setNom(cboMedicament.getSelectionModel().getSelectedItem().getNom());
        medDto.setNombre(Integer.parseInt(txtFnbreBoite.getText()));
        medDto.setPosologie(txtFposologie.getText());
        medDto.setCode(cboMedicament.getSelectionModel().getSelectedItem().getCode());
        Consultation cons= new Consultation();
        cons.toConsultation(tlbvRdvCons.getSelectionModel().getSelectedItem());
        medDto.setCons(cons);
        obvTableViewMed.add(medDto);
        loadTableViewMed();
        cboMedicament.getSelectionModel().clearSelection();
        txtFnbreBoite.clear();
        txtFposologie.clear();
    }
    
    public void loadTableViewMed(){
        tblcNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tblcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tlbcPosologie.setCellValueFactory(new PropertyValueFactory<>("posologie"));
        tblvMedicament.setItems(obvTableViewMed);
    }

    @FXML
    private void handleDropMedicament(ActionEvent event) {
        if(tblvMedicament.getSelectionModel().isEmpty()){
            txtError1.setText("Selectionner un medicament");
            txtError1.setVisible(true);
        }else{
            MedicamentDTO medDto=tblvMedicament.getSelectionModel().getSelectedItem();
            obvTableViewMed.remove(medDto);
            loadTableViewMed();
        }
    }

    @FXML
    private void handleAddRdv(ActionEvent event) {
        if("CONSULTATION".equals(cboRdv.getSelectionModel().getSelectedItem())){
            Consultation cons = new Consultation();
            int id=tlbvRdvCons.getSelectionModel().getSelectedItem().getPat().getId();
            Patient pat = service.searchPatientById(id);
            cons.setPatient(pat);
            Date date = Date.valueOf(dateS.getValue());
            cons.setDate(date);
            cons.setSpecialite(cboSpecialite.getSelectionModel().getSelectedItem());
            service.addConsultation(cons);
        }else if("PRESTATION".equals(cboRdv.getSelectionModel().getSelectedItem())){
            Prestation pres = new Prestation();
            int id=tlbvRdvCons.getSelectionModel().getSelectedItem().getPat().getId();
            Patient pat = service.searchPatientById(id);
            pres.setPatient(pat);
            Date date = Date.valueOf(dateS.getValue());
            pres.setDate(date);
            pres.setTypePres(cboTypePres.getSelectionModel().getSelectedItem());
            service.addPrestation(pres);
        }
    }

    @FXML
    private void handleEnregisterConsultation(ActionEvent event) {
        Consultation cons= new Consultation();
        cons.toConsultation(tlbvRdvCons.getSelectionModel().getSelectedItem());
        Constante constante = new Constante();
        constante.setTention(txtFtension.getText());
        constante.setTemperature(Integer.parseInt(txtFtemperature.getText()));
        constante.setTaille(Integer.parseInt(txtFtaille.getText()));
        constante.setPoids(Integer.parseInt(txtFkg.getText()));
        constante.setConsultation(cons);
        int id_constante= service.addConstante(constante);
        constante = service.searchConstanteById(id_constante);
        Ordonnance ord = new Ordonnance();
        ord.setCons(cons);
        Patient pat=tlbvRdvCons.getSelectionModel().getSelectedItem().getPat();
        ord.setPat(pat);
        int id_ordonnance=service.addOrdonnance(ord);
        ord=service.searchOrdonnanceById(id_ordonnance);
        for(MedicamentDTO medDto:obvTableViewMed){
            OrdMed ordMed = new OrdMed();
            Medicament med = service.searchMedicamentById(medDto.getId_medDto());
            ordMed.setMed(med);
            ordMed.setNombre(medDto.getNombre());
            ordMed.setPosologie(medDto.getPosologie());
            ordMed.setOrd(ord);
            service.addOrdMed(ordMed);
        }
        cons.setConstante(constante);
        cons.setOrd(ord);
        service.validerConsultation(cons);
        showAlert("Consultation Bien Enrégistrée");
        loadTableViewRdvCons();
    }
    
    public void loadTableViewRdvCons(){
        int id= ConnexionController.getCtrl().getUser().getId();
        List<ConsultationDTO> listRdvCons = service.searchRdvConsultationByMedecin(id);
        obvRdvCons=FXCollections.observableArrayList(listRdvCons);
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcPatient.setCellValueFactory(new PropertyValueFactory<>("pat"));
        tlbvRdvCons.setItems(obvRdvCons);
    }

    @FXML
    private void handleCachePane(MouseEvent event) {
        paneDoConsultation.setVisible(false);
    }
    
    public void loadComboBoxMed(ComboBox<Medicament> cbo){
        obvMedicament=FXCollections.observableArrayList(service.searchAllMedicament());
        cbo.setItems(obvMedicament);
    }
    
    private void loadComboBoxSpecialite(ComboBox<Specialite> cbo){
        obvSpec= FXCollections.observableArrayList(service.searchAllSpecialite());
        cbo.setItems(obvSpec);
    }
    
    private void loadComboBoxTypePres(ComboBox<TypePrestation> cbo){
        obvTypePres= FXCollections.observableArrayList(service.searchAllTypePrestation());
        cbo.setItems(obvTypePres);
    }

    @FXML
    private void handleChargeCombo(ActionEvent event) {
        if("CONSULTATION".equals(cboRdv.getSelectionModel().getSelectedItem())){
            cboSpecialite.setVisible(true);
            loadComboBoxSpecialite(cboSpecialite);
            cboTypePres.setVisible(false);
        }else if("PRESTATION".equals(cboRdv.getSelectionModel().getSelectedItem())){
            cboTypePres.setVisible(true);
            loadComboBoxTypePres(cboTypePres);
            cboSpecialite.setVisible(false);
        }
    }
    
    private void loadComboBoxRdv(ComboBox<String> cbo){
        cbo.getItems().add("CONSULTATION");
        cbo.getItems().add("PRESTATION");
        cbo.getSelectionModel().clearSelection();
    }
    
    @FXML
    private void handleAnnulerRdvCons(ActionEvent event) {
        if(tlbvRdvCons.getSelectionModel().isEmpty()){
            txtError.setVisible(true);
        }else{
            txtError.setVisible(false);
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Annulé consultation");
            alert.setHeaderText("Etes-vous sur de vouloir annuler la consultation ?");

            // option != null.
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
                int id = tlbvRdvCons.getSelectionModel().getSelectedItem().getId_consultation();
                service.annulerConsultation(id);
                loadTableViewRdvCons();
                paneDoConsultation.setVisible(false);
            } else if (option.get() == ButtonType.CANCEL) {
                alert.close();
            }
        }
         
    }
    
    public void showAlert( String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Consultation");
        alert.setHeaderText("");
        alert.setContentText(message);
        alert.show();
    }

    @FXML
    private void handleResetRdv(ActionEvent event) {
        cboRdv.getSelectionModel().clearSelection();
        cboSpecialite.getSelectionModel().clearSelection();
        cboSpecialite.setVisible(false);
        cboTypePres.getSelectionModel().clearSelection();
        cboTypePres.setVisible(false);
        dateS.getEditor().clear();
    }
}
