package com.example.giocoAuto;

import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
/**
 La classe StageManager gestisce le istanze degli Stage nel gioco.
 È un singleton che consente di accedere alle istanze degli Stage da diverse parti dell'applicazione.
 */
public class StageManager {

    private static StageManager instance;

    private Stage primary;
    private Stage Game;

    private List<Stage> stages = new ArrayList<>();

    private StageManager() {
    }
    /**
     * Restituisce l'istanza singola del StageManager.
     * @return L'istanza del StageManager.
     */
    public static StageManager getInstance() {
        if (instance == null) {
            instance = new StageManager();
        }
        return instance;
    }
    /**
     * Imposta il primaryStage dell'applicazione.
     * @param primaryStage Il primaryStage dell'applicazione.
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primary = primaryStage;
    }

    /**
     * Imposta lo Stage del gioco.
     * @param gameStage Lo Stage del gioco.
     */
public void setGame(Stage gameStage)
{this.Game=gameStage;}
    /**
     * Restituisce il primaryStage dell'applicazione.
     * @return Il primaryStage dell'applicazione.
     */
    public Stage getPrimary(){return primary;}
    // ...

}
