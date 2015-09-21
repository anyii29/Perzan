package modelo;

import java.io.File;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Reportes {
	private JasperDesign design;
	private JasperPrint print;
	private JasperReport report;
	private Logger log;
	Conexion con;
	
	public Reportes(){
		design = new JasperDesign();
		print = new JasperPrint();
		log = new Logger();
		con = Conexion.getInstance();
	}
	
	public void loadReport(String ruta){
		try {
			con.conectar();
			File f = new File(ruta);
			design = JRXmlLoader.load(f.getAbsolutePath());
			report = JasperCompileManager.compileReport(design);
			print = JasperFillManager.fillReport(report, new HashMap(), con.getConnection());
		} catch (JRException e) {
			// TODO Auto-generated catch block
			log.printLog(e.getMessage(), this.getClass().toString());
		}
	}
	
	public void showReport(){
		try {
			JasperViewer.viewReport(print, false);
		} catch (Exception e) {
			log.printLog(e.getMessage(), this.getClass().toString());
			// TODO: handle exception
		}
	}
	
}
