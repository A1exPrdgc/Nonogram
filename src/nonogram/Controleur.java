package nonogram;

import nonogram.ihm.FrameNonogram;
import nonogram.metier.Metier;

public class Controleur
{

	private Metier metier;
	private FrameNonogram ihm;

	private boolean couleur;

	public Controleur(String file)
	{
		this.metier = new Metier(file);
		this.ihm = new FrameNonogram(this);
	}

	public int getTaille()
	{
		return this.metier.getTaille();
	}

	public int[][] getTabCol()
	{
		return this.metier.getTab_col();
	}

	public int[][] getTabLig()
	{
		return this.metier.getTab_lig();
	}

	public int getMaxSize(int[][] tab)
	{
		return Metier.maxSize(tab);
	}

	public char[][] getGrille(){ return this.metier.getGrille(); }

	public boolean check(int lig, int col){return this.metier.check(lig + 1, col + 1);}
	public boolean coche(int lig, int col){return this.metier.coche(lig + 1, col + 1);}

	public static void main(String[] args)
	{
		new Controleur(args[0]);
	}

	public boolean testWin(){return this.metier.testWin();}

	public void fermer(){this.ihm.fermer();}

	public void croix2Coul(boolean val){this.couleur = val;}

	public boolean getCouleur(){return this.couleur;}

}
