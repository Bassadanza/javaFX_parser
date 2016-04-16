package org.viewControllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.controllers.settingsController;

public class settingsViewController {
    @FXML
    private CheckBox email;
    @FXML
    private ComboBox<String> hostsBox;
    @FXML
    private ComboBox<String> domainBox;
    @FXML
    private TextField loginField;
    @FXML
    private TextField passwordField;
    @FXML
    private CheckBox vk;
    @FXML
    private Button vkAuth;
    @FXML
    private CheckBox sms;
    @FXML
    private Button chooseFile;
    @FXML
    private Button goNext;

//    private Stage settingStage;

    @FXML
    private void initialize() {
        settingsController.getSettings();
        getSettings();
    }

 //   public void setSettingStage(Stage settingStage) {
 //       this.settingStage = settingStage;
 //   }

    private void getSettings() {
        email.setSelected(settingsController.getEmail());
        hostsBox.setItems(settingsController.getHosts());
        loginField.setText(settingsController.getLoginField());
        vk.setSelected(settingsController.getVk());
        sms.setSelected(settingsController.getSms());
    }

    @FXML
    private void choosingHostsBox() {
        domainBox.setItems(settingsController.setDomainBox(hostsBox).getItems());
    }

    @FXML
    private void choosingDomainBox() {
        try {
            settingsController.setSmtpHost(domainBox.getValue());
        } catch (Exception e) {
        }
    }

    @FXML
    private void choosingFile() {
        settingsController.choosingFile();
    }

    @FXML
    private void dndFileChoose(){

    }

    @FXML
    private void goNext() {
        settingsController.setSettings(email.isSelected(),domainBox.getValue(),loginField.getText(),passwordField.getText(),vk.isSelected(),sms.isSelected());
    }
}
