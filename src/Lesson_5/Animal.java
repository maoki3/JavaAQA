package Lesson_5;

public class Animal {
    private static int animalCount = 0;
    protected String name;

    public Animal(String name) {
        this.name = name;
        animalCount++;
    }

    public void run(int distance) {
        System.out.println(name + " пробежал " + distance + " м.");
    }

    public void swim(int distance) {
        System.out.println(name + " проплыл " + distance + " м.");
    }

    public static int getAnimalCount() {
        return animalCount;
    }
}

class Dog extends Animal {
    private static int dogCount = 0;

    public Dog(String name) {
        super(name);
        dogCount++;
    }

    @Override
    public void run(int distance) {
        if (distance <= 500) {
            super.run(distance);
        } else {
            System.out.println(name + " не может пробежать больше 500 м.");
        }
    }

    @Override
    public void swim(int distance) {
        if (distance <= 10) {
            super.swim(distance);
        } else {
            System.out.println(name + " не может проплыть больше 10 м.");
        }
    }

    public static int getDogCount() {
        return dogCount;
    }
}

class Cat extends Animal {
    private static int catCount = 0;
    private boolean isFull = false;
    private int foodBowl = 0;

    public Cat(String name) {
        super(name);
        catCount++;
    }

    @Override
    public void run(int distance) {
        if (distance <= 200) {
            super.run(distance);
        } else {
            System.out.println(name + " не может пробежать больше 200 м.");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " не умеет плавать.");
    }

    public void eat(int foodAmount) {
        if (isFull) {
            System.out.println(name + " уже сыт.");
            return;
        }

        if (foodAmount <= foodBowl) {
            foodBowl -= foodAmount;
            isFull = true;
            System.out.println(name + " поел и теперь сыт.");
        } else {
            System.out.println(name + " не хватает еды в миске.");
        }
    }

    public void addFood(int amount) {
        foodBowl += amount;
        System.out.println("В миску добавлено " + amount + " еды. Теперь еды в миске: " + foodBowl);
    }

    public boolean isFull() {
        return isFull;
    }

    public static int getCatCount() {
        return catCount;
    }
}

class Main {
    public static void main(String[] args) {
        Dog dogAkita = new Dog("Пёс: Юки");
        dogAkita.run(99);
        dogAkita.swim(6);

        Cat catHippo = new Cat("Кот: Бегемот");
        catHippo.run(55);
        catHippo.swim(1);

        Cat catSabrina = new Cat("Кот: Салем");

        catHippo.addFood(19);
        catHippo.eat(10);
        catSabrina.eat(10);

        System.out.println("Кот: Бегемот сыт? " + catHippo.isFull());
        System.out.println("Кот: Салем сыт? " + catSabrina.isFull());
        System.out.println();
        System.out.println("Создано животных: " + Animal.getAnimalCount());
        System.out.println("Создано собак: " + Dog.getDogCount());
        System.out.println("Создано котов: " + Cat.getCatCount());
    }
}