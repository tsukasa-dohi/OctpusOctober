package furukawateam.octpusoctober;


import android.content.Context;
import android.content.res.AssetManager;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CSVParser {

    private TextView textView;

    private Context context;
    private String file;
    private String txt = "";

    public CSVParser(Context context, String file){
        this.file = file;
        this.context = context;
    }

    public String parseFive() {
        // AssetManagerの呼び出し
        AssetManager assetManager = context.getResources().getAssets();
        try {
            // CSVファイルの読み込み
            InputStream is = assetManager.open(file);
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferReader = new BufferedReader(inputStreamReader);
            String line = "";

            while ((line = bufferReader.readLine()) != null) {

                // 各行が","で区切られていて5つの項目
                StringTokenizer st = new StringTokenizer(line, ",");
                String first = st.nextToken();
                String second = st.nextToken();
                String third = st.nextToken();
                String fourth = st.nextToken();
                String fifth = st.nextToken();

                txt = first + "," + second + "," + third + "," + fourth + "," + fifth;

            }

            bufferReader.close();

        } catch (IOException e) {

        }
        return txt;

    }


    public String parseThree() {
        // AssetManagerの呼び出し
        AssetManager assetManager = context.getResources().getAssets();
        try {
            // CSVファイルの読み込み
            InputStream is = assetManager.open(file);
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferReader = new BufferedReader(inputStreamReader);
            String line = "";


            while ((line = bufferReader.readLine()) != null) {

                // 各行が","で区切られていて5つの項目
                StringTokenizer st = new StringTokenizer(line, ",");
                String first = st.nextToken();
                String second = st.nextToken();
                String third = st.nextToken();

                txt = first + "," + second + "," + third;

            }

            bufferReader.close();

        } catch (IOException e) {

        }

        return txt;
    }
}


