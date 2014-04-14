package com.ia.ordenar.adpters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ia.ordenar.R;

public class ListAdapter extends BaseAdapter {
	private Activity actividad;
	private ArrayList<Item> arrayitems;

	public ListAdapter(Activity activity, ArrayList<Item> items) {
		this.actividad = activity;
		this.arrayitems = items;
	}

	@Override
	public int getCount() {
		return arrayitems.size();
	}

	@Override
	public Object getItem(int position) {
		return arrayitems.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	static class ViewHolderItem {
		TextView title;
		TextView subtitle;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		 
		// Objeto ViewHolder
		ViewHolderItem viewholder;
        // Generamos una convertView por motivos de eficiencia
        View v = convertView;
        //Asociamos el layout de la lista que hemos creado e incrustamos el ViewHolder
        if(convertView == null){
            LayoutInflater inf = (LayoutInflater) actividad.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item_list, null);
            viewholder = new ViewHolderItem();
            viewholder.title = (TextView) v.findViewById(R.id.txt_title);
            viewholder.subtitle = (TextView) v.findViewById(R.id.txt_time);
            v.setTag(viewholder);
        }
 
        // Creamos un objeto directivo
        Item dir = arrayitems.get(position);
 
        //Rellenamos la información utilizando el ViewHolder
        viewholder = (ViewHolderItem) v.getTag();
        viewholder.title.setText(dir.getTitulo());
        viewholder.subtitle.setText(dir.getSubtitulo());
 
        // Retornamos la vista
        return v;
	}
}
