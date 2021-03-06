package cc.pogoda.mobile.pogodacc.activity.handler;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import cc.pogoda.mobile.pogodacc.activity.AllStationsActivity;

public class MainActImageButtonAllStationsClickEvent implements View.OnClickListener {

    AppCompatActivity parent;

    Intent intent;

    public MainActImageButtonAllStationsClickEvent(AppCompatActivity parent) {
        this.parent = parent;

        intent = new Intent(this.parent, AllStationsActivity.class);


    }

    @Override
    public void onClick(View v) {
        launchActivity();

        return;
    }

    private void launchActivity() {
        parent.startActivity(intent);
    }
}
