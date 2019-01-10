package ZooProject.Controllers;

import ZooProject.Scripts.Animal;
import ZooProject.Scripts.DataBaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class EditAction {
    /**
     * Add your database connection values
     */
    public static final String URL = "url";
    public static final String USER = "user";
    public static final String PASSWORD = "pass";


    private static DataBaseConnection dataBaseConnection = new DataBaseConnection(URL,USER,PASSWORD);

    @FXML
    public TextField nameField;
    @FXML
    public TextField sexField;
    @FXML
    public TextField natureField;
    @FXML
    public TextField indexField;
    @FXML
    public TextField borderField;
    @FXML
    public TextField nutritionField;
    @FXML
    public TextField oreoHabitatField;
    @FXML
    public TextField diseasField;
    @FXML
    public TextField ageField;
    @FXML
    public TextField growthField;
    @FXML
    public TextField weightField;
    @FXML
    public TextField kindField;

    private Stage dialogStage;
    private boolean okClicked = false;
    private Animal animal;

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setAnimal(Animal animal){
        this.animal = animal;
        nameField.setText(animal.getName().toString());
        ageField.setText((animal.getAge().toString()));
        sexField.setText(animal.getSex().toString());
        growthField.setText(animal.getGrowth().toString());
        weightField.setText(animal.getWeight().toString());
        kindField.setText(animal.getKind().toString());
        oreoHabitatField.setText(animal.getOreo_habitat().toString());
        nutritionField.setText(animal.getNutrition().toString());
        diseasField.setText(animal.getDiseas().toString());
        natureField.setText(animal.getNature().toString());
        indexField.setText(animal.getIndex_of_animal().toString());
        borderField.setText(animal.getBorder().toString());
    }

    public void acceptAction(ActionEvent actionEvent) throws SQLException {
        animal.setName(nameField.getText());
        animal.setAge(Integer.parseInt(ageField.getText()));
        animal.setSex(sexField.getText());
        animal.setNutrition(nutritionField.getText());
        animal.setGrowth(Integer.parseInt(growthField.getText()));
        animal.setWeight(Integer.parseInt(weightField.getText()));
        animal.setKind(kindField.getText());
        animal.setOreo_habitat(oreoHabitatField.getText());
        animal.setDiseas(diseasField.getText());
        animal.setNature(natureField.getText());
        animal.setIndex_of_animal(indexField.getText());
        animal.setBorder(Integer.parseInt(borderField.getText()));
        updateAnimal(animal);
        okClicked = true;
        dialogStage.close();
    }
    public static void updateAnimal(Animal animal) throws SQLException {
        dataBaseConnection.updateAnimalCost(animal, animal.getAnimal_id());
    }

    public void cancelAction(ActionEvent actionEvent) {
        dialogStage.close();
    }
}
