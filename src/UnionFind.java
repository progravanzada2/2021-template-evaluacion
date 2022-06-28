package grafos;

import java.util.Objects;

public class UnionFind {

	private int id;
	private int rank;
	private UnionFind parent;

	public UnionFind(final int id) {
		this.id = id;
		this.rank = 0;
		this.parent = this;
	}

	public UnionFind find() {
		UnionFind x = this;

		while (!x.parent.equals(x)) {
			x.parent = x.parent.parent;
			x = x.parent;
		}

		return x;
	}

	public void union(UnionFind y) {
		UnionFind x = this.find();
		y = y.find();

		if (x.equals(y)) {
			return;
		}

		if (y.rank > x.rank) {
			UnionFind temp = x;
			x = y;
			y = temp;
		}

		y.parent = x;

		if (x.rank == y.rank) {
			x.rank++;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnionFind other = (UnionFind) obj;
		return id == other.id;
	}
}
