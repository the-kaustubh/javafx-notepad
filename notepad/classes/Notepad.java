// Notepad.java
package notepad.classes;

import java.io.*;
import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Notepad
 */
public class Notepad extends Application {

    // Stage notepad;
    String title = "Untitled";
    public static void main(String[] args) throws Exception, IOException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception, IOException {
        primaryStage.setTitle(title);

        TextArea notepad = new TextArea();
        MenuBar menuBar = getMenuBar(primaryStage, notepad);
        VBox vb = new VBox(menuBar);
        BorderPane superLayout = new BorderPane();
        superLayout.setTop(vb);

        HBox textContainer = new HBox();
        textContainer.getChildren().add(notepad);
        superLayout.setCenter(textContainer);
        
        
        Scene primaryScene = new Scene(superLayout, 800, 600);
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            Boolean choice = ConfirmClose.display(primaryStage);
            if(choice) {
                primaryStage.close();
            }
        });
        
        notepad.setMinWidth(primaryScene.getWidth()+1000);
        primaryStage.setScene(primaryScene);
        primaryStage.show();        
    }
    public MenuBar getMenuBar(Stage stage, TextArea np) throws IOException {
        MenuBar mb = new MenuBar();

        Menu m1 = new Menu("File");
        MenuItem save = new MenuItem("Save");
        MenuItem saveAs = new MenuItem("Save As");
        MenuItem close = new MenuItem("Close");
        m1.getItems().addAll(save, saveAs);
        m1.getItems().addAll(new SeparatorMenuItem(), close);

        save.setOnAction(e -> {
            String fname, data;
            if(title.equals("Untitled")) {
                // fname = ConfirmBox.display("Save File" + title, title);
                fname = title;
                data = np.getText();
                try {
                    Save(fname, data);                    
                } catch (Exception ex) {
                    e.consume(); // donno why i did this
                }
                title = fname;
                stage.setTitle(title);
                
            }
        });

        saveAs.setOnAction(e -> {
            String fname, data;
            if(title.equals("Untitled")) {
                // fname = SaveAs.display("Save File" + title, title);
                fname = title;
                data = np.getText();
                try {
                    Save(fname, data);                    
                } catch (Exception ex) {
                    e.consume();  // Remove this Later
                }
                title = fname;
                stage.setTitle(title);
                
            }
        });

        close.setOnAction(e -> {
            Boolean choice = ConfirmClose.display(stage);
            if(choice) {
                stage.close();
            }
        });



        Menu m2 = new Menu("Help");
        MenuItem m21 = new MenuItem("Welcome");
        MenuItem m22 = new MenuItem("About");
        m2.getItems().addAll(m21, m22);

        mb.getMenus().addAll(m1, m2);
        return mb;
    }

    public void Save(String filename, String data) throws IOException {
        FileWriter fw = new FileWriter(filename);
        try{
            fw.write(data);
        }
        catch(IOException ioe) {
            fw.close();
        }
        fw.close();
    }
    
}