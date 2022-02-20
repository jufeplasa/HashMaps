package ui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
	private static Object[] dominio;
	private static Object[] codominio;

	public static void main(String args[]) throws IOException {
		System.out.println ("Escriba elementos para el domino, separados por espacio:");
		Set<String> a = new HashSet<String>();
		crearConjuntos(a);
		dominio=a.toArray();
		System.out.println ("Escriba elementos para el Codominio, separados por espacio:");
		Set<String> b = new HashSet<String>();
		crearConjuntos(b);
		codominio=  b.toArray();
		System.out.println(a);
		System.out.println(b);

		HashMap<String,String> funcion =new HashMap<String,String>();
		crearPares(a,b,funcion);
		
		if(esInyectiva(funcion)) {
			System.out.println("Es inyectiva");
		}
		else {
			System.out.println("No es inyectiva");
		}
		if(esSobreyectiva(funcion)) {
			System.out.println("Es Sobreyectiva");
		}
		else {
			System.out.println("No es Sobreyectiva");
		}
		if(esBiyectiva(funcion)) {
			System.out.println("Es Biyectiva");
		}
		else {
			System.out.println("No es Biyectiva");
		}
		

	}

	public static void crearConjuntos(Set<String> set) throws IOException {
		BufferedReader br= new BufferedReader( new InputStreamReader(System.in));

		String [] setElement;

		String values=br.readLine();
		setElement=values.split("\\ ");
		for(int i=0;i<setElement.length;i++) {
			set.add(setElement[i]);
		}	
	}


	public static void crearPares(Set<String> dominio,Set<String> codominio,HashMap<String,String>funcion) throws IOException {
		BufferedReader br= new BufferedReader( new InputStreamReader(System.in));

		boolean salir=false;
		String respuesta;
		String elemento;
		String [] par;
		do {
			boolean conti=true;
			while(conti) {
				System.out.println("Escribe un par ordenado, con el siguiente formato : dominio,codominio ");
				elemento=br.readLine();
				par=elemento.split("\\,");
				if(dominio.contains(par[0])&&codominio.contains(par[1])) {
					funcion.put(par[0], par[1]);
					conti=false;
				}
				else {
					System.out.println("Alguno de los elementos no corresponde a uno de los conjuntos, ingrese una entrada valida");
				}
			}
			System.out.println("Quiere agregar otro par ordenado a la funcion?");
			respuesta=br.readLine();
			if(respuesta.equalsIgnoreCase("no")) {
				salir=true;
			}
		}while(!salir);
	}
	
	public static boolean esInyectiva(HashMap<String,String>funcion) {
		boolean inyectividad=true;
		boolean conti=true;
		for (int i = 0; i < codominio.length && conti; i++) {
			int repetido=0;
			for (int j = 0; j < dominio.length&&conti; j++) {
				if(funcion.containsKey(dominio[j])) {
					if(funcion.get(dominio[j]).equals(codominio[i])) {
						repetido++;
						if(repetido>1) {
							conti=false;
							inyectividad=false;
						}
					}
				}
				
			}
		}
		
		
		return inyectividad;
	}
	
	public static boolean esSobreyectiva(HashMap<String,String>funcion) {
		boolean sobreyectividad=true;
		for (int i = 0; i < codominio.length; i++) {
			if(funcion.containsValue(codominio[i])==false) {
				sobreyectividad=false;
			}
		}
		return sobreyectividad;
	}

	public static boolean esBiyectiva(HashMap<String,String>funcion) {
		if(esInyectiva(funcion)&&esSobreyectiva(funcion)){
			return true;
		}
		else {
			return false;
		}
	}

}
