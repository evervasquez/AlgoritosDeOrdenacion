package com.ia.ordenar.algoritmos;

import com.ia.ordenar.MainActivity.OrdenarAsyncTask;

public class Quicksort {

	public Long[] Ordenar(Long[] mNumerosOrdenar, int inicio, int fin,
			OrdenarAsyncTask obj) {
		int i = inicio, j = fin;
		Long pivote = mNumerosOrdenar[(inicio + fin) / 2];
		Long auxiliar;
		do {
			if (!obj.isCancelled()) {
				while (mNumerosOrdenar[i] < pivote)
					i++;
				while (mNumerosOrdenar[j] > pivote)
					j--;
				if (i <= j) {
					auxiliar = mNumerosOrdenar[j];
					mNumerosOrdenar[j] = mNumerosOrdenar[i];
					mNumerosOrdenar[i] = auxiliar;
					i++;
					j--;
				}
			} else {
				break;
			}
		} while (i <= j);
		if (inicio < j) {
			if (!obj.isCancelled()) {
			Ordenar(mNumerosOrdenar, inicio, j, obj);
			}
		}
		if (fin > i) {
			if (!obj.isCancelled()) {
			Ordenar(mNumerosOrdenar, i, fin, obj);
			}
		}

		return mNumerosOrdenar;

	}

}
