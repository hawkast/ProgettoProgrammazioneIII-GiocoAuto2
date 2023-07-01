package com.example.giocoAuto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


/**
 La classe SignInWindow rappresenta la finestra di registrazione nel gioco.
 Gestisce l'interazione dell'utente con la finestra e le azioni associate al pulsante di registrazione.
 */
public class SignInWindow {
    private Stage primaryStage;
    private Login login;
    @FXML
    private TextField firstNameFieldR;
            @FXML
    private TextField lastNameFieldR;
            @FXML
    private Label messageLabR;

    /**
     * Imposta il primaryStage della finestra.
     * @param primaryStage Il primaryStage della finestra.
     */
    @FXML
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;}
    /**
     * Gestisce l'evento di clic sul pulsante di registrazione.
     *
     * @param event L'evento di clic sul pulsante.
     * @throws IOException Se si verifica un'eccezione durante la registrazione.
     */
        @FXML
              private void handleSignInButtonAction(ActionEvent event) throws IOException{
            login=new Login();
        String firstName=firstNameFieldR.getText().trim();
        String lastName=lastNameFieldR.getText().trim();
if (firstName.isEmpty() || lastName.isEmpty())
{messageLabR.setText("i campi sono vuoti!");}

     else if(login.register(firstName,lastName)){

            messageLabR.setText("Registrazione effettuata con successo!");
            Stage corrente= (Stage) ((Node) event.getSource()).getScene().getWindow();
            corrente.close();
            primaryStage.show();
        }
        else {
            messageLabR.setText("Utente già registrato!");
        }

    }
}
