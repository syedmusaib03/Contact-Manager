import java.io.*;
import java.util.*;

public class ContactManager {
    static Scanner scan = new Scanner(System.in);
    static List<Contact> contacts = new ArrayList<>();
    static final String FILE = "contacts.txt";

    public static void main(String[] args) {
        load();
        while (true) {
            System.out.println("\n1. Add Contact");
            System.out.println("2. Search Contact");
            System.out.println("3. View Contacts");
            System.out.println("4. Exit");
            System.out.print("Choose: ");
            String choice = scan.nextLine();
            if (choice.equals("1")) add();
            else if (choice.equals("2")) search();
            else if (choice.equals("3")) view();
            else if (choice.equals("4")) { save(); break; }
            else System.out.println("Try again!");
        }
    }

    static void add() {
        System.out.print("Name: ");
        String name = scan.nextLine();
        System.out.print("Phone: ");
        String phone = scan.nextLine();
        contacts.add(new Contact(name, phone));
        System.out.println("Contact added!");
    }

    static void search() {
        System.out.print("Name to search: ");
        String name = scan.nextLine().toLowerCase();
        boolean found = false;
        for (Contact c : contacts) {
            if (c.name.toLowerCase().contains(name)) {
                System.out.println(c.name + " - " + c.phone);
                found = true;
            }
        }
        if (!found) System.out.println("Not found.");
    }

    static void view() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts yet.");
            return;
        }
        for (Contact c : contacts) {
            System.out.println(c.name + " - " + c.phone);
        }
    }

    static void save() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(FILE));
            for (Contact c : contacts) {
                writer.println(c.name + "," + c.phone);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Couldn't save contacts.");
        }
    }

    static void load() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",", 2);
                if (data.length == 2) contacts.add(new Contact(data[0], data[1]));
            }
            reader.close();
        } catch (IOException e) { }
    }
}

class Contact {
    String name, phone;
    Contact(String n, String p) { name = n; phone = p; }
}
