package com.example.giocoAuto;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.effect.Effect;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static javafx.scene.layout.GridPane.*;
/**
 * @author Lorenzo Guerrini
 * @author Pasquale Marzocchi
 */

/**
 La classe Auto rappresenta un'auto nel gioco.
 Contiene metodi per gestire lo stato dell'auto, la decorazione, la raccolta di oggetti e il movimento.
 */
public  class Auto  {
    @FXML
    private ImageView cars;
    @FXML
    private GridPane grid;
    private Factory factory;
    private Ostacoli os;
    private Factory factory_Object;
    private Factory factory_otherClass;
    private int contatore=0;
    private int lifepoint=3;
    private TestOb ob;


    private List<AutoDecorator> decorators = new ArrayList<>();


    private AutoState state;

    /**
     * Costruttore della classe Auto.
     * @param grid L'oggetto GridPane che rappresenta la griglia del gioco.
     * @param cars L'oggetto ImageView che rappresenta l'auto.
     */
    public Auto(GridPane grid, ImageView cars) {
        this.grid = grid;
        this.cars = cars;
        GridPane.setHalignment(cars, HPos.CENTER);  // allinea al centro orizzontalmente
        GridPane.setValignment(cars, VPos.CENTER);  // verticalmente sempre allinea al centro

    }
    /**
     * Imposta l'ImageView dell'auto.
     * @param immageView L'oggetto ImageView da impostare.
     */
    public void setImmageView(ImageView immageView) {
        this.cars = immageView;
    }

    /**
     * Imposta le factory per la creazione degli oggetti nel gioco.
     * @param factory          La factory principale.
     * @param factory_Object   La factory per la classe Object.
     * @param factory_otherClass La factory per la classe GameController.
     */
    public void setFactory( Factory factory ,Factory factory_Object,Factory factory_otherClass){
        this.factory=factory;
        this.factory_otherClass=factory_otherClass;
        this.factory_Object=factory_Object;
        ob=factory.createTestOb();
        os=factory.createOstacoli();
    }
    /**
     * Gestisce la collisione dell'auto con gli ostacoli.
     */
    public void ScontraOstacolo(){
        ArrayList<ImageView> imageviewostacoli= os.getImageOS();
        int autoCol=GridPane.getColumnIndex(cars);
        int autoRow=GridPane.getRowIndex(cars);
        System.out.println("OSTACOLO INFO AUTO"+autoCol+autoRow);
        for(Node imageView : imageviewostacoli){
            int ostacoloCol=GridPane.getColumnIndex(imageView);
            int ostacoloRow=GridPane.getRowIndex(imageView);
            if (ostacoloRow==autoRow && ostacoloCol==autoCol){
                grid.getChildren().remove(imageView);
                lifepoint--;
                addDecorator(new ColorDecorator());
                decorate();
                System.out.println("colOB"+ostacoloCol+"rowOS"+ostacoloRow);

                System.out.println("lifepoint"+lifepoint);
                if(lifepoint<0){
                    factory_otherClass.createGameController().GetStop();
                }
            }
            switch (lifepoint){
                case 3 -> factory_Object.createLifepointsLab().setText("Vite disponibili :\n O O O");
                case 2-> factory_Object.createLifepointsLab().setText("Vite disponibili :\n O O X");
                case 1 -> factory_Object.createLifepointsLab().setText("Vite disponibili :\n O X X");
                case 0-> factory_Object.createLifepointsLab().setText("Vite disponibili :\n X X X");
                default  -> factory_Object.createLifepointsLab().setText("Vite disponibili :\n Non hai piu vite");
            }}

    }
    ///////////////////////////////////////////STATE///////////////////////////
    /**
     * Imposta lo stato dell'auto.
     * @param state Lo stato dell'auto.
     */
public void setState(  AutoState state){
        this.state=state;
}
    /**
     * Trasforma l'auto in un furgone.
     */
    public void trasformafurgone(){
        AutoState furgone= factory.createFurgone();
        this.setState(furgone);
        cars.setImage(furgone.getImage().getImage());
    }


