/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.Casino.Games.Bingo;

import Casino.DataBase.User;
import ch.bbbaden.Casino.Main;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Emirhan Karaca
 */
public class OverviewPageController implements Initializable {

    private Main mainApp;
    private OverviewPageViewModel overviewPageModel;
    private User user;

    @FXML
    private Label lblCredits;

    @FXML
    private Label imgLeave;

    public void setOverviewPageViewModel(OverviewPageViewModel overviewPageModel) {
        this.overviewPageModel = overviewPageModel;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ActionStart(ActionEvent event) throws IOException {

        overviewPageModel.setMainApp(mainApp);

        overviewPageModel.showCardSelection();

    }

    public void bind() {

    }

    @FXML
    private void ActionLeave(MouseEvent event) throws IOException, SQLException, ClassNotFoundException {
        Stage stage = (Stage) imgLeave.getScene().getWindow();
        stage.close();

        mainApp.startMenu();

    }

    @FXML
    private void ActionRules(ActionEvent event) {

        JOptionPane.showMessageDialog(null,
                "Die Teilnehmer kaufen Lose oder auch Teilnahmecoupons, \n "
                + "die mit einer Anzahl von Zahlen bedruckt sind. \n "
                + "Ein Conférencier zieht wie beim Lotto per Zufall \naus einer Trommel Kugeln  "
                + "mit aufgedruckten  Zahlen. Er ruft diese Zahlen aus, woraufhin \n "
                + "die Teilnehmer sie auf ihren Spielkarten mit einem Farbstempel oder einem Stein markieren – sofern \n "
                + "sie die ausgerufenen Zahlen auf ihrem Teilnahmecoupon haben. \n "
                + "Sobald der erste Teilnehmer auf seinem Los alle 5 Zahlen einer waagerechten oder senkrechten Reihe oder Diagonale markieren konnte, \n "
                + "ruft er laut und vernehmlich „Bingo!“. […] Nach der Überprüfung \n seines Spielscheins ist er der Gewinner, erhält einen Sach- oder Geldpreis, \n "
                + "und die Spielrunde ist abgeschlossen.»",
                "Regeln des Bingo-Spiels",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;

    }

    public void setUser(User user) {
        this.user = user;
        lblCredits.setText(Double.toString(user.getCredit()));
    }

}
