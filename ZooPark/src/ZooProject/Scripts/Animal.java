package ZooProject.Scripts;


public class Animal {

    private long animal_id;
    private String name;
    private String sex;
    private String nature;
    private String index_of_animal;


    public Integer getBorder() {
        return border;
    }

    public void setBorder(Integer border) {
        this.border = border;
    }

    private Integer border;
    private String nutrition;
    private String oreo_habitat;
    private String diseas;
    private Integer age;
    private Integer growth;
    private Integer weight;
    private String kind;

    public Animal(String name, String sex, String nature,
                  String index_of_animal, Integer border,
                  String nutrition, String oreo_habitat,
                  String diseas, Integer age, Integer growth,
                  Integer weight, String kind) {
        this.name = name;
        this.sex = sex;
        this.nature = nature;
        this.index_of_animal = index_of_animal;
        this.border = border;
        this.nutrition = nutrition;
        this.oreo_habitat = oreo_habitat;
        this.diseas = diseas;
        this.age = age;
        this.growth = growth;
        this.weight = weight;
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getIndex_of_animal() {
        return index_of_animal;
    }

    public void setIndex_of_animal(String index_of_animal) {
        this.index_of_animal = index_of_animal;
    }

    public String getNutrition() {
        return nutrition;
    }

    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }

    public String getOreo_habitat() {
        return oreo_habitat;
    }

    public void setOreo_habitat(String oreo_habitat) {
        this.oreo_habitat = oreo_habitat;
    }

    public String getDiseas() {
        return diseas;
    }

    public void setDiseas(String diseas) {
        this.diseas = diseas;
    }

    public Integer getGrowth() {
        return growth;
    }

    public void setGrowth(Integer growth) {
        this.growth = growth;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public long getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(long animal_id) {
        this.animal_id = animal_id;
    }
}
