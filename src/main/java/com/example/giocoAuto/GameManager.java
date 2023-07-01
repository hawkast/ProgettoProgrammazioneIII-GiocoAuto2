package com.example.giocoAuto;
/**
 Il GameManager è responsabile della gestione dello stato di gioco e delle operazioni correlate.
 */
public class GameManager {
    private static GameManager instance = new GameManager();
    private boolean isPaused = false;

    private GameManager() {}
    /**
     Restituisce l'istanza del GameManager.
     @return L'istanza del GameManager.
     */
    public static GameManager getInstance() {
        return instance;
    }
    /**
     Verifica se il gioco è in pausa.
     @return True se il gioco è in pausa, altrimenti False.
     */
    public boolean isPaused() {
        return isPaused;
    }
    /**
     Imposta lo stato di pausa del gioco.
     @param paused True per mettere in pausa il gioco, False altrimenti.
     */
    public void setPaused(boolean paused) {
        isPaused = paused;
    }
    /**
     Mette in pausa il gioco.
     Ferma tutte le attività in corso.
     */
    public void pause() {
        isPaused = true;
        // ferma tutte le attività in corso
        // ...
    }
    /**
     Riprende il gioco.
     Riprende tutte le attività del gioco.
     */
    public void resume() {
        isPaused = false;
        // riprende tutte le attività del gioco
        // ...
    }
    /**
     Ferma il gioco.
     Ferma tutte le attività del gioco e ripristina le impostazioni iniziali.
     */
    public void stop() {
        isPaused = false;
        // ferma tutte le attività del gioco e ripristina le impostazioni iniziali
        // ...
    }

}
