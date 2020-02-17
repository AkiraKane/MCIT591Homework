package assignment_1;

public class Dog {
    String name;
    String breed;
    int age;
    double weight;

    public Dog(String dogName, String dogBreed) {
        name = dogName;
        breed = dogBreed;
        age = 0;
        weight = 125.0;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public void eat() {
        weight = weight + 0.1;
    }

    public void rename(String newName) {
        name = newName;
    }

    public void hasBirthday() {
        System.out.println("happy birthday");
        age = age + 1;
    }

    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
