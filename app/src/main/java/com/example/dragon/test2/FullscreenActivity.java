package com.example.dragon.test2;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    public ArrayList<String> itemArrayLists =new ArrayList<>();
    public ArrayList<String> cputheitemArrayLists =new ArrayList<>();
    /*mbitemArrayLists vgaitemArrayLists ramitemArrayLists hhdssditemArrayLists poweritemArrayLists*/
    public ArrayList<String> mbitemArrayLists =new ArrayList<>();
    public ArrayList<String> vgaitemArrayLists =new ArrayList<>();
    public ArrayList<String> ramitemArrayLists =new ArrayList<>();
    public ArrayList<String> hhdssditemArrayLists =new ArrayList<>();
    public ArrayList<String> poweritemArrayLists =new ArrayList<>();
    public ArrayList<String> cpuwArrayLists =new ArrayList<>();
    public ArrayList<String> vgawArrayLists =new ArrayList<>();
    public ArrayList<String> cpupriceArrayLists =new ArrayList<>();
    public ArrayList<String> vgapriceArrayLists =new ArrayList<>();
    public ArrayList<String> ssdpriceArrayLists =new ArrayList<>();
    public boolean success = false; // boolean
    public CnMyDbClass cnClass; //Connection Class Variable
    String startA , startB , startC , startD , startE , startF,startG;

    public ArrayList<String> returnarraylist (){
        return  itemArrayLists;
    }

    public void setItemArrayLists(ArrayList<String> itemArrayLists){
        this.itemArrayLists = itemArrayLists;
    }

    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);


        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.


        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
            SyncDataphptest syncDataphptest = new SyncDataphptest(FullscreenActivity.this,
                    itemArrayLists,
                    mbitemArrayLists,
                    ramitemArrayLists,
                    vgaitemArrayLists,
                    hhdssditemArrayLists,
                    poweritemArrayLists,
                    vgawArrayLists,
                    cputheitemArrayLists,
                    cpuwArrayLists,
                    cpupriceArrayLists,
                    vgapriceArrayLists,
                    ssdpriceArrayLists
            );
            syncDataphptest.execute("");
          /* cnClass = new CnMyDbClass(); // Connection Class Initialization//
            // Calling Async Task
           SyncData orderData = new SyncData();
           orderData.execute("");*/
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
    protected  void MainSystem(View v){
        //這東西呢 想像成一台貨車(Intent) 跟司機說你目前所在第跟要去的目的地 將你的行李(Bundle)丟入貨車 跟著司機跑瞜
        Intent it = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("cpu",itemArrayLists);
        bundle.putStringArrayList("mb",mbitemArrayLists);
        bundle.putStringArrayList("vga",vgaitemArrayLists);
        bundle.putStringArrayList("ram",ramitemArrayLists);
        bundle.putStringArrayList("shd",hhdssditemArrayLists);
        bundle.putStringArrayList("power",poweritemArrayLists);
        bundle.putStringArrayList("cputheselect",cputheitemArrayLists);
        bundle.putStringArrayList("powerw",cpuwArrayLists);
        bundle.putStringArrayList("vgaw",vgawArrayLists);
        bundle.putStringArrayList("cpuprice",cpupriceArrayLists);
        bundle.putStringArrayList("vgaprice",vgapriceArrayLists);
        bundle.putStringArrayList("ssdprice",ssdpriceArrayLists);
        it.putExtras(bundle);
        startActivity(it);
    }

    // Async Task has three overrided methods,
    private class SyncData extends AsyncTask<String, String, String> //設計一個SyncData繼承系統內見的非同步任務並複寫
    {
        String msg = "Internet/DB_Credentials/Windows_FireWall_TurnOn Error, See Android Monitor in the bottom For details!";
        //宣告一個儲存訊息的字串msg，若到程序結束為止都沒找到或連接資料就顯示可能得錯誤訊息="網際網路/資料庫驗證/作業系統防火牆未關閉 錯誤，請查看模擬器底部的細節!"
        ProgressDialog progress;
        //宣告一個progress是一個程序細節，可以顯示程序目前的細節內容進度等

        @Override
        protected void onPreExecute() //程序執行前可以在此做些基本的設定 Starts the progress dailog
        {
            progress = ProgressDialog.show(FullscreenActivity.this, "Synchronising",
                    "Listview Loading! Please Wait...", true);
            //設定progress的標題和內容畫面(創建ProgressDialog的對象畫面,標題,內容,顯示與否)
        }

        @Override
        protected String doInBackground(String... strings)  //程序在背景作業執行中 Connect to the database, write query and add items to array list
        {
            try
            {
                Connection conn = cnClass.CONN(); //宣告conn物件屬於Connection(連線)的物件並呼叫連線cnClass裡的CONN的方法Connection Object
                if (conn == null)//如果conn物件為空值
                {
                    msg = "Not conn";//預設的訊息就會被Not conn取代並在程序結束時顯示
                    success = false;//並且將success設為假值
                }
                else {//如果conn不為空值則執行
                    // Change below query according to your own database.

                    Statement stmt = conn.createStatement();//使用JAVA內預設連接SQL的聲明宣告stmt等於連線的聲明
                    String query = "SELECT * FROM cpu Order by cPrices asc";//設定查詢SQL語法
                    ResultSet rs = stmt.executeQuery(query);//宣告rs為JAVA內預設連接SQL的ResultSet(結果集)，並使用stmt聲明連線定執行設定的SQL語法，再將執行結果儲存於rs結果集內
                    if (rs != null) //如果執行後的結果即不是空值就執行 if resultset not null, I add items to itemArraylist using class created
                    {

                        while (rs.next())//當結果集仍有下一筆資料的時候執行，當執行到最後一筆資料後的結束點時會自動判斷沒有下一筆，就會自動結束回圈
                        {
                            try {
                                startA = ((String)rs.getString("cSeriesmodel")+"  $"+(String)rs.getString("cPrices"));
                                itemArrayLists.add(startA);
                                startG=(String)rs.getString("cId");
                                cputheitemArrayLists.add(startG);
                                String powerw=(String)rs.getString("cTDP");
                                cpuwArrayLists.add(powerw);
                                //將查詢結果使用符合該宣告且定義好的格式新增至itemArrayLists串列內
                            } catch (Exception ex) {//若在此處的項目新增等有錯誤，則會由此抓取錯誤
                                ex.printStackTrace();//列出抓取到的錯誤，利用堆疊追蹤的方法，取得例外發生的根源
                            }
                        }
                        query = "SELECT * FROM MB Order by mbPrices Asc";
                        rs = stmt.executeQuery(query);
                        while (rs.next())//當結果集仍有下一筆資料的時候執行，當執行到最後一筆資料後的結束點時會自動判斷沒有下一筆，就會自動結束回圈
                        {
                            try {
                                startB = ((String)rs.getString("mbSeriesmodel")+"  $"+(String)rs.getString("mbPrices"));
                                mbitemArrayLists.add(startB);

                                //將查詢結果使用符合該宣告且定義好的格式新增至itemArrayLists串列內
                            } catch (Exception ex) {//若在此處的項目新增等有錯誤，則會由此抓取錯誤
                                ex.printStackTrace();//列出抓取到的錯誤，利用堆疊追蹤的方法，取得例外發生的根源
                            }
                        }
                        query = "SELECT * FROM vga Order by vPrices Asc";
                        rs = stmt.executeQuery(query);
                        while (rs.next())//當結果集仍有下一筆資料的時候執行，當執行到最後一筆資料後的結束點時會自動判斷沒有下一筆，就會自動結束回圈
                        {
                            try {
                                startC = ((String)rs.getString("vSeriesmodel")+"  $"+(String)rs.getString("vPrices"));
                                vgaitemArrayLists.add(startC);
                                String v = rs.getString("vVoltage");
                                vgawArrayLists.add(v);
                                //將查詢結果使用符合該宣告且定義好的格式新增至itemArrayLists串列內
                            } catch (Exception ex) {//若在此處的項目新增等有錯誤，則會由此抓取錯誤
                                ex.printStackTrace();//列出抓取到的錯誤，利用堆疊追蹤的方法，取得例外發生的根源
                            }
                        }
                        query = "SELECT * FROM ram Order by rPrices Asc";
                        rs = stmt.executeQuery(query);
                        while (rs.next())//當結果集仍有下一筆資料的時候執行，當執行到最後一筆資料後的結束點時會自動判斷沒有下一筆，就會自動結束回圈
                        {
                            try {
                                if(rs.getString("rModel")!=null) {
                                    startD = ((String) rs.getString("rModel") + "  $" + (String) rs.getString("rPrices"));
                                }
                                else startD = "?????"+ "  $" + (String) rs.getString("rPrices");
                                ramitemArrayLists.add(startD);

                                //將查詢結果使用符合該宣告且定義好的格式新增至itemArrayLists串列內
                            } catch (Exception ex) {//若在此處的項目新增等有錯誤，則會由此抓取錯誤
                                ex.printStackTrace();//列出抓取到的錯誤，利用堆疊追蹤的方法，取得例外發生的根源
                            }
                        }
                        query = "SELECT * FROM hd order by hType , hPrices asc ";
                        rs = stmt.executeQuery(query);
                        while (rs.next())//當結果集仍有下一筆資料的時候執行，當執行到最後一筆資料後的結束點時會自動判斷沒有下一筆，就會自動結束回圈
                        {
                            try {

                                startE = ((String) rs.getString("hType") + "-"+(String) rs.getString("hModel") + "  $" + (String) rs.getString("hPrices"));
                                hhdssditemArrayLists.add(startE);

                                //將查詢結果使用符合該宣告且定義好的格式新增至itemArrayLists串列內
                            } catch (Exception ex) {//若在此處的項目新增等有錯誤，則會由此抓取錯誤
                                ex.printStackTrace();//列出抓取到的錯誤，利用堆疊追蹤的方法，取得例外發生的根源
                            }
                        }

                        query = "SELECT * FROM power order by prPrices asc ";
                        rs = stmt.executeQuery(query);
                        while (rs.next())//當結果集仍有下一筆資料的時候執行，當執行到最後一筆資料後的結束點時會自動判斷沒有下一筆，就會自動結束回圈
                        {
                            try {

                                startF = ((String) rs.getString("prSeriesmodel")+"  -  " + (String) rs.getString("prPower")+"W  "+ "  $" + (String) rs.getString("prPrices"));
                                poweritemArrayLists.add(startF);

                                //將查詢結果使用符合該宣告且定義好的格式新增至itemArrayLists串列內
                            } catch (Exception ex) {//若在此處的項目新增等有錯誤，則會由此抓取錯誤
                                ex.printStackTrace();//列出抓取到的錯誤，利用堆疊追蹤的方法，取得例外發生的根源
                            }
                        }
                        msg = "Found";//若有找到資料就將訊息替換成Found
                        success = true;//並且將success換成真值
                    } else {
                        msg = "No Data found!";//若沒有找到資料就將訊息替換成No Data found!
                        success = false;//並且將success換成假值
                    }
                    stmt.close();//關閉查詢連線聲明
                    rs.close();//關閉查詢結果集
                }
                conn.close();//關閉資料庫連線
            } catch (Exception e)
            {
                e.printStackTrace();//若在連線資料庫時出現錯誤，將利用堆疊追蹤的方法，取得例外發生的根源
                Writer writer = new StringWriter();//宣告定義寫入的文字組(每一個錯誤的訊息)
                e.printStackTrace(new PrintWriter(writer));//利用堆疊追蹤的方法，取得寫入的文字組的根源(每一個錯誤的訊息)
                msg = writer.toString();//將訊息msg替換成轉換成字串宣告的文字組
                success = false;//並且將success換成假值
            }
            return msg;//傳回訊息內容
        }

        @Override
        protected void onPostExecute(String msg) //程序執行後會將訊息結果顯示出來 disimissing progress dialoge, showing error and setting up my listview
        {
            progress.dismiss();//關閉執行程序前的訊息對話框(loading...)
            Toast.makeText(FullscreenActivity.this, msg + "", Toast.LENGTH_LONG).show();
            //Toast為Android內可嵌入的訊息提示工具，利用Toast的靜態函式makeText建立Toast物件並使用，此處是將之前儲存於msg的內容顯示
            if (success == false)//如果success為假值即代表在執行程序的某處已出現錯誤，所以並不執行任何程序，一開始的預設值即為假值
            {
            }
            else {//若success為真值即執行以下的程序
                try {
                    //spinAdapter = new MyAppAdapter(itemArrayList, activity.this);
                    //spinAdapter = new ArrayAdapter<String>(frame1.getContext(),android.R.layout.simple_spinner_dropdown_item, itemArrayLists);
                    //cpuspinner.setAdapter(frame1.cpuadpater);
                    //定義spinAdapter為一個新的ArrayAdapter並帶入參數(符合此規格的容器位置,使用內建的規格樣式得其中一種,符合此規格的陣列列表)
                    //設置spinner的擴配器為適合的擴配器spinAdapter
                } catch (Exception ex)//例外處理
                {

                }
            }
        }
    }
}

