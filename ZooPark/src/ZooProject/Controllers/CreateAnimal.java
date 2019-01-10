package ZooProject.Controllers;

import ZooProject.Scripts.Animal;
import ZooProject.Scripts.DataBaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class CreateAnimal {
    /**
     * Add your database connection values
     */
    public static final String URL = "url";
    public static final String USER = "user";
    public static final String PASSWORD = "pass";


    private static DataBaseConnection dataBaseConnection = new DataBaseConnection(URL,USER,PASSWORD);


    @FXML
    public Button addButton;
    @FXML
    public Button cancelButton;

    @FXML
    public TextField nameField;
    @FXML
    public TextField sexField;
    @FXML
    public TextField ageField;
    @FXML
    public TextField borderField;
    @FXML
    public TextField kindField;
    @FXML
    public TextField growthField;
    @FXML
    public TextField weightField;
    @FXML
    public TextField nutritionField;
    @FXML
    public TextField oreoHabitatField;
    @FXML
    public TextField natureField;
    @FXML
    public TextField diseasField;
    @FXML
    public TextField indexOfAnimalField;

    private boolean isValid(){
        String errorMessage = "";
        if(nameField.getText() == null || nameField.getText() == null || !(nameField.getText().matches("[а-яА-Я]+"))){
            errorMessage+="Недопустимое имя животного.\n";
        }
        if(sexField.getText() == null || sexField.getText() == null || !(sexField.getText().matches("[а-яА-Я]+"))){
            errorMessage+="Недопустимый пол животного.\n";
        }
        if(ageField.getText() == null || ageField.getText() == null || !(ageField.getText().matches("[0-9]+"))){
            errorMessage+="Недопустимый возраст животного.\n";
        }
        if(borderField.getText() == null || borderField.getText() == null || !(borderField.getText().matches("[0-9]+"))){
            errorMessage+="Недопустимый барьер животного.\n";
        }
        if(indexOfAnimalField.getText().charAt(0)=='К' &&(borderField.getText().charAt(0)=='3' || borderField.getText().charAt(0)=='4')){
            errorMessage+="Кроликов можно посадить в вальер ТОЛЬКО с кроликами.\n";
        }
        if(indexOfAnimalField.getText().charAt(0)=='В' &&(borderField.getText().charAt(0)=='1' || borderField.getText().charAt(0)=='2' || borderField.getText().charAt(0)=='4')){
            errorMessage+="Волков можно посадить в вальер ТОЛЬКО с волками.\n";
        }
        if(indexOfAnimalField.getText().charAt(0)=='Ж' &&(borderField.getText().charAt(0)=='1' || borderField.getText().charAt(0)=='2' || borderField.getText().charAt(0)=='3')){
            errorMessage+="Жирафов можно посадить в вальер ТОЛЬКО с Жирафами.\n";
        }
        if(kindField.getText() == null || kindField.getText() == null || !(kindField.getText().matches("[а-яА-Я]+"))){
            errorMessage+="Недопустимый вид животного.\n";
        }
        if(growthField.getText() == null || growthField.getText() == null || !(growthField.getText().matches("[0-9]+"))){
            errorMessage+="Недопустимый рост животного.\n";
        }
        if(weightField.getText() == null || weightField.getText() == null || !(weightField.getText().matches("[0-9]+"))){
            errorMessage+="Недопустимый вес животного.\n";
        }
        if(nutritionField.getText() == null || nutritionField.getText() == null || !(nutritionField.getText().matches("[а-яА-Я]+"))){
            errorMessage+="Недопустимое питание животного.\n";
        }
        if(oreoHabitatField.getText() == null || oreoHabitatField.getText() == null || !(oreoHabitatField.getText().matches("[а-яА-Я]+"))){
            errorMessage+="Недопустимый ореон обитания животного.\n";
        }
        if(natureField.getText() == null || natureField.getText() == null || !(natureField.getText().matches("[а-яА-Я]+"))){
            errorMessage+="Недопустимый характер животного.\n";
        }
        if(diseasField.getText() == null || diseasField.getText() == null || !(diseasField.getText().matches("[а-яА-Я]+"))){
            errorMessage+="Недопустимый список болезней животного.\n";
        }
        if(indexOfAnimalField.getText() == null || indexOfAnimalField.getText() == null || !(indexOfAnimalField.getText().matches("[а-яА-Я]+[0-9]+"))){
            errorMessage+="Недопустимый ПИН животного.\n";
        }
        if(errorMessage==""){
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Некорректные поля");
            alert.setHeaderText("Внесите корректную информацию.");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    public void addButtonAction(ActionEvent actionEvent) throws SQLException {
        if(isValid()){
            Animal animal = new Animal(nameField.getText(), sexField.getText(),
                    natureField.getText(), indexOfAnimalField.getText(),
                    Integer.parseInt(borderField.getText()), nutritionField.getText(),
                    oreoHabitatField.getText(), diseasField.getText(),
                    Integer.parseInt(ageField.getText()),
                    Integer.parseInt(growthField.getText()),
                    Integer.parseInt(weightField.getText()),
                    kindField.getText());
            dataBaseConnection.addAnimal(animal);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Информация о добавлении животного");
            alert.setContentText("Животное добавлено!");
            alert.showAndWait();
        }
    }

    public void cancelButtonAction(ActionEvent actionEvent) throws IOException {
        Stage stage = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
        stage.setTitle("Панель Администратора Зоопарка");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../FXML/AdminPanel.fxml"));
        stage.getIcons().add(new Image(this.getClass().getResource("../Pictures/MainLogo.jpg").toString()));
        Parent root = loader.load();
        AdminPanel controller = loader.getController();
        stage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
    }
}
