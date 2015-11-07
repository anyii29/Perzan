package modelo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class Encrypt {
	
	private  KeyGenerator keyGenerator;
	//private Key key;
	private SecretKey key;
	private String key1;
	private Cipher aes;
	private byte[] encriptado;
	private DateFormat dateFormat;
	private Date date;
	private File file;
	private Logger log;
	
	
	public Encrypt(){
		log = new Logger();
		dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
			/*keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
	        key = keyGenerator.generateKey();
	        key = new SecretKeySpec("proyectoperzan12345".getBytes(),  0, 16, "AES");*/
        	KeyGenerator kg = KeyGenerator.getInstance("DES"); 
        	key = kg.generateKey(); 
        	key1 = String.valueOf(kg.generateKey()); 
			//aes = Cipher.getInstance("AES/ECB/PKCS5Padding");	
	        aes = Cipher.getInstance("DES/ECB/PKCS5Padding");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			log.printLog(e.getMessage(), this.getClass().toString());
		}
        
	}
	
	/*metodos para encriptar contrasenas*/
    private String toHexadecimal(byte[] digest){
        String hash = "";
        for(byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) hash += "0";
            hash += Integer.toHexString(b);
        }
        return hash;
    }
 
    public String encryptText(String message){
        byte[] digest = null;
        byte[] buffer = message.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(buffer);
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error creando Digest");
        }
        return toHexadecimal(digest);
	}
    
    /*metodos para encriptar texto*/
    
    // new method try it 
    
	public String encrypt(File ruta) throws Throwable {
		date = new Date();
		String nameBackup = "perzan"+dateFormat.format(date)+".per";	
		file = ruta;
		FileInputStream fis = new FileInputStream(ruta);
		FileOutputStream fos = new FileOutputStream("BaseDatos\\"+nameBackup);
		encryptOrDecrypt(key1, Cipher.ENCRYPT_MODE, fis, fos);
		deleteFile();
		return nameBackup;
	}

	public File decrypt(File f) throws Throwable {
		date = new Date();
		String nameBackup = "perzan"+dateFormat.format(date)+".per";	
		file = new File("BaseDatos\\"+nameBackup);
		FileInputStream fis = new FileInputStream(f);
		FileOutputStream fos = new FileOutputStream(file);
		encryptOrDecrypt(key1, Cipher.DECRYPT_MODE, fis, fos);
		return file;
	}

	public void encryptOrDecrypt(String key, int mode, InputStream is, OutputStream os) throws Throwable {

		DESKeySpec dks = new DESKeySpec(key.getBytes());
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey desKey = skf.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES"); // DES/ECB/PKCS5Padding for SunJCE

		if (mode == Cipher.ENCRYPT_MODE) {
			cipher.init(Cipher.ENCRYPT_MODE, desKey);
			CipherInputStream cis = new CipherInputStream(is, cipher);
			doCopy(cis, os);
		} else if (mode == Cipher.DECRYPT_MODE) {
			cipher.init(Cipher.DECRYPT_MODE, desKey);
			CipherOutputStream cos = new CipherOutputStream(os, cipher);
			doCopy(is, cos);
			
		}
	}

	public void doCopy(InputStream is, OutputStream os) throws IOException{
		byte[] bytes = new byte[64];
		int numBytes;
		while ((numBytes = is.read(bytes)) != -1) {
			os.write(bytes, 0, numBytes);
		}
		os.flush();
		os.close();
		is.close();
	}
	
	public void deleteFile(){
		if(file.delete()){
			System.out.println("fichero borrado!!");
		}
	}
	public String encryptConexion(File ruta) throws Throwable {
		file = ruta;
		File f2 = new File("BaseDatos\\conexion.txt");
		FileInputStream fis = new FileInputStream(ruta);
		FileOutputStream fos = new FileOutputStream(f2);
		encryptOrDecrypt(key1, Cipher.ENCRYPT_MODE, fis, fos);
		file.delete();
		//f2.renameTo(new File("BaseDatos\\conexion1.txt"));
		
		return "";
	}

	public File decryptConexion(File f) throws Throwable {
		file = new File("BaseDatos\\conexion1.txt");
		FileInputStream fis = new FileInputStream(f);
		FileOutputStream fos = new FileOutputStream(file);
		encryptOrDecrypt(key1, Cipher.DECRYPT_MODE, fis, fos);
		return file;
	}
}
