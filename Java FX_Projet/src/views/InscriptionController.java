/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Service.Service;
import com.jfoenix.controls.JFXRadioButton;
import entities.Patient;
import entities.Role;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.ServiceUtils;

/**
 * FXML Controller class
 *
 * @author MATHIS
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField txtFnom;
    @FXML
    private TextField txtFprenom;
    @FXML
    private TextField txtFtelephone;
    @FXML
    private TextField txtFantecedant;
    @FXML
    private TextField txtFlogin;
    @FXML
    private PasswordField txtFpassword;
    
    private static InscriptionController ctrl;
    @FXML
    private Label txtError; 
    
    private final Service service = new Service();
    private Patient patient;
    private Role role;
    @FXML
    private JFXRadioButton rdoBtnFemme;
    @FXML
    private JFXRadioButton rdoBtnHomme;
    @FXML
    private Label txtError2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtError.setVisible(false);
        ctrl=this;
        txtError2.setVisible(false);
    }    

    @FXML
    private void handlePageConnexion(ActionEvent event) {
        this.txtError.getScene().getWindow().hide();
        AnchorPane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/views/v_connexion.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            }catch (IOException ex) {
                Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void handleInscription(ActionEvent event) {
        String nom = txtFnom.getText().trim();
        String prenom = txtFprenom.getText().trim();
        String telephone= txtFtelephone.getText().trim();
        String antecedant = txtFantecedant.getText().trim();
        String login = txtFlogin.getText().trim();
        String password = txtFpassword.getText().trim();
        String sexe= null;
        int code = Integer.parseInt(ServiceUtils.createCode());
        if(rdoBtnHomme.isSelected()){
            sexe= rdoBtnHomme.getText().trim();
        }else if (rdoBtnFemme.isSelected()){
            sexe= rdoBtnFemme.getText().trim();
        }
        if( nom.isEmpty()||prenom.isEmpty()||antecedant.isEmpty()||telephone.isEmpty()||login.isEmpty()||password.isEmpty()||sexe.isEmpty()|| rdoBtnHomme.isSelected()&&rdoBtnFemme.isSelected()){
            txtError.setText("Tous les champs sont obligatoires");
            txtError.setVisible(true);
            txtError2.setVisible(true);
        }else if(nom.isEmpty()||prenom.isEmpty()||antecedant.isEmpty()||telephone.isEmpty()||login.isEmpty()||password.isEmpty()||sexe.isEmpty()){
            txtError2.setVisible(false);
            txtError.setVisible(true);    
        }else{
            if(rdoBtnHomme.isSelected()&&rdoBtnFemme.isSelected()){
                txtError2.setVisible(true);
                txtError.setVisible(false);
            }else{
                patient = new Patient();
                patient.setNom(nom);
                patient.setPrenom(prenom);
                patient.setTel(Integer.parseInt(telephone));
                patient.setSexe(sexe);
                patient.setAntecedant(antecedant);
                patient.setLogin(login);
                patient.setPassword(password);
                patient.setCode(code);
                int verif = service.addPatient(patient);
                if(verif >0){
                    this.txtError.getScene().getWindow().hide();
                    AnchorPane root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/views/v_connexion.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                        }catch (IOException ex) {
                            Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    Alert alert =new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("User suscribe");
                    alert.setContentText("Inscription réussie, Connecter vous à votre compte");
                    alert.show();
                }
            }
            
        }
        
    }
    
}
