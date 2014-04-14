package com.ia.ordenar.algoritmos;

import com.ia.ordenar.MainActivity.OrdenarAsyncTask;

public class Seleccion {
	private Long[] mNumerosOrdenar;
	private OrdenarAsyncTask objeto;

	public Seleccion(Long[] num, OrdenarAsyncTask obj) {
		this.mNumerosOrdenar = num;
		objeto = obj;
	}

	public Long[] Ordenar() {
		int i, k, p, limit = mNumerosOrdenar.length - 1;
		Long buffer;
		for (k = 0; k < limit; k++) {
			p = k;
			if(!objeto.isCancelled()){
			for (i = k + 1; i <= limit; i++)
				if (mNumerosOrdenar[i] < mNumerosOrdenar[p])
					p = i;
			if (p != k) {
				buffer = mNumerosOrdenar[p];
				mNumerosOrdenar[p] = mNumerosOrdenar[k];
				mNumerosOrdenar[k] = buffer;
			}
			}else{
				break;
			}
		}
		
		return mNumerosOrdenar;
	}
}
