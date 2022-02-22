 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Service.Service;
import com.jfoenix.controls.JFXDatePicker;
import entities.Consultation;
import entities.Patient;
import entities.Prestation;
import entities.Rdv;
import entities.Specialite;
import entities.TypePrestation;
import entities.User;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author MATHIS
 */
public class DemandeRdvPatientController implements Initializable {
    private final Service service = new Service();
    private User user;
    
    private ObservableList<Specialite> obvSpec;
    private ObservableList<TypePrestation> obvTpres;
    private ObservableList<Rdv> obvRdvTableView;
    
    @FXML
    private ComboBox<String> cboRdv;
    @FXML
    private JFXDatePicker dateSelected;
    @FXML
    private ComboBox<TypePrestation> cboType;
    @FXML
    private TableColumn<Rdv, Date> tblcDate;
    @FXML
    private TableColumn<Rdv, String> tblcType;
    @FXML
    private TableColumn<Rdv, String> tblcEtat;
    @FXML
    private TableView<Rdv> tblvRdv;
    @FXML
    private ComboBox<Specialite> cboSpecialite;
    @FXML
    private Text txtError1;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int id= ConnexionController.getCtrl().getUser().getId();
        loadComboBoxRdv(cboRdv);
        loadTableView(id);
        cboSpecialite.setVisible(false);
        cboType.setVisible(false);
        txtError1.setVisible(false);
        dateHandler(dateSelected);
    }    

    @FXML
    private void handleAddRdv(ActionEvent event) {
        if(cboRdv.getSelectionModel().isEmpty()|| dateSelected.getValue().toString().trim().isEmpty()){
            txtError1.setVisible(true);
        }else{
            if("CONSULTATION".equals(cboRdv.getSelectionModel().getSelectedItem())){
            Consultation cons = new Consultation();
            Patient pat = service.searchPatientById(ConnexionController.getCtrl().getUser().getId());
            cons.setPatient(pat);
            Date date = Date.valueOf(dateSelected.getValue());
            cons.setDate(date);
            cons.setSpecialite(cboSpecialite.getSelectionModel().getSelectedItem());
            service.addConsultation(cons);
            int id= ConnexionController.getCtrl().getUser().getId();
            loadTableView(id);
        }else if("PRESTATION".equals(cboRdv.getSelectionModel().getSelectedItem())){
            Prestation pres = new Prestation();
            Patient pat = service.searchPatientById(ConnexionController.getCtrl().getUser().getId());
            pres.setPatient(pat);
            Date date = Date.valueOf(dateSelected.getValue());
            pres.setDate(date);
            pres.setTypePres(cboType.getSelectionModel().getSelectedItem());
            service.addPrestation(pres);
            int id= ConnexionController.getCtrl().getUser().getId();
            loadTableView(id);
        }
        }
    }
    
    private void loadComboBoxRdv(ComboBox<String> cbo){
        cbo.getItems().add("CONSULTATION");
        cbo.getItems().add("PRESTATION");
        cbo.getSelectionModel().clearSelection();
    }
    
    private void loadComboBoxType(ComboBox<TypePrestation> cbo){
        obvTpres= FXCollections.observableArrayList(service.searchAllTypePrestation());
        cbo.setItems(obvTpres);
    }
    
    private void loadComboBoxSpecialite(ComboBox<Specialite> cbo){
        obvSpec= FXCollections.observableArrayList(service.searchAllSpecialite());
        cbo.setItems(obvSpec);
    }
    
    private void loadTableView(int id){
        obvRdvTableView = FXCollections.observableArrayList(service.searchRdvByPatient(id));
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcType.setCellValueFactory(new PropertyValueFactory<>("type_rdv"));
        tblcEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tblvRdv.setItems(obvRdvTableView);
    }
    
    @FXML
    private void handleChargeCombo(ActionEvent event) {
        if("CONSULTATION".equals(cboRdv.getSelectionModel().getSelectedItem())){
            cboSpecialite.setVisible(true);
            loadComboBoxSpecialite(cboSpecialite);
            cboType.setVisible(false);
        }else if("PRESTATION".equals(cboRdv.getSelectionModel().getSelectedItem())){
            cboType.setVisible(true);
            loadComboBoxType(cboType);
            cboSpecialite.setVisible(false);
        }
    }
    
    public void clearField(){
        
    }
    
    private void dateHandler(DatePicker dp){
        dp.setDayCellFactory(picker -> new DateCell() 
        { 
        @Override
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            LocalDate today = LocalDate.now();
            setDisable(empty || date.compareTo(today) < 0 );
        }
        }); 
        //dp.setValue(LocalDate.now());
    }
}
