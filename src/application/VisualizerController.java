package application;

import application.algorithms.Djikstra;

public class VisualizerController {
    private Board _board;
    private Djikstra _djikstra;

    public VisualizerController(Board board) {
        _board = board;
        _djikstra = new Djikstra(_board.getNodeArray(), _board.getStartNode(), _board.getFinishNode());
        startVisualization();
    }

    public void startVisualization() {
        _djikstra.start();
        System.out.println(_djikstra.getVisitedInOrder());
        _board.animateBoard(_djikstra.getVisitedInOrder());
        _board.animateShortestPath(_djikstra.getShortestPath(_board.getFinishNode()));
    }

}
