package com.jiayu.workhome;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author kangkang
 * @since 2018-10-28
 */
public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("家庭作业");
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainView.fxml"));
        Scene scene=new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(450);
        primaryStage.setMinWidth(700);
        primaryStage.show();
    }
}
