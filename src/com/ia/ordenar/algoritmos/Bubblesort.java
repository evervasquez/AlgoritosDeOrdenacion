package com.ia.ordenar.algoritmos;

import com.ia.ordenar.MainActivity.OrdenarAsyncTask;

public class Bubblesort {
	private Long[] mNumerosOrdenar;
	private OrdenarAsyncTask objeto;

	public Bubblesort(Long[] num, OrdenarAsyncTask obj) {
		this.mNumerosOrdenar = num;
		objeto = obj;
	}

	public Long[] Ordenar() {
		Long buffer;
		int i, j;
		for (i = 0; i < mNumerosOrdenar.length; i++) {
			if (!objeto.isCancelled()) {
				for (j = 0; j < i; j++) {
					if (mNumerosOrdenar[i] < mNumerosOrdenar[j]) {
						buffer = mNumerosOrdenar[j];
						mNumerosOrdenar[j] = mNumerosOrdenar[i];
						mNumerosOrdenar[i] = buffer;
					}
				}
			}else{
				break;
			}
		}
		return mNumerosOrdenar;
	}
}
