package application;

import javafx.scene.layout.BorderPane;

public class PaneOrganizer {
    private BorderPane _root;
    private Board _board;

    public PaneOrganizer() {
        _root = new BorderPane();
        _board = new Board();
        _root.setCenter(_board.getBoard());
    }

    public BorderPane getRoot() {
        return _root;
    }
}
