package ZooProject.Controllers;


import ZooProject.Scripts.Animal;
import ZooProject.Scripts.DataBaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GuestPanel implements Initializable {
    /**
     * Add your database connection values
     */
    public static final String URL = "url";
    public static final String USER = "user";
    public static final String PASSWORD = "pass";

    private static DataBaseConnection dataBaseConnection = new DataBaseConnection(URL,USER,PASSWORD);

    @FXML
    public Label countOfRabbits;
    @FXML
    public Label countOfWolfs;
    @FXML
    public Label countOfGiraffe;
    @FXML
    public Button rabbitOnAction;
    @FXML
    public Button wolfsButton;
    @FXML
    public Button giraffeButton;
    @FXML
    public Button back;



    public void backButtonAction(ActionEvent actionEvent) throws IOException {
        Stage stage = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
        stage.setTitle("Городской Зоопарк Мир Животных");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../FXML/MainPage.fxml"));
        stage.getIcons().add(new Image(this.getClass().getResource("../Pictures/MainLogo.jpg").toString()));
        Parent root = loader.load();
        MainPage controller = loader.getController();
        stage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
    }

    public void rabbitButtonAction(ActionEvent actionEvent) throws IOException {
        Stage stage = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
        stage.setTitle("Кролики нашего Зоопарка");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../FXML/RabbitsTable.fxml"));
        stage.getIcons().add(new Image(this.getClass().getResource("../Pictures/MainLogo.jpg").toString()));
        Parent root = loader.load();
        RabbitsController controller = loader.getController();
        stage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ArrayList<Animal> animalsArrayList = dataBaseConnection.getAllAnimals();
            int rabbCount=0, wolfCount=0, girafCount=0;
            for(Animal animal: animalsArrayList){
                if(animal.getIndex_of_animal().charAt(0)=='К') rabbCount++;
                if(animal.getIndex_of_animal().charAt(0)=='В') wolfCount++;
                if(animal.getIndex_of_animal().charAt(0)=='Ж') girafCount++;
            }
            countOfRabbits.setText(String.valueOf(rabbCount));
            countOfWolfs.setText(String.valueOf(wolfCount));
            countOfGiraffe.setText(String.valueOf(girafCount));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void wolfsButtonAction(ActionEvent actionEvent) throws IOException {
        Stage stage = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
        stage.setTitle("Волки нашего Зоопарка");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../FXML/WolfsTable.fxml"));
        stage.getIcons().add(new Image(this.getClass().getResource("../Pictures/MainLogo.jpg").toString()));
        Parent root = loader.load();
        WolfsController controller = loader.getController();
        stage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
    }

    public void giraffeButtonAction(ActionEvent actionEvent) throws IOException {
        Stage stage = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
        stage.setTitle("Жирафы нашего Зоопарка");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../FXML/GiraffesTable.fxml"));
        stage.getIcons().add(new Image(this.getClass().getResource("../Pictures/MainLogo.jpg").toString()));
        Parent root = loader.load();
        GiraffesController controller = loader.getController();
        stage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
    }
}
