package Lesson_4;

public class Park {

    private String name;
    private Attraction[] attractions;
    private int attractionCount;

    public Park(String name, int maxAttractions) {
        this.name = name;
        this.attractions = new Attraction[maxAttractions];
        this.attractionCount = 0;
    }

    public void addAttraction(String name, String workingHours, double price) {
        if (attractionCount < attractions.length) {
            attractions[attractionCount++] = new Attraction(name, workingHours, price);
        } else {
            System.out.println("Достигнуто максимальное количество аттракционов.");
        }
    }

    public void showAttractions() {
        System.out.println("Аттракционы в парке " + name + ":");
        for (int i = 0; i < attractionCount; i++) {
            attractions[i].showInfo();
        }
    }

    private class Attraction {
        private String name;
        private String workingHours;
        private double price;

        public Attraction(String name, String workingHours, double price) {
            this.name = name;
            this.workingHours = workingHours;
            this.price = price;
        }

        public void showInfo() {
            System.out.println("Название: " + name);
            System.out.println("Время работы: " + workingHours);
            System.out.println("Стоимость: " + price + " рублей");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Park park = new Park("Швейцария", 5);

        park.addAttraction("Гусеница", "09:00 - 18:00", 200);
        park.addAttraction("Колесо обозрения", "09:00 - 20:00", 300);
        park.addAttraction("Автодром", "09:00 - 18:00", 250);
        park.addAttraction("Лодки", "09:00 - 18:00", 250);
        park.addAttraction("Мельница", "09:00 - 18:00", 200);

        park.showAttractions();
    }
}
