package com.medicalLink.muslim.mldoctorapp;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by muslim on 8/21/2017.
 */

public class viewPager_adapter extends PagerAdapter {

    private int[] imageResorces={R.drawable.slider_bg1,R.drawable.slider_bg5,R.drawable.slider_bg4};
    private Context ctx;
    private LayoutInflater layoutInflater;

    public viewPager_adapter(Context ctx){

        this.ctx=ctx;

    }
    @Override
    public int getCount() {
        return imageResorces.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return( view==(RelativeLayout)o);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view=layoutInflater.inflate(R.layout.swip_view_layout,container,false);
        ImageView imageView=(ImageView) item_view.findViewById(R.id.imageView18);
        imageView.setImageResource(imageResorces[position]);
        container.addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);

    }
}
