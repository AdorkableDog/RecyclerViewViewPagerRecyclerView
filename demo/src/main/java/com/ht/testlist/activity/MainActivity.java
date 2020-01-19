package com.ht.testlist.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ht.testlist.JavaFiles.MultiTypeAdapter;
import com.ht.testlist.JavaFiles.ParentRecyclerView;
import com.ht.testlist.kotlinFiles.CategoryBean;
import com.ht.testlist.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import kotlin.collections.ArraysKt;

/**
 * Created by song on 2018/8/22 0022
 * My email : logisong@163.com
 * The role of this :
 */
public class MainActivity extends AppCompatActivity {

	private static final String TAG = "MainActivity";
	private List<String> data = new ArrayList<>();

	String[] title = new String[]{"关注", "推荐", "视频", "直播", "图片", "段子", "精华", "热门"};

//	private List<String> mDataList = new ArrayList<>();

	private final ArrayList mDataList = new ArrayList();

	public ParentRecyclerView parentRecyclerView;


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		data.add("精选");
		data.add("医生");
		data.add("医院");

		parentRecyclerView = findViewById(R.id.rv);
		parentRecyclerView.initLayoutManager();

		for (int i = 0; i < title.length; i++) {
			mDataList.add("parent item text" + i);
		}


		CategoryBean categoryBean = new CategoryBean();
		categoryBean.getTabTitleList().clear();
		categoryBean.getTabTitleList().addAll((Collection) ArraysKt.asList((Object[]) this.title));
		mDataList.add(categoryBean);

		MultiTypeAdapter multiTypeAdapter = new MultiTypeAdapter(mDataList);
		parentRecyclerView.setAdapter(multiTypeAdapter);


      /*  //状态栏高度
        int statusBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            statusBarHeight =getResources().getDimensionPixelSize(resourceId);
        }
        //屏幕高度
        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();*/

//
//        for (int i = 0; i <data.size() ; i++) {
//            fragments.add(PagerFragment.newInstance(data.get(i))) ;
//        }
//   final float scale = dm.density;
//        int i = (int) (54 * scale + 0.5f);
//
//       fragments.get(0);
//        mainAdapter = new MainAdapter(this,getSupportFragmentManager(),data, fragments);
//        rv.setAdapter(mainAdapter);
////        mainAdapter.setPagerChangeListener(this);
//        mainAdapter.setOnItemClickListener(new PagerListAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View v, int position) {
//                Toast.makeText(getApplicationContext(),"外部部RV  "+position,Toast.LENGTH_LONG).show();
//            }
//        });
//        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//            }
//        });


	}

/*    public void adjustIntercept(boolean b){

    }

    @Override
    public void pagerChange(int position) {
        Log.i(TAG, "pagerChange: "+ position);
//        currentFragment=fragments.get(position);
    }
    */


}
