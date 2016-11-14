package com.example.hzkto.patternrecognition;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.gcacace.signaturepad.views.SignaturePad;

public class MainActivity extends AppCompatActivity {

    private SignaturePad sv;
    private Toast t;
    public static Bitmap bmp;
    Button btnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Распознавание букв Z,L,Y");
        sv = (SignaturePad) findViewById(R.id.s_view);
        sv.setPenColorRes(R.color.colorPrimary);
        sv.setMinWidth(5);
        sv.setMinWidth(7);
        t = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        t.setGravity(0, 0, 135);
        btnInfo = (Button) findViewById(R.id.btn_info);
    }


    public void onClickBtnInfo(View view) {
        bmp = sv.getSignatureBitmap();
        startActivity(new Intent(this, InfoActivity.class));
    }

    public void onClickBtnOk(View view) {
        String str = Helper.Recognize(sv);
        t.setText(str);

        if (!str.equals("Нарисуйте букву")) {
            btnInfo.setBackgroundResource(R.drawable.rect_adapter_button_active);
            btnInfo.setEnabled(true);
        } else {
            btnInfo.setBackgroundResource(R.drawable.rect_adapter_button_inactive);
            btnInfo.setEnabled(false);
        }
        t.show();
    }

    public void onClickBtnReset(View view) {
        sv.clear();
        btnInfo.setBackgroundResource(R.drawable.rect_adapter_button_inactive);
        btnInfo.setEnabled(false);
    }

}
