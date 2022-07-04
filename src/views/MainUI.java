package views;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

import controllers.EditController;
import controllers.FileController;

@SuppressWarnings("serial")
public class MainUI extends JFrame {

	JMenuBar menuBar = new JMenuBar();
	JMenu fileMenu = new JMenu("Fichier");
	JMenu editMenu = new JMenu("Edition");
	public JMenuItem newItem = new JMenuItem("Nouveau");
	public JMenuItem openItem = new JMenuItem("Ouvrir");
	public JMenuItem saveItem = new JMenuItem("Enregistrer");
	public JMenuItem saveAsItem = new JMenuItem("Enregistrer Sous ...");
	public JMenuItem closeItem = new JMenuItem("Fermer l'onglet");
	public JMenuItem exitItem = new JMenuItem("Quitter");
	public JMenuItem copyItem = new JMenuItem("Copier");
	public JMenuItem cutItem = new JMenuItem("Couper");
	public JMenuItem pasteItem = new JMenuItem("Coller");
	public JMenuItem selectAllItem = new JMenuItem("Selectionner Tout");
	public JMenuItem searchReplaceItem = new JMenuItem("Rechercher");

	public JTabbedPane content = new JTabbedPane(JTabbedPane.BOTTOM);

	private void initComponents() {

		fileMenu.add(newItem);
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(saveAsItem);
		fileMenu.add(closeItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);

		editMenu.add(copyItem);
		editMenu.add(cutItem);
		editMenu.add(pasteItem);
		editMenu.add(selectAllItem);
		editMenu.addSeparator();
		editMenu.add(searchReplaceItem);

		menuBar.add(fileMenu);
		menuBar.add(editMenu);

		EditorTab defaultEditor = new EditorTab(new JTextPane(), "Sans Titre");
		content.add("Sans titre", defaultEditor);
		this.add(content);
		this.setJMenuBar(menuBar);

	}

	private void initEvents() {

		FileController fileController = new FileController(this);
		newItem.addActionListener(fileController);
		openItem.addActionListener(fileController);
		saveItem.addActionListener(fileController);
		saveAsItem.addActionListener(fileController);
		closeItem.addActionListener(fileController);
		exitItem.addActionListener(fileController);

		EditController editController = new EditController(this);
		copyItem.addActionListener(editController);
		pasteItem.addActionListener(editController);
		selectAllItem.addActionListener(editController);
		cutItem.addActionListener(editController);
		searchReplaceItem.addActionListener(editController);

	}

	public MainUI() {
		this.setTitle("Editeur de Texte");
		this.setBounds(50, 50, 1000, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initComponents();
		this.initEvents();
		this.setVisible(true);
	}

}
