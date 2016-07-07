package furukawateam.octpusoctober;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MapActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.Hokkaido).setOnClickListener(this);
        findViewById(R.id.Hokuriku).setOnClickListener(this);
        findViewById(R.id.Tohoku).setOnClickListener(this);
        findViewById(R.id.Tokai).setOnClickListener(this);
        findViewById(R.id.Tyugoku).setOnClickListener(this);
        findViewById(R.id.Kanto).setOnClickListener(this);
        findViewById(R.id.Kinki).setOnClickListener(this);
        findViewById(R.id.Kyusyu).setOnClickListener(this);
        findViewById(R.id.Shikoku).setOnClickListener(this);

        //フッターButtonのクリックイベント
        Button btn1 = (Button) findViewById(R.id.include_view_btn).findViewById(R.id.button1);
        Button btn2 = (Button)findViewById(R.id.include_view_btn).findViewById(R.id.button2);
        Button btn3 = (Button) findViewById(R.id.include_view_btn).findViewById(R.id.button3);
        Button btn4 = (Button) findViewById(R.id.include_view_btn).findViewById(R.id.button4);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapActivity.this, TopActivity.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapActivity.this, AllTakoyakiActivity.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_top, menu);

        // SearchViewを取得する
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();


        return true;
    }

    //地域別たこ焼き一覧に遷移 regio変数に地域名を保存して渡す
    void intentMap(String region){
        Intent intent = new Intent(this, RegionTakoyakiActivity.class);
        intent.putExtra("region", region);
        startActivity(intent);
    }

    @Override
    public void onClick(View v){

        if (v != null) {
            switch (v.getId()) {
                case R.id.Hokkaido:
                    // クリック処理
                    intentMap("hokkaido");

                    break;

                case R.id.Hokuriku:
                    // クリック処理
                    intentMap("hokuriku");
                    break;

                case R.id.Tohoku:
                    // クリック処理
                    intentMap("tohoku");
                    break;

                case R.id.Tokai:
                    // クリック処理
                    intentMap("tokai");
                    break;

                case R.id.Tyugoku:
                    // クリック処理
                    intentMap("tyugoku");
                    break;

                case R.id.Kanto:
                    // クリック処理
                    intentMap("kanto");
                    break;

                case R.id.Kinki:
                    // クリック処理
                    intentMap("kinki");
                    break;

                case R.id.Kyusyu:
                    // クリック処理
                    intentMap("kyusyu");
                    break;

                case R.id.Shikoku:
                    // クリック処理
                    intentMap("shikoku");
                    break;

                default:
                    break;
            }
        }

    }
}
