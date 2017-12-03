package com.anselmo.isscodingchallenge.ui;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.anselmo.isscodingchallenge.R;
import com.anselmo.isscodingchallenge.adapters.PassTimeAdapter;
import com.anselmo.isscodingchallenge.models.ISSResponse;
import com.anselmo.isscodingchallenge.ui.base.BaseActivity;

public class DetailActivity extends BaseActivity {
    private Toolbar toolbar;
    private RecyclerView recycler;
    private ISSResponse model;
    private PassTimeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_detail);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            model = extras.getParcelable("model");
            mAdapter = new PassTimeAdapter(this, model);
        }

        toolbar = getToolbar();
        toolbar.setNavigationIcon(R.mipmap.ic_up);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recycler = (RecyclerView) findViewById(R.id.genericRecycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recycler.setHasFixedSize(true);
        recycler.setItemViewCacheSize(20);
        recycler.setDrawingCacheEnabled(true);
        recycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recycler.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}