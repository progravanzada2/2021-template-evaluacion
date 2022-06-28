package grafos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrizAdyacente implements Grafo {

	protected Double[][] grafo;

	public MatrizAdyacente(int size) {
		grafo = new Double[size][size];
	}

	public MatrizAdyacente(double[][] grafo) {
		this.grafo = new Double[grafo.length][grafo.length];

		for (int i = 0; i < grafo.length; i++) {
			for (int j = 0; j < grafo.length; j++) {
				// Si la relacion es 0 (inexistente) entonces la convertimos a null.
				this.grafo[i][j] = (grafo[i][j] != 0) ? grafo[i][j] : null;
			}
		}
	}

	@Override
	public int size() {
		return grafo.length;
	}

	@Override
	public void setArista(int desde, int hasta, double costo) {
		grafo[desde][hasta] = costo;
	}

	@Override
	public double costo() {
		double costo = 0;

		for (int i = 0; i < grafo.length; i++) {
			for (int j = 0; j < grafo.length; j++) {
				costo += grafo[i][j];
			}
		}

		return costo;
	}

	@Override
	public Double getArista(int desde, int hasta) {
		return grafo[desde][hasta];
	}

	@Override
	public List<Integer> getAristasDe(int desde) {
		List<Integer> aristas = new ArrayList<>();

		for (int i = 0; i < grafo[desde].length; i++) {
			if (grafo[desde][i] != null) {
				aristas.add(i);
			}
		}

		return aristas;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();

		str.append(Arrays.toString(grafo[0]));
		for (int i = 1; i < grafo.length; i++) {
			str.append("\n" + Arrays.toString(grafo[i]));
		}

		return str.toString();
	}

}
