package com.example.dragon.test2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;;import org.json.JSONArray;
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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class selectfragment extends Fragment {
    View selectfragment;
    int time = 0;
    ArrayList<String> itemArrayLists = new ArrayList<>();
    ArrayList<String> mbitemArrayLists = new ArrayList<>();
    ArrayList<String> mbidArrayLists = new ArrayList<>();
    ArrayList<String> vgaitemArrayLists = new ArrayList<>();
    ArrayList<String> ramitemArrayLists = new ArrayList<>();
    ArrayList<String> ramidArrayLists = new ArrayList<>();
    ArrayList<String> hhdssditemArrayLists = new ArrayList<>();
    ArrayList<String> poweritemArrayLists = new ArrayList<>();
    ArrayList<String> poweridArrayLists = new ArrayList<>();
    ArrayList<String> cpuwitemArrayLists = new ArrayList<>();
    ArrayList<String> cputheitemArrayLists = new ArrayList<>();
    ArrayList<String> vgawitemArrayLists = new ArrayList<>();
    ArrayList<String> cpupriceArrayLists = new ArrayList<>();
    ArrayList<String> vgapriceArrayLists = new ArrayList<>();
    ArrayList<String> ssdpriceArrayLists = new ArrayList<>();
    ArrayList<String> mbpriceArrayLists = new ArrayList<>();
    ArrayList<String> rampriceArrayLists = new ArrayList<>();
    ArrayList<String> powerpriceArrayLists = new ArrayList<>();
    String detail;
    int powerw=150,cpuw=0,vgaw=0;
    ArrayAdapter cpuadpater, mbadpater, vgaadpater, ramadpater, hhdadpater, poweradpater;
    Fragmenthomepage fragmenthomepage = new Fragmenthomepage();

    String page []=new String[6] ;
    int price []=new int[]{0,0,0,0,0,0};


    public selectfragment() { //建構子(Required empty public constructor)


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        selectfragment = inflater.inflate(R.layout.fragment_selectfragment, container, false);
        init();
        return selectfragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        itemArrayLists = ((MainActivity) context).returnarraylist("CPU");
        vgaitemArrayLists = ((MainActivity) context).returnarraylist("VGA");
        ramitemArrayLists = ((MainActivity) context).returnarraylist("RAM");
        hhdssditemArrayLists = ((MainActivity) context).returnarraylist("SHD");
        poweritemArrayLists = ((MainActivity) context).returnarraylist("POWER");
        cputheitemArrayLists = ((MainActivity) context).returnarraylist("cpuselect");
        cpuwitemArrayLists = ((MainActivity) context).returnarraylist("powerw");
        vgawitemArrayLists = ((MainActivity) context).returnarraylist("vgaw");
        cpupriceArrayLists = ((MainActivity) context).returnarraylist("cpuprice");
        vgapriceArrayLists = ((MainActivity) context).returnarraylist("vgaprice");
        ssdpriceArrayLists = ((MainActivity) context).returnarraylist("ssdpirce");

    }

    private void init() {

        TextView AsInstructions=(TextView)selectfragment.findViewById(R.id.AsInstructions);
        AsInstructions.setClickable(true);
        AsInstructions.setOnClickListener(AlterInstu);
        final Spinner ramspinner = (Spinner) selectfragment.findViewById(R.id.reramspinner);
        final Spinner mbspinner = (Spinner) selectfragment.findViewById(R.id.rembspinner);
        Spinner cpuspinner = (Spinner) selectfragment.findViewById(R.id.recpuspinner);
        cpuadpater = new ArrayAdapter(getContext(), R.layout.myspinnertextstyle, itemArrayLists);
        cpuadpater.setDropDownViewResource(R.layout.myspinnertextstyle);
        cpuspinner.setAdapter(cpuadpater);
        cpuspinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if(time<1){
                    time++;
                }
                else {
                    powerw-=cpuw;
                    page[0]="";
                    price[0]=0;
                    detail = arg0.getSelectedItem().toString();
                    int i = arg0.getSelectedItemPosition();
                    cpuw=Integer.valueOf(cpuwitemArrayLists.get(i));
                    powerw+=cpuw;
                    TextView test = (TextView) selectfragment.findViewById(R.id.test);
                    String phpcpusql = "http://203.68.252.59/Android/product/fire.php?type=cpu&select=" + cputheitemArrayLists.get(i);
                    page[0]=detail;
                    selectphptest selectphptest = new selectphptest(phpcpusql, test, getContext(),"mb",mbspinner,mbitemArrayLists,mbidArrayLists,mbadpater,mbpriceArrayLists);
                    selectphptest.execute("");
                    price[0] = Integer.valueOf(cpupriceArrayLists.get(i).toString());
                    settext(page,price);
                    powergearing(powerw);
                   //mbidArrayLists = selectphptest.returnmbidArrayLists();
                }
                /*  page[0]="";
                final Spinner mbspinner = (Spinner) selectfragment.findViewById(R.id.rembspinner);
                detail = arg0.getSelectedItem().toString();
                int i =  arg0.getSelectedItemPosition();
                cpuw=Integer.valueOf(cpuwitemArrayLists.get(i));
                powerw+=cpuw;
               -- powergearing(powerw);
                cpudetail = "select mb.mbSeriesmodel,mf.mfName,mf.mfCName,mb.mbPrices,mcr.DDR,mcr.RAMupG,mcr.RAMupMHz from (mb join mf on mb.mfId = mf.mfId)join mcr on mb.mbId = mcr.mbId join (select * from cpu where cpu.cId = '"+ cputheitemArrayLists.get(i) + "' ) as cpu on mcr.CPU = cpu.cId where cpu.cPin = mcr.CPUpin Order by cPrices ASC";
                selectsqlcomn selectsqlcomn1=new selectsqlcomn(cpudetail , 1,getContext(),mbitemArrayLists,mbadpater,mbspinner);
                selectsqlcomn sqlddr = new selectsqlcomn(cpudetail , 2,getContext(),ddrArrayLists,mbadpater,mbspinner);
                selectsqlcomn sqlmhz = new selectsqlcomn(cpudetail , 3,getContext(),mhzArrayLists,mbadpater,mbspinner);
                page[0]=detail;
                settext(page);*/
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        mbspinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if(time<1){
                    time++;
                }
                else {
                    page[1]="";
                    price[1]=0;
                    detail = arg0.getSelectedItem().toString();
                    int i = arg0.getSelectedItemPosition();
                    TextView test = (TextView) selectfragment.findViewById(R.id.test);
                    String phpmbsql = "http://203.68.252.59/Android/product/fire.php?type=mb&select=" + mbidArrayLists.get(i);
                    page[1]=detail;
                    selectphptest selectmbphptest = new selectphptest(phpmbsql, test, getContext(),"ram",ramspinner,ramitemArrayLists,ramidArrayLists,ramadpater,rampriceArrayLists);
                    selectmbphptest.execute("");
                    price[1] = Integer.valueOf(mbpriceArrayLists.get(i).toString());
                    settext(page,price);
                }

                /*  page[1]="";
                detail = arg0.getSelectedItem().toString();
                int i = arg0.getSelectedItemPosition();
                ddrtype= Integer.valueOf(ddrArrayLists.get(i));
                mhz= Integer.valueOf(mhzArrayLists.get(i));

                String ddrdetail="select * from ram join mf on ram.mfId = mf.mfId where ram.rMHz >= '"+ mhz +"' AND ram.rDDR = '"+ddrtype+"' Order by rPrices ASC";
                selectsqlcomn ddr = new selectsqlcomn( ddrdetail , 4,getContext(),ramitemArrayLists,ramadpater,ramspinner);
                page[1]=detail;
                settext(page);*/
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        /**ram**/
        ramspinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (time < 1) {
                    time++;
                } else {
                    price[2]=0;
                    page[2] = "";
                    detail = arg0.getSelectedItem().toString();
                    int i = arg0.getSelectedItemPosition();
                    page[2] = detail;
                    price[2] = Integer.valueOf(rampriceArrayLists.get(i).toString());
                    settext(page,price);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        /***vga***/
        Spinner vgaspinner = (Spinner)selectfragment.findViewById(R.id.revgaspinner);
        vgaadpater = new ArrayAdapter(getContext(), R.layout.myspinnertextstyle, vgaitemArrayLists);
        vgaadpater.setDropDownViewResource(R.layout.myspinnertextstyle);
        vgaspinner.setAdapter(vgaadpater);
        vgaspinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if(time<1){
                    time++;
                }
                else {
                    page[3] = "";
                    price[3]=0;
                    detail = arg0.getSelectedItem().toString();
                    powerw -= vgaw;
                    int i = arg0.getSelectedItemPosition();
                    vgaw = Integer.valueOf(vgawitemArrayLists.get(i));
                    powerw += vgaw;
                    powergearing(powerw);
                    page[3] = detail;
                    price[3] = Integer.valueOf(vgapriceArrayLists.get(i).toString());
                    settext(page, price);
                }
                //cpudetail = "select mb.mbSeriesmodel,mf.mfName,mf.mfCName,mb.mbPrices,mcr.DDR,mcr.RAMupG,mcr.RAMupMHz from (mb join mf on mb.mfId = mf.mfId)join mcr on mb.mbId = mcr.mbId join (select * from cpu where cpu.cId = '"+ cputheitemArrayLists.get(i) + "' ) as cpu on mcr.CPU = cpu.cId where cpu.cPin = mcr.CPUpin";
                //selectsqlcomn selectsqlcomn1=new selectsqlcomn(cpudetail , 1,getContext(),mbitemArrayLists,mbadpater,mbspinner);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        /****hd****/

        Spinner hhdspinner = (Spinner)selectfragment.findViewById(R.id.rehddspinner);
        hhdadpater = new ArrayAdapter(getContext(), R.layout.myspinnertextstyle,hhdssditemArrayLists);
        hhdadpater.setDropDownViewResource(R.layout.myspinnertextstyle);
        hhdspinner.setAdapter(hhdadpater);
        hhdspinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if(time<1){
                    time++;
                }
                else {
                    page[4] = "";
                    price[4]=0;
                    int i = arg0.getSelectedItemPosition();
                    detail = arg0.getSelectedItem().toString();
                    page[4] = detail;
                    price[4] = Integer.valueOf(ssdpriceArrayLists.get(i).toString());
                    settext(page, price);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        Spinner powerspinner =(Spinner)selectfragment.findViewById(R.id.repowerspinner);
        powerspinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if(time<1){
                    time++;
                }
                else {
                    page[5] = "";
                    price[5]=0;
                    int i = arg0.getSelectedItemPosition();
                    detail = arg0.getSelectedItem().toString();
                    page[5] = detail;
                    price[5] = Integer.valueOf(powerpriceArrayLists.get(i).toString());
                    settext(page,  price);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }
    void settext(String a[],int j[]){
       String b="";
       int total = 0;
        for(int i = 0 ; i < a.length;i++){
            b+=a[i]+" \n";
            total+=j[i];
        }
        TextView test = (TextView)selectfragment.findViewById(R.id.test);
        test.setText(b+"\n total:"+total+" 元");
    }
    public Button.OnClickListener AlterInstu=new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            fragmenthomepage.alert("組裝說明","備註：以上配備所列出之電源供應器為最低規格，為保險起見，建議選擇較高規格之電源供應器",getContext());
        }
    };
    void powergearing(int i){
        Spinner powerspinner =(Spinner)selectfragment.findViewById(R.id.repowerspinner);
        TextView test = (TextView)selectfragment.findViewById(R.id.test);
        String phppowersql = "http://203.68.252.59/Android/product/fire.php?type=power&select=" + powerw;
        selectphptest selectmbphptest = new selectphptest(phppowersql, test, getContext(),"power",powerspinner,poweritemArrayLists,poweridArrayLists,poweradpater,powerpriceArrayLists);
        selectmbphptest.execute("");
    }
}
class selectsqlcomn {
    String msg;
    Boolean success;
    CnMyDbClass cnClass = new CnMyDbClass();
    selectsqlcomn(String cpudetail, int type, Context context, ArrayList<String> sqlarraylist, ArrayAdapter adpater, Spinner spinner) {
        try {
            sqlarraylist.clear();
            Connection conn = cnClass.CONN(); //宣告conn物件屬於Connection(連線)的物件並呼叫連線cnClass裡的CONN的方法Connection Object
            if (conn == null)//如果conn物件為空值
            {
                msg = "Not conn";//預設的訊息就會被Not conn取代並在程序結束時顯示
                success = false;//並且將success設為假值
            } else {//如果conn不為空值則執行
                // Change below query according to your own database.
                Statement stmt = conn.createStatement();//使用JAVA內預設連接SQL的聲明宣告stmt等於連線的聲明
                {
                    ResultSet rs = stmt.executeQuery(cpudetail);//宣告rs為JAVA內預設連接SQL的ResultSet(結果集)，並使用stmt聲明連線定執行設定的SQL語法，再將執行結果儲存於rs結果集內
                    if (rs != null) //如果執行後的結果即不是空值就執行 if resultset not null, I add items to itemArraylist using class created
                    {
                        while (rs.next())//當結果集仍有下一筆資料的時候執行，當執行到最後一筆資料後的結束點時會自動判斷沒有下一筆，就會自動結束回圈
                        {
                            try {
                                if (type == 1) {
                                    sqlarraylist.add((rs.getString("mfName") + " " + rs.getString("mbSeriesmodel") + " DDR" + rs.getString("DDR") + " MAX " + rs.getString("RAMupG") + "G " + rs.getString("RAMupMHz") + " MHz(MAX)") + "$" + rs.getString("mbPrices"));
                                } else if (type == 2) {
                                    sqlarraylist.add((rs.getString("DDR")));
                                } else if (type == 3) {
                                    sqlarraylist.add((rs.getString("RAMupMHz")));
                                } else if (type == 4) {
                                    sqlarraylist.add(rs.getString("mfName")+" "+rs.getString("rCapacity")+"G DDR"+rs.getString("rDDR")+" "+rs.getString("rMHz")+" MHz $"+rs.getString("rPrices"));
                                } else if (type == 5) {
                                    sqlarraylist.add(rs.getString("mfName")+" "+rs.getString("prSeriesmodel")+" $"+(rs.getString("prPrices")));
                                }

                                //將查詢結果使用符合該宣告且定義好的格式新增至itemArrayLists串列內
                            } catch (Exception ex) {//若在此處的項目新增等有錯誤，則會由此抓取錯誤
                                ex.printStackTrace();//列出抓取到的錯誤，利用堆疊追蹤的方法，取得例外發生的根源
                            }
                        }

                        if(type==1||type==4||type==5) {
                            adpater = new ArrayAdapter(context, android.R.layout.simple_spinner_item, sqlarraylist);
                            adpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner.setAdapter(adpater);
                        }
                        msg = "Found";//若有找到資料就將訊息替換成Found
                        success = true;//並且將success換成真值
                    } else {
                        Toast.makeText(context, "GG4", Toast.LENGTH_SHORT).show();
                        msg = "No Data found!";//若沒有找到資料就將訊息替換成No Data found!
                        success = false;//並且將success換成假值
                    }
                    stmt.close();//關閉查詢連線聲明
                    rs.close();//關閉查詢結果集
                }
            }
            conn.close();//關閉資料庫連線
        } catch (Exception e) {
            e.printStackTrace();//若在連線資料庫時出現錯誤，將利用堆疊追蹤的方法，取得例外發生的根源
            Writer writer = new StringWriter();//宣告定義寫入的文字組(每一個錯誤的訊息)
            e.printStackTrace(new PrintWriter(writer));//利用堆疊追蹤的方法，取得寫入的文字組的根源(每一個錯誤的訊息)
            msg = writer.toString();//將訊息msg替換成轉換成字串宣告的文字組
            success = false;//並且將success換成假值
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
class selectphptest extends AsyncTask<String, String, String> {
    BufferedReader reader = null;
    HttpURLConnection conn;
    ProgressDialog progress;
    Context context;
    String path = " ";
    String data = "false";
    String type;
    TextView test;
    Spinner spinner;
    ArrayList<String> sqlarraylist;
    ArrayList<String> sqlidarraylist;
    ArrayList<String> pricearraylist;
    ArrayAdapter adpater;
    selectphptest(String path,
                  TextView test,
                  Context context,
                  String type,
                  Spinner spinner,
                  ArrayList<String> sqlarraylist,
                  ArrayList<String> sqlidarraylist,
                  ArrayAdapter adpater,
                  ArrayList<String> pricearraylist
                  ) {
        super();
        this.path=path;
        this.test=test;
        this.context=context;
        this.type=type;
        this.spinner=spinner;
        this.sqlarraylist = sqlarraylist;
        this.sqlidarraylist=sqlidarraylist;
        this.adpater = adpater;
        this.pricearraylist=pricearraylist;
    }

   /* @Override
    protected void onPreExecute() {
        progress = ProgressDialog.show(context, "Synchronising",
                "資料載入中...請稍候", true);
    }*/

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(6 * 1000);
            conn.setReadTimeout(6 * 1000);
           /* List params = new ArrayList();
            params.add(new BasicNameValuePair("data", d));*/
            /*
            OutputStream os=conn.getOutputStream();
            String params="name="+ d;
            os.write(params.getBytes());

            conn.setDoInput(true);
*/
            String s = "";

            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();//得到网络返回的输入流
                BufferedReader buf = new BufferedReader(new InputStreamReader(is));//转化为字符缓冲流
                data = buf.readLine();
                buf.close();
                is.close();
            }
            return data.toString();

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
    protected void onPostExecute(String msg) {
        super.onPostExecute(msg);
        sqlarraylist.clear();
        sqlidarraylist.clear();
        pricearraylist.clear();
        try {
            if(type == "mb") {
                JSONObject jsonObject = new JSONObject(msg);
                JSONArray jsonArray = jsonObject.getJSONArray("mb");
                int lenght = jsonObject.getJSONArray("mb").length();
                for (int i = 0; i < lenght; i++) {
                    JSONObject jsonObject1 = new JSONObject(jsonArray.get(i).toString());
                    String name = jsonObject1.getString("Name");
                    String seriesmodel = jsonObject1.getString("Seriesmodel");
                    String price = jsonObject1.getString("Prices");
                    String DDR = jsonObject1.getString("DDR");
                    String RAMupG = jsonObject1.getString("RAMupG");
                    String RAMupMHz = jsonObject1.getString("RAMupMHz");
                    String mbid = jsonObject1.getString("mbId");
                    sqlarraylist.add(" $" + price + " " + name + " " + seriesmodel + " DDR" + DDR + " MAX " + RAMupG + "G " + RAMupMHz + " MHz(MIN)");
                    sqlidarraylist.add(mbid);
                    pricearraylist.add(price);
                }
            }
            else if(type == "ram"){
                JSONObject jsonObject = new JSONObject(msg);
                JSONArray jsonArray = jsonObject.getJSONArray("ram");
                int lenght = jsonObject.getJSONArray("ram").length();
                for (int i = 0; i < lenght; i++) {
                    JSONObject jsonObject1 = new JSONObject(jsonArray.get(i).toString());
                    String name = jsonObject1.getString("Name");
                    String price = jsonObject1.getString("Price");
                    String DDR = jsonObject1.getString("DDR");
                    String G = jsonObject1.getString("G");
                    String MHz = jsonObject1.getString("MHz");
                    sqlarraylist.add(name + " DDR" +DDR+ " "+G+"G "+MHz+"MHz $"+price );
                    pricearraylist.add(price);
                }
            }
            else if(type == "power"){
               JSONObject jsonObject = new JSONObject(msg);
                JSONArray jsonArray = jsonObject.getJSONArray("power");
                int lenght = jsonObject.getJSONArray("power").length();
                for (int i = 0; i < lenght; i++) {
                    JSONObject jsonObject1 = new JSONObject(jsonArray.get(i).toString());
                    String name = jsonObject1.getString("Name");
                    String price = jsonObject1.getString("Prices");
                    String model = jsonObject1.getString("Model");
                    String power = jsonObject1.getString("Power");
                    sqlarraylist.add((i+1 )+" "+name + " "+model+" "+power+"w $"+price );
                    sqlidarraylist.add(power);
                    pricearraylist.add(price);
                }
            }


            if(type == "mb" ||type == "ram"  || type=="power") {
                adpater = new ArrayAdapter(context, R.layout.myspinnertextstyle, sqlarraylist);
                adpater.setDropDownViewResource(R.layout.myspinnertextstyle);
                spinner.setAdapter(adpater);
            }

        } catch (JSONException e) {
            Fragmenthomepage fragmenthomepage = new Fragmenthomepage();
            fragmenthomepage.alert("E","資料庫連線錯誤 請稍後再試",context);
        }
        // progress.dismiss();
    }

    /*ArrayList returnmbidArrayLists(){
        return sqlidarraylist;
    }*/
}