class CnMyDbClass {
    String Driver = "com.mysql.jdbc.Driver";
    //String Driver = "net.sourceforge.jtds.jdbc.Driver";
    //String Driver = "oracle.jdbc.driver.oracledriver";
    String ip = "10.0.2.2:1433";
    String db = "pcprsdb20180411";
    String user = "root";
    String pass = "M15E6I2";
    String URL = null;

    @SuppressLint("NewApi")
    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        try {
            Class.forName(Driver);
            //URL = "jdbc:jtds:sqlserver://" + ip + ";" + "databaseName=" + db + ";user=" + user + ";password=" + pass + ";";
            //URL = "jdbc:mysql://" + ip + "/" + db + "?user=" + user + "&password=" + pass + "&useSSL=true";
            //URL = "jdbc:mysql://" + ip + "/" + db + "?user=" + user + "&password=" + pass + "&useSSL=true";
            URL = "jdbc:mysql://10.0.2.2:3306/pcprsdb20180411?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false";
            //URL = "http://localhost:80/android/get_post.php?status=0";
            //URL="jdbc:mysql://localhost:3306/pcprsdb20180411?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false";
            //jdbc:mysql://localhost:3306/pcprsdb20180411?user=root&password=M15E6I2&useSSL=false
            //DriverManager.getDrivers();
            conn = DriverManager.getConnection(URL,user,pass);
            //Connection conn = (Connection) DriverManager.getConnection(URL,user,pass);
        } catch (SQLException se) {
            Log.e("ERROA", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERROB", e.getMessage());
        } catch (Exception e) {
            Log.e("ERROC", e.getMessage());
        }
        return conn;
    }
}
class SyncDataphptest extends AsyncTask<String, String, String> //設計一個SyncData繼承系統內見的非同步任務並複寫
{
    /*mbitemArrayLists vgaitemArrayLists ramitemArrayLists hhdssditemArrayLists poweritemArrayLists*/
    //建立各個陣列型態的串列儲存等等要拉的資料
    ArrayList<String> itemArrayLists;
    ArrayList<String> mbitemArrayLists;
    ArrayList<String> vgaitemArrayLists;
    ArrayList<String> ramitemArrayLists;
    ArrayList<String> hhdssditemArrayLists;
    ArrayList<String> poweritemArrayLists;
    ArrayList<String> vgawArrayLists;
    ArrayList<String> cputheitemArrayLists;
    ArrayList<String> cputhewArrayLists;
    ArrayList<String> cpupriceArrayLists;
    ArrayList<String> vgapriceArrayLists;
    ArrayList<String> ssdpriceArrayLists;

