package bkubiak90.mytrainings;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import static bkubiak90.mytrainings.TrainingActivity.TRAINING_KEY;

/**
 * Tworzenie RecyclerViewAdaptera (3/9)
 * Rozszerzenie klasy głownej o RecyclerView.....
 */
public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.ViewHolder> {
    private static final String TAG = "TrainingAdapter";

    /**
     * Tworzenie RecyclerViewAdaptera (5/9)
     * Utworzenie pól: Lista obiektów, i Context(jeśli używamy obrazów)
     * Stworzenie konstruktora przyjmującego context jako parametr
     */
    private ArrayList<Training> trainings = new ArrayList<>();
    private Context context;

    public TrainingAdapter(Context context) {
        this.context = context;
    }

    /**
     * Tworzenie RecyclerViewAdaptera (4/9)
     * Zaimplementowanie 3 metod (Ctrl + I) onCreateViewHolder,onBindViewHolder,getItemCount
     */

    /**
     * Tworzenie RecyclerViewAdaptera (7/9)
     * Zmodyfikowanie metody onCreateViewHolder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.training_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Tworzenie RecyclerViewAdaptera (8/9)
     * Zmodyfikowanie metody onBindViewHolder
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: Called");

        holder.activityName.setText(trainings.get(position).getName());
        holder.activityDecription.setText(trainings.get(position).getShortDesc());
        holder.image.setImageResource(trainings.get(position).getImageId());
                /*.asBitmap()
                .load(trainings.get(position).getImageUrl())
                .into(holder.image);*/

        // Jeśli chcemy żeby wciśniecie całego kafelka coś robiło
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TrainingActivity.class);
                intent.putExtra(TRAINING_KEY, trainings.get(position));
                context.startActivity(intent);
            }
        });
    }

    /**
     * Tworzenie RecyclerViewAdaptera (6/9)
     * Zmodyfikowanie metody getItemCount, aby zwracała rozmiar listy
     */
    @Override
    public int getItemCount() {

        return trainings.size();
    }


    /**
     * Tworzenie RecyclerViewAdaptera (9/9)
     * Utworzenie settera do listy i dodanie notifyDataSetChanged
     */
    public void setTrainings(ArrayList<Training> trainings) {
        this.trainings = trainings;
        notifyDataSetChanged();
    }

    /**
     * Tworzenie RecyclerViewAdaptera (1/9)
     * Utworzenie klasy ViewHolder i jej konstruktora
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * Tworzenie RecyclerViewAdaptera (2/9)
         * Zainicjalizowanie pól klasy (takich jak użyliśmy w CardView - treiningItem)
         */
        private MaterialCardView parent;
        private ImageView image;
        private TextView activityName, activityDecription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            image = itemView.findViewById(R.id.imgActivity);
            activityName = itemView.findViewById(R.id.txtActivityName);
            activityDecription = itemView.findViewById(R.id.txtShortDescription);
        }
    }
}



