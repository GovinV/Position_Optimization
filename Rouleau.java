import java.lang.Math;
public class Rouleau
{
	public MatrixRec matrice;
	public int taille;
	public double x;
	public double y;
	public double currentMaxWidth;
	public int ti,tj;
	public int lasti,lastj;
	static final double maxWitdh = 7086.6138;
	static final double maxHeight = 3543.3069;

	public Rouleau(int t)
	{
		this.taille=t;
		this.matrice =new MatrixRec(t);
		this.x=0;
		this.y=0;
		this.currentMaxWidth=0;
		this.ti=0;
		this.tj=0;
	}

	public void addRect(Rectangle r)
	{
		if(y+r.height()<maxHeight)
		{
			r.set_position(x,y);
			y+=r.height();
		}
		else
		{
			tj++;
			ti=0;
			x=currentMaxWidth;
			y=0;
			r.set_position(x,y);
			y+=r.height();	
			currentMaxWidth=x+r.width();
		}
		matrice.set(ti,tj,r);
		ti++;
		if(x+r.width()>currentMaxWidth)
		{
			currentMaxWidth=x+r.width();
		}
	}


	public void brLeftRect()
	{
		for(int j=0;j<this.taille;j++)
		{
			for(int i=0;i<this.taille;i++)
			{
				Rectangle rec=this.matrice.get(i,j);
				if(rec!=null)
				{
					double maxWitdhCol=0;
					for(int k=0;k<this.taille;k++)
					{
						for(int l=0;l<this.taille;l++)
						{
							Rectangle it=this.matrice.get(l,k);
							if(it!=null)
							{
								this.lasti=i;
								this.lastj=j;
								if((f(it.ymin())>f(rec.ymin()) && f(it.ymax())<f(rec.ymax()))
								|| (f(it.ymin())<f(rec.ymax()) && f(it.ymax())>f(rec.ymax()))
								|| (f(it.ymax())>f(rec.ymin()) && f(it.ymin())<f(rec.ymin()))
								|| (f(it.ymin())<f(rec.ymin()) && f(it.ymax())>f(rec.ymax()))
								|| f(it.ymax())==f(rec.ymax()) || f(it.ymin())==f(rec.ymin()))
								{
									if(f(it.xmax())<=f(rec.xmin()) && f(it.xmax())>maxWitdhCol)
									{
										
										maxWitdhCol=f(it.xmax());
									}
								}
							}
						}
					}
					if((maxWitdhCol)<f(rec.xmin()))
					{
						rec.set_position(maxWitdhCol,rec.ymin());
					}
				}
			}
		}
	}

	public void brUpRect()
	{
		for(int j=0;j<this.taille;j++)
		{
			for(int i=0;i<this.taille;i++)
			{
				Rectangle rec=this.matrice.get(i,j);
				if(rec!=null)
				{
					double maxHeightCol=0;
					for(int k=0;k<this.taille;k++)
					{
						for(int l=0;l<this.taille;l++)
						{
							Rectangle it=this.matrice.get(l,k);
							if(it!=null)
							{
								if(f(it.ymax())<=f(rec.ymin()))
								{
									if((f(it.xmin())>f(rec.xmin()) && f(it.xmax())<f(rec.xmax()))
									|| (f(it.xmin())<f(rec.xmax()) && f(it.xmax())>f(rec.xmax()))
									|| (f(it.xmax())>f(rec.xmin()) && f(it.xmin())<f(rec.xmin()))
									|| (f(it.xmin())<f(rec.xmin()) && f(it.xmax())>f(rec.xmax()))
									|| f(it.xmax())==f(rec.xmax()) || f(it.xmin())==f(rec.xmin()))
									{
										if(f(it.ymax())>maxHeightCol)
										{
											maxHeightCol=f(it.ymax());
										}
									}
								}
							}
						}
					}
					if((maxHeightCol)<f(rec.ymin()))
					{
						rec.set_position(rec.xmin(),maxHeightCol);
					}
				}
			}
		}
	}

	public void brLeftUpRect()
	{
		for(int j=this.lastj;j>=0;j--)
		{
			for(int i=this.lasti;i>=0;i--)
			{
				Rectangle rec=this.matrice.get(i,j);
				if(rec!=null)
				{
					if(matrice.curHeight()+rec.height()<maxHeight)
						rec.set_position(0.0,matrice.curHeight());
					else
					{
						Point possiblePos=matrice.findPlace(rec);
						if(possiblePos.x<rec.xmin())
							rec.set_position(possiblePos.x,possiblePos.y);
					}
				}
			}
		}
	}

	public double f(double x)
	{
		return Math.floor(x * 100) / 100;
	}
}