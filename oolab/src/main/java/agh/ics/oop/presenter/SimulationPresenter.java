package agh.ics.oop.presenter;
import agh.ics.oop.model.*;
import agh.ics.oop.*;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.geometry.HPos;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Platform;


public class SimulationPresenter implements MapChangeListener {
    private WorldMap worldMap;
    private ExecutorService executorService;

    @FXML
    private Label infoLabel;
    @FXML
    private TextField movesField;
    @FXML
    private GridPane mapGrid;

    public void setWorldMap(WorldMap worldMap){
        this.worldMap = worldMap;
    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0)); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

    public void drawMap() {
        Platform.runLater(() -> {
            if (worldMap == null) {
                return;
            }

            clearGrid();

            Boundary bounds = worldMap.getCurrentBounds();
            int width = bounds.upperRight().getX() - bounds.lowerLeft().getX() + 1;
            int height = bounds.upperRight().getY() - bounds.lowerLeft().getY() + 1;

            for (int i = 0; i <= width; i++) {
                mapGrid.getColumnConstraints().add(new ColumnConstraints(30));
            }
            for (int j = 0; j <= height; j++) {
                mapGrid.getRowConstraints().add(new RowConstraints(30));
            }

            for (int x = 0; x < width; x++) {
                Label label = new Label(String.valueOf(bounds.lowerLeft().getX() + x));
                GridPane.setHalignment(label, HPos.CENTER);
                mapGrid.add(label, x + 1, 0); // Oś X
            }
            for (int y = 0; y < height; y++) {
                Label label = new Label(String.valueOf(bounds.upperRight().getY() - y));
                GridPane.setHalignment(label, HPos.CENTER);
                mapGrid.add(label, 0, y + 1); // Oś Y
            }
            Label cornerLabel = new Label("y/x");
            GridPane.setHalignment(cornerLabel, HPos.CENTER);
            mapGrid.add(cornerLabel, 0, 0);

            for (WorldElement element : worldMap.getElements()) {
                int x = element.getPosition().getX() - bounds.lowerLeft().getX() + 1;
                int y = bounds.upperRight().getY() - element.getPosition().getY() + 1;

                if (x > 0 && y > 0 && x <= width && y <= height) {
                    try {
                        if (element instanceof Grass) {
                            Label label = new Label(element.toString());
                            GridPane.setHalignment(label, HPos.CENTER);
                            mapGrid.add(label, x, y);
                        }
                        if (element instanceof Animal) {
                            boolean isGrass = false;
                            for (WorldElement e : worldMap.getElements()) {
                                if (e instanceof Grass && e.getPosition().equals(element.getPosition())) {
                                    isGrass = true;
                                    break;
                                }
                            }
                            if (!isGrass) {
                                Label label = new Label(element.toString());
                                GridPane.setHalignment(label, HPos.CENTER);
                                mapGrid.add(label, x, y);
                            }
                        }
                    } catch (IndexOutOfBoundsException e) {
                        System.err.println(e.getMessage());
                    }
                }
            }
        });
    }

    public void mapChanged(WorldMap worldMap, String message){
        drawMap();
    }

    public SimulationPresenter() {
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void onSimulationStartClicked(){
        String userInput = movesField.getText();
        List<MoveDirection> directions = OptionsParser.parse(userInput.split(" "));
        infoLabel.setText("Starting simulation");

        executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> simulate(directions));
    }

    private void simulate(List<MoveDirection> directions) {
        try {
            Animal animal1 = new Animal(new Vector2d(2, 2));
            Animal animal2 = new Animal(new Vector2d(3, 3));
            try {
                worldMap.place(animal1);
                worldMap.place(animal2);
            } catch (IncorrectPositionException e) {
                Platform.runLater(() -> infoLabel.setText(e.getMessage()));
                e.printStackTrace();
                return;
            }

            Boundary bounds = worldMap.getCurrentBounds();
            MoveValidator validator = position ->
                    worldMap.canMoveTo(position) &&
                            position.getX() >= bounds.lowerLeft().getX() &&
                            position.getX() <= bounds.upperRight().getX() &&
                            position.getY() >= bounds.lowerLeft().getY() &&
                            position.getY() <= bounds.upperRight().getY();

            Platform.runLater(this::drawMap);
            for (int i = 0; i < directions.size(); i++) {
                MoveDirection direction = directions.get(i);
                Animal currentAnimal = (i % 2 == 0) ? animal1 : animal2;

                Vector2d oldPos = currentAnimal.getPosition();
                MapDirection oldOrientation = currentAnimal.getOrientation();
                currentAnimal.move(direction, validator);
                Vector2d newPos = currentAnimal.getPosition();
                MapDirection newOrientation = currentAnimal.getOrientation();

                Platform.runLater(() -> {
                    drawMap();
                    if (!oldPos.equals(newPos)) {
                        infoLabel.setText("Animal moved from: " + oldPos + " to: " + newPos);
                    }
                    if (!oldOrientation.equals(newOrientation)) {
                        infoLabel.setText("Animal turned from: " + oldOrientation + " to: " + newOrientation);
                    }
                });
                Thread.sleep(500);
            }
            Platform.runLater(() -> infoLabel.setText("Simulation finished."));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
