package casestudy;

public class passenger {
    private String name;
    private int age;
    private String gender;

    public passenger(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getDetails() {
        return "Name: " + name + ", Age: " + age + ", Gender: " + gender;
    }
}
