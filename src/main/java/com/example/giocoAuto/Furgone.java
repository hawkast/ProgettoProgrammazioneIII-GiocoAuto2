package com.example.giocoAuto;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
/**
 * La classe Furgone rappresenta un furgone nel gioco.
 */
public class Furgone extends Auto implements AutoState {
    private final ImageView furgoneImageView;
    private final Image image2;

    /**
     * Costruttore della classe Furgone.
     * @param grid         Il GridPane in cui viene posizionato il furgone.
     * @param cars         L'immagine dell'auto.
     * @param imageFurgone L'immagine del furgone.
     */
    public Furgone(GridPane grid, ImageView cars ,Image imageFurgone) {
        super(grid,cars);
       this.image2=imageFurgone;
        System.out.println("GridPane: " + grid);
        System.out.println("ImageView: " + cars);
        System.out.println("url"+imageFurgone);


furgoneImageView = new ImageView(image2);

    }
    /**
     * Restituisce l'immagine del furgone.
     * @return L'immagine del furgone.
     */
    public ImageView getImageView() {

        return furgoneImageView;
    }


    /**
     * Restituisce l'immagine del furgone.
     * @return L'immagine del furgone.
     */
    @Override
    public ImageView getImage() {
       return  furgoneImageView;
    }

}
