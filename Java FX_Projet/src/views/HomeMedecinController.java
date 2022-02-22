/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Service.Service;
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
public class HomeMedecinController implements Initializable {
    private static HomeMedecinController ctrl;
    private final Service service= new Service();
    @FXML
    private Text txtNom;
    @FXML
    private Text txtPrenom;
    @FXML
    private Text txtRole;
    @FXML
    private AnchorPane AnchorContent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtNom.setText("Nom : "+ConnexionController.getCtrl().getUser().getNom());
        txtPrenom.setText("Prénom : "+ConnexionController.getCtrl().getUser().getPrenom());
        txtRole.setText("Role : "+ConnexionController.getCtrl().getUser().getRole().getLibelle());
        try {
            loadView("v_listRdvMedecin");
        } catch (IOException ex) {
            Logger.getLogger(HomeMedecinController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void handlePageListRdv(ActionEvent event) throws IOException {
        loadView("v_listRdvMedecin");
    }

    @FXML
    private void handleVoirDosseierMedical(ActionEvent event) throws IOException {
        loadView("v_dossierMedPatient");
    }

    @FXML
    private void handlePageListConsultation(ActionEvent event) throws IOException {
        loadView("v_consultationMedecin");
    }

    @FXML
    private void handlePageConnexion(ActionEvent event) {
        this.txtNom.getScene().getWindow().hide();
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
    private void loadView(String view) throws IOException{
        AnchorPane root;
        root = FXMLLoader.load(getClass().getResource("/views/"+view+".fxml"));
        AnchorContent.getChildren().clear();
        AnchorContent.getChildren().add(root);
    }
    
}
