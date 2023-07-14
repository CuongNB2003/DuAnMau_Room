package cuongnbph22662.poly.thuvienpoly.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import cuongnbph22662.poly.thuvienpoly.R;

public class ManChaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_chao);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ManChaoActivity.this, LoginActivity.class));
                finish();
            }
        }, 2000);
    }
}