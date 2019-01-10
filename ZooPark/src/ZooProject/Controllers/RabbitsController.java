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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RabbitsController implements Initializable {

    /**
     * Add your database connection values
     */
    public static final String URL = "url";
    public static final String USER = "user";
    public static final String PASSWORD = "pass";


    private static DataBaseConnection dataBaseConnection = new DataBaseConnection(URL,USER,PASSWORD);
    @FXML
    private javafx.scene.control.Button back;

    public void backButtonAction(ActionEvent actionEvent) throws IOException {
        Stage stage = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
        stage.setTitle("Городской Зоопарк Мир Животных");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../FXML/GuestPanel.fxml"));
        stage.getIcons().add(new Image(this.getClass().getResource("../Pictures/MainLogo.jpg").toString()));
        Parent root = loader.load();
        GuestPanel controller = loader.getController();
        stage.setScene(new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight()));
    }

    private final ObservableList<Animal> rabbitsData = FXCollections.observableArrayList();
    @FXML
    private TableView<Animal> rabbitsTable;
    @FXML
    private TableColumn<Animal, String> rabbitNameColumn;
    @FXML
    private TableColumn<Animal, Integer> rabbitBorderColumn;
    @FXML
    private javafx.scene.control.Label rabbitNameLabel;
    @FXML
    private javafx.scene.control.Label rabbitAgeLabel;
    @FXML
    private javafx.scene.control.Label rabbitSexLabel;
    @FXML
    private javafx.scene.control.Label rabbitGrowthLabel;
    @FXML
    private javafx.scene.control.Label rabbitWeightLabel;
    @FXML
    private javafx.scene.control.Label rabbitKindLabel;
    @FXML
    private javafx.scene.control.Label rabbitOreoHabitatLabel;
    @FXML
    private javafx.scene.control.Label rabbitNutritionLabel;
    @FXML
    private javafx.scene.control.Label rabbitDiseasLabel;
    @FXML
    private javafx.scene.control.Label rabbitNatureLabel;

    public void showRabbitsDetails(Animal rabbits) throws SQLException {
        ArrayList<Animal> rabbitsArrayList = dataBaseConnection.getAllAnimals();
        if (rabbits != null) {
            rabbitNameLabel.setText(rabbits.getName().toString());
            rabbitAgeLabel.setText((rabbits.getAge().toString()) + " мес");
            rabbitSexLabel.setText(rabbits.getSex().toString());
            rabbitGrowthLabel.setText(rabbits.getGrowth().toString() + " см");
            rabbitWeightLabel.setText(rabbits.getWeight().toString() + " гр");
            rabbitKindLabel.setText(rabbits.getKind().toString());
            rabbitOreoHabitatLabel.setText(rabbits.getOreo_habitat().toString());
            rabbitNutritionLabel.setText(rabbits.getNutrition().toString());
            rabbitDiseasLabel.setText(rabbits.getDiseas().toString());
            rabbitNatureLabel.setText(rabbits.getNature().toString());
        } else {
            rabbitNameLabel.setText("");
            rabbitAgeLabel.setText("мес");
            rabbitSexLabel.setText("");
            rabbitGrowthLabel.setText("см");
            rabbitWeightLabel.setText("гр");
            rabbitKindLabel.setText("");
            rabbitOreoHabitatLabel.setText("");
            rabbitNutritionLabel.setText("");
            rabbitDiseasLabel.setText("");
            rabbitNatureLabel.setText("");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        try {
            initData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        rabbitNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        rabbitBorderColumn.setCellValueFactory(new PropertyValueFactory<>("border"));
        rabbitsTable.setItems(rabbitsData);
        try {
            showRabbitsDetails(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        rabbitsTable.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                showRabbitsDetails(newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }));
    }

    public void initData() throws SQLException {
        ArrayList<Animal> rabbitsArrayList = dataBaseConnection.getAllAnimals();
        for (Animal rabbits: rabbitsArrayList){
            if(rabbits.getIndex_of_animal().charAt(0)=='К') {
                rabbitsData.add(new Animal(rabbits.getName(),
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
    }
}
