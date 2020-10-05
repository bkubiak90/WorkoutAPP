package bkubiak90.mytrainings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder> {
    private static final String TAG = "PlanAdapter";

    public interface RemovePlan{
        void onRemovePlanResult(Plan plan);
    }

    private RemovePlan removePlan;
    private ArrayList<Plan> plans = new ArrayList<>();
    private Context context;
    private String type = "";

    public PlanAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: Called");

        holder.name.setText(plans.get(position).getTraining().getName());
        holder.time.setText(String.valueOf(plans.get(position).getMinutes()));
        holder.description.setText(plans.get(position).getTraining().getShortDesc());
        holder.image.setImageResource(plans.get(position).getTraining().getImageId());
        holder.timeOrReps.setText(plans.get(position).getTraining().getTimeOrReps());
        /*Glide.with(context)
                .asBitmap()
                .load(plans.get(position).getTraining().getImageUrl())
                .into(holder.image);*/

        if (plans.get(position).isAccomplished()) {
            holder.emptyCircle.setVisibility(View.GONE);
            holder.checkedCircle.setVisibility(View.VISIBLE);
        } else {
            holder.checkedCircle.setVisibility(View.GONE);
            holder.emptyCircle.setVisibility(View.VISIBLE);
        }

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TrainingActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(TRAINING_KEY, plans.get(position).getTraining());
                context.startActivity(intent);
            }
        });

        if (type.equals("edit")) {
            holder.emptyCircle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context)
                            .setTitle("Wykonano")
                            .setMessage("Czy ukończyłeś trening " + plans.get(position).getTraining().getName() + "?")
                            .setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    for (Plan p : Utils.getPlans()) {
                                        if (p.equals(plans.get(position))) {
                                            p.setAccomplished(true);
                                        }
                                    }
                                    notifyDataSetChanged();
                                }
                            });
                    builder.create().show();
                }
            });
            holder.parent.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context)
                            .setTitle("Usuń")
                            .setMessage("Czy jesteś pewien, że chcesz skasować " + plans.get(position).getTraining().getName() + " ze swojego planu?" )
                            .setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    try {
                                        removePlan = (RemovePlan) context;
                                        removePlan.onRemovePlanResult(plans.get(position));
                                    } catch (ClassCastException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                    builder.create().show();
                    return true;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return plans.size();
    }

    public void setPlans(ArrayList<Plan> plans) {
        this.plans = plans;
        notifyDataSetChanged();
    }

    public void setType(String type) {
        this.type = type;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name, time, description, timeOrReps;
        private ImageView image, emptyCircle, checkedCircle;
        private MaterialCardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtName);
            time = itemView.findViewById(R.id.txtTime);
            description = itemView.findViewById(R.id.txtDescription);
            image = itemView.findViewById(R.id.trainingImage);
            emptyCircle = itemView.findViewById(R.id.emptyCircle);
            checkedCircle = itemView.findViewById(R.id.checkedCircle);
            parent = itemView.findViewById(R.id.parent);
            timeOrReps = itemView.findViewById(R.id.timeOrRepsTxt);
        }
    }
}
