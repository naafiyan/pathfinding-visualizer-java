package application.algorithms;

import application.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of Djikstra's algorithm
 * startNode dist = 0
 * everything else is Infinity (Integer.Max_Value)
 * sortByDist i.e. startNode is in _unvisitedNodes[0],
 * in first loop startNode is minDistNode since dist = 0,
 * every iteration, sort arraylist
 * neighbors get updated so that their dist = dist of prev node + 1
 * */

public class Djikstra {
    private Node[][] _nodeArray;
    private Node _startNode;
    private Node _finishNode;
    private List<Node> _unvisitedNodes;
    private List<Node> _visitedInOrder;

    public Djikstra(Node[][] nodeArray, Node startNode, Node finishNode) {
        _nodeArray = nodeArray;
        _unvisitedNodes = copyNodes(_nodeArray);
        _visitedInOrder = new ArrayList<Node>();
        _startNode = _unvisitedNodes.get(_unvisitedNodes.indexOf(startNode));
        _finishNode = _unvisitedNodes.get(_unvisitedNodes.indexOf(finishNode));
    }

    public void start() {
        System.out.println("Starting Djikstra");
        this.nodesInit();
        // sets distance of initial node to zero
        _startNode.setDist(0);
        algorithm();
    }

    private void nodesInit() {
        for (int i = 0; i < _unvisitedNodes.size(); i++) {
            _unvisitedNodes.get(i).setDist(Integer.MAX_VALUE); // set distance of all nodes to infinity
        }
    }

    private List<Node> algorithm() {
        while (_unvisitedNodes.size() > 0) {
            sortNodesByDist(_unvisitedNodes);

            // Shifts all elements forward by one
            // Initial: minDistNode = startNode
            Node minDistNode = _unvisitedNodes.remove(0);

            // marks node as visited
            minDistNode.setVisit(true);

            // adds it arraylist of all visited nodes (needed to animate board later)
            _visitedInOrder.add(minDistNode);

            // found finish node
            if(minDistNode == _finishNode) {
                // returns all the nodes that were visited
                System.out.println(_visitedInOrder.size());
                return _visitedInOrder;
            }

            // did not find node, keep moving to neighbor
            updateNeighbors(minDistNode);
        }

        // went through all the nodes and did not find a finish node
        // something went wrong
        return _unvisitedNodes;
    }

    // returns all unvisited neighbors
    private List<Node> getNeighbors(Node node) {
        // ArrayList of all the neighbor nodes
        List<Node> neighbors = new ArrayList<Node>();

        int col = node.getCol();
        int row = node.getRow();

        // node not in top row
        if (row > 0) {
            neighbors.add(_nodeArray[row-1][col]);
        }
        // node not in bottom row
        if (row < _nodeArray.length - 1) {
            neighbors.add(_nodeArray[row+1][col]);
        }
        // node not on the right-most col
        if (col < _nodeArray[0].length - 1) {
            neighbors.add(_nodeArray[row][col+1]);
        }
        // node not on the left-most col
        if(col > 0) {
            neighbors.add(_nodeArray[row][col-1]);
        }

        // only returns neighbor nodes that are not visited
        for (int i = 0; i < neighbors.size(); i++) {
            if (neighbors.get(i).getVisit()) {
                neighbors.remove(i);
            }
        }
        return neighbors;
    }

    // updates neighbors
    private void updateNeighbors(Node node) {
        List<Node> neighbors = getNeighbors(node);
        for (int i = 0; i < neighbors.size(); i++) {
            neighbors.get(i).setDist(node.getDist() + 1);
            neighbors.get(i).setPrev(node);
        }
    }

    // sort nodes by distance
    private void sortNodesByDist(List<Node> nodes) {
        Collections.sort(nodes);
        // test nodes dist
        for (int i = 0; i < nodes.size(); i++) {
            System.out.println(nodes.get(i).getDist());
        }
    }

    // convert 2 nodeArray to 1D _nodes arrayList
    public List<Node> copyNodes(Node[][] array) {
        List<Node> nodes = new ArrayList<Node>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                nodes.add(_nodeArray[i][j]);
            }
        }
        return nodes;
    }

    public List<Node> getVisitedInOrder() {
        return _visitedInOrder;
    }

    // start at finish node and backtrack to the first node
    public List<Node> getShortestPath(Node finishNode) {
        List<Node> nodesShortestPath = new ArrayList<Node>();
        Node currNode = finishNode;
        while (currNode != null) {
            nodesShortestPath.add(currNode);
            currNode = currNode.getPrev();
        }
        return nodesShortestPath;
    }
}
