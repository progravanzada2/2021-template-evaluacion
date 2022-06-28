package grafos;

public class ListaAdyacenteNoDirigida extends ListaAdyacente {

	public ListaAdyacenteNoDirigida(int size) {
		super(size);
	}

	public ListaAdyacenteNoDirigida(Arista[] grafo, int length) {
		super(grafo, length);
	}

	@Override
	public void setArista(int desde, int hasta, double costo) {
		super.setArista(desde, hasta, costo);
		super.setArista(hasta, desde, costo);
	}

	@Override
	public double costo() {
		return super.costo() / 2;
	}

}
