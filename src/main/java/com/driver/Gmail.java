package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;


public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    ArrayList<Inbox> db;// Inbox DataBase
    ArrayList<Inbox> trash;
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        this.db = new ArrayList<>();
        this.trash = new ArrayList<>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(db.size()>=inboxCapacity){
            Inbox x = db.get(0);
            db.remove(x);
            trash.add(x);
        }
        Inbox ind = new Inbox(date,sender,message);
        db.add(ind);
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for (int i = 0; i< db.size(); i++){
            Inbox x = db.get(i);
            if(x.getMessage().equals(message)){
                trash.add(db.remove(i));
                break;
            }
        }

    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(db.size() == 0){
            return null;
        }else {
            Inbox x = db.get(db.size()-1);
            return x.getMessage();
        }

    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(db.size() == 0){
            return null;
        }else {
            Inbox x = db.get(0);
            return x.getMessage();
        }
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int s = 0, e = 0;
        for (int i =0; i<db.size(); i++){
            Inbox x = db.get(i);
            if(x.getDate().equals(start)){
                s = i;
            }
            if(x.getDate().equals(end)){
                e = i;
            }
        }
        return e-s+1;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return db.size();

    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return  trash.size();

    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return this.inboxCapacity;
    }
}
