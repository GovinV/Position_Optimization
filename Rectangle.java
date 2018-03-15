import java.util.*;
public class Rectangle {

    public Vector<Forme> vf;
    private int count;
    private double xmin, ymin;   // minimum x- and y-coordinates
    private double xmax, ymax;   // maximum x- and y-coordinates
    private double height,width;
    private Point position;



    public Rectangle()
    {
        this.vf=new Vector<Forme>();
    }

    public void set_position(double a, double b)
    {
        this.toRel();
        this.position=new Point(a,b);
        this.toPos();
        this.set_min();
        this.set_max();

    }
    public void addForme(Forme f)
    {
        vf.add(f);
    }
    public double xmin() {
        this.set_min();
        return xmin;
    }

    public double xmax() {
        this.set_max();
        return xmax;
    }

    public double ymin() {
        this.set_min();
        return ymin;
    }

    public double ymax() {
        this.set_max();
        return ymax;
    }

    public double width() {
        this.set_min();
        this.set_max();
        this.width=xmax - xmin;
        return xmax - xmin;
    }

    public double height() {
        this.set_min();
        this.set_max();
        this.height=ymax-ymin;
        return ymax - ymin;
    }

    public boolean intersects(Rectangle that) {
        return this.xmax >= that.xmin && this.ymax >= that.ymin
            && that.xmax >= this.xmin && that.ymax >= this.ymin;
    }

    public boolean contains(Rectangle rect) {
        return (rect.xmin >= xmin) && (rect.xmax <= xmax)
            && (rect.ymin >= ymin) && (rect.ymax <= ymax);
    }

    public void set_count(int c)
    {
        this.count=c;
    }

    public void set_min()
    {
        int max=this.vf.size();
        this.vf.elementAt(0).get_min();
        Point min=this.vf.elementAt(0).min;
        for(int i=1;i<max;i++)
        {
            this.vf.elementAt(i).get_min();
            double minX,minY;
            minX=Point.minX(min,this.vf.elementAt(i).min);
            minY=Point.minY(min,this.vf.elementAt(i).min);
            min=new Point(minX,minY);
        }
        this.xmin=min.getX();
        this.ymin=min.getY();

    }

    public void set_max()
    {
        int m=this.vf.size();
        this.vf.elementAt(0).get_max();
        Point max=this.vf.elementAt(0).max;
        for(int i=1;i<m;i++)
        {
            this.vf.elementAt(i).get_max();
            double maxX,maxY;
            maxX=Point.maxX(max,this.vf.elementAt(i).max);
            maxY=Point.maxY(max,this.vf.elementAt(i).max);
            max=new Point(maxX,maxY);
        }
        this.xmax=max.getX();
        this.ymax=max.getY();
    }

    public String toString() {
        
        return this.vf.toString();
    }

    public void toPos()
    {
        this.set_min();
        this.set_max();
        Point a=this.position;
        int m=this.vf.size();
        for(int i=0;i<m;i++)
        {
            this.vf.elementAt(i).addition(a);
        }
        this.xmax+=a.x;
        this.ymax+=a.y;
        this.xmin=a.x;
        this.ymin=a.y;
    }

    public void toRel()
    {
        this.set_min();
        this.set_max();
        Point a=new Point(this.xmin,this.ymin);
        int m=this.vf.size();
        for(int i=0;i<m;i++)
        {
            this.vf.elementAt(i).substraction(a);
        }
        this.xmax-=this.xmin;
        this.ymax-=this.ymin;
        this.xmin=0;
        this.ymin=0;
    }
}




