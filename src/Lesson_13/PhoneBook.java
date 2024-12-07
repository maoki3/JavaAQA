package Lesson_13;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class PhoneBook {
    private Map<String, List<String>> phoneBook;

    public PhoneBook() {
        phoneBook = new HashMap<>();
    }

    public void add(String lastName, String phoneNumber) {
        List<String> phoneNumbers = phoneBook.getOrDefault(lastName, new ArrayList<>());
        phoneNumbers.add(phoneNumber);
        phoneBook.put(lastName, phoneNumbers);
    }

    public List<String> get(String lastName) {
        return phoneBook.getOrDefault(lastName, new ArrayList<>());
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Микаэлян", "+7(800)555-35-35");
        phoneBook.add("Кузьмин", "+7(987)177-20-08");
        phoneBook.add("Гришко", "+7(988)174-19-88");
        phoneBook.add("Сергеев", "+7(777)777-77-70");
        phoneBook.add("Архипова", "+7(986)164-19-86");
        phoneBook.add("Архипова", "+7(689)461-91-68");

        System.out.println("Номера телефонов Микаэляна: " + phoneBook.get("Микаэлян"));
        System.out.println("Номера телефонов Кузьмина: " + phoneBook.get("Кузьмин"));
        System.out.println("Номера телефонов Гришко: " + phoneBook.get("Гришко"));
        System.out.println("Номера телефонов Сергеева: " + phoneBook.get("Сергеев"));
        System.out.println("Номера телефонов Архиповой: " + phoneBook.get("Архипова"));
    }
}
