package org;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.viewControllers.settingsViewController;

public class Main extends Application {

    private static Stage settingsStage;
    private AnchorPane rootLayout;

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
    @Override
    public void start(Stage stage) throws Exception {

        settingsStage = stage;
        settingsStage.setTitle("Тестовый парсер");

        initSettingsStage();
    }



    private void initSettingsStage() {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/settingsView.fxml"));
            rootLayout = loader.load();
            settingsStage.setScene(new Scene(rootLayout));

            settingsViewController controller = loader.getController();
            controller.setSettingStage(settingsStage);


            settingsStage.show();
        }
        catch(Exception e){
            System.out.print(e);
        }
    }

    public static Stage getSettingsStage() {
        return settingsStage;
    }
}
