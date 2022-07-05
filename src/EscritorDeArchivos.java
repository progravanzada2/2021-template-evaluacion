package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import unlam.progava.oia.Grafo;

public class EscritorDeArchivos {

	public static void escribir(Grafo grafo, String path) {
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fw = new FileWriter(new File(path));
			pw = new PrintWriter(fw);
			
			if (grafo.isArbol()) {
				pw.println("Si " + grafo.getRegla1().get(0));
			} else {
				pw.println("No");
				/*Regla 1*/
				for (Integer nodo : grafo.getRegla1()) {
					pw.print(nodo);
					pw.print(' ');
				}
				
				pw.println();
				/*Regla 2*/
				for (Integer nodo : grafo.getRegla2()) {
					pw.print(nodo);
					pw.print(' ');
				}
				
				pw.println();
				/*Regla 3*/
				for (Integer nodo : grafo.getRegla3()) {
					pw.print(nodo);
					pw.print(' ');
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
