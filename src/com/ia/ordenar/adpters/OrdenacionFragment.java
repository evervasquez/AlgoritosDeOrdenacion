package com.ia.ordenar.adpters;

import android.app.DialogFragment;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.TextView;

import com.ia.ordenar.MainActivity.OrdenarAsyncTask;
import com.ia.ordenar.R;

public class OrdenacionFragment extends DialogFragment {
	private Chronometer mCronometro;
	private String mTiempo;
	private View mView;
	private static ListView list;
	private static int posicion;
	private static OrdenarAsyncTask contexto;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.cronometro, container);
		mCronometro = (Chronometer) mView.findViewById(R.id.chronometer1);
		mCronometro.setBase(SystemClock.elapsedRealtime());
		getDialog().setTitle("Tiempo de Calculo");
		Iniciar();
		return mView;
	}

	public static OrdenacionFragment newInstance(ListView view, int position, OrdenarAsyncTask context) {
		OrdenacionFragment f = new OrdenacionFragment();
		list = view;
		posicion = position;
		contexto = context;
		return f;
	}

	public void Iniciar() {
		mCronometro.start();
	}

	@Override
	public void onDestroy() {
		mCronometro.stop();
		mTiempo = (String) mCronometro.getText();
		mCronometro.setBase(SystemClock.elapsedRealtime());
		setSubtitle(mTiempo);
		contexto.cancel(true);
		super.onDestroy();
	}

	

	public void setSubtitle(String tiempo) {
		View view = list.getChildAt(posicion);
		TextView subtitulo = (TextView) view.findViewById(R.id.txt_time);
		subtitulo.setText("Tiempo de Ordenación :" + tiempo);
	}
}
