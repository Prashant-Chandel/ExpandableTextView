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



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                mLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        RvAdapter mAdapter = new RvAdapter(this);
        recyclerView.setAdapter(mAdapter);


//        TextView tv = (TextView)findViewById(R.id.tv);
//        tv.setText(getString(R.string.dummy_text));
//
//        ReadMoreOption readMoreOption = new ReadMoreOption.Builder(this)
//              // Optional parameters
//                .setTextLength(3, ReadMoreOption.TYPE_LINE) //OR
//              //.setTextLength(300, ReadMoreOption.TYPE_CHARACTER)
//                .setMoreLabel("MORE")
//                .setLessLabel("LESS")
//                .setMoreLabelColor(Color.RED)
//                .setLessLabelColor(Color.BLUE)
//                .setLabelUnderLine(true)
//                .setExpandAnimation(true)
//                .build();
//        readMoreOption.addReadMoreTo(tv, getString(R.string.dummy_text));

    }
}