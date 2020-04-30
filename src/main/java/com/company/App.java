
/** This is very simple and raw version of JavaFX program for code editing.
 * Every behaviour of editor is not yet implemented.
 *
 * @author: Aikio
 * @version: 1.0
 * @since: 2020.04.29
 * */

/** package */
package com.company;

/** import */
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SplitPane;
import javafx.animation.RotateTransition;
import java.util.Optional;
import javafx.util.Duration;
import java.io.File;
import com.company.util.FileHandler;

/** class App */
public class App extends Application {
    @Override
    public void start(Stage stage) {
        /** setting title */
        stage.setTitle("JavaFX code editor");

        /** creating new textareas, colorpicker, menubar, splitpane */
        TextArea textarea = new TextArea();
        TextArea textarea2 = new TextArea();
        ColorPicker colorPicker = new ColorPicker();
        MenuBar menuBar = new MenuBar();
        ScrollPane scrollPane = new ScrollPane();
        SplitPane splitPane = new SplitPane();

        /** creating file menu */
        Menu filemenu = new Menu("File");
        MenuItem menuItemnew = new MenuItem("New");
        menuItemnew.setOnAction(e -> {
            System.out.println("New created");
        });
        MenuItem menuItemopen = new MenuItem("Open");
        menuItemopen.setOnAction(e -> {
            //System.out.println("File opened");
            FileChooser fileChooser1 = new FileChooser();
            fileChooser1.setInitialDirectory(new File("C:/Code_Training_Camp/testi_filet/"));
            File selectedFile = fileChooser1.showOpenDialog(stage);
            String selfile = selectedFile.toString();
            //System.out.println(selfile);
            FileHandler filehandler = new FileHandler(selfile);
            String content = filehandler.open();
            textarea.setText(content);
        });
        MenuItem menuItemsave = new MenuItem("Save");
        menuItemsave.setOnAction(e -> {
            //FileChooser fileChooser2 = new FileChooser();
            FileChooser fileChooser2 = new FileChooser();
            fileChooser2.setInitialDirectory(new File("C:/Code_Training_Camp/testi_filet/"));
            File selectedFile = fileChooser2.showOpenDialog(stage);
            String selfile2 = selectedFile.toString();
            FileHandler filehandler = new FileHandler(selfile2);
            filehandler.save(textarea.getText());
            System.out.println("File saved");
        });
        MenuItem menuItemsaveas = new MenuItem("Save as");
        menuItemsaveas.setOnAction(e -> {
                String initialpath = "C:/Code_Training_Camp/testi_filet/";
                TextInputDialog dialog = new TextInputDialog("file");
                dialog.setTitle("Text Input Dialog");
                dialog.setHeaderText("Your file will be save initial path");
                dialog.setContentText("Please enter your file name:");
                // Traditional way to get the response value.
                Optional<String> result = dialog.showAndWait();
                String setpath = "";
                if (result.isPresent()) {
                    setpath = initialpath + result.get();
                    //System.out.println(setpath);
                }
            FileHandler filehandler = new FileHandler(setpath);
            filehandler.save(textarea.getText());
            System.out.println("File saved");
        });
        MenuItem menuItemexit = new MenuItem("Exit");
        menuItemexit.setOnAction(e -> {
            //System.out.println("exit");
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Are you sure you want to exit?");
            alert.setContentText("Are you ok with this?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                // ... user chose OK
                System.out.println("exit");
                System.exit(0);
            } else {
                // ... user chose CANCEL or closed the dialog
                System.out.println("continue");
            }
            //System.exit(0);
        });

        filemenu.getItems().add(menuItemnew);
        filemenu.getItems().add(menuItemopen);
        filemenu.getItems().add(menuItemsave);
        filemenu.getItems().add(menuItemsaveas);
        filemenu.getItems().add(menuItemexit);

        /** creating edit menu */
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
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Are you sure you want to clear all?");
            alert.setContentText("Are you ok with this?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                // ... user chose OK
                System.out.println("Cleared all");
                textarea.setText("");
            } else {
                // ... user chose CANCEL or closed the dialog
                System.out.println("not cleared");
            }
        });

        editmenu.getItems().add(menuItemcut);
        editmenu.getItems().add(menuItemcopy);
        editmenu.getItems().add(menuItempaste);
        editmenu.getItems().add(menuItemclear);

        /** creating about menu */
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

        /** creating menubar */
        menuBar.getMenus().add(filemenu);
        menuBar.getMenus().add(editmenu);
        menuBar.getMenus().add(aboutmenu);

        /** creating vbox for menubar */
        VBox vBox = new VBox(menuBar);


        /** creating Choiceboxes */
        ChoiceBox choiceBoxfont = new ChoiceBox();
        choiceBoxfont.getItems().addAll("serif","sans-serif","Helvetica","Arial");

        ChoiceBox choiceBoxtab = new ChoiceBox();
        choiceBoxtab.getItems().addAll("normal","4 spaces");


        /** creating textfields*/
        TextField textField2 = new TextField();
        TextField textField3 = new TextField();

        /** creating buttons */
        Button buttonchoice = new Button("Click to change font,size and color");
        Button buttonfont = new Button("font");
        Button buttonsize = new Button("size");
        Button buttoncolor = new Button("color");
        Button buttontab = new Button("click tab selection");
        Button buttonsearch = new Button("search");
        Button buttonforward = new Button(">");
        Button buttonbackward = new Button("<");
        Button buttonclickme = new Button("click me");

        /** choice button for selecting font, size and color t*/
        buttonchoice.setOnAction(action -> {
            Color valuecolor = colorPicker.getValue();
            String color = "#" + (valuecolor.toString().substring(2,8));
            String font = (String) choiceBoxfont.getValue();
            System.out.println(font);
            String size = (textField2.getText());
            //textarea.setStyle("-fx-font-size: " + (textField2.getText()) + ";");
            textarea.setStyle("-fx-font-family: " + font + "; -fx-font-size: " + size +"; -fx-text-fill: " + color +";");
        });

        /** setting tabulator */
        buttontab.setOnAction(action -> {
            String tab = (String) choiceBoxtab.getValue();
            if (tab.equals("4 spaces")) {
                //String key = KeyCode.TAB.toString();
                //if (key.equals("\t"))
                textarea.setOnKeyPressed(e -> {
                  String key = e.getCode().toString();
                    if ((key).equals("TAB")) {
                        int position = textarea.getCaretPosition();
                        textarea.replaceText(position-1, position, "    ");
                    }
                });
            } else if (tab.equals("normal")) {
                System.out.println("normal tab");
                textarea.setOnKeyPressed(e -> {
                    String key2 = e.getCode().toString();
                    if (key2.equals("TAB")) {
                        textarea.replaceText(textarea.getCaretPosition()-1, (textarea.getCaretPosition()), "\t");
                    }
                });
            }
        });

        /** search operation */
        buttonsearch.setOnAction(action -> {
            String word = (textField3.getText());
            int index = textarea.getText().indexOf(word);
            if (index == -1) {
                System.out.println("Not found");
            } else {
                //textarea.selectRange(textField3.getText().charAt(0),textField3.getText().length());
                textarea.selectRange(index,(index + word.length()));
            }
            buttonforward.setOnAction(eteen -> {
                textarea.forward();
                int indexcaret = textarea.getCaretPosition();
                int pituus =textarea.getLength();
                if (((pituus - indexcaret) < word.length()) || (indexcaret == -1)) {
                    System.out.println("Not found");
                } else {
                    int index2 = textarea.getText(indexcaret, (pituus)).indexOf(word);
                    int index3 = index2 + indexcaret;
                    System.out.println(index3);
                    textarea.selectRange(index3, (index3 + word.length()));
                }
            });
            buttonbackward.setOnAction(taakse -> {
                textarea.backward();
                int indexcaret2 = textarea.getCaretPosition();
                if (((indexcaret2) < word.length()) || (indexcaret2 == 0)) {
                    System.out.println("Not found");
                } else {
                    int index4 = textarea.getText(0, indexcaret2).lastIndexOf(word);
                    textarea.selectRange(index4, (index4 + word.length()));
                }
            });
        });

        /** button click me animation */
        buttonclickme.setOnAction(rotate -> {
            RotateTransition rotateTransition = new RotateTransition();
            rotateTransition.setDuration(Duration.millis(400));
            rotateTransition.setByAngle(360);
            rotateTransition.setAutoReverse(true);
            rotateTransition.setCycleCount(2);

            rotateTransition.setNode(buttonclickme);
            rotateTransition.play();
        });

        /** layout settings*/
        HBox topControl = new HBox(textarea);
        HBox bottomControl = new HBox(textarea2);
        splitPane.getItems().addAll(topControl,bottomControl);

        HBox hbox = new HBox(buttonchoice,buttonfont,choiceBoxfont,buttonsize,textField2,buttoncolor,colorPicker);
        HBox hbox2 = new HBox(buttontab,choiceBoxtab,buttonsearch,textField3,buttonforward,buttonbackward,buttonclickme);
        VBox vBox3 = new VBox(menuBar,hbox,hbox2);
        BorderPane layout = new BorderPane();
        layout.setTop(vBox3);
        layout.setCenter(splitPane);
        //layout.setBottom(splitPane);
        //scrollPane.setContent(layout);

        Scene content = new Scene(layout, 800, 480);

        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(content);
        stage.show();


    }
    public static void main(String args[]) {
        launch(args);
    }
}
