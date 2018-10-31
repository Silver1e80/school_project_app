package com.example.dragon.test2;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import static android.content.Intent.getIntent;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragmenthomepage extends Fragment implements AdapterView.OnItemSelectedListener {
    LinkedHashSet<String> cpu;
    LinkedHashSet<String> mb;
    LinkedHashSet<String> ram;
    LinkedHashSet<String> vga;
    LinkedHashSet<String> power;
    LinkedHashSet<String> hd;

    String detail, data;
    Spinner cpuspinner, mbspinner, vgaspinner, ramspinner, hhdspinner, powerspinner;
    ArrayAdapter cpuadpater, mbadpater, vgaadpater, ramadpater, hhdadpater, poweradpater;
    ArrayList<String> itemArrayLists = new ArrayList<>();
    ArrayList<String> mbitemArrayLists = new ArrayList<>();
    ArrayList<String> vgaitemArrayLists = new ArrayList<>();
    ArrayList<String> ramitemArrayLists = new ArrayList<>();
    ArrayList<String> hhdssditemArrayLists = new ArrayList<>();
    ArrayList<String> poweritemArrayLists = new ArrayList<>();

    //ArrayList<ClassListItems> mbboxname;
    //MyAppAdapter myAppAdapter;

    Button MBmenumore, VGAmenumore, RAMmenumore, HHDmenumore, powermenumore;
    View fragmenthomepage1;
    private List<Integer> datas;
    private RecyclerView rv;
    private RecyclerView.ItemDecoration itemDecoration;
    private RvAdapter rvAdapter;
    public Fragmenthomepage() { //建構子(Required empty public constructor)

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //指定用於填充fragment的layout資源文件(Inflate the layout for this fragment)
        fragmenthomepage1 = inflater.inflate(R.layout.fragment_fragmenthomepage, container, false);
        setview();
        initid();

        return fragmenthomepage1;
    }

    private void setview() {
        rv = (RecyclerView) fragmenthomepage1.findViewById(R.id.rv);
        initData(1, getContext());
        rvAdapter.setType(1);
        rv.removeItemDecoration(itemDecoration);
        rv.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        rv.setLayoutManager(new LinearLayoutManager(fragmenthomepage1.getContext(), LinearLayoutManager.HORIZONTAL, false));
        itemDecoration = new DividerItemDecoration(fragmenthomepage1.getContext(), DividerItemDecoration.HORIZONTAL_LIST);
        rv.addItemDecoration(itemDecoration);
    }

    /**
     * 初始化数据
     */

    void initData(final int type ,Context context) {
        datas = new ArrayList<>();
        int max = 7;
        /**
         *
         * 將Iv(小圖片)規格以及相關規格存放在Datas堆疊裡
         * */
        for (int i = 0; i < max; i++) {
            Resources res = getResources();
            datas.add(res.getIdentifier("ic_category_" + i, "mipmap",  context.getPackageName()));
        }
        /**
         *用来确定每一个item如何进行排列摆放
         * LinearLayoutManager 相当于ListView的效果
         GridLayoutManager相当于GridView的效果
         StaggeredGridLayoutManager 瀑布流
         */
        /**第一步：设置布局管理器**/
        rv.setLayoutManager(new LinearLayoutManager( context, LinearLayoutManager.VERTICAL, false));

        /**********************************************/
        /**第二步：添加分割线**/
        itemDecoration = new DividerItemDecoration( context, DividerItemDecoration.VERTICAL_LIST);
        rv.addItemDecoration(itemDecoration);
        /**********************************************/


        /**第三步：设置适配器**/
        rvAdapter = new RvAdapter( context, datas);
        rv.setAdapter(rvAdapter);

        /**設置監聽器即換聽**/
        rvAdapter.setOnItemClickListener(new RvAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, Integer data) {
                if(type ==1) {
                    RelativeLayout searchrv = (RelativeLayout) fragmenthomepage1.findViewById(R.id.menu);
                    RelativeLayout cpurv = (RelativeLayout) fragmenthomepage1.findViewById(R.id.cpum);
                    RelativeLayout hdrv = (RelativeLayout) fragmenthomepage1.findViewById(R.id.HDm);
                    RelativeLayout mbrv = (RelativeLayout) fragmenthomepage1.findViewById(R.id.MBm);
                    RelativeLayout ramrv = (RelativeLayout) fragmenthomepage1.findViewById(R.id.RAMm);
                    RelativeLayout VGArv = (RelativeLayout) fragmenthomepage1.findViewById(R.id.VGAm);
                    RelativeLayout POWERrv = (RelativeLayout) fragmenthomepage1.findViewById(R.id.POWERm);

                    TableLayout powerOK = (TableLayout) fragmenthomepage1.findViewById(R.id.powerOK);
                    TableLayout hdOK = (TableLayout) fragmenthomepage1.findViewById(R.id.hdTable);
                    // Toast.makeText(recommendationfragment1.getContext(), "点击了" + position, Toast.LENGTH_SHORT).show();
                    searchrv.setVisibility(View.GONE);
                    hdrv.setVisibility(View.GONE);
                    mbrv.setVisibility(View.GONE);
                    cpurv.setVisibility(View.GONE);
                    ramrv.setVisibility(View.GONE);
                    VGArv.setVisibility(View.GONE);
                    POWERrv.setVisibility(View.GONE);
                    powerOK.setVisibility(View.GONE);
                    hdOK.setVisibility(View.GONE);
                    switch (position) {
                        case 0:
                            Toast.makeText(fragmenthomepage1.getContext(), "您選擇了menu", Toast.LENGTH_SHORT).show();
                            searchrv.setVisibility(View.VISIBLE);
                            break;
                        case 1:
                            Toast.makeText(fragmenthomepage1.getContext(), "您選擇了cpu系列", Toast.LENGTH_SHORT).show();
                            cpurv.setVisibility(View.VISIBLE);
                            break;
                        case 3:
                            Toast.makeText(fragmenthomepage1.getContext(), "您選擇了主機板系列", Toast.LENGTH_SHORT).show();
                            mbrv.setVisibility(View.VISIBLE);
                            break;
                        case 4:
                            Toast.makeText(fragmenthomepage1.getContext(), "您選擇了記憶體系列", Toast.LENGTH_SHORT).show();
                            ramrv.setVisibility(View.VISIBLE);
                            break;
                        case 5:
                            Toast.makeText(fragmenthomepage1.getContext(), "您選擇了顯卡系列", Toast.LENGTH_SHORT).show();
                            VGArv.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            Toast.makeText(fragmenthomepage1.getContext(), "您選擇了硬碟系列", Toast.LENGTH_SHORT).show();
                            LinearLayout hdok1 = (LinearLayout) fragmenthomepage1.findViewById(R.id.hdlinear);
                            Button hdgo = (Button) fragmenthomepage1.findViewById(R.id.hdgo);
                            Button hdback = (Button) fragmenthomepage1.findViewById(R.id.hdgoback);
                            hdrv.setVisibility(View.VISIBLE);
                            hdgo.setVisibility(View.VISIBLE);
                            hdok1.setVisibility(View.VISIBLE);
                            hdback.setVisibility(View.GONE);
                            hdOK.setVisibility(View.VISIBLE);
                            break;
                        default:
                            Toast.makeText(fragmenthomepage1.getContext(), "您選擇了電源系列", Toast.LENGTH_SHORT).show();
                            LinearLayout powerok1 = (LinearLayout) fragmenthomepage1.findViewById(R.id.powerOK1);
                            Button powergo = (Button) fragmenthomepage1.findViewById(R.id.powergo);
                            Button powerback = (Button) fragmenthomepage1.findViewById(R.id.goback);

                            POWERrv.setVisibility(View.VISIBLE);
                            powergo.setVisibility(View.VISIBLE);
                            powerok1.setVisibility(View.VISIBLE);
                            powerback.setVisibility(View.GONE);
                            powerOK.setVisibility(View.VISIBLE);
                            break;
                    }
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        itemArrayLists = ((MainActivity) context).returnarraylist("CPU");
        mbitemArrayLists = ((MainActivity) context).returnarraylist("MB");
        vgaitemArrayLists = ((MainActivity) context).returnarraylist("VGA");
        ramitemArrayLists = ((MainActivity) context).returnarraylist("RAM");
        hhdssditemArrayLists = ((MainActivity) context).returnarraylist("SHD");
        poweritemArrayLists = ((MainActivity) context).returnarraylist("POWER");
    }

    protected void initid() {
        mb = new LinkedHashSet();
        ram = new LinkedHashSet();
        vga = new LinkedHashSet();
        power = new LinkedHashSet();
        hd = new LinkedHashSet();
        cpu = new LinkedHashSet();

        Button CPUmenumore = (Button) fragmenthomepage1.findViewById(R.id.CPUmenumore);

        cpuspinner = (Spinner) fragmenthomepage1.findViewById(R.id.cpuspinner);
        CPUmenumore = (Button) fragmenthomepage1.findViewById(R.id.CPUmenumore);
        CPUmenumore.setOnClickListener(listener);
        cpuadpater = new ArrayAdapter(getContext(), R.layout.myspinnertextstyle, itemArrayLists);
        cpuadpater.setDropDownViewResource(R.layout.myspinnertextstyle);
        cpuspinner.setAdapter(cpuadpater);

        MBmenumore = (Button) fragmenthomepage1.findViewById(R.id.MBmenumore);
        mbspinner = (Spinner) fragmenthomepage1.findViewById(R.id.mbspinner);
        mbadpater = new ArrayAdapter(getContext(),  R.layout.myspinnertextstyle, mbitemArrayLists);
        mbadpater.setDropDownViewResource( R.layout.myspinnertextstyle);
        mbspinner.setAdapter(mbadpater);
        MBmenumore.setOnClickListener(listener);

        VGAmenumore = (Button) fragmenthomepage1.findViewById(R.id.VGAmenumore);
        vgaspinner = (Spinner) fragmenthomepage1.findViewById(R.id.vgaspinner);
        VGAmenumore.setOnClickListener(listener);
        vgaadpater = new ArrayAdapter(getContext(), R.layout.myspinnertextstyle, vgaitemArrayLists);
        vgaadpater.setDropDownViewResource( R.layout.myspinnertextstyle);
        vgaspinner.setAdapter(vgaadpater);

        RAMmenumore = (Button) fragmenthomepage1.findViewById(R.id.RAMmenumore);
        ramspinner = (Spinner) fragmenthomepage1.findViewById(R.id.ramspinner);
        RAMmenumore.setOnClickListener(listener);
        ramadpater = new ArrayAdapter(getContext(), R.layout.myspinnertextstyle, ramitemArrayLists);
        ramadpater.setDropDownViewResource( R.layout.myspinnertextstyle);
        ramspinner.setAdapter(ramadpater);

        HHDmenumore = (Button) fragmenthomepage1.findViewById(R.id.HHDmenumore);
        hhdspinner = (Spinner) fragmenthomepage1.findViewById(R.id.hhdspinner);
        HHDmenumore.setOnClickListener(listener);
        hhdadpater = new ArrayAdapter(getContext(), R.layout.myspinnertextstyle, hhdssditemArrayLists);
        hhdadpater.setDropDownViewResource( R.layout.myspinnertextstyle);
        hhdspinner.setAdapter(hhdadpater);

        powermenumore = (Button) fragmenthomepage1.findViewById(R.id.powermenumore);
        powerspinner = (Spinner) fragmenthomepage1.findViewById(R.id.powerspinner);
        powermenumore.setOnClickListener(listener);
        poweradpater = new ArrayAdapter(getContext(), R.layout.myspinnertextstyle, poweritemArrayLists);
        poweradpater.setDropDownViewResource( R.layout.myspinnertextstyle);
        powerspinner.setAdapter(poweradpater);

        CheckBox cpuintelcheckBox = (CheckBox)fragmenthomepage1.findViewById(R.id.cpuintelcheckBox);
        cpuintelcheckBox.setOnCheckedChangeListener(check);
        CheckBox cpuAMDcheckBox = (CheckBox)fragmenthomepage1.findViewById(R.id.cpuAMDcheckBox);
        cpuAMDcheckBox.setOnCheckedChangeListener(check);

        CheckBox mbasus = (CheckBox)fragmenthomepage1.findViewById(R.id.mbasus);
        mbasus.setOnCheckedChangeListener(check);
        CheckBox mbgigabyte = (CheckBox)fragmenthomepage1.findViewById(R.id.mbgigabyte);
        mbgigabyte.setOnCheckedChangeListener(check);
        CheckBox mbmsi = (CheckBox)fragmenthomepage1.findViewById(R.id.mbmsi);
        mbmsi.setOnCheckedChangeListener(check);
        CheckBox mbasrock = (CheckBox)fragmenthomepage1.findViewById(R.id.mbasrock);
        mbasrock.setOnCheckedChangeListener(check);

        CheckBox ramAdata = (CheckBox)fragmenthomepage1.findViewById(R.id.ramAdata);
        ramAdata.setOnCheckedChangeListener(check);
        CheckBox ramgskill = (CheckBox)fragmenthomepage1.findViewById(R.id.ramgskill);
        ramgskill.setOnCheckedChangeListener(check);
        CheckBox ramKingston = (CheckBox)fragmenthomepage1.findViewById(R.id.ramKingston);
        ramKingston.setOnCheckedChangeListener(check);
        CheckBox ramMicron = (CheckBox)fragmenthomepage1.findViewById(R.id.ramMicron);
        ramMicron.setOnCheckedChangeListener(check);
        CheckBox ramTranscend = (CheckBox)fragmenthomepage1.findViewById(R.id.ramTranscend);
        ramTranscend.setOnCheckedChangeListener(check);
        CheckBox ramUMAX = (CheckBox)fragmenthomepage1.findViewById(R.id.ramUMAX);
        ramUMAX.setOnCheckedChangeListener(check);

        CheckBox vgaASUS = (CheckBox)fragmenthomepage1.findViewById(R.id.vgaASUS);
        vgaASUS.setOnCheckedChangeListener(check);
        CheckBox vgaMSI  = (CheckBox)fragmenthomepage1.findViewById(R.id.vgaMSI);
        vgaMSI.setOnCheckedChangeListener(check);
        CheckBox vgaEVGA = (CheckBox)fragmenthomepage1.findViewById(R.id.vgaEVGA);
        vgaEVGA.setOnCheckedChangeListener(check);
        CheckBox vgaGalaxy = (CheckBox)fragmenthomepage1.findViewById(R.id.vgaGalaxy);
        vgaGalaxy.setOnCheckedChangeListener(check);
        CheckBox vgaGIGABYTE = (CheckBox)fragmenthomepage1.findViewById(R.id.vgaGIGABYTE);
        vgaGIGABYTE.setOnCheckedChangeListener(check);
        CheckBox vgaINNO3D = (CheckBox)fragmenthomepage1.findViewById(R.id.vgaINNO3D);
        vgaINNO3D.setOnCheckedChangeListener(check);
        CheckBox vgaLEADTEK = (CheckBox)fragmenthomepage1.findViewById(R.id.vgaLEADTEK);
        vgaLEADTEK.setOnCheckedChangeListener(check);
        //mbboxname = new ArrayList<ClassListItems>();


        CheckBox powerDelta = (CheckBox)fragmenthomepage1.findViewById(R.id.powerDelta);
        powerDelta.setOnCheckedChangeListener(check);
        CheckBox powerFSP = (CheckBox)fragmenthomepage1.findViewById(R.id.powerFSP);
        powerFSP.setOnCheckedChangeListener(check);
        CheckBox powerAntec = (CheckBox)fragmenthomepage1.findViewById(R.id.powerAntec);
        powerAntec.setOnCheckedChangeListener(check);
        CheckBox powerCorsair = (CheckBox)fragmenthomepage1.findViewById(R.id.powerCorsair);
        powerCorsair.setOnCheckedChangeListener(check);
        CheckBox powerBfriend = (CheckBox)fragmenthomepage1.findViewById(R.id.powerBfriend);
        powerBfriend.setOnCheckedChangeListener(check);
        CheckBox powerGIGABYTE = (CheckBox)fragmenthomepage1.findViewById(R.id.powerGIGABYTE);
        powerGIGABYTE.setOnCheckedChangeListener(check);
        CheckBox powerSAMA = (CheckBox)fragmenthomepage1.findViewById(R.id.powerSAMA);
        powerSAMA.setOnCheckedChangeListener(check);
        CheckBox powerAndyson = (CheckBox)fragmenthomepage1.findViewById(R.id.powerAndyson);
        powerAndyson.setOnCheckedChangeListener(check);
        CheckBox powerXFX = (CheckBox)fragmenthomepage1.findViewById(R.id.powerXFX);
        powerXFX.setOnCheckedChangeListener(check);
        CheckBox powerThermaltake = (CheckBox)fragmenthomepage1.findViewById(R.id.powerThermaltake);
        powerThermaltake.setOnCheckedChangeListener(check);
        CheckBox powerCyberSLIM = (CheckBox)fragmenthomepage1.findViewById(R.id.powerCyberSLIM);
        powerCyberSLIM.setOnCheckedChangeListener(check);
        CheckBox Superflower = (CheckBox)fragmenthomepage1.findViewById(R.id.powerSuperflower);
        Superflower.setOnCheckedChangeListener(check);
        CheckBox powerZUMAX = (CheckBox)fragmenthomepage1.findViewById(R.id.powerZUMAX);
        powerZUMAX.setOnCheckedChangeListener(check);
        CheckBox powerCoolerMaster = (CheckBox)fragmenthomepage1.findViewById(R.id.powerCoolerMaster);
        powerCoolerMaster.setOnCheckedChangeListener(check);
        CheckBox powerSeventeam = (CheckBox)fragmenthomepage1.findViewById(R.id.powerSeventeam);
        powerSeventeam.setOnCheckedChangeListener(check);
        CheckBox powerSilverstone = (CheckBox)fragmenthomepage1.findViewById(R.id.powerSilverstone);
        powerSilverstone.setOnCheckedChangeListener(check);
        CheckBox powerBit = (CheckBox)fragmenthomepage1.findViewById(R.id.powerBitFenix);
        powerBit.setOnCheckedChangeListener(check);
        CheckBox powerSeasonic = (CheckBox)fragmenthomepage1.findViewById(R.id.powerSeasonic);
        powerSeasonic.setOnCheckedChangeListener(check);
        CheckBox powerSEED = (CheckBox)fragmenthomepage1.findViewById(R.id.powerSEED);
        powerSEED.setOnCheckedChangeListener(check);
        CheckBox powerEnermax = (CheckBox)fragmenthomepage1.findViewById(R.id.powerEnermax);
        powerEnermax.setOnCheckedChangeListener(check);
        CheckBox powerEVGA = (CheckBox)fragmenthomepage1.findViewById(R.id.powerEVGA);
        powerEVGA.setOnCheckedChangeListener(check);


        CheckBox hdAdata = (CheckBox)fragmenthomepage1.findViewById(R.id.hdAdata);
        hdAdata.setOnCheckedChangeListener(check);
        CheckBox hdSeagate = (CheckBox)fragmenthomepage1.findViewById(R.id.hdSeagate);
        hdSeagate.setOnCheckedChangeListener(check);
        CheckBox hdToshiba = (CheckBox)fragmenthomepage1.findViewById(R.id.hdToshiba);
        hdToshiba.setOnCheckedChangeListener(check);
        CheckBox hdWD = (CheckBox)fragmenthomepage1.findViewById(R.id.hdWD);
        hdWD.setOnCheckedChangeListener(check);
        CheckBox hdHGST = (CheckBox)fragmenthomepage1.findViewById(R.id.hdHGST);
        hdHGST.setOnCheckedChangeListener(check);
        CheckBox hdSamsung = (CheckBox)fragmenthomepage1.findViewById(R.id.hdSamsung);
        hdSamsung.setOnCheckedChangeListener(check);
        CheckBox hdUMAX = (CheckBox)fragmenthomepage1.findViewById(R.id.hdUMAX);
        hdUMAX.setOnCheckedChangeListener(check);
        CheckBox hdGalaxy = (CheckBox)fragmenthomepage1.findViewById(R.id.hdGalaxy);
        hdGalaxy.setOnCheckedChangeListener(check);
        CheckBox hdMicron = (CheckBox)fragmenthomepage1.findViewById(R.id.hdMicron);
        hdMicron.setOnCheckedChangeListener(check);
        CheckBox hdIntel = (CheckBox)fragmenthomepage1.findViewById(R.id.hdIntel);
        hdIntel.setOnCheckedChangeListener(check);
        CheckBox hdHikvision = (CheckBox)fragmenthomepage1.findViewById(R.id.hdHikvision);
        hdHikvision.setOnCheckedChangeListener(check);
        CheckBox hdPioneer = (CheckBox)fragmenthomepage1.findViewById(R.id.hdPioneer);
        hdPioneer.setOnCheckedChangeListener(check);
        CheckBox hdKingston = (CheckBox)fragmenthomepage1.findViewById(R.id.hdKingston);
        hdKingston.setOnCheckedChangeListener(check);
        CheckBox hdSanDisk = (CheckBox)fragmenthomepage1.findViewById(R.id.hdSanDisk);
        hdSanDisk.setOnCheckedChangeListener(check);
        CheckBox hdTranscend = (CheckBox)fragmenthomepage1.findViewById(R.id.hdTranscend);
        hdTranscend.setOnCheckedChangeListener(check);
        CheckBox hdLiteon = (CheckBox)fragmenthomepage1.findViewById(R.id.hdLiteon);
        hdLiteon.setOnCheckedChangeListener(check);
        CheckBox hdPlextor = (CheckBox)fragmenthomepage1.findViewById(R.id.hdPlextor);
        hdPlextor.setOnCheckedChangeListener(check);


        Button mbdd = (Button) fragmenthomepage1.findViewById(R.id.MBgo);
        mbdd.setOnClickListener(findselect);

        Button rambb = (Button) fragmenthomepage1.findViewById(R.id.ramgo);
        rambb.setOnClickListener(findselect);

        Button vgacc = (Button) fragmenthomepage1.findViewById(R.id.vgago);
        vgacc.setOnClickListener(findselect);

        Button powerdd = (Button) fragmenthomepage1.findViewById(R.id.powergo);
        powerdd.setOnClickListener(findselect);

        Button powerback = (Button)fragmenthomepage1.findViewById(R.id.goback);
        powerback.setOnClickListener(goback);

        Button hdee = (Button) fragmenthomepage1.findViewById(R.id.hdgo);
        hdee.setOnClickListener(findselect);

        Button hdback = (Button)fragmenthomepage1.findViewById(R.id.hdgoback);
        hdback.setOnClickListener(goback);

        Button cpugo = (Button)fragmenthomepage1.findViewById(R.id.cpugo);
        cpugo.setOnClickListener(findselect);

        Button cpumore = (Button)fragmenthomepage1.findViewById(R.id.CPUmore);
        cpumore.setOnClickListener(more);
        Button mbmore = (Button)fragmenthomepage1.findViewById(R.id.MBmore);
        mbmore.setOnClickListener(more);
        Button rammore = (Button)fragmenthomepage1.findViewById(R.id.RAMmore);
        rammore.setOnClickListener(more);
        Button vgamore = (Button)fragmenthomepage1.findViewById(R.id.VGAmore);
        vgamore.setOnClickListener(more);
        Button hhdmore = (Button)fragmenthomepage1.findViewById(R.id.HHDmore);
        hhdmore.setOnClickListener(more);
        Button powermore = (Button)fragmenthomepage1.findViewById(R.id.powermore);
        powermore.setOnClickListener(more);

        //ArrayAdapter<String> cpuadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, main.itemArrayList.size());
        //cpuadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //cpuspinner.setAdapter((SpinnerAdapter) listView);
      /*
        cpuspinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
               // detail = fragmenthomepage1.getResources().getString(arg2);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
      /*  CPUmenumore.setOnClickListener(listener);*/

    }
    private Button.OnClickListener more = new Button.OnClickListener() {
        String cpu="(CPU)處理器 如人類的心臟，各零件的運作不能沒有它\n" +
                "\n" +
                "主要用來接收指令、執行運算及控制程序的重要零件，如同人類的大腦，掌管記憶、思考、決策並接收及整合訊息的重要角色。";
        String mb="(MB)主機板  如人類的身體，各零件必須組裝在它身上\n" +
                "\n" +
                "零件傳遞訊息的橋梁，如人類的身軀，提供各器官之間傳導的通道";
        String ram="(RAM)記憶體 如人類的大腦，一般來說記憶體越大，則電腦能夠同時處理的工作就越多，，但一斷電，所記憶的東西會不見\n" +
                "\n" +
                "存放一些臨時性資料或程式，供處理器快速存取；一般來說記憶體愈大，則電腦能夠同時處理的工作愈多，如人類常用或臨時的記憶存放空間。";
        String hhd="(HDD、SSD)硬碟 如書庫，硬碟越大、能裝的資料越多，一斷電，所儲存的資料不會不見\n" +
                "\n" +
                "將需要長久保存的訊息或大量的資訊儲存容器中，即使關閉電源也不受影響，如書庫，容量愈大、能裝的資料越多。\n";
        String vga="(VGA)顯示卡  如眼睛，顯示卡規格越高、畫質越好、顯示的越清楚";
        String power="(power)電源供應器  顧名思義，不裝電源，電腦不能跑\n" +
                "\n" +
                "，如同人類的心臟，提供各器官所需要的能量";

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.CPUmore:
                    alert("CPU說明",cpu,getContext());break;
                case R.id.MBmore:
                    alert("主機板說明",mb,getContext());break;
                case R.id.RAMmore:
                    alert("記憶體說明",ram,getContext());break;
                case R.id.HHDmore:
                    alert("硬碟說明",hhd,getContext());break;
                case R.id.VGAmore:
                    alert("顯示卡說明",vga,getContext());break;
                case R.id.powermore:
                    alert("電源供應器說明",power,getContext());break;
            }
        }
    };
    private Button.OnClickListener goback = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.goback: {
                    LinearLayout powerok1 = (LinearLayout) fragmenthomepage1.findViewById(R.id.powerOK1);
                    Button powergo = (Button) fragmenthomepage1.findViewById(R.id.powergo);
                    Button powerback = (Button) fragmenthomepage1.findViewById(R.id.goback);
                    TableLayout powerOK = (TableLayout) fragmenthomepage1.findViewById(R.id.powerOK);

                    powergo.setVisibility(View.VISIBLE);
                    powerok1.setVisibility(View.VISIBLE);
                    powerback.setVisibility(View.GONE);
                    powerOK.setVisibility(View.VISIBLE);
                    break;
                }
                case R.id.hdgoback: {
                    LinearLayout hdok1 = (LinearLayout) fragmenthomepage1.findViewById(R.id.hdlinear);
                    Button hdgo = (Button) fragmenthomepage1.findViewById(R.id.hdgo);
                    Button hdback = (Button) fragmenthomepage1.findViewById(R.id.hdgoback);
                    TableLayout hdtable = (TableLayout) fragmenthomepage1.findViewById(R.id.hdTable);

                    hdgo.setVisibility(View.VISIBLE);
                    hdok1.setVisibility(View.VISIBLE);
                    hdback.setVisibility(View.GONE);
                    hdtable.setVisibility(View.VISIBLE);
                    break;
                }
            }
        }
    };
    private Button.OnClickListener findselect = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            SQLcomn SQLcomn1;
                try {
                    if (v.getId() == R.id.MBgo) {
                        String mbselect = "http://203.68.252.59/Android/product/post.php?type=mb&select=";
                        final ArrayList<ClassListItems> mbboxname = new ArrayList<>();
                        ListView mblistview = (ListView) fragmenthomepage1.findViewById(R.id.mblistView);
                        EditText mblowe = (EditText) fragmenthomepage1.findViewById(R.id.mblow);
                        EditText mbupe = (EditText) fragmenthomepage1.findViewById(R.id.mbup);
                        if (String.valueOf(mb.size()) != "0") {
                            mbselect+=resultjson(mbupe,mblowe,mb);
                        } else {/*Adata G.skill Kingston Micron Transcend UMAX*/
                            JSONObject json_obj = new JSONObject();            //用來當內層被丟進陣列內的JSON物件
                            JSONObject json_obj_arr = new JSONObject();            //用來當內層被丟進陣列內的JSON物件
                            JSONArray json_arr = new JSONArray();
                            json_obj=elseresultjson(mbupe,mblowe,json_obj);
                            //json_obj_arr.put("name0","Intel");
                            //json_obj_arr.put("name1","AMD");
                            json_arr.put("ASUS");
                            json_arr.put("GIGABYTE");
                            json_arr.put("MSI");
                            json_arr.put("ASRock");
                            // json_arr.put(json_obj_arr);
                            json_obj.put("arr",json_arr);
                            mbselect+=json_obj.toString();
                        }

                        cellphptest cellphptest = new cellphptest(getContext(),mbselect.toString(),mbboxname,mblistview,"mb");
                        cellphptest.execute("");

                      //  mbselect = "(SELECT * FROM mb join mf on mb.mfId = mf.mfId WHERE mf.mfName='ASUS' or mf.mfName='GIGABYTE' or mf.mfName='MSI' or mf.mfName='ASRock') Order by mbPrices asc";

                        //SQLcomn1= new SQLcomn(mbselect, sqlPrice, mbup, mblow, mbboxname, mblistview, 1,getContext());
                    }
                    else if(v.getId()==R.id.ramgo){
                        final ArrayList<ClassListItems> ramboxname = new ArrayList<>();
                        String ramselect = "http://203.68.252.59/Android/product/post.php?type=ram&select=";
                        ListView ramlistview = (ListView) fragmenthomepage1.findViewById(R.id.ramlistView);
                        EditText ramlowe = (EditText) fragmenthomepage1.findViewById(R.id.ramlower);
                        EditText ramupe = (EditText) fragmenthomepage1.findViewById(R.id.ramupper);
                        if (String.valueOf(ram.size()) != "0") {
                            ramselect+=resultjson(ramupe,ramlowe,ram);
                        } else {/*Adata G.skill Kingston Micron Transcend UMAX*/
                            JSONObject json_obj = new JSONObject();            //用來當內層被丟進陣列內的JSON物件
                            JSONObject json_obj_arr = new JSONObject();            //用來當內層被丟進陣列內的JSON物件
                            JSONArray json_arr = new JSONArray();
                            json_obj=elseresultjson(ramupe,ramlowe,json_obj);
                            json_arr.put("Adata");
                            json_arr.put("G.skill");
                            json_arr.put("Kingston");
                            json_arr.put("Micron");
                            json_arr.put("Transcend");
                            json_arr.put("UMAX");
                            json_obj.put("arr",json_arr);
                            ramselect+=json_obj.toString();
                        }
                        cellphptest cellphptest = new cellphptest(getContext(),ramselect.toString(),ramboxname,ramlistview,"ram");
                        cellphptest.execute("");
                        ramselect = "(SELECT * FROM ram join mf on ram.mfId = mf.mfId WHERE " +
                                "mf.mfName='Adata' or mf.mfName='G.skill' or mf.mfName='Kingston' " +
                                "or mf.mfName='Micron' or mf.mfName='Transcend' or mf.mfName='UMAX') Order by rPrices asc";

                      //  SQLcomn1 = new SQLcomn(ramselect, sqlPrice, ramup, ramlow, ramboxname, ramlistview, 3,getContext());
                    }
                    else if(v.getId()==R.id.vgago){
                        final ArrayList<ClassListItems> vgaboxname = new ArrayList<>();
                        String vgaselect = "http://203.68.252.59/Android/product/post.php?type=vga&select=";
                        ListView vgalistview = (ListView) fragmenthomepage1.findViewById(R.id.vgalistView);
                        EditText vgalowe = (EditText) fragmenthomepage1.findViewById(R.id.vgalower);
                        EditText vgaupe = (EditText) fragmenthomepage1.findViewById(R.id.vgaupper);
                        if (String.valueOf(vga.size()) != "0") {
                            vgaselect+=resultjson(vgaupe,vgalowe,vga);
                        } else {/*Adata G.skill Kingston Micron Transcend UMAX*/
                            JSONObject json_obj = new JSONObject();            //用來當內層被丟進陣列內的JSON物件
                            JSONObject json_obj_arr = new JSONObject();            //用來當內層被丟進陣列內的JSON物件
                            JSONArray json_arr = new JSONArray();
                            json_obj=elseresultjson(vgaupe,vgalowe,json_obj);
                            json_arr.put("ASUS");
                            json_arr.put("MSI");
                            json_arr.put("GIGABYTE");
                            json_arr.put("EVGA");
                            json_arr.put("Galaxy");
                            json_arr.put("INNO3D");
                            json_arr.put("Leadtek");
                            // json_arr.put(json_obj_arr);
                            json_obj.put("arr",json_arr);
                            vgaselect+=json_obj.toString();
                        }

                        cellphptest cellphptest = new cellphptest(getContext(),vgaselect.toString(),vgaboxname,vgalistview,"vga");
                        cellphptest.execute("");
                        vgaselect = "( SELECT * FROM vga join make on vga.vId = make.vId join mf on make.mfId=mf.mfId " +
                                " WHERE mf.mfName='ASUS' or mf.mfName='MSI' or mf.mfName='GIGABYTE' or mf.mfName='EVGA' or mf.mfName='Galaxy'" +
                                " or mf.mfName='INNO3D' or mf.mfName='Leadtek') Order by vPrices asc";
                        //SQLcomn1=new SQLcomn(vgaselect, sqlPrice, vgaup, vgalow, vgaboxname, vgalistview, 4,getContext());
                    }
                    else if(v.getId()==R.id.cpugo){
                                 //JSON陣列
//首先將要丟進陣列內的JSON物件存好內容後丟進陣列
                        ArrayList<ClassListItems> cpuboxname = new ArrayList<>();
                        ListView cpulistview = (ListView) fragmenthomepage1.findViewById(R.id.cpulistView);
                        EditText cpulowe = (EditText) fragmenthomepage1.findViewById(R.id.cpulower);
                        EditText cpuupe = (EditText) fragmenthomepage1.findViewById(R.id.cpuupper);
                        String cpuurlip="http://203.68.252.59/Android/product/post.php?type=cpu&select=";
                        if (String.valueOf(cpu.size()) != "0") {
                            cpuurlip+=resultjson(cpuupe,cpulowe,cpu);
                        } else {/*Adata G.skill Kingston Micron Transcend UMAX*/
                            JSONObject json_obj = new JSONObject();            //用來當內層被丟進陣列內的JSON物件
                            JSONObject json_obj_arr = new JSONObject();            //用來當內層被丟進陣列內的JSON物件
                            JSONArray json_arr = new JSONArray();
                            json_obj=elseresultjson(cpuupe,cpulowe,json_obj);
                            //json_obj_arr.put("name0","Intel");
                            //json_obj_arr.put("name1","AMD");
                            json_arr.put("Intel");
                            json_arr.put("AMD");
                           // json_arr.put(json_obj_arr);
                            json_obj.put("arr",json_arr);
                            cpuurlip+=json_obj.toString();
                        }

                        cellphptest cellphptest = new cellphptest(getContext(),cpuurlip.toString(),cpuboxname,cpulistview,"cpu");
                        cellphptest.execute("");

                        String sqlPrice = "cPrices";
                        String cpuselect ="SELECT * FROM cpu join mf on cpu.mfId = mf.mfId WHERE mf.mfName='INTEL' Order by cPrices asc";
                        // SQLcomn1=new SQLcomn(cpuselect, sqlPrice, cpuup, cpulow, cpuboxname, cpulistview, 2,getContext());
                    }
                    else if(v.getId()==R.id.powergo){
                        TableLayout powerOK = (TableLayout)fragmenthomepage1.findViewById(R.id.powerOK);
                        LinearLayout powerok1 = (LinearLayout)fragmenthomepage1.findViewById(R.id.powerOK1);
                        Button powergo = (Button)fragmenthomepage1.findViewById(R.id.powergo);
                        Button powerback = (Button)fragmenthomepage1.findViewById(R.id.goback);
                        powergo.setVisibility(View.GONE);
                        powerOK.setVisibility(View.GONE);
                        powerok1.setVisibility(View.GONE);
                        powerback.setVisibility(View.VISIBLE);
                        String sqlPrice = "prPrices";
                        final ArrayList<ClassListItems> powerboxname = new ArrayList<>();
                        String powerselect ="http://203.68.252.59/Android/product/post.php?type=power&select=";
                        ListView powerlistview = (ListView) fragmenthomepage1.findViewById(R.id.powerlistView);
                        EditText powerlowe = (EditText) fragmenthomepage1.findViewById(R.id.powerlower);
                        EditText powerupe = (EditText) fragmenthomepage1.findViewById(R.id.powerupper);
                        if (String.valueOf(power.size()) != "0") {
                            powerselect+=resultjson(powerupe,powerlowe,power);
                        } else {/*Adata G.skill Kingston Micron Transcend UMAX*/
                            JSONObject json_obj = new JSONObject();            //用來當內層被丟進陣列內的JSON物件
                            JSONArray json_arr = new JSONArray();
                            json_obj=elseresultjson(powerupe,powerlowe,json_obj);
                            json_obj.put("arr",json_arr);
                            powerselect+=json_obj.toString();
                        }

                        cellphptest cellphptest = new cellphptest(getContext(),powerselect.toString(),powerboxname,powerlistview,"power");
                        cellphptest.execute("");
                        powerselect ="(SELECT * FROM power join mf on power.mfId = mf.mfId WHERE mf.mfName='Deita' or mf.mfName='FSP' or mf.mfName='Antec' or mf.mfName='Corsair' or mf.mfName='BitFenix火鳥'" +
                                " or mf.mfName='B.FRIEND' or mf.mfName='GIGABYTE' or mf.mfName='SAMA' or mf.mfName='EVGA' or mf.mfName='Andyson'" +
                                " or mf.mfName='XFX' or mf.mfName='Thermaltake' or mf.mfName='CyberSLIM' or mf.mfName='SUPERFLOWER' or mf.mfName='ZUMAX'" +
                                " or mf.mfName='CoolerMaster' or mf.mfName='SILVERSTONE' or mf.mfName='Seasonic' or mf.mfName='SEED' or mf.mfName='Enermax' ) Order by prPrices asc";

                       // SQLcomn1=new SQLcomn(powerselect, sqlPrice, powerup, powerlow, powerboxname, powerlistview, 5 , getContext());
                    }else if(v.getId()==R.id.hdgo){
                        TableLayout hdOK = (TableLayout) fragmenthomepage1.findViewById(R.id.hdTable);
                        LinearLayout hdok1 = (LinearLayout) fragmenthomepage1.findViewById(R.id.hdlinear);
                        Button hdgo = (Button) fragmenthomepage1.findViewById(R.id.hdgo);
                        Button hdback = (Button) fragmenthomepage1.findViewById(R.id.hdgoback);
                        hdgo.setVisibility(View.GONE);
                        hdOK.setVisibility(View.GONE);
                        hdok1.setVisibility(View.GONE);
                        hdback.setVisibility(View.VISIBLE);
                        final ArrayList<ClassListItems> hdboxname = new ArrayList<>();
                        ListView hdlistview = (ListView) fragmenthomepage1.findViewById(R.id.HDlistView);
                        EditText hdlowe = (EditText) fragmenthomepage1.findViewById(R.id.hdlower);
                        EditText hdupe = (EditText) fragmenthomepage1.findViewById(R.id.hdupper);
                        String hdselect = "http://203.68.252.59/Android/product/post.php?type=hd&select=";
                        if (String.valueOf(hd.size()) != "0") {
                            hdselect+=resultjson(hdupe,hdlowe,hd);
                        } else {/*Adata G.skill Kingston Micron Transcend UMAX*/
                            JSONObject json_obj = new JSONObject();            //用來當內層被丟進陣列內的JSON物件
                            JSONObject json_obj_arr = new JSONObject();            //用來當內層被丟進陣列內的JSON物件
                            JSONArray json_arr = new JSONArray();
                            json_obj=elseresultjson(hdupe,hdlowe,json_obj);
                            json_arr.put("Adata");
                            json_arr.put("Seagate");
                            json_arr.put("WD");
                            json_arr.put("Toshiba");
                            json_arr.put("HGST");
                            json_arr.put("Samsung");
                            json_arr.put("UMAX");
                            json_arr.put("Galaxy");
                            json_arr.put("Micron");
                            json_arr.put("Intel");
                            json_arr.put("Hikvision");
                            json_arr.put("Pioneer");
                            json_arr.put("SanDisk");
                            json_arr.put("Kingston");
                            json_arr.put("Transcend");
                            json_arr.put("Liteon");
                            json_arr.put("Plextor");
                            json_obj.put("arr",json_arr);
                            hdselect+=json_obj.toString();
                        }

                        cellphptest cellphptest = new cellphptest(getContext(),hdselect.toString(),hdboxname,hdlistview,"HHDSSD");
                        cellphptest.execute("");
                        // SQLcomn1=new SQLcomn(hdselect, sqlPrice, hdup, hdlow, hdboxname, hdlistview, 6,getContext());
                    }

                } catch (Exception e) {
                    alert("格式錯誤", "請輸入正確參數", getContext());
                }
        }
    };

    private CheckBox.OnCheckedChangeListener check=new CheckBox.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()){
                /****************HD*****************/
                case R.id.hdAdata:{
                    if (isChecked) {
                        hd.add("Adata");
                    } else {
                        hd.remove("Adata");
                    }break;
                }
                case R.id.hdSeagate:{
                    if (isChecked) {
                        hd.add("Seagate");
                    } else {
                        hd.remove("Seagate");
                    }break;
                }
                case R.id.hdWD:{
                    if (isChecked) {
                        hd.add("WD");
                    } else {
                        hd.remove("WD");
                    }break;
                }
                case R.id.hdToshiba:{
                    if (isChecked) {
                        hd.add("Toshiba");
                    } else {
                        hd.remove("Toshiba");
                    }break;
                }
                case R.id.hdHGST:{
                    if (isChecked) {
                        hd.add("HGST");
                    } else {
                        hd.remove("HGST");
                    }break;
                }
                case R.id.hdSamsung:{
                    if (isChecked) {
                        hd.add("Samsung");
                    } else {
                        hd.remove("Samsung");
                    }break;
                }
                case R.id.hdUMAX:{
                    if (isChecked) {
                        hd.add("UMAX");
                    } else {
                        hd.remove("UMAX");
                    }break;
                }
                case R.id.hdGalaxy:{
                    if (isChecked) {
                        hd.add("Galaxy");
                    } else {
                        hd.remove("Galaxy");
                    }break;
                }
                case R.id.hdMicron:{
                    if (isChecked) {
                        hd.add("Micron");
                    } else {
                        hd.remove("Micron");
                    }break;
                }
                case R.id.hdIntel:{
                    if (isChecked) {
                        hd.add("Intel");
                    } else {
                        hd.remove("Intel");
                    }break;
                }
                case R.id.hdHikvision:{
                    if (isChecked) {
                        hd.add("Hikvision");
                    } else {
                        hd.remove("Hikvision");
                    }break;
                }
                case R.id.hdPioneer:{
                    if (isChecked) {
                        hd.add("Pioneer");
                    } else {
                        hd.remove("Pioneer");
                    }break;
                }
                case R.id.hdKingston:{
                    if (isChecked) {
                        hd.add("Kingston");
                    } else {
                        hd.remove("Kingston");
                    }break;
                }
                case R.id.hdSanDisk:{
                    if (isChecked) {
                        hd.add("SanDisk");
                    } else {
                        hd.remove("SanDisk");
                    }break;
                }
                case R.id.hdTranscend:{
                    if (isChecked) {
                        hd.add("Transcend");
                    } else {
                        hd.remove("Transcend");
                    }break;
                }
                case R.id.hdLiteon:{
                    if (isChecked) {
                        hd.add("Liteon");
                    } else {
                        hd.remove("Liteon");
                    }break;
                }
                case R.id.hdPlextor:{
                    if (isChecked) {
                        hd.add("Plextor");
                    } else {
                        hd.remove("Plextor");
                    }break;
                }

                /**************Power****************/
                case R.id.powerDelta:{
                    if (isChecked) {
                        power.add("Delta");
                    } else {
                        power.remove("Delta");
                    }break;
                }
                case R.id.powerFSP:{
                    if (isChecked) {
                        power.add("FSP");
                    } else {
                        power.remove("FSP");
                    }break;
                }
                case R.id.powerAntec:{
                    if (isChecked) {
                        power.add("Antec");
                    } else {
                        power.remove("Antec");
                    }break;
                }
                case R.id.powerCorsair:{
                    if (isChecked) {
                        power.add("Corsair");
                    } else {
                        power.remove("Corsair");
                    }break;
                }
                case R.id.powerBitFenix:{
                    if (isChecked) {
                        power.add("BitFenix火鳥");
                    } else {
                        power.remove("BitFenix火鳥");
                    }break;
                }
                case R.id.powerBfriend:{
                    if (isChecked) {
                        power.add("B.FRIEND");
                    } else {
                        power.remove("B.FRIEND");
                    }break;
                }
                case R.id.powerGIGABYTE:{
                    if (isChecked) {
                        power.add("GIGABYTE");
                    } else {
                        power.remove("GIGABYTE");
                    }break;
                }
                case R.id.powerSAMA:{
                    if (isChecked) {
                        power.add("SAMA");
                    } else {
                        power.remove("SAMA");
                    }break;
                }
                case R.id.powerEVGA:{
                    if (isChecked) {
                        power.add("EVGA");
                    } else {
                        power.remove("EVGA");
                    }break;
                }
                case R.id.powerAndyson:{
                    if (isChecked) {
                        power.add("Andyson");
                    } else {
                        power.remove("Andyson");
                    }break;
                }
                case R.id.powerThermaltake:{
                    if (isChecked) {
                        power.add("Thermaltake");
                    } else {
                        power.remove("Thermaltake");
                    }break;
                }
                case R.id.powerXFX:{
                    if (isChecked) {
                        power.add("XFX");
                    } else {
                        power.remove("XFX");
                    }break;
                }
                case R.id.powerCyberSLIM:{
                    if (isChecked) {
                        power.add("CyberSLIM");
                    } else {
                        power.remove("CyberSLIM");
                    }break;
                }
                case R.id.powerSuperflower:{
                    if (isChecked) {
                        power.add("SUPERFLOWER");
                    } else {
                        power.remove("SUPERFLOWER");
                    }break;
                }
                case R.id.powerZUMAX:{
                    if (isChecked) {
                        power.add("ZUMAX");
                    } else {
                        power.remove("ZUMAX");
                    }break;
                }
                case R.id.powerCoolerMaster:{
                    if (isChecked) {
                        power.add("CoolerMaster");
                    } else {
                        power.remove("CoolerMaster");
                    }break;
                }
                case R.id.powerSeventeam:{
                    if (isChecked) {
                        power.add("Seventeam");
                    } else {
                        power.remove("Seventeam");
                    }break;
                }
                case R.id.powerSilverstone:{
                    if (isChecked) {
                        power.add("SILVERSTONE");
                    } else {
                        power.remove("SILVERSTONE");
                    }break;
                }
                case R.id.powerSeasonic:{
                    if (isChecked) {
                        power.add("Seasonic");
                    } else {
                        power.remove("Seasonic");
                    }break;
                }
                case R.id.powerSEED:{
                    if (isChecked) {
                        power.add("SEED");
                    } else {
                        power.remove("SEED");
                    }break;
                }
                case R.id.powerEnermax:{
                    if (isChecked) {
                        power.add("Enermax");
                    } else {
                        power.remove("Enermax");
                    }break;
                }
                case R.id.mbasus: {
                    if (isChecked) {
                        mb.add("ASUS");
                    } else {
                        mb.remove("ASUS");
                    }break;
                }
                case R.id.mbgigabyte:{
                    if (isChecked) {
                        mb.add("gigabyte");
                    } else {
                        mb.remove("gigabyte");
                    }break;
                }
                case R.id.mbmsi:{
                    if (isChecked) {
                        mb.add("msi");
                    } else {
                        mb.remove("msi");
                    }break;
                }
                case R.id.mbasrock:{
                    if (isChecked) {
                        mb.add("asrock");
                    } else {
                        mb.remove("asrock");
                    }break;
                }
                /************CPU************/
                case R.id.cpuintelcheckBox:{
                    if(isChecked){
                        cpu.add("Intel");
                    }else{
                        cpu.remove("Intel");
                    }break;
                }
                case R.id.cpuAMDcheckBox:{
                    if(isChecked){
                        cpu.add("AMD");
                    }else{
                        cpu.remove("AMD");
                    }break;
                }

                /*************RAM************/

                case R.id.ramAdata:{
                    if (isChecked) {
                        ram.add("Adata");
                    } else {
                        ram.remove("Adata");
                    }break;
                }
                case R.id.ramgskill:{
                    if (isChecked) {
                        ram.add("G.skill");
                    } else {
                        ram.remove("G.skill");
                    }break;
                }
                case R.id.ramKingston:{
                    if (isChecked) {
                        ram.add("Kingston");
                    } else {
                        ram.remove("Kingston");
                    }break;
                }
                case R.id.ramMicron:{
                    if (isChecked) {
                        ram.add("Micron");
                    } else {
                        ram.remove("Micron");
                    }break;
                }
                case R.id.ramTranscend:{
                    if (isChecked) {
                        ram.add("Transcend");
                    } else {
                        ram.remove("Transcend");
                    }break;
                }
                case R.id.ramUMAX:{
                    if (isChecked) {
                        ram.add("UMAX");
                    } else {
                        ram.remove("UMAX");
                    }break;
                }

                /**********************VGA*************************/
                case R.id.vgaASUS:{
                    if (isChecked) {
                        vga.add("ASUS");
                    } else {
                        vga.remove("ASUS");
                    }break;
                }
                case R.id.vgaMSI:{
                    if (isChecked) {
                        vga.add("MSI");
                    } else {
                        vga.remove("MSI");
                    }break;
                }
                case R.id.vgaGIGABYTE:{
                    if (isChecked) {
                        vga.add("GIGABYTE");
                    } else {
                        vga.remove("GIGABYTE");
                    }break;
                }
                case R.id.vgaEVGA:{
                    if (isChecked) {
                        vga.add("EVGA");
                    } else {
                        vga.remove("EVGA");
                    }break;
                }
                case R.id.vgaGalaxy:{
                    if (isChecked) {
                        vga.add("Galaxy");
                    } else {
                        vga.remove("Galaxy");
                    }break;
                }
                case R.id.vgaINNO3D:{
                    if (isChecked) {
                        vga.add("INNO3D");
                    } else {
                        vga.remove("INNO3D");
                    }break;
                }
                case R.id.vgaLEADTEK:{
                    if (isChecked) {
                        vga.add("LEADTEK");
                    } else {
                        vga.remove("LEADTEK");
                    }break;
                }
                default:break;
            }

        }
    };
    JSONObject elseresultjson(EditText up ,EditText down , JSONObject jsonObject) throws JSONException {
        int editup = Integer.valueOf(up.getText().toString());
        int editdown = Integer.valueOf(down.getText().toString());
        jsonObject.put("priceup",editup);
        jsonObject.put("pricedown",editdown);
        return jsonObject;
    }
    String resultjson(EditText up , EditText down,LinkedHashSet type) throws JSONException {
        JSONObject jsonObject =new JSONObject();
        int editup = Integer.valueOf(up.getText().toString());
        int editdown = Integer.valueOf(down.getText().toString());
        jsonObject.put("priceup",editup);
        jsonObject.put("pricedown",editdown);
        JSONArray jsonArray = new JSONArray();
        JSONObject json_obj_arr = new JSONObject();
        String [] typearray = new String[type.size()];
        type.toArray(typearray);
        for(int i = 0; i < typearray.length;i++){
            // json_obj_arr.put("name"+i,typearray[i]);
            jsonArray.put(typearray[i]);
        }
        // jsonArray.put(json_obj_arr);
        jsonObject.put("arr",jsonArray);

        return jsonObject.toString();
    }
    private Button.OnClickListener listener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.CPUmenumore) {
                detail = ((Spinner)fragmenthomepage1.findViewById(R.id.cpuspinner)).getSelectedItem().toString();
                //detail = ((Spinner)fragmenthomepage1.findViewById(R.id.cpuspinner)).toString();
                //產生視窗物件
                alert("CPU處理器詳細規格",detail,getContext());
            }
            else if (v.getId() == R.id.MBmenumore) {
                detail = ((Spinner)fragmenthomepage1.findViewById(R.id.mbspinner)).getSelectedItem().toString();
                //產生視窗物件
                alert("主機板詳細規格",detail,getContext());
            }
            else if (v.getId() == R.id.VGAmenumore) {
                detail = ((Spinner)fragmenthomepage1.findViewById(R.id.vgaspinner)).getSelectedItem().toString();
                //產生視窗物件
                alert("顯示卡詳細規格",detail,getContext());
            }
            else if (v.getId() == R.id.RAMmenumore) {
                detail = ((Spinner)fragmenthomepage1.findViewById(R.id.ramspinner)).getSelectedItem().toString();
                //產生視窗物件
                alert("記憶體詳細規格",detail,getContext());
            }
            else if (v.getId() == R.id.HHDmenumore) {
                detail = ((Spinner)fragmenthomepage1.findViewById(R.id.hhdspinner)).getSelectedItem().toString();
                //產生視窗物件
                alert("硬碟詳細規格",detail,getContext());
            }
            else if (v.getId() == R.id.powermenumore) {
                detail = ((Spinner)fragmenthomepage1.findViewById(R.id.powerspinner)).getSelectedItem().toString();
                //產生視窗物件
                alert("電源供應器詳細規格",detail,getContext());

            }
        }
    };

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void alert(String title,String message , Context context) {
        new AlertDialog.Builder(context)
                .setTitle(title)//設定視窗標題
                // .setIcon(R.mipmap.ic_launcher)//設定對話視窗圖示
                .setMessage(message)//設定顯示的文字
                .setPositiveButton("關閉視窗", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //finish(); 關閉整個程式(會造成重啟)
                    }
                })//設定結束的子視窗
                .show();//呈現對話視窗

    }
}

