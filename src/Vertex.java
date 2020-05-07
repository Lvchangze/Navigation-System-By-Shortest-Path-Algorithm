public class Vertex {
    private final static double infinite_dis = 1000.0;
    private String name;  //节点名字
    private boolean known; //此节点之前是否已知
    private double adjuDist; //从起始点到此点的最短距离
    private Vertex parent; //当前从初始节点到此节点的最短路径下，的父节点。
    public int nowBusTime;

    public Vertex(){
        this.known = false;
        this.adjuDist = infinite_dis;
        nowBusTime = 1000;
        this.parent = null;
        this.name = "";
    }
    public Vertex(String name){
        this.known = false;
        this.adjuDist = infinite_dis;
        nowBusTime = 1000;
        this.parent = null;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean isKnown() {
        return known;
    }
    public void setKnown(boolean known) {
        this.known = known;
    }

    public double getAdjuDist() {
        return adjuDist;
    }
    public void setAdjuDist(double adjuDist) {
        this.adjuDist = adjuDist;
    }

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Vertex)) {
            throw new ClassCastException("an object to compare with a Vertext must be Vertex");
        }
        if (this.name==null) {
            throw new NullPointerException("name of Vertex to be compared cannot be null");
        }
        return this.name.equals(obj);
    }

}