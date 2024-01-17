package com.esprit.espritevent.Controllers.Admin;

import com.esprit.espritevent.Models.Manager;
import com.esprit.espritevent.Services.Manager.ManagerService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ManagerController implements Initializable {
    public TextField manager_id_fid;
    public TextField manager_nom_fid;
    public TextField manager_prenom_fid;
    public TextField manager_email_fid;
    public TextField manager_associatedClub_fid;
    public TableView<Manager> manager_table_view_fid;
    public TableColumn<Manager, Long> column_manager_id_fid;
    public TableColumn<Manager, String> column_manager_nom_fid;
    public TableColumn<Manager, String> column_manager_prenom_fid;
    public TableColumn<Manager, String> column_manager_email_fid;
    public TableColumn<Manager, String> column_manager_associatedClub_fid;
    public Button save_btn_fid;
    public Button delete_btn_fid;
    public Button update_btn_fid;
    ManagerService managerService = new ManagerService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        save_btn_fid.setOnAction(event -> addNewManager());
        update_btn_fid.setOnAction(event -> updateManager());
        delete_btn_fid.setOnAction(event -> deleteManager());
        initTable();
        refreshTable();
    }
    private void initTable() {
        column_manager_id_fid.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getId()));
        column_manager_nom_fid.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getNom()));
        column_manager_prenom_fid.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getPrenom()));
        column_manager_email_fid.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getEmail()));
        column_manager_associatedClub_fid.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getAssociatedClub()));
        // Listen for selection changes and update the input fields
        manager_table_view_fid.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                updateInputFields(newSelection);
            }
        });

    }

    private void refreshTable() {
        try {
            manager_table_view_fid.setItems(managerService.getAllManger());
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    private void addNewManager() {
        try {
            Manager manager = new Manager();
            manager.setNom(manager_nom_fid.getText());
            manager.setPrenom(manager_prenom_fid.getText());
            manager.setEmail(manager_email_fid.getText());
            manager.setAssociatedClub(manager_associatedClub_fid.getText());
            managerService.addManager(manager);
            manager_nom_fid.setText("");
            manager_prenom_fid.setText("");
            manager_email_fid.setText("");
            manager_associatedClub_fid.setText("");
            refreshTable();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    private void updateInputFields(Manager selectedManager) {
        manager_nom_fid.setText(selectedManager.getNom());
//         Uncomment and adjust the following lines based on your date fields
        column_manager_prenom_fid.setText(selectedManager.getPrenom());
        column_manager_email_fid.setText(selectedManager.getEmail());
        column_manager_associatedClub_fid.setText(selectedManager.getAssociatedClub());
    }
    private void updateManager() {
        Manager selectedManager = manager_table_view_fid.getSelectionModel().getSelectedItem();

        if (selectedManager != null) {
            try {
                // Update the selectedLocal object directly
                selectedManager.setNom(manager_nom_fid.getText());
                selectedManager.setPrenom(manager_prenom_fid.getText());
                selectedManager.setEmail(manager_email_fid.getText());
                selectedManager.setAssociatedClub(manager_associatedClub_fid.getText());

                managerService.updateManager(selectedManager);
                refreshTable();
            } catch (SQLException e) {
                System.out.println(e);
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please select a Manager to update.");
        }
    }
    private void deleteManager (){
        try {
            Manager selectedManager = manager_table_view_fid.getSelectionModel().getSelectedItem();
            long id = selectedManager.getId();
            System.out.println(id);
            managerService.deleteManager(id);
            refreshTable();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}

