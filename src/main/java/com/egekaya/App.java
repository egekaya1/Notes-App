package com.egekaya;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.SplitPane;

public class App extends Application
{
    private ObservableList<String> noteTitles = FXCollections.observableArrayList();
    private Map<String, String> noteContents = new HashMap<>();
    private String currentNoteTitle = null;
    private int noteCounter = 1;
    
    @Override
    public void start(Stage primaryStage) {

        BorderPane mainLayout = new BorderPane();

        ListView<String> notesList = new ListView<>(noteTitles);
        notesList.setMinWidth(250);
        
        HBox toolbar = new HBox(10);
        Button createButton = new Button("Create Note");
        createButton.setOnAction(event -> {
            String title = "Note " + noteCounter++;

            noteTitles.add(title);
            noteContents.put(title, "");
            
            notesList.getSelectionModel().select(title);
        });
        Button deleteButton = new Button("Delete");
        toolbar.getChildren().addAll(createButton, deleteButton);
        toolbar.setStyle("-fx-padding: 10;");

        SplitPane splitPane = new SplitPane();

        
        TextArea editorArea  = new TextArea();
        editorArea.setPromptText("Select or create a note to start.");

        splitPane.getItems().addAll(notesList, editorArea);
        splitPane.setDividerPositions(0.3);

        mainLayout.setTop(toolbar);
        mainLayout.setCenter(splitPane);
        
        Scene scene = new Scene(mainLayout,1000,600);
        primaryStage.setTitle("Notes - Ege Kaya");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main( String[] args )
    {
        launch(args);
    }
}
