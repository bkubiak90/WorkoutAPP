package bkubiak90.mytrainings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class AllTrainingsActivity extends AppCompatActivity {

    /**
     * Użycie adaptera
     * 1. Stworzenie pól i zainicjowanie ich;
     * 2. przypisanie adaptera do recyclerView
     * 3. określenie layoutu dla recyclerView
     */
    private RecyclerView recyclerView;
    private TrainingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_training);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new TrainingAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        ArrayList<Training> allTrainings = Utils.getTrainings();
        if (null != allTrainings) {
            adapter.setTrainings(allTrainings);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AllTrainingsActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}