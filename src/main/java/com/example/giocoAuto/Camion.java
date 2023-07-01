package com.example.giocoAuto;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
/**
 La classe Camion rappresenta un oggetto di tipo Camion nel gioco.
 Estende la classe Auto e implementa l'interfaccia AutoState.
 Contiene un'immagine associata al camion e metodi per ottenere l'immagine del camion.
 */
public class Camion  extends Auto implements AutoState{
    private Image image;
    private ImageView camionImage;
    /**
     Costruisce un oggetto Camion con la GridPane, l'ImageView e l'immagine specificati.
     @param grid La GridPane che rappresenta la griglia di gioco.
     @param cars L'ImageView che rappresenta la macchina del camion.
     @param imageCamion L'immagine associata al camion.
     */
    public Camion(GridPane grid, ImageView cars, Image imageCamion) {
        super(grid, cars);
        this.image=imageCamion;
        camionImage=new ImageView(image);

    }
    /**
     Restituisce l'ImageView associata all'immagine del camion.
     @return L'ImageView che rappresenta l'immagine del camion.
     */
    public ImageView getCamionImage(){
        return camionImage;
    }

    /**
     Restituisce l'ImageView associata all'immagine del camion.
     Implementazione del metodo dell'interfaccia AutoState.
     @return L'ImageView che rappresenta l'immagine del camion.
     */
    @Override
    public ImageView getImage() {
        return camionImage;
    }


}
