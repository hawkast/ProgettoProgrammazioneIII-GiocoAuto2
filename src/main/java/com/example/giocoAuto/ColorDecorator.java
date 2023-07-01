package com.example.giocoAuto;

import javafx.scene.effect.ColorAdjust;
/**
 La classe ColorDecorator rappresenta un decoratore per l'oggetto Auto.
 Implementa l'interfaccia AutoDecorator e aggiunge un effetto di colore all'immagine dell'auto.
 */
public class ColorDecorator implements AutoDecorator {
    /**
     Applica l'effetto di colore all'immagine dell'auto specificata.
     @param auto L'oggetto Auto da decorare.
     */
    @Override
    public void decorate(Auto auto) {
        ColorAdjust colorAdjust = new ColorAdjust();
        double randomHue = (Math.random() * 2) - 1; // genera un valore casuale tra -1 e 1
        colorAdjust.setHue(randomHue);
        auto.getImmageView().setEffect(colorAdjust);

    }
}
