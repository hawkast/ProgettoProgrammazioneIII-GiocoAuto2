package com.example.giocoAuto;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;



/**
 La classe MenuStart è un controller per la schermata di avvio del gioco.
 Gestisce gli eventi dell'interfaccia utente e comunica con gli oggetti correlati per avviare il gioco.
 */
public class MenuStart {
    @FXML
    private Label messageLabel;

    private Stage primaryStage;
    private Stage childStage;
    private GameController gameController;
private Login login;



    /**
     * Imposta il riferimento alla stage primaria.
     * @param primaryStage La stage primaria da impostare.
     */
@FXML

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    /**
     * Gestisce l'evento di click sul pulsante di avvio del gioco.
     * Controlla se l'utente ha effettuato l'accesso e avvia la schermata di gioco.
     * @throws IOException Se si verifica un errore durante il caricamento della schermata di gioco.
     */
@FXML
    private void handleStartButtonAction() throws IOException {
        if (ControllerLogin.getInstance().isLogin()) {
            GameManager.getInstance().resume();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ControllerPartita.fxml"));
            Parent root=loader.load();
            ControllerPartita controller = loader.getController();
            controller.setPrimaryStage(primaryStage);
          childStage = new Stage();
            childStage.setScene(new Scene(root));
            StageManager.getInstance().setGame(primaryStage);
            primaryStage.close();
            controller.setCorrente(childStage);
            childStage.showAndWait();

        } else {
            System.out.println("Devi effettuare l'accesso per avviare il gioco!");
            messageLabel.setText("Devi effettuare l'accesso per avviare il gioco!");


        }
    }

    /**
     * Gestisce l'evento di click sul pulsante di accesso.
     * Apre la finestra di accesso.
     * @throws IOException Se si verifica un errore durante il caricamento della finestra di accesso.
     */
    @FXML
    public void handeLoginButton()throws IOException{

        FXMLLoader loader =new FXMLLoader(getClass().getResource("LoginWindow.fxml"));
        Parent root=loader.load();
        LoginWindow logWi =loader.getController();
        logWi.setPrimaryStage(primaryStage);
        Stage loginStage=new Stage();
        loginStage.setScene(new Scene(root));
        loginStage.showAndWait();

    }
    /**
     * Gestisce l'evento di click sul pulsante di registrazione.
     * Apre la finestra di registrazione.
     * @throws IOException Se si verifica un errore durante il caricamento della finestra di registrazione.
     */
    @FXML
    public void handSignButton() throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("SignInWindow.fxml"));
        Parent root=loader.load();
        SignInWindow signWi=loader.getController();
        signWi.setPrimaryStage(primaryStage);
        Stage signStage=new Stage();
        signStage.setScene(new Scene(root));
        signStage.showAndWait();
    }
    /**
     * Inizializza il controller e gli oggetti correlati.
     * @throws IOException Se si verifica un errore durante la creazione degli oggetti correlati.
     */
    public void initialize() throws IOException {
login=new Login();
gameController=new GameController();
gameController.setprimaryStage(primaryStage);



}}