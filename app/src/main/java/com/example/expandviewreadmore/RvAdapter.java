package com.example.expandviewreadmore;

/*
 *  Copyright (c) 2019 pc.
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */



import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import dev.pc.expandabletextview.AddReadMoreOption;


/**
 * Created by PC 2019.
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    private Context context;
    private AddReadMoreOption readMoreOption;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView mTextView;
        ViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.tv);
        }
    }

    RvAdapter(Context context) {
        this.context = context;
        readMoreOption = new AddReadMoreOption.Builder(context)
                .build();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RvAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AddReadMoreOption readMoreOption = new AddReadMoreOption.Builder(context)
                // Optional parameters
//                .setTextLength(3, AddReadMoreOption.TYPE_LINE) //OR
                .setTextLength(300, AddReadMoreOption.TYPE_CHARACTER)
                .setMoreLabel("MORE")
                .setLessLabel("LESS")
                .setMoreLabelColor(Color.RED)
                .setLessLabelColor(Color.BLUE)
                .setLabelUnderLine(true)
                .setExpandAnimation(true)
                .build();
        if (position % 2 == 0) {
            readMoreOption.addReadMoreTo(holder.mTextView, Html.fromHtml(context.getString(R.string.dummy_text)));
        } else {
            readMoreOption.addReadMoreTo(holder.mTextView, Html.fromHtml(context.getString(R.string.dummy_text)).toString());
        }



    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return 20;
    }
}

