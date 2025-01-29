package agh.ics.oop.model;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import agh.ics.oop.presenter.SimulationPresenter;

public class SimulationApp extends Application {
    private SimulationPresenter presenter;
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
            BorderPane viewRoot = loader.load();
            SimulationPresenter presenter = loader.getController();

            configureStage(primaryStage, viewRoot);
            primaryStage.show();

            GrassField map = new GrassField(10);
            presenter.setWorldMap(map);
            map.addObserver(presenter);
            presenter.drawMap();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }
}
