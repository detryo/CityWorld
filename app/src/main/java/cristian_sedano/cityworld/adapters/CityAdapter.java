package cristian_sedano.cityworld.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cristian_sedano.cityworld.R;
import cristian_sedano.cityworld.models.City;

/**
 * Created by Christian on 17/10/2017.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private Context context;
    private List<City> cities;
    private int layout;
    private OnItemClickListener itemClickListener;
    private OnButtonClickListener btnClickListener;


    public CityAdapter(List<City> cities, int layout, OnItemClickListener itemListener, OnButtonClickListener btnListener) {
        this.cities = cities;
        this.layout = layout;
        this.itemClickListener = itemListener;
        this.btnClickListener = btnListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        context = parent.getContext();
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(cities.get(position), itemClickListener, btnClickListener);
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView description;
        public TextView stars;
        public ImageView image;
        public Button btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textViewCityName);
            description = (TextView) itemView.findViewById(R.id.textViewCityDescription);
            stars = (TextView) itemView.findViewById(R.id.textViewStars);
            image = (ImageView) itemView.findViewById(R.id.imageViewCity);
            btnDelete = (Button) itemView.findViewById(R.id.buttonDeleteCity);
        }

        public void bind(final City city, final OnItemClickListener itemListener, final OnButtonClickListener btnListener) {
            name.setText(city.getName());
            description.setText(city.getDescription());
            stars.setText(city.getStars() + "");
            Picasso.with(context).load(city.getImage()).fit().into(image);


            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnListener.onButtonClick(city, getAdapterPosition());
                }
            });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemListener.onItemClick(city, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(City city, int position);
    }

    public interface OnButtonClickListener {
        void onButtonClick(City city, int position);
    }

}
