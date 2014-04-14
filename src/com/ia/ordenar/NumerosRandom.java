package com.ia.ordenar;



public class NumerosRandom {
	private Long[] numeros;
	private Long mTotal;
	
	public NumerosRandom(Long total) {
		this.mTotal = total;
		generarNumeros();
	}
	
	public Long[] generarNumeros() {
		int numerocreado;
		numeros = new Long[Integer.parseInt(mTotal.toString())];
		for (int i = 0; i < mTotal; i++) {
			numerocreado = (int) (Math.random()*5000 + 91);
			numeros[i] =(long) numerocreado;
		}
		return numeros;
	}
	
}
