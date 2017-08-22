//NoteServer.java
package notews;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class NoteServer {


	public boolean addNote(@WebParam(name="text") String text) {
		System.out.println(text);
		Connection con;
		boolean succes = false;
		try {
			con = getConnection();
			Calendar c = new GregorianCalendar();
			String query = "insert or ignore into Notes(tijdstip,tekst)\n" + "values(?, ?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, "" + c.getTime());
			pstmt.setString(2, text);
			succes = pstmt.executeUpdate() != 0;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return succes;
	}

	public boolean removeNote(@WebParam(name="id") int noteNr) {
		System.out.println("notenr: " + noteNr);
		Connection con;
		boolean succes = false;
		try {
			con = getConnection();
			PreparedStatement stmt1 = con.prepareStatement("DELETE FROM Notes WHERE rangnummer = ?;");
			stmt1.setInt(1, noteNr);

			succes = stmt1.executeUpdate() != 0;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return succes;
	}

	public Note[] getNotes() {
		Connection con;
		List<Note> notes = new ArrayList<Note>();
		try {
			con = getConnection();
			PreparedStatement stmt1 = con.prepareStatement("select rangnummer, tijdstip, tekst FROM Notes;");
			stmt1.setString(1, "");
			java.sql.ResultSet rs = stmt1.executeQuery();
			while (rs.next()) {
				Note note = new Note();
				note.rangnummer = rs.getInt(1);
				note.tijdstip = rs.getString(2);
				note.tekst = rs.getString(3);
				notes.add(note);
			}
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		Note[] notess = new Note[notes.size()];
		notes.toArray(notess);
		return notess;
	}

	private Connection getConnection() throws SQLException, ClassNotFoundException {
		// OPEN DATABASE
		File dir = new File("database");
		dir.mkdir();
		Class.forName("org.sqlite.JDBC");
		String url = "jdbc:sqlite:database/week5_2.sqlite";
		Connection con = DriverManager.getConnection(url);
		createTable(con);
		return con;
	}

	private void createTable(Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		stmt.executeUpdate("CREATE TABLE if not exists Notes (" + "rangnummer INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "tijdstip   TEXT," + "tekst      TEXT" + ");");
		//con.close();
}
	
}