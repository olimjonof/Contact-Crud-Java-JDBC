package controller;

import dto.Contact;
import service.ContactService;
import util.DataBaseUtil;

import java.util.Scanner;

public class ContactController {
    private final ContactService contactService = new ContactService();
    private final Scanner strScanner = new Scanner(System.in);
    private final Scanner numScanner = new Scanner(System.in);
    public void start(){
        DataBaseUtil.createTable();
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
    public void addContact(){
        System.out.print("Enter name : ");
        String name = strScanner.next();

        System.out.print("Enter surname : ");
        String surname = strScanner.next();

        System.out.print("Enter phone : ");
        String phone = strScanner.next();

        Contact contact = new Contact();
        contact.setName(name);
        contact.setSurname(surname);
        contact.setPhone(phone);

        contactService.addContact(contact);
    }
    public void contactList(){
        contactService.contactList();
    }
    public void deleteContact(){
        System.out.println("Enter phone:");
        String phone = strScanner.next();
        contactService.deleteContact(phone);
    }
    public void search(){
        System.out.println("Enter query:");
        String query = strScanner.next();
        contactService.search(query);
    }
    public void showMenu(){
        System.out.println("*** Menu ***");
        System.out.println("1 - Add Contact");
        System.out.println("2 - Contact List");
        System.out.println("3 - Delete Contact");
        System.out.println("4 - Search");
        System.out.println("0 - Exit");
    }
    public int getAction(){
        System.out.print("Enter Action : ");
        return numScanner.nextInt();
    }
}
