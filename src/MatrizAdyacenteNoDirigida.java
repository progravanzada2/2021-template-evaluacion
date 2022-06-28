package grafos;

public class MatrizAdyacenteNoDirigida extends MatrizAdyacente {

	public MatrizAdyacenteNoDirigida(int size) {
		super(size);
	}

	public MatrizAdyacenteNoDirigida(double[][] grafo) {
		super(grafo);
	}

	@Override
	public void setArista(int desde, int hasta, double costo) {
		grafo[desde][hasta] = costo;
		grafo[hasta][desde] = costo;
	}

	@Override
	public double costo() {
		return super.costo() / 2;
	}

}
