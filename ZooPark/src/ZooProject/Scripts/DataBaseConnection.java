package ZooProject.Scripts;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseConnection {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet set;


    public DataBaseConnection(String url, String user, String password) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    //RABBITS
    //language=SQL
    private static final String SQL_SELECT_ANIMALS_ALL = "SELECT * FROM animals";
    //language=SQL
    private static final String SQL_FIND_ANIMALS_ByIndex = "SELECT * FROM animals where " +
            "index_of_animal=?";
    //language=SQL
    private static final String SQL_UPDATE_ANIMALS = "UPDATE animals SET name=?, sex=?, " +
            "nature=?," +
            "index_of_animal=?, border=?, nutrition=?, oreo_habitat=?, diseas=?, age=?," +
            " growth=?, weight=?," +
            "kind=? " +
            "WHERE animal_id=?";
    //language=SQL
    private static final String SQL_DELETE_ANIMAL = "DELETE FROM animals where animal_id=?";
    //language=SQL
    private static final String SQL_ADD_ANIMAL = "INSERT INTO animals(name,sex,nature," +
                                                        "index_of_animal,border," +
                                                        "nutrition,oreo_habitat,diseas," +
                                                        "age,growth,weight,kind) " +
                                                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

    public void addAnimal(Animal animal)throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_ANIMAL);
        preparedStatement.setString(1, animal.getName());
        preparedStatement.setString(2, animal.getSex());
        preparedStatement.setString(3, animal.getNature());
        preparedStatement.setString(4, animal.getIndex_of_animal());
        preparedStatement.setInt(5, animal.getBorder());
        preparedStatement.setString(6, animal.getNutrition());
        preparedStatement.setString(7, animal.getOreo_habitat());
        preparedStatement.setString(8, animal.getDiseas());
        preparedStatement.setInt(9, animal.getAge());
        preparedStatement.setInt(10, animal.getGrowth());
        preparedStatement.setInt(11, animal.getWeight());
        preparedStatement.setString(12, animal.getKind());
        preparedStatement.execute();
    }

    public void deleteAnimalCost(long animal_id) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ANIMAL);
        preparedStatement.setInt(1, (int)animal_id);
        preparedStatement.execute();
    }

    public void updateAnimalCost(Animal animal,long animal_id) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ANIMALS);
        preparedStatement.setString(1, animal.getName());
        preparedStatement.setString(2, animal.getSex());
        preparedStatement.setString(3, animal.getNature());
        preparedStatement.setString(4, animal.getIndex_of_animal());
        preparedStatement.setInt(5, animal.getBorder());
        preparedStatement.setString(6, animal.getNutrition());
        preparedStatement.setString(7, animal.getOreo_habitat());
        preparedStatement.setString(8, animal.getDiseas());
        preparedStatement.setInt(9, animal.getAge());
        preparedStatement.setInt(10, animal.getGrowth());
        preparedStatement.setInt(11, animal.getWeight());
        preparedStatement.setString(12, animal.getKind());
        preparedStatement.setLong(13, animal_id);
        preparedStatement.execute();
    }

    public ArrayList<Animal> getAllAnimals() throws SQLException {
        Statement statement = connection.createStatement();
        set = statement.executeQuery(SQL_SELECT_ANIMALS_ALL);
        ArrayList<Animal> list = new ArrayList<>();
        while (set.next()) {
            list.add(new Animal(set.getString("name"),
                    set.getString("sex"),
                    set.getString("nature"),
                    set.getString("index_of_animal"),
                    set.getInt("border"),
                    set.getString("nutrition"),
                    set.getString("oreo_habitat"),
                    set.getString("diseas"),
                    set.getInt("age"),
                    set.getInt("growth"),
                    set.getInt("weight"),
                    set.getString("kind")
            ));
        }
        return list;
    }

    public Animal getAnimalByIndex(String index_of_animal) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_ANIMALS_ByIndex);
        statement.setString(1, index_of_animal);
        set = statement.executeQuery();
        set.next();

        return new Animal(set.getString("name"),
                set.getString("sex"),
                set.getString("nature"),
                set.getString("index_of_animal"),
                set.getInt("border"),
                set.getString("nutrition"),
                set.getString("oreo_habitat"),
                set.getString("diseas"),
                set.getInt("age"),
                set.getInt("growth"),
                set.getInt("weight"),
                set.getString("kind")
        );

    }
}