/***************************************************************************************************************/
class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder> {
    private Context context;
    private List<Integer> datas;

    /**
     *item裡面所有的點擊事件
     */
    private OnItemClickListener onItemClickListener;
    /**
     * 瀑布流时的item随机高度
     private List<Integer> heights = new ArrayList<>();
     */


    /**
     *
     * 不同的类型设置item不同的高度
     *
     * @param type
     */

    /**
     * 瀑布流所產生的高度 以亂數產生長度
     */

    private int type = 0;
    public RvAdapter(Context context, List<Integer> datas) {
        this.context = context;
        this.datas = datas;
        for (int i : datas) {
            int height = (int) (Math.random() * 100 + 300);
            // heights.add(height);
        }
    }

    public void setType(int type) {
        this.type = type;
    }

    /**
     * 设置点击事件
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(contentView);
        return viewHolder;
    }
    /**
     *
     * */
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        RecyclerView.LayoutParams layoutParams;
        if (type == 0) {
            layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        /**
         *
         * 下面是設定橫排的方向 上面是直排方向
         * */
        else {
            layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        //這設置其他版規的方向設定
        // else {
        //   layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heights.get(position));
        //   layoutParams.setMargins(2, 2, 2, 2);
        // }
        holder.itemView.setLayoutParams(layoutParams);
        holder.imageView.setImageResource(datas.get(position));
            switch (position) {
                case 0:
                    holder.tv.setText("Menu");
                    holder.tv.setTextColor(Color.parseColor("#FFFFFF"));
                    break;
                case 1:
                    holder.tv.setText("cpu系列");
                    holder.tv.setTextColor(Color.parseColor("#FFFFFF"));
                    break;
                case 2:
                    holder.tv.setText("硬碟系列");
                    holder.tv.setTextColor(Color.parseColor("#FFFFFF"));
                    break;
                case 3:
                    holder.tv.setText("主機板");
                    holder.tv.setTextColor(Color.parseColor("#FFFFFF"));
                    break;
                case 4:
                    holder.tv.setText("記憶體");
                    holder.tv.setTextColor(Color.parseColor("#FFFFFF"));
                    break;
                case 5:
                    holder.tv.setText("顯卡系列");
                    holder.tv.setTextColor(Color.parseColor("#FFFFFF"));
                    break;
                case 6:
                    holder.tv.setText("Power");
                    holder.tv.setTextColor(Color.parseColor("#FFFFFF"));
                    break;
            }
        /**设置item点击监听**/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClickListener(position, datas.get(position));
                }
            }
        });
    }

    /**
     * 设置item监听的接口
     */
    public interface OnItemClickListener {
        void onItemClickListener(int position, Integer data);

    }
    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    /**
     * 用于缓存的ViewHolder
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
/*************************************************************************************************************/
class RecycleViewDivider extends RecyclerView.ItemDecoration {

