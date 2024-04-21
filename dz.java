import java.util.*;

public class PhoneBook {
    private HashMap<String, Set<String>> phoneBook;

    public PhoneBook() {
        phoneBook = new HashMap<>();
    }

    public void addPhoneNumber(String name, String phoneNumber) {
        phoneBook.computeIfAbsent(name, k -> new HashSet<>()).add(phoneNumber);
    }

    public void removePhoneNumber(String name, String phoneNumber) {
        if (phoneBook.containsKey(name)) {
            Set<String> numbers = phoneBook.get(name);
            numbers.remove(phoneNumber);
            if (numbers.isEmpty()) {
                phoneBook.remove(name);
            }
        }
    }

    public void removePerson(String name) {
        phoneBook.remove(name);
    }

    public void editPhoneNumber(String name, String oldNumber, String newNumber) {
        if (phoneBook.containsKey(name) && phoneBook.get(name).remove(oldNumber)) {
            phoneBook.get(name).add(newNumber);
        }
    }

    public void printPhoneBook() {
        List<Map.Entry<String, Set<String>>> entries = new ArrayList<>(phoneBook.entrySet());
        
        entries.sort((entry1, entry2) -> Integer.compare(entry2.getValue().size(), entry1.getValue().size()));
        
        for (Map.Entry<String, Set<String>> entry : entries) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    public void findPerson(String name) {
        if (phoneBook.containsKey(name)) {
            System.out.println(name + " -> " + phoneBook.get(name));
        } else {
            System.out.println("Person not found.");
        }
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose an option: \n1. Add Phone Number\n2. Remove Phone Number\n3. Remove Person\n4. Edit Phone Number\n5. Print Phone Book\n6. Find Person\n7. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter Name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter Phone Number:");
                    String phoneNumber = scanner.nextLine();
                    phoneBook.addPhoneNumber(name, phoneNumber);
                    break;
                case 2:
                    System.out.println("Enter Name:");
                    name = scanner.nextLine();
                    System.out.println("Enter Phone Number to Remove:");
                    phoneNumber = scanner.nextLine();
                    phoneBook.removePhoneNumber(name, phoneNumber);
                    break;
                case 3:
                    System.out.println("Enter Name to Remove:");
                    name = scanner.nextLine();
                    phoneBook.removePerson(name);
                    break;
                case 4:
                    System.out.println("Enter Name:");
                    name = scanner.nextLine();
                    System.out.println("Enter Old Phone Number:");
                    String oldNumber = scanner.nextLine();
                    System.out.println("Enter New Phone Number:");
                    String newNumber = scanner.nextLine();
                    phoneBook.editPhoneNumber(name, oldNumber, newNumber);
                    break;
                case 5:
                    phoneBook.printPhoneBook();
                    break;
                case 6:
                    System.out.println("Enter Name to Find:");
                    name = scanner.nextLine();
                    phoneBook.findPerson(name);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                    break;
            }
        }
    }
}
