package com.example.giocoAuto;

import java.util.Iterator;
import java.util.List;
/**
 La classe UserIterator implementa l'interfaccia Iterator per la classe User.
 Permette di iterare su una lista di utenti.
 */
public class UserIterator implements Iterator<User> {
    private List<User> users;
    private int currentIndex;
    /**
     * Costruttore della classe UserIterator.
     * @param users La lista di utenti su cui iterare.
     */
    public UserIterator(List<User> users) {
        this.users = users;
        this.currentIndex = 0;
    }
    /**
     * Verifica se esistono ulteriori utenti da iterare.
     * @return true se esistono ulteriori utenti, false altrimenti.
     */
    public boolean hasNext() {
        return currentIndex < users.size();
    }
    /**
     * Restituisce il prossimo utente della lista.
     * @return Il prossimo utente della lista.
     */
    public User next() {
        if (hasNext()) {
            return users.get(currentIndex++);
        }
        return null;
    }
}