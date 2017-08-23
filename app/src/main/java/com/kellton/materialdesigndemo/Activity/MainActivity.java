package com.kellton.materialdesigndemo.Activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kellton.materialdesigndemo.Adapters.RecyclerViewAdapter;
import com.kellton.materialdesigndemo.R;


/**
 * <h1><font color="orange">MainActivity</font></h1>
 * Activity class implementing navigation views,drawers,fabs,snackbars and ripple.
 *
 * @author Divya Khanduri
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnTouchListener {
    private DrawerLayout drawerLayout;
    private int[] myDataset = {R.drawable.md1, R.drawable.md2, R.drawable.md3, R.drawable.md4, R.drawable.md5, R.drawable.md6, R.drawable.md7};
    private String TAG = MainActivity.class.getSimpleName();
    private NavigationView navigationView;
    private RippleDrawable rippleDrawable;
    private CoordinatorLayout mCoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpToolbar();
        initView();
        addRipple();

    }

    /**
     * Initializing Views
     */
    private void initView() {

        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.containerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        RecyclerView.Adapter adapter = new RecyclerViewAdapter(myDataset);
        recyclerView.setAdapter(adapter);

    }

    /**
     *    Initializing toolbar
     */
    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_navigation);
            actionBar.setTitle("MATERIAL DESIGN LIBRARY");
            actionBar.setSubtitle("Android");
        }

    }

    /*
     *Shows a ripple effect on Touch
     */
    public void addRipple() {

        View header = navigationView.getHeaderView(0);
        ImageView ivNav = header.findViewById(R.id.ic_nav);
        rippleDrawable = (RippleDrawable) ivNav.getBackground();
        ivNav.setOnTouchListener(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);

            default:
                Log.e(TAG, getString(R.string.wrong_case_selection));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else {
            finish();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        // Handle navigation view item clicks here.
        switch (item.getItemId()) {

            case R.id.home: {
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            }
            case R.id.snackbar: {
                showCustomSnackbar();
                break;
            }
            case R.id.view: {
                break;
            }
            case R.id.tabs: {
                startActivity(new Intent(MainActivity.this, TabActivity.class));
                break;
            }
            default:
                Log.e(TAG, getString(R.string.wrong_case_selection));
                break;
        }
        //close navigation drawer
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /*
     *    Shows a Custom Snackbar
     */

    private void showCustomSnackbar() {
        // Create the Snackbar
        Snackbar snackbar = Snackbar.make(mCoordinatorLayout, "", Snackbar.LENGTH_LONG);
        // Get the Snackbar's layout view
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        // Hide the text
        TextView textView = layout.findViewById(android.support.design.R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);
        // Inflate our custom view
        View snackView = View.inflate(this, R.layout.my_custom_snackbar, null);
        // Configure the view
        ImageView imageView = snackView.findViewById(R.id.iv_background);
        Bitmap image = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        imageView.setImageBitmap(image);
        TextView textViewTop = snackView.findViewById(R.id.tv_snackbar);
        textViewTop.setText(getString(R.string.have_a_bite));
        textViewTop.setTextColor(Color.CYAN);

        // Add the view to the Snackbar's layout
        layout.addView(snackView, 0);
        // Show the Snackbar
        snackbar.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()) {
            case R.id.ic_nav:
                rippleDrawable.setHotspot(motionEvent.getX(), motionEvent.getY());
                rippleDrawable.setColor(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorShadeBlue)));
                break;
        }
        return false;
    }
}
