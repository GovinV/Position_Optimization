import java.util.*;
public class Forme{
	
	public Point courant;
	public Point arrive;
	public Point tireur1;
	public Point tireur2;
	boolean isCourbe;
	public Point min;
	public Point max;
	public Vector<Forme> vCL;/*Aprox curve by lines*/
	
	public Forme(Point a, Point b, Point c, Point d)
	{
		this.courant=new Point(a.getX(),a.getY());
		this.arrive=new Point(b.getX(),b.getY());
		this.tireur1=new Point(c.getX(),c.getY());
		this.tireur2=new Point(d.getX(),d.getY());
		isCourbe=true;
		vCL=new Vector<Forme>();
		this.setCurve();
	}

	public Forme(Point a, Point b)
	{
		this.courant=new Point(a.getX(),a.getY());
		this.arrive=new Point(b.getX(),b.getY());
		isCourbe=false;
	}

	public void get_min()
	{
		if(!isCourbe)
		{
			this.min=new Point(Point.minX(this.courant,this.arrive),
							   Point.minY(this.courant,this.arrive));

		}
		else
		{
			double xmin,ymin;
			int size=this.vCL.size();
			this.vCL.elementAt(0).get_min();
			xmin=this.vCL.elementAt(0).min.x;
			ymin=this.vCL.elementAt(0).min.y;
			for(int i=1;i<size;i++)
			{
				this.vCL.elementAt(i).get_min();
				if(xmin>this.vCL.elementAt(i).min.x)
					xmin=this.vCL.elementAt(i).min.x;
				if(ymin>this.vCL.elementAt(i).min.y)
					ymin=this.vCL.elementAt(i).min.y;
			}
			this.min=new Point(xmin,ymin);
		}
	}

	public void get_max()
	{
		if(!isCourbe)
		{
			this.max=new Point(Point.maxX(this.courant,this.arrive),
							   Point.maxY(this.courant,this.arrive));

		}
		else
		{
			double xmax,ymax;
			int size=this.vCL.size();
			this.vCL.elementAt(0).get_max();
			xmax=this.vCL.elementAt(0).max.x;
			ymax=this.vCL.elementAt(0).max.y;
			for(int i=1;i<size;i++)
			{
				this.vCL.elementAt(i).get_max();
				if(xmax<this.vCL.elementAt(i).max.x)
					xmax=this.vCL.elementAt(i).max.x;
				if(ymax<this.vCL.elementAt(i).max.y)
					ymax=this.vCL.elementAt(i).max.y;
			}
			this.max=new Point(xmax,ymax);
		}
	}

	public void addition(Point a)
	{
		this.courant.addition(a);
		this.arrive.addition(a);
		this.min.addition(a);
		this.max.addition(a);
		if(isCourbe)
		{
			int size=this.vCL.size();
			this.tireur1.addition(a);
			this.tireur2.addition(a);
			for(int i=0;i<size;i++)
			{
				this.vCL.elementAt(i).addition(a);
			}
		}
	}

	public void substraction(Point a)
	{
		this.courant.substraction(a);
		this.arrive.substraction(a);
		this.min.substraction(a);
		this.max.substraction(a);
		if(isCourbe)
		{
			int size=this.vCL.size();
			this.tireur1.substraction(a);
			this.tireur2.substraction(a);
			for(int i=0;i<size;i++)
			{
				this.vCL.elementAt(i).substraction(a);
			}
		}
	}

	public void setCurve()
	{
		double t=0.0;
		double dx,ax,dy,ay;
		dx =this.arrive.x;
		dy =this.arrive.y;
		for(t=0.05;t<=1;t+=0.05)
		{
			ax =(t*t*t)*courant.x+3*t*t*(1-t)*tireur1.x+3*t*(1-t)*(1-t)*tireur2.x+(1-t)*(1-t)*(1-t)*arrive.x;
			ay =(t*t*t)*courant.y+3*t*t*(1-t)*tireur1.y+3*t*(1-t)*(1-t)*tireur2.y+(1-t)*(1-t)*(1-t)*arrive.y;
			this.vCL.add(new Forme(new Point(dx,dy),new Point(ax,ay)));
			dx=ax;
			dy=ay;
		}
	}
	public String toString()
	{
		String a="\nForme:\n";
		if(!isCourbe)
			a=a+"source: "+ courant.toString()+ "\nArrive: "+arrive.toString();
		else
			a=a+" "+courant.toString()+ "\n"+arrive.toString()+
				"\n Tireurs:"+tireur1.toString()+" "+tireur2.toString();
		return a;
	}
}