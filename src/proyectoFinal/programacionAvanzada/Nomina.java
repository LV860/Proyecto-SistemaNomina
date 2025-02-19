package proyectoFinal.programacionAvanzada;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Nomina implements Serializable	{
	private static final long serialVersionUID = 1L;
	
	private List <EmpleadosEmpresa> nomina = new ArrayList<>();
	
	public Nomina() {
	}

	public Nomina(List<EmpleadosEmpresa> nomina) {
		this.nomina = nomina;
	}
	
	public List<EmpleadosEmpresa> getNomina() {
		return nomina;
	}
	
	public boolean empleadoExiste(int documento){
        for(EmpleadosEmpresa e: nomina){
            if(e.getDocumento() == documento){
                return true;
            }
        }
        return false;
    }
    
public void AgregarEmpleado(String cargo,String nombre,int documento, Nomina n){
        
        Scanner scan = new Scanner(System.in);
            switch(cargo){
            case "Empleado": System.out.println("Digite el numero de salarios: ");
                             int numeroSalarios=scan.nextInt();
                             scan.nextLine();
                             //Empleado empleado=new Empleado(nombre,documento,numeroSalarios);
                             
                             n.getNomina().add(new Empleado(nombre,documento,numeroSalarios));
                             break;
            case "Profesor": System.out.println("Digite el escalafon");
                             System.out.print("\nIngrese el tipo de Escalafon: (CATEDRA,INSTRUCTOR,ASISTENTE,ASOCIADO,TITULAR) ");
                             String tipoEscalafon = scan.nextLine();
                             
                             ESCALAFON escalafon = Enum.valueOf(ESCALAFON.class,tipoEscalafon.toUpperCase());
                             //Profesor profesor= new Profesor(nombre,documento,escalafon);
                             n.getNomina().add(new Profesor(nombre,documento,escalafon));
                             break;
            case "Monitor": 
            				System.out.println("Digite las horas totales");
            				int horasTotales=scan.nextInt();
            				scan.nextLine();
            				//Monitor monitor=new Monitor(nombre,documento,horasTotales);
            				n.getNomina().add(new Monitor(nombre,documento,horasTotales));
            				break;	
            }               
        
	}
    
    public boolean eliminarEmpleado(String nombre){
        for(EmpleadosEmpresa a:nomina){
            if(a.getNombre().equals(nombre)){
                nomina.remove(a);
                return true;
            }

        }
        return false;
    }
    
    public boolean eliminarEmpleado(int documento){
        for(EmpleadosEmpresa a:nomina){
            if(a.getDocumento()==documento){
                nomina.remove(a);
                return true;
            }

        }
        return false;
    }


}