    private Paint mPaint;
    private Drawable mDivider;
    private int mDividerHeight = 2;//分割线高度，默认为1px
    private int mOrientation;//列表的方向：LinearLayoutManager.VERTICAL或LinearLayoutManager.HORIZONTAL
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    /**
     * 默认分割线：高度为2px，颜色为灰色
     *
     * @param context
     * @param orientation 列表方向
     */
    public RecycleViewDivider(Context context, int orientation) {
        if (orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL) {
            throw new IllegalArgumentException("请输入正确的参数！");
        }
        mOrientation = orientation;

        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
    }

    /**
     * 自定义分割线
     *
     * @param context
     * @param orientation 列表方向
     * @param drawableId  分割线图片
     */
    public RecycleViewDivider(Context context, int orientation, int drawableId) {
        this(context, orientation);
        mDivider = ContextCompat.getDrawable(context, drawableId);
        mDividerHeight = mDivider.getIntrinsicHeight();
    }

    /**
     * 自定义分割线
     *
     * @param context
     * @param orientation   列表方向
     * @param dividerHeight 分割线高度
     * @param dividerColor  分割线颜色
     */
    public RecycleViewDivider(Context context, int orientation, int dividerHeight, int dividerColor) {
        this(context, orientation);
        mDividerHeight = dividerHeight;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
    }

