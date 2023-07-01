package com.example.giocoAuto;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
/**
 La classe Factory rappresenta una fabbrica per la creazione di oggetti utilizzati nel gioco.
 */
public class Factory {
    private Furgone furgone;
    private Ostacoli ostacoli;
    private TestOb ob;

    @FXML
    private GridPane grid;
    private Auto auto;
    private GameController gameController;
    @FXML
    private Label lifepointsLab;
    @FXML
    private Label countLabel;
    @FXML
    private Label errorLab;
    private Camion camion;


    /**
     * Costruttore della Factory che accetta i parametri per inizializzare gli oggetti.
     * @param grid     Il GridPane utilizzato nel gioco.
     * @param auto     L'oggetto Auto utilizzato nel gioco.
     * @param ob       L'oggetto TestOb utilizzato nel gioco.
     * @param furgone  L'oggetto Furgone utilizzato nel gioco.
     * @param ostacoli L'oggetto Ostacoli utilizzato nel gioco.
     * @param camion   L'oggetto Camion utilizzato nel gioco.
     */
    public Factory(GridPane grid, Auto auto, TestOb ob, Furgone furgone, Ostacoli ostacoli, Camion camion) {
        this.grid = grid;

        this.auto = auto;
        this.ob = ob;
        this.furgone = furgone;
        this.ostacoli = ostacoli;
        this.camion = camion;

    }
    /**
     * Costruttore della Factory che accetta un oggetto GameController.
     * @param gameController L'oggetto GameController utilizzato nel gioco.
     */
    public Factory(GameController gameController) {
        this.gameController = gameController;
    }
    /**
     * Costruttore della Factory che accetta le etichette per i punti vita, il conteggio e gli errori.
     * @param countLabel     L'etichetta per il conteggio.
     * @param errorLab       L'etichetta per gli errori.
     * @param lifepointsLab  L'etichetta per i punti vita.
     */
    public Factory(Label countLabel, Label errorLab, Label lifepointsLab) {
        this.lifepointsLab = lifepointsLab;
        this.countLabel = countLabel;
        this.errorLab = errorLab;

    }
    /**
     * Restituisce il GridPane creato dalla Factory.
     * @return Il GridPane creato.
     */
    public GridPane createGrid() {
        return grid;
    }
    /**
     * Restituisce il GameController creato dalla Factory.
     * @return Il GameController creato.
     */
    public GameController createGameController() {
        return gameController;
    }
    /**
     * Restituisce il Camion creato dalla Factory.
     * @return Il Camion creato.
     */
    public Camion createCamion() {
        return camion;
    }
    /**
     * Restituisce il Furgone creato dalla Factory.
     * @return Il Furgone creato.
     */
    public Furgone createFurgone() {
        return furgone;
    }
    /**
     * Restituisce l'Auto creata dalla Factory.
     * @return L'Auto creata.
     */
    public Auto createAuto() {
        return auto;
    }
    /**
     * Restituisce gli Ostacoli creati dalla Factory.
     * @return Gli Ostacoli creati.
     */
    public Ostacoli createOstacoli() {
        return ostacoli;
    }
    /**
     * Restituisce il TestOb creato dalla Factory.
     * @return Il TestOb creato.
     */
    public TestOb createTestOb() {
        return ob;
    }
    /**
     * Restituisce l'etichetta per il conteggio creata dalla Factory.
     * @return L'etichetta per il conteggio creata.
     */
    public Label CreateCountLabel() {
        return countLabel;
    }
    /**
     * Restituisce l'etichetta per gli errori creata dalla Factory.
     * @return L'etichetta per gli errori creata.
     */
    public Label createErrorLab() {
        return errorLab;
    }
    /**
     * Restituisce l'etichetta per i punti vita creata dalla Factory.
     * @return L'etichetta per i punti vita creata.
     */
    public Label createLifepointsLab() {
        return lifepointsLab;
    }





}


