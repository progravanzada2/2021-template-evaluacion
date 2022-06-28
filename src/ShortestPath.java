package grafos.algoritmos;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import grafos.Arista;
import grafos.Grafo;
import monticulos.ColaDePrioridad;
import monticulos.MinMonticulo;

public class ShortestPath {

	private int[] antecesores;

	public double[] dijkstra(final Grafo grafo, final int root) {
		return this.dijkstra(grafo, root, null);
	}

	// O((n+a).log(n))
	public double[] dijkstra(final Grafo grafo, final int root, final List<Integer> blacklist) {
		ColaDePrioridad<Arista> queue = new MinMonticulo<>();
		double[] costos = new double[grafo.size()];
		boolean[] visitados = new boolean[grafo.size()];
		this.antecesores = new int[grafo.size()];

		// O(n)
		for (int i = 0; i < grafo.size(); i++) {
			double costo = (i == root) ? 0 : Double.MAX_VALUE;
			costos[i] = costo;
			antecesores[i] = root;
			visitados[i] = false;
		}

		// O(1) -> Por ser el primer elemento
		queue.insert(new Arista(root, 0));

		// O(n.log(n)) + O(a.log(n)) => O((n+a).log(n))
		while (!queue.isEmpty()) { // O(n)
			Arista current = queue.remove(); // O(log(n))
			int u = current.getHasta();
			visitados[u] = true;

			List<Integer> aristas = grafo.getAristasDe(u); // O(n)

			// O(a*log(n))
			for (Integer arista : aristas) { // O(a)
				if (blacklist != null && blacklist.contains(arista)) {
					continue;
				}

				Double costo = grafo.getArista(u, arista);

				if (!visitados[arista]) {
					double nuevoCosto = costo + current.getCosto();
					if (nuevoCosto < costos[arista]) {
						queue.insert(new Arista(u, arista, nuevoCosto)); // O(log(n))
						antecesores[arista] = u;
						costos[arista] = nuevoCosto;
					}
				}
			}
		}

		return costos;
	}

	public List<Integer> caminoHasta(int desde, int hasta) {
		List<Integer> lista = new LinkedList<>();
		Deque<Integer> pila = new LinkedList<>();

		pila.add(hasta);
		while (this.antecesores[hasta] != desde) {
			pila.push(this.antecesores[hasta]);
			hasta = this.antecesores[hasta];
		}
		pila.push(desde);

		while (!pila.isEmpty()) {
			lista.add(pila.pop());
		}

		return lista;
	}

	// O(n³)
	public static double[][] floyd(Grafo grafo) {
		double[][] result = new double[grafo.size()][grafo.size()];

		// O(n²)
		for (int i = 0; i < result.length; i++) { // O(n)
			for (int j = 0; j < result.length; j++) { // O(n)
				Double costo = grafo.getArista(i, j);
				result[i][j] = (costo != null) ? costo : Double.MAX_VALUE;
			}
			result[i][i] = 0;
		}

		// O(n³)
		for (int k = 0; k < result.length; k++) { // O(n)
			for (int i = 0; i < result.length; i++) { // O(n)
				for (int j = 0; j < result.length; j++) { // O(n)
					double costo = result[i][k] + result[k][j];
					result[i][j] = (costo < result[i][j]) ? costo : result[i][j];
				}
			}
		}

		return result;
	}

	// O(n³)
	public static boolean[][] warshall(Grafo grafo) {
		boolean[][] result = new boolean[grafo.size()][grafo.size()];

		// O(n²)
		for (int i = 0; i < result.length; i++) { // O(n)
			for (int j = 0; j < result.length; j++) { // O(n)
				Double costo = grafo.getArista(i, j);
				result[i][j] = (costo != null);
			}
		}

		// O(n³)
		for (int k = 0; k < result.length; k++) { // O(n)
			for (int i = 0; i < result.length; i++) { // O(n)
				for (int j = 0; j < result.length; j++) { // O(n)
					result[i][j] = (result[i][j] || (result[i][k] && result[k][j]));
				}
			}
		}

		return result;
	}

}