    /**
     * Trasforma l'auto in un camion.
     */
    public void trasformaCamion(){
    AutoState camion= factory.createCamion();
    this.setState(camion);
        cars.setImage(camion.getImage().getImage());
    }
    /**
     * Aggiunge un decoratore all'auto.
     * @param decorator Il decoratore da aggiungere.
     */
    public void addDecorator(AutoDecorator decorator) {
        decorators.add(decorator);
    }
    /**
     * Applica tutti i decoratori all'auto.
     */
    public void decorate() {
        for (AutoDecorator decorator : decorators) {
            decorator.decorate(this);
        }
    }
    /**
     * Raccoglie gli oggetti presenti sulla griglia.
     */
    public void raccogliOggetti(){
        ArrayList<ImageView> imageViewsList = ob.getImageOb();
        int autoCol = GridPane.getColumnIndex(cars);
        int autoRow = GridPane.getRowIndex(cars);

        // richiama la lista di testoob

        for (Node imageView : imageViewsList) {     // un ciclo for per iterare su tutti gli oggetti della lista
            // in modo tale che questo metodo funziona e recupera la posizione su tutti gli oggetti raccolti
            int oggettoCol = GridPane.getColumnIndex(imageView);
            int oggettoRow = GridPane.getRowIndex(imageView);
            if (oggettoCol == autoCol && oggettoRow == autoRow) {
                grid.getChildren().remove(imageView);
                System.out.println("oggetto"+imageView);
                contatore++;
                // case 2 camion
                int c=contatore;
                switch (c) {
                    case 1 -> {
                        trasformafurgone();

                    }
                    case 2 -> {
                        trasformaCamion();
                    }
                }

            } }

        factory_Object.CreateCountLabel().setText("Oggetti Raccolti:\n" + contatore);;
        System.out.println("contatore"+contatore);
    }
    /**
     * Restituisce l'immagine dell'auto.
     * @return L'immagine dell'auto.
     */
    public ImageView getImmageView() {
        return cars;
    }
    /**
     * Restituisce l'effetto di colore dell'auto.
     * @return L'effetto di colore dell'auto.
     */
    public Effect getColorAuto(){
        return cars.getEffect();
    }
    /**
     * Restituisce il numero di punti vita dell'auto.
     * @return Il numero di punti vita dell'auto.
     */
    public int getLifepoint(){
        return lifepoint;
    }
    /**
     * Restituisce il contatore degli oggetti raccolti.
     * @return Il contatore degli oggetti raccolti.
     */
    public int getContatore(){
        return contatore;
    }
    /**
     * Imposta il valore del contatore degli oggetti raccolti.
     * @param cont Il valore del contatore degli oggetti raccolti.
     */
    public void setcontatore(int cont){
        contatore=cont;
    }
    /**
     * Imposta il numero di punti vita dell'auto.
     * @param life Il numero di punti vita dell'auto.
     */
    public void setLifepoint(int life){
        lifepoint=life;
    }