    String a="1";
    //先宣告一個從網頁HTTP動態瀏覽抓String的內建方法
    BufferedReader reader =null;
    //宣告一個從Clinet端(Android studio)連接到Server端(PHP)的通訊協定 使用內建HttpURLConnection
    HttpURLConnection conn ;
    //宣告一個儲存訊息的字串msg，若到程序結束為止都沒找到或連接資料就顯示可能得錯誤訊息="網際網路/資料庫驗證/作業系統防火牆未關閉 錯誤，請查看模擬器底部的細節!"
    ProgressDialog progress;
    Context context;
    //宣告建構子
    SyncDataphptest(Context context,
                    ArrayList itemArrayLists,
                    ArrayList mbitemArrayLists ,
                    ArrayList ramitemArrayLists,
                    ArrayList vgaitemArrayLists,
                    ArrayList hhdssditemArrayLists,
                    ArrayList poweritemArrayLists,
                    ArrayList vgawArrayLists,
                    ArrayList cputheitemArrayLists,
                    ArrayList cputhewArrayLists,
                    ArrayList cpupriceArrayLists,
                    ArrayList vgapriceArrayLists,
                    ArrayList ssdpriceArrayLists){
        super();
        this.context=context;
        this.itemArrayLists=itemArrayLists;
        this.mbitemArrayLists = mbitemArrayLists;
        this.vgaitemArrayLists=vgaitemArrayLists;
        this.ramitemArrayLists=ramitemArrayLists;
        this.hhdssditemArrayLists = hhdssditemArrayLists;
        this.poweritemArrayLists=poweritemArrayLists;
        this.vgawArrayLists=vgawArrayLists;
        this.cputheitemArrayLists=cputheitemArrayLists;
        this.cputhewArrayLists = cputhewArrayLists;
        this.cpupriceArrayLists = cpupriceArrayLists;
        this.vgapriceArrayLists = vgapriceArrayLists;
        this.ssdpriceArrayLists = ssdpriceArrayLists;
    }
    //宣告一個progress是一個程序細節，可以顯示程序目前的細節內容進度等

