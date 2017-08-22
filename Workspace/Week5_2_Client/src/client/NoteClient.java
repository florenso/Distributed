package client;

import notews.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class NoteClient extends JFrame {

	NoteService service = new NoteService();
	Notes noteProxy = service.getNotePort();

	private static final long serialVersionUID = 1L;
	private JTextField noteField;
	private JTextField removeNoteField;
	private JTextArea jta;

	private JLabel resultField;

	public NoteClient() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 300);

		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(4, 2));
		p1.add(new JLabel("Note:"));
		noteField = new JTextField();
		p1.add(noteField);

		p1.add(new JLabel("ID:"));
		removeNoteField = new JTextField();
		p1.add(removeNoteField);

		p1.add(new JLabel("Result"));
		resultField = new JLabel("Unknown");
		p1.add(resultField);

		JButton jb = new JButton("Add Note");

		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (noteField != null) {
					System.out.println("NoteField: " + noteField.getText().trim());
					boolean result = noteProxy.addNote(noteField.getText().trim());
					if (resultField != null) {

						resultField.setText(Boolean.toString(result));
					}

					System.out.println("Pressed button!!");
				}
			}
		});
		p1.add(jb);

		JButton jb1 = new JButton("Remove Note");
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (noteField != null) {
					boolean result = noteProxy.removeNote(Integer.parseInt(removeNoteField.getText().trim()));

					if (resultField != null) {

						resultField.setText(Boolean.toString(result));
					}

					System.out.println("Pressed remove button!!");
				}
			}
		});
		p1.add(jb1);

		JPanel p2 = new JPanel();
		jta = new JTextArea(7, 40);
		p2.add(jta);

		JButton jb2 = new JButton("Refresh Notes");
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jta.setText("");
				Note[] result = noteProxy.getNotes();
				for (Note note : result) {
					jta.append("" + note.rangnummer + note.tijdstip + note.tekst + "\n");
				}
			}
		});
		p2.add(jb2);

		getContentPane().add(p1, BorderLayout.NORTH);
		getContentPane().add(p2, BorderLayout.SOUTH);
	}

	public static void main(String args[]) {

		NoteClient nc = new NoteClient();
		nc.setVisible(true);

		// boolean result = calculatorProxy.addNote("Hoi");
		// if(result){
		// System.out.println("Note added");
		// }

	}
}