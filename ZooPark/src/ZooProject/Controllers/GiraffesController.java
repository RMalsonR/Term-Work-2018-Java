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

public class GiraffesController implements Initializable {

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

    private final ObservableList<Animal> giraffesData = FXCollections.observableArrayList();
    @FXML
    private TableView<Animal> giraffesTable;
    @FXML
    private TableColumn<Animal, String> giraffeNameColumn;
    @FXML
    private TableColumn<Animal, Integer> giraffeBorderColumn;
    @FXML
    private javafx.scene.control.Label giraffeNameLabel;
    @FXML
    private javafx.scene.control.Label giraffeAgeLabel;
    @FXML
    private javafx.scene.control.Label giraffeSexLabel;
    @FXML
    private javafx.scene.control.Label giraffeGrowthLabel;
    @FXML
    private javafx.scene.control.Label giraffeWeightLabel;
    @FXML
    private javafx.scene.control.Label giraffeKindLabel;
    @FXML
    private javafx.scene.control.Label giraffeOreoHabitatLabel;
    @FXML
    private javafx.scene.control.Label giraffeNutritionLabel;
    @FXML
    private javafx.scene.control.Label giraffeDiseasLabel;
    @FXML
    private javafx.scene.control.Label giraffeNatureLabel;

    public void showgiraffesDetails(Animal giraffes) throws SQLException {
        ArrayList<Animal> giraffesArrayList = dataBaseConnection.getAllAnimals();
        if (giraffes != null) {
            giraffeNameLabel.setText(giraffes.getName().toString());
            giraffeAgeLabel.setText(String.format("%(.1f",(double)(giraffes.getAge())/12) + " лет/год");
            giraffeSexLabel.setText(giraffes.getSex().toString());
            giraffeGrowthLabel.setText(((double)(giraffes.getGrowth())/100) + " м");
            giraffeWeightLabel.setText(((double)(giraffes.getWeight())/1000)+ " кг");
            giraffeKindLabel.setText(giraffes.getKind().toString());
            giraffeOreoHabitatLabel.setText(giraffes.getOreo_habitat().toString());
            giraffeNutritionLabel.setText(giraffes.getNutrition().toString());
            giraffeDiseasLabel.setText(giraffes.getDiseas().toString());
            giraffeNatureLabel.setText(giraffes.getNature().toString());
        } else {
            giraffeNameLabel.setText("");
            giraffeAgeLabel.setText("лет/год");
            giraffeSexLabel.setText("");
            giraffeGrowthLabel.setText("м");
            giraffeWeightLabel.setText("кг");
            giraffeKindLabel.setText("");
            giraffeOreoHabitatLabel.setText("");
            giraffeNutritionLabel.setText("");
            giraffeDiseasLabel.setText("");
            giraffeNatureLabel.setText("");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        try {
            initData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        giraffeNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        giraffeBorderColumn.setCellValueFactory(new PropertyValueFactory<>("border"));
        giraffesTable.setItems(giraffesData);
        try {
            showgiraffesDetails(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        giraffesTable.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                showgiraffesDetails(newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }));
    }

    public void initData() throws SQLException {
        ArrayList<Animal> giraffesArrayList = dataBaseConnection.getAllAnimals();
        for (Animal giraffes: giraffesArrayList){
            if(giraffes.getIndex_of_animal().charAt(0)=='Ж') {
                giraffesData.add(new Animal(giraffes.getName(),
                        giraffes.getSex(),
                        giraffes.getNature(),
                        giraffes.getIndex_of_animal(),
                        giraffes.getBorder(),
                        giraffes.getNutrition(),
                        giraffes.getOreo_habitat(),
                        giraffes.getDiseas(),
                        giraffes.getAge(),
                        giraffes.getGrowth(),
                        giraffes.getWeight(),
                        giraffes.getKind()));
            }
        }
    }
}
