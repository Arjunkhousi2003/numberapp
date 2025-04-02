package login.application.numberapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Set;

public class Adapter_page extends BaseAdapter {
    private Context context;
    private List<Integer> numbers;
    private Set<Integer> highlighted;

    public Adapter_page(Context context, List<Integer> numbers, Set<Integer> highlightedNumbers) {
        this.context = context;
        this.numbers = numbers;
        this.highlighted = highlightedNumbers;
    }

    @Override
    public int getCount() {
        return numbers.size();
    }

    @Override
    public Object getItem(int position) {
        return numbers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View newView, ViewGroup parent) {
        if (newView == null) {
            newView = LayoutInflater.from(context).inflate(R.layout.adaptor_screen, parent, false);
        }

        TextView numberText = newView.findViewById(R.id.numberText);
        int number = numbers.get(position);
        numberText.setText(String.valueOf(number));

        if (highlighted.contains(number)) {
            newView.setBackgroundColor(0xFF64B5F6);
        } else {
            newView.setBackgroundColor(0xFFFFFFFF);
        }

        return newView;
    }

    public void updateHighlightedNumbers(Set<Integer> newHighlightedNumbers) {
        this.highlighted= newHighlightedNumbers;
        notifyDataSetChanged();
    }
}

