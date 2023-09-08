package entities;

public class Human {
    String name;
    int age;
    int weight;
    int height;

    public Human(String name, int age, int weight, int height) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    public void info () {
        System.out.println(this.name);
        System.out.println(this.age);
        System.out.println(this.weight);
        System.out.println(this.height);
    }
}
