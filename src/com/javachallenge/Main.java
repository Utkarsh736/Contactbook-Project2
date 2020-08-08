package com.javachallenge;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Contact> contacts;
    private static Scanner scanner;
    private static int id = 0;


    public static void main(String[] args) {
        contacts = new ArrayList<>();
        System.out.println("Welcome");

        showInitialOptions();

    }

    private static void showInitialOptions(){
        System.out.println("chose one:" +
                "\n\t1. Contacts" +
                "\n\t2. Messages" +
                "\n\t3. Quit");

        scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                manageContacts();
                break;

            case 2:
                manageMessages();
                break;

            default:
                break;
        }

    }

    private static void manageMessages() {
        System.out.println("Please select one" +
                "\n\t1. Show All Messages" +
                "\n\t2. Send A New Message" +
                "\n\t3. Go Back");
        int choice = scanner.nextInt();
                switch(choice){
                    case 1:
                        showAllMessages();
                        break;

                    case 2:
                        sendNewMessage();
                        break;

                    default:
                        showInitialOptions();
                        break;
                }
    }

    private static void sendNewMessage() {
        System.out.println("Who are you going to send a message");
        String name = scanner.next();
        if(name.equals("")){
            System.out.println("Please enter the name of contact");
            sendNewMessage();
        }else {
            boolean doesExist = false;
            for (Contact c: contacts){
                if(c.getName().equals(name)){

                    doesExist = true;

                }

            }
            if(doesExist){
                System.out.println("What are you going to say?");
                String text = scanner.next();
                if(text.equals("")){
                    System.out.println("Please enter a message ");
                    sendNewMessage();
                }else {
                    id++;
                    Message newMessage = new Message(text, name, id);

                    for(Contact c: contacts){
                        if (c.getName().equals(name)){
                            ArrayList<Message> newMessages = c.getMessagess();
                            newMessages.add(newMessage);
                            Contact currentContact = c;
                            currentContact.setMessagess(newMessages);
                            contacts.remove(c);
                            contacts.add(currentContact);
                        }
                    }

                }

            }else {
                System.out.println("There is no such contact");
            }
        }
        showInitialOptions();
    }

    private static void showAllMessages() {ArrayList<Message> allMessages = new ArrayList<>();
        for (Contact c: contacts){
            allMessages.addAll(c.getMessagess());

        }
        if (allMessages.size()>0){
            for (Message m: allMessages){
                m.getDetails();
                System.out.println("**********");
            }
        }else {
            System.out.println("You don't have any messages");
        }
        showInitialOptions();
    }

    private static void manageContacts(){
        System.out.println("choose one" +
                "\n\t1. Show All Contact" +
                "\n\t2. Add A New Contact" +
                "\n\t3. Search A Contact" +
                "\n\t4. Delete A Contact" +
                "\n\t5. Go Back");

        int choice = scanner.nextInt();

        switch (choice){
            case 1:
                showAllContacts();
                break;

            case 2:
                addNewContact();
                break;

            case 3:
                searchForContact();
                break;

            case 4:
                deleteContact();
                break;

            default:
                showInitialOptions();
                break;
        }
    }

    private static void deleteContact(){
        System.out.println("Enter contact name");
        String name = scanner.next();
        if(name.equals("")){
            System.out.println("enter name");
            deleteContact();
        }else {
            Boolean doesExist = false;

            for(Contact c: contacts){
                if(c.getName().equals(name)){
                    doesExist = true;
                    contacts.remove(c);
                }
            }
            if(!doesExist){
                System.out.println("There is no such contact");
            }
        }
        showInitialOptions();
    }

    private static void searchForContact(){
        System.out.println("enter contact name");
        String name = scanner.next();
        if(name.equals("")){
            System.out.println("enter contact name");
            searchForContact();
        }else{
            Boolean doesExist = false;
            for(Contact c: contacts){
                if(c.getName().equals(name)){
                    doesExist = true;
                    c.getDetails();
                }
            }
            if(!doesExist){
                System.out.println("Contact doesn't exist");
            }
        }
        showInitialOptions();
    }

    private static void addNewContact(){
        System.out.println("Add New Contact" +
                "\n enter contact's name");
        String name = scanner.next();
        System.out.println("enter number");
        String number = scanner.next();
        System.out.println("enter emailid");
        String email = scanner.next();

        if(name.equals("")||number.equals("")||email.equals("")){
            System.out.println("Enter all the details");

           addNewContact();
        }else {

            boolean doesExist = false;
            for (Contact c: contacts){
                if (c.getName().equals(name)){
                    doesExist = true;
                }
            }
            if (doesExist){
                System.out.println("We already have contact " + name + "saved in device");
                addNewContact();
            }else {
                Contact contact = new Contact(name, number, email);
                contacts.add(contact);
                System.out.println(name + " added succesfully");
            }


            
        }
        showInitialOptions();
    }

    private static void showAllContacts(){

        for (Contact c: contacts){
            c.getDetails();
            System.out.println("***********");
        }
        showInitialOptions();

    }
}
