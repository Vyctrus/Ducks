public class Duck implements Comparable<Duck>{
    int height;
    int weight;
    Float beingTall;
    int startingIndex;

    public Duck(int height,int weight,int startingIndex){
        this.height=height;
        this.weight=weight;
        this.beingTall= (float)height/(float)weight;
        this.startingIndex=startingIndex;
    }

    @Override
    public int compareTo(Duck o) {
        return this.beingTall.compareTo(o.beingTall);
    }
}
