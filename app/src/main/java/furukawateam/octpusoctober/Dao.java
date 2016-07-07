package furukawateam.octpusoctober;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Dao {

    private static final String TAKOYAKI_TBL = "takoyaki_tbl";
    private static final String COLUMN_ID = "takoyaki_id";
    private static final String COLUMN_TAKOYAKI_NAME = "takoyaki_name";
    private static final String COLUMN_COMMENT = "comment";
    private static final String COLUMN_IMAGE_NAME = "image_name";
    private static final String COLUMN_REGION = "region";

    private static final String MATERIAL_TBL = "material_tbl";
    private static final String MATERIAL_NAME = "material_name";
    private static final String MATERIAL_AMOUNT = "material_amount";

    private static final String HOW_TO_MAKE_TBL = "how_to_make_tbl";
    private static final String PROCESS_NUMBER = "process_number";
    private static final String PROCESS_TEXT = "process_text";

    private static final String[] TAKOYAKI_COLUMNS = {COLUMN_ID, COLUMN_TAKOYAKI_NAME,
                COLUMN_COMMENT,COLUMN_IMAGE_NAME,COLUMN_REGION
    };

    private static final String[] MATERIAL_COLUMNS = {COLUMN_ID,MATERIAL_NAME,MATERIAL_AMOUNT};

    private static final String[] HOW_TO_MAKE_COLUMNS = {COLUMN_ID,PROCESS_NUMBER,PROCESS_TEXT};




    private SQLiteDatabase db;

    //コンストラクタ
    public Dao(SQLiteDatabase db){
        this.db = db;
    }


    //たこやきテーブルから全データの取得しリストで返す
    public List<Dto> findAll() {
        List<Dto> dtoList = new ArrayList<Dto>();
        Cursor cursor = db.query(
                TAKOYAKI_TBL,
                TAKOYAKI_COLUMNS,
                null,
                null,
                null,
                null,
                COLUMN_ID);

        while (cursor.moveToNext()) {
            Dto dto = new Dto();
            dto.setTakoyaki_id(cursor.getInt(0));
            dto.setTakoyaki_name(cursor.getString(1));
            dto.setComment(cursor.getString(2));
            dto.setImage_name(cursor.getString(3));
            dto.setRegion(cursor.getString(4));
            dtoList.add(dto);
        }

        return dtoList;
    }

    //たこやきテーブルから引数の地域のデータを取得しdtoを返す
    public Dto findByIdTakoyaki(int rowId) {

        Dto dto = new Dto();
        String selection = COLUMN_ID + "=" + "'" + rowId +"'";
        Cursor cursor = db.query(
                TAKOYAKI_TBL,
                TAKOYAKI_COLUMNS,
                selection,
                null,
                null,
                null,
                null);

            cursor.moveToNext();
            dto.setTakoyaki_id(cursor.getInt(0));
            dto.setTakoyaki_name(cursor.getString(1));
            dto.setComment(cursor.getString(2));
            dto.setImage_name(cursor.getString(3));
            dto.setRegion(cursor.getString(4));

        return dto;
    }


    //たこやきテーブルから引数の地域のデータを取得しdtoを返す
    public List<Dto> findByRegion(String rowRegion) {

        List<Dto> dtoList = new ArrayList<Dto>();
        String selection = COLUMN_REGION + "=" + "'" + rowRegion +"'";
        Cursor cursor = db.query(
                TAKOYAKI_TBL,
                TAKOYAKI_COLUMNS,
                selection,
                null,
                null,
                null,
                null);

        while (cursor.moveToNext()) {
            Dto dto = new Dto();
            dto.setTakoyaki_id(cursor.getInt(0));
            dto.setTakoyaki_name(cursor.getString(1));
            dto.setComment(cursor.getString(2));
            dto.setImage_name(cursor.getString(3));
            dto.setRegion(cursor.getString(4));
            dtoList.add(dto);
        }

        return dtoList;
    }




    //材料テーブルから引数のIDのデータを取得しリストで返す
    public List<Dto> findByIdMaterial(int rowId) {

        List<Dto> dtoList = new ArrayList<Dto>();
        String selection = COLUMN_ID + "=" + "'" + rowId +"'";
        Cursor cursor = db.query(
                MATERIAL_TBL,
                MATERIAL_COLUMNS,
                selection,
                null,
                null,
                null,
                null);

        while (cursor.moveToNext()) {
            Dto dto = new Dto();
            dto.setTakoyaki_id(cursor.getInt(0));
            dto.setMaterial_name(cursor.getString(1));
            dto.setMaterial_amount(cursor.getString(2));
            dtoList.add(dto);
        }
        return dtoList;
    }


    //材料テーブルから引数のIDの材料名を取得しリストで返す
    public List<String> findMatetialById(int id) {
        List<String> dtoList = new ArrayList<String>();

        String selection = COLUMN_ID + "=" + id;
        Cursor cursor = db.query(
                MATERIAL_TBL,
                MATERIAL_COLUMNS,
                selection,
                null,
                null,
                null,
                null);

        while (cursor.moveToNext()) {
            Dto dto = new Dto();
            dto.setMaterial_name(cursor.getString(1));
            dtoList.add(dto.getMaterial_name());
        }

        return dtoList;
    }



    //作り方テーブルから引数のIDのデータを取得しリストで返す
    public List<Dto> findByIdHowToMake(int rowId) {

        List<Dto> dtoList = new ArrayList<Dto>();
        String selection = COLUMN_ID + "=" + "'" + rowId +"'";
        Cursor cursor = db.query(
                HOW_TO_MAKE_TBL,
                HOW_TO_MAKE_COLUMNS,
                selection,
                null,
                null,
                null,
                null);

        while (cursor.moveToNext()) {
            Dto dto = new Dto();
            dto.setTakoyaki_id(cursor.getInt(0));
            dto.setProcess_number(cursor.getString(1));
            dto.setProcess_text(cursor.getString(2));
            dtoList.add(dto);
        }
        return dtoList;
    }


}
