/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Service.Service;
import entities.Consultation;
import entities.Medecin;
import entities.OrdMed;
import entities.Patient;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author MATHIS
 */
public class ConsultationMedecinController implements Initializable {
    private final Service service = new Service();
    private ObservableList<Consultation> obvTableViewConsultation;
    private ObservableList<OrdMed> obvTableViewOrdonnance;

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
    private TableView<Consultation> tblvConsultation;
    @FXML
    private TableColumn<Consultation, Date> tblcDate;
    @FXML
    private TableColumn<Consultation, String> tblcPatient;
    @FXML
    private Text txtPrenom;
    @FXML
    private Text txtCode;
    @FXML
    private Text txtAntecedant;
    @FXML
    private Text txtSexe;
    @FXML
    private Pane PaneInfoPatient;
    @FXML
    private Text txtNom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTableViewConsultation();
        PaneInfoPatient.setVisible(false);
        paneConsultation.setVisible(false);
    }    

    @FXML
    private void handleChargeDetail(MouseEvent event) {
        PaneInfoPatient.setVisible(true);
        paneConsultation.setVisible(true);
        Consultation cons =tblvConsultation.getSelectionModel().getSelectedItem();
        Patient pat=cons.getPatient();
        txtPrenom.setText("Nom : "+pat.getPrenom());
        txtNom.setText("Prénom : "+pat.getNom());
        txtCode.setText("Code : "+pat.getCode());
        txtSexe.setText("Sexe : "+pat.getSexe());
        txtAntecedant.setText("Antécédant : "+pat.getAntecedant());
        txtTemperature.setText("Température : "+Integer.toString(cons.getConstante().getTemperature())+" °C");
        txtTaille.setText("Taille : "+Integer.toString(cons.getConstante().getTaille())+" cm");
        txtPoid.setText("Poids : "+Integer.toString(cons.getConstante().getPoids())+" Kg");
        txtTension.setText("Tension : "+cons.getConstante().getTention());
        loadTableMedicament();
    }
    
    public void loadTableViewConsultation(){
        int id= ConnexionController.getCtrl().getUser().getId();
        Medecin med = service.findMedecinById(id);
        obvTableViewConsultation=FXCollections.observableArrayList(service.searchConsultationByMedecin(med));
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcPatient.setCellValueFactory(new PropertyValueFactory<>("patient"));
        tblvConsultation.setItems(obvTableViewConsultation);
    }
    
    public void loadTableMedicament(){
        Consultation cons = tblvConsultation.getSelectionModel().getSelectedItem();
        int id_ordonnance=cons.getOrd().getId_ordonnance();
        obvTableViewOrdonnance=FXCollections.observableArrayList(service.searchMedicamentByOrdonnance(id_ordonnance));
        tblcNom.setCellValueFactory(new PropertyValueFactory<>("med"));
        tblcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tblcPosologie.setCellValueFactory(new PropertyValueFactory<>("posologie"));
        tblvOrdonnance.setItems(obvTableViewOrdonnance);
    }
}
