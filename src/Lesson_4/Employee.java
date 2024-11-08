package Lesson_4;

public class Employee {
    private String fullName;
    private String positionAtWork;
    private String email;
    private String phoneNumber;
    private int salary;
    private int age;

    public Employee(String fullName, String positionAtWork, String email, String phoneNumber, int salary, int age) {
        this.fullName = fullName;
        this.positionAtWork = positionAtWork;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }

    public void seeInfo() {
        System.out.println("ФИО: " + fullName);
        System.out.println("Должность: " + positionAtWork);
        System.out.println("Email: " + email);
        System.out.println("Телефон: " + phoneNumber);
        System.out.println("Зарплата: " + salary +"$");
        System.out.println("Возраст: " + age);
    }


    public static void main(String[] args) {

        Employee employee = new Employee("Сергеев Сильвестр Андреевич", "Председатель попечительского совета вуза", "oligarh@ya.ru", "+7(777)777-77-77", 41667000, 53);

        employee.seeInfo();
    }
}
