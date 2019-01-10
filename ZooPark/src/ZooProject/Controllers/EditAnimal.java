package ZooProject.Controllers;

import ZooProject.Scripts.Animal;
import ZooProject.Scripts.DataBaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditAnimal implements Initializable {
    /**
     * Add your database connection values
     */
    public static final String URL = "url";
    public static final String USER = "user";
    public static final String PASSWORD = "pass";


    private static DataBaseConnection dataBaseConnection = new DataBaseConnection(URL,USER,PASSWORD);

    private final ObservableList<Animal> animalsData = FXCollections.observableArrayList();
    @FXML
    public TableView<Animal> ownTableView;
    @FXML
    public TableColumn<Animal, String> indexOfAnimalColumn;
    @FXML
    public TableColumn<Animal, String> nameOfAnimalColumn;
    @FXML
    public TextField searchField;
    @FXML
    public Button searchButton;
    @FXML
    public Button editButton;
    @FXML
    public Button deleteButton;
    @FXML
    public Label animalNameLabel;
    @FXML
    public Label animalAgeLabel;
    @FXML
    public Label animalSexLabel;
    @FXML
    public Label animalGrowthLabel;
    @FXML
    public Label animalWeightLabel;
    @FXML
    public Label animalKindLabel;
    @FXML
    public Label animalOreoHabitatLabel;
    @FXML
    public Label animalNutritionLabel;
    @FXML
    public Label animalDiseasLabel;
    @FXML
    public Label animalNatureLabel;



    @FXML
    private Button backButton;

    public void backButtonAction(ActionEvent actionEvent) throws IOException {
        Stage stage = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
        stage.setTitle("Панель Администратора Зоопарка");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../FXML/AdminPanel.fxml"));
        stage.getIcons().add(new Image(this.getClass().getResource("../Pictures/AdminLogo.jpg").toString()));
        Parent root = loader.load();
        AdminPanel controller = loader.getController();
        stage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
    }

    public void showAnimalDetails(Animal animals) throws SQLException {
        if (animals != null) {
            animalNameLabel.setText(animals.getName().toString());
            animalAgeLabel.setText((animals.getAge().toString()) + " мес");
            animalSexLabel.setText(animals.getSex().toString());
            animalGrowthLabel.setText(animals.getGrowth().toString() + " см");
            animalWeightLabel.setText(animals.getWeight().toString() + " гр");
            animalKindLabel.setText(animals.getKind().toString());
            animalOreoHabitatLabel.setText(animals.getOreo_habitat().toString());
            animalNutritionLabel.setText(animals.getNutrition().toString());
            animalDiseasLabel.setText(animals.getDiseas().toString());
            animalNatureLabel.setText(animals.getNature().toString());
        } else {
            animalNameLabel.setText("");
            animalAgeLabel.setText("мес");
            animalSexLabel.setText("");
            animalGrowthLabel.setText("см");
            animalWeightLabel.setText("гр");
            animalKindLabel.setText("");
            animalOreoHabitatLabel.setText("");
            animalNutritionLabel.setText("");
            animalDiseasLabel.setText("");
            animalNatureLabel.setText("");
        }
    }

    public void initData() throws SQLException {
        ArrayList<Animal> animalsArrayList = dataBaseConnection.getAllAnimals();
        for (Animal rabbits: animalsArrayList){
            animalsData.add(new Animal(
                    rabbits.getName(),
                    rabbits.getSex(),
                    rabbits.getNature(),
                    rabbits.getIndex_of_animal(),
                    rabbits.getBorder(),
                    rabbits.getNutrition(),
                    rabbits.getOreo_habitat(),
                    rabbits.getDiseas(),
                    rabbits.getAge(),
                    rabbits.getGrowth(),
                    rabbits.getWeight(),
                    rabbits.getKind()));
        }
    }


    public boolean showAnimalEditDialog(Animal animal) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EditAction.class.getResource("../FXML/EditAction.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Изменение животного");
            dialogStage.getIcons().add(new Image(this.getClass().getResource("../Pictures/AdminLogo.jpg").toString()));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(null);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            EditAction controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAnimal(animal);

            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void editAction(ActionEvent actionEvent) throws IOException, SQLException {
        Animal selectedAnimal = ownTableView.getSelectionModel().getSelectedItem();
        if (selectedAnimal != null) {
            boolean okClicked = showAnimalEditDialog(selectedAnimal);
            if (okClicked) {
                EditAction newAnimal = new EditAction();
                showAnimalDetails(selectedAnimal);
                int selectedIndex = ownTableView.getSelectionModel().getSelectedIndex();
                animalsData.set(selectedIndex, selectedAnimal);
            }
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setContentText("Выберите животного, для его редактирования.");
            alert.showAndWait();
        }
    }

    public void deleteAction(ActionEvent actionEvent) throws SQLException {
        int id = ownTableView.getSelectionModel().getSelectedIndex();
        if(id >=0) {
            deleteAnimal(ownTableView.getItems().remove(id));
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setContentText("Выберите поле животного.");
            alert.showAndWait();
        }
    }

    public static void deleteAnimal(Animal animal) throws SQLException {
        dataBaseConnection.deleteAnimalCost(animal.getAnimal_id());
    }

    public void searchAction(ActionEvent actionEvent) throws SQLException {
        String srch = searchField.getText();
        animalsData.clear();
        Animal animal = dataBaseConnection.getAnimalByIndex(srch);
        animalsData.add(animal);
    }

    @Override
    public void initialize(java.net.URL location, ResourceBundle resources) {
        try {
            initData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        indexOfAnimalColumn.setCellValueFactory(new PropertyValueFactory<>("index_of_animal"));
        nameOfAnimalColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ownTableView.setItems(animalsData);
        try {
            showAnimalDetails(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ownTableView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                showAnimalDetails(newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }));
    }
    private boolean isValid(){
        String errorMessage = "";
        if(animalNameLabel.getText() == null || animalNameLabel.getText() == null || !(animalNameLabel.getText().matches("[а-яА-Я]+"))){
            errorMessage+="Недопустимое имя животного.\n";
        }
        if(animalSexLabel.getText() == null || animalSexLabel.getText() == null || !(animalSexLabel.getText().matches("[а-яА-Я]+"))){
            errorMessage+="Недопустимый пол животного.\n";
        }
        if(animalAgeLabel.getText() == null || animalAgeLabel.getText() == null || !(animalAgeLabel.getText().matches("[0-9]+"))){
            errorMessage+="Недопустимый возраст животного.\n";
        }
        if(animalKindLabel.getText() == null || animalKindLabel.getText() == null || !(animalKindLabel.getText().matches("[а-яА-Я]+"))){
            errorMessage+="Недопустимый вид животного.\n";
        }
        if(animalGrowthLabel.getText() == null || animalGrowthLabel.getText() == null || !(animalGrowthLabel.getText().matches("[0-9]+"))){
            errorMessage+="Недопустимый рост животного.\n";
        }
        if(animalWeightLabel.getText() == null || animalWeightLabel.getText() == null || !(animalWeightLabel.getText().matches("[0-9]+"))){
            errorMessage+="Недопустимый вес животного.\n";
        }
        if(animalNutritionLabel.getText() == null || animalNutritionLabel.getText() == null || !(animalNutritionLabel.getText().matches("[а-яА-Я]+"))){
            errorMessage+="Недопустимое питание животного.\n";
        }
        if(animalOreoHabitatLabel.getText() == null || animalOreoHabitatLabel.getText() == null || !(animalOreoHabitatLabel.getText().matches("[а-яА-Я]+"))){
            errorMessage+="Недопустимый ореон обитания животного.\n";
        }
        if(animalNatureLabel.getText() == null || animalNatureLabel.getText() == null || !(animalNatureLabel.getText().matches("[а-яА-Я]+"))){
            errorMessage+="Недопустимый характер животного.\n";
        }
        if(animalDiseasLabel.getText() == null || animalDiseasLabel.getText() == null || !(animalDiseasLabel.getText().matches("[а-яА-Я]+"))){
            errorMessage+="Недопустимый список болезней животного.\n";
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
}
