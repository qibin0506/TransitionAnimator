package org.loader.transitionanimator;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.list);
        mAdapter = new Adapter();
        mListView.setAdapter(new Adapter());
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                startActivity(view, mAdapter.getItem(position));
            }
        });
    }

    public void startActivity(View view, String content) {
        View statusBar = findViewById(android.R.id.statusBarBackground);
        View navigationBar = findViewById(android.R.id.navigationBarBackground);

        List<Pair<View, String>> pairs = new ArrayList<>();
        pairs.add(Pair.create(statusBar, Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME));
        pairs.add(Pair.create(navigationBar, Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME));
        pairs.add(Pair.create(view, view.getTransitionName()));

        Intent intent = new Intent(this, MessageActivity.class);
        intent.putExtra("msg", content);

        ActivityOptionsCompat compat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this, pairs.toArray(new Pair[pairs.size()]));
        ActivityCompat.startActivity(this, intent, compat.toBundle());
    }

//    public void startActivity(View view, String content) {
//        Intent intent = new Intent(this, MessageActivity.class);
//        intent.putExtra("msg", content);
//
//        ActivityOptionsCompat compat = ActivityOptionsCompat
//                .makeSceneTransitionAnimation(this, view, view.getTransitionName());
//        ActivityCompat.startActivity(this, intent, compat.toBundle());
//    }

    class Adapter extends BaseAdapter {

        private static final String CONTENT = "目前，返回的拟南芥样品一部分已做固定处理，拟南芥果荚将带回实验室继续培养。综合材料实验返回的两批次样品将在实验室进行解剖分析研究，第三批次的6个样品将留轨进行装置热特性测量实验，以期揭示在地面重力环境下难以获知的材料物理和化学过程的规律，获得优质材料的空间制备技术和生产工艺，指导地面材料加工工艺的改进与发展。神舟十一号飞船返回后，天宫二号空间实验室转入独立飞行阶段，空间应用系统将继续按计划开展有效载荷在轨测试以及科学实验与探测，进行科学设备的参数精调，开展地球观测设备的定标和同步观测，同时深入分析研究科学实验与探测数据，开展地球观测数据的应用推广，争取获得更大科学成果，取得更大应用效益。";

        @Override
        public int getCount() {
            return 50;
        }

        @Override
        public String getItem(int position) {
            return CONTENT;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final Holder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
                holder = new Holder(convertView);
                convertView.setTag(holder);
            }else {
                holder = (Holder) convertView.getTag();
            }

            holder.tv.setText(getItem(position));
            return convertView;
        }

        class Holder {
            TextView tv;
            Holder(View view) {
                tv = (TextView) view.findViewById(R.id.item);
            }
        }
    }
}
