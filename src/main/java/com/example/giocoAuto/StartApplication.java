package com.example.giocoAuto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Lorenzo Guerrini
 * @author Pasquale Marzocchi
 */

/**
 La classe StartApplication è la classe di avvio dell'applicazione.
 Estende la classe Application di JavaFX e implementa il metodo start(),
 che avvia l'interfaccia grafica del gioco.
 */
public class StartApplication extends Application {
    /**
     * Il metodo start() avvia l'interfaccia grafica del gioco.
     * @param primaryStage Lo Stage principale dell'applicazione.
     * @throws IOException Se si verifica un errore durante il caricamento del file FXML.
     */
    @Override

    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("MenuStart.fxml"));
        Parent root=fxmlLoader.load();
        MenuStart menu =fxmlLoader.getController();
        menu.setPrimaryStage(primaryStage);
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Hello!");
StageManager.getInstance().setPrimaryStage(primaryStage);
        primaryStage.show();
       primaryStage.setResizable(false);
    }

    /**
     * Il metodo main() avvia l'applicazione.
     * @param args Gli argomenti della riga di comando.
     */
    public static void main(String[] args) {

        launch();

    }
}