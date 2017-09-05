package com.example.isuyo_000.activities.UserData;

import android.content.Context;
import android.support.annotation.IntegerRes;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.isuyo_000.activities.R;

import java.util.List;

/**
 * Created by McLovin on 9/5/2017.
 */

public class UserAdapter  extends ArrayAdapter<User> implements View.OnClickListener {

    private List<User> data;
    private Context context;

    public UserAdapter(List<User> data, Context context){
        super(context, R.layout.line_basic, data);
        this.data = data;
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        int position = (Integer) view.getTag();
        User user = (User) getItem(position);
        switch (view.getId()){
            case R.id.userID:
                Snackbar.make(view, "User: " + user.getID(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }
    }

    private int lastPositoin = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        User user = getItem(position);

        View result = convertView;

        if(convertView == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            result = vi.inflate(R.layout.line_basic, null);
        }

        if(user != null){
            TextView id = (TextView) result.findViewById(R.id.userID);
            TextView data = (TextView) result.findViewById(R.id.userInfo);
            if(id != null){
                id.setText(""+user.getID());
            }
            if(data != null) {
                data.setText(user.getLimits().toString());
            }
        }
        return result;
    }
}
