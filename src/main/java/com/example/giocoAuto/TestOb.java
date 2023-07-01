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
import java.util.Random;
import java.util.ArrayList;

/**
 La classe TestOb rappresenta un oggetto di test utilizzato nel gioco.
 Gestisce la creazione e lo spostamento casuale dell'oggetto all'interno della griglia.
 */
public class TestOb {
    private ArrayList<ImageView> imageViewsList = new ArrayList<>();

    private ImageView newImageView;
    private GridPane grid;
    private Random random2;
    private  Factory factory;

    private Auto auto;
    private Ostacoli ostacolo;
    /**
     * Costruttore della classe TestOb.
     * @param grid La griglia in cui posizionare l'oggetto di test.
     */
    public TestOb(GridPane grid) {
        this.grid = grid;
        this.random2 = new Random();
    }
    /**
     * Crea un nuovo oggetto di test e lo posiziona all'interno della griglia.
     */
public void Creaob(){

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
            if(GameManager.getInstance().isPaused()){return;}
            InputStream oggettiImage=getClass().getResourceAsStream("/com/example/giocoAuto/images/coin.jpg");
            Image imageOggetti=new Image(oggettiImage);
           newImageView = new ImageView(imageOggetti);
            // Aggiungi l'oggetto ImageView alla lista
            newImageView.setFitHeight(50);           // misura auto in altezza
            newImageView.setFitWidth(45);   //  in larghezza
GridPane.setHalignment(newImageView, HPos.CENTER);  // allinea al centro orizzontalmente
GridPane.setValignment(newImageView, VPos.CENTER);  // verticalmente sempre allinea al centro
            imageViewsList.add(newImageView);

            int colIndex2 = random2.nextInt(grid.getColumnCount());
            int rowindex=0;
            ArrayList<ImageView> imageViewsList = ostacolo.getImageOS();
            for(ImageView ostacoloIm : imageViewsList){
                int ostacoloCol = GridPane.getColumnIndex(ostacoloIm);
            int ostacolorow = GridPane.getRowIndex(ostacoloIm);
            if (ostacoloCol == colIndex2 && ostacolorow == rowindex) {
                // La nuova cella contiene già un oggetto, quindi passa alla prossima cella casuale
                colIndex2 = random2.nextInt(grid.getColumnCount());
                ostacoloIm = imageViewsList.get(0); // Ricomincia a controllare dalla prima cella
            }
        }
            // Aggiungi l'oggetto ImageView alla scena o al contenitore
           grid.add(newImageView,colIndex2,rowindex);
    }));
    timeline.setCycleCount(1);
    timeline.play();

    }
    /**
     * Imposta la Factory utilizzata per creare l'oggetto di test e gestire l'auto e gli ostacoli.
     * @param factory La Factory da utilizzare.
     */
  public void set(Factory factory){
       this.factory=factory;
       auto=factory.createAuto();
       ostacolo=factory.createOstacoli();
  }

    /**
     * Restituisce la lista di ImageView degli oggetti di test.
     * @return La lista di ImageView degli oggetti di test.
     */
    public ArrayList<ImageView> getImageOb() {
        return imageViewsList;
    }
    /**
     * Sposta casualmente gli oggetti di test all'interno della griglia.
     */
    public void spostaOggettiCasualmente() {

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(3), event -> {
            if(GameManager.getInstance().isPaused()){return;}
        Random random = new Random();
        for (ImageView imageView : imageViewsList) {
            int colIndex = GridPane.getColumnIndex(imageView);
            int rowIndex = GridPane.getRowIndex(imageView);
            // Rimuovi l'oggetto ImageView dalla sua posizione attuale nella GridPane
            grid.getChildren().remove(imageView);
            // Sposta l'oggetto ImageView in una nuova posizione casuale di una sola cella
            int[] directions = {  1 }; // possibili direzioni di movimento
            int randomDirectionIndex = random.nextInt(directions.length);
            int direction = directions[randomDirectionIndex];
            int newColIndex = (colIndex+ direction + grid.getColumnCount()) % grid.getColumnCount();
            int newRowIndex = (rowIndex + direction + grid.getRowCount()) % grid.getRowCount();
            ArrayList<ImageView> imageViewsList = ostacolo.getImageOS();
            for (ImageView imageobs : imageViewsList) {
                int ostacolooCol = GridPane.getColumnIndex(imageobs);
                int ostacoloRow = GridPane.getRowIndex(imageobs);
                if (ostacolooCol == newColIndex && ostacoloRow == newRowIndex) {
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
            grid.getChildren().remove(imageView);
            grid.add(imageView, newColIndex, newRowIndex);
        }
    }
                ));
                timeline.setCycleCount(1);
                timeline.play();}
}

