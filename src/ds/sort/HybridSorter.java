package ds.sort;

public class HybridSorter<K extends Comparable<? super K>> implements Sorter<K> {

	@Override
	public void sort(Pair<K, ?>[] array, int left, int right) {
		int maxDepth = (int)(Math.floor(Math.log(right - left + 1) / Math.log(2)));
		sortHelper(array, left, right, maxDepth);
	}
	
	/* Introsort */
	public void sortHelper(Pair<K, ?>[] array, int left, int right, int depth) {
		if (depth == 0) { // If recursion exceed some level, alternate the algorithm from quicksort to heapsort
			AlternativeSorter<K> as = new AlternativeSorter<K>();
			as.sort(array, left, right);
			return;
		}
		
		int pivotIndex = (left + right) / 2;
		swap(array, pivotIndex, right); // move pivotIndex to right end
		
		int k = partition(array, left - 1, right, array[right].getKey());
		swap(array, k, right); // swap pivot (locate it proper place)
		
		if (k - left > 0) sortHelper(array, left, k - 1, depth - 1);
		if (right - k > 0) sortHelper(array, k + 1, right, depth - 1);
	}

	public int partition(Pair<K, ?>[] ar, int i, int j, K pivot) {
		int l = i;
		int r = j;

		do {
			while (ar[++l].getKey().compareTo(pivot) < 0);
			while (r != 0 && ar[--r].getKey().compareTo(pivot) > 0);
			swap(ar, l, r);
		} while (l < r);
		swap(ar, l, r); // reverse
		
		return l;
	}
	
	public void swap(Pair<K, ?>[] ar, int i, int j) {
		Pair<K, ?> temp = ar[i];
		ar[i] = ar[j];
		ar[j] = temp;
	}

}
