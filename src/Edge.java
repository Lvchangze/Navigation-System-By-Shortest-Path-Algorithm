public class Edge{
    private Vertex startVertex;  //此有向边的起始点
    private Vertex endVertex;  //此有向边的终点
    private double weight;  //此有向边的权值
    public boolean canDrive;
    public boolean canWalk;
    public boolean canBus;
    public int busTime;

    public Edge(Vertex startVertex, Vertex endVertex, double weight) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
        canDrive = true;
        canWalk = true;
        canBus = true;
        busTime = 0;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }
    public void setStartVertex(Vertex startVertex) {
        this.startVertex = startVertex;
    }

    public Vertex getEndVertex() {
        return endVertex;
    }
    public void setEndVertex(Vertex endVertex) {
        this.endVertex = endVertex;
    }

    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
}