package com.bawei.lenovo.pengbo20181221.until;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.bawei.lenovo.pengbo20181221.R;

public class Seach extends RelativeLayout {
    public Seach(Context context) {
        super(context);
        init(context);
    }

    public Seach(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
        private Context con;
    private EditText seachEdit;
    private void init(Context context) {
        this.con=context;
        View view=View.inflate(context, R.layout.seach,null);
        seachEdit=view.findViewById(R.id.searchEdit);
        addView(view);
    }
}
