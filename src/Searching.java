package grafos.algoritmos;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import grafos.Grafo;

public class Searching {
	
	private Searching() {}

	public static void BFS(Grafo grafo, int root) {
		boolean[] visitado = new boolean[grafo.size()];
		Queue<Integer> queue = new LinkedList<>();

		visitado[root] = true;
		queue.add(root);

		while (!queue.isEmpty()) {
			int v = queue.poll();

			List<Integer> aristas = grafo.getAristasDe(v);
			for (Integer w : aristas) {
				if (!visitado[w]) {
					visitado[w] = true;
					queue.add(w);
				}
			}
		}
	}

	//O(a+n)
	public static void DFS(Grafo grafo, int root) {
		boolean[] visitado = new boolean[grafo.size()];
		Deque<Integer> stack = new LinkedList<>();

		visitado[root] = true;
		stack.push(root); //O(1)

		//O(a+n)
		while (!stack.isEmpty()) {
			int v = stack.pop(); //O(1)

			List<Integer> aristas = grafo.getAristasDe(v); //O(n)
			
			for (Integer w : aristas) { //O(a)
				if (!visitado[w]) {
					visitado[w] = true;
					stack.push(w); //O(1)
				}
			}
		}
	}
}
