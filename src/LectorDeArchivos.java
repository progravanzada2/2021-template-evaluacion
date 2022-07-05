package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import unlam.progava.oia.Grafo;

public class LectorDeArchivos {

	public static Grafo leer(String path) {
		Scanner scanner = null;
		Grafo grafo = null;
		try {
			scanner = new Scanner(new File(path));

			int cantNodos =scanner.nextInt();
			int cantAristas = scanner.nextInt();
			
			grafo = new Grafo(cantNodos);
			
			for(int i = 0; i < cantAristas; i++) {
				int desdeIndice = scanner.nextInt() - 1;
				int hastaIndice = scanner.nextInt() - 1;
				grafo.setArista(desdeIndice, hastaIndice);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally { 
			if (scanner != null) {
				scanner.close();
			}
		}
		
		return grafo;
	}
}
