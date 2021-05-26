package com.example.lesson3android3.ui.detail;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.lesson3android3.R;


public class ProgressButton {

    private CardView cardView;
    private ConstraintLayout constraintLayout;
    private ProgressBar progressBar;
    private TextView textView;
    private Animation fadeIn;

    public ProgressButton(Context context, View view) {
        fadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        cardView = view.findViewById(R.id.cardViewForPBar);
        constraintLayout = view.findViewById(R.id.constraintForPBar);
        progressBar = view.findViewById(R.id.progressBarBtn);
        textView = view.findViewById(R.id.textViewForPBar);
    }

    public void buttonActivated() {
        progressBar.setAnimation(fadeIn);
        progressBar.setVisibility(View.VISIBLE);
        textView.setAnimation(fadeIn);
        textView.setText("Saving");
    }

    public void buttonFinished() {
        constraintLayout.setBackgroundColor(cardView.getResources().getColor(R.color.grey));
        progressBar.setVisibility(View.GONE);
        textView.setText("DONE");
    }
}
