package nonogram.ihm;

import nonogram.Controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PanelBas extends JPanel implements ItemListener
{
	private JPanel panelChoix;
	private ButtonGroup group;
	private JRadioButton croix;
	private JRadioButton couleur;
	private Controleur ctrl;
	public PanelBas(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setLayout(new GridLayout(1, 2));
		this.panelChoix = new JPanel(new GridLayout(1, 2));

		this.group = new ButtonGroup();

		this.couleur = new JRadioButton("Noir");
		this.couleur.setSelected(true);
		this.panelChoix.add(this.couleur);
		this.group.add(this.couleur);
		this.couleur.addItemListener(this);

		this.croix = new JRadioButton("X");
		this.panelChoix.add(this.croix);
		this.group.add(this.croix);
		this.croix.addItemListener(this);

		this.add(this.panelChoix);
	}


	@Override
	public void itemStateChanged(ItemEvent e)
	{
		if (this.croix.isSelected())
		{
			this.ctrl.croix2Coul(false);
		}

		if (this.couleur.isSelected())
		{
			this.ctrl.croix2Coul(true);
		}
	}
}
