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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MATHIS
 */
public class ConnexionController implements Initializable {

    @FXML
    private Text txtError;
    
    @FXML
    private PasswordField txtFpassword;
    
    private static ConnexionController ctrl;
    @FXML
    private TextField txtFlogin;
    
    private final Service service = new Service();
    private User user;
    private Patient patient;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtError.setVisible(false);
        ctrl = this;
    }    

    @FXML
    private void handlePageInscription(ActionEvent event) {
        this.txtError.getScene().getWindow().hide();
        AnchorPane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/views/v_inscription.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            }catch (IOException ex) {
                Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void handleConnexion(ActionEvent event) {
        String login = txtFlogin.getText().trim();
        String password= txtFpassword.getText().trim();
        if(login.isEmpty() || password.isEmpty()){
            txtError.setText("Login ou Mot de Passe Obligatoire");
            txtError.setVisible(true);
        }else{
            user= service.login(login, password);
            if(user==null){
                txtError.setText("Login ou Mot de Passe Incorrect");
                txtError.setVisible(true);
            }else{
                this.txtError.getScene().getWindow().hide();
                switch (user.getRole().getId_role()) {
                    case 1:
                        try {
                            loadView("v_homeAdmin");
                        } catch (IOException ex) {
                            Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
                        }   break;
                    case 2:
                        try {
                            loadView("v_homePatient");  
                        } catch (IOException ex) {
                            Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
                        }   break;
                    case 3:
                        try {
                            loadView("v_homeRp");
                        } catch (IOException ex) {
                            Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
                        }   break;
                    case 4:
                        try {
                            loadView("v_homeSecretaire");
                        } catch (IOException ex) {
                            Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
                        }   break;
                    case 5:
                        try {
                            loadView("v_homeMedecin");
                        } catch (IOException ex) {
                            Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
                        }   break;
                    default:
                        break;
                }
                
            }
        }
    }

    public static ConnexionController getCtrl() {
        return ctrl;
    }

    public User getUser() {
        return user;
    }
    
    private void loadView(String view) throws IOException{
        AnchorPane root= null;
        root = FXMLLoader.load(getClass().getResource("/views/"+view+".fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    
    
}
