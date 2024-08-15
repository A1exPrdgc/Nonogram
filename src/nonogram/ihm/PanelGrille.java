package nonogram.ihm;

import nonogram.Controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PanelGrille extends JPanel
{
	private Controleur ctrl;
	private Graphics2D g2;
	private GestionSouris souris;

	private int largeurTop;
	private int hauteurTop;
	private int largeurGauche;
	private int hauteurGauche;

	private int espaceG;
	private int espaceT;
	public PanelGrille(Controleur ctrl, int largeurTop, int hauteurTop, int largeurGauche, int hauteurGauche, int espaceG, int espaceT)
	{
		this.ctrl = ctrl;

		this.largeurTop = largeurTop;
		this.hauteurTop = hauteurTop;

		this.largeurGauche = largeurGauche;
		this.hauteurGauche = hauteurGauche;

		this.espaceG = espaceG;
		this.espaceT = espaceT;

		this.souris = new GestionSouris();

		this.addMouseListener(this.souris);
	}

	public void majDessin()
	{
		this.repaint();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		final double DECAL = 2;

		this.g2 = (Graphics2D) g;

		for (int i=0; i < this.ctrl.getGrille().length; i++)
		{
			for (int j=0; j < this.ctrl.getGrille().length; j++)
			{
				if (this.ctrl.getGrille()[j][i] == 'O')
				{
					this.g2.setColor(Color.black);
					this.g2.fillRect( (int)(largeurTop / 2 + largeurTop * (i * DECAL)), hauteurGauche * j, (int)(largeurTop * DECAL), hauteurGauche);
				}
				else
				{
					if (this.ctrl.getGrille()[j][i] == 'X')
					{
						this.g2.setColor(Color.black);
						this.g2.drawLine((int)(largeurTop / 2 + largeurTop * (i * DECAL)), hauteurGauche * j, (int)(largeurTop / 2 + largeurTop * (i * DECAL)) + (int)(largeurTop * DECAL), hauteurGauche * j + hauteurGauche);
					}
					else
					{
						this.g2.setColor(Color.white);
						this.g2.fillRect( (int)(largeurTop / 2 + largeurTop * (i * DECAL)), hauteurGauche * j, (int)(largeurTop * DECAL), hauteurGauche);
					}
				}
				this.g2.setColor(Color.black);
				this.g2.drawRect( (int)(largeurTop / 2 + largeurTop * (i * DECAL)), hauteurGauche * j, (int)(largeurTop * DECAL), hauteurGauche);
			}
		}
	}

	private class GestionSouris extends MouseAdapter
	{
		private int x;
		private int y;

		private int indiceLig;
		private int indiceCol;
		@Override
		public void mouseClicked(MouseEvent e)
		{
			this.x = e.getX();
			this.y = e.getY();

			boolean GorB;

			if (this.x > 8)
			{
				this.indiceCol = (this.x - 8) / (int)(largeurTop * 2);
			}
			this.indiceLig = this.y / hauteurGauche;

			if(PanelGrille.this.ctrl.getCouleur())
			{
				GorB = PanelGrille.this.ctrl.check(indiceLig, indiceCol);
			}
			else
			{
				GorB = PanelGrille.this.ctrl.coche(indiceLig, indiceCol);
			}

			if (!GorB)
			{
				PanelGrille.this.ctrl.moins1Vie();
				PanelGrille.this.ctrl.majPanelVie();
			}

			PanelGrille.this.majDessin();

			if(PanelGrille.this.ctrl.getVie() == 0)
			{
				PanelGrille.this.ctrl.fermer();
				JOptionPane.showMessageDialog(null, "Vous avez perdu");
				System.exit(0);
			}

			if(PanelGrille.this.ctrl.testWin())
			{
				PanelGrille.this.ctrl.fermer();
				JOptionPane.showMessageDialog(null, "Vous avez gagné");
				System.exit(0);
			}
		}
	}
}
