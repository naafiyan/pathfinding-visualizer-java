package application;

public class Node implements Comparable<Node> {
    private int _col;
    private int _row;
    private boolean _isStart;
    private boolean _isVisit;
    private boolean _isFinish;
    private int _dist;
    private Node _prev;

    public Node(int row, int col) {
        _row = row;
        _col = col;
        _isStart = false;
        _isVisit = false;
        _isFinish = false;
        _prev = null;
    }

    public void setStart() {
        _isStart = true;
    }

    public void setFinish() {
        _isFinish = true;
    }

    public boolean getStart() {
        return _isStart;
    }

    public boolean getFinish() {
        return _isFinish;
    }

    public void setDist(int d) {
        _dist = d;
    }

    public int getDist() {
        return _dist;
    }

    public void setPrev(Node node) {
        _prev = node;
    }

    public Node getPrev() {
        return _prev;
    }

    public void setVisit(boolean visit) {
        _isVisit = visit;
    }

    public boolean getVisit() {
       return _isVisit;
    }

    public int getCol() {
        return _col;
    }

    public int getRow() {
        return  _row;
    }

    //Implement compareTo method to allow for sorting by distance in ascending order
    // In djikstra class
    @Override
    public int compareTo(Node compNode) {
        int compNodeDist = compNode.getDist();
        return _dist - compNodeDist;
    }
}
