package modelo;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseDatos {
	
	private String host;
	private int puerto;
	private String usuario;
	private String password;
	private String bd;
	private String nameBackup;
	private String encrypText;
	private DateFormat dateFormat;
	private Date date;
	private File f;
	private FileReader file = null;
	private BufferedReader brFile = null;
	private Object[] datos;
	private Logger log;
	private Encrypt encrypt;
	
	public BaseDatos(){
		log = new Logger();
		dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		encrypt = new Encrypt();
		try {
			file = new FileReader("BaseDatos\\conexion.txt");
			brFile = new BufferedReader(file);
			host = brFile.readLine();
			puerto = Integer.parseInt(brFile.readLine());
			usuario = brFile.readLine();
			password = brFile.readLine();
			bd = brFile.readLine();
			brFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public boolean respaldo(){
		date = new Date();
		nameBackup = "perzan"+dateFormat.format(date)+".per";
		f = new File("basedatos\\"+ nameBackup);
		System.out.println(nameBackup);
//        String exec = "C:\\Program Files\\PostgreSQL\\9.4\\bin\\pg_dump.exe "
//        		+ "-h "+host+" -p "+puerto+" -U "+usuario+" -w -d "+bd+ " -f custom -b"
//        		+ " -f C:\\Users\\GHOST\\eclipse_proyects\\ProyectoPerzan\\BaseDatos\\"+nameBackup;
		String exec = "C:\\Program Files\\PostgreSQL\\9.4\\bin\\pg_dump.exe "
        		+ "-h "+host+" -p "+puerto+" -U "+usuario+" -w -d "+bd+ " -f custom -b"
        		+ " -f " + f.getAbsolutePath();
        
        //C:\Users\GHOST\eclipse proyects\ProyectoPerzan\BaseDatos -v -b -f c 
        //pg_dump.exe --host localhost --port 5432 --username "postgres" --no-password  --format custom --blobs --verbose --file "D:\perzanBackup.backup" "perzan"
        Process p;
		try {
			p = Runtime.getRuntime().exec(exec);
			
	        int time = p.waitFor();
	        if(time == 0){
	          System.out.println("backup is created");
	          try {
	        	nameBackup = encrypt.encrypt(f);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	          return true;
	        }
	        else{
	          System.out.println("fail to create backup");
	        }
	        p.destroy();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.printLog(e.getMessage(), this.getClass().toString());
		}
        
		return false;
	}
	public boolean restaurar(File file){
		File f = null;
		try {
			
			f = encrypt.decrypt(file);
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//encrypText = encrypt.decryptBackup(readBackup("dato"), nameBackup);
        //writeBackup("dato", encrypText);
		if(f != null){
			/*String comando = "C:\\Program Files\\PostgreSQL\\9.4\\bin\\psql.exe"
					+ " -h "+host+" -p "+ puerto+" -U " + usuario +" -d "+ bd
							+ " -w -f C:\\Users\\GHOST\\eclipse_proyects\\ProyectoPerzan\\BaseDatos\\"+dir;*/
			String comando = "C:\\Program Files\\PostgreSQL\\9.4\\bin\\psql.exe"
					+ " -h "+host+" -p "+ puerto+" -U " + usuario +" -d "+ bd
							+ " -w -f " + f.getAbsolutePath();
			String drop = "C:\\Program Files\\PostgreSQL\\9.4\\bin\\dropdb.exe"
					+ " -h "+host+" -p "+ puerto+" -U " + usuario
					+ " -w perzan";
			String create = "C:\\Program Files\\PostgreSQL\\9.4\\bin\\createdb.exe"
					+ " -h "+host+" -p "+ puerto+" -U " + usuario
					+ " -w perzan";
			//pg_restore.exe --host localhost --port 5432 --username "postgres" --dbname "perzan" --no-password  --verbose "D:\perzanBackup.backup"
			Process p;
			try {			
				p = Runtime.getRuntime().exec(drop);
				System.out.println(p.waitFor());
				p = Runtime.getRuntime().exec(create);
				System.out.println(p.waitFor());
				p = Runtime.getRuntime().exec(comando);
				System.out.println("restore");
				
				int time = p.waitFor();
				System.out.println(time);
				if(time == 0){
					System.out.println("DataBase Restored");
					return true;
				}
				else{
					System.out.println("Failed Restore");
				}

				p.destroy();
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.printLog(e.getMessage(), this.getClass().toString());
			}
			finally{
				encrypt.deleteFile();
			}
		}
		
		return false;
	}
}
