package com.example.giocoAuto;

import java.util.Observable;
/**
 La classe LoginObservable estende la classe Observable e rappresenta un oggetto osservabile
 utilizzato per gestire la notifica di cambiamenti agli oggetti osservatori registrati.
 Ogni volta che viene impostato un nuovo messaggio tramite il metodo setMessage(),
 l'oggetto notifica automaticamente tutti gli osservatori registrati mediante il metodo notifyObservers().
 */
public class LoginObservable extends Observable {
    private String message;
    /**
     * Imposta il messaggio da notificare agli osservatori e segnala il cambiamento.
     * @param message Il messaggio da impostare.
     */
    public void setMessage(String message) {
        this.message = message;
        setChanged();
        notifyObservers();
    }
    /**
     * Restituisce il messaggio corrente.
     * @return Il messaggio corrente.
     */
    public String getMessage() {
        return message;
    }
}
/// da cancellare classe dopo verifiche //////////////////////