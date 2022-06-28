public double[] dijkstra(int desde) {
	
	double[] distancias = new double[getNodos()];
	this.predecesores = new int[getNodos()];

	//Conjunto S - contiene todos los nodos analizados del grafo
	Set<Integer> s = new HashSet<Integer>();

	s.add(desde);
	//Conjunto v-S - contiene todos los nodos no analizados del grafo.
	Set<Integer> vMenosS = new HashSet<Integer>();

	// cargo v-S con todos los nodos excepto el que se encuentra ya en s
	for (int i = 0; i < this.getNodos(); i++) {
		if (desde != i)
			vMenosS.add(i);
	}
	// Se inicializa el vector distancias con infinito(Double.MAX_VALUE) en todas las posiciones
	for (int i = 0; i < distancias.length; i++) {
		distancias[i] = Double.MAX_VALUE;
	}

	distancias[desde] = 0;

	// Se inicializa vector de predecesores con el nodo inicial
	for (int i = 0; i < this.predecesores.length; i++) {
		this.predecesores[i] = desde;
	}

	// Primer paso: se carga en distancias todas las distancias a nodos directos desde el nodo inicial
	for (Nodo nodo : this.grafo.get(desde)) {
		distancias[nodo.getId()] = nodo.getPeso();
	}

	// Segundo y n pasos... Mientras v-S no sea vacio
	while (!vMenosS.isEmpty()) {
		boolean bandera = false;
		double min = 0;
		int w = 0;

		for (Integer integer : vMenosS) {
			if (!bandera) {
				min = distancias[integer];
				w = integer;
				bandera = true;
			} else {
				if (distancias[integer] < min) {
					min = distancias[integer];
					w = integer;
				}
			}
		}
		// ya calcule el minimo, lo eliminamos del conjunto v-S y lo agregamos al conjunto s.
		vMenosS.remove(w);
		s.add(w);
		PriorityQueue<Nodo> auxiliar = new PriorityQueue<Nodo>();
		auxiliar.addAll(this.grafo.get(w));

		while (!auxiliar.isEmpty()) {
			Nodo nodoAux = auxiliar.poll();
			if (distancias[nodoAux.getId()] > distancias[w] + nodoAux.getPeso()) {
				distancias[nodoAux.getId()] = distancias[w] + nodoAux.getPeso();
				this.predecesores[nodoAux.getId()] = w;
			}
		}

	}
	return distancias;
}
