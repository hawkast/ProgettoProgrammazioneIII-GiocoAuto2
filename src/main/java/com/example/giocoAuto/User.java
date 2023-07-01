package com.example.giocoAuto;
/**
 La classe User rappresenta un utente del gioco.
 Contiene informazioni come il nome e il cognome dell'utente.
 */
public class User {
    private String firstName;
    private String lastName;
    /**
     * Costruttore della classe User.
     * @param firstName Il nome dell'utente.
     * @param lastName  Il cognome dell'utente.
     */
    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    /**
     * Restituisce il nome dell'utente.
     * @return Il nome dell'utente.
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Imposta il nome dell'utente.
     * @param firstName Il nome dell'utente da impostare.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * Restituisce il cognome dell'utente.
     * @return Il cognome dell'utente.
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Imposta il cognome dell'utente.
     * @param lastName Il cognome dell'utente da impostare.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
