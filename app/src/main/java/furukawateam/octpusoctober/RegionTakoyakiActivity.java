package furukawateam.octpusoctober;


import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class RegionTakoyakiActivity extends Activity {


    private Dao dao;
    private ListView lv;
    private String imageName;
    private String takoyaki_name;
    private int takoyaki_id;
    private String material_name;
    private int rid;
    private ImageView no_data_view;
    private Drawable no_data_img;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        Intent intent = getIntent();
        String region = intent.getStringExtra("region");


        //データベース接続
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();


        //リストの生成
        List<Dto> list = new ArrayList<Dto>();

        //daoのインスタンス生成
        dao = new Dao(db);

        //リストにたこ焼きテーブルの全データを格納
        List<Dto> dtoList = dao.findByRegion(region);


        for (Dto dto : dtoList) {

            takoyaki_id = dto.getTakoyaki_id();
            takoyaki_name = dto.getTakoyaki_name();
            imageName = dto.getImage_name();
            //リソースIDの取得
            rid = getResources().getIdentifier(imageName, "drawable", getPackageName());

            //IDから材料名を取り出しリストに格納
            List<String> dtoMaterialList = dao.findMatetialById(takoyaki_id);
            material_name = "";

            //材料の種類をひとつの文字列として生成
            for (int i = 0; i < dtoMaterialList.size(); ++i) {
                if (i == 0) {
                    material_name = dtoMaterialList.get(i);
                } else {
                    material_name = material_name + " , " + dtoMaterialList.get(i);
                }
            }

            Dto item = new Dto();
            item.setTakoyaki_id(takoyaki_id);
            item.setTakoyaki_name(takoyaki_name);
            item.setImage_src(rid);
            item.setMaterial_name(material_name);
            list.add(item);

        }

        ImageArrayAdapter adapter = new ImageArrayAdapter(this, R.layout.list, list);

        lv = (ListView) findViewById(R.id.listview);
        lv.setAdapter(adapter);


        no_data_img = getResources().getDrawable(R.drawable.no_data);
        no_data_view = (ImageView)findViewById(R.id.no_data_view);
        no_data_view.setImageDrawable(no_data_img);
        lv.setEmptyView(no_data_view);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View view, int pos, long id) {

                // 選択アイテムを取得
                ListView lv = (ListView) parent;
                Dto item = (Dto) lv.getItemAtPosition(pos);
                int takoyaki_id = item.getTakoyaki_id();
                detailIntent(takoyaki_id);

            }
        });


    }

    //たこ焼き詳細に遷移するメソッド takoyaki_id変数にIDを保存して渡す
    void detailIntent(int id){
        Intent intent = new Intent(this, TakoyakiDetailActivity.class);
        intent.putExtra("takoyaki_id", id);
        startActivity(intent);
    }


}