    //获取分割线尺寸
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 0, 0, mDividerHeight);
    }

    //绘制分割线
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    //绘制横向 item 分割线
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getMeasuredWidth() - parent.getPaddingRight();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + layoutParams.bottomMargin;
            final int bottom = top + mDividerHeight;
            if (mDivider != null) {
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            }
            if (mPaint != null) {
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }

    //绘制纵向 item 分割线
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + layoutParams.rightMargin;
            final int right = left + mDividerHeight;
            if (mDivider != null) {
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            }
            if (mPaint != null) {
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }
}
/*************************************************************************************************************/
/**
 * /*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * This class is from the v7 samples of the Android SDK. It's not by me!
 * <p/>
 * See the license above for details.
 */
class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;


    private Drawable mDivider;

    private int mOrientation;

    public DividerItemDecoration(Context context, int orientation) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
     //   Log.v("recyclerview - itemdecoration", "onDraw()");
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        //c.drawColor(Color.parseColor("#FFFFFF"));
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView v = new RecyclerView(
                    parent.getContext());
            //child.setBackgroundColor(Color.parseColor("#FDC3FD"));
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, bottom, right, top);
            mDivider.draw(c);
        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();
        //c.drawColor(Color.parseColor("#FFFFFF"));
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            //child.setBackgroundColor(Color.parseColor("#FDC3FD"));
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, bottom, right, top);
            mDivider.draw(c);

        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition,
                               RecyclerView parent) {
        if (mOrientation == VERTICAL_LIST) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }
    }
}
class ClassListItems
{
    public String Seriesmodel;
    public String Prices;

