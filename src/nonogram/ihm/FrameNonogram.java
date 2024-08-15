package nonogram.ihm;

import nonogram.Controleur;

import javax.swing.*;
import java.awt.*;

public class FrameNonogram extends JFrame
{
	private Controleur ctrl;
	private PanelNumbersLeft panelLeft;
	private PanelTop panelTop;
	private PanelGrille panelGrille;
	private PanelBas panelBas;

	private Graphics2D g2;
	public FrameNonogram(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setTitle("Nonogram v1");
		this.setLayout(new BorderLayout());

		this.panelTop = new PanelTop(this.ctrl);
		this.panelLeft = new PanelNumbersLeft(this.ctrl);
		this.panelBas = new PanelBas(this.ctrl);

		this.setSize(this.ctrl.getTaille() * (this.panelTop.getW() + 20) + this.panelLeft.getMax() * this.panelLeft.getW(), (int)(this.panelTop.getH() * this.panelTop.getMax() + this.ctrl.getTaille() * (this.panelLeft.getH() + 9 / (this.ctrl.getTaille() / 5)) + this.panelBas.getPreferredSize().height));

		this.add(this.panelBas, BorderLayout.SOUTH);
		this.add(panelTop, BorderLayout.NORTH);
		this.add(panelLeft, BorderLayout.WEST);

		this.panelGrille = new PanelGrille(this.ctrl, this.panelTop.getW(), this.panelTop.getH(),
												      this.panelLeft.getW(), this.panelLeft.getH(),
						                              this.panelLeft.getMax(), this.panelTop.getMax());
		this.add(panelGrille, BorderLayout.CENTER);

		this.setVisible(true);
	}

	public void fermer()
	{
		this.dispose();
	}

	public void majPanelVie() {this.panelBas.majNbVie();}
}
