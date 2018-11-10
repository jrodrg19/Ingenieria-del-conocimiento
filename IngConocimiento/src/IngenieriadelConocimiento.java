import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.jespxml.JespXML;
import org.jespxml.modelo.Atributo;
import org.jespxml.modelo.Encoding;
import org.jespxml.modelo.Tag;

public class IngenieriadelConocimiento {
	public static Tag programa = new Tag("Programa");
	public static Tag suma;
	public static Atributo a;
	public static Atributo b;

	public static void repeticion(int accion, String opcion, String condicion) {

		if (accion == 1) {
			Tag raiz1 = new Tag("Operacion");
			Tag datos1 = new Tag("Datos");
			Tag accion1 = new Tag("accion");
			
			raiz1.addAtributo(new Atributo("accion", "resta"));
			
			accion1.addContenido("1");
			
			datos1.addTagHijo(accion1);
			
			raiz1.addTagHijo(datos1);
			programa.addTagHijo(raiz1);
		}
		if (accion == 2) {
			Tag raiz2 = new Tag("Operacion");
			Tag datos2 = new Tag("Datos");
			Tag accion2 = new Tag("accion");
			Tag opcion2 = new Tag("opcion");
			Tag condicion2 = new Tag("condicion");
			raiz2.addAtributo(new Atributo("accion", "Multiplicacion"));
			accion2.addContenido("2");
			int opcion1=Integer.parseInt(opcion);
			if (opcion1 == 0) {
				condicion2.addContenido(condicion);
				datos2.addTagHijo(condicion2);
			} else {
				opcion2.addContenido(opcion);
				datos2.addTagHijo(opcion2);
				
			}
			datos2.addTagHijo(accion2);
			raiz2.addTagHijo(datos2);
			programa.addTagHijo(raiz2);

			
		}
		if (accion == 3) {
			Tag raiz3 = new Tag("Operacion");
			Tag datos3 = new Tag("Datos");
			Tag accion3 = new Tag("accion");
			Tag opcion3 = new Tag("opcion");
			Tag condicion3 = new Tag("condicion");
			raiz3.addAtributo(new Atributo("accion", "Potencia"));
			accion3.addContenido("3");
			int opcion1=Integer.parseInt(opcion);
			if (opcion1 == 0) {
				condicion3.addContenido(condicion);
				datos3.addTagHijo(condicion3);
			} else {
				opcion3.addContenido(opcion);
				datos3.addTagHijo(opcion3);
			}
			datos3.addTagHijo(accion3);
			
			raiz3.addTagHijo(datos3);
			programa.addTagHijo(raiz3);
		}
		
		if (accion == 4) {
			Tag raiz4 = new Tag("Operacion");
			Tag datos4 = new Tag("Datos");
			Tag accion4 = new Tag("accion");
			Tag opcion4 = new Tag("opcion");
			Tag condicion4 = new Tag("condicion");
			raiz4.addAtributo(new Atributo("accion", "Division"));
			accion4.addContenido("4");
			int opcion1=Integer.parseInt(opcion);
			if (opcion1 == 0) {
				condicion4.addContenido(condicion);
				datos4.addTagHijo(condicion4);
			} else {
				opcion4.addContenido(opcion);
				datos4.addTagHijo(opcion4);
			}
			datos4.addTagHijo(accion4);
			
			raiz4.addTagHijo(datos4);
			programa.addTagHijo(raiz4);
		}

	}

	public static int suma(int a, int b) {

		int resultado = a + b;
		return resultado;

	}

	public static void main(String[] args) {
		String opcion = null;
		String condicion = null;
			
		programa.addAtributo(new Atributo("primero", "a"));
		programa.addAtributo(new Atributo("segundo", "b"));

		Scanner sc = new Scanner(System.in);
		System.out.println("Dame una accion:\n" + "1º Resta\n" + "2º Multiplicacion\n" + "3º Potencia\n" + "4º Division\n" + "5º Salir\n");
		int accion = sc.nextInt();

		if (accion != 5) {
			if(accion!=1){
				System.out.println("Dame un opcion:");
				opcion = sc.next();
				int opcion1=Integer.parseInt(opcion);
				if(opcion1==0){
					System.out.println("Dame una condicion:");
					condicion = sc.next();
				}
			}
			repeticion(accion, opcion, condicion);
		}

		while (accion != 5) {

			System.out.println("Dame una accion:\n" + "1º Resta\n" + "2º Multiplicacion\n" + "3º Potencia\n" + "4º Division\n" + "5º Salir\n");

			accion = sc.nextInt();
			if (accion != 5) {
				if(accion!=1){
					System.out.println("Dame un opcion:");
					opcion = sc.next();
					int opcion1=Integer.parseInt(opcion);
					if(opcion1==0){
						System.out.println("Dame una condicion:");
						condicion = sc.next();
					}
				}
				repeticion(accion, opcion, condicion);
				
			}

		}

		File fichero=new File("Probando.xml");
		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter(fichero));
			bw.write("");
			bw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sc.close();

		JespXML xml = new JespXML(new File("/home/javier//workspace/IngConocimiento/probando.xml"), Encoding.UTF_8);
		xml.canWrite();
		cargaXML cargar=new cargaXML();
		cargar.main(null);
		try {
			xml.escribirXML(programa);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
