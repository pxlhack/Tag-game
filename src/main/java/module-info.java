module ru.pxlhack.game {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.pxlhack.game to javafx.fxml;
    exports ru.pxlhack.game;
}