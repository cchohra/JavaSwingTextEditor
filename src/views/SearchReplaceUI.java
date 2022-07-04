package views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class SearchReplaceUI extends JFrame {

	JTextPane editor;

	JLabel searchLabel = new JLabel("Rechercher : ");
	JLabel replaceLabel = new JLabel("Remplacer par : ");

	JTextField searchInput = new JTextField();
	JTextField replaceInput = new JTextField();

	JButton find = new JButton("Suivant");
	JButton replace = new JButton("Remplacer");
	JButton replaceAll = new JButton("Remplacer Tout");

	int searchStart = 0;

	private void initComponents() {
		this.setLayout(null);
		this.searchLabel.setBounds(10, 10, 100, 30);
		this.replaceLabel.setBounds(10, 45, 100, 30);
		this.searchInput.setBounds(110, 10, 200, 30);
		this.replaceInput.setBounds(110, 45, 200, 30);
		this.find.setBounds(10, 80, 140, 30);
		this.replace.setBounds(170, 80, 140, 30);
		this.replaceAll.setBounds(170, 115, 140, 30);
		this.add(this.searchLabel);
		this.add(this.replaceLabel);
		this.add(this.searchInput);
		this.add(this.replaceInput);
		this.add(this.find);
		this.add(this.replace);
		this.add(this.replaceAll);
	}

	private void initEvents() {

		this.find.addActionListener(e -> {
			String text = this.editor.getText().replace("\r", "").substring(searchStart);
			String search = this.searchInput.getText();
			int index = text.indexOf(search);
			if (index != -1) {
				this.editor.setSelectionStart(index + this.searchStart);
				this.editor.setSelectionEnd(index + this.searchStart + search.length());
				this.searchStart = this.searchStart + index + 1;
			}
			else {
				if (this.searchStart == 0) JOptionPane.showMessageDialog(this, "Impopssible de trouver le texte");
				else {
					this.searchStart = 0;
					this.find.doClick();
				}
			}
		});

		this.replace.addActionListener(e -> {
			String text = this.editor.getText().replace("\r", "");
			String search = this.searchInput.getText();
			String replace = this.replaceInput.getText();
			int index = text.indexOf(search);
			if (index != -1) {
				this.editor.setText(text.substring(0, index) + replace + text.substring(index + search.length()));
				this.searchStart = this.searchStart + index + 1;
			}
			else JOptionPane.showMessageDialog(this, "Impopssible de trouver le texte");
		});

		this.replaceAll.addActionListener(e -> {
			String text = this.editor.getText();
			String search = this.searchInput.getText();
			String replace = this.replaceInput.getText();
			this.editor.setText(text.replace(search, replace));
		});

	}

	public SearchReplaceUI(JTextPane editor) {
		this.editor = editor;
		this.setTitle("Rechercher et Remplacer");
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setSize(350, 210);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.initComponents();
		this.initEvents();
		this.setVisible(true);
	}

}
