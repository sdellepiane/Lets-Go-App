package com.letsgo.appletsgo.app.utils;

import android.view.View;

/**
 * Created by louislopez on 13/02/17.
 */

public interface RecyclerClickListener {
    public void onClick(View view, int position);

    public void onLongClick(View view, int position);
}
