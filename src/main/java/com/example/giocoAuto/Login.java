package com.example.giocoAuto;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 La classe Login gestisce il processo di accesso e registrazione degli utenti nel gioco.
 */
public class Login {
    private GameController gamecontroller;
    private static final String CREDENTIALS_FILE_NAME = "credentialUsers.txt";
    private static final Path CREDENTIALS_FILE_PATH = Paths.get(System.getProperty("user.home"), CREDENTIALS_FILE_NAME);
    private static final String GAMES_DIRECTORY_NAME = "partite";
    private static final Path GAMES_DIRECTORY_PATH = Paths.get(System.getProperty("user.dir"), GAMES_DIRECTORY_NAME);

private List<User> users;
    /**
     Crea un'istanza di Login e carica gli utenti dal file se esiste, altrimenti crea una nuova lista vuota.
     @throws IOException Se si verifica un errore durante il caricamento degli utenti dal file.
     */
    public Login() throws IOException {
        if (Files.exists(CREDENTIALS_FILE_PATH)) {
            this.users = loadUsersFromFile(CREDENTIALS_FILE_PATH);
        } else {
            this.users = new ArrayList<>();
        }
    }
    /**
     Imposta il GameController associato a Login.
     @param gamecontroller Il GameController da impostare.
     */
    public void setGamecontroller(GameController gamecontroller) {
        this.gamecontroller = gamecontroller;
    }