    public ClassListItems(String Seriesmodel, String Prices)
    {
        super();
        this.Seriesmodel = Seriesmodel;
        this.Prices = Prices;
    }

    public String getSeriesmodel() {
        return Seriesmodel;
    }

    public String getPrices() {
        return Prices;
    }
}

class MyAppAdapter extends BaseAdapter         //has a class viewholder which holds
{
    private LayoutInflater myInflater;
    Fragmenthomepage fragmenthomepage1 = new Fragmenthomepage();

    public class ViewHolder
    {
        TextView textSeriesModel;
        TextView textPrices;
        ImageView imageView;
    }

    public List<ClassListItems> parkingList;

    public Context context;
    ArrayList<ClassListItems> arraylist;
    int type;
    MyAppAdapter(List<ClassListItems> apps, Context context,int a)
    {
        myInflater = LayoutInflater.from(context);
        this.parkingList = apps;
        this.context = context;
        arraylist = new ArrayList<ClassListItems>();
        arraylist.addAll(parkingList);
        type=a;
    }

    @Override
    public int getCount() {
        return parkingList.size();
    }

    @Override
    public Object getItem(int position) {
        return  parkingList.get(position);
    }

    @Override
    public long getItemId(int position) {return parkingList.indexOf(getItem(position));
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) // inflating the layout and initializing widgets
    {

        View rowView = convertView;
        ViewHolder viewHolder= null;
        if (rowView == null&&type==0)
        {
            //LayoutInflater inflater = fragmenthomepage1.getLayoutInflater();
            //LayoutInflater inflater = getLayoutInflater();
            rowView = myInflater.inflate(R.layout.list_content, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textSeriesModel = (TextView) rowView.findViewById(R.id.textSeriesModel);
            viewHolder.textPrices = (TextView) rowView.findViewById(R.id.textPrices);
            viewHolder.imageView = (ImageView) rowView.findViewById(R.id.imageView);
            rowView.setTag(viewHolder);
        }
        else if(rowView == null&&type==1){
            rowView = myInflater.inflate(R.layout.list_content2, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textSeriesModel = (TextView) rowView.findViewById(R.id.textSeriesModel);
            viewHolder.textPrices = (TextView) rowView.findViewById(R.id.textPrices);
            viewHolder.imageView = (ImageView) rowView.findViewById(R.id.imageView);
            rowView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
      /*  if(type == 1) {
            ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) rowView.getLayoutParams();
            lp.height = 300;
            rowView.setLayoutParams(lp);
        }*/

        // here setting up names and images
        viewHolder.textPrices.setTextColor(Color.parseColor("#00FA9A"));
        viewHolder.textSeriesModel.setTextColor(Color.parseColor("#00FA9A"));
        viewHolder.textSeriesModel.setText(parkingList.get(position).getSeriesmodel()+"");
        viewHolder.textPrices.setText(parkingList.get(position).getPrices()+"");
        //Picasso.with(context).load("http://"+parkingList.get(position).getMbPrices()).into(viewHolder.imageView);
        return rowView;
    }
}
class SQLcomn{
    ProgressDialog progress;
    Context context;
    public SQLcomn(String query , String sqlPrice, int up , int low , final ArrayList<ClassListItems> boxname , ListView listview , int type, final Context context) {
        {
            this.context=context;
            final Fragmenthomepage fragmenthomepage = new Fragmenthomepage();
            String msg;
            Boolean success;
            CnMyDbClass cnClass = new CnMyDbClass();
            try {
                Connection conn = cnClass.CONN(); //宣告conn物件屬於Connection(連線)的物件並呼叫連線cnClass裡的CONN的方法Connection Object
                if (conn == null)//如果conn物件為空值
                {
                    msg = "Not conn";//預設的訊息就會被Not conn取代並在程序結束時顯示
                    success = false;//並且將success設為假值
                } else {//如果conn不為空值則執行
                    // Change below query according to your own database.
                    Statement stmt = conn.createStatement();//使用JAVA內預設連接SQL的聲明宣告stmt等於連線的聲明
                    {
                        //String query = mbselect + " Order by mbPrices asc";//設定查詢SQL語法
/*+"'In (SELECT * FROM mb join mf on mb.mfId = mf.mfId WHERE mbPrice Between "
                                  +mblow+" AND " +mbup+")"*/
                        ResultSet rs = stmt.executeQuery(query);//宣告rs為JAVA內預設連接SQL的ResultSet(結果集)，並使用stmt聲明連線定執行設定的SQL語法，再將執行結果儲存於rs結果集內
                        if (rs != null) //如果執行後的結果即不是空值就執行 if resultset not null, I add items to itemArraylist using class created
                        {
                            while (rs.next())//當結果集仍有下一筆資料的時候執行，當執行到最後一筆資料後的結束點時會自動判斷沒有下一筆，就會自動結束回圈
                            {
                                if (type == 7) {
                                    try {
                                        boxname.add(new ClassListItems((rs.getString("mbSeriesmodel")+" "+rs.getString("mbPrices")+"\n"+rs.getString("cSeriesmodel")+" "+rs.getString("cPrices")+"\n"+rs.getString("rModel")+" "+rs.getString("rPrices")+"\n"+rs.getString("vSeriesmodel")+" "+rs.getString("vPrices")+"\n"+rs.getString("hModel")+" "+rs.getString("hPrices")+"\n"+rs.getString("prSeriesmodel")+" "+rs.getString("prPrices")+"\n"),rs.getString("tprices")));
                                    }catch (Exception e){
                                        fragmenthomepage.alert("defail","fk", context);
                                    }
                                }
                                try {
                                    int i = Integer.valueOf(rs.getString(sqlPrice));
                                    if (i >= low && i <= up) {
                                        if (type == 1) {
                                            boxname.add(new ClassListItems((rs.getString("mfName") + " " + rs.getString("mbSeriesmodel")), "$" + rs.getString("mbPrices")));
                                        } else if (type == 2) {
                                            boxname.add(new ClassListItems((rs.getString("cSeriesmodel") +"\n"+ "Pin:" + rs.getString("cPin")) + " " + rs.getString("cCore") + "核" + rs.getString("cThread") + "緒", "$" + rs.getString("cPrices")));
                                        } else if (type == 3) {
                                            boxname.add(new ClassListItems((rs.getString("mfName") + " DDR" + rs.getString("rDDR") + "\n" + rs.getString("rCapacity") + "G " + rs.getString("rMHz") + " MHz"), "$" + rs.getString("rPrices")));
                                        } else if (type == 4) {
                                            boxname.add(new ClassListItems((rs.getString("mfName") + "\n" + rs.getString("vSeriesmodel")), "$" + rs.getString("vPrices")));
                                        } else if (type == 5) {
                                            boxname.add(new ClassListItems((rs.getString("mfName") + "\n" + rs.getString("prSeriesmodel")), "$" + rs.getString("prPrices")));
                                        } else if (type == 6) {
                                            boxname.add(new ClassListItems(rs.getString("mfName") + " " + rs.getString("hModel") + "\n" + rs.getString("hType") + " " + rs.getString("hCapacity") + "G", "$" + rs.getString("hPrices")));
                                        }
                                    }


                                    //將查詢結果使用符合該宣告且定義好的格式新增至itemArrayLists串列內
                                } catch (Exception ex) {//若在此處的項目新增等有錯誤，則會由此抓取錯誤

                                    ex.printStackTrace();//列出抓取到的錯誤，利用堆疊追蹤的方法，取得例外發生的根源
                                }
                            }
                            final MyAppAdapter myAppAdapter;

                            if(type==7) {
                                if(boxname.size()!=0) {
                                    myAppAdapter = new MyAppAdapter(boxname, context, 1);
                                    listview.setAdapter(myAppAdapter);
                                }
                                else fragmenthomepage.alert("組合結果", "很抱歉 無此組合", context);

                            }
                            else{ myAppAdapter = new MyAppAdapter(boxname, context, 0);listview.setAdapter(myAppAdapter);
                            //myAppAdapter = new MyAppAdapter(mbboxname, getContext());

                            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    //產生視窗物件
                                    String detail = boxname.get(position).getSeriesmodel() + " " + boxname.get(position).getPrices();
                                    fragmenthomepage.alert("產品詳細規格", detail, context);
                                }
                            });}
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
};

class cellphptest extends AsyncTask<String, String, String> {
    BufferedReader reader = null;
    HttpURLConnection conn;
    ProgressDialog progress;
    Context context;
    String path = " ";
    String data = "false";
    ArrayList<ClassListItems> boxname;
    ListView listView;
    String type;

    cellphptest(Context context, String path, ArrayList<ClassListItems> boxname, ListView listView,String type) {
        super();
        this.context = context;
        this.path = path;
        this.boxname = boxname;
        this.listView = listView;
        this.type=type;
    }

    @Override
    protected void onPreExecute() {
        //progress = ProgressDialog.show(context, "Synchronising","載入中...請稍候", true);
    }

    @Override
    protected String doInBackground(String... strings) {
        try {

            URL url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("test-header", "post-header-value");
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
        JSONObject jsonObject = null;
        //progress.dismiss();
       // progress.dismiss();
        try {
            jsonObject = new JSONObject(msg);
            if (type == "cpu") {
                JSONArray jsonArray = jsonObject.getJSONArray("cpu");
                int length = jsonObject.getJSONArray("cpu").length();

                for (int j = 0; j < length; j++) {
                    JSONObject ob = (JSONObject) jsonArray.get(j);
                    String Name = ob.getString("Name");
                    String CName = ob.getString("CName");
                    String Core = ob.getString("Core");
                    String thread = ob.getString("Thread");
                    String Seriesmodel = ob.getString("Seriesmodel");
                    String Prices = ob.getString("Prices");
                    boxname.add(new ClassListItems(Seriesmodel + " " + Core + "核" + thread + "緒 ", "$" + Prices));
                }
            } else if (type == "mb") {
                JSONArray jsonArray = jsonObject.getJSONArray("mb");
                int length = jsonObject.getJSONArray("mb").length();

                for (int j = 0; j < length; j++) {
                    JSONObject ob = (JSONObject) jsonArray.get(j);
                    String Name = ob.getString("Name");
                    String CName = ob.getString("CName");
                    String Seriesmodel = ob.getString("Seriesmodel");
                    String Prices = ob.getString("Prices");
                    boxname.add(new ClassListItems(Name + " " + Seriesmodel, "$" + Prices));
                }
            } else if (type == "ram") {
                JSONArray jsonArray = jsonObject.getJSONArray("ram");
                int length = jsonObject.getJSONArray("ram").length();

                for (int j = 0; j < length; j++) {
                    JSONObject ob = (JSONObject) jsonArray.get(j);
                    String Name = ob.getString("Name");
                    String CName = ob.getString("CName");
                    String Seriesmodel = ob.getString("Seriesmodel");
                    String Capacity = ob.getString("Capacity");
                    String DDR = ob.getString("DDR");
                    String MHz = ob.getString("MHz");
                    String Prices = ob.getString("Prices");
                    boxname.add(new ClassListItems(Name + " "+ Capacity +"G DDR"+DDR+" "+MHz+"MHz ", "$" + Prices));
                }
            } else if (type == "vga") {
                JSONArray jsonArray = jsonObject.getJSONArray("vga");
                int length = jsonObject.getJSONArray("vga").length();
                String c = "";
                String price="";
                for (int j = 0; j < length; j++) {
                    JSONObject ob = (JSONObject) jsonArray.get(j);
                    if (c != ob.getString("Seriesmodel" )&& price!= ob.getString("Prices")) {
                        c=ob.getString("Seriesmodel");
                        price = ob.getString("Prices");
                        String Name = ob.getString("Name");
                        String CName = ob.getString("CName");
                        String Seriesmodel = ob.getString("Seriesmodel");
                        String Capacity = ob.getString("Capacity");
                        String Prices = ob.getString("Prices");
                        String Voltage = ob.getString("Voltage");
                        boxname.add(new ClassListItems(Name + " " + Seriesmodel + " " + Voltage + "W ", "$" + Prices));
                    }else{
                        c=ob.getString("Seriesmodel");
                        price = ob.getString("Prices");
                    }
                }
            } else if (type == "power") {
                JSONArray jsonArray = jsonObject.getJSONArray("power");
                int length = jsonObject.getJSONArray("power").length();

                for (int j = 0; j < length; j++) {
                    JSONObject ob = (JSONObject) jsonArray.get(j);
                    String Name = ob.getString("Name");
                    String CName = ob.getString("CName");
                    String Seriesmodel = ob.getString("Seriesmodel");
                    String Power= ob.getString("Power");
                    String Prices = ob.getString("Prices");
                    boxname.add(new ClassListItems(Name+" "+Seriesmodel + " " +Power+"W ", "$" + Prices));
                }
            } else if (type == "HHDSSD") {
                JSONArray jsonArray = jsonObject.getJSONArray("hd");
                int length = jsonObject.getJSONArray("hd").length();

                for (int j = 0; j < length; j++) {
                    JSONObject ob = (JSONObject) jsonArray.get(j);
                    String Name = ob.getString("Name");
                    String CName = ob.getString("CName");
                    String Type = ob.getString("Type");
                    String Capacity = ob.getString("Capacity");
                    String Seriesmodel = ob.getString("Seriesmodel");
                    String Prices = ob.getString("Prices");
                    boxname.add(new ClassListItems(Type+" "+Name+" "+Seriesmodel + " " +Capacity+"G ", "$" + Prices));
                }
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }

        final Fragmenthomepage fragmenthomepage = new Fragmenthomepage();
        MyAppAdapter myAppAdapter = new MyAppAdapter(boxname, context, 0);
        listView.setAdapter(myAppAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //產生視窗物件
                String detail = boxname.get(position).getSeriesmodel() + " " + boxname.get(position).getPrices();
                fragmenthomepage.alert("產品詳細規格", detail, context);
            }

        });
    }
}