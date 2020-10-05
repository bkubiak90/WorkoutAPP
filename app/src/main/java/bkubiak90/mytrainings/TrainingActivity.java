package bkubiak90.mytrainings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class TrainingActivity extends AppCompatActivity implements PlanDetailDialog.PassPlanInterface {
    private static final String TAG = "TrainingActivity";

    public static final String TRAINING_KEY = "training";

    @Override
    public void getPlan(Plan plan) {
        Log.d(TAG, "getPlan: Plan" + plan.toString());

        if (Utils.addPlan(plan)) {
            Toast.makeText(this,  plan.getTraining().getName() + " dodano do twojego planu", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, PlanActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    private Button btnAddToPlan;
    private TextView txtTrainingName, txtDescription;
    private ImageView imgTraining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        initViews();

        Intent intent = getIntent();
        if (null != intent) {
            final Training training = intent.getParcelableExtra(TRAINING_KEY);
            if (null != training) {
                txtTrainingName.setText(training.getName());
                txtDescription.setText(training.getLongDesc());
                imgTraining.setImageResource(training.getImageId());
                /*Glide.with(this)
                        .asBitmap()
                        .load(training.getImageUrl())
                        .into(imgTraining);*/
                btnAddToPlan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: 13.09.2020 show the dialog
                        PlanDetailDialog dialog = new PlanDetailDialog();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(TRAINING_KEY, training);
                        dialog.setArguments(bundle);
                        dialog.show(getSupportFragmentManager(), "plan detail dialog ");
                    }
                });
            }
        }

    }

    private void initViews() {
        Log.d(TAG, "initViews: started");
        btnAddToPlan = findViewById(R.id.btnAddToPlan);
        txtTrainingName = findViewById(R.id.txtName);
        txtDescription = findViewById(R.id.txtDescription);
        imgTraining = findViewById(R.id.trainingImage);


    }

    
}