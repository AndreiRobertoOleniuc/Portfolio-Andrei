/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.Casino.Login;

import Casino.DataBase.Query;
import Casino.DataBase.User;
import ch.bbbaden.Casino.Main;
import ch.bbbaden.Casino.Menu.Model.Model;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author Andrei Oleniuc 
 */
public class LoginViewController implements Initializable {

    @FXML
    private Button btnSubmit;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button close;

    private String email = "";
    private String password = "";
    private final Query sql = new Query();
    private ArrayList<User> benutzer = new ArrayList<>();
    private Model mo;
    private Main mainApp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            sql.updateUser();
        } catch (SQLException ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < sql.getUsers().size(); i++) {
            benutzer.add(sql.getUsers().get(i));
        }

        try {
            sql.selectUsers();
        } catch (SQLException ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void loginaction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        benutzer.clear();
        sql.updateUser();
        String AllowedChars = "[a-zA-Z0-9@.]*";
        boolean invalidChecker = false;

        for (int i = 0; i < sql.getUsers().size(); i++) {
            benutzer.add(sql.getUsers().get(i));
        }

        //Check email for unallowed chars
        try {
            if (txtUsername.getText().matches(AllowedChars)) {
                email = txtUsername.getText();
                invalidChecker = false;
            } else {
                JOptionPane.showMessageDialog(null, "Bitte geben Sie eine Mail ein!", "Fehler", JOptionPane.ERROR_MESSAGE);
                invalidChecker = true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Bitte verwenden Sie nur Buchstaben und Zahlen!", "Fehler", JOptionPane.ERROR_MESSAGE);
            invalidChecker = true;
        }

        try {
            if (txtPassword.getText().matches(AllowedChars)) {
                password = txtPassword.getText();
                invalidChecker = false;
            } else {
                JOptionPane.showMessageDialog(null, "Bitte geben Sie ein Passwort ein!", "Fehler", JOptionPane.ERROR_MESSAGE);
                invalidChecker = true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Bitte verwenden Sie nur Buchstaben und Zahlen!", "Fehler", JOptionPane.ERROR_MESSAGE);
            invalidChecker = true;
        }

        if (invalidChecker == false) {
            for (int i = 0; i < sql.getUsers().size(); i++) {
                if (benutzer.get(i).getEmail().equals(email)) {
                    if (BCrypt.checkpw(password, benutzer.get(i).getPassword())) {
                        mainApp.setUser(benutzer.get(i));
                        mainApp.startMenu();
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Email oder Passwort ist falsch.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    @FXML
    private void signUp(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        mainApp.registerStart();

    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}
