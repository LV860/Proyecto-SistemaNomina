package proyectoFinal.programacionAvanzada;

import java.io.*;
import java.util.*;

/*
De: 
	Leonardo Velázquez Colin
	Juan Carlos Canon Cardenas
	Juan David Torres Chaparro

*/
public class Main {
	
	public static File archivo = null;
	public static FileReader fr = null;
	public static FileWriter file = null;
	public static BufferedWriter writer = null;
	public static BufferedReader br = null;

	public static void main(String[] args) {
		File archivoNominaObjetos = new File ("NominaObjetos.txt");
		boolean salir = false;
		boolean creadoReporte = false;
		boolean creadoRetefuente = false;
		Nomina n = new Nomina();
		try(Scanner scan = new Scanner(System.in)) {
			
			System.out.println("Para todos los empleados el salario base es de $1'000.000 \nValor hora es de $5.000");
			System.out.println(" ");
			
			do {
				ImprimirMenu ();
				switch(scan.nextInt()) {
				
				case 1: scan.nextLine();
				leerArchivoNomina(n);
				System.out.println("Se leyo correctamente");
					break;
				case 2: scan.nextLine();
				try {
                    System.out.println("Digite los datos del empleado \n");
                    System.out.println("Digite el nombre del empleado: ");
                    String nombre=scan.nextLine();
                    System.out.println("Digite el documento del empleado: ");
                    int documento=scan.nextInt();
                    scan.nextLine();
                    if(n.empleadoExiste(documento)==true) {
                    throw new IllegalArgumentException("El empleado ya existe");
                        }
                    System.out.println("Digite el cargo del empleado(Empleado,Profesor,Monitor):");
                    String cargo=scan.nextLine();
                    n.AgregarEmpleado(cargo,nombre,documento,n);
                    System.out.println("Se creo correctamente el empleado de la empresa");
                }catch(IllegalArgumentException e) {
                    System.err.println("El empleado ya existe");
                }
					break;
				case 3: scan.nextLine();
				try {
                    System.out.println("Eliminar empleado\n");
                    System.out.println("Quieres hacerlo por nombre(1) o documento(2) \n");
                    int opcion=scan.nextInt();
                    scan.nextLine();
                    switch(opcion){
                        case 1: System.out.println("Digite el nombre del empleado: ");
                                String nombre_eliminar=scan.nextLine();
                                if(n.eliminarEmpleado(nombre_eliminar)==false) {
                                    throw new IllegalArgumentException("El empleado no existe");
                                }
                                else
                                    System.out.println("El empleado ha sido eliminado");
                                
                    break;
                        case 2: System.out.println("Digite el documento del empleado: ");
                                int documento_eliminar=scan.nextInt();
                                if(n.eliminarEmpleado( documento_eliminar)==false) {
                                    throw new IllegalArgumentException("El empleado no existe");
                                }
                                else
                                    System.out.println("El empleado ha sido eliminado");
                                break;
                        default: System.out.println("Opcion no existente");
                    }

                 }catch(IllegalArgumentException e) {
                     System.err.println("IllegalArgumentException");
                 }
					break;
				case 4: scan.nextLine();
				for (EmpleadosEmpresa s: n.getNomina()) {
					if (s instanceof Empleado) {
						((Empleado)s).calcularSalario();
						System.out.println("Empleado "+s.getNombre()+" su salario es: $"+s.getSueldo());
					}
				}
					break;
				case 5: scan.nextLine();
				for (EmpleadosEmpresa s: n.getNomina()) {
					if (s instanceof Profesor) {
						((Profesor)s).calcularSalario();
						System.out.println("Profesor "+s.getNombre()+" su salario es: $"+s.getSueldo());
					}
				}
					break;
				case 6: scan.nextLine();
				for (EmpleadosEmpresa s: n.getNomina()) {
					if (s instanceof Monitor) {
						((Monitor)s).calcularSalario();
						System.out.println("Monitor "+s.getNombre()+" su salario es: $"+s.getSueldo());
					}
				}
					break;
				case 7: scan.nextLine();
				if(creadoRetefuente == false) {
					ManejoArchivos.crearArchivo("Retefuente.txt");
					creadoRetefuente = true;
				}
				System.out.println("Ingrese el valor por parametro: ");
				int valor = scan.nextInt();
				
				escribirArchivoRetención(n,valor);
					break;
					
				case 8: scan.nextLine();
				if(creadoReporte  == false ) {
					ManejoArchivos.crearArchivo("Reporte.txt");
					creadoReporte = true;
				}
				escribirArchivoReporte(n);
					break;
				case 9: scan.nextLine();
					try {
						crearEmpleadosArchivos (archivoNominaObjetos ,n);
						System.out.println("Se creo correctamente el archivo");
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 10: salir = true;
					break;
				default:
					break;
				}
				
				
			}while(!salir);
		}
	}
	
	
	static void ImprimirMenu () {
		System.out.println("1)Cargar los empleados de una empresa a partir del siguiente archivo (Nomina.txt)"
				+ "\n2)Agregar un empleado"
				+ "\n3)Eliminar un empleado"
				+ "\n4)Calcular el salario de los empleados"
				+ "\n5)Calcular salarios de los profesores"
				+ "\n6)Calcular el salario de los monitores"
				+ "\n7)Listar los empleados que deben pagar Retencion en la Fuente"
				+ "\n8)Generar la nomina en un archivo (Reporte.txt)"
				+ "\n9)Hacer persistir la nomina"
				+ "\n10)Salir");
	}
	
	public static void leerArchivoNomina( Nomina n) {

        try {
        	String nombre ="", id="", cargo="", escalafon="", numeroSalarios="", horasTotales=""; 
        	ESCALAFON es;
        	archivo = new File ("Nomina.txt");
        	fr = new FileReader (archivo);
        	br = new BufferedReader(fr);
        	String linea = br.readLine();
           
           while ( !linea.equals("FIN")){
          	 String datosEmpleado = linea;
               StringTokenizer st = new StringTokenizer(datosEmpleado,"%");
               
               if(st.hasMoreTokens()){
              	 	nombre = st.nextToken().trim();
              	 	id = st.nextToken().trim();
              	 	cargo = st.nextToken().trim();
                   }
               
               StringTokenizer token2;
               linea = br.readLine();
               while(!linea.equals("#") ) {
              	 token2 = new StringTokenizer(linea, "\n");
              	 if(token2.hasMoreTokens()) {
              		 if(cargo.equalsIgnoreCase("Profesor")) {
              			 escalafon = token2.nextToken().trim();
              			 es = Enum.valueOf(ESCALAFON.class, escalafon.toUpperCase());
              			 n.getNomina().add(new Profesor(nombre,Integer.parseInt(id),es));
              			 
              		 }
              		if(cargo.equalsIgnoreCase("Empleado")) {
              			numeroSalarios = token2.nextToken().trim();
             			 n.getNomina().add(new Empleado(nombre,Integer.parseInt(id),Integer.parseInt(numeroSalarios)));
             			 
             		 }
              		if(cargo.equalsIgnoreCase("Monitor")) {
              			horasTotales = token2.nextToken().trim();
             			 n.getNomina().add(new Monitor(nombre,Integer.parseInt(id),Integer.parseInt(horasTotales)));
             			 
             		 }
              		 
   				}//fin if token2
              	 linea = br.readLine();
               }//fin while
               
               linea = br.readLine();
           }
           
        }
        catch(Exception e){
           e.printStackTrace();
        }finally{
           try{
              if( null != fr ){
                 fr.close();
              }
           }catch (Exception e2){ 
              e2.printStackTrace();
           }
        }
     }
	
	public static void escribirArchivoReporte(Nomina n) {
		try {
			archivo = new File ("Reporte.txt");
			file = new FileWriter("Reporte.txt",true);
			writer = new BufferedWriter(file);
			for(EmpleadosEmpresa e: n.getNomina()) {
				e.calcularSalario();
				writer.write(e.getNombre()+","+e.getDocumento()+","+ e.getSueldo());
				writer.newLine();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if( null != writer ){
					writer.close();
				}
			}catch (Exception e2){ 
				e2.printStackTrace();
			}
		}
			
	}
	
	public static void crearEmpleadosArchivos (File dataFile, Nomina n) throws IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)))){
			for (EmpleadosEmpresa s: n.getNomina()) {
				out.writeObject(s);
			}
		}
	}
	
	public static void escribirArchivoRetención(Nomina n, int valor) {
		try {
			archivo = new File ("Retefuente.txt");
			file = new FileWriter("Retefuente.txt",true);
			writer = new BufferedWriter(file);
			for(EmpleadosEmpresa e: n.getNomina()) {
					
					if (e instanceof Monitor) {
						if (valor < e.getSueldo()) {
								e.calcularSalario();
								writer.write("Nombre: " +e.getNombre());
								writer.newLine();
								writer.write("Tipo: Monitor" );
								writer.newLine();
								writer.write("Sueldo base:" + e.getSueldo());
								writer.newLine();
								writer.write("Valor de Rete Fuente: "+ (e.getSueldo()*0.18));
								writer.newLine();
								System.out.println("Nombre: " +e.getNombre() + "\n" + "Tipo: Monitor" + "\n" + "Sueldo base: " + e.getSueldo() + "\n" + "Valor de Rete Fuente: "+ (e.getSueldo()*0.18));
						}
					}
					if (e instanceof Empleado) {
						if (valor < e.calcularSalarioBase()) {
								writer.write("Nombre: " +e.getNombre());
								writer.newLine();
								writer.write("Tipo: Empleado" );
								writer.newLine();
								writer.write("Sueldo base: " + e.calcularSalarioBase());
								writer.newLine();
								writer.write("Valor de Rete Fuente: "+ (e.calcularSalarioBase()*0.18));
								writer.newLine();
								System.out.println("Nombre: " +e.getNombre() + "\n" + "Tipo: Empleado" + "\n" + "Sueldo base: " + e.calcularSalarioBase() + "\n" + "Valor de Rete Fuente: "+ (e.calcularSalarioBase()*0.18));
						}
					}
					
					  if (e instanceof Profesor) { 
						  if (valor < e.calcularSalarioBase()) {
							  	writer.write("Nombre: " +e.getNombre()); writer.newLine();
							  	writer.write("Tipo: Profesor" ); writer.newLine();
							  	writer.write("Sueldo base: " + e.calcularSalarioBase()); writer.newLine();
							  	writer.write("Valor de Rete Fuente: "+ (e.calcularSalarioBase()*0.18));
							  	writer.newLine(); System.out.println("Nombre: " +e.getNombre() + "\n" +
							  			"Tipo: Profesor" + "\n" + "Sueldo base: " + e.calcularSalarioBase() + "\n" +
							  			"Valor de Rete Fuente: "+ (e.calcularSalarioBase()*0.18)); } }
				}
				
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if( null != writer ){
					writer.close();
				}
			}catch (Exception e2){ 
				e2.printStackTrace();
			}
		}
			
	}

}
