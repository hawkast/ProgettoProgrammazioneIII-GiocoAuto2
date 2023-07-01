package com.example.giocoAuto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
/**
 La classe LoginWindow è un controller per la finestra di accesso del gioco.
 Gestisce gli eventi dell'interfaccia utente e comunica con l'oggetto Login per effettuare l'accesso.
 Implementa l'interfaccia Observer per ricevere notifiche dall'oggetto LoginObservable.
 */
public class LoginWindow {

    private Stage primaryStage;
    private Login login;

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private Label messageLabel;
    private GameController gamecontroller;
    private String firstName;
    private  String lastName;
   // private LoginObservable loginObservable = new LoginObservable();

//////////////DA CANCELLAREEEE//////////////////////////
    /**
     * Imposta il riferimento alla stage primaria.
     * @param primaryStage La stage primaria da impostare.
     */
    @FXML
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    /**
     * Imposta il riferimento al GameController.
     * @param gamecontroller Il GameController da impostare.
     */
    @FXML
    public void setGamecontroller(GameController gamecontroller) {
     this.gamecontroller=gamecontroller;
    }
    /**
     * Gestisce l'evento di click sul pulsante di accesso.
     * Verifica le credenziali inserite e avvia la schermata successiva se l'accesso è consentito.
     * @param event L'evento di ActionEvent associato al pulsante di accesso.
     * @throws IOException Se si verifica un errore durante il caricamento della schermata successiva.
     */
    @FXML
    private void handleLoginButtonAction(ActionEvent event) throws IOException {
        firstName = firstNameField.getText().trim();
        lastName = lastNameField.getText().trim();
        if (firstName.isEmpty() || lastName.isEmpty())
        {
            messageLabel.setText("i campi sono vuoti!");
        }
        if (login.login(firstName, lastName)) {
           FXMLLoader loa= new FXMLLoader(getClass().getResource("ControllerPartita.fxml"));
           Parent root=loa.load();
           ControllerPartita hello=loa.getController();
           hello.setFirstName(firstName);
hello.setLastName(lastName);
            Stage corrente=(Stage) ((Node) event.getSource()).getScene().getWindow();
           corrente.close();
            primaryStage.show();
          ControllerLogin.getInstance().log();
            // Avvia la schermata successiva
        } else {
          messageLabel.setText("Credenziali errate!");
         ControllerLogin.getInstance().nolog();


        }
    }
    /**
     * Inizializza il controller e l'oggetto Login.
     * @throws IOException Se si verifica un errore durante la creazione dell'oggetto Login.
     */
    public void initialize() throws IOException {
     //  loginObservable.addObserver(this);
           setGamecontroller(gamecontroller);
        login = new Login();
        login.setGamecontroller(gamecontroller);

    }


}