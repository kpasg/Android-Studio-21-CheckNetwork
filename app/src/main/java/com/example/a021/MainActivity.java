package com.example.a021;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void click2(View view){
        ConnectivityManager connectivityManager =
                (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = connectivityManager.getActiveNetworkInfo();

        if (null != network) {

            if (network.getType() == ConnectivityManager.TYPE_MOBILE) {
                Toast.makeText(this, "有網路可用(行動數據)", Toast.LENGTH_SHORT).show();

            } else if (network.getType() == ConnectivityManager.TYPE_WIFI) {
                Toast.makeText(this, "有網路可用(WIFI)", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "無網路可用", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("目前沒有網路");
            builder.setMessage("是否前往設定?");
            builder.setCancelable(true);
            builder.setNeutralButton("不用設定", null);
            builder.setPositiveButton("設定", new DialogInterface.OnClickListener() {
                @Override

                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent();
                    intent.setAction("android.net.wifi.PICK_WIFI_NETWORK");
                    startActivity(intent);
                }
            });
            builder.show();
        }
    }
}