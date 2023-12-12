package com.esprit.espritevent.Controllers.Admin;

import com.esprit.espritevent.Models.Local;
import com.esprit.espritevent.Services.Local.ServiceLocal;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ManageLocalsController implements Initializable {
    public TextField local_name_fid;
    public TextField local_capacity_fid;
    public DatePicker local_available_from_fid;
    public DatePicker local_available_until_fid;
    public TextField is_booked_fid;
    public Button save_btn_fid;
    public TableView<Local> local_table_view_fid;
    public TableColumn<Local, Long> column_local_id_fid;
    public TableColumn<Local, String> column_local_name_fid;
    public TableColumn<Local, Long> column_local_capacity_fid;
    public TableColumn<Local, LocalDate> column_local_available_from_fid;
    public TableColumn<Local, LocalDate> column_local_available_until_fid;

    public TableColumn<Local, Boolean> column_is_booked_fid;
    public Button delete_btn_fid;
    public Button update_btn_fid;
    ServiceLocal serviceLocal = new ServiceLocal();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        save_btn_fid.setOnAction(event -> addNewLocal());
        update_btn_fid.setOnAction(event -> updateLocal());
        delete_btn_fid.setOnAction(event -> deleteLocal());
        initTable();
        refreshTable();
    }

    private void initTable() {
        column_local_name_fid.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getLocalName()));
//            column_local_available_from_fid.setCellValueFactory(cell -> {
//                LocalDate availableFrom = cell.getValue().getLocalAvailableFrom().toLocalDate();
//                return new SimpleObjectProperty<>(availableFrom != null ? availableFrom : LocalDate.MIN);
//            });
//            column_local_available_until_fid.setCellValueFactory(cell -> {
//                LocalDate availableUntil = cell.getValue().getLocalAvailableUntil().toLocalDate();;
//                return new SimpleObjectProperty<>(availableUntil != null ? availableUntil : LocalDate.MIN);
//            });
        column_local_id_fid.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getIdLocal()));
        column_is_booked_fid.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getIsBooked()));
        column_local_capacity_fid.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getLocalCapacity()));
        // Listen for selection changes and update the input fields
        local_table_view_fid.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                updateInputFields(newSelection);
            }
        });

    }

    private void refreshTable() {
        try {
            local_table_view_fid.setItems(serviceLocal.getAllLocals());
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    private void addNewLocal() {
        try {
            Local local = new Local();
            local.setLocalName(local_name_fid.getText());
//          local.setLocalAvailableUntil(Date.valueOf(local_available_until_fid.getText()));
            local.setLocalAvailableUntil(null);
            local.setLocalAvailableFrom(null);
//          local.setLocalAvailableFrom(Date.valueOf(local_available_from_fid.getText()));
            local.setIsBooked(Boolean.parseBoolean(is_booked_fid.getText()));
            local.setLocalCapacity(Long.parseLong(local_capacity_fid.getText()));
            serviceLocal.addLocal(local);
//            local_available_until_fid.setText("");
//            local_available_from_fid.setText("");
            is_booked_fid.setText("");
            local_capacity_fid.setText("");
            local_name_fid.setText("");
            refreshTable();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    private void updateInputFields(Local selectedLocal) {
        local_name_fid.setText(selectedLocal.getLocalName());
        // Uncomment and adjust the following lines based on your date fields
        // local_available_from_fid.setValue(selectedLocal.getLocalAvailableFrom());
        // local_available_until_fid.setValue(selectedLocal.getLocalAvailableUntil());
        is_booked_fid.setText(String.valueOf(selectedLocal.getIsBooked()));
        local_capacity_fid.setText(String.valueOf(selectedLocal.getLocalCapacity()));
    }
    private void updateLocal() {
        Local selectedLocal = local_table_view_fid.getSelectionModel().getSelectedItem();

        if (selectedLocal != null) {
            try {
                // Update the selectedLocal object directly
                selectedLocal.setLocalName(local_name_fid.getText());
                // Uncomment and adjust the following lines based on your date fields
                // selectedLocal.setLocalAvailableFrom(local_available_from_fid.getValue());
                // selectedLocal.setLocalAvailableUntil(local_available_until_fid.getValue());
                selectedLocal.setIsBooked(Boolean.parseBoolean(is_booked_fid.getText()));
                selectedLocal.setLocalCapacity(Long.parseLong(local_capacity_fid.getText()));

                serviceLocal.updateLocal(selectedLocal);
                refreshTable();
            } catch (SQLException e) {
                System.out.println(e);
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please select a Local to update.");
        }
    }
    private void deleteLocal (){
        try {
        Local selectedLocal = local_table_view_fid.getSelectionModel().getSelectedItem();
        long id = selectedLocal.getIdLocal();
        System.out.println(id);
        serviceLocal.deleteLocal(id);
        refreshTable();
    } catch (SQLException e) {
        System.out.println(e);
    }
    }

}
