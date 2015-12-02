package modelo;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.input.DataFormat;
import net.sf.jasperreports.components.map.fill.MapFillFactory;
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
	
	public void loadReport(String ruta,boolean tipo){
		try {
			con.conectar();
			File f = new File(ruta);
			design = JRXmlLoader.load(f.getAbsolutePath());
			report = JasperCompileManager.compileReport(design);
			Map<String, Object> parametros = new HashMap<String, Object>();
			if(tipo){
				parametros.put("activo", "s");
			}
			else{
				parametros.put("activo", "n");
			}
			print = JasperFillManager.fillReport(report, parametros, con.getConnection());
			con.desconectar();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			log.printLog(e.getMessage(), this.getClass().toString());
		}
	}
	
	public void loadReportCV(String ruta, String fechaInicio, String fechaFin){
		try {
			con.conectar();
			File f = new File(ruta);
			design = JRXmlLoader.load(f.getAbsolutePath());
			report = JasperCompileManager.compileReport(design);
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("fechainicio", fechaInicio);
			//fecha = new Date("2015-11-01");
			parametros.put("fechafin", fechaFin);
			print = JasperFillManager.fillReport(report, parametros, con.getConnection());
			con.desconectar();
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
