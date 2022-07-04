package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import views.EditorTab;
import views.MainUI;

public class FileController implements ActionListener {

	MainUI ui;
	private JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));

	public FileController(MainUI ui) {
		this.ui = ui;
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers Texte (.txt)", "txt");
		this.fileChooser.addChoosableFileFilter(filter);
	}

	public void actionPerformed(ActionEvent e) {
		JComponent source = (JComponent) e.getSource();
		
		// L'action du menu Nouveau
		if (source.equals(this.ui.newItem)) {
			EditorTab newEditor = new EditorTab(new JTextPane(), "Sans Titre");
			this.ui.content.add("Sans Titre", newEditor);
			this.ui.content.setSelectedComponent(newEditor);
		}

		// L'action du menu Fermer l'onglet
		if (source.equals(this.ui.closeItem)) {
			EditorTab selected = (EditorTab) ui.content.getSelectedComponent();
			if (selected.saved) {
				ui.content.remove(ui.content.getSelectedIndex());
			}
			else {
				int choice = JOptionPane.showConfirmDialog(
						null,
						"Des modifications ont été effectuées sur le fihcier "
						+ selected.title + " voulez vous enregistrer le fichier",
						"Attention",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE);
				if (choice == JOptionPane.YES_OPTION) {
					if (selected.file != null) {
						selected.save();
						ui.content.remove(ui.content.getSelectedIndex());
					}
					else if (this.saveAs())
						ui.content.remove(ui.content.getSelectedIndex());
				} else if (choice == JOptionPane.NO_OPTION)
					ui.content.remove(ui.content.getSelectedIndex());
			}
		}

		// L'action du menu Ouvrir
		if (source.equals(this.ui.openItem)) {
			int selected = this.fileChooser.showOpenDialog(null);
			if (selected == JFileChooser.APPROVE_OPTION) {
				File file = this.fileChooser.getSelectedFile();
				EditorTab newEditor = new EditorTab(new JTextPane(), file, file.getName());
				if (file != null) {
					FileReader reader;
					try {
						reader = new FileReader(file);
						String editorText = "";
						while (true) {
							int n = reader.read();
							if (n == -1) break;
							else {
								char c = (char) n;
								editorText = editorText + c;
							}
						}
						newEditor.content.setText(editorText);
						this.ui.content.add(file.getName(), newEditor);
						this.ui.content.setSelectedComponent(newEditor);
						reader.close();
					} catch (FileNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Ce fichier n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "On ne peut pas ouvrir ce fichier", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}

		// L'action du menu Enregistrer
		if (source.equals(this.ui.saveItem)) {
			EditorTab selected = (EditorTab) ui.content.getSelectedComponent();
			if (selected.file != null) {
				selected.save();
			}
			else {
				this.saveAs();
			}
		}

		// L'action du menu Enregistrer Sous ...
		if (source.equals(this.ui.saveAsItem)) {
			this.saveAs();
		}
	
		// L'action du menu Quitter
		if (source.equals(this.ui.exitItem)) {
			int confirm = JOptionPane.showConfirmDialog(null, "Êtes vous sur", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (confirm == JOptionPane.YES_OPTION) System.exit(0);
		}
	}

	boolean saveAs() {
		EditorTab selected = (EditorTab) ui.content.getSelectedComponent();
		int selectedOption = this.fileChooser.showSaveDialog(null);
		if (selectedOption == JFileChooser.APPROVE_OPTION) {
			if (this.fileChooser.getSelectedFile().exists()) {
				int confirm = JOptionPane.showConfirmDialog(null, "Le fichier " + this.fileChooser.getSelectedFile().getName() + " Existe déjà, Voulez vous l'écraser", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (confirm == JOptionPane.YES_OPTION) {
					selected.file = this.fileChooser.getSelectedFile();
					if (selected.file != null) {
						selected.save();
						selected.title = selected.file.getName();
						ui.content.setTitleAt(ui.content.getSelectedIndex(), selected.file.getName());
						return true;
					}
					else {
						JOptionPane.showMessageDialog(null, "Le fichier choisi n'est pas accessible", "Erreur", JOptionPane.ERROR_MESSAGE);
						return false;
					}
				}
				else return false;
			}
			else {
				selected.file = this.fileChooser.getSelectedFile();
				if (selected.file != null) {
					selected.save();
					selected.title = selected.file.getName();
					ui.content.setTitleAt(ui.content.getSelectedIndex(), selected.file.getName());
					return true;
				}
				else {
					JOptionPane.showMessageDialog(null, "Le fichier choisi n'est pas accessible", "Erreur", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		}
		else return false;
	}

}
