package presentacion.paneles;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import negocio.cruce.CruceEnum;
import presentacion.recursos.Colores;

public class PanelCruce extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> cB;
	private JSpinner pS;
	private JLabel infoS;

	public PanelCruce() {
		iniGUI();
	}

	private void iniGUI() {
		setLayout(null);
		setBounds(5, 65, 300, 80);
		setBackground(Colores.CLARO2);

		Border b = BorderFactory.createLineBorder(Colores.OSCURO2, 2);
		setBorder(BorderFactory.createTitledBorder(b, "ELIGE EL TIPO DE CRUCE ", TitledBorder.LEFT, TitledBorder.TOP,new Font("Arial", Font.BOLD, 15),Colores.OSCURO2));

		cB = new JComboBox<String>();
		cB.addItem("PMX");
		cB.addItem("CX");
		cB.addItem("CO");
		cB.addItem("OX");
		cB.addItem("OXPP");
		cB.addItem("OXOP");
		cB.setBounds(20, 20, 260, 20);
		cB.setBackground(Colores.CLARO1);
		cB.setForeground(Colores.OSCURO2);
		
		SpinnerNumberModel modeloSpinner = new SpinnerNumberModel(60, 0, 100, 1);
		pS = new JSpinner(modeloSpinner);
        pS.setBounds(200, 45, 70, 20);
        pS.setToolTipText("Seleccionar porcentaje de probabilidad de cruce");
        
        infoS = new JLabel("Probabilidad de cruce:");
		infoS.setBounds(20, 45, 180, 20);
		infoS.setForeground(Colores.OSCURO2);
		
		add(cB);
		add(pS);
		add(infoS);
	}

	public CruceEnum getCruce() {
		switch ((String) cB.getSelectedItem()) {
		case "PMX":
			return CruceEnum.Cruce_PMX;
		case "CO":
			return CruceEnum.Cruce_CO;
		case "CX":
			return CruceEnum.Cruce_CX;
		case "OX":
			return CruceEnum.Cruce_OX;
		case "OXPP":
			return CruceEnum.Cruce_OXPP;
		case "OXOP":
			return CruceEnum.Cruce_OXOP;
		default:
			return CruceEnum.Cruce_PMX;
		}
	}
	
	public int getPorcentajeCruce() {
		return (int) pS.getValue();
	}

}
