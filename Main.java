package inco;

import java.util.Scanner;

public class Main {
	
	public static int a,resultado;
	
	
	public static void repetir(int accion, int noCondicion) {
		
		if(accion==1){
			int resta = 0;
			for(int i=0; i<1; i++){
				 resta=suma(a,(-1)*noCondicion);
			}
			
			System.out.println("resta"+resta);
			
		}
		
		if(accion==2){
			
			int multi=0;
			for(int j=0;j<noCondicion;j++){

				multi=multi+a;
				
			}
			System.out.println("multiplicacion;"+multi);
		}
		
		if(accion==3){
			
				int repeticion2=0;
				for(int k=0; k<noCondicion; k++){
					int repeticion = 0;
					
					for(int l=0; l<a; l++){
						repeticion=repeticion+a;
						repeticion2=repeticion2+repeticion;
					}
					
				}
			
			System.out.println(repeticion2);

		}
		
		
	}
	
	
	public static int suma(int a, int b){
			
			resultado=a+b;
			return resultado;
	}
	
	
	
	

	public static void main(String[] args) {
		
		Scanner escaner=new Scanner(System.in);
		
		System.out.println("Dame un numero");
		a=escaner.nextInt();
		System.out.println("Dame otro numero");
		int b=escaner.nextInt();
			
		int accion=0;
		
		while (accion!=4){
			
			System.out.println("Elige una opcion:\n"
					+ "1º resta.\n"
					+ "2º multiplicacion.\n"
					+ "3º potencia.\n"
					+ "4º Salir.\n");
			accion=escaner.nextInt();
			

			if(accion==1){
				repetir(1,b);
			}

			if(accion==2){
				repetir(2,b);
			}
			
			if(accion==3){
				repetir(3, b);
			}
		}
		escaner.close();
	}

}
