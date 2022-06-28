package grafos;

/**
 * Define la relacion entre dos Nodos de un Grafo.
 */
public class Arista implements Comparable<Arista> {

	protected Nodo desde;
	protected Nodo hasta;
	protected double costo;

	public Arista(int desde, int hasta, double costo) {
		this.desde = new Nodo(desde, String.valueOf(desde));
		this.hasta = new Nodo(hasta, String.valueOf(hasta));
		this.costo = costo;
	}

	public Arista(int desde, double costo) {
		this(desde, desde, costo);
	}

	public int getDesde() {
		return desde.getId();
	}

	public int getHasta() {
		return hasta.getId();
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public String getDesdeNombre() {
		return desde.getNombre();
	}

	public String getHastaNombre() {
		return hasta.getNombre();
	}

	public void setNombres(String desde, String hasta) {
		this.desde.setNombre(desde);
		this.hasta.setNombre(hasta);
	}

	@Override
	public String toString() {
		return hasta + " " + desde;
	}

	@Override
	public int compareTo(Arista o) {
		return (int) (this.costo - o.costo);
	}

}
