/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Service.Service;
import dto.PrestationDTO;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author MATHIS
 */
public class ListPrestationPatientController implements Initializable {
    private ObservableList<PrestationDTO> obvPrestation;
    private Service service = new Service();
    
    @FXML
    private TableView<PrestationDTO> tblvPrestations;
    @FXML
    private TableColumn<PrestationDTO, Date> tblcDate;
    @FXML
    private TableColumn<PrestationDTO, String> tblcType;
    @FXML
    private TableColumn<PrestationDTO, String> tblcLibelle;
    @FXML
    private TableColumn<PrestationDTO, String> tblcStatut;
    @FXML
    private TableColumn<PrestationDTO, String> tblcResultat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int id = ConnexionController.getCtrl().getUser().getId();
        List<PrestationDTO> listPrestationDto = service.searchPrestationByPatient(id);
        loadTableViewPrestation(listPrestationDto);
    }
    
    public void loadTableViewPrestation(List<PrestationDTO> listPrestationDto){
        obvPrestation=FXCollections.observableArrayList(listPrestationDto);
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcType.setCellValueFactory(new PropertyValueFactory<>("type_rdv"));
        tblcLibelle.setCellValueFactory(new PropertyValueFactory<>("typePres"));
        tblcStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        tblcResultat.setCellValueFactory(new PropertyValueFactory<>("resultat"));
        tblvPrestations.setItems(obvPrestation);
    }
    
}
