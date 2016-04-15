package org.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class settingsController {
    private static String domain;
    private static String smtpHost;
    private static String addressFrom;
    private static boolean email;
    private static ObservableList<String> hosts = FXCollections.observableArrayList( "Mail", "Rambler");

    public static ObservableList<String> getHosts() {
        return hosts;
    }

    private static String loginField;
    private static String passwordField;
    private static boolean sms;
    private static boolean vk;

    public static boolean getEmail() {
        return email;
    }
    public static void setEmail(boolean email) {
        settingsController.email = email;
    }

    private static ComboBox<String> domainBox = new ComboBox<>();
    public static void setDomain(String domain){settingsController.domain = domain;}
    public static String getDomain(){return domain;}

    public static String getLoginField() {
        return loginField;
    }
    public static void setLoginField(String loginField) {
        settingsController.loginField = loginField;
    }

    public static void setPasswordField(String passwordField){settingsController.passwordField = passwordField;}
    public static String getPasswordField(){return passwordField;}

    public static boolean getVk() {
        return vk;
    }
    public static void setVk(boolean vk) {
        settingsController.vk = vk;
    }

    public static boolean getSms() {
        return sms;
    }
    public static void setSms(boolean sms) {
        settingsController.sms = sms;
    }

    public static ComboBox<String> setDomainBox(ComboBox<String> hostsBox) {
        domainBox.setValue(null);
        String host = hostsBox.getValue();
        if (host.equals("Mail")) {
            ObservableList<String> mailDomains = FXCollections.observableArrayList( "@mail.ru", "@inbox.ru", "@list.ru", "@bk.ru");
            domainBox.setItems(mailDomains);
        } else if (host.equals("Rambler")) {
            ObservableList<String> ramblerDomains = FXCollections.observableArrayList( "@rambler.ru");
            domainBox.setItems(ramblerDomains);
        }
        return domainBox;
    }

    public static void setAddressFrom(String addressFrom){settingsController.addressFrom=addressFrom;}
    public static String getAddressFrom(){return addressFrom;}

    public static String getSmtpPort() {
        return "25";
    }
    public static String getSmtpHost() {
        return smtpHost;
    }

    public static void setSmtpHost(String domain) {
        if (domain.equals("@mail.ru")) {
            smtpHost = "smtp.mail.ru";
        } else if (domain.equals("@inbox.ru")) {
            smtpHost = "smtp.inbox.ru";
        } else if (domain.equals("@list.ru")) {
            smtpHost = "smtp.list.ru";
        } else if (domain.equals("@bk.ru")) {
            smtpHost = "smtp.bk.ru";
        } else if (domain.equals("@rambler.ru")) {
            smtpHost = "smtp.rambler.ru";
        }
    }


    public static void getSettings(){
        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);
            smtpHost = property.getProperty("smtpHost");
            addressFrom = property.getProperty("addressFrom");
            loginField = property.getProperty("login");
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
    }

    public static void setSettings(boolean emailBox,String domainChoose,String login, String password, boolean vkBox, boolean smsBox){
        setEmail(emailBox);
        setDomain(domainChoose);
        setLoginField(login);
        setPasswordField(password);
        setVk(vkBox);
        setSms(smsBox);
        setAddressFrom(getLoginField()+getDomain());

        Properties prop = new Properties();
        OutputStream output = null;
        try {
            output = new FileOutputStream("src/main/resources/config.properties");
            prop.setProperty("login", loginField);
            prop.setProperty("smtpHost", smtpHost);
            prop.setProperty("addressFrom", addressFrom);
            prop.store(output, null);
        } catch (IOException io) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

