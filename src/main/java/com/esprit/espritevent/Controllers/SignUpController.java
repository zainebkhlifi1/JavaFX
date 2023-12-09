package com.esprit.espritevent.Controllers;

import com.esprit.espritevent.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    public Button go_back_login_fid;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        go_back_login_fid.setOnAction(event -> {
            Stage stage = (Stage) go_back_login_fid.getScene().getWindow();
            Model.getInstance().getViewFactroy().closeStage(stage);
            Model.getInstance().getViewFactroy().showLoginWindow();
        });

    }

}
