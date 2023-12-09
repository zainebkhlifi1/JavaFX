package com.esprit.espritevent.Controllers.Student;

import com.esprit.espritevent.Models.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentMenuController implements Initializable {

    public Button dashboard_btn;
    public Button new_club_proposal_fid;
    public Button event_available_fid;
    public Button profile_btn;
    public Button logout_btn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logout_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Model.getInstance().getViewFactroy().showLoginWindow();
                Model.getInstance().setAdminLoginSuccessFlag(false);
            }
        });
    }
}
