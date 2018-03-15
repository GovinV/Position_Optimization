public class Point
{
	public double x,y;
	public Point(double x, double y)
	{
		this.x=x;
		this.y=y;
	}
	public void deplace(double dx, double dy)
	{
		x += dx;
		y += dy;
	}
	public double getX()
	{
		return this.x;
	}
	public double getY()
	{
		return this.y;
	}
	public static Point addition(Point a, Point b)
	{
		Point c = new Point(a.x+b.x, a.y+b.y);
		return c;
	}

	public void addition(Point a)
	{
		this.x+=a.x;
		this.y+=a.y;
	}

	public void substraction(Point a)
	{
		this.x-=a.x;
		this.y-=a.y;
	}

  	public boolean equals(Point obj)
  	{
    	if(x == obj.getX() && y == obj.getY())
    	  return true;
    	else
      		return false;
	}

  	public double distance(Point obj)
  	{
    	return Math.sqrt((obj.getX() - x) * (obj.getX() - x) + (obj.getY() - y)*(obj.getY() - y));
	}
	
	public String toString()
	{
		return "Point de coordonees : " + x + ", " + y;
	}

	public static double maxX(Point a, Point b)
	{
		return Math.max(a.x, b.x);
	}
	public static double maxY(Point a, Point b)
	{
		return Math.max(a.y, b.y);
	}

	public static double minX(Point a, Point b)
	{
		return Math.min(a.x, b.x);
	}	
	public static double minY(Point a, Point b)
	{
		return Math.min(a.y, b.y);
	}

	public static double maxX(double a, Point b)
	{
		return Math.max(a, b.x);
	}
	public static double maxY(double a, Point b)
	{
		return Math.max(a, b.y);
	}

	public static double minX(double a, Point b)
	{
		return Math.min(a, b.x);
	}	
	public static double minY(double a, Point b)
	{
		return Math.min(a, b.y);
	}




	public static Point convertir(String s)
	{		
		if(s==null)
			return new Point(0,0);
		String[] valeurs = new String[2];
		valeurs=s.split(",");
		Double x = new Double(valeurs[0]);
		Double y = new Double(valeurs[1]);
		if(s.contains(String.valueOf('e')))
		{
			if(valeurs[0].contains(String.valueOf('e')))
				x=0.0;
			if(valeurs[1].contains(String.valueOf('e')))
				y=0.0;
		}
				
		Point p=new Point(x,y);
		return p;
	}
}