package modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	private DateFormat dateFormat;
	private Date date;
	
	public Logger(){
		dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		date = new Date();
	}
	
	public void printLog(String mensaje, String clase){
		FileWriter pw = null;
		BufferedWriter bw = null;
		try {
			File archivo = new File("Logs\\log.txt");
			//File archivo = new File("C:\\Users\\GHOST\\Desktop\\log.txt");
			pw = new FileWriter(archivo, true);
			bw = new BufferedWriter(pw);
			bw.write(clase);
			bw.newLine();
			bw.write(mensaje + " " + dateFormat.format(date) + " ");
			bw.newLine();
			bw.write("*******************************************************************");
			bw.newLine();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}