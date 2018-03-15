import java.util.*;

public class Parser{
	private String translate;//toujours qu'il y ai majuscules ou minuscules
	private String translateG;
	private String translateP;
	private Point translation;
	private Vector<String> d;
	private Rectangle r;
	private int count;

	public Parser(String s, String t)
	{
		this.translate=new String(t);
		this.translateG=null;
		this.translateP=null;
		this.d=new Vector<String>();
		this.d.add(new String(s));
		this.r=new Rectangle();
		this.count=1;
	}

	public Parser(String t)
	{
		this.translate=new String(t);
		this.d=new Vector<String>();
		this.translateG=null;
		this.translateP=null;
		this.r=new Rectangle();
	}
	
	public void setTranslateG(String t)
	{
		this.translateG=new String(t);
	}

	public void setTranslateP(String t)
	{
		this.translateP=new String(t);
	}


	public void addSParser(String s)
	{
		this.d.add(new String(s));
		this.count++;
	}

	public void setTranslation()
	{
		translation=Point.addition(Point.convertir(translate),
			Point.addition(Point.convertir(translateG),Point.convertir(translateP)));
	}
	public void parseur()
	{
		this.setTranslation();
		for (int i=0;i<this.count;i++)
		{
			boolean rel=false;
			boolean flagl=false;
			boolean flagc=false;
			boolean pasPremPoint=true;
			Point b,c,d;
			Point a=new Point(translation.getX(),translation.getY());
			Point relM=a;
			Point premPoint=a;
			String[] sep=new String[1000];
			sep=this.d.elementAt(i).split(" ");
			for(int j=0;j<sep.length;j++)
			{
				if(sep[j].contains(String.valueOf('m')))
				{
					rel=true;
					flagc=false;
					flagl=true;
					relM=Point.addition(Point.convertir(sep[j+1]),a);
					if(pasPremPoint)
					{
						pasPremPoint=false;
						premPoint=relM;
					}
					a=relM;
					j++;
				}
				else if(sep[j].contains(String.valueOf('l')))
				{
					relM=a;
					rel=true;
					flagc=false;
					flagl=true;
				}
				else if(sep[j].contains(String.valueOf('c')))
				{
					relM=a;
					rel=true;
					flagl=false;
					flagc=true;
				}
				else if(sep[j].contains(String.valueOf('M')))
				{
					relM=a;
					rel=false;
					flagc=false;
					flagl=true;
					Point tmp=Point.addition(Point.convertir(sep[j+1]),translation);
					if(pasPremPoint)
					{
						pasPremPoint=false;
						premPoint=tmp;
					}
					a=tmp;
					j++;
				}
				else if(sep[j].contains(String.valueOf('C')))
				{
					rel=false;
					flagl=false;
					flagc=true;
				}
				else if(sep[j].contains(String.valueOf('L')))
				{
					rel=false;
					flagl=true;
					flagc=false;
				}
				else if(sep[j].contains(String.valueOf('z')))
				{
					r.addForme(new Forme(a,premPoint));
				}
				else if(sep[j].contains(String.valueOf('Z')))
				{
					r.addForme(new Forme(a,premPoint));
				}
				else if(sep[j].contains(String.valueOf(',')))
				{
					if(rel)
					{	
						b=Point.addition(Point.convertir(sep[j]),relM);
						if(flagl)
						{
							r.addForme(new Forme(a,b));
							relM=b;
						}
						else if(flagc)
						{
							c=Point.addition(Point.convertir(sep[j+1]),relM);
							d=Point.addition(Point.convertir(sep[j+2]),relM);
							r.addForme(new Forme(a,d,b,c));
							j+=2;
							relM=d;
						}
						a=relM;
					}
					else
					{
						b=Point.addition(Point.convertir(sep[j]),translation);
						if(flagl)
						{
							r.addForme(new Forme(a,b));
							a=b;
						}
						else if(flagc)
						{
							c=Point.addition(Point.convertir(sep[j+1]),translation);
							d=Point.addition(Point.convertir(sep[j+2]),translation);
							r.addForme(new Forme(a,d,b,c));
							j+=2;
							a=d;
						}
					}
					
				}
			}
		}
	}

	public Rectangle get_Rect()
	{
		this.r.set_count(this.count);
		return this.r;
	}

	public String toString()
	{
		if(count>1)
		{
			String a="GROUPE:  \n Translate"+ "\n"+ translate+ "  translateG :" + translateG;
			for(int i=0;i<count;i++)
				a+="\n" +d.elementAt(i)+ "\n";
			return a;
		}
		return translate + "\n" +"translateP :" + translateP+ "\n" +d.elementAt(0);
	}
}

//si l bah tj ligne entre les point si 
//c bah toujours tous les trois points c et si m bah comme l des lignes entre