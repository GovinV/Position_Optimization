import java.awt.Graphics;
import javax.swing.JPanel;

public class Panneau extends JPanel
{
	public Rouleau r;

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(r==null)
		{
			g.drawString("Choisir son Fichier et patienter", 10, 20);
			return;
		}
		for(int i=0;i<r.taille;i++)
		{
			for(int k=0;k<r.taille;k++)
			{
				Rectangle rec=r.matrice.get(i,k);
				if(rec != null)
				{	
					Forme f;
					int max=rec.vf.size();
					for(int j=0;j<max;j++)
					{
						f=rec.vf.elementAt(j);
						if(!f.isCourbe)
							g.drawLine((int)f.courant.x/4,(int)f.courant.y/4,
								(int)f.arrive.x/4,(int)f.arrive.y/4); 	
						else
						{
							this.drawcourbe(f.courant,f.tireur1,f.tireur2,f.arrive,g);
						}
					}
				}
			}
		}
	}

	public void drawcourbe(Point c,Point t1, Point t2, Point a, Graphics g)
	{
		double t=0.0;
		double dx,ax,dy,ay;
		dx =a.x;
		dy =a.y;
		for(t=0.001;t<=1;t+=0.001)
		{
			ax =(t*t*t)*c.x+3*t*t*(1-t)*t1.x+3*t*(1-t)*(1-t)*t2.x+(1-t)*(1-t)*(1-t)*a.x;
			ay =(t*t*t)*c.y+3*t*t*(1-t)*t1.y+3*t*(1-t)*(1-t)*t2.y+(1-t)*(1-t)*(1-t)*a.y;
			g.drawLine((int)dx/4,(int)dy/4,(int)ax/4,(int)ay/4);
			dx=ax;
			dy=ay;
		}
	}


	public void addRouleau(Rouleau rec)
	{	
		this.revalidate();
		this.r=rec;
	}
}

