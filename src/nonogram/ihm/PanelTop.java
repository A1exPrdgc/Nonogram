package nonogram.ihm;

import nonogram.Controleur;

import javax.swing.*;
import java.awt.*;

public class PanelTop extends JPanel
{
	private Controleur ctrl;
	private int[][] tabCol;
	private JLabel[][] tabLabel;

	public PanelTop(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.tabCol = this.ctrl.getTabCol();

		this.tabLabel = new JLabel[this.ctrl.getTaille() - 1 + this.ctrl.getMaxSize(this.ctrl.getTabLig())][this.ctrl.getMaxSize(this.tabCol)];

		this.setLayout(new GridLayout(this.ctrl.getMaxSize(this.tabCol), this.ctrl.getTaille()));

		for (int i=0; i < this.ctrl.getMaxSize(this.tabCol); i++)
		{

			for (int j=0; j < this.ctrl.getMaxSize(this.ctrl.getTabLig()) - 2; j++)
			{
				JLabel temp = new JLabel();
				temp.setPreferredSize(new Dimension(80, temp.getPreferredSize().height));
				this.add(temp);
			}

			for (int j=0; j < this.ctrl.getTaille(); j++)
			{
				if (this.tabCol[j][i] != 0)
				{
					this.tabLabel[j][i] = new JLabel(this.tabCol[j][i] + "");
					this.tabLabel[j][i].setFont(new Font("", Font.BOLD, 30));
				}
				else
				{
					this.tabLabel[j][i] = new JLabel();
				}
				this.add(this.tabLabel[j][i]);
			}
		}

	}

	public int getMax()
	{
		return this.ctrl.getMaxSize(this.tabCol);
	}

	public int getW()
	{
		return (int)this.tabLabel[4][0].getPreferredSize().getWidth();
	}

	public int getH()
	{
		return (int)this.tabLabel[0][0].getPreferredSize().getHeight();
	}
}
