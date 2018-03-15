import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Optimiseur
{
	private Vector<Rectangle> vr;
    private BufferedReader lecture;
    public Panneau pan;
    private boolean pathSet;


    public Optimiseur()
    {
    	this.lecture=null;
    	this.vr = new Vector<Rectangle>();
    	this.pathSet=false;
    	this.pan=new Panneau();
    }

    public void setPath(String argv) 
    {
		this.initFich(argv);
		if(this.lecture==null)
			return;
		try
		{
			this.lire();
		} 
		catch(IOException error)
		{
			System.out.println("usage: Problem to read this file\n");
			return;
		}	
	
		this.pathSet=true;
		this.optimiser();

    }
    public void initFich(String argv)
    {
		try
		{
			this.lecture = new BufferedReader(new FileReader(argv));
		} 
		catch(IOException error)
		{
			return;
		}
	}

    public void lire() throws IOException
    {
		int lettre,dlet='\0';
		int i=0;
		Vector<Parser> lignes = new Vector<Parser>();
		String lig;
		String translateL="0,0";
		String translateG="0,0";
		String translateP="0,0";
		String tmp[]=new String[3];
		char[] mot=new char[2];
		char[] mot1= new char[5];
		boolean realGroup=false;
		boolean isGroup=false;
		boolean tslLF=false;
	    while ((lettre = lecture.read()) != -1)
	    {
	    	if(lettre=='g' && dlet=='<')
	    	{
	    		if(realGroup)
	    		{
	    			lignes.add(new Parser(translateL));
	    			isGroup=true;
	    			i++;
	    		}
	    		else
	    			realGroup=true;
	    	}
			if(lettre=='t')
			{
				lecture.read(mot1,0,5);
				if(mot1[0]=='r' && mot1[1]=='a' && mot1[2]=='n' && mot1[3]=='s' && mot1[4]=='l')
				{
					lig=lecture.readLine();
					tmp=lig.split("\\(");
					tmp=tmp[1].split("\\)");
					if(isGroup)
					{
						translateG=tmp[0];
						lignes.elementAt(i-1).setTranslateG(translateG);
					}
					else
					{
						if(!tslLF)
						{
							tslLF=true;
							translateL=tmp[0];
						}
						else
							lignes.elementAt(i-1).setTranslateP(tmp[0]);
					}
				}
			}
	    	if(lettre=='g' && dlet=='/')
	    		isGroup=false;
	    	if(lettre=='d' && dlet!='i' && dlet!='e' && realGroup)
	    	{
	    		lecture.read(mot, 0, 2);
	    		if(mot[0]=='=' && mot[1]=='"')
	    		{
	    			if(!isGroup)
	    			{
		    			lig=(lecture.readLine());
		    			lignes.add(new Parser(lig.replace((char)34,' '),translateL));
		    			i++;
	    			}
	    			else
	    			{
	    				lig=(lecture.readLine());
	    				lignes.elementAt(i-1).addSParser(lig.replace((char)34,' '));
	    			}
	    		}
	    	}
	    	dlet=lettre;
	    }
	    lecture.close();


	    for(int j=0;j<i;j++)
	    {
	    	lignes.elementAt(j).parseur();
			this.vr.add(lignes.elementAt(j).get_Rect());
	    }
	}

	public void optimiser()
	{
		if(!pathSet)
		{
			return;
		}
		int i = this.vr.size();
		Rouleau rl=new Rouleau(i);

	    for(int j=0;j<i;j++)
	    {
	    	int adThis=0;
	    	int widthMax=(int)vr.elementAt(0).width();
	    	int heightc=(int)vr.elementAt(0).height();
	    	for(int k=0;k<i-j;k++)
	    	{
	    		if((int)vr.elementAt(k).width()==widthMax)
	    		{
	    			if((int)vr.elementAt(k).height()>heightc)
	    			{
		    			widthMax=(int)vr.elementAt(k).width();
		    			heightc=(int)vr.elementAt(k).height();
		    			adThis=k;
	    			}
	    		}
	    		if((int)vr.elementAt(k).width()>widthMax)
	    		{
		    		adThis=k;
		    		widthMax=(int)vr.elementAt(k).width();
	    		}
	    	}
	    	rl.addRect(vr.remove(adThis));
	    }

	    rl.brLeftRect();
	    rl.brUpRect();
	    rl.brLeftUpRect();
	    rl.brLeftRect();
	    this.pan.addRouleau(rl);	
	    this.pan.repaint(); 
	}

} 
