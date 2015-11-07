package controlador;


public class Validar {
	
	public boolean nombres(String dato){
		return dato.matches("(([A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)\\s*)*");	
	}
	public boolean contrasena(String dato){
		return dato.matches("(([A-Z]{1})([a-z]+)([0-9]+))+");	
	}
	public boolean cadena(String dato){
		return dato.matches("([A-Za-zÁÉÍÓÚáéíóúñÑ0-9.,/]+\\s*)+");	
	}
	public boolean usuario(String dato){
		return dato.matches("([A-Za-z]+)([0-9]+)");	
	}
	public boolean direccion(String dato){
		return dato.matches("[[A-Za-zÁÉÍÓÚáéíóú0-9/.,#-ñÑ]\\s]*");	
	}
	public boolean entero(String dato){
		return dato.matches("\\d{1,5}");	
	}
	public boolean telefono(String dato){
		return dato.matches("\\d{3}-\\d{3}-\\d{2}-\\d{2}");	
	}
	public boolean precio(String dato){
		return dato.matches("(\\d{1,5})||(\\d{1,5}+[.]+\\d{1,2})");	
	}
	public boolean precio1(String dato){
		return dato.matches("(\\d{1,5}+[.]+\\d{1,2})+");	
	}
}
