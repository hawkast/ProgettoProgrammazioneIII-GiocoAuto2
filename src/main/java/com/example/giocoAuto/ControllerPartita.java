package com.example.giocoAuto;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
/**
 La classe ControllerPartita gestisce la logica e l'interfaccia della partita nel gioco dell'auto.
 Contiene metodi per gestire l'avvio, la messa in pausa e il salvataggio della partita, nonché per gestire
 l'interazione con l'utente attraverso l'interfaccia grafica.
 */
public class ControllerPartita {

    @FXML
    public Label countLabel;
    private Timeline time;
    @FXML
    private Label lifepointsLab;
    @FXML
    public ToggleButton startToggleButton;
    @FXML
    public ToggleButton PauseToggleButton;
    @FXML
    private GridPane grid;
    @FXML
    public Label errorLab;
    @FXML
    private Button btnSaveGame;
    @FXML
    private Button btnLoadGame;



private Ostacoli ostacoli;
private Login login;
private TestOb ob;
    private Auto auto;
    private Furgone furgone;
    private GameController gamecontroller;

    private Camion camion;
private Factory factory_Class;
private Factory factory_Object;
private User user;
    private static String firstName;
    private static String lastName;
    @FXML
    private Stage corrente;
    @FXML
private ImageView cars;
    /**
     Costruttore della classe ControllerPartita.
     */
public ControllerPartita(){

}
    private Stage primaryStage;
    /**
     Imposta il main stage dell'applicazione.
     @param primaryStage Il main stage dell'applicazione.
     */
@FXML
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    /**
     Imposta il current stage della partita.
     @param corrente Il current stage della partita.
     */
    @FXML
    public void setCorrente(Stage corrente){this.corrente=corrente;}
    /**
     Gestisce l'evento di game over nella partita.
     @throws IOException Eccezione in caso di errore durante il caricamento del file FXML.
     */
@FXML
public void GameOver()throws IOException{
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Gameover.fxml"));
    Parent root=loader.load();
    GameOverWindow controller = loader.getController();
    controller.setPrimaryStage(primaryStage);
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.initModality(Modality.APPLICATION_MODAL);
   time=new Timeline( new KeyFrame(Duration.seconds(1),event1 -> {
    if(auto.getLifepoint()<0){
      stage.setTitle("GAME-OVER");
        time.stop();
        stage.show();
        corrente.close();
    }}));
            time.setCycleCount(Animation.INDEFINITE);
            time.play();
}
    /**
     Gestisce l'evento di messa in pausa del gioco.
     */
    @FXML
    private void pauseGame() {
        // Metti in pausa il gioco
        // ...
        if(PauseToggleButton.isSelected()) {

       GameManager.getInstance().pause();


        // Visualizza il popup
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    //  alert.setGraphic(aler);
        alert.setTitle("Pausa");
        alert.setHeaderText(null);
        alert.setContentText("Il gioco è stato messo in pausa. Clicca su Riprendi per continuare.");

        ButtonType resumeButton = new ButtonType("Riprendi");
        ButtonType returnButton = new ButtonType("Torna al Menu");
        alert.getButtonTypes().setAll(resumeButton,returnButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == resumeButton) {
           GameManager.getInstance().resume();
startToggleButton.setDisable(true);
            // Riprendi il gioco
            // ...
        }
        else if(result.get()==returnButton) {
            GameManager.getInstance().stop();
            corrente.close();

gamecontroller.GetStop();

primaryStage.show();

        }}
    }
    /**
     Gestisce l'evento di avvio del gioco.
     @throws IOException Eccezione in caso di errore durante il caricamento del file FXML.
     */
    @FXML
    private void StartGame()  throws IOException {
        // Metti in pausa il gioco

        if(startToggleButton.isSelected()) {

            gamecontroller.iniziaGioco();
            GameOver();
            startToggleButton.setDisable(true);
        }


}
    /**
     Imposta il nome dell'utente.
     @param fName Il nome dell'utente.
     */
    public static void setFirstName(String fName) {
        firstName = fName;
    }
    /**
     Imposta il cognome dell'utente.
     @param lName Il cognome dell'utente.
     */
public static void setLastName(String lName){
        lastName=lName;
}
    /**
     Salva la partita corrente.
     */
     public void saveGame() {

        if (!ControllerLogin.getInstance().isLogin()) {
            System.out.println("Effettua il login per salvare la partita");
            return;
        }

        try {
            user=new User(firstName, lastName);
            login.saveGame(user,gamecontroller);
            System.out.println("SAVE_GAME_SUCCESS_MESSAGE");
        } catch (IOException e) {
            System.out.println("SAVE_GAME_ERROR_MESSAGE");
            e.printStackTrace();
        }
    }
    /**
     Carica una partita salvata.
     @throws IOException Eccezione in caso di errore durante il caricamento del file FXML.
     */
public void carica() throws IOException {
        User user=new User(firstName,lastName);
        login.loadGame(user,gamecontroller);
    System.out.println(login.loadGame(user,gamecontroller));
}
    /**
     Inizializza la partita.
     @throws IOException Eccezione in caso di errore durante il caricamento delle immagini.
     */
    public void initialize() throws IOException {
        InputStream imageauto = getClass().getResourceAsStream("/com/example/giocoAuto/images/autorossa.jpg");
        cars.setImage(new Image(imageauto));
        InputStream imageFurgone = getClass().getResourceAsStream("/com/example/giocoAuto/images/furgone.jpg");
        Image furgoneImage =new Image(imageFurgone);
        furgone = new Furgone(grid, cars, furgoneImage);
        auto = new Auto(grid, cars); // per associare l'immagine alla classe AUTO e i suoi metodi
        InputStream imageCamion=getClass().getResourceAsStream("/com/example/giocoAuto/images/camion.png");
        Image camionImage=new Image(imageCamion);
        camion = new Camion(grid, cars,camionImage );
        ob = new TestOb(grid);
        ostacoli = new Ostacoli(grid);
        factory_Class = new Factory(grid, auto, ob, furgone, ostacoli, camion);
        factory_Object = new Factory(countLabel, errorLab, lifepointsLab);

        gamecontroller = new GameController(factory_Class, factory_Object);
        Factory factory_otherClass = new Factory(gamecontroller);


        login = new Login();



        // Settaggi per le istanze

        auto.setFactory(factory_Class, factory_Object, factory_otherClass);
        ob.set(factory_Class);
        ostacoli.set(factory_Class);
        grid.add(cars, 4, 5);


// asssegnazione dei button

        btnLoadGame.setOnAction(event -> {

            try {
                carica();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        btnSaveGame.setOnAction(event -> {
            saveGame();

        });


    }

}