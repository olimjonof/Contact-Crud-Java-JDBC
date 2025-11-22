package service;

import dto.Contact;
import repository.ContactRepository;

import java.util.List;

public class ContactService {
    private ContactRepository contactRepository = new ContactRepository();

    public void addContact(Contact contact){
        //check
        Contact exists = contactRepository.getByPhone(contact.getPhone());
        if (exists!=null){
            System.out.println("Phone already exists.");
            return;
        }
        //save
        boolean result = contactRepository.saveContact(contact);
        if (result){
            System.out.println("dto.Contact added");
        }else {
            System.out.println("Something went wrong");
        }
    }
    public void contactList(){
        List<Contact> contactList = contactRepository.getList();
        for (Contact contact: contactList){
            System.out.println(contact.getName()+" "+contact.getSurname()+" "+contact.getPhone());
        }
    }
    public void deleteContact(String phone){
        /*dto.Contact contact = contactRepository.getByPhone(phone);
        if (contact == null){
            System.out.println("dto.Contact not exists!!!");
            return;
        }*/
        int effectedRows = contactRepository.delete(phone);
        if (effectedRows == 1){
            System.out.println("dto.Contact successfully deleted.");
        }else {
            System.out.println("dto.Contact not exists!!!");
        }
    }
    public void search(String query){
        List<Contact> contactList = contactRepository.search(query);
        for (Contact contact: contactList){
            System.out.println(contact.getName()+" "+contact.getSurname()+" "+contact.getPhone());
        }
    }
}