    /**
     * Muove l'auto verso l'oggetto più vicino.
     */
    public void moveToClosestObject2() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
            if(GameManager.getInstance().isPaused()){return;}
            ArrayList<ImageView> imageViewsList = factory.createTestOb().getImageOb();
            // Calcola la posizione corrente dell'auto sulla griglia
            int currentX = getColumnIndex(cars);
            int currentY = getRowIndex(cars);
            // Inizializza le variabili per l'oggetto più vicino e la sua distanza
            Node closestObject = null;
            double closestDistance = Double.MAX_VALUE;
            ArrayList<Node> closestObjects = new ArrayList<>();
            double closestObjectsDistance = Double.MAX_VALUE;
// Scorri la lista degli oggetti e trova quelli più vicini all'auto
            for (Node object : imageViewsList) {
                // Calcola la distanza tra l'auto e l'oggetto
                int objectX = getColumnIndex(object);
                int objectY = getRowIndex(object);
                double distance = Math.sqrt(Math.pow(objectX - currentX, 2) + Math.pow(objectY - currentY, 2));

                // Aggiorna la lista degli oggetti più vicini se necessario
                if (distance < closestObjectsDistance) {
                    closestObjects.clear();
                    closestObjects.add(object);
                    closestObjectsDistance = distance;
                } else if (distance == closestObjectsDistance) {
                    closestObjects.add(object);
                }
                // Aggiorna l'oggetto più vicino se necessario
                if (distance < closestDistance) {
                    closestObject = object;
                    closestDistance = distance;
                }
            }
            ArrayList<ImageView> imageViewsListos = os.getImageOS();
            Node closestObstacleLeft = null;
            double closestDistanceLeft = Double.MAX_VALUE;
            Node closestObstacleRight = null;
            double closestDistanceRight = Double.MAX_VALUE;
            // Scorri la lista degli ostacoli e trova quelli più vicini all'auto a sinistra e a destra
            for (Node obstacle : imageViewsListos) {
                // Calcola la distanza tra l'auto e l'ostacolo
                int obstacleX = getColumnIndex(obstacle);
                int obstacleY = getRowIndex(obstacle);
                double distance = Math.sqrt(Math.pow(obstacleX - currentX, 2) + Math.pow(obstacleY - currentY, 2));
                // Aggiorna l'ostacolo più vicino a sinistra se necessario
                if (obstacleX < currentX && distance < closestDistanceLeft) {
                    closestObstacleLeft = obstacle;
                    closestDistanceLeft = distance;
                }
                // Aggiorna l'ostacolo più vicino a destra se necessario
                if (obstacleX > currentX && distance < closestDistanceRight) {
                    closestObstacleRight = obstacle;
                    closestDistanceRight = distance;
                }
            }
            try{
                if(closestObject==null ){
                    throw  new NullPointerException("creazione del percorso in corso...");
                }
                factory_Object.createErrorLab().setDisable(true);
                factory_Object.createErrorLab().setVisible(false);
                // Calcola la direzione verso cui muovere l'auto
                int targetX = getColumnIndex(closestObject);
                int targetY = getRowIndex(closestObject);
                int dx = targetX - currentX;
                int dy = targetY - currentY;
                // Conta il numero di ostacoli a sinistra e a destra
                int numObstaclesLeft = 0;
                int numObstaclesRight = 0;
                for (Node obstacle : imageViewsListos) {
                    int obstacleX = getColumnIndex(obstacle);
                    if (obstacleX < currentX) {
                        numObstaclesLeft++;
                    } else if (obstacleX > currentX) {
                        numObstaclesRight++;
                    }
                }
                // Conta il numero di oggetti a sinistra e a destra
                int numObjectLeft = 0;
                int numObjectRight = 0;
                for (Node object : imageViewsList) {
                    int objectX = getColumnIndex(object);
                    if (objectX < currentX) {
                        numObjectLeft++;
                    } else if (objectX > currentX) {
                        numObjectRight++;
                    }
                }
                if(closestObjects.size()>1){
                    if(numObstaclesLeft>numObjectLeft && dx>0) {
                        grid.getChildren().remove(cars);
                        grid.add(cars, currentX + 1, currentY);
                    }
                    else if(numObstaclesRight>numObjectRight && dx<0){
                        grid.getChildren().remove(cars);
                        grid.add(cars, currentX - 1, currentY);
                    } else {
                        // Muovi l'auto in modo casuale
                        Random randomCars = new Random();
                        int Col = randomCars.nextInt(1);
                        int Row = randomCars.nextInt(1) ;
                        int newColIndex = (currentY + Col + grid.getColumnCount()) % grid.getColumnCount();
                        int newRowIndex = (currentX + Row + grid.getRowCount()) % grid.getRowCount();
                        grid.getChildren().remove(cars);
                        grid.add(cars, newColIndex, newRowIndex);
                    }}
                else{
                    // Muovi l'auto di una cella nella direzione calcolata
                    if (dx > 0) {
                        // Muovi a destra
                        grid.getChildren().remove(cars);
                        grid.add(cars, currentX + 1, currentY);
                    } else if (dx < 0) {
                        // Muovi a sinistra
                        grid.getChildren().remove(cars);
                        grid.add(cars, currentX - 1, currentY);
                    } else if (dy > 0) {
                        // Muovi in basso
                        grid.getChildren().remove(cars);
                        grid.add(cars, currentX, currentY + 1);
                    } else if (dy < 0) {
                        // Muovi in alto
                        grid.getChildren().remove(cars);
                        grid.add(cars, currentX, currentY - 1);
                    }}
                    // Se l'auto è sopra l'oggetto più vicino, raccoglilo e rimuovilo dalla lista
                    if (currentX == targetX && currentY == targetY) {
                        imageViewsList.remove(closestObject); // lo rimuove dalla lista (corretto) senza questo lo considera sempre
                        grid.getChildren().remove(closestObject);  // se fai solo grid.remove
                    }
raccogliOggetti();
                    ScontraOstacolo();;
            } catch (NullPointerException event){
                String errorm="creazione del percorso in corso...";
                factory_Object.createErrorLab().setText(errorm);
            }
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }



}




