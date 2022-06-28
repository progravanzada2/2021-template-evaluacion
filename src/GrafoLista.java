package grafos;

import java.util.Comparator;

/**
 * Extension de la interfaz Grafo, que incluye un par de herramientas utiles
 * para un grafo representado como una Lista Adyacente.
 */
public interface GrafoLista extends Grafo {

	/**
	 * Asigna un nombre a los Nodos del Grafo, la String Array tiene que ser del
	 * mismo tamaño que el Grafo y los nombres son asignados de forma ascendente.
	 * 
	 * @param nombres - String[]
	 * @throws Exception Cuando la String[] dada es menor o mayor al tamaño del
	 *                   Grafo.
	 */
	void setNombres(String[] nombres) throws Exception;

	/**
	 * Ordena el Grafo en base al Comparator especificado como parametro.
	 * 
	 * @param cmp - Comparator<Arista>
	 */
	void sort(Comparator<Arista> cmp);

}
