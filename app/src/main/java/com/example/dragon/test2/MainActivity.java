package com.example.dragon.test2;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Parameter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements ViewPager.OnPageChangeListener,
        TabLayout.OnTabSelectedListener{
    ViewPager viewPager;
    TabLayout tabLayout;
    List<Fragment> listFragment;
    MyFragAdapter myAdapter;
    ArrayList<String> itemArrayLists = new ArrayList<>() ;
    ArrayList<String> mbitemArrayLists = new ArrayList<>();
    ArrayList<String> vgaitemArrayLists = new ArrayList<>();
    ArrayList<String> ramitemArrayLists = new ArrayList<>();
    ArrayList<String> hhdssditemArrayLists = new ArrayList<>();
    ArrayList<String> cpuwitemArrayLists = new ArrayList<>();
    ArrayList<String> vgawitemArrayLists = new ArrayList<>();
    ArrayList<String> poweritemArrayLists = new ArrayList<>();
    ArrayList<String> cputheitemArrayLists = new ArrayList<>();
    ArrayList<String> cpupriceArrayLists = new ArrayList<>();
    ArrayList<String> vgapriceArrayLists = new ArrayList<>();
    ArrayList<String> ssdpriceArrayLists = new ArrayList<>();
    ArrayList returnarraylist(String type){
        if(type=="CPU"){
            return itemArrayLists;
        }
        else if(type =="MB"){
            return  mbitemArrayLists;
        }
        else if(type == "VGA"){
            return  vgaitemArrayLists;
        }
        else if(type == "RAM"){
            return ramitemArrayLists;
        }
        else if(type == "SHD"){
            return hhdssditemArrayLists;
        }
        else if(type == "POWER"){
            return poweritemArrayLists;
        }
        else if(type=="cpuselect"){
            return cputheitemArrayLists;
        }
        else if(type=="powerw"){
            return cpuwitemArrayLists;
        }
        else if(type=="vgaw"){
            return vgawitemArrayLists;
        }
        else if(type=="cpuprice"){
            return cpupriceArrayLists;
        }
        else if(type=="vgaprice"){
            return vgapriceArrayLists;
        }
        else if(type=="ssdpirce"){
            return ssdpriceArrayLists;
        }
        else return  null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        deliver();
        initView();
        tabLayout = (TabLayout) findViewById(R.id.tablay);
        tabLayout.addOnTabSelectedListener(this);
    }

    private void deliver() {
        Bundle bundle = this.getIntent().getExtras();
        itemArrayLists = (ArrayList<String>) bundle.getStringArrayList("cpu");
        mbitemArrayLists = (ArrayList<String>) bundle.getStringArrayList("mb");
        vgaitemArrayLists =  (ArrayList<String>) bundle.getStringArrayList("vga");
        ramitemArrayLists =(ArrayList<String>)bundle.getStringArrayList("ram");
        hhdssditemArrayLists =(ArrayList<String>)bundle.getStringArrayList("shd");
        poweritemArrayLists =(ArrayList<String>)bundle.getStringArrayList("power");
        cputheitemArrayLists = (ArrayList<String>)bundle.getStringArrayList("cputheselect");
        cpuwitemArrayLists = (ArrayList<String>)bundle.getStringArrayList("powerw");
        vgawitemArrayLists = (ArrayList<String>)bundle.getStringArrayList("vgaw");
        cpupriceArrayLists = (ArrayList<String>)bundle.getStringArrayList("cpuprice");
        vgapriceArrayLists= (ArrayList<String>)bundle.getStringArrayList("vgaprice");
        ssdpriceArrayLists= (ArrayList<String>)bundle.getStringArrayList("ssdprice");

    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager1);

        //向ViewPager添加各页面
        listFragment = new ArrayList<>();

        listFragment.add(new Fragmenthomepage());
        listFragment.add(new recommendationfragment());
        listFragment.add(new selectfragment());
        listFragment.add(new brandfragment());
        myAdapter = new MyFragAdapter(getSupportFragmentManager(), this, listFragment);
        viewPager.setAdapter(myAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tabLayout.getTabAt(position).select();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
class MyFragAdapter extends FragmentStatePagerAdapter {
    Context context;
    List<Fragment> listFragment;
    public MyFragAdapter(FragmentManager fm, Context context, List<Fragment> listFragment) {
        super(fm);
        this.context = context;
        this.listFragment = listFragment;
    }
    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }
}
