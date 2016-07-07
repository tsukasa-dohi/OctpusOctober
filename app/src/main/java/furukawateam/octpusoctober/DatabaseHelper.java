package furukawateam.octpusoctober;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {




    private static final String DB_NAME = "octopusOctober.db";
    private static final int DB_VERSION = 1;

    //たこやきテーブルのクリエイト文
    private static final String CREATE_TAKOYAKI =
            "create table takoyaki_tbl("
                    +"takoyaki_id integer primary key,"
                    +"takoyaki_name text not null,"
                    +"comment text not null,"
                    +"image_name text not null,"
                    +"region text not null" +
                    ");";

    //材料テーブルのクリエイト文
    private static final String CREATE_MATERIAL =
            "create table material_tbl("
                    +"takoyaki_id integer,"
                    +"material_name text,"
                    +"material_amount text not null,"
                    +"primary key(takoyaki_id,material_name),"
                    +"foreign key(takoyaki_id) references takoyaki_tbl(takoyaki_id)" +
                    ");";

    //作り方テーブルのクリエイト文
    private static final String CREATE_HOW_TO_MAKE =
            "create table how_to_make_tbl("
                    +"takoyaki_id integer,"
                    +"process_number text,"
                    +"process_text text not null,"
                    +"primary key(takoyaki_id,process_number),"
                    +"foreign key(takoyaki_id) references takoyaki_tbl(takoyaki_id)" +
                    ");";

    //たこ焼きテーブルへのインサート文
    private  static  final  String INSERT_TAKOYAKI =
            "insert into takoyaki_tbl(takoyaki_id,takoyaki_name,comment,image_name,region)" +
                    "values('1','神戸たこ焼き','兵庫区、長田区の下町を中心に食べられていた「たこ焼き」(の食べ方)です。\n" +
                    "たこ焼き(大阪のたこ焼きと違い具材は「たこ」のみのプレーンなものが基本)を深めの器に入れそこに鰹、昆布などをベースにしたお出汁をかけて、お好みによりソースをかけて頂きます。','kobe','kinki')," +

                    "('2','明石焼き','明石の味が、家で作れる喜び。','akashi','kinki')," +

                    "('3','ラジオ焼き','具はちくわとチーズだけ！だしのきいたたこ焼粉とちくわが好相性♪おやつにもなる超カンタン激ウマレシピです。','radio','kinki')," +

                    "('4','魚肉ボール焼','おいしい','ball','kinki')," +

                    "('5','近江牛たま','おいしい','oumi','kinki')," +

                    "('6','ばくだん焼き','おいしい','bakudan','kanto')," +

                    "('11','ねぎだこ','おいしい','negi','kanto')," +
                    "('12','ねぎだこ','おいしい','negi','kanto')," +
                    "('13','ねぎだこ','おいしい','negi','kanto')," +
                    "('14','ねぎだこ','おいしい','negi','kanto')," +
                    "('15','ねぎだこ','おいしい','negi','kanto')," +
                    "('16','ねぎだこ','おいしい','negi','kanto')," +
                    "('17','ねぎだこ','おいしい','negi','kanto')," +
                    "('18','ねぎだこ','おいしい','negi','kanto')," +
                    "('19','ねぎだこ','おいしい','negi','kanto')," +
                    "('20','ねぎだこ','おいしい','negi','kanto')," +
                    "('21','ねぎだこ','おいしい','negi','kanto')," +
                    "('22','ねぎだこ','おいしい','negi','kanto')," +
                    "('23','ねぎだこ','おいしい','negi','kanto')," +
                    "('24','ねぎだこ','おいしい','negi','kanto')," +
                    "('25','ねぎだこ','おいしい','negi','kanto')," +
                    "('26','ねぎだこ','おいしい','negi','kanto')," +
                    "('27','ねぎだこ','おいしい','negi','kanto')," +
                    "('28','ねぎだこ','おいしい','negi','kanto')," +
                    "('29','ねぎだこ','おいしい','negi','kanto')," +
                    "('30','ねぎだこ','おいしい','negi','kanto')," +
                    "('31','ねぎだこ','おいしい','negi','kanto')," +
                    "('32','ねぎだこ','おいしい','negi','kanto')," +
                    "('34','ねぎだこ','おいしい','negi','kanto')," +
                    "('35','ねぎだこ','おいしい','negi','kanto')," +
                    "('36','ねぎだこ','おいしい','negi','kanto')," +
                    "('37','ねぎだこ','おいしい','negi','kanto')," +
                    "('38','ねぎだこ','おいしい','negi','kanto')," +
                    "('39','ねぎだこ','おいしい','negi','kanto')," +
                    "('40','ねぎだこ','おいしい','negi','kanto')," +
                    "('41','ねぎだこ','おいしい','negi','kanto')," +
                    "('42','ねぎだこ','おいしい','negi','kanto')," +
                    "('43','ねぎだこ','おいしい','negi','kanto')," +
                    "('44','ねぎだこ','おいしい','negi','kanto')," +
                    "('45','ねぎだこ','おいしい','negi','kanto')," +
                    "('111','ねぎだこ','おいしい','negi','kanto')," +
                    "('1112','ねぎだこ','おいしい','negi','kanto')," +
                    "('113','ねぎだこ','おいしい','negi','kanto')," +
                    "('123','ねぎだこ','おいしい','negi','kanto')," +
                    "('112','ねぎだこ','おいしい','negi','kanto')," +
                    "('555','ねぎだこ','おいしい','negi','kanto')," +
                    "('666','ねぎだこ','おいしい','negi','kanto')," +
                    "('222','ねぎだこ','おいしい','negi','kanto')," +
                    "('333','ねぎだこ','おいしい','negi','kanto')," +
                    "('321','ねぎだこ','おいしい','negi','kanto')," +
                    "('166','ねぎだこ','おいしい','negi','kanto')," +
                    "('344','ねぎだこ','おいしい','negi','kanto')," +
                    "('77','ねぎだこ','おいしい','negi','kanto')," +
                    "('655','ねぎだこ','おいしい','negi','kanto')," +
                    "('466','ねぎだこ','おいしい','negi','kanto')," +
                    "('700','ねぎだこ','おいしい','negi','kanto')," +
                    "('8','ちょこ焼き','おいしい','tyoko','kyusyu')" +
                    ";";



    //材料テーブルへのインサート文
    private  static  final  String INSERT_MATERIAL =
            "insert into material_tbl(takoyaki_id,material_name,material_amount)" +
                    "values('1','日清 たこ焼粉','150g')," +
                    "('1','卵','１個')," +
                    "('1','水','450cc')," +
                    "('1','たこ(1.5cm角)','80g')," +
                    "('1','天かす','適量')," +
                    "('1','紅しょうが','お好み')," +
                    "('1','とんかつソースなど辛口の中濃ソース','適量')," +
                    "('1','水(だし用)','500cc')," +
                    "('1','こんぶ','約5cm')," +
                    "('1','花かつお','ひとつかみ')," +
                    "('1','塩','小さじ1/2')," +
                    "('1','しょうゆ','小さじ1')," +

                    "('2','A たこ焼き粉','50g')," +
                    "('2','A 水','1・1/2カップ')," +
                    "('2','A 塩','少々')," +
                    "('2','卵','3個')," +
                    "('2','ゆでだこ（1.5cm角）','50g')," +
                    "('2','ほんだし','小さじ2/3')," +
                    "('2','B 水','1・1/4カップ')," +
                    "('2','B しょうゆ','小さじ1')," +
                    "('2','B 塩','適量')," +
                    "('2','万能ねぎの小口切り','適量')," +
                    "('2','サラダ油','適量')," +

                    "('3','たこ焼き粉','100g')," +
                    "('3','卵','1個')," +
                    "('3','水','300ml')," +
                    "('3','しょうゆ','約小さじ1')," +
                    "('3','ちくわ','30g')," +
                    "('3','サラダ油','適量')," +
                    "('3','ピザ用チーズ','お好みで')," +
                    "('3','わさび・しょうゆ','お好みで')" +

                    ";";

    //作り方テーブルへのインサート文
    private  static  final  String INSERT_HOW_TO_MAKE =
            "insert into how_to_make_tbl(takoyaki_id,process_number,process_text)" +
                    "values('1','1','だしの作り方\n" +
                    "大きめの鍋に水を入れ、昆布を入れて1時間以上おきます。')," +
                    "('1','2','1の鍋を火にかけ強火で沸騰させないように注意して、鍋肌に気泡ができて沸騰してきたら昆布をとりだし、一度沸騰させます。')," +
                    "('1','3','火を止め、花かつおを入れ、沈んで落ちついたらザルでこします。塩としょうゆで味を整えます。')," +
                    "('1','4','小鉢に焼きあがったたこ焼きを並べ、ソースを塗ってから、たこ焼きが浸るくらいにだしを静かにそそいで、できあがり。\n" +
                    "※たこ以外の具材でたこ焼きを焼いて、だしの変わりにいろんなスープを試してもおいしくいただけます。')," +

                    "('2','1','ボウルにＡを入れ、泡立器でよく混ぜ合わせ、卵を加えて生地を作る。焼き型はあらかじめ熱しておく。')," +
                    "('2','2','鍋に「ほんだし」、Ｂを入れ、ひと煮立ちさせ、だし汁を作る。')," +
                    "('2','3','手順１の焼き型に油を薄くひき、手順１の生地を焼き型の穴の八分目まで流し込み、ゆでだこを入れる。さらに上から生地をフチいっぱいまでかけ、弱火でゆっくりと焼く。')," +
                    "('2','4','フチが浮いたような感じになったら裏返して焼き上げる。器に盛り、万能ねぎを散らした手順２のだし汁につけて食べる。')" +
                    ";";



        public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //各テーブルのクリエイトとインサートを実行
        db.execSQL(CREATE_TAKOYAKI);
        db.execSQL(CREATE_MATERIAL);
        db.execSQL(CREATE_HOW_TO_MAKE);
        db.execSQL(INSERT_TAKOYAKI);
        db.execSQL(INSERT_MATERIAL);
        db.execSQL(INSERT_HOW_TO_MAKE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
