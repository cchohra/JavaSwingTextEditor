package views;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class EditorTab extends JScrollPane {

	public JTextPane content;
	public File file = null;
	public String title;
	public boolean saved = false;

	private Font font = new Font("Monospaced", Font.BOLD, 14);

	private void initEvents() {
		content.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				saved = false;
			}
			public void keyReleased(KeyEvent e) {
				saved = false;
			}
			public void keyPressed(KeyEvent e) {
				saved = false;
			}
		});
	}

	public EditorTab(Component component, String title) {
		super(component);
		this.content = (JTextPane) component;
		this.title = title;
		this.content.setFont(font);
		this.initEvents();
	}

	public EditorTab(Component component, File file, String title) {
		super(component);
		this.content = (JTextPane) component;
		this.file = file;
		this.title = title;
		this.content.setFont(font);
		this.saved = true;
		this.initEvents();
	}

	public void save() {
		if (this.file != null) {
			try {
				this.saved = true;
				String text = this.content.getText();
				FileWriter writer = new FileWriter(this.file);
				writer.write(text);
				writer.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "On ne peut pas écrire dans ce fichier", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Le Fichier est null", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

}
