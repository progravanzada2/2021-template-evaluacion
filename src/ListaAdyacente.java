package grafos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListaAdyacente implements GrafoLista {

	protected List<Arista>[] grafo;

	@SuppressWarnings("unchecked")
	public ListaAdyacente(int size) {
		grafo = new List[size];

		for (int i = 0; i < size; i++) {
			grafo[i] = new ArrayList<>();
		}
	}

	public ListaAdyacente(Arista[] grafo, int length) {
		this(length);

		for (int i = 0; i < grafo.length; i++) {
			Arista arista = grafo[i];
			this.setArista(arista.getDesde(), arista.getHasta(), arista.getCosto());
		}
	}

	@Override
	public int size() {
		return grafo.length;
	}

	@Override
	public void setArista(int desde, int hasta, double costo) {
		for (Arista arista : grafo[desde]) {
			if (arista.getHasta() == hasta) {
				arista.setCosto(costo);
				return;
			}
		}

		grafo[desde].add(new Arista(desde, hasta, costo));
	}

	@Override
	public Double getArista(int desde, int hasta) {
		for (Arista arista : grafo[desde]) {
			if (arista.getHasta() == hasta) {
				return arista.getCosto();
			}
		}

		return null;
	}

	@Override
	public List<Integer> getAristasDe(int desde) {
		List<Integer> aristas = new ArrayList<>();

		for (Arista arista : grafo[desde]) {
			if (arista.getHasta() != desde) {
				aristas.add(arista.getHasta());
			}
		}

		return aristas;
	}

	@Override
	public double costo() {
		double costo = 0;

		for (int i = 0; i < grafo.length; i++) {
			for (int j = 0; j < grafo[i].size(); j++) {
				Arista n = grafo[i].get(j);
				if (n != null) {
					costo += n.getCosto();
				}
			}
		}

		return costo;
	}

	@Override
	public void setNombres(String[] nombres) throws Exception {
		if (nombres.length != this.size()) {
			throw new Exception("Cantidad de nombres insuficiente para la cantidad de nodos disponibles.");
		}

		for (List<Arista> list : grafo) {
			for (Arista arista : list) {
				arista.setNombres(nombres[arista.getDesde()], nombres[arista.getHasta()]);
			}
		}
	}

	@Override
	public void sort(Comparator<Arista> cmp) {
		for (List<Arista> list : grafo) {
			list.sort(cmp);
		}
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		boolean first = true;

		for (int i = 0; i < grafo.length; i++) {
			for (int j = 0; j < grafo[i].size(); j++) {
				if (grafo[i].get(j).costo != 0) {
					if (!first) {
						str.append("\n");
					}
					str.append(grafo[i].get(j));
					first = false;
				}
			}
		}

		return str.toString();
	}

}
