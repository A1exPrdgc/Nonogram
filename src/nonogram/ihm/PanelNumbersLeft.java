package nonogram.ihm;

import nonogram.Controleur;

import javax.swing.*;
import java.awt.*;

public class PanelNumbersLeft extends JPanel
{
	private Controleur ctrl;
	private int[][] tabLig;
	private JLabel[][] tabLabel;

	public PanelNumbersLeft(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.tabLig = this.ctrl.getTabLig();

		this.tabLabel = new JLabel[this.ctrl.getTaille()][this.ctrl.getMaxSize(this.tabLig)];
		this.setLayout(new GridLayout(this.ctrl.getTaille(), this.ctrl.getMaxSize(this.tabLig)));

		for (int i=0; i < this.tabLig.length ; i++)
		{
			for (int j=0; j < this.ctrl.getMaxSize(this.tabLig); j++)
			{
				if (this.tabLig[i][j] != 0)
				{
					this.tabLabel[i][j] = new JLabel(this.tabLig[i][j] + "");
					this.tabLabel[i][j].setFont(new Font("", Font.BOLD, 30));
				}
				else
				{
					this.tabLabel[i][j] = new JLabel();
				}
				this.add(this.tabLabel[i][j]);
			}
		}
	}

	public int getW()
	{
		return (int)this.tabLabel[0][0].getPreferredSize().getWidth();
	}

	public int getH()
	{
		return (int)this.tabLabel[0][0].getPreferredSize().getHeight();
	}

	public int getMax()
	{
		return this.ctrl.getMaxSize(this.ctrl.getTabLig());
	}


}
