package proyectoFinal.programacionAvanzada;

import java.io.Serializable;

public class Profesor extends EmpleadosEmpresa implements Serializable {
	private static final long serialVersionUID = 1L;
	private ESCALAFON escalafon;

	public Profesor(String nombre, int documento, ESCALAFON escalafon) {
		super(nombre, documento);
		this.escalafon = escalafon;
	}
	
	public ESCALAFON getEscalafon() {
		return escalafon;
	}

	@Override
	public void calcularSalario() {
		setSueldo((SALARIOMINIMO*0.75f)*escalafon.getPorcentaje());
	}
	
	@Override
	public double  calcularSalarioBase() {
		return (SALARIOMINIMO*escalafon.getPorcentaje());
	}

}
