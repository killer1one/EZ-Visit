package Util;

import java.util.List;
import java.util.Map;

import com.mdsoft.movilbusiness.R.id;
import com.mdsoft.movilbusiness.impl.Funciones;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Row_ProductoTempAdapter extends SimpleAdapter {

	public Row_ProductoTempAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to) {
		super(context, data, resource, from, to);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = super.getView(position, convertView, parent);
		LinearLayout ll = (LinearLayout) view.findViewById(R.id.layout_row_producto_descuento);
		Map elemento = (Map) getItem(position);
		
		if(ll != null && Operaciones.ParPedDescAplic){//Caso MetalDom
			ll.setVisibility(View.VISIBLE);
			
		}
		
		if (Operaciones.ParReferenciaProductoEnPED2) {
			view.findViewById(R.id.PedProReferencia2).setVisibility(View.VISIBLE);
		}
		
		if (Operaciones.PedOfeCol && elemento.get("PedIndicadorOferta") != null &&
				Funciones.strToBool(elemento.get("PedIndicadorOferta").toString())) {
			view.setBackgroundColor(Operaciones.PedOfeColor);
		} else {
			view.setBackgroundColor(Color.WHITE);
		}
		
		return view;
	}

}
