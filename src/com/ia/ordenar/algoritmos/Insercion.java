package com.ia.ordenar.algoritmos;

import com.ia.ordenar.MainActivity.OrdenarAsyncTask;

public class Insercion {

	private Long[] mNumerosOrdenar;
	private OrdenarAsyncTask objeto;

	public Insercion(Long[] num, OrdenarAsyncTask obj) {
		this.mNumerosOrdenar = num;
		objeto = obj;
	}

	public Long[] Ordenar() {
		int i, a;
		Long index;
		  for (i=1; i < mNumerosOrdenar.length; i++) {
			  if (!objeto.isCancelled()) {
		    index = mNumerosOrdenar[i];
		    a = i-1;
		    while (a >= 0 && mNumerosOrdenar[a] > index) {
		    	mNumerosOrdenar[a + 1] = mNumerosOrdenar[a];
		      a--;
		    }
		    mNumerosOrdenar[a+1] = index;
			  }else{
				  break;
			  }
		  }
		  return mNumerosOrdenar;
	}
}
