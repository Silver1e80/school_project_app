package com.example.dragon.test2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashSet;


public class recommendationfragment extends Fragment {
    View recommendationfragment1;
    Fragmenthomepage fragmenthomepage1=new Fragmenthomepage();
    final ArrayList<ClassListItems> boxname = new ArrayList<>();
    LinkedHashSet<String> mb;
    LinkedHashSet<String> ram;
    LinkedHashSet<String> vga;
    LinkedHashSet<String> power;
    LinkedHashSet<String> hd;
    LinkedHashSet<String> cpu;
    String computertype ="1030";
    String priceorder="desc";
    JSONObject jsonObject;

    JSONArray jsonArray;
    ArrayAdapter ramspaceadpater , ssdhddtypeadpater,ssdhddspaceadpater,vgaleveladpater, cpuleveladpater;
    String space [] = new String[]{"普通","高","高超"};
    String cpulevel [] = new String[]{"低","中低","中","中高","高","超高"};
    String ssdhhdtype [] = new String[]{"快速","基本功能"};
    String ssdhhdspace [] = new String[]{"128","256","512","1024","2048"};
    String vgalevel[]= new String[]{"710","730","1030","1050","1050Ti","1060","1070","1070ti","1080","1080ti"};
    String clinetssdtype = "SSD";
    String clinetssdspace= "128";
    String vgalevelchoice= "710";
    ArrayAdapter cpu_alladapter;
    LinkedHashSet<String> cpuseting;
    int spacetosql=4;
    int cpuanaly=0;
    public recommendationfragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        recommendationfragment1 = inflater.inflate(R.layout.fragment_recommendationfragment, container, false);
        init();
        return  recommendationfragment1;
    }

    private void init() {
        mb=new LinkedHashSet();
        vga =new LinkedHashSet();
        power=new LinkedHashSet();
        cpu=new LinkedHashSet();
        ram=new LinkedHashSet();
        hd=new LinkedHashSet();


        cpuseting=new LinkedHashSet();
        jsonObject=new JSONObject();


        /************checkbox**************/
        CheckBox cpuIntelchoice = (CheckBox)recommendationfragment1.findViewById(R.id.cpuIntelchoice);
        cpuIntelchoice.setOnCheckedChangeListener(check);
        CheckBox cpuamdchoice = (CheckBox)recommendationfragment1.findViewById(R.id.cpuamdchoice);
        cpuamdchoice.setOnCheckedChangeListener(check);
        RadioGroup computertype = (RadioGroup)recommendationfragment1.findViewById(R.id.computertype);
        computertype.setOnCheckedChangeListener(computertypelisten);

        CheckBox mbasuschoice = (CheckBox)recommendationfragment1.findViewById(R.id.mbasuschoice);
        mbasuschoice.setOnCheckedChangeListener(check);
        CheckBox mbgigabytechoice = (CheckBox)recommendationfragment1.findViewById(R.id.mbgigabytechoice);
        mbgigabytechoice.setOnCheckedChangeListener(check);
        CheckBox mbmsichoice = (CheckBox)recommendationfragment1.findViewById(R.id.mbmsichoice);
        mbmsichoice.setOnCheckedChangeListener(check);
        CheckBox mbasrockchoice = (CheckBox)recommendationfragment1.findViewById(R.id.mbasrockchoice);
        mbasrockchoice.setOnCheckedChangeListener(check);

        CheckBox ramAdatachoice = (CheckBox)recommendationfragment1.findViewById(R.id.ramAdatachoice);
        ramAdatachoice.setOnCheckedChangeListener(check);
        CheckBox ramgskillchoice = (CheckBox)recommendationfragment1.findViewById(R.id.ramgskillchoice);
        ramgskillchoice.setOnCheckedChangeListener(check);
        CheckBox ramKingstonchoice = (CheckBox)recommendationfragment1.findViewById(R.id.ramKingstonchoice);
        ramKingstonchoice.setOnCheckedChangeListener(check);
        CheckBox ramMicronchoice = (CheckBox)recommendationfragment1.findViewById(R.id.ramMicronchoice);
        ramMicronchoice.setOnCheckedChangeListener(check);
        CheckBox ramTranscendchoice = (CheckBox)recommendationfragment1.findViewById(R.id.ramTranscendchoice);
        ramTranscendchoice.setOnCheckedChangeListener(check);
        CheckBox ramUMAXCHOICE = (CheckBox)recommendationfragment1.findViewById(R.id.ramUMAXchoice);
        ramUMAXCHOICE.setOnCheckedChangeListener(check);

        CheckBox vgaASUSCHOICE = (CheckBox)recommendationfragment1.findViewById(R.id.vgaASUSchoice);
        vgaASUSCHOICE.setOnCheckedChangeListener(check);
        CheckBox vgaMSICHOICE  = (CheckBox)recommendationfragment1.findViewById(R.id.vgaMSIchoice);
        vgaMSICHOICE.setOnCheckedChangeListener(check);
        CheckBox vgaEVGA = (CheckBox)recommendationfragment1.findViewById(R.id.vgaEVGAchoice);
        vgaEVGA.setOnCheckedChangeListener(check);
        CheckBox vgaGalaxy = (CheckBox)recommendationfragment1.findViewById(R.id.vgaGalaxychoice);
        vgaGalaxy.setOnCheckedChangeListener(check);
        CheckBox vgaGIGABYTECHOICE = (CheckBox)recommendationfragment1.findViewById(R.id.vgaGIGABYTEchoice);
        vgaGIGABYTECHOICE.setOnCheckedChangeListener(check);
        CheckBox vgaINNO3D = (CheckBox)recommendationfragment1.findViewById(R.id.vgaINNO3Dchoice);
        vgaINNO3D.setOnCheckedChangeListener(check);
        CheckBox vgaLEADTEK = (CheckBox)recommendationfragment1.findViewById(R.id.vgaLEADTEKchoice);
        vgaLEADTEK.setOnCheckedChangeListener(check);


        CheckBox powerDelta = (CheckBox)recommendationfragment1.findViewById(R.id.powerDeltachoice);
        powerDelta.setOnCheckedChangeListener(check);
        CheckBox powerFSP = (CheckBox)recommendationfragment1.findViewById(R.id.powerFSPchoice);
        powerFSP.setOnCheckedChangeListener(check);
        CheckBox powerAntec = (CheckBox)recommendationfragment1.findViewById(R.id.powerAntecchoice);
        powerAntec.setOnCheckedChangeListener(check);
        CheckBox powerCorsair = (CheckBox)recommendationfragment1.findViewById(R.id.powerCorsairchoice);
        powerCorsair.setOnCheckedChangeListener(check);
        CheckBox powerBfriend = (CheckBox)recommendationfragment1.findViewById(R.id.powerBfriendchoice);
        powerBfriend.setOnCheckedChangeListener(check);
        CheckBox powerGIGABYTECHOICE = (CheckBox)recommendationfragment1.findViewById(R.id.powerGIGABYTEchoice);
        powerGIGABYTECHOICE.setOnCheckedChangeListener(check);
        CheckBox powerSAMA = (CheckBox)recommendationfragment1.findViewById(R.id.powerSAMAchoice);
        powerSAMA.setOnCheckedChangeListener(check);
        CheckBox powerAndyson = (CheckBox)recommendationfragment1.findViewById(R.id.powerAndysonchoice);
        powerAndyson.setOnCheckedChangeListener(check);
        CheckBox powerXFX = (CheckBox)recommendationfragment1.findViewById(R.id.powerXFXchoice);
        powerXFX.setOnCheckedChangeListener(check);
        CheckBox powerThermaltake = (CheckBox)recommendationfragment1.findViewById(R.id.powerThermaltakechoice);
        powerThermaltake.setOnCheckedChangeListener(check);
        CheckBox powerCyberSLIM = (CheckBox)recommendationfragment1.findViewById(R.id.powerCyberSLIMchioice);
        powerCyberSLIM.setOnCheckedChangeListener(check);
        CheckBox Superflower = (CheckBox)recommendationfragment1.findViewById(R.id.powerSuperflowerchioice);
        Superflower.setOnCheckedChangeListener(check);
        CheckBox powerZUMAXCHOICE = (CheckBox)recommendationfragment1.findViewById(R.id.powerZUMAXchioice);
        powerZUMAXCHOICE.setOnCheckedChangeListener(check);
        CheckBox powerCoolerMaster = (CheckBox)recommendationfragment1.findViewById(R.id.powerCoolerMasterchoice);
        powerCoolerMaster.setOnCheckedChangeListener(check);
        CheckBox powerSeventeam = (CheckBox)recommendationfragment1.findViewById(R.id.powerSeventeamchoice);
        powerSeventeam.setOnCheckedChangeListener(check);
        CheckBox powerSilverstone = (CheckBox)recommendationfragment1.findViewById(R.id.powerSilverstonechoice);
        powerSilverstone.setOnCheckedChangeListener(check);
        CheckBox powerBit = (CheckBox)recommendationfragment1.findViewById(R.id.powerBitFenixchoice);
        powerBit.setOnCheckedChangeListener(check);
        CheckBox powerSeasonic = (CheckBox)recommendationfragment1.findViewById(R.id.powerSeasonicchoice);
        powerSeasonic.setOnCheckedChangeListener(check);
        CheckBox powerSEED = (CheckBox)recommendationfragment1.findViewById(R.id.powerSEEDchoice);
        powerSEED.setOnCheckedChangeListener(check);
        CheckBox powerEnermax = (CheckBox)recommendationfragment1.findViewById(R.id.powerEnermaxchoice);
        powerEnermax.setOnCheckedChangeListener(check);
        CheckBox powerEVGA = (CheckBox)recommendationfragment1.findViewById(R.id.powerEVGAchoice);
        powerEVGA.setOnCheckedChangeListener(check);


        CheckBox hdAdatachoice = (CheckBox)recommendationfragment1.findViewById(R.id.hdAdatachoice);
        hdAdatachoice.setOnCheckedChangeListener(check);
        CheckBox hdSeagate = (CheckBox)recommendationfragment1.findViewById(R.id.hdSeagatechoice);
        hdSeagate.setOnCheckedChangeListener(check);
        CheckBox hdToshiba = (CheckBox)recommendationfragment1.findViewById(R.id.hdToshibachoice);
        hdToshiba.setOnCheckedChangeListener(check);
        CheckBox hdWD = (CheckBox)recommendationfragment1.findViewById(R.id.hdWDchoice);
        hdWD.setOnCheckedChangeListener(check);
        CheckBox hdHGST = (CheckBox)recommendationfragment1.findViewById(R.id.hdHGSTchoice);
        hdHGST.setOnCheckedChangeListener(check);
        CheckBox hdSamsung = (CheckBox)recommendationfragment1.findViewById(R.id.hdSamsungchoice);
        hdSamsung.setOnCheckedChangeListener(check);
        CheckBox hdUMAXCHOICE = (CheckBox)recommendationfragment1.findViewById(R.id.hdUMAXchoice);
        hdUMAXCHOICE.setOnCheckedChangeListener(check);
        CheckBox hdGalaxy = (CheckBox)recommendationfragment1.findViewById(R.id.hdGalaxychoice);
        hdGalaxy.setOnCheckedChangeListener(check);
        CheckBox hdMicronchoice = (CheckBox)recommendationfragment1.findViewById(R.id.hdMicronchoice);
        hdMicronchoice.setOnCheckedChangeListener(check);
        CheckBox hdIntelchoice = (CheckBox)recommendationfragment1.findViewById(R.id.hdIntelchoice);
        hdIntelchoice.setOnCheckedChangeListener(check);
        CheckBox hdHikvision = (CheckBox)recommendationfragment1.findViewById(R.id.hdHikvisionchoice);
        hdHikvision.setOnCheckedChangeListener(check);
        CheckBox hdPioneer = (CheckBox)recommendationfragment1.findViewById(R.id.hdPioneerchoice);
        hdPioneer.setOnCheckedChangeListener(check);
        CheckBox hdKingstonchoice = (CheckBox)recommendationfragment1.findViewById(R.id.hdKingstonchoice);
        hdKingstonchoice.setOnCheckedChangeListener(check);
        CheckBox hdSanDisk = (CheckBox)recommendationfragment1.findViewById(R.id.hdSanDiskchoice);
        hdSanDisk.setOnCheckedChangeListener(check);
        CheckBox hdTranscendchoice = (CheckBox)recommendationfragment1.findViewById(R.id.hdTranscendchoice);
        hdTranscendchoice.setOnCheckedChangeListener(check);
        CheckBox hdLiteon = (CheckBox)recommendationfragment1.findViewById(R.id.hdLiteonchoice);
        hdLiteon.setOnCheckedChangeListener(check);
        CheckBox hdPlextor = (CheckBox)recommendationfragment1.findViewById(R.id.hdPlextorchoice);
        hdPlextor.setOnCheckedChangeListener(check);
        Button goresult = (Button)recommendationfragment1.findViewById(R.id.goresult);
        goresult.setOnClickListener(select);
        Button clear = (Button)recommendationfragment1.findViewById(R.id.clear);
        clear.setOnClickListener(clear1);
        Button toset = (Button)recommendationfragment1.findViewById(R.id.toset);
        toset.setOnClickListener(setview);
        Button backresult = (Button)recommendationfragment1.findViewById(R.id.backresult);
        backresult.setOnClickListener(setview);

        Button changeLv2 = (Button)recommendationfragment1.findViewById(R.id.lv2);
        changeLv2.setOnClickListener(change);
        Button changeLv1 = (Button)recommendationfragment1.findViewById(R.id.lv1);
        changeLv1.setOnClickListener(change);

        TextView aboutrecommend = (TextView)recommendationfragment1.findViewById(R.id.aboutrecommend);
        aboutrecommend.setClickable(true);
        aboutrecommend.setOnClickListener(about);

        Button pricetype = (Button)recommendationfragment1.findViewById(R.id.pricebutton);
        pricetype.setOnClickListener(typechange);
        Button gametype = (Button)recommendationfragment1.findViewById(R.id.gamebutton);
        gametype.setOnClickListener(typechange);

        final Spinner ramspinner = (Spinner) recommendationfragment1.findViewById(R.id.ramspace);
        ramspaceadpater = new ArrayAdapter(getContext(), R.layout.myspinnertextstyle, space);
        ramspaceadpater.setDropDownViewResource(R.layout.myspinnertextstyle);
        ramspinner.setAdapter(ramspaceadpater);
        ramspinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if(arg0.getSelectedItemPosition()==0){
                    spacetosql = 4;
                }
                else if(arg0.getSelectedItemPosition()==1){
                    spacetosql = 8;
                }
                else{
                    spacetosql = 16;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        final Spinner ssdhddspinner = (Spinner) recommendationfragment1.findViewById(R.id.ssdhddtype);
        ssdhddtypeadpater = new ArrayAdapter(getContext(), R.layout.myspinnertextstyle, ssdhhdtype);
        ssdhddtypeadpater.setDropDownViewResource(R.layout.myspinnertextstyle);
        ssdhddspinner.setAdapter(ssdhddtypeadpater);
        ssdhddspinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if(arg0.getSelectedItemPosition()==0){
                    clinetssdtype= "SSD";
                }
                else clinetssdtype = "HDD";
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        final Spinner ssdhddspacespinner = (Spinner) recommendationfragment1.findViewById(R.id.ssdhddspace);
        ssdhddspaceadpater = new ArrayAdapter(getContext(), R.layout.myspinnertextstyle, ssdhhdspace);
        ssdhddspaceadpater.setDropDownViewResource(R.layout.myspinnertextstyle);
        ssdhddspacespinner.setAdapter(ssdhddspaceadpater);
        ssdhddspacespinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                clinetssdspace= arg0.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        final Spinner vgalv = (Spinner) recommendationfragment1.findViewById(R.id.vgalv);
        vgaleveladpater = new ArrayAdapter(getContext(), R.layout.myspinnertextstyle, vgalevel);
        vgaleveladpater.setDropDownViewResource(R.layout.myspinnertextstyle);
        vgalv.setAdapter(vgaleveladpater);
        vgalv.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                vgalevelchoice = arg0.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        final Spinner cpulv = (Spinner) recommendationfragment1.findViewById(R.id.cpulv);
        cpuleveladpater = new ArrayAdapter(getContext(), R.layout.myspinnertextstyle, cpulevel);
        cpuleveladpater.setDropDownViewResource(R.layout.myspinnertextstyle);
        cpulv.setAdapter(cpuleveladpater);
        cpulv.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                vgalevelchoice = arg0.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        Button pricego = (Button)recommendationfragment1.findViewById(R.id.pricego);
        pricego.setOnClickListener(typetophp);

        Button pricebackresult = (Button)recommendationfragment1.findViewById(R.id.pricebackresult);
        pricebackresult.setOnClickListener(typechange);
    }
    private  Button.OnClickListener typetophp = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            int computerlow ;int computerup ;
            Fragmenthomepage fragmenthomepage = new Fragmenthomepage();
            ListView priceresultListview = (ListView)recommendationfragment1.findViewById(R.id.priceresultListview);
            if(v.getId()==R.id.pricego){
                try {
                    ScrollView pricelv = (ScrollView) recommendationfragment1.findViewById(R.id.priceview);
                    pricelv.setVisibility(View.GONE);
                    LinearLayout priceresultlayout = (LinearLayout)recommendationfragment1.findViewById(R.id.priceresultlayout);
                    priceresultlayout.setVisibility(View.VISIBLE);
                    /*String ip = "http://203.68.252.59/Android/product/change.php?type="+computerordertype+"&G="+spacetosql+"&tPriceslow="+computerlow+"&tPricesup="+computerlow+"&ssdhddtype="+clinetssdtype+"&ssdhddspace="+clinetssdspace;
                    Toast.makeText(getContext(), ip , Toast.LENGTH_LONG).show();
                    Synca phptest2= new Synca(getContext(), ip, boxname, priceresultListview);
                    phptest2.execute("");*/
                }catch (Exception e){
                    fragmenthomepage.alert("格式錯誤","請輸入正確數字",getContext());
                }
            }
        }
    };

    private  Button.OnClickListener typechange = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.pricebutton){
                LinearLayout gamelv = (LinearLayout)recommendationfragment1.findViewById(R.id.gamelv);
                ScrollView pricelv = (ScrollView) recommendationfragment1.findViewById(R.id.priceview);
                if(gamelv.getVisibility()==View.VISIBLE) gamelv.setVisibility(View.GONE);
                pricelv.setVisibility(View.VISIBLE);
                LinearLayout priceresultlayout = (LinearLayout)recommendationfragment1.findViewById(R.id.priceresultlayout);
                priceresultlayout.setVisibility(View.GONE);
            }
            else if(v.getId()==R.id.gamebutton){
                ScrollView pricelv = (ScrollView) recommendationfragment1.findViewById(R.id.priceview);
                LinearLayout gamelv = (LinearLayout)recommendationfragment1.findViewById(R.id.gamelv);
                if(pricelv.getVisibility()==View.VISIBLE) pricelv.setVisibility(View.GONE);
                setLinearlayoutshow(gamelv);
                LinearLayout priceresultlayout = (LinearLayout)recommendationfragment1.findViewById(R.id.priceresultlayout);
                priceresultlayout.setVisibility(View.GONE);
            }
            else if(v.getId()==R.id.pricebackresult){
                ScrollView pricelv = (ScrollView) recommendationfragment1.findViewById(R.id.priceview);
                if(pricelv.getVisibility()==View.VISIBLE) pricelv.setVisibility(View.GONE);
                LinearLayout priceresultlayout = (LinearLayout)recommendationfragment1.findViewById(R.id.priceresultlayout);
                priceresultlayout.setVisibility(View.VISIBLE);
            }
        }
    };
    private  Button.OnClickListener about = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.aboutrecommend){
               fragmenthomepage1.alert("本頁說明","文書機與電競機差異為:文書機將設定為顯卡:ASUS廠商GT730規格,故當選文書機時," +
                       "無法設定顯卡系列",getContext());
            }
        }
    };
    private  RadioGroup.OnCheckedChangeListener order = new RadioGroup.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(checkedId == R.id.radiouptolow) priceorder = "Desc";
            else priceorder = "Asc";
        }
    };
    private  RadioGroup.OnCheckedChangeListener computertypelisten = new RadioGroup.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            LinearLayout vgapriceliner = (LinearLayout)recommendationfragment1.findViewById(R.id.vgapriceliner);
            LinearLayout VGAchoicelayout = (LinearLayout)recommendationfragment1.findViewById(R.id.VGAchoicelayout);
            LinearLayout VGAchoicelayout2 = (LinearLayout)recommendationfragment1.findViewById(R.id.VGAchoicelayout2);
            TextView vgatext= (TextView)recommendationfragment1.findViewById(R.id.vgatext);
            View vgatextview1 = (View)recommendationfragment1.findViewById(R.id.vgatextview1);
            View vgatextview2 = (View)recommendationfragment1.findViewById(R.id.vgatextview2);
            TextView vgapricetext = (TextView)recommendationfragment1.findViewById(R.id.vgapricetext);
            View vgaview1 = (View)recommendationfragment1.findViewById(R.id.vgaview1);
            View vgaview2 = (View)recommendationfragment1.findViewById(R.id.vgaview2);
            EditText vgaselectlow = (EditText)recommendationfragment1.findViewById(R.id.vgaselectlow);
            EditText vgaselectup = (EditText)recommendationfragment1.findViewById(R.id.vgaselectup);
            if (checkedId == R.id.gamecomputer) {
                vgapriceliner.setVisibility(View.VISIBLE);
                vgapricetext.setVisibility(View.VISIBLE);
                vgaview1.setVisibility(View.VISIBLE);
                vgaview2.setVisibility(View.VISIBLE);
                vgaselectlow.setFocusableInTouchMode(true);
                vgaselectlow.setFocusable(true);
                vgaselectlow.requestFocus();
                vgaselectup.setFocusableInTouchMode(true);
                vgaselectup.setFocusable(true);
                vgaselectup.requestFocus();
                vgatextview1.setVisibility(View.VISIBLE);
                vgatextview2.setVisibility(View.VISIBLE);
                VGAchoicelayout.setVisibility(View.VISIBLE);
                VGAchoicelayout2.setVisibility(View.VISIBLE);
                vgatext.setVisibility(View.VISIBLE);
                removevga();
                computertype = "1030";
            } else {
                vgaview1.setVisibility(View.GONE);
                vgaview2.setVisibility(View.GONE);
                vgapriceliner.setVisibility(View.GONE);
                vgapricetext.setVisibility(View.GONE);
                vgaselectlow.setFocusable(false);
                vgaselectlow.setFocusableInTouchMode(false);
                vgaselectup.setFocusable(false);
                vgaselectup.setFocusableInTouchMode(false);
                vgatext.setVisibility(View.GONE);
                vgatextview1.setVisibility(View.GONE);
                vgatextview2.setVisibility(View.GONE);
                VGAchoicelayout.setVisibility(View.GONE);
                VGAchoicelayout2.setVisibility(View.GONE);
                removevga();
                vga.add("ASUS");
                computertype = "710";
            }
        }
    };
    private  Button.OnClickListener change = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.lv2){
                RelativeLayout resultrelativelayout = (RelativeLayout) recommendationfragment1.findViewById(R.id.resultrelativelayout);
                resultrelativelayout.setVisibility(View.GONE);
                ScrollView ifview = (ScrollView)recommendationfragment1.findViewById(R.id.ifview);
                ifview.setVisibility(View.GONE);
                Button changeLv2 = ((Button)recommendationfragment1.findViewById(R.id.lv2));
                changeLv2.setVisibility(View.GONE);

                Button changeLv1 = ((Button)recommendationfragment1.findViewById(R.id.lv1));
                changeLv1.setVisibility(View.VISIBLE);
                LinearLayout lv2layout = (LinearLayout)recommendationfragment1.findViewById(R.id.lv2layout);
                lv2layout.setVisibility(View.VISIBLE);
            }
            else if(v.getId()==R.id.lv1){
                Button changeLv1 = (Button)recommendationfragment1.findViewById(R.id.lv1);
                changeLv1.setVisibility(View.GONE);
                LinearLayout lv2layout = (LinearLayout)recommendationfragment1.findViewById(R.id.lv2layout);
                lv2layout.setVisibility(View.GONE);
                ScrollView ifview = (ScrollView)recommendationfragment1.findViewById(R.id.ifview);
                ifview.setVisibility(View.VISIBLE);
                Button changeLv2 = ((Button)recommendationfragment1.findViewById(R.id.lv2));
                changeLv2.setVisibility(View.VISIBLE);
            }
        }
    };

    private  void setLinearlayoutshow(LinearLayout linearlayout){
        if(linearlayout.getVisibility()==View.VISIBLE){
            linearlayout.setVisibility(View.GONE);
        }
        else linearlayout.setVisibility(View.VISIBLE);
    }
    private Button.OnClickListener setview = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
           if(v.getId()==R.id.toset) {
               RelativeLayout resultrelativelayout = (RelativeLayout) recommendationfragment1.findViewById(R.id.resultrelativelayout);
               ScrollView ifview = (ScrollView) recommendationfragment1.findViewById(R.id.ifview);
               Button backresult = (Button) recommendationfragment1.findViewById(R.id.backresult);
               backresult.setVisibility(View.VISIBLE);
               ifview.setVisibility(View.VISIBLE);
               resultrelativelayout.setVisibility(View.GONE);
           }
           else if(v.getId()==R.id.backresult){
               ScrollView ifview = (ScrollView) recommendationfragment1.findViewById(R.id.ifview);
               RelativeLayout resultrelativelayout = (RelativeLayout) recommendationfragment1.findViewById(R.id.resultrelativelayout);
               ifview.setVisibility(View.GONE);
               resultrelativelayout.setVisibility(View.VISIBLE);
           }
        }
    };

    JSONArray returnselect(LinkedHashSet<String> type,String name) throws JSONException {
        String returnname="";
        String selectname="";
        String linkedname [] = new String[type.size()];
        type.toArray(linkedname);

        JSONObject jsonObject1 = new JSONObject();
        switch(name){
            case "cpu":selectname ="";break;
        }
        for(int i = 0 ; i <linkedname.length;i++){
            if(name=="cpu")
            {
                jsonObject1.put("cpu"+i,linkedname[i]);
            }
            else if(name =="mb")
            {
                jsonObject1.put("mb"+i,linkedname[i]);

            }
            else if(name =="ram")
            {
                jsonObject1.put("ram"+i,linkedname[i]);

            }
            else if(name =="vga")
            {
                jsonObject1.put("vga"+i,linkedname[i]);

            }
            else if(name =="hd")
            {
                jsonObject1.put("hd"+i,linkedname[i]);

            }

            else if(name =="power")
            {
                jsonObject1.put("power"+i,linkedname[i]);
            }
        }
        jsonArray.put(jsonObject1);
        return jsonArray;
    };
    private Button.OnClickListener clear1 = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            CheckBox cpuIntelchoice = (CheckBox)recommendationfragment1.findViewById(R.id.cpuIntelchoice);
            cpuIntelchoice.setChecked(false);
            CheckBox cpuamdchoice = (CheckBox)recommendationfragment1.findViewById(R.id.cpuamdchoice);
            cpuamdchoice.setChecked(false);

            CheckBox mbasuschoice = (CheckBox)recommendationfragment1.findViewById(R.id.mbasuschoice);
            mbasuschoice.setChecked(false);
            CheckBox mbgigabytechoice = (CheckBox)recommendationfragment1.findViewById(R.id.mbgigabytechoice);
            mbgigabytechoice.setChecked(false);
            CheckBox mbmsichoice = (CheckBox)recommendationfragment1.findViewById(R.id.mbmsichoice);
            mbmsichoice.setChecked(false);
            CheckBox mbasrockchoice = (CheckBox)recommendationfragment1.findViewById(R.id.mbasrockchoice);
            mbasrockchoice.setChecked(false);

            CheckBox ramAdatachoice = (CheckBox)recommendationfragment1.findViewById(R.id.ramAdatachoice);
            ramAdatachoice.setChecked(false);
            CheckBox ramgskillchoice = (CheckBox)recommendationfragment1.findViewById(R.id.ramgskillchoice);
            ramgskillchoice.setChecked(false);
            CheckBox ramKingstonchoice = (CheckBox)recommendationfragment1.findViewById(R.id.ramKingstonchoice);
            ramKingstonchoice.setChecked(false);
            CheckBox ramMicronchoice = (CheckBox)recommendationfragment1.findViewById(R.id.ramMicronchoice);
            ramMicronchoice.setChecked(false);
            CheckBox ramTranscendchoice = (CheckBox)recommendationfragment1.findViewById(R.id.ramTranscendchoice);
            ramTranscendchoice.setChecked(false);
            CheckBox ramUMAXCHOICE = (CheckBox)recommendationfragment1.findViewById(R.id.ramUMAXchoice);
            ramUMAXCHOICE.setChecked(false);

            CheckBox vgaASUSCHOICE = (CheckBox)recommendationfragment1.findViewById(R.id.vgaASUSchoice);
            vgaASUSCHOICE.setChecked(false);
            CheckBox vgaMSICHOICE  = (CheckBox)recommendationfragment1.findViewById(R.id.vgaMSIchoice);
            vgaMSICHOICE.setChecked(false);
            CheckBox vgaEVGA = (CheckBox)recommendationfragment1.findViewById(R.id.vgaEVGAchoice);
            vgaEVGA.setChecked(false);
            CheckBox vgaGalaxy = (CheckBox)recommendationfragment1.findViewById(R.id.vgaGalaxychoice);
            vgaGalaxy.setChecked(false);
            CheckBox vgaGIGABYTECHOICE = (CheckBox)recommendationfragment1.findViewById(R.id.vgaGIGABYTEchoice);
            vgaGIGABYTECHOICE.setChecked(false);
            CheckBox vgaINNO3D = (CheckBox)recommendationfragment1.findViewById(R.id.vgaINNO3Dchoice);
            vgaINNO3D.setChecked(false);
            CheckBox vgaLEADTEK = (CheckBox)recommendationfragment1.findViewById(R.id.vgaLEADTEKchoice);
            vgaLEADTEK.setChecked(false);


            CheckBox powerDelta = (CheckBox)recommendationfragment1.findViewById(R.id.powerDeltachoice);
            powerDelta.setChecked(false);
            CheckBox powerFSP = (CheckBox)recommendationfragment1.findViewById(R.id.powerFSPchoice);
            powerFSP.setChecked(false);
            CheckBox powerAntec = (CheckBox)recommendationfragment1.findViewById(R.id.powerAntecchoice);
            powerAntec.setChecked(false);
            CheckBox powerCorsair = (CheckBox)recommendationfragment1.findViewById(R.id.powerCorsairchoice);
            powerCorsair.setChecked(false);
            CheckBox powerBfriend = (CheckBox)recommendationfragment1.findViewById(R.id.powerBfriendchoice);
            powerBfriend.setChecked(false);
            CheckBox powerGIGABYTECHOICE = (CheckBox)recommendationfragment1.findViewById(R.id.powerGIGABYTEchoice);
            powerGIGABYTECHOICE.setChecked(false);
            CheckBox powerSAMA = (CheckBox)recommendationfragment1.findViewById(R.id.powerSAMAchoice);
            powerSAMA.setChecked(false);
            CheckBox powerAndyson = (CheckBox)recommendationfragment1.findViewById(R.id.powerAndysonchoice);
            powerAndyson.setChecked(false);
            CheckBox powerXFX = (CheckBox)recommendationfragment1.findViewById(R.id.powerXFXchoice);
            powerXFX.setChecked(false);
            CheckBox powerThermaltake = (CheckBox)recommendationfragment1.findViewById(R.id.powerThermaltakechoice);
            powerThermaltake.setChecked(false);
            CheckBox powerCyberSLIM = (CheckBox)recommendationfragment1.findViewById(R.id.powerCyberSLIMchioice);
            powerCyberSLIM.setChecked(false);
            CheckBox Superflower = (CheckBox)recommendationfragment1.findViewById(R.id.powerSuperflowerchioice);
            Superflower.setChecked(false);
            CheckBox powerZUMAXCHOICE = (CheckBox)recommendationfragment1.findViewById(R.id.powerZUMAXchioice);
            powerZUMAXCHOICE.setChecked(false);
            CheckBox powerCoolerMaster = (CheckBox)recommendationfragment1.findViewById(R.id.powerCoolerMasterchoice);
            powerCoolerMaster.setChecked(false);
            CheckBox powerSeventeam = (CheckBox)recommendationfragment1.findViewById(R.id.powerSeventeamchoice);
            powerSeventeam.setChecked(false);
            CheckBox powerSilverstone = (CheckBox)recommendationfragment1.findViewById(R.id.powerSilverstonechoice);
            powerSilverstone.setChecked(false);
            CheckBox powerBit = (CheckBox)recommendationfragment1.findViewById(R.id.powerBitFenixchoice);
            powerBit.setChecked(false);
            CheckBox powerSeasonic = (CheckBox)recommendationfragment1.findViewById(R.id.powerSeasonicchoice);
            powerSeasonic.setChecked(false);
            CheckBox powerSEED = (CheckBox)recommendationfragment1.findViewById(R.id.powerSEEDchoice);
            powerSEED.setChecked(false);
            CheckBox powerEnermax = (CheckBox)recommendationfragment1.findViewById(R.id.powerEnermaxchoice);
            powerEnermax.setChecked(false);
            CheckBox powerEVGA = (CheckBox)recommendationfragment1.findViewById(R.id.powerEVGAchoice);
            powerEVGA.setChecked(false);


            CheckBox hdAdatachoice = (CheckBox)recommendationfragment1.findViewById(R.id.hdAdatachoice);
            hdAdatachoice.setChecked(false);
            CheckBox hdSeagate = (CheckBox)recommendationfragment1.findViewById(R.id.hdSeagatechoice);
            hdSeagate.setChecked(false);
            CheckBox hdToshiba = (CheckBox)recommendationfragment1.findViewById(R.id.hdToshibachoice);
            hdToshiba.setChecked(false);
            CheckBox hdWD = (CheckBox)recommendationfragment1.findViewById(R.id.hdWDchoice);
            hdWD.setChecked(false);
            CheckBox hdHGST = (CheckBox)recommendationfragment1.findViewById(R.id.hdHGSTchoice);
            hdHGST.setChecked(false);
            CheckBox hdSamsung = (CheckBox)recommendationfragment1.findViewById(R.id.hdSamsungchoice);
            hdSamsung.setChecked(false);
            CheckBox hdUMAXCHOICE = (CheckBox)recommendationfragment1.findViewById(R.id.hdUMAXchoice);
            hdUMAXCHOICE.setChecked(false);
            CheckBox hdGalaxy = (CheckBox)recommendationfragment1.findViewById(R.id.hdGalaxychoice);
            hdGalaxy.setChecked(false);
            CheckBox hdMicronchoice = (CheckBox)recommendationfragment1.findViewById(R.id.hdMicronchoice);
            hdMicronchoice.setChecked(false);
            CheckBox hdIntelchoice = (CheckBox)recommendationfragment1.findViewById(R.id.hdIntelchoice);
            hdIntelchoice.setChecked(false);
            CheckBox hdHikvision = (CheckBox)recommendationfragment1.findViewById(R.id.hdHikvisionchoice);
            hdHikvision.setChecked(false);
            CheckBox hdPioneer = (CheckBox)recommendationfragment1.findViewById(R.id.hdPioneerchoice);
            hdPioneer.setChecked(false);
            CheckBox hdKingstonchoice = (CheckBox)recommendationfragment1.findViewById(R.id.hdKingstonchoice);
            hdKingstonchoice.setChecked(false);
            CheckBox hdSanDisk = (CheckBox)recommendationfragment1.findViewById(R.id.hdSanDiskchoice);
            hdSanDisk.setChecked(false);
            CheckBox hdTranscendchoice = (CheckBox)recommendationfragment1.findViewById(R.id.hdTranscendchoice);
            hdTranscendchoice.setChecked(false);
            CheckBox hdLiteon = (CheckBox)recommendationfragment1.findViewById(R.id.hdLiteonchoice);
            hdLiteon.setChecked(false);
            CheckBox hdPlextor = (CheckBox)recommendationfragment1.findViewById(R.id.hdPlextorchoice);
            hdPlextor.setChecked(false);

            mb.clear();
            vga.clear();
            power.clear();
            cpu.clear();
            ram.clear();
            hd.clear();
        }
    };
    private void removevga(){
        CheckBox vgaASUSCHOICE = (CheckBox)recommendationfragment1.findViewById(R.id.vgaASUSchoice);
        vgaASUSCHOICE.setChecked(false);
        CheckBox vgaMSICHOICE  = (CheckBox)recommendationfragment1.findViewById(R.id.vgaMSIchoice);
        vgaMSICHOICE.setChecked(false);
        CheckBox vgaEVGA = (CheckBox)recommendationfragment1.findViewById(R.id.vgaEVGAchoice);
        vgaEVGA.setChecked(false);
        CheckBox vgaGalaxy = (CheckBox)recommendationfragment1.findViewById(R.id.vgaGalaxychoice);
        vgaGalaxy.setChecked(false);
        CheckBox vgaGIGABYTECHOICE = (CheckBox)recommendationfragment1.findViewById(R.id.vgaGIGABYTEchoice);
        vgaGIGABYTECHOICE.setChecked(false);
        CheckBox vgaINNO3D = (CheckBox)recommendationfragment1.findViewById(R.id.vgaINNO3Dchoice);
        vgaINNO3D.setChecked(false);
        CheckBox vgaLEADTEK = (CheckBox)recommendationfragment1.findViewById(R.id.vgaLEADTEKchoice);
        vgaLEADTEK.setChecked(false);
        vga.clear();
    }
    private CheckBox.OnCheckedChangeListener check=new CheckBox.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch (buttonView.getId()) {
                    case R.id. cpuIntelchoice: {
                        if (isChecked) {
                            cpu.add("Intel");
                        } else {
                            cpu.remove("Intel");
                        }
                        break;
                    }
                    case R.id. cpuamdchoice: {
                        if (isChecked) {
                            cpu.add("AMD");
                        } else {
                            cpu.remove("AMD");
                        }
                        break;
                    }
                    /****************HD*****************/
                    case R.id.hdAdatachoice: {
                        if (isChecked) {
                            hd.add("Adata");
                        } else {
                            hd.remove("Adata");
                        }
                        break;
                    }
                    case R.id.hdSeagatechoice: {
                        if (isChecked) {
                            hd.add("Seagate");
                        } else {
                            hd.remove("Seagate");
                        }
                        break;
                    }
                    case R.id.hdWDchoice: {
                        if (isChecked) {
                            hd.add("WD");
                        } else {
                            hd.remove("WD");
                        }
                        break;
                    }
                    case R.id.hdToshibachoice: {
                        if (isChecked) {
                            hd.add("Toshiba");
                        } else {
                            hd.remove("Toshiba");
                        }
                        break;
                    }
                    case R.id.hdHGSTchoice: {
                        if (isChecked) {
                            hd.add("HGST");
                        } else {
                            hd.remove("HGST");
                        }
                        break;
                    }
                    case R.id.hdSamsungchoice: {
                        if (isChecked) {
                            hd.add("Samsung");
                        } else {
                            hd.remove("Samsung");
                        }
                        break;
                    }
                    case R.id.hdUMAXchoice: {
                        if (isChecked) {
                            hd.add("UMAX");
                        } else {
                            hd.remove("UMAX");
                        }
                        break;
                    }
                    case R.id.hdGalaxychoice: {
                        if (isChecked) {
                            hd.add("Galaxy");
                        } else {
                            hd.remove("Galaxy");
                        }
                        break;
                    }
                    case R.id.hdMicronchoice: {
                        if (isChecked) {
                            hd.add("Micron");
                        } else {
                            hd.remove("Micron");
                        }
                        break;
                    }
                    case R.id.hdIntelchoice: {
                        if (isChecked) {
                            hd.add("Intel");
                        } else {
                            hd.remove("Intel");
                        }
                        break;
                    }
                    case R.id.hdHikvisionchoice: {
                        if (isChecked) {
                            hd.add("Hikvision");
                        } else {
                            hd.remove("Hikvision");
                        }
                        break;
                    }
                    case R.id.hdPioneerchoice: {
                        if (isChecked) {
                            hd.add("Pioneer");
                        } else {
                            hd.remove("Pioneer");
                        }
                        break;
                    }
                    case R.id.hdKingstonchoice: {
                        if (isChecked) {
                            hd.add("Kingston");
                        } else {
                            hd.remove("Kingston");
                        }
                        break;
                    }
                    case R.id.hdSanDiskchoice: {
                        if (isChecked) {
                            hd.add("SanDisk");
                        } else {
                            hd.remove("SanDisk");
                        }
                        break;
                    }
                    case R.id.hdTranscendchoice: {
                        if (isChecked) {
                            hd.add("Transcend");
                        } else {
                            hd.remove("Transcend");
                        }
                        break;
                    }
                    case R.id.hdLiteonchoice: {
                        if (isChecked) {
                            hd.add("Liteon");
                        } else {
                            hd.remove("Liteon");
                        }
                        break;
                    }
                    case R.id.hdPlextorchoice: {
                        if (isChecked) {
                            hd.add("Plextor");
                        } else {
                            hd.remove("Plextor");
                        }
                        break;
                    }
                    /**************Power****************/
                    case R.id.powerDeltachoice: {
                        if (isChecked) {
                            power.add("Delta");
                        } else {
                            power.remove("Delta");
                        }
                        break;
                    }
                    case R.id.powerFSPchoice: {
                        if (isChecked) {
                            power.add("FSP");
                        } else {
                            power.remove("FSP");
                        }
                        break;
                    }
                    case R.id.powerAntecchoice: {
                        if (isChecked) {
                            power.add("Antec");
                        } else {
                            power.remove("Antec");
                        }
                        break;
                    }
                    case R.id.powerCorsairchoice: {
                        if (isChecked) {
                            power.add("Corsair");
                        } else {
                            power.remove("Corsair");
                        }
                        break;
                    }
                    case R.id.powerBitFenixchoice: {
                        if (isChecked) {
                            power.add("BitFenix火鳥");
                        } else {
                            power.remove("BitFenix火鳥");
                        }
                        break;
                    }
                    case R.id.powerBfriendchoice: {
                        if (isChecked) {
                            power.add("B.FRIEND");
                        } else {
                            power.remove("B.FRIEND");
                        }
                        break;
                    }
                    case R.id.powerGIGABYTEchoice: {
                        if (isChecked) {
                            power.add("GIGABYTE");
                        } else {
                            power.remove("GIGABYTE");
                        }
                        break;
                    }
                    case R.id.powerSAMAchoice: {
                        if (isChecked) {
                            power.add("SAMA");
                        } else {
                            power.remove("SAMA");
                        }
                        break;
                    }
                    case R.id.powerEVGAchoice: {
                        if (isChecked) {
                            power.add("EVGA");
                        } else {
                            power.remove("EVGA");
                        }
                        break;
                    }
                    case R.id.powerAndysonchoice: {
                        if (isChecked) {
                            power.add("Andyson");
                        } else {
                            power.remove("Andyson");
                        }
                        break;
                    }
                    case R.id.powerThermaltakechoice: {
                        if (isChecked) {
                            power.add("Thermaltake");
                        } else {
                            power.remove("Thermaltake");
                        }
                        break;
                    }
                    case R.id.powerXFXchoice: {
                        if (isChecked) {
                            power.add("XFX");
                        } else {
                            power.remove("XFX");
                        }
                        break;
                    }
                    case R.id.powerCyberSLIMchioice: {
                        if (isChecked) {
                            power.add("CyberSLIM");
                        } else {
                            power.remove("CyberSLIM");
                        }
                        break;
                    }
                    case R.id.powerSuperflowerchioice: {
                        if (isChecked) {
                            power.add("SUPERFLOWER");
                        } else {
                            power.remove("SUPERFLOWER");
                        }
                        break;
                    }
                    case R.id.powerZUMAXchioice: {
                        if (isChecked) {
                            power.add("ZUMAX");
                        } else {
                            power.remove("ZUMAX");
                        }
                        break;
                    }
                    case R.id.powerCoolerMasterchoice: {
                        if (isChecked) {
                            power.add("CoolerMaster");
                        } else {
                            power.remove("CoolerMaster");
                        }
                        break;
                    }
                    case R.id.powerSeventeamchoice: {
                        if (isChecked) {
                            power.add("Seventeam");
                        } else {
                            power.remove("Seventeam");
                        }
                        break;
                    }
                    case R.id.powerSilverstonechoice: {
                        if (isChecked) {
                            power.add("SILVERSTONE");
                        } else {
                            power.remove("SILVERSTONE");
                        }
                        break;
                    }
                    case R.id.powerSeasonicchoice: {
                        if (isChecked) {
                            power.add("Seasonic");
                        } else {
                            power.remove("Seasonic");
                        }
                        break;
                    }
                    case R.id.powerSEEDchoice: {
                        if (isChecked) {
                            power.add("SEED");
                        } else {
                            power.remove("SEED");
                        }
                        break;
                    }
                    case R.id.powerEnermaxchoice: {
                        if (isChecked) {
                            power.add("Enermax");
                        } else {
                            power.remove("Enermax");
                        }
                        break;
                    }
                    case R.id.mbasuschoice: {
                        if (isChecked) {
                            mb.add("ASUS");
                        } else {
                            mb.remove("ASUS");
                        }
                        break;
                    }
                    case R.id.mbgigabytechoice: {
                        if (isChecked) {
                            mb.add("gigabyte");
                        } else {
                            mb.remove("gigabyte");
                        }
                        break;
                    }
                    case R.id.mbmsichoice: {
                        if (isChecked) {
                            mb.add("msi");
                        } else {
                            mb.remove("msi");
                        }
                        break;
                    }
                    case R.id.mbasrockchoice: {
                        if (isChecked) {
                            mb.add("asrock");
                        } else {
                            mb.remove("asrock");
                        }
                        break;
                    }
                    /*************RAM************/

                    case R.id.ramAdatachoice: {
                        if (isChecked) {
                            ram.add("Adata");
                        } else {
                            ram.remove("Adata");
                        }
                        break;
                    }
                    case R.id.ramgskillchoice: {
                        if (isChecked) {
                            ram.add("G.skill");
                        } else {
                            ram.remove("G.skill");
                        }
                        break;
                    }
                    case R.id.ramKingstonchoice: {
                        if (isChecked) {
                            ram.add("Kingston");
                        } else {
                            ram.remove("Kingston");
                        }
                        break;
                    }
                    case R.id.ramMicronchoice: {
                        if (isChecked) {
                            ram.add("Micron");
                        } else {
                            ram.remove("Micron");
                        }
                        break;
                    }
                    case R.id.ramTranscendchoice: {
                        if (isChecked) {
                            ram.add("Transcend");
                        } else {
                            ram.remove("Transcend");
                        }
                        break;
                    }
                    case R.id.ramUMAXchoice: {
                        if (isChecked) {
                            ram.add("UMAX");
                        } else {
                            ram.remove("UMAX");
                        }
                        break;
                    }

                    /**********************VGA*************************/
                    case R.id.vgaASUSchoice: {
                        if (isChecked) {
                            vga.add("ASUS");
                        } else {
                            vga.remove("ASUS");
                        }
                        break;
                    }
                    case R.id.vgaMSIchoice: {
                        if (isChecked) {
                            vga.add("MSI");
                        } else {
                            vga.remove("MSI");
                        }
                        break;
                    }
                    case R.id.vgaGIGABYTEchoice: {
                        if (isChecked) {
                            vga.add("GIGABYTE");
                        } else {
                            vga.remove("GIGABYTE");
                        }
                        break;
                    }
                    case R.id.vgaEVGAchoice: {
                        if (isChecked) {
                            vga.add("EVGA");
                        } else {
                            vga.remove("EVGA");
                        }
                        break;
                    }
                    case R.id.vgaGalaxychoice: {
                        if (isChecked) {
                            vga.add("Galaxy");
                        } else {
                            vga.remove("Galaxy");
                        }
                        break;
                    }
                    case R.id.vgaINNO3Dchoice: {
                        if (isChecked) {
                            vga.add("INNO3D");
                        } else {
                            vga.remove("INNO3D");
                        }
                        break;
                    }
                    case R.id.vgaLEADTEKchoice: {
                        if (isChecked) {
                            vga.add("LEADTEK");
                        } else {
                            vga.remove("LEADTEK");
                        }
                        break;
                    }
                    default:
                        break;
                }
            }
    };
    private Button.OnClickListener select = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            jsonArray = new JSONArray();
            final Fragmenthomepage fragmenthomepage = new Fragmenthomepage();
            RelativeLayout resultrelativelayout=(RelativeLayout)recommendationfragment1.findViewById(R.id.resultrelativelayout);
            final ArrayList<ClassListItems> allboxname = new ArrayList<>();
            ListView resultListview = (ListView)recommendationfragment1.findViewById(R.id.resultListview);/* ***** */
            int cpuselectlow;
            int cpuselectup;
            int mbselectlow;
            int mbselectup;
            int ramselectlow;
            int ramselectup;
            int vgaselectlow;
            int vgaselectup;
            int hdselectlow;
            int hdselectup;
            int powerselectlow;
            int powerselectup;
            String cpuselect = "";
            String mbselect="";
            String ramselect="";
            String vgaselect="";
            String hdselect="";
            String powerselect="";
            String recommendationall1="";
            try {
                cpuselectlow= Integer.valueOf(((EditText)recommendationfragment1.findViewById(R.id.cpuselectlow)).getText().toString());
                cpuselectup = Integer.valueOf(((EditText)recommendationfragment1.findViewById(R.id.cpuselectup)).getText().toString());
                mbselectlow = Integer.valueOf(((EditText)recommendationfragment1.findViewById(R.id.mbselectlow)).getText().toString());
                mbselectup  = Integer.valueOf(((EditText)recommendationfragment1.findViewById(R.id.mbselectup)).getText().toString());
                ramselectlow= Integer.valueOf(((EditText)recommendationfragment1.findViewById(R.id.ramselectlow)).getText().toString());
                ramselectup = Integer.valueOf(((EditText)recommendationfragment1.findViewById(R.id.ramselectup)).getText().toString());
                vgaselectlow= Integer.valueOf(((EditText)recommendationfragment1.findViewById(R.id.vgaselectlow)).getText().toString());
                vgaselectup = Integer.valueOf(((EditText)recommendationfragment1.findViewById(R.id.vgaselectup)).getText().toString());
                hdselectlow = Integer.valueOf(((EditText)recommendationfragment1.findViewById(R.id.hdselectlow)).getText().toString());
                hdselectup  = Integer.valueOf(((EditText)recommendationfragment1.findViewById(R.id.hdselectup)).getText().toString());
                powerselectlow= Integer.valueOf(((EditText)recommendationfragment1.findViewById(R.id.powerselectlow)).getText().toString());
                powerselectup= Integer.valueOf(((EditText)recommendationfragment1.findViewById(R.id.powerselectup)).getText().toString());

                if(computertype=="710"){vgaselectlow = 2490;vgaselectup=2600;}
                else if (computertype!="710" && vgaselectlow < 2600){vgaselectlow = 2600;}
                jsonObject.put("cpulow",cpuselectlow);
                jsonObject.put("cpuup",cpuselectup);
                jsonObject.put("mblow",mbselectlow);
                jsonObject.put("mbup",mbselectup);
                jsonObject.put("vgalow",vgaselectlow);
                jsonObject.put("vgaup",vgaselectup);
                jsonObject.put("hdlow",hdselectlow);
                jsonObject.put("hdup",hdselectup);
                jsonObject.put("ramlow",ramselectlow);
                jsonObject.put("ramup",ramselectup);
                jsonObject.put("powerlow",powerselectlow);
                jsonObject.put("powerup",powerselectup);
                jsonObject.put("computertype",computertype);
                jsonObject.put("order",priceorder);
                jsonArray =returnselect(cpu,"cpu");
                jsonArray =returnselect(mb,"mb");
                jsonArray =returnselect(ram,"ram");
                jsonArray =returnselect(vga,"vga");
                jsonArray =returnselect(hd,"hd");
                jsonArray =returnselect(power,"power");
                jsonObject.put("type",jsonArray);
                String ip = "http://203.68.252.59/Android/product/aa.php?json="+jsonObject.toString();
                if ((String.valueOf(power.size())!="0")&&(String.valueOf(mb.size())!="0")&&(String.valueOf(vga.size())!="0")&&(String.valueOf(ram.size())!="0")&&(String.valueOf(hd.size())!="0")) {
                        phptest phptest1 = new phptest(getContext(), ip, allboxname, resultListview);
                        phptest1.execute("");
                        ScrollView ifview = (ScrollView) recommendationfragment1.findViewById(R.id.ifview);
                        ifview.setVisibility(View.GONE);
                        resultrelativelayout.setVisibility(View.VISIBLE);
                }else {fragmenthomepage.alert("格式錯誤","請檢查是否每種選擇至少選一種廠商或者金錢數字輸入不正確",getContext());}
                /*if ((String.valueOf(power.size())!="0")&&(String.valueOf(mb.size())!="0")&&(String.valueOf(vga.size())!="0")&&(String.valueOf(ram.size())!="0")&&(String.valueOf(hd.size())!="0")) {
                    powerselect = returnselect(power,"power");
                    mbselect = returnselect(mb,"mb");
                    vgaselect = returnselect(vga,"vga");
                    cpuselect = returnselect(cpu,"cpu");
                    hdselect = returnselect(hd,"hd");
                    ramselect = returnselect(ram,"ram");
                    recommendationall1="SELECT mbmcr.mbSeriesmodel,mbmcr.mbPrices,cpu.cSeriesmodel,cpu.cPrices,ram.rModel,ram.rPrices,vga.vSeriesmodel,vga.vPrices,\n" +
                            "hd.hModel,hd.hPrices,power.prSeriesmodel,power.prPrices,(cpu.cPrices + mbmcr.mbPrices + ram.rPrices + vga.vPrices + hd.hPrices + power.prPrices) AS tprices\n" +
                            "FROM\n" +
                            "((\n" +
                            "    SELECT cpu.cId,cpu.cSeriesmodel,mfcpu.mfCName,cpu.cPin,cpu.cCore,cpu.cThread,cpu.cTDP,cpu.cPrices\n" +
                            "    FROM cpu JOIN mf AS mfcpu ON cpu.mfId = mfcpu.mfId \n" +
                            "    WHERE mfcpu.mfName = 'Intel' \n" +
                            ") AS CPU CROSS JOIN\n" +
                            "(\n" +
                            "    SELECT mb.mbId,mb.mbSeriesmodel,mb.mfCName,mb.mbPrices,mcr.mbId AS mcrId,mcr.CPU,mcr.CPUpin,mcr.DDR,mcr.RAMupG,mcr.RAMupMHz\n" +
                            "    FROM\n" +
                            "    (\n" +
                            "    \tSELECT mb.mbId,mb.mbSeriesmodel,mfmb.mfCName,mb.mbPrices\n" +
                            "    \tFROM mb JOIN mf AS mfmb ON mb.mfId = mfmb.mfId\n" +
                            "    \tWHERE "+ mbselect +"  \n" +
                            "    )AS MB JOIN mcr ON MB.mbId = mcr.mbId\n" +
                            ") AS MBMCR ON cpu.cId = MBMCR.CPU) CROSS JOIN \n" +
                            "(\n" +
                            "    SELECT ram.rModel,mfram.mfCName,ram.rCapacity,ram.rDDR,ram.rMHz,ram.rPrices\n" +
                            "    FROM ram JOIN mf AS mfram ON ram.mfId = mfram.mfId\n" +
                            "    WHERE "+ramselect+" \n" +
                            ") AS RAM CROSS JOIN \n" +
                            "(\n" +
                            "    SELECT vga.vSeriesmodel,mfv.mfCName,vga.vCapacity,vga.vDynamcclock,vga.vCoreclock,vga.vVoltage,vga.vPrices\n" +
                            "    FROM vga LEFT OUTER JOIN make ON vga.vId = make.vId LEFT OUTER JOIN mf AS mfv ON make.mfId = mfv.mfId \n" +
                            "    Where "+vgaselect+" \n" +
                            ") AS VGA CROSS JOIN \n" +
                            "(\n" +
                            "    SELECT hd.hModel,mfhd.mfCName,hd.hType,hd.hCapacity,hd.hUse,hd.hPrices\n" +
                            "    FROM hd JOIN mf AS mfhd ON hd.mfId = mfhd.mfId\n" +
                            "    Where "+hdselect+" \n" +
                            "\n" +
                            ") AS HD CROSS JOIN \n" +
                            "(\n" +
                            "    SELECT power.prSeriesmodel,mfpower.mfCName,power.prPower,power.prPrices\n" +
                            "    FROM power JOIN mf AS mfpower ON power.mfId = mfpower.mfId \n" +
                            "    Where "+powerselect+"  \n"+
                            ") AS POWER\n" +
                            "WHERE CPU.cPin = MBMCR.CPUpin AND RAM.rDDR = MBMCR.DDR AND RAM.rCapacity <= MBMCR.RAMupG AND RAM.rMHz <= MBMCR.RAMupMHz AND   \n" +
                            "                                  (CPU.cPrices between   "+cpuselectlow+"  and   "+cpuselectup+"  ) and (MBMCR.mbPrices between   "+mbselectlow+"   and  "+mbselectup+" ) AND   \n" +
                            "                                  (RAM.rPrices between   "+ramselectlow+"   and   "+ramselectup+"   ) and (VGA.vPrices between   "+vgaselectlow+"   and   "+vgaselectup+"  ) AND  \n" +
                            "                                  (HD.hPrices between   "+hdselectlow+"   and   "+hdselectup+"   ) and (POWER.prPrices between  "+powerselectlow+"   and   "+powerselectup+"  )    \n" +
                            "Order By tprices DESC\n" +
                            "                       " ;

                            Synca synca = new Synca(recommendationall1,allboxname,resultListview,getContext());
                            synca.execute("");
                    ScrollView ifview = (ScrollView)recommendationfragment1.findViewById(R.id.ifview);
                    ifview.setVisibility(View.GONE);
                    resultrelativelayout.setVisibility(View.VISIBLE);

                }else {fragmenthomepage.alert("格式錯誤","每種選擇請至少選一種廠商",getContext());}
*/
            }catch (Exception e){
               fragmenthomepage.alert("格式錯誤","請輸入正確數字",getContext());
            }
        }
    };
}
class phptest extends AsyncTask<String, String, String> {
    BufferedReader reader =null;
    HttpURLConnection conn ;
    ProgressDialog progress;
    Context context;
    String ip;
    ArrayList<ClassListItems> boxname;
    ListView listview;
    Fragmenthomepage fragmenthomepage = new Fragmenthomepage();
    phptest(Context context ,String ip,ArrayList<ClassListItems> boxname,ListView listview)  {
        super();
        this.context=context;
        this.ip=ip;
        this.boxname = boxname;
        this.listview=listview;
    }
    @Override
    protected void onPreExecute(){
        progress = new ProgressDialog(context);
        progress.setTitle("Synchronising");
        progress.setMessage("載入中...請稍候");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progress.cancel();
                        // TODO Auto-generated method stub
                    }
                });
        progress.show();

       /* progress.show(context, "Synchronising",
                "載入中...請稍候", true);*/
    }
    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(ip);

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            //conn.setConnectTimeout(6 * 1000);
           // conn.setReadTimeout(6 * 1000);
            InputStream stream = conn.getInputStream();
            StringBuffer stringBuffer = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(stream));

            String s = "";
            while ((s = reader.readLine()) != null) {
                stringBuffer.append(s);
            }
            return stringBuffer.toString();

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
    protected void onPostExecute(String msg){
            super.onPostExecute(msg);
            boxname.clear();
        try {
            JSONObject jsonObject = new JSONObject(msg);
            JSONObject jsonObject1 = jsonObject.getJSONObject("computer");
                String cpu = jsonObject1.getString("處理器").toString();
                String mb = jsonObject1.getString("主機板").toString();
                String ram = jsonObject1.getString("記憶體").toString();
                String vga = jsonObject1.getString("顯示卡").toString();
                String ssdhhd = jsonObject1.getString("硬碟").toString();
                String power = jsonObject1.getString("電源供應器").toString();
                String total = jsonObject1.getString("總金額").toString();
                boxname.add((new ClassListItems (cpu+"\n"+mb+"\n"+ram+"\n"+vga+"\n"+ssdhhd+"\n"+power+"\n",total)));

            progress.dismiss();
            MyAppAdapter myAppAdapter = new MyAppAdapter(boxname, context, 1);
            listview.setAdapter(myAppAdapter);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //產生視窗物件
                    String detail = boxname.get(position).getSeriesmodel() + " " + boxname.get(position).getPrices();
                    fragmenthomepage.alert("產品詳細規格", detail, context);
                }
            });
        } catch (JSONException e) {
            progress.dismiss();
            fragmenthomepage.alert("無此配對", ip, context);
        }
    }
}

