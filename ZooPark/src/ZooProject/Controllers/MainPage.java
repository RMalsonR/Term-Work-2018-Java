package ZooProject.Controllers;

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
import java.util.Optional;

public class MainPage {
    @FXML private Button adminButton;
    @FXML private Button guestButton;


    public void handleButtonActionGuest(ActionEvent actionEvent) throws IOException {
        Stage stage = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
        stage.setTitle("Городской Зоопарк 'Мир Животных'");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../FXML/GuestPanel.fxml"));
        stage.getIcons().add(new Image(this.getClass().getResource("../Pictures/MainLogo.jpg").toString()));
        Parent root = loader.load();
        GuestPanel controller = loader.getController();
        stage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
    }

    private void PasswordExeption(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Сообщение об ошибке");
        alert.setContentText("Проверьте введённый пароль");
        alert.showAndWait();
    }

    public void handleButtonActionAdmin(ActionEvent actionEvent) throws IOException{
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Вход в Админ панель");
        dialog.setContentText("Введите пароль:");
        Optional<String> result = dialog.showAndWait();
        if(result.isPresent()){
            if(result.get().equals("553835")) {
                Stage stage = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
                stage.setTitle("Панель Администратора");
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../FXML/AdminPanel.fxml"));
                stage.getIcons().add(new Image(this.getClass().getResource("../Pictures/MainLogo.jpg").toString()));
                Parent root = loader.load();
                AdminPanel controller = loader.getController();
                stage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
            } else PasswordExeption();
        } else PasswordExeption();
    }
}
