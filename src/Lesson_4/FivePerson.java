package Lesson_4;

public class FivePerson {

    private String fullName;
    private String position;
    private String email;
    private String phone;
    private int salary;
    private int age;

    public FivePerson(String fullName, String position, String email, String phone, int salary, int age) {
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public void seeInfo() {
        System.out.println("ФИО: " + fullName);
        System.out.println("Должность: " + position);
        System.out.println("Email: " + email);
        System.out.println("Телефон: " + phone);
        System.out.println("Зарплата: " + salary + "₽");
        System.out.println("Возраст: " + age);
        System.out.println();
    }

    public String getFullName() {
        return fullName;
    }

    public String getPosition() {
        return position;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public static void main(String[] args) {

        FivePerson[] persArray = new FivePerson[5];

        persArray[0] = new FivePerson("Артур Тигранович Микаэлян", "Управляющий в итальянском ресторане «Silverio»", "a.micailyan@ya.ru", "8(800)555-35-35", 181818, 25);
        persArray[1] = new FivePerson("Эдуард Васильевич Кузьмин", "Живая реклама котлет (ростовой костюм)", "kuytyzayya@ya.ru", "+7(987)177-20-08", 500, 23);
        persArray[2] = new FivePerson("Алла Юрьевна Гришко", "Секретарь", "a.lla@ya.ru", "+7(988)174-19-88", 50000, 20);
        persArray[3] = new FivePerson("Александр Сильвестрович Сергеев", "Заместитель генерального директора в страховой компании «Меридиан»", "alexanderseethestar@gmail.com", "+7(777)777-77-70", 1000, 19);
        persArray[4] = new FivePerson("Татьяна Николаевна Архипова", "Администратор кафе «Милана»", "arhipova.ta@ya.ru", "+7(986)164-19-86", 30000, 25);

        for (FivePerson person : persArray) {
            person.seeInfo();
        }
    }
}
