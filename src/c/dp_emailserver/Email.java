/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.dp_emailserver;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author Michael Lawson
 */
public class Email {
    private String sender; //email address of the email's sender
    private String subject; //subject of the email
    private String message; //message of the email
    private String[] recipients; //the email addresses of the people that receive the email

    public Email(String sender, String subject, String message, String[] recipients) {
        this.sender = sender;
        this.subject = subject;
        this.message = message;
        this.recipients = recipients;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getRecipients() {
        return recipients;
    }

    public void setRecipients(String[] recipients) {
        this.recipients = recipients;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.sender);
        hash = 17 * hash + Objects.hashCode(this.subject);
        hash = 17 * hash + Objects.hashCode(this.message);
        hash = 17 * hash + Arrays.deepHashCode(this.recipients);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Email other = (Email) obj;
        if (!Objects.equals(this.sender, other.sender)) {
            return false;
        }
        if (!Objects.equals(this.subject, other.subject)) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        if (!Arrays.deepEquals(this.recipients, other.recipients)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Email{" + "sender=" + sender + ", subject=" + subject + ", message=" + message + ", recipients=" + recipients + '}';
    }
    
    
    
}
