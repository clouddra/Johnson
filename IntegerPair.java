class IntegerPair implements Comparable {
  Integer _index, _dist;

  public IntegerPair(Integer f, Integer s) {
    _index = f;
    _dist = s;
  }

	public int compareTo(Object o) {
		// compare by weight then vertex number
		if (this.dist() != ((IntegerPair )o).dist())
			return this.dist() - ((IntegerPair )o).dist();

		return this.index() - ((IntegerPair )o).index();
	}

  Integer dist() { return _dist; }
  Integer index() { return _index; }
}
