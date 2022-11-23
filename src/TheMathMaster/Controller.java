package TheMathMaster;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.sql.*;
import java.io.IOException;
import java.net.URI;
import java.util.Objects;

public class Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private AnchorPane anchor;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private TextField tfUsername;

    @FXML
    private Label lStatus;

    @FXML
    private Label lUser;

    @FXML
    private Label lSignInError;

    @FXML
    private TextArea taMicrobit;


    @FXML
    void btnSignIn(ActionEvent event) {

        /**
         * Verify username and password with database on button click SignIn
         */
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mathmasterdb", "root", "Math123");
            String username = tfUsername.getText();
            String password = tfPassword.getText();
            Statement statement = connection.createStatement();
            String sql = "select * from account where username='"+username+"' and password='"+password+"'";
            ResultSet resultSet = statement.executeQuery(sql);

            if(resultSet.next()){
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("panels/controlPanel.fxml")));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                lSignInError.setTextFill(Color.web("#ff0000"));
                lSignInError.setText("Wrong username or password");
                tfUsername.setText("");
                tfPassword.setText("");
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Signs out of the accounts
     * @param event
     * @throws IOException
     */
    @FXML
    void btnSignOut(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("panels/signIn.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * TODO Opens up the website for password reset
     * @param event
     */
    @FXML
    void btnForgotPassword(ActionEvent event) {
        try {
            URI uri = new URI("http://www.google.com");
            java.awt.Desktop.getDesktop().browse(uri);
            System.out.println("The Math Master forgot password site opened");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSetupQuestions(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("panels/questionPanel.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void btnAdminPanel(ActionEvent event) throws  IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("panels/adminPanel.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    /**
     * Microbit connection, needs manual press on the button to check.
     * @param event
     */
    @FXML
    public void btnConnectMathMaster(ActionEvent event) {
        MicrobitDetect.waitForNotifying();
        System.out.println(MicrobitDetect.returnExampleBool());
        if (MicrobitDetect.returnExampleBool()) {
            lStatus.setTextFill(Color.web("#23eb00"));
            lStatus.setText("Connected");
            taMicrobit.setText("test");
        } else {
            lStatus.setTextFill(Color.web("#ff0000"));
        }
    }
}

