package proyectoFinal.programacionAvanzada;

import java.io.Serializable;

public class Empleado extends EmpleadosEmpresa implements Serializable {
	private static final long serialVersionUID = 1L;
	private int numeroSalarios;
	
	public Empleado(String nombre, int documento, int numeroSalarios) {
		super(nombre, documento);
		this.setNumeroSalarios(numeroSalarios);
	}

	public int getNumeroSalarios() {
		return numeroSalarios;
	}

	public void setNumeroSalarios(int numeroSalarios) {
		this.numeroSalarios = numeroSalarios;
	}
	
	@Override
	public void calcularSalario() {
		setSueldo((SALARIOMINIMO*this.numeroSalarios)*0.75f);
	}
	
	@Override
	public double calcularSalarioBase() {
		return ((SALARIOMINIMO*this.numeroSalarios));
	}
}
