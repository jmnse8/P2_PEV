package presentacion.paneles;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import negocio.seleccion.SeleccionEnum;
import presentacion.recursos.Colores;

public class PanelSeleccion extends JPanel{

	private static final long serialVersionUID = 1L;
	private JComboBox<String> cB;
	
	public PanelSeleccion() {
		iniGUI();
	}
	
	private void iniGUI() {
		setLayout(null);
		setBounds(5,5,300, 60);		
		setBackground(Colores.CLARO2);

		Border b = BorderFactory.createLineBorder(Colores.OSCURO2, 2);
		setBorder(BorderFactory.createTitledBorder(b, "ELIGE EL TIPO DE SELECCIÓN ", TitledBorder.LEFT, TitledBorder.TOP,new Font("Arial", Font.BOLD, 15),Colores.OSCURO2));
		
		cB = new JComboBox<String>();
		cB.addItem("Estocastica Universal");
		cB.addItem("Restos");
		cB.addItem("Ruleta");
		cB.addItem("Truncamiento");
		cB.addItem("Torneo Deterministico");
		cB.addItem("Torneo Probabilistico");
		cB.addItem("Ranking");
		cB.setBounds(20,20, 260, 20);
		cB.setBackground(Colores.CLARO1);
		cB.setForeground(Colores.OSCURO2);
		add(cB);
	}
	
	public SeleccionEnum getSeleccion() {
		switch ((String) cB.getSelectedItem()) {
		case "Estocastica Universal":
			return SeleccionEnum.Seleccion_Estocastica_Universal;
		case "Restos":
			return SeleccionEnum.Seleccion_Restos;
		case "Ruleta":
			return SeleccionEnum.Seleccion_Ruleta;
		case "Torneo Deterministico":
			return SeleccionEnum.Seleccion_Torneo_Deterministico;
		case "Torneo Probabilistico":
			return SeleccionEnum.Seleccion_Torneo_Probabilistico;
		case "Truncamiento":
			return SeleccionEnum.Seleccion_Truncamiento;
		case "Ranking":
			return SeleccionEnum.Seleccion_Ranking;
		default:
			return SeleccionEnum.Seleccion_Restos;
		}
	}

}
