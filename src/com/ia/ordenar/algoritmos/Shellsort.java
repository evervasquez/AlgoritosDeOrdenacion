package com.ia.ordenar.algoritmos;

import com.ia.ordenar.MainActivity.OrdenarAsyncTask;

public class Shellsort {

	private Long[] mNumerosOrdenar;
	private OrdenarAsyncTask objeto;

	public Shellsort(Long[] num, OrdenarAsyncTask obj) {
		this.mNumerosOrdenar = num;
		objeto = obj;
	}

	public Long[] Ordenar() {
		final int N = mNumerosOrdenar.length;
        int incremento = N;
        do {
        	if(!objeto.isCancelled()){
            incremento = incremento / 2;
            for (int k = 0; k < incremento; k++) {
                for (int i = incremento + k; i < N; i += incremento) {
                    int j = i;
                    while (j - incremento >= 0 && mNumerosOrdenar[j] < mNumerosOrdenar[j - incremento]) {
                        Long tmp = mNumerosOrdenar[j];
                        mNumerosOrdenar[j] = mNumerosOrdenar[j - incremento];
                        mNumerosOrdenar[j - incremento] = tmp;
                        j -= incremento;
                    }
                }
            }
        	}else{
        		break;
        	}
        } while (incremento > 1);
        
        return mNumerosOrdenar;
	}

}
