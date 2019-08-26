package notepad.classes;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.*;

/**
 * ConfirmClose
 */
public class ConfirmClose {

    static boolean choice = false;
    public static boolean display(Stage st) {
        //
        Stage CloseWindow = new Stage();
        
        CloseWindow.initModality(Modality.APPLICATION_MODAL);
        CloseWindow.setTitle("KNotes");
        // CloseWindow.setMinHeight(200);
        // CloseWindow.setMinWidth(400);

        Label msg = new Label("Are you sure you want to leave without saving? ARE YOU?");
        Button yes, no;
        yes = new Button("Yup, Close");
        no = new Button("Nope, Let Me Continue");

        yes.setOnAction(e -> {
            choice = true;
            CloseWindow.close();
            st.close();
        });
        no.setOnAction(e -> {
            choice = false;
            CloseWindow.close();
        });
        VBox vb = new VBox(15.0);
        HBox hb = new HBox(15.0);
        hb.setPadding(new Insets(10, 10, 10, 10));
        vb.setPadding(new Insets(10, 10, 10, 10));

        hb.getChildren().addAll(yes, no);
        vb.getChildren().addAll(msg, hb);

        hb.setAlignment(Pos.BOTTOM_CENTER);
        vb.setAlignment(Pos.CENTER);
        Scene sc = new Scene(vb);
        CloseWindow.setScene(sc);
        CloseWindow.showAndWait();

        return choice;
    }
}