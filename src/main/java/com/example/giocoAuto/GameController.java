package com.example.giocoAuto;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.InputStream;
import java.util.ArrayList;
/**
 * La classe GameController gestisce il gioco e fornisce metodi per interagire con gli oggetti del gioco.
 */
public class GameController   {

    private Factory factory_Class;
    private Factory factory_Object;
    private Timeline timeline;
    private Stage primaryStage;
    /**
     * Costruttore della classe GameController.
     * @param factory_Class   L'istanza della Factory per le classi.
     * @param factory_Object  L'istanza della Factory per gli oggetti.
     */
    public GameController(Factory factory_Class,Factory factory_Object){

this.factory_Class=factory_Class;
this.factory_Object=factory_Object;

    }
    /**
     * Costruttore vuoto della classe GameController.
     */
    public GameController() {

    }

    /**
     * Avvia il gioco.
     */
    public void iniziaGioco(){
        timeline = new Timeline(new KeyFrame(Duration.seconds(3.5), e -> {

            if (GameManager.getInstance().isPaused()) {
                return;
            }
          factory_Class.createTestOb().Creaob();
         factory_Class.createOstacoli().creaOstacolo();
      factory_Class.createAuto().moveToClosestObject2();
        factory_Class.createTestOb().spostaOggettiCasualmente();
           factory_Class.createOstacoli().MuoviOstacoli();

        }));
        timeline.setCycleCount(Animation.INDEFINITE);
      start();
    }
   //METODI GETTER
    /**
     * Restituisce la posizione dell'auto nel formato [x, y].
     * @return La posizione dell'auto.
     */
    public int[] getPositionAuto(){
       int posX=GridPane.getRowIndex(factory_Class.createAuto().getImmageView());
       int posY=GridPane.getColumnIndex(factory_Class.createAuto().getImmageView());
       return new int[]{posY, posX};

    }
    /**
     * Restituisce l'elenco delle immagini degli oggetti nel gioco.
     * @return L'elenco delle immagini degli oggetti.
     */
    public ArrayList<ImageView> GetImOggetti(){
      return   factory_Class.createTestOb().getImageOb();

    }
    /**
     * Restituisce l'elenco delle immagini degli ostacoli nel gioco.
     * @return L'elenco delle immagini degli ostacoli.
     */
    public ArrayList<ImageView> getImOstacoli(){
        return factory_Class.createOstacoli().getImageOS();
    }
    /**
     * Restituisce l'effetto di colore dell'auto.
     * @return L'effetto di colore dell'auto.
     */
    public ColorAdjust getColorAut(){
    Effect effect=factory_Class.createAuto().getColorAuto();
    return (ColorAdjust) effect;
    }
    /**
     * Ferma il gioco.
     */
    public void GetStop(){
        timeline.stop();
    }
    /**
     * Avvia il gioco.
     */
    public void start(){
        timeline.play();
    }
    /**
     * Restituisce il punteggio di vita dell'auto.
     * @return Il punteggio di vita dell'auto.
     */
    public int getLif(){
       return factory_Class.createAuto().getLifepoint();

    }
    /**
     * Restituisce l'etichetta dei punti vita.
     * @return L'etichetta dei punti vita.
     */
    public Label getLabLife(){
        return factory_Object.createLifepointsLab();
    }
    /**
     * Restituisce l'etichetta del conteggio.
     * @return L'etichetta del conteggio.
     */
    public Label getLabConta(){
        return factory_Object.CreateCountLabel();
    }
    /**
     * Restituisce il valore del conteggio.
     * @return Il valore del conteggio.
     */
    public int getConta(){
        return factory_Class.createAuto().getContatore();
    }



