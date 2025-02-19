package proyectoFinal.programacionAvanzada;

import java.io.Serializable;

public class Monitor extends EmpleadosEmpresa implements Serializable {
	private static final long serialVersionUID = 1L;
	private int horasTotales;
	
	public Monitor(String nombre, int documento, int horasTotales) {
		super(nombre, documento);
		this.setHorasTotales(horasTotales);
	}
	
	public int getHorasTotales() {
		return horasTotales;
	}

	public void setHorasTotales(int horasTotales) {
		this.horasTotales = horasTotales;
	}

	@Override
	public void calcularSalario() {
		setSueldo(5000*this.horasTotales);
	}
	
	@Override
	public double calcularSalarioBase() {
		return 0.0;
	}
	

}