package client;

import notews.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextField;

class NoteClient extends JFrame {
	
    NoteService service = new NoteService();
    Notes noteProxy = service.getNotePort();
  
	  private static final long serialVersionUID = 1L;
	  private JTextField noteField;
	  private JTextField removeNoteField;
	  
	  
	  private JLabel resultField;
	  
	  public NoteClient(){
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setSize(350, 200);
      
      JPanel p1 = new JPanel();
      p1.setLayout(new GridLayout(4, 2));
      p1.add(new JLabel("What is your note?"));
      noteField = new JTextField();
      noteField.setText("Type your note here...");
      p1.add(noteField);
      
      p1.add(new JLabel("Remove Note ID"));
      removeNoteField = new JTextField();
      removeNoteField.setText("Note ID");
      p1.add(removeNoteField);
      
      p1.add(new JLabel("Result"));
      resultField = new JLabel("Unknown");
      p1.add(resultField);
      

      JButton jb = new JButton("Add Note");
      
      
      jb.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          
          if(noteField != null){
          System.out.println("NoteField: " + noteField.getText().trim());
          boolean result = noteProxy.addNote(noteField.getText().trim());
          if(resultField != null){
            
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
          
          if(noteField != null){
          boolean result = noteProxy.removeNote(Integer.parseInt(removeNoteField.getText().trim())); 
          
          if(resultField != null){
            
            resultField.setText(Boolean.toString(result));  
          }
          
          System.out.println("Pressed remove button!!");  
          }
        }
      });
      p1.add(jb1);
      
      getContentPane().add(p1, BorderLayout.NORTH);
      
      
	  }
	
  public static void main(String args[])
  {

	  

          
        NoteClient nc = new NoteClient();
        nc.setVisible(true);
        
        //boolean result = calculatorProxy.addNote("Hoi");
        //if(result){
        //  System.out.println("Note added");
        //}
        
        

        
  }
}