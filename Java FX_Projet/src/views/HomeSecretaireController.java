/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Service.Service;
import dto.RdvDTO;
import entities.Consultation;
import entities.Medecin;
import entities.Prestation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MATHIS
 */
public class HomeSecretaireController implements Initializable {
    private final Service service = new Service();
    private HomeSecretaireController ctrl;
    private ObservableList<RdvDTO> obvRdv;
    private ObservableList<Medecin> obvMedecin;
    
    @FXML
    private Text txtNom;
    @FXML
    private Text txtPrenom;
    @FXML
    private Text txtRole;
    @FXML
    private TextField txtFnomPatient;
    @FXML
    private TextField txtFprenomPatient;
    @FXML
    private DatePicker dateF;
    @FXML
    private TextField txtFrdv;
    @FXML
    private ComboBox<Medecin> cboMed;
    @FXML
    private TextField txtFtype;
    @FXML
    private TableView<RdvDTO> tblvRdv;
    @FXML
    private TableColumn<RdvDTO, Date> tblcDate;
    @FXML
    private TableColumn<RdvDTO, String> tblcPatient;
    @FXML
    private TableColumn<RdvDTO, String> tblcTypeRdv;
    @FXML
    private TableColumn<RdvDTO, String> tblcEtat;
    @FXML
    private Text txtError;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ctrl= this;
        txtNom.setText("Nom : "+ConnexionController.getCtrl().getUser().getNom());
        txtPrenom.setText("Prenom : "+ConnexionController.getCtrl().getUser().getPrenom());
        txtRole.setText("Role : "+ConnexionController.getCtrl().getUser().getRole().getLibelle());
        txtError.setVisible(false);
        List<RdvDTO> listRdvDto = service.searchAllRdv();
        loadTableViewRdv(listRdvDto);
        cboMed.setVisible(false);
    }    

    @FXML
    private void handleValiderRendezvous(ActionEvent event) {
        Alert alert =new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Enrégistrement rendez-vous");
        RdvDTO rdvDto=tblvRdv.getSelectionModel().getSelectedItem();
        if(txtFnomPatient.getText().trim().isEmpty() || txtFprenomPatient.getText().trim().isEmpty()|| txtFtype.getText().trim().isEmpty() 
                || txtFrdv.getText().trim().isEmpty() ||dateF.toString().trim().isEmpty() ){
            txtError.setText("Veuillez sélectionner un rendez-vous");
            txtError.setVisible(true);
        }else{
            txtError.setVisible(false);
            if("CONSULTATION".equals(rdvDto.getType_rdv())){
                if(cboMed.getSelectionModel().isEmpty()){
                    txtError.setText("Sélectionnez un medecin disponible");
                    txtError.setVisible(true);
                }else{
                    txtError.setVisible(false);
                    Consultation cons = service.findRdvConsultationById(rdvDto.getId_rdv());
                    cons.setMedecin(cboMed.getSelectionModel().getSelectedItem());
                    service.validerRdvConsultation(cons);
                    alert.setContentText("Consultation programmée !!");
                    alert.show();
                    clearField();
                    List<RdvDTO> listRdvDto = service.searchAllRdv();
                    loadTableViewRdv( listRdvDto);   
                }     
            }else if("PRESTATION".equals(rdvDto.getType_rdv())){
                Prestation pres = service.findRdvPrestationById(rdvDto.getId_rdv());
                service.validerRdvPrestation(pres);
                alert.setContentText("Prestation programmée !!");
                alert.show();
                clearField();
                List<RdvDTO> listRdvDto = service.searchAllRdv();
                loadTableViewRdv( listRdvDto);
            }
        }
        
    }

    @FXML
    private void handleChargePatient(MouseEvent event) {
        RdvDTO rdvDto = tblvRdv.getSelectionModel().getSelectedItem();
        txtFnomPatient.setText(rdvDto.getPatient().getNom());
        txtFprenomPatient.setText(rdvDto.getPatient().getPrenom());
        txtFrdv.setText(rdvDto.getType_rdv());
        Date date = rdvDto.getDate();
        dateF.setValue(date.toLocalDate());
        txtFnomPatient.setDisable(true);
        txtFprenomPatient.setDisable(true);
        txtFrdv.setDisable(true);
        dateF.setDisable(true);
        if("CONSULTATION".equals(rdvDto.getType_rdv())){
           txtFtype.setText(rdvDto.getSpec().getLibelle());
           txtFtype.setDisable(true);
           cboMed.setVisible(true);
           loadComboBoxMedecin(cboMed, rdvDto.getSpec().getId_specialite());
        }else if("PRESTATION".equals(rdvDto.getType_rdv())){
           txtFtype.setText(rdvDto.getTypePres().getLibelle());
           txtFtype.setDisable(true);
           cboMed.setVisible(false);
        }
        
    }
    
    public void loadTableViewRdv(List<RdvDTO> listRdvDto){
        obvRdv=FXCollections.observableArrayList(listRdvDto);
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcTypeRdv.setCellValueFactory(new PropertyValueFactory<>("type_rdv"));
        tblcPatient.setCellValueFactory(new PropertyValueFactory<>("patient"));
        tblcEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tblvRdv.setItems(obvRdv);
    }
    
    private void loadComboBoxMedecin(ComboBox<Medecin> cbo, int id){
        obvMedecin= FXCollections.observableArrayList(service.searchMedecinBySpecialite(id));
        cboMed.setItems(obvMedecin);
    }

    @FXML
    private void handlePageConnexion(ActionEvent event) {
        this.txtFnomPatient.getScene().getWindow().hide();
        AnchorPane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/views/v_connexion.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            } catch (IOException ex) {
                  Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void clearField(){
        txtFnomPatient.clear();
        txtFprenomPatient.clear();
        txtFrdv.clear();
        txtFtype.clear();
        dateF.getEditor().clear();
    }
}
