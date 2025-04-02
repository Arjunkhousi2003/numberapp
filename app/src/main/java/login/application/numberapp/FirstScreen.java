package login.application.numberapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;



import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FirstScreen extends AppCompatActivity {


    private GridView grid;
    private Spinner spinner_select;
    private Adapter_page adapter;
    private List<Integer> numbers;
    String [] operations= new String[]{"Select operation", "Odd Numbers", "Even Numbers", "Prime Numbers", "Fibonacci Numbers"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

        grid = findViewById(R.id.grid);
        spinner_select = findViewById(R.id.spinner);

        numbers = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            numbers.add(i);
        }

        adapter = new Adapter_page(this, numbers, new HashSet<>());
        grid.setAdapter(adapter);

        ArrayAdapter<String> s = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,operations);
        s.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_select.setAdapter(s);

        spinner_select.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                update(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void update(int r) {
        Set<Integer> highlightedNumbers = new HashSet<>();
        switch (r) {
            case 1:
                for (int num : numbers) {
                    if (num % 2 != 0) {
                        highlightedNumbers.add(num);
                    }
                }
                break;
            case 2:
                for (int num : numbers) {
                    if (num % 2 == 0) {
                        highlightedNumbers.add(num);
                    }
                    }

                break;
            case 3:
                for (int num : numbers) {
                    if (prime(num))
                    {
                        highlightedNumbers.add(num);
                    }
                }
                break;
            case 4:
                highlightedNumbers.addAll(fibonacci(100));
                break;
        }
        adapter.updateHighlightedNumbers(highlightedNumbers);
    }

    private boolean prime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private Set<Integer> fibonacci(int max) {
        Set<Integer> fibSet = new HashSet<>();
        int a = 0, b = 1;
        while (a <= max) {
            fibSet.add(a);
            int temp = a + b;
            a = b;
            b = temp;
        }
        return fibSet;
    }}