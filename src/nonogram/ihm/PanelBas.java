package nonogram.ihm;

import nonogram.Controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PanelBas extends JPanel implements ItemListener
{
	private JPanel panelChoix;
	private JPanel panelVie;

	private ButtonGroup group;
	private JLabel vie;
	private JRadioButton croix;
	private JRadioButton couleur;
	private Controleur ctrl;
	public PanelBas(Controleur ctrl)
	{
		this.ctrl = ctrl;

		JLabel coeur;

		this.setLayout(new GridLayout(1, 2));
		this.panelChoix = new JPanel(new GridLayout(1, 2));
		this.panelVie = new JPanel(new FlowLayout(FlowLayout.LEFT));

		this.group = new ButtonGroup();

		this.couleur = new JRadioButton("Noir");
		this.couleur.setFont(new Font("", Font.BOLD, 15));
		this.couleur.setSelected(true);
		this.panelChoix.add(this.couleur);
		this.group.add(this.couleur);
		this.couleur.addItemListener(this);

		this.croix = new JRadioButton("Croix");
		this.croix.setFont(new Font("", Font.BOLD, 15));
		this.panelChoix.add(this.croix);
		this.group.add(this.croix);
		this.croix.addItemListener(this);

		coeur = new JLabel(new ImageIcon("./nonogram/ressources/coeur.png"));
		this.panelVie.add(coeur);

		this.vie = new JLabel();
		this.vie.setFont(new Font("", Font.PLAIN, 30));
		this.majNbVie();
		this.panelVie.add(this.vie);

		this.add(this.panelVie  );
		this.add(this.panelChoix);
	}

	public void majNbVie()
	{
		this.vie.setText("X " + this.ctrl.getVie());
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
