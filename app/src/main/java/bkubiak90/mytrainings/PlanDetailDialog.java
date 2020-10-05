package bkubiak90.mytrainings;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import static bkubiak90.mytrainings.TrainingActivity.TRAINING_KEY;

public class PlanDetailDialog extends DialogFragment {

    public interface  PassPlanInterface {
        void getPlan(Plan plan);
    }

    private PassPlanInterface passPlanInterface;

    private Button add, dismiss;
    private TextView name;
    private EditText edtMinutes;
    private Spinner daysSpinner;



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_plan_details, null);
        initViews(view);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(view);

        Bundle bundle = getArguments();
        if (null != bundle) {
            final Training training = bundle.getParcelable(TRAINING_KEY);
            if (null != training) {
                name.setText(training.getName());
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String day = daysSpinner.getSelectedItem().toString();
                        int minutes = Integer.valueOf(edtMinutes.getText().toString());
                        Plan plan = new Plan(training, minutes, day, false);

                        try {
                            passPlanInterface = (PassPlanInterface) getActivity();
                            passPlanInterface.getPlan(plan);
                            dismiss();
                        } catch (ClassCastException e) {
                            e.printStackTrace();
                            dismiss();
                        }
                    }
                });
                dismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                         dismiss();
                    }
                });

            }
        }
        return builder.create();
    }

    private void initViews(View view) {
        add = view.findViewById(R.id.btnAdd);
        dismiss = view.findViewById(R.id.btnDismiss);
        name = view.findViewById(R.id.txtTrainingName);
        edtMinutes = view.findViewById(R.id.edtTxtMinutes);
        daysSpinner = view.findViewById(R.id.spinnerDays);
    }

}
