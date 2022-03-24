package main;

import java.awt.Font;

import javax.swing.plaf.FontUIResource;

import presentacion.mainFrame.MainFrame;

public class Main {
	public static void main(String[] args) {
		javax.swing.UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Consolas", Font.PLAIN, 11)));
		MainFrame.getInstance();
	}
}
