import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SudokuGUI extends Application {
    public void start(Stage stage) {

        //10.feladat a.)
        Text meretFelirat = new Text("Új feladvány mérete:");
        TextField meretInput = new TextField("4");
        meretInput.setEditable(false);
        meretInput.setPrefWidth(40);

        Button minusGomb = new Button("-");
        Button plusGomb = new Button("+");

        Text kezdoFelirat = new Text("Kezdőállapot:");
        TextField kezdoInput = new TextField("");
        Text visszaSzamlaloFelirat = new Text("");

        Button ellenorzesGomb = new Button("Ellenőrzés");

        GridPane gridPane = new GridPane();
        gridPane.add(meretFelirat, 0, 0);
        gridPane.add(minusGomb, 1, 0);
        gridPane.add(meretInput, 2, 0);
        gridPane.add(plusGomb, 3, 0);
        gridPane.add(kezdoFelirat, 0, 2);
        gridPane.add(kezdoInput, 0, 3, 10, 1);
        gridPane.add(visszaSzamlaloFelirat, 0, 4);
        gridPane.add(ellenorzesGomb, 9, 4);

        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(gridPane, 540, 210);
        stage.setTitle("Sudoku-ellenőrző");
        stage.setScene(scene);
        stage.show();

        //10.feladat b.)
        EventHandler<MouseEvent> minusKattintasKezelo = event -> {
            int meret = Integer.parseInt(meretInput.textProperty().getValue());
            if (meret > 4) {
                meret--;
                meretInput.setText(String.valueOf(meret));
            }
        };
        minusGomb.addEventHandler(MouseEvent.MOUSE_CLICKED, minusKattintasKezelo);

        EventHandler<MouseEvent> plusKattintasKezelo = event -> {
            int meret = Integer.parseInt(meretInput.textProperty().getValue());
            if (meret < 9) {
                meret++;
                meretInput.setText(String.valueOf(meret));
            }
        };
        plusGomb.addEventHandler(MouseEvent.MOUSE_CLICKED, plusKattintasKezelo);

        //10.feladat c.)
        kezdoInput.textProperty().addListener((obs, oldText, newText) -> {
            visszaSzamlaloFelirat.textProperty().setValue("Hossz: " + newText.length());
        });

        //10.feladat d.)
        EventHandler<MouseEvent> ellenorzesKattintasKezelo = event -> {
            int elvartMeret = Integer.parseInt(meretInput.textProperty().getValue());
            elvartMeret = elvartMeret * elvartMeret;
            int inputMeret = kezdoInput.textProperty().getValue().length();
            if (elvartMeret == inputMeret) {
                infouzenetLetrehozas("A feladvány megfelelő hosszúságú!");
            } else if (inputMeret < elvartMeret) {
                infouzenetLetrehozas("A feladvány rövid: kell még " + (elvartMeret - inputMeret) + " számjegy!");
            } else {
                infouzenetLetrehozas("A feladvány hosszú: törlendő " + (inputMeret - elvartMeret) + " számjegy!");
            }
        };
        ellenorzesGomb.addEventHandler(MouseEvent.MOUSE_CLICKED, ellenorzesKattintasKezelo);
    }

    private void infouzenetLetrehozas(String uzenet) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Üzenet");
        alert.setHeaderText(null);
        alert.setContentText(uzenet);
        alert.showAndWait();
    }
}
