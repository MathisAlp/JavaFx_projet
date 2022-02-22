/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Service.Service;
import dto.PrestationDTO;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MATHIS
 */
public class HomeRpController implements Initializable {
    private HomeRpController ctrl;
    private final Service service = new Service();
    private ObservableList<PrestationDTO> obvPrestation;
    
    @FXML
    private Text txtFprenom;
    @FXML
    private Text txtFnom;
    @FXML
    private TableView<PrestationDTO> tblvPrestation;
    @FXML
    private TableColumn<PrestationDTO, Date> tblcDate;
    @FXML
    private TableColumn<PrestationDTO, String> tblcType;
    @FXML
    private TableColumn<PrestationDTO, String> tblcPatient;
    @FXML
    private DatePicker dateSelected;
    @FXML
    private Pane paneDetailPrestation;
    @FXML
    private Text txtNomPatient;
    @FXML
    private Text txtEtatPrestation;
    @FXML
    private Text txtTypePrestation;
    @FXML
    private Text txtPrenomPatient;
    @FXML
    private Text txtError;
    @FXML
    private Button btnAnnuler;
    @FXML
    private Text txtFrole;
    @FXML
    private Text txtError1;
    @FXML
    private TextField txtFresultat;
    @FXML
    private Text txtError2;
    @FXML
    private Button btnValider;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtFnom.setText("Nom : "+ConnexionController.getCtrl().getUser().getNom());
        txtFprenom.setText("Prenom : "+ConnexionController.getCtrl().getUser().getPrenom());
        txtFrole.setText("Role : "+ConnexionController.getCtrl().getUser().getRole().getLibelle());
        paneDetailPrestation.setVisible(false);
        txtError.setVisible(false);
        txtError1.setVisible(false);
        List<PrestationDTO> listPrestationDto = service.searchAllPrestation();
        loadTableViewPrestation(listPrestationDto);
        ctrl=this;
    }    

    @FXML
    private void handlePageConnexion(ActionEvent event) {
        this.txtFnom.getScene().getWindow().hide();
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

    @FXML
    private void handleSearchPrestation(MouseEvent event) {
        paneDetailPrestation.setVisible(true);
        PrestationDTO presDto= tblvPrestation.getSelectionModel().getSelectedItem();
        txtNomPatient.setText("Nom : "+presDto.getPat().getNom());
        txtPrenomPatient.setText("Prénom : "+presDto.getPat().getPrenom());
        txtTypePrestation.setText("Type : "+presDto.getType_rdv());
        txtEtatPrestation.setText("Statut : "+presDto.getStatut());
        if(presDto.getResultat()!=null){
            txtFresultat.setText(presDto.getResultat());
            txtError2.setVisible(false);
            txtFresultat.setDisable(true);
            btnValider.setDisable(true);
            btnAnnuler.setDisable(true);
        }else{
            txtFresultat.setText(presDto.getResultat());
            txtFresultat.setDisable(false);
            txtError.setVisible(false);
            txtError2.setVisible(false);
            btnAnnuler.setDisable(false);
            btnValider.setDisable(false); 
        } 
    }

    @FXML
    private void handlefilterByDate(MouseEvent event) {
        if(dateSelected.getValue()==null){
            txtError1.setVisible(true);
        }else{
            txtError1.setVisible(false);
            List<PrestationDTO> listPrestationDto=service.searchPrestationByDate(Date.valueOf(dateSelected.getValue()));
            loadTableViewPrestation(listPrestationDto); 
        }
        
    }

    @FXML
    private void handleAnnulerPrestation(ActionEvent event){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Annulation Prestation");
        alert.setHeaderText("Etes-vous sur de vouloir annuler la prestation ?");

        // option != null.
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {
            PrestationDTO presDto= tblvPrestation.getSelectionModel().getSelectedItem();
            service.annulerPrestation(presDto);
            paneDetailPrestation.setVisible(false);
            List<PrestationDTO> listPrestationDto = service.searchAllPrestation();
            loadTableViewPrestation(listPrestationDto);
        } else if (option.get() == ButtonType.CANCEL) {
            alert.close();
        }
    }
    
    public void loadTableViewPrestation(List<PrestationDTO> listPrestationDto){
        obvPrestation=FXCollections.observableArrayList(listPrestationDto);
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcType.setCellValueFactory(new PropertyValueFactory<>("typePres"));
        tblcPatient.setCellValueFactory(new PropertyValueFactory<>("pat"));
        tblvPrestation.setItems(obvPrestation);
    }

    @FXML
    private void handleReset(MouseEvent event) {
        dateSelected.getEditor().clear();
        txtError1.setVisible(false);
        List<PrestationDTO> listPrestationDto = service.searchAllPrestation();
        loadTableViewPrestation(listPrestationDto);
    }

    @FXML
    private void handleCacheDetails(MouseEvent event) {
        paneDetailPrestation.setVisible(false);
        txtError1.setVisible(false);
    }

    @FXML
    private void handleValiderPrestation(ActionEvent event) {
        System.out.println(txtFresultat.getText());
        if(txtFresultat.getText()==null){
            txtError2.setVisible(true);
        }else{
            txtError2.setVisible(false);
            PrestationDTO presDto= tblvPrestation.getSelectionModel().getSelectedItem();
            presDto.setResultat(txtFresultat.getText());
            service.validerPrestation(presDto);
            paneDetailPrestation.setVisible(false);
            List<PrestationDTO> listPrestationDto = service.searchAllPrestation();
            loadTableViewPrestation(listPrestationDto);
        }
    }

    
}
