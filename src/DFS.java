public static void DFS(Grafo grafo, int source) { //O(A+N)
	
	boolean[] visitado = new boolean[grafo.size()]; //marco todos los nodos como no visitados por defecto
	Deque<Integer> stack = new LinkedList<>(); //creo una pila
	
	visitado[source] = true; //marco el nodo actual como visitado
	stack.push(source); //Agrego el primer nodo a la pila

	//O(A+N)
	while (!stack.isEmpty()) { //Mientras la pila no esté vacía.
		int v = stack.pop(); //O(1)
		//Obtengo las aristas del nodo v
		List<Integer> aristas = grafo.getAristasDe(v); //O(n)
		//Recorro las aristas 
		for (Integer w : aristas) { //O(a)
			if (!visitado[w]) {
				visitado[w] = true;
				stack.push(w); //O(1)
			}
		}
	}
} 
