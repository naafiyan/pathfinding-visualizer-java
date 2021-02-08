package application;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class Board {
    private GridPane _board;
    private Node _nodeArray[][];
    private Rectangle _rectArray[][];

    private Node _startNode;
    private Node _finishNode;

    private boolean _hasStart;
    private boolean _hasFinish;

    public Board() {

        // GridPane config
        _board = new GridPane();
        _board.setMaxSize(Constants.BOARD_WIDTH * Constants.NODE_DIM, Constants.BOARD_HEIGHT * Constants.NODE_DIM);
        _board.setGridLinesVisible(true);
        _board.setStyle("-fx-background-color: black");

        // Array of all the nodes on the grid
        _nodeArray = new Node[Constants.BOARD_HEIGHT][Constants.BOARD_WIDTH];
        // Array of all the rects on the GridPane (represents nodes)
        _rectArray = new Rectangle[Constants.BOARD_HEIGHT][Constants.BOARD_WIDTH];

        // Checks if board also has start and finish nodes
        _hasStart = false;
        _hasFinish = false;

        // initializes the board
        this.boardInit();
    }

    private void boardInit() {
        for (int i = 0; i < Constants.BOARD_HEIGHT; i++) {
            for (int j = 0; j < Constants.BOARD_WIDTH; j++) {
                _nodeArray[i][j] = new Node(i, j);
                Rectangle rect = new Rectangle(Constants.NODE_DIM, Constants.NODE_DIM, Color.WHITE);
                rect.setStroke(Color.BLACK);
                final int row = i;
                final int col = j;
                rect.setOnMouseClicked(e -> this.handleClick(row, col));
                _rectArray[i][j] = rect;
                _board.add(_rectArray[i][j], i, j);
            }
        }
    }

    // creates association in Controller with current instance of Board
    private void startVis() {
       new VisualizerController(this);
    }

    private void handleClick(int row, int col) {
        int rectIdx = _board.getChildren().indexOf(_rectArray[row][col]);
        Rectangle rect = (Rectangle) _board.getChildren().get(rectIdx);

        // If the board doesnt have a start node and if the node clicked is not a start node
        if(!this._hasStart && !_nodeArray[row][col].getStart()) {
            _nodeArray[row][col].setStart();
            rect.fillProperty().setValue(Color.LIGHTBLUE);
            _startNode = _nodeArray[row][col];
            _hasStart = true;
        }

        // If the board doesnt have a finish node and if the node clicked is not a finish node
        else if(!this._hasFinish && !_nodeArray[row][col].getStart() && !_nodeArray[row][col].getFinish()) {
            _nodeArray[row][col].setFinish();
            rect.fillProperty().setValue(Color.RED);
            _finishNode = _nodeArray[row][col];
            _hasFinish = true;
        } else {
            this.startVis();
        }
    }


    // TODO: add in some actual animation lol
    public void animateBoard(List<Node> order) {
        for (int i = 0; i < order.size(); i++) {
            int row = order.get(i).getRow();
            int col = order.get(i).getCol();
            int rectIdx = _board.getChildren().indexOf(_rectArray[row][col]);
            Rectangle rect = (Rectangle) _board.getChildren().get(rectIdx);
            rect.fillProperty().setValue(Color.YELLOW);
        }
    }

    // TODO: add in some actual animation lol
    public void animateShortestPath(List<Node> order) {
        for (int i = 0; i < order.size(); i++) {
            int row = order.get(i).getRow();
            int col = order.get(i).getCol();
            int rectIdx = _board.getChildren().indexOf(_rectArray[row][col]);
            Rectangle rect = (Rectangle) _board.getChildren().get(rectIdx);
            rect.fillProperty().setValue(Color.DARKBLUE);
        }
    }
    public GridPane getBoard() {
        return _board;
    }

    public Node[][] getNodeArray() {
        return _nodeArray;
    }

    public Node getStartNode() {
        return _startNode;
    }

    public Node getFinishNode() {
        return _finishNode;
    }
}
