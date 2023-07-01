package com.example.giocoAuto;


import javafx.scene.image.ImageView;
/**
 L'interfaccia AutoState rappresenta lo stato di un oggetto Auto.
 Definisce un metodo per ottenere l'immagine associata allo stato.
 */
public interface AutoState {
    /**
     Restituisce l'immagine associata allo stato dell'oggetto Auto.
     @return L'ImageView che rappresenta l'immagine dello stato.
     */
    ImageView getImage();

}
