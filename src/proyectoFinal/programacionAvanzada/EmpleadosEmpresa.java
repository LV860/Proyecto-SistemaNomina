package proyectoFinal.programacionAvanzada;

import java.io.Serializable;

public abstract class EmpleadosEmpresa implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private int documento;
	private float sueldo;
	protected static int SALARIOMINIMO = 1000000;

	protected EmpleadosEmpresa (String nombre, int documento) {
		this.nombre = nombre;
		this.documento = documento;
	}

	public String getNombre() {
		return nombre;
	}

	public int getDocumento() {
		return documento;
	}
	
	public float getSueldo()  {
		return sueldo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDocumento(int documento) {
		this.documento = documento;
	}
	
	public void setSueldo (float sueldo)  {
		this.sueldo = sueldo;
	}

	public static int getSALARIOMINIMO() {
		return SALARIOMINIMO;
	}
	
	public abstract void calcularSalario();
	
	public abstract double calcularSalarioBase();



}
