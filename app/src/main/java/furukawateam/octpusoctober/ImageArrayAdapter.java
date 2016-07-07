package furukawateam.octpusoctober;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class ImageArrayAdapter extends ArrayAdapter<Dto>{

    private int resourceId;
    private List<Dto> items;
    private LayoutInflater inflater;

    public ImageArrayAdapter(Context context, int resourceId, List<Dto> items) {
        super(context, resourceId, items);

        this.resourceId = resourceId;
        this.items = items;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        if (convertView != null) {
            view = convertView;


        } else {

            view = inflater.inflate(
                    R.layout.list_item,
                    parent,
                    false);


        }

        Dto item = this.items.get(position);

        // たこ焼き名をセット
        TextView takoyakiNameText = (TextView)view.findViewById(R.id.item_text);
        takoyakiNameText.setText(item.getTakoyaki_name());

        // 材料名をセット
        TextView materialNameText = (TextView)view.findViewById(R.id.material_text);
        materialNameText.setText(item.getMaterial_name());

        // サムネイルをセット
        ImageView list_thumbnail = (ImageView)view.findViewById(R.id.item_image);
        list_thumbnail.setImageResource(item.getImage_src());



        return view;
    }



}




