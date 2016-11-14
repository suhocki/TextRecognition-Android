package com.example.hzkto.patternrecognition;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    private Bitmap bmp;
    private ImageView iv;
    private TextView z, l, y, z1, z2, z3, l1, l2, l3, y1, y2, y3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        setTitle("Подробности");
        iv = (ImageView) findViewById(R.id.imageView);
        iv.setImageBitmap(Bitmap.createScaledBitmap(Helper.getPaintedBitmap(), 800, 800, false));

        initTable();
        setTableContent();
        paintLineInTable();
    }

    private void setTableContent() {
        z1.setText(String.valueOf(Helper.getZ1().cross));
        z2.setText(String.valueOf(Helper.getZ2().cross));
        z3.setText(String.valueOf(Helper.getZ3().cross));
        l1.setText(String.valueOf(Helper.getZ1().cross));
        l2.setText(String.valueOf(Helper.getZ2().cross));
        l3.setText(String.valueOf(Helper.getZ3().cross));
        y1.setText(String.valueOf(Helper.getZ1().cross));
        y2.setText(String.valueOf(Helper.getZ2().cross));
        y3.setText(String.valueOf(Helper.getZ3().cross));
    }

    private void paintLineInTable() {
        if (Helper.getZ1().cross > 0 && Helper.getZ2().cross > 0) {
            z.setBackgroundResource(R.drawable.rect_adapter_green);
            z1.setBackgroundResource(R.drawable.rect_adapter_green);
            z2.setBackgroundResource(R.drawable.rect_adapter_green);
            z3.setBackgroundResource(R.drawable.rect_adapter_green);
        }
        if (Helper.getZ1().cross == 0 && Helper.getZ2().cross > 0 && Helper.getZ3().cross > 0) {
            y.setBackgroundResource(R.drawable.rect_adapter_green);
            y1.setBackgroundResource(R.drawable.rect_adapter_green);
            y2.setBackgroundResource(R.drawable.rect_adapter_green);
            y3.setBackgroundResource(R.drawable.rect_adapter_green);
        }
        if (Helper.getZ3().cross == 0) {
            l.setBackgroundResource(R.drawable.rect_adapter_green);
            l1.setBackgroundResource(R.drawable.rect_adapter_green);
            l2.setBackgroundResource(R.drawable.rect_adapter_green);
            l3.setBackgroundResource(R.drawable.rect_adapter_green);
        }

    }

    private void initTable() {
        z = (TextView) findViewById(R.id.tvZ);
        l = (TextView) findViewById(R.id.tvL);
        y = (TextView) findViewById(R.id.tvY);
        z1 = (TextView) findViewById(R.id.tv_z_zond1);
        z2 = (TextView) findViewById(R.id.tv_z_zond2);
        z3 = (TextView) findViewById(R.id.tv_z_zond3);
        l1 = (TextView) findViewById(R.id.tv_l_zond1);
        l2 = (TextView) findViewById(R.id.tv_l_zond2);
        l3 = (TextView) findViewById(R.id.tv_l_zond3);
        y1 = (TextView) findViewById(R.id.tv_y_zond1);
        y2 = (TextView) findViewById(R.id.tv_y_zond2);
        y3 = (TextView) findViewById(R.id.tv_y_zond3);
    }
}