    @Override
    protected void onPreExecute() //程序執行前可以在此做些基本的設定 Starts the progress dailog
    {
        progress = ProgressDialog.show(context, "Synchronising",
                "Listview Loading! Please Wait...", true);
        //設定progress的標題和內容畫面(創建ProgressDialog的對象畫面,標題,內容,顯示與否)
    }

    @Override
    protected String doInBackground(String... strings)  //程序在背景作業執行中 Connect to the database, write query and add items to array list
    {
        try {
            //先建立一個指標引擎 指到我們的Server丟出來的網頁
            URL url = new URL("http://203.68.252.59/api/read/readAll.php");
            //開通連接
            conn = (HttpURLConnection) url.openConnection();
            //使用GET(抓取)方法
            conn.setRequestMethod("GET");
            //宣告個設定連接時間 等待多久
            conn.setConnectTimeout(6 * 1000);
            //宣告讀取時間 等待多久
            conn.setReadTimeout(6 * 1000);
            //宣告個從網頁輸入一些東東的方法
            InputStream stream = conn.getInputStream();
            //宣告個從網頁瀏覽字串的方法
            StringBuffer stringBuffer = new StringBuffer();
            //建立動態抓取資料的相關變數 建立動態抓取資料－＞對應到網頁瀏覽字串＋要瀏覽的方法
            reader = new BufferedReader(new InputStreamReader(stream));

            String s = "";
            //老司機開車讀資料瞜
            while ((s = reader.readLine()) != null) {
                stringBuffer.append(s);
            }
            //老司機熄火 整合資料 從動態變靜態
            return stringBuffer.toString();

            //以下不重要
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String msg) //程序執行後會將訊息結果顯示出來 disimissing progress dialoge, showing error and setting up my listview
    {
        try {
            itemArrayLists.clear();
            cputheitemArrayLists.clear();
            cputhewArrayLists.clear();
            cpupriceArrayLists.clear();
            mbitemArrayLists.clear();
            ramitemArrayLists.clear();
            vgawArrayLists.clear();
            vgaitemArrayLists.clear();
            vgapriceArrayLists.clear();
            hhdssditemArrayLists.clear();
            poweritemArrayLists.clear();
            //建立個解JSON封包的方法 以下看各位學弟妹的能力瞜 不要問拎北 拎北是傳說
            JSONObject jsonObject = new JSONObject(msg);
            JSONArray jsonallArray = jsonObject.getJSONArray("All");
            JSONObject lv2jsonObject = (JSONObject)jsonallArray.get(0);
            int length= lv2jsonObject.getJSONArray("cpu").length();
            JSONArray jsonArray =  lv2jsonObject.getJSONArray("cpu");
            for(int j = 0 ; j < length ; j++) {
                JSONObject ob = (JSONObject) jsonArray.get(j);
                String Name = ob.getString("Name");
                String CName = ob.getString("CName");
                String Seriesmodel = ob.getString("Seriesmodel");
                String Prices = ob.getString("Prices");
                String pin = ob.getString("Pin");
                itemArrayLists.add(CName+" "+Seriesmodel+" pin:"+pin+" $"+Prices);
                cputheitemArrayLists.add(ob.getString("ID"));
                cputhewArrayLists.add(ob.getString("TDP"));
                cpupriceArrayLists.add(ob.getString("Prices"));
            }

            lv2jsonObject = (JSONObject)jsonallArray.get(1);
            length= lv2jsonObject.getJSONArray("mb").length();
            jsonArray =  lv2jsonObject.getJSONArray("mb");
            for(int j = 0 ; j < length ; j++) {
                JSONObject ob = (JSONObject) jsonArray.get(j);
                String Name = ob.getString("Name");
                String CName = ob.getString("CName");
                String Seriesmodel = ob.getString("Seriesmodel");
                String Prices = ob.getString("Prices");
                mbitemArrayLists.add(CName+" "+Seriesmodel+" $"+Prices);
            }
            lv2jsonObject = (JSONObject)jsonallArray.get(2);
            length= lv2jsonObject.getJSONArray("ram").length();
            jsonArray =  lv2jsonObject.getJSONArray("ram");
            for(int j = 0 ; j < length ; j++) {

                JSONObject ob = (JSONObject) jsonArray.get(j);
                String Name= ob.getString("Name");
                String CName= ob.getString("CName");
                String Model= ob.getString("Model");
                String Capacity= ob.getString("Capacity");
                String DDR= ob.getString("DDR");
                String MHz= ob.getString("MHz");
                String Prices= ob.getString("Prices");
                ramitemArrayLists.add(CName+" "+Capacity+"G DDR"+DDR+" "+MHz+"MHz $"+Prices);
            }
            lv2jsonObject = (JSONObject)jsonallArray.get(3);
            length= lv2jsonObject.getJSONArray("vga").length();
            jsonArray =  lv2jsonObject.getJSONArray("vga");
            String b = " ";
            for(int j = 0 ; j < length ; j++) {
                JSONObject ob = (JSONObject) jsonArray.get(j);
                String a = ob.getString("Seriesmodel");
                b = ob.getString("Seriesmodel");
                String CName = ob.getString("CName");
                String Seriesmodel = ob.getString("Seriesmodel");
                String Dynamic_clock = ob.getString("Dynamic clock");
                String Core_clock = ob.getString("Core clock");
                String Voltage = ob.getString("Voltage");
                String Prices = ob.getString("Prices");
                vgawArrayLists.add(Voltage);
                vgaitemArrayLists.add("$" + Prices + " " + CName + " " + Seriesmodel + " 動態" + Dynamic_clock + "Mhz 核心" + Core_clock + "MHz");
                vgapriceArrayLists.add(ob.getString("Prices"));

            }
            lv2jsonObject = (JSONObject)jsonallArray.get(4);
            length= lv2jsonObject.getJSONArray("hd").length();
            jsonArray =  lv2jsonObject.getJSONArray("hd");
            for(int j = 0 ; j < length ; j++) {
                JSONObject ob = (JSONObject) jsonArray.get(j);
                String Type= ob.getString("Type");
                String Name= ob.getString("Name");
                String CName= ob.getString("CName");
                String Capacity= ob.getString("Capacity");
                String Prices= ob.getString("Prices");
                hhdssditemArrayLists.add(Type +" "+CName+" "+Capacity+"G $"+Prices);
                ssdpriceArrayLists.add(ob.getString("Prices"));
            }
            lv2jsonObject = (JSONObject)jsonallArray.get(5);
            length= lv2jsonObject.getJSONArray("power").length();
            jsonArray =  lv2jsonObject.getJSONArray("power");
            for(int j = 0 ; j < length ; j++) {
                a="";
                JSONObject ob = (JSONObject) jsonArray.get(j);
                String Name= ob.getString("Name");
                String CName= ob.getString("CName");
                String Seriesmodel= ob.getString("Seriesmodel");
                String Power= ob.getString("Power");
                String Prices= ob.getString("Prices");
                poweritemArrayLists.add(CName +" "+Seriesmodel+" "+Power+"W $"+Prices);
                a =CName +" "+Seriesmodel+" "+Power+"W $"+Prices;
            }
        a="FK";
        } catch (JSONException e) {
            e.printStackTrace();
            a="0";
        }
        catch (Exception e) {
            a="連線失敗";
        }
        // for (int i = 0; i < dataArray.length(); i++) {
        //arrayList.add(dataArray.getJSONObject(i).getString("mfName"));

        // }

        progress.dismiss();//關閉執行程序前的訊息對話框(loading...)
        Toast.makeText(context, a, Toast.LENGTH_LONG).show();
        //Toast為Android內可嵌入的訊息提示工具，利用Toast的靜態函式makeText建立Toast物件並使用，此處是將之前儲存於msg的內容顯示
    }




}