/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Service.Service;
import dto.ConsultationDTO;
import entities.Consultation;
import entities.Patient;
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
public class ListConsultationPatientController implements Initializable {
    private final Service service = new Service ();
    private Patient patient;
    
    private ObservableList<ConsultationDTO> obvConsultation;
    
    @FXML
    private TableColumn<ConsultationDTO, Date> tblcDate;
    @FXML
    private TableColumn<ConsultationDTO, String> tblcType;
    @FXML
    private TableColumn<ConsultationDTO, String> tblcLibelle;
    @FXML
    private TableView<ConsultationDTO> tblvConsultation;
    @FXML
    private TableColumn<ConsultationDTO, String> tblcMedecin;
    @FXML
    private TableColumn<ConsultationDTO, String> tblcStatut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int id= ConnexionController.getCtrl().getUser().getId();
        List<ConsultationDTO> listConsultationDto =service.searchConsultationByPatient(id);
        loadTableViewConsultation(listConsultationDto);
    }    
    
    private void loadTableViewConsultation(List<ConsultationDTO> listConsultationDto){
        obvConsultation=FXCollections.observableArrayList(listConsultationDto);
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcType.setCellValueFactory(new PropertyValueFactory<>("type_rdv"));
        tblcLibelle.setCellValueFactory(new PropertyValueFactory<>("spec"));
        tblcMedecin.setCellValueFactory(new PropertyValueFactory<>("med"));
        tblcStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        tblvConsultation.setItems(obvConsultation);
    }
    
}
