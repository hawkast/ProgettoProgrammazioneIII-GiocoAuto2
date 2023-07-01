package com.example.giocoAuto;
/**
 L'interfaccia AutoDecorator rappresenta un decoratore per la classe Auto.
 I decoratori vengono utilizzati per aggiungere funzionalità o caratteristiche aggiuntive agli oggetti Auto.
 */
public interface AutoDecorator {
    /**
     Decora l'oggetto Auto fornito con funzionalità aggiuntive.
     @param auto L'oggetto Auto da decorare.
     */
    void decorate(Auto auto);
}
