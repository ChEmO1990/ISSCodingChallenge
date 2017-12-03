package com.anselmo.isscodingchallenge.ui.base;

import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.anselmo.isscodingchallenge.R;

/**
 * Created by Anselmo on 12/2/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setSupportActionBar(getToolbar());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ( item.getItemId() ) {
            case R.id.action_github:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
                startActivity(browserIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected Toolbar getToolbar() {
        if (mToolbar == null) {
            mToolbar = (Toolbar) findViewById(R.id.iss_toolbar);
            if (mToolbar != null) {
                setSupportActionBar(mToolbar);
            }
        }
        return mToolbar;
    }
}