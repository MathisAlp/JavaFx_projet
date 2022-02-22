/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Service.Service;
import entities.Patient;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MATHIS
 */
public class HomePatientController implements Initializable {
    private static HomePatientController ctrl;
    private final Service service= new Service();
    private Patient patient;
    
    @FXML
    private Text txtCode;
    @FXML
    private Text txtSexe;
    @FXML
    private Text txtNom;
    @FXML
    private Text txtTelephone;
    @FXML
    private Text txtPrenom;
    @FXML
    private Text txtAntecedant;
    @FXML
    private AnchorPane anchorContent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       patient = service.searchPatientById(ConnexionController.getCtrl().getUser().getId());
       txtCode.setText("Code : " + patient.getCode());
       txtSexe.setText("Sexe : " + patient.getSexe());
       txtNom.setText("Nom : " + patient.getNom());
       txtPrenom.setText("Prénom : " + patient.getPrenom());
       txtTelephone.setText("Téléphone : " + Integer.toString(patient.getTel()));
       txtAntecedant.setText("Antécédant : " + patient.getAntecedant());
       try {
            loadView("v_DemandeRdvPatient");;
        } catch (IOException ex) {
            Logger.getLogger(HomePatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
       ctrl = this;
    }
    
    private void loadView(String view) throws IOException{
        AnchorPane root;
        root = FXMLLoader.load(getClass().getResource("/views/"+view+".fxml"));
        anchorContent.getChildren().clear();
        anchorContent.getChildren().add(root);
    }

    @FXML
    private void handleLoadViewPatientRdv(ActionEvent event) {
        try {
            loadView("v_DemandeRdvPatient");
        } catch (IOException ex) {
            Logger.getLogger(HomePatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleLoadViewPatientPrestations(ActionEvent event) {
        try {
            loadView("v_listPrestationPatient");
        } catch (IOException ex) {
            Logger.getLogger(HomePatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleLoadViewPatientConsultation(ActionEvent event) {
        try {
            loadView("v_listConsultationPatient");
        } catch (IOException ex) {
            Logger.getLogger(HomePatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleLoadViewConnexion(ActionEvent event) {
        this.txtCode.getScene().getWindow().hide();
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

    public static HomePatientController getCtrl() {
        return ctrl;
    }

    public Patient getPatient() { 
        return patient;
    }
    
    
}
