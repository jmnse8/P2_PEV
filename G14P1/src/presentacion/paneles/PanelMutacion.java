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

import negocio.mutacion.MutacionEnum;
import presentacion.recursos.Colores;

public class PanelMutacion extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> cB;
	private JSpinner pS;
	private JLabel infoS;

	public PanelMutacion() {
		iniGUI();
	}

	private void iniGUI() {
		setLayout(null);
		setBounds(5, 145, 300, 80);
		setBackground(Colores.CLARO2);

		Border b = BorderFactory.createLineBorder(Colores.OSCURO2, 2);
		setBorder(BorderFactory.createTitledBorder(b, "ELIGE EL TIPO DE MUTACIÓN ", TitledBorder.LEFT, TitledBorder.TOP,new Font("Arial", Font.BOLD, 15),Colores.OSCURO2));

		cB = new JComboBox<String>();
		cB.addItem("Inserción");
		cB.addItem("Intercambio");
		cB.addItem("Inversión");
		cB.addItem("Heurística");
		cB.setBounds(20, 20, 260, 20);
		cB.setBackground(Colores.CLARO1);
		cB.setForeground(Colores.OSCURO2);
		
		SpinnerNumberModel modeloSpinner = new SpinnerNumberModel(5, 0, 100, 1);
		pS = new JSpinner(modeloSpinner);
        pS.setBounds(200, 45, 70, 20);
        pS.setToolTipText("Seleccionar porcentaje de probabilidad de mutación");
        
        infoS = new JLabel("Probabilidad de mutacion:");
        infoS.setForeground(Colores.OSCURO2);
		infoS.setBounds(20, 45, 180, 20);
		infoS.setForeground(Colores.OSCURO2);
		
		add(cB);
		add(pS);
		add(infoS);
	}

	public MutacionEnum getMutacion() {
		switch ((String) cB.getSelectedItem()) {
		case "Inserción":
			return MutacionEnum.Mutacion_Insercion;
		case "Intercambio":
			return MutacionEnum.Mutacion_Intercambio;
		case "Inversión":
			return MutacionEnum.Mutacion_Inversion;
		case "Heurística":
			return MutacionEnum.Mutacion_Heuristica;
		default:
			return MutacionEnum.Mutacion_Insercion;
		}
	}
	
	public int getPorcentajeMutacion() {
		return (int) pS.getValue();
	}
}
