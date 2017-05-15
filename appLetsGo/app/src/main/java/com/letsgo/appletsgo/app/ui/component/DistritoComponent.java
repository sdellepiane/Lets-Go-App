package com.letsgo.appletsgo.app.ui.component;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.letsgo.appletsgo.R;
import com.letsgo.appletsgo.app.utils.LogUtils;
import com.letsgo.appletsgo.app.utils.ScreenUtils;
import com.letsgo.appletsgo.data.store.SessionUser;
import com.letsgo.appletsgo.domain.model.entity.DateGroup;
import com.letsgo.appletsgo.domain.model.entity.Distrito;
import com.letsgo.appletsgo.domain.model.entity.DistritosSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by louislopez on 7/03/17.
 */

public class DistritoComponent extends LinearLayout {
    private List<Distrito> distritoList;
    private List<View> childrens;
    private Context context;
    private boolean allDistrito;
    private ImageView allDistritoCheck;


    public DistritoComponent(Context context) {
        super(context);
    }

    public DistritoComponent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DistritoComponent(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(List<Distrito> distritoList, Context context, boolean allDistrito, ImageView allDistritoCheck){
        this.distritoList = distritoList;
        this.context = context;
        this.allDistrito = allDistrito;
        this.allDistritoCheck = allDistritoCheck;
        populate();
    }

    private void populate(){
        int bottom= ScreenUtils.dpToPx(10);
        childrens= new ArrayList<>();
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(90, (FrameLayout.LayoutParams.WRAP_CONTENT));
        lp.setMargins(0, 0, 0, bottom);
        View child;
        Distrito distrito;
        for (int i = 0 ; i < this.distritoList.size() ; i++){
            distrito = this.distritoList.get(i);
            child= buildCombosChild(distrito, i);
            //child.setLayoutParams(lp);
            this.addView(child);
            childrens.add(child);
        }
        this.requestLayout();
    }

    private View buildCombosChild(final Distrito distrito, final int position){
        View item = LayoutInflater.from(getContext()).inflate(R.layout.layout_distrito,null);
        item.setTag(distrito);

        final ViewHolder holder = new ViewHolder(item);
        holder.tviDistrito.setText(distrito.getDescription());

        if (allDistrito != true) {
            List<Distrito> Mydistrito = SessionUser.getDistrosUser(context).getDistritoList();
            for (int i = 0 ; i < Mydistrito.size(); i++){
                if (distrito.getId_ubigeos().equals(Mydistrito.get(i).getId_ubigeos())){
                    if (Mydistrito.get(position).isCheck()){
                        holder.iviCheck.setVisibility(View.VISIBLE);
                    }else{
                        holder.iviCheck.setVisibility(View.GONE);
                    }
                }

            }
        }else{
            List<Distrito> Mydistrito = new ArrayList<>();
            DistritosSession distritosSession = new DistritosSession();
            distritosSession.setDistritoList(Mydistrito);
            SessionUser.saveDistrosUser(context, distritosSession);
        }



        holder.rlaRowDistrito.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                allDistritoCheck.setVisibility(GONE);
                LogUtils.v("SESION", "OPTION SELECCIONADOooo : " +  distritoList.get(position).getDescription());
                if (distritoList.get(position).isCheck()){
                    distritoList.get(position).setCheck(false);
                    holder.iviCheck.setVisibility(View.GONE);

                    DistritosSession saveFilterUpdateSession = new DistritosSession();
                    saveFilterUpdateSession.setDistritoList(distritoList);
                    SessionUser.saveDistrosUser(context, saveFilterUpdateSession);
                }else{
                    distritoList.get(position).setCheck(true);
                    holder.iviCheck.setVisibility(View.VISIBLE);

                    DistritosSession saveFilterUpdateSession = new DistritosSession();
                    saveFilterUpdateSession.setDistritoList(distritoList);
                    SessionUser.saveDistrosUser(context, saveFilterUpdateSession);
                }

                LogUtils.v("SESION DISTRITO", SessionUser.getDistrosUser(context).toString());


            }
        });




        return item;
    }
    private class ViewHolder {
        public RelativeLayout rlaRowDistrito;
        public TextView tviDistrito;
        public ImageView iviCheck;

        public ViewHolder(View container) {
            this.tviDistrito = (TextView)container.findViewById(R.id.tviDistrito);
            this.iviCheck = (ImageView)container.findViewById(R.id.iviCheck);
            this.rlaRowDistrito = (RelativeLayout)container.findViewById(R.id.rlaRowDistrito);
        }
    }
}
