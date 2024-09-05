package nonogram;

import nonogram.ihm.FrameNonogram;
import nonogram.metier.Metier;

import javax.swing.*;

public class Controleur
{

	private static final int VIE = 3;

	private Metier metier;
	private FrameNonogram ihm;

	private int vie;

	private boolean couleur;

	public Controleur(String file)
	{
		this.vie = VIE;

		this.couleur = true;
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
		if (args.length != 0)
		{
			new Controleur(args[0]);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Erreur : Veuillez charger une sauvegarde", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	public boolean testWin(){return this.metier.testWin();}

	public void fermer(){this.ihm.fermer();}

	public void croix2Coul(boolean val){this.couleur = val;}

	public boolean getCouleur(){return this.couleur;}

	public void moins1Vie(){this.vie -= 1;}

	public void majPanelVie(){this.ihm.majPanelVie();}

	public int getVie(){return this.vie;}

}
