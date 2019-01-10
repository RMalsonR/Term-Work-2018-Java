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

public class WolfsController implements Initializable {

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

    private final ObservableList<Animal> wolfsData = FXCollections.observableArrayList();
    @FXML
    private TableView<Animal> wolfsTable;
    @FXML
    private TableColumn<Animal, String> wolfNameColumn;
    @FXML
    private TableColumn<Animal, Integer> wolfBorderColumn;
    @FXML
    private javafx.scene.control.Label wolfNameLabel;
    @FXML
    private javafx.scene.control.Label wolfAgeLabel;
    @FXML
    private javafx.scene.control.Label wolfSexLabel;
    @FXML
    private javafx.scene.control.Label wolfGrowthLabel;
    @FXML
    private javafx.scene.control.Label wolfWeightLabel;
    @FXML
    private javafx.scene.control.Label wolfKindLabel;
    @FXML
    private javafx.scene.control.Label wolfOreoHabitatLabel;
    @FXML
    private javafx.scene.control.Label wolfNutritionLabel;
    @FXML
    private javafx.scene.control.Label wolfDiseasLabel;
    @FXML
    private javafx.scene.control.Label wolfNatureLabel;

    public void showWolfsDetails(Animal wolfs) throws SQLException {
        ArrayList<Animal> wolfsArrayList = dataBaseConnection.getAllAnimals();
        if (wolfs != null) {
            wolfNameLabel.setText(wolfs.getName().toString());
            wolfAgeLabel.setText(String.format("%(.1f",(double)(wolfs.getAge())/12) + " лет/год");
            wolfSexLabel.setText(wolfs.getSex().toString());
            wolfGrowthLabel.setText(wolfs.getGrowth().toString() + " см");
            wolfWeightLabel.setText(((double)(wolfs.getWeight())/1000)+ " кг");
            wolfKindLabel.setText(wolfs.getKind().toString());
            wolfOreoHabitatLabel.setText(wolfs.getOreo_habitat().toString());
            wolfNutritionLabel.setText(wolfs.getNutrition().toString());
            wolfDiseasLabel.setText(wolfs.getDiseas().toString());
            wolfNatureLabel.setText(wolfs.getNature().toString());
        } else {
            wolfNameLabel.setText("");
            wolfAgeLabel.setText("лет/год");
            wolfSexLabel.setText("");
            wolfGrowthLabel.setText("см");
            wolfWeightLabel.setText("кг");
            wolfKindLabel.setText("");
            wolfOreoHabitatLabel.setText("");
            wolfNutritionLabel.setText("");
            wolfDiseasLabel.setText("");
            wolfNatureLabel.setText("");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        try {
            initData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        wolfNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        wolfBorderColumn.setCellValueFactory(new PropertyValueFactory<>("border"));
        wolfsTable.setItems(wolfsData);
        try {
            showWolfsDetails(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        wolfsTable.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                showWolfsDetails(newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }));
    }

    public void initData() throws SQLException {
        ArrayList<Animal> wolfsArrayList = dataBaseConnection.getAllAnimals();
        for (Animal wolfs: wolfsArrayList){
            if(wolfs.getIndex_of_animal().charAt(0)=='В') {
                wolfsData.add(new Animal(wolfs.getName(),
                        wolfs.getSex(),
                        wolfs.getNature(),
                        wolfs.getIndex_of_animal(),
                        wolfs.getBorder(),
                        wolfs.getNutrition(),
                        wolfs.getOreo_habitat(),
                        wolfs.getDiseas(),
                        wolfs.getAge(),
                        wolfs.getGrowth(),
                        wolfs.getWeight(),
                        wolfs.getKind()));
            }
        }
    }
}
