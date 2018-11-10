import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.jdom2.Document;         
import org.jdom2.Element;          
import org.jdom2.JDOMException;    
import org.jdom2.input.SAXBuilder;
import org.jespxml.JespXML;
import org.jespxml.modelo.Encoding;
import org.jespxml.modelo.Tag; 

public class cargaXML{
	
	public static int cont=0;
	public static String accion;
	public static String opcion;
	public static String condicion;
	public static int contEje=0;
	
public static void main(String[] args) {
		cargarXml();
		
}
public static void cargarXml()
{
	
    //Se crea un SAXBuilder para poder parsear el archivo
    SAXBuilder builder = new SAXBuilder();
    File xmlFile = new File( "probando.xml" );
    try
    {
        //Se crea el documento a traves del archivo
        Document document = (Document) builder.build( xmlFile );
        //Se obtiene la raiz 'tables'
        Element rootNode = document.getRootElement();
        rootNode.getAttribute("primero");
        rootNode.getAttributeValue("primero");
        //Se obtiene la lista de hijos de la raiz 'tables'
        List list = rootNode.getChildren( "Operacion" );
        
        File programa=new File("Programa");
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
          fichero = new FileWriter(programa);
            pw = new PrintWriter(fichero);

      
            fichero.write("#include <stdio.h>\n");
            fichero.write("");
            fichero.write("int suma(int a,int b);\n");
            fichero.write("");
            fichero.write("int main (){\n");
            fichero.write("	int i,j,l,k;\n");
            fichero.write("	int a="+rootNode.getAttributeValue("primero")+";\n");
            fichero.write("	int b="+rootNode.getAttributeValue("segundo")+";\n");
            //Se recorre la lista de hijos de 'tables'
            for ( int i = 0; i < list.size(); i++ )
            {
                //Se obtiene el elemento 'tabla'
                Element tabla = (Element) list.get(i);
     
                //Se obtiene el atributo 'nombre' que esta en el tag 'tabla'
                String nombreAccion = tabla.getAttributeValue("accion");
     
                //Se obtiene la lista de hijos del tag 'tabla'
                List lista_campos = tabla.getChildren();
     
                //Se recorre la lista de campos
                for ( int j = 0; j < lista_campos.size(); j++ )
                {
                    //Se obtiene el elemento 'campo'
                    Element campo = (Element)lista_campos.get( j );
             
                    //Se obtienen los valores que estan entre los tags '<campo></campo>'
                    //Se obtiene el valor que esta entre los tags '<nombre></nombre>'
                    String accion = campo.getChildTextTrim("accion");
     
                    //Se obtiene el valor que esta entre los tags '<tipo></tipo>'
                    String opcion = campo.getChildTextTrim("opcion");
     
                    //Se obtiene el valor que esta entre los tags '<valor></valor>'
                    String condicion = campo.getChildTextTrim("condicion");
                   int accion1=Integer.parseInt(accion);
                    
                   switch(accion1){
                    
                   case 1:
                    	
                    	//Resta
                    	int cont=0;
                    	if(cont==0){
                    		
                    		System.out.println("RESTA");
                    		System.out.println("resta=suma("+rootNode.getAttributeValue("primero")+",(b*-1));\n");
                        	System.out.println("printf ("+"%d"+", resta);\n");
                        	fichero.write("resta=suma("+rootNode.getAttributeValue("primero")+",("+rootNode.getAttributeValue("segundo")+"*-1));\n");
                        	fichero.write("printf ("+"%d"+", resta);\n");
                        	fichero.write("");
                    	cont++;
                    	}
                    	break;
                    	
                    case 2:
                    	
                    	//Multiplicacion
                    	if(condicion==null){
                    		
                    		System.out.println("MULTIPLICACION");
                    		System.out.println("int multiplicacion=0;\n");
                        	System.out.println("for(int i=0;i<"+opcion+""+rootNode.getAttributeValue("segundo")+";i++){\n"
                        			+ "		mutilplicacion=suma(multiplicacion, "+rootNode.getAttributeValue("primero")+");\n}");
                        	System.out.println("printf ("+"%d"+", multiplicacion);\n");
                        	fichero.write("int multiplicacion=0;\n");
                        	
                        	fichero.write("for(int i=0;i<"+opcion+""+rootNode.getAttributeValue("segundo")+";i++){\n"
                        			+ "		mutilplicacion=suma(multiplicacion,"+rootNode.getAttributeValue("primero")+");\n}");
                        	fichero.write("printf ("+"%d"+", multiplicacion);\n");
                    		
                    	}
                    	else{
                    		
                    		System.out.println("MULTIPLICACION");
                    		System.out.println("int multiplicacion=0;\n");
                        	System.out.println("for(int i=0;i"+condicion+""+rootNode.getAttributeValue("segundo")+";i++){\n"
                        			+ "		mutilplicacion=suma(multiplicacion, "+rootNode.getAttributeValue("primero")+");\n}");
                        	System.out.println("printf ("+"%d"+", multiplicacion);\n");
                        	fichero.write("int multiplicacion=0;\n");
                        	
                        	fichero.write("for(int i=0;i"+condicion+""+rootNode.getAttributeValue("segundo")+" ;i++){\n"
                        			+ "		mutilplicacion=suma(multiplicacion,"+rootNode.getAttributeValue("primero")+");\n}");
                        	fichero.write("printf ("+"%d"+", multiplicacion);\n");
                    		
                    	}
                    	
                    	
                    	break;
                    	
                    case 3:
                    	//Potencia
                    	
                    	if (condicion==null){
                    		
                    		System.out.println("POTENCIA");
                    		System.out.println("int potencia = 1;\n");
                    		System.out.println("for(k=0; k<"+opcion+";k++){\n"
                    				+ " potencia = potencia * "+rootNode.getAttributeValue("primero")+";\n}\n");
                    		System.out.println("printf ("+"%d"+", potencia);\n");
                    		
                    		fichero.write("int potencia = 1;\n");
                    		fichero.write("for(k=0 ;k <"+opcion+";k++){\n"
                    				+ "	potencia = potencia * "+rootNode.getAttributeValue("primero")+";\n}\n");
                    		fichero.write("printf ("+"%d"+", potencia);\n");
                    				
                    	}
                    	
                    	else{
                    		System.out.println("POTENCIA");
                    		System.out.println("int potencia = 1;\n");
                    		System.out.println("for(k=0; k"+condicion+""+rootNode.getAttributeValue("segundo")+";k++){\n"
                    				+ " potencia = potencia * "+rootNode.getAttributeValue("primero")+";\n}\n");
                    		System.out.println("printf ("+"%d"+", potencia);\n");
                    		
                    		fichero.write("int potencia = 1;\n");
                    		fichero.write("for(k=0 ;k "+condicion+""+rootNode.getAttributeValue("segundo")+";k++){\n"
                    				+ "	potencia = potencia * "+rootNode.getAttributeValue("primero")+";\n}\n");
                    		fichero.write("printf ("+"%d"+", potencia);\n");
                    	}
                    	break;
                    	
                    case 4:
                    	
                    	//Division
                    	
                    	if(condicion==null){
                    		
                    		System.out.println("DIVISION");
                    		System.out.println("int division = 0;");
                    		System.out.println("resto = "+rootNode.getAttributeValue("primero")+"%"+rootNode.getAttributeValue("segundo")+";\n");
                    		System.out.println("divi = "+rootNode.getAttributeValue("primero")+";\n");
                        	System.out.println("if ("+rootNode.getAttributeValue("primero")+">"+opcion+"){\n"
                        							+"do{\n"
                        							+"division ++;\n"
                        							+"divi = suma(divi,("+rootNode.getAttributeValue("segundo")+"*-1));\n"
                        							+"}while (divi > resto);\n"
                    								+"}");
                        	System.out.println("else{\n"
                        						+"division = 0;\n"
                        						+"}\n");
                        	System.out.println("printf ("+"%d"+", division);\n");
                        	
                        	fichero.write("int division = 0;");
                        	fichero.write("resto = "+rootNode.getAttributeValue("primero")+"%"+rootNode.getAttributeValue("segundo")+";\n");
                        	fichero.write("divi = "+rootNode.getAttributeValue("primero")+";\n");
                        	fichero.write("if ("+rootNode.getAttributeValue("primero")+">"+opcion+"){\n"
                        							+"do{\n"
                        							+"division ++;\n"
                        							+"divi = suma(divi,("+rootNode.getAttributeValue("segundo")+"*-1));\n"
                        							+"}while (divi > resto);\n"
                    								+"}");
                        	fichero.write("else{\n"
                        						+"division = 0;\n"
                        						+"}\n");
                        	fichero.write("printf ("+"%d"+", division);\n");
                    		
                    	}
                    	else{
                    		
                    		System.out.println("DIVISION");
                    		System.out.println("int division = 0;");
                    		System.out.println("resto = "+rootNode.getAttributeValue("primero")+"%"+rootNode.getAttributeValue("segundo")+";\n");
                    		System.out.println("divi = "+rootNode.getAttributeValue("primero")+";\n");
                        	System.out.println("if ("+rootNode.getAttributeValue("primero")+""+condicion+""+rootNode.getAttributeValue("segundo")+"){\n"
                        							+"do{\n"
                        							+"division ++;\n"
                        							+"divi = suma(divi,("+rootNode.getAttributeValue("segundo")+"*-1));\n"
                        							+"}while (divi > resto);\n"
                    								+"}");
                        	System.out.println("else{\n"
                        						+"division = 0;\n"
                        						+"}\n");
                        	System.out.println("printf ("+"%d"+", division);\n");
                        	
                        	fichero.write("int division = 0;");
                        	fichero.write("resto = "+rootNode.getAttributeValue("primero")+"%"+rootNode.getAttributeValue("segundo")+";\n");
                        	fichero.write("divi = "+rootNode.getAttributeValue("primero")+";\n");
                        	fichero.write("if ("+rootNode.getAttributeValue("primero")+""+condicion+""+rootNode.getAttributeValue("segundo")+"){\n"
                        							+"do{\n"
                        							+"division ++;\n"
                        							+"divi = suma(divi,("+rootNode.getAttributeValue("segundo")+"*-1));\n"
                        							+"}while (divi > resto);\n"
                    								+"}");
                        	fichero.write("else{\n"
                        						+"division = 0;\n"
                        						+"}\n");
                        	fichero.write("printf ("+"%d"+", division);\n");
                    		
                    	}
                    	
                    	
                    	
                    	break;
                    
                    }
                }
                
            }
            
            fichero.write("return 0;\n");
            fichero.write("}\n");
            fichero.write("int suma (int a,int b){\n"
            				+"	int resultado =0;\n"
            				+"	resultado = a+b;\n"
            				+"	return resultado;\n"
        					+"}\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
     
    }catch ( IOException io ) {
        System.out.println( io.getMessage() );
    }catch ( JDOMException jdomex ) {
        System.out.println( jdomex.getMessage() );
    }
	
}
}