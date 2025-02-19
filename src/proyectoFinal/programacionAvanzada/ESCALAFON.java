package proyectoFinal.programacionAvanzada;

public enum ESCALAFON {
	CATEDRA(0.5f),
	INSTRUCTOR(1.0f),
	ASISTENTE(1.5f),
	ASOCIADO(2.0f),
	TITULAR(2.5f);
	
	private float porcentaje;
	
	private ESCALAFON (float porcentaje) {
		this.porcentaje = porcentaje;
	}

	public float getPorcentaje() {
		return porcentaje;
	}
	
}
