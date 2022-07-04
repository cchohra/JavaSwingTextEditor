package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import views.EditorTab;
import views.MainUI;
import views.SearchReplaceUI;

public class EditController implements ActionListener {

	MainUI ui;

	public EditController(MainUI ui) {
		this.ui = ui;
	}

	public void actionPerformed(ActionEvent e) {
		JMenuItem source = (JMenuItem) e.getSource();

		if (source.equals(ui.copyItem)) {
			EditorTab selected = (EditorTab) ui.content.getSelectedComponent();
			selected.content.copy();
		}

		if (source.equals(ui.pasteItem)) {
			EditorTab selected = (EditorTab) ui.content.getSelectedComponent();
			selected.content.paste();
		}

		if (source.equals(ui.cutItem)) {
			EditorTab selected = (EditorTab) ui.content.getSelectedComponent();
			selected.content.cut();
		}

		if (source.equals(ui.selectAllItem)) {
			EditorTab selected = (EditorTab) ui.content.getSelectedComponent();
			selected.content.selectAll();
		}

		if (source.equals(ui.searchReplaceItem)) {
			EditorTab selected = (EditorTab) ui.content.getSelectedComponent();
			new SearchReplaceUI(selected.content);
		}


	}

}
