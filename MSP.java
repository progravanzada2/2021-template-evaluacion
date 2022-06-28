package grafos.algoritmos;

import java.util.ArrayList;
import java.util.List;

import grafos.Arista;
import grafos.Grafo;
import grafos.GrafoLista;
import grafos.ListaAdyacente;
import grafos.UnionFind;
import monticulos.ColaDePrioridad;
import monticulos.MinMonticulo;

/**
 * Minimum Spanning Tree (MSP), es un sub-grafo que busca conectar todos los
 * nodos del grafo de una forma tal que no contenga ciclos y tenga el menor
 * costo posible al recorrerlo.
 */
public class MSP {

	private MSP() {}

	//O((n+a).log(n))
	public static GrafoLista prim(Grafo grafo, Arista raiz) {
		GrafoLista resultado = new ListaAdyacente(grafo.size());

		double[] costos = new double[grafo.size()];
		boolean[] visitado = new boolean[grafo.size()];

		//O(n)
		for (int i = 0; i < grafo.size(); i++) {
			costos[i] = Double.MAX_VALUE;
		}

		// Guardamos los Nodos a revisar en una Cola de Prioridad para optimizar el
		// tiempo de busqueda para ir al siguiente nodo con el menor costo.
		ColaDePrioridad<Arista> queue = new MinMonticulo<>();
		queue.insert(raiz); //O(1) -> Primer elemento insertado

		//O(n.log(n)) + O(a.log(n)) => O((n+a).log(n))
		while (!queue.isEmpty()) { //O(n)
			Arista current = queue.remove(); //O(log(n))
			int u = current.getHasta();
			visitado[u] = true;

			double minCosto = current.getCosto();

			if (minCosto < costos[u]) {
				costos[u] = minCosto;
				resultado.setArista(current.getDesde(), u, minCosto);
			}

			List<Integer> aristas = grafo.getAristasDe(u); //O(n)
			//O(a.log(n))
			for (Integer arista : aristas) { //O(a)
				if (!visitado[arista]) {
					Double costo = grafo.getArista(u, arista);
					queue.insert(new Arista(u, arista, costo)); //O(log(n))
				}
			}
		}

		return resultado;
	}

	//O((a+n).log(n))
	public static GrafoLista kruskal(Grafo grafo) {
		GrafoLista resultado = new ListaAdyacente(grafo.size());
		List<Arista> lista = new ArrayList<>();
		UnionFind[] set = new UnionFind[grafo.size()];

		for (int i = 0; i < grafo.size(); i++) { //O(n)
			set[i] = new UnionFind(i);

			List<Integer> aristas = grafo.getAristasDe(i); //O(n)
			for (Integer arista : aristas) { //O(a)
				Double costo = grafo.getArista(i, arista);
				lista.add(new Arista(i, arista, costo));
			}
		}
		
		lista.sort(Arista::compareTo); //O(n.log(n))

		//O(a.log(n))
		for (Arista current : lista) { //O(a)
			int desde = current.getDesde();
			int hasta = current.getHasta();

			//O(log(n))
			if (!set[desde].find().equals(set[hasta].find())) {
				resultado.setArista(desde, hasta, current.getCosto());
				set[desde].union(set[hasta]);
			}
		}

		return resultado;
	}

}
