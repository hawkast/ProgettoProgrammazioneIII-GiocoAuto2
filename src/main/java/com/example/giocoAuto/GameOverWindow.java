package com.example.giocoAuto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
/**
 La classe GameOverWindow gestisce la finestra di Game Over del gioco.
 */
public class GameOverWindow {
    private Stage primaryStage;
    /**
     Imposta il primaryStage per la finestra di Game Over.
     @param primaryStage Il primaryStage da impostare.
     */
    @FXML
    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage=primaryStage;
    }

    /**
     Gestisce l'evento di ritorno al menu principale.
     @param event L'evento che ha scatenato l'azione.
     */
    public void handlereturnMenu(ActionEvent event) {
        Stage corrente=(Stage) ((Node) event.getSource()).getScene().getWindow();
        corrente.close();
StageManager.getInstance().getPrimary().show();


    }
    /**
     Inizializza la finestra di Game Over.
     Questo metodo viene chiamato automaticamente dopo il caricamento del file FXML.
     */
    public void initialize() {

    }

}