class Synca extends AsyncTask<String, String, String>{
    BufferedReader reader =null;
    HttpURLConnection conn ;
    ProgressDialog progress;
    Context context;
    String ip;
    ArrayList<ClassListItems> boxname;
    ListView listview;
    Fragmenthomepage fragmenthomepage = new Fragmenthomepage();
    Synca(Context context ,String ip,ArrayList<ClassListItems> boxname,ListView listview)  {
        super();
        this.context=context;
        this.ip=ip;
        this.boxname = boxname;
        this.listview=listview;
    }
    @Override
    protected void onPreExecute(){
        progress = new ProgressDialog(context);
        progress.setTitle("Synchronising");
        progress.setMessage("載入中...請稍候");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progress.cancel();
                        // TODO Auto-generated method stub
                    }
                });
        progress.show();

       /* progress.show(context, "Synchronising",
                "載入中...請稍候", true);*/
    }
    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(ip);

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            //conn.setConnectTimeout(6 * 1000);
            // conn.setReadTimeout(6 * 1000);
            InputStream stream = conn.getInputStream();
            StringBuffer stringBuffer = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(stream));

            String s = "";
            while ((s = reader.readLine()) != null) {
                stringBuffer.append(s);
            }
            return stringBuffer.toString();

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
    protected void onPostExecute(String msg){
        super.onPostExecute(msg);
        boxname.clear();
        try {
            JSONObject jsonObject = new JSONObject(msg);
            JSONArray jsonArray = jsonObject.getJSONArray("computer");
            int max_size = jsonArray.length();
            for(int i = 0 ; i < max_size ; i++){
                JSONObject inboxthing = (JSONObject) jsonArray.get(i);
                String cpu = inboxthing.getString("處理器");
                String mb = inboxthing.getString("主機板");
                String ram = inboxthing.getString("記憶體");
                String vga = inboxthing.getString("顯示卡");
                String ssdhhd = inboxthing.getString("硬碟");
                String power = inboxthing.getString("電源供應器");
                String total = inboxthing.getString("總金額");
                boxname.add((new ClassListItems (cpu+"\n"+mb+"\n"+ram+"\n"+vga+"\n"+ssdhhd+"\n"+power+"\n",total)));
            }
            progress.dismiss();
            MyAppAdapter myAppAdapter = new MyAppAdapter(boxname, context, 1);
            listview.setAdapter(myAppAdapter);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //產生視窗物件
                    String detail = boxname.get(position).getSeriesmodel() + " " + boxname.get(position).getPrices();
                    fragmenthomepage.alert("產品詳細規格", detail, context);
                }
            });
        } catch (JSONException e) {
            progress.dismiss();
            fragmenthomepage.alert("無此配對", "無資料", context);
        }
        progress.dismiss();
    }
}
