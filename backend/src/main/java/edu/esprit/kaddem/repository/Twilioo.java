package edu.esprit.kaddem.repository;

public interface Twilioo {
    public void sendSms(String to, String from, String body);


    public void makeCall(String from, String to);
}
