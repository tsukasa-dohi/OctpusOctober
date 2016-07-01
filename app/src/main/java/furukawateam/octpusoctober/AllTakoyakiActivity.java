package furukawateam.octpusoctober;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class AllTakoyakiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_takoyaki);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //フッターButtonのクリックイベント
        Button btn1 = (Button)findViewById(R.id.include_view_btn).findViewById(R.id.button1);
        Button btn2 = (Button)findViewById(R.id.include_view_btn).findViewById(R.id.button2);
        Button btn3 = (Button)findViewById(R.id.include_view_btn).findViewById(R.id.button3);
        Button btn4 = (Button)findViewById(R.id.include_view_btn).findViewById(R.id.button4);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllTakoyakiActivity.this, TopActivity.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllTakoyakiActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
