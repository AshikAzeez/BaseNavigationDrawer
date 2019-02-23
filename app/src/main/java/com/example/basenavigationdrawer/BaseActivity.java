package com.example.basenavigationdrawer;import android.app.Activity;import android.app.ActivityManager;import android.content.Intent;import android.content.res.Configuration;import android.os.Bundle;import android.os.Handler;import android.view.View;import android.widget.ImageView;import android.widget.Toast;import java.util.ArrayList;import java.util.Arrays;import java.util.List;import androidx.annotation.NonNull;import androidx.annotation.Nullable;import androidx.appcompat.app.ActionBar;import androidx.appcompat.app.ActionBarDrawerToggle;import androidx.appcompat.app.AppCompatActivity;import androidx.appcompat.widget.Toolbar;import androidx.core.view.GravityCompat;import androidx.drawerlayout.widget.DrawerLayout;import androidx.recyclerview.widget.LinearLayoutManager;import androidx.recyclerview.widget.RecyclerView;/** * @author Ashik * Ashik K A * ashikazeez45@gmail.com * 20-02-2019 6:12 PM */@SuppressWarnings("ALL")public abstract class BaseActivity extends AppCompatActivity implements NavDrawerAdapter.DrawerItemClickListner {    //-------------This 4 views as common in All Activity-----------------------------    private ImageView icHamBurgerClose;    private DrawerLayout drawerLayout;    RecyclerView recyclerView;    private Toolbar toolbar;    //------------------------------------------------    private ActionBarDrawerToggle drawerToggle;    private int position = 0;    private boolean isExit = false;    private List<String> item = new ArrayList<>(Arrays.asList("Activity1", "Activity2", "Activity3", "Activity4"));    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(getLayoutRes());        initView();        setUpToolBar();        recyclerView.setLayoutManager(new LinearLayoutManager(this));        recyclerView.setAdapter(new NavDrawerAdapter(this, item, this));        icHamBurgerClose.setOnClickListener(v -> drawerLayout.closeDrawer(GravityCompat.START));    }    private void setUpToolBar() {        if (getSupportActionBar() != null) {            getSupportActionBar().setTitle(getTitle());        }    }    private void initView() {        icHamBurgerClose = findViewById(R.id.ic_close_hanberger);        toolbar = findViewById(R.id.toolbar);        setSupportActionBar(toolbar);        final ActionBar actionBar = getSupportActionBar();        if (actionBar != null) {            actionBar.setDisplayHomeAsUpEnabled(true);        }        drawerLayout = findViewById(R.id.drawer_layout);        drawerToggle = new ActionBarDrawerToggle(BaseActivity.this, drawerLayout, toolbar,                                                 0, 0        ) {            public void onDrawerClosed(View view) {                supportInvalidateOptionsMenu();                //drawerOpened = false;            }            public void onDrawerOpened(View drawerView) {                supportInvalidateOptionsMenu();            }        };        drawerToggle.setDrawerIndicatorEnabled(true);        drawerLayout.setDrawerListener(drawerToggle);        drawerToggle.syncState();        recyclerView = findViewById(R.id.left_drawer);    }    private boolean isCurrentView(int res) {        return getLayoutRes() == res;    }    @Override    protected void onPostCreate(@Nullable Bundle savedInstanceState) {        super.onPostCreate(savedInstanceState);        drawerToggle.syncState();    }    @Override    public void onConfigurationChanged(@NonNull Configuration newConfig) {        super.onConfigurationChanged(newConfig);        drawerToggle.onConfigurationChanged(newConfig);    }    @Override    public void onBackPressed() {        List<ActivityManager.RunningTaskInfo> taskList = ((ActivityManager) getSystemService(ACTIVITY_SERVICE)).getRunningTasks(10);        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {            drawerLayout.closeDrawer(GravityCompat.START);        } else if ((taskList.get(0).numActivities == 1) && (!isExit)) {            isExit = true;            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();        } else {            super.onBackPressed();        }    }    @Override    public void onClick(int position) {        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {            drawerLayout.closeDrawer(GravityCompat.START);        }        isExit = false;        new Handler().postDelayed(new Runnable() {            @Override            public void run() {                switch (position) {                    case 0:                        if (!isCurrentView(R.layout.activity_one)) {                            Intent intent = new Intent(BaseActivity.this, ActivityOne.class);                            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);                            startActivity(intent);                        }                        break;                    case 1:                        if (!isCurrentView(R.layout.activity_two)) {                            Intent intent = new Intent(BaseActivity.this, ActivityTwo.class);                            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);                            startActivity(intent);                        }                        break;                    case 2:                        if (!isCurrentView(R.layout.activity_three)) {                            Intent intent = new Intent(BaseActivity.this, ActivityThree.class);                            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);                            startActivity(intent);                        }                        break;                    case 3:                        if (!isCurrentView(R.layout.activity_four)) {                            Intent intent = new Intent(BaseActivity.this, ActivityFour.class);                            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);                            startActivity(intent);                        }                        break;                    default:                        break;                }            }        }, 150);    }    protected abstract int getLayoutRes();    protected abstract Activity getActivityInstance();    protected abstract String setTitle();}