import java.lang.Math;
public class MatrixRec 
{

	private Rectangle[][] rec;
	private int taille;
	static final double maxWitdh = 7086.6138;
	static final double maxHeight = 3543.3069;


	public MatrixRec(int t)
	{
		this.rec=new Rectangle[t][t];
		this.taille=t;
	}

	public Rectangle get(int i, int j)
	{
		return rec[i][j];
	}

	public void set(int i, int j, Rectangle r)
	{
		rec[i][j]=r;
	}
	public double curHeight()
	{
		double height=0;
		for(int i=0;i<taille;i++)
		{
			for(int j=0;j<taille;j++)
			{
				if(rec[i][j]!=null)
					if(rec[i][j].ymax()>height)
						height=rec[i][j].ymax();
			}
		}
		return height;
	}
	
	public double curwidth()
	{
		double width=0;
		for(int i=0;i<taille;i++)
		{
			for(int j=0;j<taille;j++)
			{
				if(rec[i][j]!=null)
					if(rec[i][j].xmax()>width)
						width=rec[i][j].xmax();
			}
		}
		return width;
	}

	public double findHeight(double xmin, double xmax)
	{
		double maxHeightCol=0;
		for(int i=0;i<taille;i++)
		{
			for(int j=0;j<taille;j++)
			{
				if(rec[i][j]==null)
					continue;
				if((f(rec[i][j].xmin())>f(xmin) && f(rec[i][j].xmax())<f(xmax))
				|| (f(rec[i][j].xmin())<f(xmax) && f(rec[i][j].xmax())>f(xmax))
				|| (f(rec[i][j].xmax())>f(xmin) && f(rec[i][j].xmin())<f(xmin))
				|| (f(rec[i][j].xmin())<f(xmin) && f(rec[i][j].xmax())>f(xmax))
				|| f(rec[i][j].xmax())==f(xmax) || f(rec[i][j].xmin())==f(xmin))
				{
					if(f(rec[i][j].ymax())>maxHeightCol)
					{
						maxHeightCol=f(rec[i][j].ymax());
					}
				}
			}
		}
		return maxHeightCol;
	}

	public Point findPlace(Rectangle r)
	{
		for(double x =0;x<r.xmin();x+=(r.width()/2))
		{
			double height=findHeight(x,x+r.width());
			if(height+r.height()<maxHeight)
				return new Point(x,height);
		}
		return new Point(r.xmax(),r.ymax());
	}

	public double f(double x)
	{
		return Math.floor(x * 100) / 100;
	}


}