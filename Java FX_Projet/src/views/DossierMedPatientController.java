/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Service.Service;
import dto.MedicamentDTO;
import dto.RdvDTO;
import entities.OrdMed;
import entities.Patient;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
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
public class DossierMedPatientController implements Initializable {
    private final Service service = new Service();
    private ObservableList<Patient> obvPatient;
    private ObservableList<RdvDTO> obvTableViewRdv;
    private ObservableList<OrdMed> obvTableViewOrdonnance;
    
    @FXML
    private ComboBox<Patient> cboPatient;
    @FXML
    private TextField txtCode;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtPrenom;
    @FXML
    private TextField txtAntecedant;
    @FXML
    private TableView<RdvDTO> tblvRdv;
    @FXML
    private TableColumn<RdvDTO, Date> tblcDate;
    @FXML
    private TableColumn<RdvDTO, String> tblvTypeRdv;
    @FXML
    private Pane paneConsultation;
    @FXML
    private Text txtTemperature;
    @FXML
    private Text txtTaille;
    @FXML
    private Text txtPoid;
    @FXML
    private Text txtTension;
    @FXML
    private TableView<OrdMed> tblvOrdonnance;
    @FXML
    private TableColumn<OrdMed, String> tblcNom;
    @FXML
    private TableColumn<OrdMed, Integer> tblcNombre;
    @FXML
    private TableColumn<OrdMed, String> tblcPosologie;
    @FXML
    private Text txtSpecialite;
    @FXML
    private Text txtMedecin;
    @FXML
    private Pane panePrestation;
    @FXML
    private Text txtTypePres;
    @FXML
    private Text txtResultat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        panePrestation.setVisible(false);
        paneConsultation.setVisible(false);
        loadComboBoxPatient(cboPatient); 
    } 
    
    public void loadComboBoxPatient(ComboBox<Patient> cbo){
       obvPatient =FXCollections.observableArrayList(service.searchAllPatient());
       cbo.setItems(obvPatient);
    }
    private void loadTableViewRdvPatient(){
        int id=cboPatient.getSelectionModel().getSelectedItem().getId();
        obvTableViewRdv=FXCollections.observableArrayList(service.searchRdvValideByPatient(id));
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblvTypeRdv.setCellValueFactory(new PropertyValueFactory<>("type_rdv"));
        tblvRdv.setItems(obvTableViewRdv);
    }
    
    @FXML
    private void handleChargeDetail(MouseEvent event) {
        RdvDTO rdvDto = tblvRdv.getSelectionModel().getSelectedItem();
        if("CONSULTATION".equals(rdvDto.getType_rdv())){
            paneConsultation.setVisible(true);
            panePrestation.setVisible(false);
            txtSpecialite.setText("Specialite : "+rdvDto.getSpec().getLibelle());
            txtMedecin.setText("Medecin : "+rdvDto.getMed().getNom()+" "+rdvDto.getMed().getPrenom());
            txtTemperature.setText("Température : "+Integer.toString(rdvDto.getConstante().getTemperature())+" °C");
            txtTaille.setText("Taille : "+Integer.toString(rdvDto.getConstante().getTaille())+" cm");
            txtPoid.setText("Poids : "+Integer.toString(rdvDto.getConstante().getPoids())+" Kg");
            txtTension.setText("Tension : "+rdvDto.getConstante().getTention());
            loadTableMedicament();
        }else if("PRESTATION".equals(rdvDto.getType_rdv())){
            paneConsultation.setVisible(false);
            panePrestation.setVisible(true);
            txtResultat.setText("Resultat : "+rdvDto.getResultat());
            txtTypePres.setText("Type Prestation : "+rdvDto.getTypePres().getLibelle());
        }
    }
    
    @FXML
    private void handleChargeTblv(ActionEvent event) {
        loadTableViewRdvPatient();
        Patient pat = cboPatient.getSelectionModel().getSelectedItem();
        txtNom.setText(pat.getNom());
        txtNom.setDisable(true);
        txtPrenom.setText(pat.getPrenom());
        txtPrenom.setDisable(true);
        txtAntecedant.setText(pat.getAntecedant());
        txtAntecedant.setDisable(true);
        txtCode.setText(Integer.toString(pat.getCode()));
        txtCode.setDisable(true);
        panePrestation.setVisible(false);
        paneConsultation.setVisible(false);
    }
    
    public void loadTableMedicament(){
        RdvDTO rdvDto = tblvRdv.getSelectionModel().getSelectedItem();
        int id_ordonnance=rdvDto.getOrd().getId_ordonnance();
        obvTableViewOrdonnance=FXCollections.observableArrayList(service.searchMedicamentByOrdonnance(id_ordonnance));
        tblcNom.setCellValueFactory(new PropertyValueFactory<>("med"));
        tblcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tblcPosologie.setCellValueFactory(new PropertyValueFactory<>("posologie"));
        tblvOrdonnance.setItems(obvTableViewOrdonnance);
    }
}
