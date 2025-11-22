import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        start();

    }
    public static void start(){
        dataBaseUtil.createTable();
        boolean b = true;
        while (b){
            showMenu();
            int action = getAction();
            switch (action){
                case 1:
                    System.out.println("Add Contact");
                    addContact();
                    break;
                case 2:
                    System.out.println("Contact List");
                    contactList();
                    break;
                case 3:
                    System.out.println("Delete Contact");
                    deleteContact();
                    break;
                case 4:
                    System.out.println("Search");
                    search();
                    break;
                case 0:
                    b = false;
                    break;
                default:
                    b = false;
            }

        }

    }
    public static void addContact(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter name : ");
        String name = scanner.next();

        System.out.print("Enter surname : ");
        String surname = scanner.next();

        System.out.print("Enter phone : ");
        String phone = scanner.next();

        Contact contact = new Contact();
        contact.setName(name);
        contact.setSurname(surname);
        contact.setPhone(phone);

        ContactRepository contactRepository = new ContactRepository();
        //check
        Contact exists = contactRepository.getByPhone(phone);
        if (exists!=null){
            System.out.println("Phone already exists.");
            return;
        }
        //save
        boolean result = contactRepository.saveContact(contact);
        if (result){
            System.out.println("Contact added");
        }else {
            System.out.println("Something went wrong");
        }
    }
    public static void contactList(){
        ContactRepository contactRepository = new ContactRepository();
        List<Contact> contactList = contactRepository.getList();
        for (Contact contact: contactList){
            System.out.println(contact.getName()+" "+contact.getSurname()+" "+contact.getPhone());
        }
    }
    public static void deleteContact(){
        System.out.println("Enter phone:");
        Scanner scanner = new Scanner(System.in);
        String phone = scanner.next();

        ContactRepository contactRepository = new ContactRepository();
        Contact contact = contactRepository.getByPhone(phone);
        /*if (contact == null){
            System.out.println("Contact not exists!!!");
            return;
        }*/
        int effectedRows = contactRepository.delete(phone);
        if (effectedRows == 1){
            System.out.println("Contact successfully deleted.");
        }else {
            System.out.println("Contact not exists!!!");
        }
    }
    public static void search(){
        System.out.println("Enter query:");
        Scanner scanner = new Scanner(System.in);
        String query = scanner.next();
        ContactRepository contactRepository = new ContactRepository();
        List<Contact> contactList = contactRepository.search(query);
        for (Contact contact: contactList){
            System.out.println(contact.getName()+" "+contact.getSurname()+" "+contact.getPhone());
        }
    }

    public static void showMenu(){
        System.out.println("*** Menu ***");
        System.out.println("1 - Add Contact");
        System.out.println("2 - Contact List");
        System.out.println("3 - Delete Contact");
        System.out.println("4 - Search");
        System.out.println("0 - Exit");
    }
    public static int getAction(){
        System.out.print("Enter Action : ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}