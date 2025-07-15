/**
 * Raphael Dela Pena
 * Morgan Lee
 * Sean Bartolome
 *
 * April 22, 2025
 *
 * T05
 */

package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        HelloController cont = fxmlLoader.getController();
        cont.load(file);
        stage.setTitle("NBA Stat Tracker");
        stage.setScene(scene);
        stage.show();


    }

    private static File file = null;
    public static void main(String[] args) throws IOException {
        if(args.length > 2) {
            System.err.println("Expected one command line argument for filename to load from");
        }
        if(args.length == 1) {
            file = new File(args[0]);
            if(!file.exists()){
                file.createNewFile();
                System.err.println("Can not load from empty file");
                //System.exit(1);
            }
        }
        launch();
    }
}