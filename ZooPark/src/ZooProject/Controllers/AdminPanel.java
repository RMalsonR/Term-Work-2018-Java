package ZooProject.Controllers;

import ZooProject.Scripts.DataBaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminPanel {

    @FXML
    public Button addAnimal;
    @FXML
    public Button editAnimal;
    @FXML
    private Button quitAdminButton;

    public void backAction(ActionEvent actionEvent) throws IOException {
        Stage stage = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
        stage.setTitle("Городской Зоопарк Мир Животных");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../FXML/MainPage.fxml"));
        stage.getIcons().add(new Image(this.getClass().getResource("../Pictures/MainLogo.jpg").toString()));
        Parent root = loader.load();
        MainPage controller = loader.getController();
        stage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
    }


    public void addAnimalAction(ActionEvent actionEvent) throws IOException {
        Stage stage = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
        stage.setTitle("Добавление животного");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../FXML/CreateAnimal.fxml"));
        stage.getIcons().add(new Image(this.getClass().getResource("../Pictures/MainLogo.jpg").toString()));
        Parent root = loader.load();
        CreateAnimal controller = loader.getController();
        stage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
    }

    public void editAnimalAction(ActionEvent actionEvent) throws IOException {
        Stage stage = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
        stage.setTitle("Редактирование животных");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../FXML/EditAnimal.fxml"));
        stage.getIcons().add(new Image(this.getClass().getResource("../Pictures/MainLogo.jpg").toString()));
        Parent root = loader.load();
        EditAnimal controller = loader.getController();
        stage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
    }
}
