package dadm.quixada.ufc.atv2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CarroAdapter extends ArrayAdapter<Carro> {

    private Context context;
    private int resource;

    public CarroAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Carro> objects){

        super(context,resource, objects);
        this.context = context;
        this.resource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        convertView = layoutInflater.inflate(resource,parent,false);

        TextView textNome = convertView.findViewById(R.id.textViewNome);

        TextView textId = convertView.findViewById(R.id.textViewId);

        textNome.setText(getItem(position).getNome());

        textId.setText(String.valueOf(getItem(position).getId()));

        return convertView;
    }
}
