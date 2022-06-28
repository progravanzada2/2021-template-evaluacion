package grafos;

import java.util.List;

public interface Grafo {

	/**
	 * Cantidad de Nodos pertenecientes al grafo.
	 * 
	 * @return int - Cantidad de Nodos
	 */
	int size();

	/**
	 * Calcula el costo del grafo, util para comparar el costo entre distintos
	 * grafos.
	 * 
	 * @return double - Costo del grafo.
	 */
	double costo();

	/**
	 * Crea una Arista en el Grafo desde un Nodo hasta el otro con el costo
	 * indicado.
	 * 
	 * @param desde - int
	 * @param hasta - int
	 * @param costo - double
	 */
	void setArista(int desde, int hasta, double costo);

	/**
	 * Obtiene el costo de una Arista desde un Nodo hasta el otro.
	 * 
	 * @param desde - int
	 * @param hasta - int
	 * @return Double - Costo de la Arista o null si no existe.
	 */
	Double getArista(int desde, int hasta);

	List<Integer> getAristasDe(int desde);

}
