package ds.sort;

public class AlternativeSorter<K extends Comparable<? super K>> implements Sorter<K> {

	@Override
	public void sort(Pair<K, ?>[] array, int left, int right) {
		// Fill your code to sort the elements in `array`.
		// insertionSort(array, left, right);
		heapSort(array, left, right);
	}
	
	public void doHeap(Pair<K, ?>[] ar, int i, int n, int l) {
		Pair<K, ?> s = ar[l + i - 1];
		int c;
		
		while (i <= n / 2) {
			c = 2 * i;
			while (c < n && ar[l+c-1].getKey().compareTo(ar[l+c].getKey()) < 0) {
				c++;
			}
			if (s.getKey().compareTo(ar[l+c-1].getKey()) >= 0) {
				break;
			}
			
			ar[l+i-1] = ar[l+c-1];
			i = c;
		}

		ar[l+i-1] = s;
	}
	
	public void heapSort(Pair<K, ?>[] ar, int l, int r) {
		int n = r - l + 1;
		
		for (int i = n / 2; i >= 1; i--) { // Build heap
			doHeap(ar, i, n, l);
		}
		for (int i = n; i > 1; i--) {
			swap(ar, i + l - 1, l);
			doHeap(ar, 1, i - 1, l);
		}
	}
	
	public void insertionSort(Pair<K, ?>[] ar, int l, int r) {
		for (int i = l + 1; i <= r; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (ar[j].getKey().compareTo(ar[j+1].getKey()) > 0) {
					swap(ar, j, j + 1);
				} else {
					break;
				}
			}
		}
	}
	
	public void swap(Pair<K, ?>[] ar, int i, int j) {
		Pair<K, ?> temp = ar[i];
		ar[i] = ar[j];
		ar[j] = temp;
	}

}
