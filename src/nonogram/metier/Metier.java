package nonogram.metier;

import java.io.FileInputStream;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Metier
{
	private int[][] tab_col;
	private int[][] tab_lig;
	private char[][] grille;
	private String nom;
	private int taille;
	private char tab[][];

	public Metier(String nom)
	{
		this.nom = nom;

		this.genereNonogram();
	}

	private void genereNonogram()
	{
		this.tab = file2Tab(this.nom);

		this.taille = this.tab.length;

		this.grille = new char[this.taille][this.taille];
		this.tab_lig = new int[this.taille][this.taille];
		this.tab_col = new int[this.taille][this.taille];

		int cpt_lig = 0;
		int cpt_col = 0;

		int pos_lig = 0;
		int pos_col = 0;

		for (int i=0; i < tab.length; i++)
		{
			for (int j=0; j < tab.length; j++)
			{
				if (tab[i][j] != '0')
				{
					cpt_lig++;
				}
				else
				{
					if (cpt_lig != 0)
					{
						tab_lig[i][pos_lig] = cpt_lig;
						cpt_lig = 0;
						pos_lig++;
					}
				}
			}
			if (tab[i][tab.length - 1] == '1')
			{
				tab_lig[i][pos_lig] = cpt_lig;
			}
			cpt_lig = 0;
			pos_lig = 0;
		}

		for (int i=0; i < tab.length; i++)
		{
			for (int j=0; j < tab.length; j++)
			{
				if (tab[j][i] != '0')
				{
					cpt_col++;
				}
				else
				{
					if (cpt_col != 0)
					{
						tab_col[i][pos_col] = cpt_col;
						pos_col++;
						cpt_col = 0;
					}
				}
			}
			if (tab[tab.length - 1][i] == '1')
			{
				tab_col[i][pos_col] = cpt_col;
			}
			cpt_col = 0;
			pos_col = 0;
		}

		for (int i=0; i < this.taille; i++)
		{
			for (int j=0; j < this.taille; j++)
			{
				this.grille[i][j] = ' ';
			}
		}
	}

	private static char[][] file2Tab(String file)
	{
		try
		{
			Scanner sc = new Scanner ( new FileInputStream( file ) );

			int taille = sc.nextLine().length();
			sc.close();

			sc = new Scanner ( new FileInputStream( file ) );

			char[][] res = new char[taille][taille];

			int lig = 0;
			while ( sc.hasNextLine() )
			{
				String line = sc.nextLine();
				for (int col=0; col < line.length(); col++)
				{
					if (col >= taille)
					{
						JOptionPane.showMessageDialog(null, "Erreur : Sauvegarde invalide (taille invalide)", "Erreur", JOptionPane.ERROR_MESSAGE);
						System.exit(0);
					}
					if(line.charAt(col) == '0' || line.charAt(col) == '1')
					{
						res[lig][col] = line.charAt(col);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Erreur : Sauvegarde invalide (caractère invalide)", "Erreur", JOptionPane.ERROR_MESSAGE);
						System.exit(0);
					}
				}
				lig++;
			}
			sc.close();

			return res;
		}
		catch (Exception e){ e.printStackTrace(); return null;}
	}

	public static int maxSize(int[][] tab)
	{
		int max = 0;
		int cpt = 0;
		for (int i=0; i < tab.length; i++)
		{
			for (int j=0; j < tab.length; j++) {
				if (tab[i][j] != 0)
				{
					cpt++;
				}
			}
			if (cpt > max)
			{
				max = cpt;
			}
			cpt = 0;
		}
		return max;
	}

	public String toString()
	{

		int max_lig = maxSize(this.tab_lig);
		String s = "\nTest : \n";
		String temp;

		int size;

		boolean empty;

		for (int i=0; i < this.tab_col.length; i++)
		{
			empty = true;

			temp = "";

			for (int j=0; j < this.tab_col.length; j++)
			{
				if (tab_col[j][i] != 0)
				{
					temp += tab_col[j][i];
					empty = false;
				}
				else
				{
					temp += " ";
				}
				temp += "|";
			}
			if(!empty)
			{
				s += "\n";

				for (int j=0; j < max_lig * 2; j++) {
					s+=" ";
				}
				s+= "|" + temp;
			}
		}



		for (int i=0; i < this.tab_lig.length; i++)
		{
			s += "\n|";
			size = 0;
			for (int j=0; j < this.tab_lig.length; j++)
			{
				if (tab_lig[i][j] != 0)
				{
					s += tab_lig[i][j] + "|";
					size++;
				}
			}
			while(size < max_lig)
			{
				s += " |";
				size++;
			}
			for (int j=0; j < this.tab_lig.length; j++)
			{
				s += this.grille[i][j] + "|";
			}

		}

		return s;
	}

	public boolean check(int lig, int col)
	{
		int true_lig;
		int true_col;

		if (lig > this.taille)
		{
			true_lig = this.taille - 1;
		}
		else
		{
			if (lig < 1)
			{
				true_lig = 0;
			}
			else
			{
				true_lig = lig - 1;
			}
		}

		if (col > this.taille)
		{
			true_col = this.taille - 1;
		}
		else
		{
			if (col < 1)
			{
				true_col = 0;
			}
			else
			{
				true_col = col - 1;
			}
		}
		if ((this.grille[true_lig][true_col] != 'O' || this.grille[true_lig][true_col] != 'X'))
		{
			this.grille[true_lig][true_col] = 'O';
			return testPos(true_lig, true_col);
		}
		return false;
	}

	public boolean coche(int lig, int col)
	{
		int true_lig;
		int true_col;

		if (lig > this.taille)
		{
			true_lig = this.taille - 1;
		}
		else
		{
			if (lig < 1)
			{
				true_lig = 0;
			}
			else
			{
				true_lig = lig - 1;
			}
		}

		if (col > this.taille)
		{
			true_col = this.taille - 1;
		}
		else
		{
			if (col < 1)
			{
				true_col = 0;
			}
			else
			{
				true_col = col - 1;
			}
		}
		if (this.grille[true_lig][true_col] != 'O' || this.grille[true_lig][true_col] != 'X')
		{
			this.grille[true_lig][true_col] = 'X';
			return testPos(true_lig, true_col);
		}
		return false;
	}

	public boolean testPos(int i, int j)
	{
		if (!((this.tab[i][j] == '1' && this.grille[i][j] == 'O') ||
			(this.tab[i][j] == '0' && this.grille[i][j] == 'X')))
		{
			this.grille[i][j] = ' ';
			return false;
		}
		return true;
	}

	public boolean testWin()
	{
		for (int i=0; i < this.grille.length; i++)
		{
			for (int j=0; j < this.grille.length; j++)
			{
				if (this.tab[i][j] == '1' && this.grille[i][j] != 'O')
				{
					return false;
				}
			}
		}
		return true;
	}

	public int getTaille(){
		return this.taille;
	}

	public int[][] getTab_col(){ return tab_col; }
	public int[][] getTab_lig(){ return tab_lig; }

	public char[][] getGrille(){
		return grille;
	}
}
