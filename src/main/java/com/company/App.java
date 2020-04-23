
package com.company;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.scene.control.TextInputControl;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("JavaFX");

        TextArea textarea = new TextArea();

        MenuBar menuBar = new MenuBar();

        Menu filemenu = new Menu("File");
        MenuItem menuItemnew = new MenuItem("New");
        menuItemnew.setOnAction(e -> {
            System.out.println("New created");
        });
        MenuItem menuItemopen = new MenuItem("Open");
        menuItemopen.setOnAction(e -> {
            System.out.println("File opened");
            FileChooser fileChooser1 = new FileChooser();
        });
        MenuItem menuItemsave = new MenuItem("Save");
        menuItemsave.setOnAction(e -> {
            System.out.println("File saved");
            FileChooser fileChooser2 = new FileChooser();
        });
        MenuItem menuItemexit = new MenuItem("Exit");
        menuItemexit.setOnAction(e -> {
            System.out.println("exit");
            System.exit(0);
        });

        filemenu.getItems().add(menuItemnew);
        filemenu.getItems().add(menuItemopen);
        filemenu.getItems().add(menuItemsave);
        filemenu.getItems().add(menuItemexit);

        Menu editmenu = new Menu("Edit");
        MenuItem menuItemcut = new MenuItem("Cut");
        menuItemcut.setOnAction(e -> {
            System.out.println("Cut");
            textarea.cut();
        });
        MenuItem menuItemcopy = new MenuItem("Copy");
        menuItemcopy.setOnAction(e -> {
            System.out.println("Copy");
            textarea.copy();
        });
        MenuItem menuItempaste = new MenuItem("Paste");
        menuItempaste.setOnAction(e -> {
            System.out.println("Paste");
            textarea.paste();
        });
        MenuItem menuItemclear = new MenuItem("Clear all");
        menuItemclear.setOnAction(e -> {
            System.out.println("Cleared all");
            textarea.setText("");
        });

        editmenu.getItems().add(menuItemcut);
        editmenu.getItems().add(menuItemcopy);
        editmenu.getItems().add(menuItempaste);
        editmenu.getItems().add(menuItemclear);

        Menu aboutmenu = new Menu("About");
        MenuItem menuItemapp = new MenuItem("About app");
        menuItemapp.setOnAction(e -> {
            System.out.println("About application");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("About application");
            alert.setContentText("Application has been done just training purpose");
            alert.showAndWait();
        });

        aboutmenu.getItems().add(menuItemapp);

        menuBar.getMenus().add(filemenu);
        menuBar.getMenus().add(editmenu);
        menuBar.getMenus().add(aboutmenu);

        VBox vBox = new VBox(menuBar);

        /*Button clear = new Button("Clear");
        clear.setOnAction(e -> {
            System.out.println("clear text");
            //tahan oikea tekstin puhdistus
        });*/

        //Button save = new Button("Save");

        //HBox ylapalkki = new HBox(clear, save);
        //TextArea textarea = new TextArea();

        BorderPane layout = new BorderPane();
        layout.setTop(vBox);
        layout.setCenter(textarea);

        //Group group = new Group(new Button("Hello"), new Button("World"));
        Scene content = new Scene(layout, 640, 480);


        //stage.initStyle(StageStyle.DECORATED);
        stage.initStyle(StageStyle.UTILITY);
        //stage.setWidth(640);
        //stage.setHeight(480);
        stage.setScene(content);
        stage.show();

        textarea.setOnKeyPressed(e -> {
            if (e.equals("\t")) {
                textarea.replaceText(textarea.getCaretPosition(), 1, "    ");
            }
        });


    }
    public static void main(String args[]) {
        launch(args);
    }
}
