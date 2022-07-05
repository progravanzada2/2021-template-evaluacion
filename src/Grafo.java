package unlam.progava.oia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Grafo {
	
	private int[][] matrizAdy;
	private Boolean esArbol = true;
	private List<Integer> regla1 = new ArrayList<>();
	private List<Integer> regla2 = new ArrayList<>();
	private List<Integer> regla3 = new ArrayList<>();
	
	public Grafo(int n) {
		matrizAdy = new int[n][n];
	}

	public int getArista(int desde, int hasta) {
		return matrizAdy[desde][hasta];
	}
	
	public void setArista(int desde, int hasta) {
		matrizAdy[desde][hasta] = 1;
	}

	public void BFS() {
		verificarRegla1y2();
		
		if (regla1.size() != 1 || regla1.get(0) == 0) {
			regla3.add(0); //O(n)
			esArbol = false;	
		} else {
			//inicializado en false por defecto
			boolean[] visitado = new boolean[matrizAdy.length];
			Queue<Integer> q = new LinkedList<>();
			
			//obtengo la raiz
			int raizIndice = regla1.get(0) - 1;
			
			q.add(raizIndice); //Agrego a la cola
			visitado[raizIndice] = true; //lo visito
			
			//mientras haya algo que desencolar
			while(!q.isEmpty()) {
				//desencolo
				int visitante = q.poll();
				//visito todos los nodos conectados al visitante
				for(int i = 0; i < matrizAdy.length; i++) {
					//si es un vecino y no esta visitado
					if(matrizAdy[visitante][i] == 1 && !visitado[i]) {
						q.add(i); //Agrego a la cola
						visitado[i] = true; //lo visito
					}
				}
			}
			//reviso si hay nodos no visitados
			for(int i = 0; i < visitado.length; i++) {
				//si encuentro un no visitado, no es un arbol y lo agrego a la regla3.
				if(!visitado[i]) {
					esArbol = false;
					regla3.add(i+1);
				}
			}
			//si estan todos visitados, agrego un 0.
			if(regla3.isEmpty()) {
				regla3.add(0);
			}
		}
	}
	
	public void verificarRegla1y2() { //O(N^2)
		
		//Verifico la regla1
		for (int j = 0; j < matrizAdy.length; j++) { //O(n^2)
			boolean posibleRaiz = true;
			for (int i = 0; i < matrizAdy.length; i++) { //O(n)
				if (matrizAdy[i][j] != 0) {
					posibleRaiz = false;
				}
			}
			if (posibleRaiz) {
				regla1.add(j+1); //O(n)
			}
		}

		if (regla1.size() != 1) { //O(1)
			esArbol = false;
		}
		
		if (regla1.isEmpty()) { //O(1)
			regla1.add(0); //O(n)
		}
		
		//verifico la regla 2
		for(int j = 0; j < matrizAdy.length; j++) { //O(n^2)
			int nodosApuntando = 0;
			for (int i = 0; i < matrizAdy.length; i++) { //O(n)
				if (matrizAdy[i][j] != 0) {
					nodosApuntando++;
				}
			}
			if (nodosApuntando > 1 || (nodosApuntando == 0 && regla1.size() > 1)) {
				regla2.add(j+1); //O(n)
			}
		}
		if (regla2.isEmpty()) { //O(1)
			regla2.add(0); //O(n)
		} else {
			esArbol = false;
		}
	}

	public List<Integer> getRegla1() {
		return regla1;
	}

	public List<Integer> getRegla2() {
		return regla2;
	}

	public List<Integer> getRegla3() {
		return regla3;
	}

	public Boolean isArbol() {
		return esArbol;
	}

}
