package com.example.giocoAuto;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
/**
 La classe Ostacoli gestisce la generazione e il movimento degli ostacoli nel gioco.
 Utilizza un oggetto GridPane per posizionare gli ostacoli all'interno della griglia di gioco.
 */
public class Ostacoli  {
    private ArrayList<ImageView> imageViewsListOstacoli = new ArrayList<>();

    private ImageView ostacoloviews;
    private GridPane grid;
    private Random randomOs;
    private TestOb ob;
    private Auto auto;
    private Factory factory;
    /**
     * Costruttore della classe Ostacoli.
     * @param grid La griglia di gioco in cui posizionare gli ostacoli.
     */
    public Ostacoli(GridPane grid){
        this.grid=grid;
        this.randomOs=new Random();

    }
    /**
     * Genera un nuovo ostacolo e lo posiziona nella griglia di gioco.
     * Se il gioco è in pausa, l'ostacolo non viene generato.
     */
    public void creaOstacolo() {

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
        if (GameManager.getInstance().isPaused()) {
            return;
        }
        InputStream ostacoloImage = getClass().getResourceAsStream("/com/example/giocoAuto/images/ostacolo.jpg");
        Image imageOstacolo = new Image(ostacoloImage);
        ostacoloviews = new ImageView(imageOstacolo);
        // Aggiungi l'oggetto ImageView alla lista
        ostacoloviews.setFitHeight(50);           // misura auto in altezza
        ostacoloviews.setFitWidth(45);   //  in larghezza
        GridPane.setHalignment(ostacoloviews, HPos.CENTER);  // allinea al centro orizzontalmente
        GridPane.setValignment(ostacoloviews, VPos.CENTER);  // verticalmente sempre allinea al centro
        imageViewsListOstacoli.add(ostacoloviews);

        int colIndex2 = randomOs.nextInt(grid.getColumnCount());
        int newRowIndex = 0;
        ArrayList<ImageView> imageViewsList = ob.getImageOb();
        for (ImageView imageView : imageViewsList) {
            int oggettoCol = GridPane.getColumnIndex(imageView);
            int oggettoRow = GridPane.getRowIndex(imageView);
            if (oggettoCol == colIndex2 && oggettoRow == newRowIndex) {
                // La nuova cella contiene già un oggetto, quindi passa alla prossima cella casuale
                colIndex2 = randomOs.nextInt(grid.getColumnCount());
                imageView = imageViewsList.get(0); // Ricomincia a controllare dalla prima
            }
        }
        // Aggiungi l'ostacolo ImageView nella nuova posizione nella GridPane

        grid.add(ostacoloviews, colIndex2, newRowIndex);
        }));
        timeline.setCycleCount(1);
        timeline.play();

    }





    /**
     * Imposta la factory per la creazione dell'auto e dell'oggetto di test.
     * @param factory La factory da utilizzare per creare l'auto e l'oggetto di test.
     */
    public void set(Factory factory ){
    this.factory=factory;
    auto=factory.createAuto();
    ob=factory.createTestOb();}
    /**
     * Restituisce la lista di ImageView degli ostacoli.
     * @return La lista di ImageView degli ostacoli.
     */
    public ArrayList<ImageView> getImageOS() {
        return imageViewsListOstacoli;

    }

    /**
     * Muove gli ostacoli nella griglia di gioco.
     */
    public void MuoviOstacoli(){
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(3), event -> {
        Random random = new Random();
        for (ImageView imageView : imageViewsListOstacoli) {
            int colIndex = GridPane.getColumnIndex(imageView);
            int rowIndex = GridPane.getRowIndex(imageView);
            // Rimuovi l'ostacolo ImageView dalla sua posizione attuale nella GridPane
            grid.getChildren().remove(imageView);

            // Sposta l'oggetto ImageView in una nuova posizione casuale di una sola cella
            int[] directions = {  0, 1, }; // possibili direzioni di movimento
            int randomDirectionIndex = random.nextInt(directions.length);
            int direction = directions[randomDirectionIndex];
            int newColIndex = (colIndex+ direction + grid.getColumnCount()) % grid.getColumnCount();
            int newRowIndex = (rowIndex + direction + grid.getRowCount()) % grid.getRowCount();
            ArrayList<ImageView> imageViewsList = ob.getImageOb();
            for (ImageView imageob : imageViewsList) {
                int oggettoCol = GridPane.getColumnIndex(imageob);
                int oggettoRow = GridPane.getRowIndex(imageob);
                if (oggettoCol == newColIndex && oggettoRow == newRowIndex) {
                    // La nuova cella contiene già un oggetto, quindi passa alla prossima cella casuale
                     newColIndex = (colIndex+ direction + grid.getColumnCount()) % grid.getColumnCount();
                     newRowIndex = (rowIndex + direction + grid.getRowCount()) % grid.getRowCount();
                }
            }
            int autoC=GridPane.getColumnIndex(auto.getImmageView());
            int autoR=GridPane.getRowIndex(auto.getImmageView());
            if(autoR==newRowIndex && autoC==newColIndex){

return;
            }

            // Aggiungi l'oggetto ImageView nella nuova posizione nella GridPane
            grid.add(imageView, newColIndex, newRowIndex);

        }
    }

                ));
                timeline.setCycleCount(1);
                timeline.play();}


}