    /**
     Registra un nuovo utente nel gioco.
     @param firstName Il nome del nuovo utente.
     @param lastName Il cognome del nuovo utente.
     @return true se la registrazione ha avuto successo, false se l'utente è già registrato.
     @throws IOException Se si verifica un errore durante il salvataggio degli utenti nel file.
     */
    public boolean register(String firstName, String lastName) throws IOException {
        UserIterator iterator = new UserIterator(users);
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getFirstName().equals(firstName) && user.getLastName().equals(lastName)) {
                System.out.println("Utente già registrato!");
                return false;}}
        User newUser = new User(firstName, lastName);
        users.add(newUser);
        saveUsersToFile(CREDENTIALS_FILE_PATH, users);
        System.out.println("Registrazione effettuata con successo!");
        return true;
    }
    /**
     Effettua l'accesso di un utente registrato nel gioco.
     @param firstName Il nome dell'utente.
     @param lastName Il cognome dell'utente.
     @return true se l'accesso ha avuto successo, false se le credenziali sono errate.
     */
    public boolean login(String firstName, String lastName) {
        UserIterator iterator = new UserIterator(users);
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getFirstName().equals(firstName) && user.getLastName().equals(lastName)) {
                System.out.println("Accesso effettuato con successo!");
                return true;}}
        System.out.println("Credenziali errate!");
        return false;
    }
    /**
     Carica gli utenti dal file specificato.
     @param filePath Il percorso del file contenente gli utenti registrati.
     @return Una lista degli utenti caricati dal file.
     @throws IOException Se si verifica un errore durante il caricamento degli utenti dal file.
     */
    private List<User> loadUsersFromFile(Path filePath) throws IOException {
        List<User> users = new ArrayList<>();
        Scanner scanner = new Scanner(filePath);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(",");
            User user = new User(tokens[0], tokens[1]);
            users.add(user);
        }
        scanner.close();
        return users;
    }
    /**
     Salva gli utenti nel file specificato.
     @param filePath Il percorso del file in cui salvare gli utenti.
     @param users La lista degli utenti da salvare.
     @throws IOException Se si verifica un errore durante il salvataggio degli utenti nel file.
     */
    private void saveUsersToFile(Path filePath, List<User> users) throws IOException {
        File file = filePath.toFile();
        FileWriter writer = new FileWriter(file, true);

        for (User user : users) {
            writer.write(user.getFirstName() + "," + user.getLastName() + "\n");
        }

        writer.close();
    }
    /**
     Carica lo stato di gioco di un utente specifico.
     @param user L'utente di cui caricare lo stato di gioco.
     @param game Il GameController del gioco.
     @return Il GameController con lo stato di gioco dell'utente caricato.
     @throws IOException Se si verifica un errore durante il caricamento dello stato di gioco dal file.
     */
    public GameController loadGame(User user,GameController game) throws IOException {
        String gameFileName = user.getFirstName() + "_" + user.getLastName() + ".txt";
        Path gameFilePath = Paths.get(GAMES_DIRECTORY_PATH.toString(), gameFileName);
        if (!Files.exists(gameFilePath)) {
            throw new FileNotFoundException("File not found: " + gameFilePath.toString());
        }
        BufferedReader reader = new BufferedReader(new FileReader(gameFilePath.toFile()));
        String line;
        int[] carPos = null;
        List<int[]> objectPositions = new ArrayList<>();
        List<int[]>obstaclePositions=new ArrayList<>();
        int carSt=0;
        String oggettiRccoltu=null;
        String viteRimaste=null;
        int carLif=0;
        ColorAdjust carColorAdjust = new ColorAdjust();

        while ((line = reader.readLine()) != null) {
            if (line.startsWith("CarPosition:")) {
                String[] parts = line.split(":")[1].split(",");
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                System.out.println("Loaded car position: " + x + "," + y);
                carPos = new int[]{x, y};
            }
            else if (line.startsWith("ObjectPosition:")) {
                String[] parts = line.split(":")[1].split(",");
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                System.out.println("Loaded object position: " + x + "," + y);
                objectPositions.add(new int []{x, y});
            }
            else if(line.startsWith("ObstaclePosition:")){
                String[] parts=line.split(":")[1].split(",");
                int x=Integer.parseInt(parts[0]);
                int y= Integer.parseInt(parts[1]);
                obstaclePositions.add(new int []{x,y});
            }
            else if(line.startsWith("CarState:")){
                carSt = Integer.parseInt(line.split(":")[1]);

            }
            else if(line.startsWith("LabelConta:")){
                oggettiRccoltu = line.split(":")[1];
                System.out.println(oggettiRccoltu);

            }
            else if(line.startsWith("CarLife:")){
                carLif=Integer.parseInt(line.split(":")[1]);
            }
            else if(line.startsWith("LabelLife:")){
                viteRimaste=line.split(":")[1];
            }
            else if(line.startsWith("CarColor:")){
                String[] parts = line.split(":")[1].split(",");
                double hue = Double.parseDouble(parts[0]);
                carColorAdjust.setHue(hue);

            }
        }
        reader.close();
        if (carPos == null) {
            throw new IOException("Invalid game file: " + gameFilePath.toString());
        }
 game.setAutoPosition(carPos[0], carPos[1]);
        for (int[] pos : objectPositions) {
            game.setPositionOggetti(pos[0], pos[1]);}
        for(int [] pos: obstaclePositions){
            game.setPositionOstacoli(pos[0],pos[1]);
        }
        // set stato auto,fuegone,camion
        game.setAutoIm(carSt);//
        // vite e label
        if(viteRimaste!=null){
            game.setLifepointS(carLif);
            game.setLifeLabel(viteRimaste+"\n");
            System.out.println(viteRimaste);
        }
        if (oggettiRccoltu!=null){
            game.setConta(carSt);
        game.setContalabel(oggettiRccoltu+":\n",carSt);}
        if(carColorAdjust!=null){
            game.setColorAuto(carColorAdjust);
        }// In questo modo la Label contiene sia il testo che il contatore.
        return game;

    }
    /**
     Salva lo stato di gioco di un utente specifico.
     @param user L'utente di cui salvare lo stato di gioco.
     @param game Il GameController del gioco.
     @throws IOException Se si verifica un errore durante il salvataggio dello stato di gioco nel file.
     */
    public void saveGame(User user, GameController game) throws IOException {
            String gameFileName = user.getFirstName() + "_" + user.getLastName() + ".txt";
            Path gameDirectoryPath = Files.createDirectories(GAMES_DIRECTORY_PATH);
            Path gameFilePath = gameDirectoryPath.resolve(gameFileName);
        BufferedWriter writer = new BufferedWriter(new FileWriter(gameFilePath.toFile()));

      // salva  set stato auto,fuegone,camion
int stateA=game.getConta();
int viteA=game.getLif();
writer.write("CarLife:"+viteA);
writer.newLine();
//
writer.write("CarState:"+stateA);
writer.newLine();
// label contatore oggetti raccolti
String contaOgRaccolti= game.getLabConta().getText();
writer.write("LabelConta:"+contaOgRaccolti);
writer.newLine();
//LABEL CONTA VITE
        String contaVite=game.getLabLife().getText();
        writer.write("LabelLife:"+contaVite);
        writer.newLine();
// colore auto
        ColorAdjust colorAdjust  =game.getColorAut();
        if(colorAdjust!=null){
        writer.write("CarColor:"+colorAdjust.getHue());
        writer.newLine();}
            // Salva la posizione dell'auto
            int[] carPos = game.getPositionAuto();
            writer.write("CarPosition:" + carPos[0] + "," + carPos[1] + "\n");
            writer.newLine();

            //salva posizione Oggetti
        // Salva la posizione degli oggetti
        ArrayList<ImageView> imageViewsList = game.GetImOggetti();
        for (ImageView imageOggetto : imageViewsList) {
            int posX = GridPane.getRowIndex(imageOggetto);
            int posY = GridPane.getColumnIndex(imageOggetto);
            writer.write("ObjectPosition:" + posX + "," + posY);
            writer.newLine();
        }
        //Salva posizione ostacooli
        ArrayList<ImageView> imageviewslistostacoli=game.getImOstacoli();
        for(ImageView imageOstacolo : imageviewslistostacoli){
            int posX= GridPane.getRowIndex(imageOstacolo);
            int posY=GridPane.getColumnIndex(imageOstacolo);
            writer.write("ObstaclePosition:"+posX+","+posY);
            writer.newLine();
        }
        writer.close();
    }
}
