package com.mycompany.newtesting2;

import com.mycompany.db.dao.ProfileJpaController;
import com.mycompany.db.entity.Profile;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.persistence.Persistence;

public class MainApp extends Application {

    public static Parent root;
    public static Scene scene;
    public static Stage stage = new Stage();
    public static MainApp mainApp = new MainApp();
    public static ProfileJpaController profileJpaController = new ProfileJpaController(Persistence
            .createEntityManagerFactory("Testing"));

    public static Profile user = null;

    @Override
    public void start(Stage stage) throws Exception {
        mainApp.goToInterface("FXMLEntry");
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void goToInterface(String namePage) throws IOException {
        this.stage.hide();
        this.stage.close();
        this.root = FXMLLoader.load(getClass().getResource("/fxml/" + namePage + ".fxml"));
        this.scene = new Scene(root);
        boolean add = MainApp.scene.getStylesheets().add("/styles/Styles.css");
        this.stage.setTitle("JavaFX and Maven");
        this.stage.setScene(scene);
        this.stage.show();
    }

    public void goToInterface(String namePage, Stage stage) throws IOException {
        this.stage.hide();
        this.stage.close();
        this.stage = stage;
        this.root = FXMLLoader.load(getClass().getResource("/fxml/" + namePage + ".fxml"));
        this.scene = new Scene(root);
        this.scene.getStylesheets().add("/styles/Styles.css");
        this.stage.setTitle("JavaFX and Maven");
        this.stage.setScene(scene);
        this.stage.show();
    }

}
