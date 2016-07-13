package furukawateam.octpusoctober;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;


public class TakoyakiDetailActivity extends AppCompatActivity {

    private Dao dao;
    private Dto takoyaki_detail;
    private List<Dto> material_detailList;
    private List<Dto> how_to_make_detailList;
    private int takoyaki_id;
    private int rid;
    private String img_src;
    private String comment;
    private String material_name;
    private String material_amount;
    private String process_number;
    private String process_text;
    private LinearLayout material_lay;
    private LinearLayout material_horizontal;
    private LinearLayout how_to_make_lay;
    private LinearLayout how_to_make_horizontal;
    private int padding;
    private int row_cnt = 1;
    private TextView material_name_view;
    private TextView material_amount_view;
    private TextView border;
    private TextView process_number_view;
    private TextView process_text_view;
    private int matchParent = ViewGroup.LayoutParams.MATCH_PARENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takoyaki_detail);

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
                Intent intent = new Intent(TakoyakiDetailActivity.this, TopActivity.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TakoyakiDetailActivity.this, AllTakoyakiActivity.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TakoyakiDetailActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //広告の表示
        AdView adView = (AdView)this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        //データベース接続
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Intent intent = getIntent();
        takoyaki_id = intent.getIntExtra("takoyaki_id", 0);

        //dao,dto,リストのインスタンス生成
        dao = new Dao(db);
        takoyaki_detail = new Dto();

        //paddin変数に5dを保存
        padding = this.getResources().getDimensionPixelSize(R.dimen.smallPadding);

        //引数のIDのたこやき,材料テーブルの全データをDto型で格納
        takoyaki_detail = dao.findByIdTakoyaki(takoyaki_id);
        material_detailList = dao.findByIdMaterial(takoyaki_id);
        how_to_make_detailList = dao.findByIdHowToMake(takoyaki_id);

        //データベースの各データを取得し変数に保存
        img_src = takoyaki_detail.getImage_name();
        comment = takoyaki_detail.getComment();


        //リソースIDの取得
        rid = getResources().getIdentifier(img_src, "drawable", getPackageName());
        // 画像ををセット
        ImageView detail_img = (ImageView)findViewById(R.id.detailImg);
        detail_img.setImageResource(rid);

        // コメントをセット
        TextView detail_comment = (TextView)findViewById(R.id.comment);
        detail_comment.setText(comment);


        //材料名,材料数量の一のVIEWを生成し表示
        for (Dto dto : material_detailList) {

            material_name = dto.getMaterial_name();
            material_amount = dto.getMaterial_amount();

            //材料名をセット
            material_name_view = new TextView(this);
            material_name_view.setText(material_name);
            material_name_view.setWidth(this.getResources().getDimensionPixelSize(R.dimen.material_name_width));
            material_name_view.setPadding(padding, padding, padding, padding);

            // 材料数量をセット
            material_amount_view = new TextView(this);
            material_amount_view.setText(material_amount);
            material_amount_view.setWidth(this.getResources().getDimensionPixelSize(R.dimen.material_amount_width));
            material_amount_view.setPadding(padding, padding, padding, padding);

            // 親のViewGroupを取得して、Viewを追加
            material_horizontal = new LinearLayout(this);
            material_horizontal.addView(material_name_view);
            material_horizontal.addView(material_amount_view);
            material_horizontal.setPadding(padding, padding, padding, padding);

            //偶数行の背景色をセット
            if (row_cnt % 2==0){
                material_horizontal.setBackgroundColor(Color.rgb(240, 240, 240));
            }

            //材料名,材料数量の組み合わせレイアウトをLinearLayouに追加
            material_lay = (LinearLayout)findViewById(R.id.matetial_lay);
            material_lay.addView(material_horizontal);
            //行数のカウントアップ
            row_cnt++;
        }

        //作り方のVIEWを生成し表示
        for (Dto dto : how_to_make_detailList) {

            process_number = dto.getProcess_number();
            process_text = dto.getProcess_text();
            //ボーダーの生成
            border = new TextView(this);
            border.setBackgroundColor(Color.rgb(226,222,255));
            border.setHeight(this.getResources().getDimensionPixelSize(R.dimen.border));
            border.setWidth(matchParent);

            //材料名をセット
            process_number_view = new TextView(this);
            process_number_view.setText(process_number);
            process_number_view.setWidth(this.getResources().getDimensionPixelSize(R.dimen.process_number));
            process_number_view.setPadding(padding, padding, padding, padding);

            // 材料数量をセット
            process_text_view = new TextView(this);
            process_text_view.setText(process_text);
            process_text_view.setWidth(this.getResources().getDimensionPixelSize(R.dimen.process_text));
            process_text_view.setPadding(padding, padding, padding, padding);

            // 親のViewGroupを取得して、Viewを追加
            how_to_make_horizontal = new LinearLayout(this);
            how_to_make_horizontal.addView(process_number_view);
            how_to_make_horizontal.addView(process_text_view);
            how_to_make_horizontal.setPadding(padding, padding, padding, padding);

            //材料名,材料数量の組み合わせレイアウトをLinearLayouに追加
            how_to_make_lay = (LinearLayout)findViewById(R.id.how_to_make_lay);
            how_to_make_lay.addView(how_to_make_horizontal);
            how_to_make_lay.addView(border);

        }

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

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }


}
