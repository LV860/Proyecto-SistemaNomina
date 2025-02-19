package proyectoFinal.programacionAvanzada;
import java.io.*;

public class ManejoArchivos {
	
	public static void crearArchivo(String file) {
		File Archivo = new File(file);
		try {
			PrintWriter salida = new PrintWriter(Archivo);
			salida.close();
			System.out.println("Se creo correctamente el archivo");
		}catch(IOException e) {
			e.printStackTrace(System.out);
		}
	}
	

}