   // METODI SETTER
    //
    /**
     * Imposta lo Stage principale.
     * @param primaryStage Lo Stage principale.
     */
    public void setprimaryStage(Stage primaryStage){ this.primaryStage=primaryStage;}
    /**
     * Imposta la posizione degli oggetti nel gioco.
     * @param x La coordinata x.
     * @param y La coordinata y.
     */
    public void setPositionOggetti( int x ,int y){
        ArrayList<ImageView> oggetti=factory_Class.createTestOb().getImageOb();
        InputStream oggettoImage=getClass().getResourceAsStream("/com/example/giocoAuto/images/coin.jpg");
        Image imageOggetto=new Image(oggettoImage);
        ImageView oggetto=new ImageView(imageOggetto);
        oggetto.setFitHeight(50);           // misura auto in altezza
        oggetto.setFitWidth(45);   //  in larghezza
        GridPane.setHalignment(oggetto, HPos.CENTER);  // allinea al centro orizzontalmente
        GridPane.setValignment(oggetto, VPos.CENTER);  // verticalmente sempre allinea al centro
        oggetti.add(oggetto);

        factory_Class.createGrid().add(oggetto,y,x);//}
    }
    /**
     * Imposta la posizione degli ostacoli nel gioco.
     * @param x La coordinata x.
     * @param y La coordinata y.
     */
    public void setPositionOstacoli(int x,int y){
        ArrayList<ImageView>ostacoli=factory_Class.createOstacoli().getImageOS();
        InputStream ostacoloImage=getClass().getResourceAsStream("/com/example/giocoAuto/images/ostacolo.jpg");
        Image imageOstacolo=new Image(ostacoloImage);
        ImageView ostacolo=new ImageView(imageOstacolo);
        ostacolo.setFitHeight(50);
        ostacolo.setFitWidth(45);
        GridPane.setHalignment(ostacolo,HPos.CENTER);
        GridPane.setValignment(ostacolo,VPos.CENTER);
        ostacoli.add(ostacolo);
        factory_Class.createGrid().add(ostacolo,y,x);
    }
    /**
     * Imposta la posizione dell'auto nel gioco.
     * @param x La coordinata x.
     * @param y La coordinata y.
     */
    public void setAutoPosition(int x, int y) {
        // modifica la posizione dell'auto in base ai parametri x e y
        // ad esempio:
        ImageView auto = factory_Class.createAuto().getImmageView();
        GridPane.setRowIndex(auto, y);
        GridPane.setColumnIndex(auto, x);
    }
    /**
     * Imposta l'etichetta del conteggio.
     * @param conta      La stringa del conteggio.
     * @param contatore  Il valore del conteggio.
     */
    public void setContalabel(String conta,int contatore){
        factory_Object.CreateCountLabel().setText(conta+contatore);

    }
    /**
     * Imposta il valore del conteggio.
     * @param cont Il valore del conteggio.
     */
    public void setConta(int cont){
        factory_Class.createAuto().setcontatore(cont);
    }
    /**
     * Imposta l'etichetta dei punti vita.
     * @param life L'etichetta dei punti vita.
     */
    public void setLifeLabel(String life){
        factory_Object.createLifepointsLab().setText(life);
    }
    /**
     * Imposta i punti vita dell'auto.
     * @param lifeP I punti vita dell'auto.
     */
    public void setLifepointS(int lifeP){
        factory_Class.createAuto().setLifepoint(lifeP);
    }
    /**
     * Imposta il colore dell'auto.
     * @param colorAdjust L'effetto di colore dell'auto.
     */
    public void setColorAuto (ColorAdjust colorAdjust){
        factory_Class.createAuto().getImmageView().setEffect(colorAdjust);
    }
    /**
     * Imposta l'immagine dell'auto in base allo stato.
     * @param stato Lo stato dell'auto (1 per Furgone, 2 per Camion).
     */
    public void setAutoIm(int stato){
        switch (stato){
            case 1-> factory_Class.createAuto().getImmageView().setImage(factory_Class.createFurgone().getImageView().getImage());
            case 2->factory_Class.createAuto().getImmageView().setImage(factory_Class.createCamion().getCamionImage().getImage());
        }
    }

    }


