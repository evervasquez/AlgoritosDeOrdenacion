package com.ia.ordenar;

import java.util.ArrayList;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ia.ordenar.adpters.Item;
import com.ia.ordenar.adpters.ListAdapter;
import com.ia.ordenar.adpters.OrdenacionFragment;
import com.ia.ordenar.algoritmos.Bubblesort;
import com.ia.ordenar.algoritmos.Insercion;
import com.ia.ordenar.algoritmos.Quicksort;
import com.ia.ordenar.algoritmos.Seleccion;
import com.ia.ordenar.algoritmos.Shellsort;

public class MainActivity extends Activity {
	public ListView listView;
	private ArrayList<Item> items;
	private String[] arraylist;
	private Resources resource;
	private static final int RESULT_SETTING = 1;
	public ProgressDialog pd;
	public DialogFragment newFragment;
	private Long[] arrayNumeros;
	public Long[] NumerosOrdenados;
	public TextView resultado;
	public TextView titulo_lista;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		resource = getResources();

		listView = (ListView) findViewById(R.id.lista);
		resultado = (TextView) findViewById(R.id.resultado);

		items = new ArrayList<Item>();

		arraylist = resource.getStringArray(R.array.array_list);

		items.add(new Item(arraylist[0], "Click para ordenación"));
		items.add(new Item(arraylist[1], "Click para ordenación"));
		items.add(new Item(arraylist[2], "Click para ordenación"));
		items.add(new Item(arraylist[3], "Click para ordenación"));
		items.add(new Item(arraylist[4], "Click para ordenación"));

		ListAdapter adapter = new ListAdapter(this, items);

		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				resultado.setText("");
				new OrdenarAsyncTask(position).execute();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			Intent intent = new Intent(MainActivity.this, ConfigActivity.class);
			startActivityForResult(intent, RESULT_SETTING);
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		finish();
		super.onBackPressed();
	}

	@Override
	// metodo para recuperar la url de preferences
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case RESULT_SETTING:
			showSettings();
			break;

		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	// recuperamos el total
	private String getTotal() {
		SharedPreferences pref = getSharedPreferences("OrdenarPreferences",
				MODE_PRIVATE);
		return pref.getString("configTotal", "0");
	}

	private void showSettings() {
		SharedPreferences mSharePreferences = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		String total = mSharePreferences.getString("configTotal", "NULL");

		mSharePreferences = getSharedPreferences("OrdenarPreferences",
				MODE_PRIVATE);

		// guardamos las preferencias en un xml
		SharedPreferences.Editor editor = mSharePreferences.edit();
		editor.putString("configTotal", total);
		editor.commit();
		new datosAsyncTask().execute();
		titulo_lista = (TextView) findViewById(R.id.titulo_lista);
		titulo_lista.setText("Ordenar " + getTotal() + " numeros");
	}

	private class datosAsyncTask extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			pd = ProgressDialog.show(MainActivity.this, "", "Creando los "
					+ getTotal() + " Numeros aleatorios", true);
		}

		@Override
		protected Void doInBackground(String... arg0) {
			
			if(Long.parseLong(getTotal()) <= 1000000){
			NumerosRandom num = new NumerosRandom(Long.parseLong(getTotal()));
			arrayNumeros = num.generarNumeros();
			}else{
				cancel(true);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			pd.dismiss();
		}

		@Override
		protected void onCancelled() {
			titulo_lista.setText("Configure la cantidad de numeros a ordenar");
			Toast.makeText(MainActivity.this,
					"El total de numeros a ordenar tiene que ser menor o igual a un millon", Toast.LENGTH_SHORT).show();
			pd.dismiss();
			super.onCancelled();
		}

	}

	// otro
	public class OrdenarAsyncTask extends AsyncTask<String, Void, Void> {
		private int posicion;

		public OrdenarAsyncTask(int position) {
			this.posicion = position;
		}

		@Override
		protected Void doInBackground(String... arg0) {
			newFragment = OrdenacionFragment.newInstance(listView, posicion,
					OrdenarAsyncTask.this);
			newFragment.show(getFragmentManager(), "a");

			if (posicion == 0) {
				NumerosOrdenados = new Bubblesort(arrayNumeros,OrdenarAsyncTask.this).Ordenar();
			}
			if (posicion == 1) {
				NumerosOrdenados = new Insercion(arrayNumeros,OrdenarAsyncTask.this).Ordenar();
			}
			if (posicion == 2) {
				NumerosOrdenados = new Seleccion(arrayNumeros,OrdenarAsyncTask.this).Ordenar();
			}

			if (posicion == 3) {
				NumerosOrdenados = new Shellsort(arrayNumeros,OrdenarAsyncTask.this).Ordenar();
			}
			if (posicion == 4) {
				NumerosOrdenados = new Quicksort().Ordenar(arrayNumeros, 0,
						arrayNumeros.length - 1,OrdenarAsyncTask.this);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (!isCancelled()) {
				Toast.makeText(getApplicationContext(),
						"se ordeno correctamente", Toast.LENGTH_SHORT).show();
				newFragment.dismiss();
				newFragment = null;
				String res = "";
				if (NumerosOrdenados.length > 0) {
					for (int i = 0; i < 100; i++) {
						res += NumerosOrdenados[i].toString() + "-";
					}
					resultado.setText(res);
				}
				NumerosOrdenados = null;
			} 
		}
		
		@Override
		protected void onCancelled() {
			Toast.makeText(MainActivity.this,
					"Acción Cancelada", Toast.LENGTH_SHORT).show();
			pd.dismiss();
			super.onCancelled();
		}
	}

}
