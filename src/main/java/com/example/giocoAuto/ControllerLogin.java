package com.example.giocoAuto;
/**
 La classe ControllerLogin rappresenta il controller per la gestione del login nel gioco.
 È implementata come un singleton per garantire un'unica istanza del controller.
 Contiene metodi per impostare lo stato del login e verificare se l'utente è loggato.
 */
public class ControllerLogin {

private static ControllerLogin instance = new ControllerLogin();
private boolean isLogin = false;

private ControllerLogin() {}
    /**
     Restituisce l'istanza del ControllerLogin.
     @return L'istanza del ControllerLogin.
     */
public static ControllerLogin getInstance() {
        return instance;
        }
    /**
     Verifica se l'utente è loggato.
     @return True se l'utente è loggato, False altrimenti.
     */
public boolean isLogin() {
        return isLogin;
        }
    /**
     Imposta lo stato del login.
     @param log Lo stato del login da impostare.
     */
public void setLogin(boolean log) {
       isLogin = log;
        }
    /**
     Esegue l'accesso, impostando lo stato del login a True.
     */
public void log() {
    isLogin = true;
}
    /**
     Esegue il logout, impostando lo stato del login a False.
     */
public void nolog() {
        isLogin = false;

        }}