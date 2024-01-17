package com.esprit.espritevent.Services.Manager;

import com.esprit.espritevent.Models.Manager;
import com.esprit.espritevent.Utils.DataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ManagerService implements IManagerService {
    Connection conn = DataSource.getInstance().getConn();
    PreparedStatement preparedStatement = null;

    @Override
    public void addManager(Manager manager) throws SQLException {
        try {
            preparedStatement = conn.prepareStatement("INSERT INTO `manager`(`id_manager`, `associated_club`, `email`, `nom`, `prenom`)VALUES (?,?,?,?,?);");
            preparedStatement.setLong(1, manager.getId());
            preparedStatement.setString(2, manager.getAssociatedClub());
            preparedStatement.setString(3, manager.getNom());
            preparedStatement.setString(4, manager.getPrenom());
            preparedStatement.setString(5, manager.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public ObservableList<Manager> getAllManger() throws SQLException {
        ObservableList<Manager> managers = FXCollections.observableArrayList();

        try {
            Statement ste = conn.createStatement();
            String req = "SELECT * FROM `manager`";
            ResultSet res = ste.executeQuery(req);

            while (res.next()) {

                Manager manager = new Manager(
                        res.getInt("id_manager"),
                        res.getString("nom"),
                        res.getString("prenom"),
                        res.getString("email"),
                        res.getObject("associated_club")
                );
                managers.add(manager);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return managers;
    }

    @Override
    public void updateManager(Manager manager) throws SQLException {
        try {

            preparedStatement = conn.prepareStatement("UPDATE Manager SET id_manager`=?,associated_club`=?,`email`=?,`nom`=?,`prenom`=? WHERE id_manager = ?;");
            preparedStatement.setLong(1, manager.getId());
            preparedStatement.setString(2, manager.getAssociatedClub());
            preparedStatement.setString(3, manager.getEmail());
            preparedStatement.setString(4, manager.getNom());
            preparedStatement.setString(5, manager.getPrenom());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void deleteManager(long idManager) throws SQLException {
        try {
            preparedStatement = conn.prepareStatement("DELETE FROM `manager` WHERE  id_manager = ?;");
            preparedStatement.setLong(1, idManager);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}