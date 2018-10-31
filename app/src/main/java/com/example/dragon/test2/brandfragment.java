package com.example.dragon.test2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class brandfragment extends Fragment implements View.OnClickListener {

    View brandfragment1;
    Intent it ;
    public brandfragment() { //建構子(Required empty public constructor)

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //指定用於填充fragment的layout資源文件(Inflate the layout for this fragment)
        brandfragment1 = inflater.inflate(R.layout.fragment_brandfragment, container, false);
        findViewID();
        return brandfragment1;
    }
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cpu_intel:
                intel();break;
            case R.id.cpu_AMD:
                AMD();break;
            case R.id.power_elt:
                elt();break;
            case R.id.power_antec:
                antec();break;
            case R.id.power_bit:
                bit();break;
            case R.id.power_sama:
                sama();break;
            case R.id.power_gigabyte:
                gigabyte();break;
            case R.id.power_evga:
                evga();break;
            case R.id.power_andyson:
                andyson();break;
            case R.id.power_xfx:
                xfx();break;
            case R.id.power_cyberslim:
                cyberslim();break;
            case R.id.power_thermaltake:
                thermaltake();break;
            case R.id.power_superflower:
                superflower();break;
            case R.id.power_corsair:
                corsair();break;
            case R.id.power_zumax:
                zumax();break;
            case R.id.power_silverstone:
                silverstone();break;
            case R.id.power_seventeam:
                seventeam();break;
            case R.id.power_coolermaster:
                coolermaster();break;
            case R.id.power_seasonic:
                seasonic();break;
            case R.id.power_seed:
                seed();break;
            case R.id.power_enermax:
                enermax();break;
            case R.id.mb_asus:
                asus();break;
            case R.id.mb_nvidia:
                nvidia();break;
            case R.id.mb_gigabyte:
                gigabyte();break;
            case R.id.mb_asrock:
                asrock();break;
            case R.id.mb_supermicro:
                supermicro();break;
            case R.id.mb_msi:
                msi();break;
            case R.id.vga_gigabyte:
                gigabyte();break;
            case R.id.vga_asus:
                asus();break;
            case R.id.vga_evga:
                evga();break;
            case R.id.vga_msi:
                msi();break;
            case R.id.vga_galax:
                galax();break;
            case R.id.vga_inno3d:
                inno3d();break;
            case R.id.vga_leadtek:
                leadtek();break;
            case R.id.vga_nvidia:
                nvidia();break;
            case R.id.vga_tul:
                tul();break;
            case R.id.vga_amd:
                AMD();break;
            case R.id.ram_kingston:
                kingston();break;
            case R.id.ram_vcolor:
                vcolor();break;
            case R.id.ram_gskill:
                gskill();break;
            case R.id.ram_transcend:
                transcend();break;
            case R.id.ram_umax:
                umax();break;
            case R.id.ram_micron:
                micron();break;
            case R.id.ram_adata:
                adata();break;
            case R.id.shd_umax:
                umax();break;
            case R.id.shd_galax:
                galax();break;
            case R.id.shd_samsung:
                samsung();break;
            case R.id.shd_intel:
                intel();break;
            case R.id.shd_adata:
                adata();break;
            case R.id.shd_hikvision:
                hikvision();break;
            case R.id.shd_pioneer:
                pioneer();break;
            case R.id.shd_kingston:
                kingston();break;
            case R.id.shd_sandisk:
                sandisk();break;
            case R.id.shd_transcend:
                transcend();break;
            case R.id.shd_liteon:
                liteon();break;
            case R.id.shd_plextor:
                plextor();break;
            case R.id.shd_micron:
                micron();break;
            case R.id.shd_wd:
                wd();break;
            case R.id.shd_vcolor:
                vcolor();break;
            case R.id.shd_toshiba:
                toshiba();break;
            case R.id.shd_seagate:
                seagate();break;
            case R.id.shd_hgst:
                hgst();break;
        }
    }
    public void findViewID(){
        Button cpu_intel = (Button)brandfragment1.findViewById(R.id.cpu_intel);
        Button cpu_AMD = (Button)brandfragment1.findViewById(R.id.cpu_AMD);
        Button power_elt = (Button)brandfragment1.findViewById(R.id.power_elt);
        Button power_antec = (Button)brandfragment1.findViewById(R.id.power_antec);
        Button power_bit = (Button)brandfragment1.findViewById(R.id.power_bit);
        Button power_sama = (Button)brandfragment1.findViewById(R.id.power_sama);
        Button power_gigabyte = (Button)brandfragment1.findViewById(R.id.power_gigabyte);
        Button power_evga = (Button)brandfragment1.findViewById(R.id.power_evga);
        Button power_andyson = (Button)brandfragment1.findViewById(R.id.power_andyson);
        Button power_xfx = (Button)brandfragment1.findViewById(R.id.power_xfx);
        Button power_cyberslim = (Button)brandfragment1.findViewById(R.id.power_cyberslim);
        Button power_thermaltake = (Button)brandfragment1.findViewById(R.id.power_thermaltake);
        Button power_superflower = (Button)brandfragment1.findViewById(R.id.power_superflower);
        Button power_corsair = (Button)brandfragment1.findViewById(R.id.power_corsair);
        Button power_zumax = (Button)brandfragment1.findViewById(R.id.power_zumax);
        Button power_silverstone = (Button)brandfragment1.findViewById(R.id.power_silverstone);
        Button power_seventeam = (Button)brandfragment1.findViewById(R.id.power_seventeam);
        Button power_coolermaster = (Button)brandfragment1.findViewById(R.id.power_coolermaster);
        Button power_seasonic = (Button)brandfragment1.findViewById(R.id.power_seasonic);
        Button power_seed = (Button)brandfragment1.findViewById(R.id.power_seed);
        Button power_enermax = (Button)brandfragment1.findViewById(R.id.power_enermax);
        Button mb_asus = (Button)brandfragment1.findViewById(R.id.mb_asus);
        Button mb_nvidia = (Button)brandfragment1.findViewById(R.id.mb_nvidia);
        Button mb_gigabyte = (Button)brandfragment1.findViewById(R.id.mb_gigabyte);
        Button mb_asrock = (Button)brandfragment1.findViewById(R.id.mb_asrock);
        Button mb_supermicro = (Button)brandfragment1.findViewById(R.id.mb_supermicro);
        Button mb_msi = (Button)brandfragment1.findViewById(R.id.mb_msi);
        Button vga_gigabyte = (Button)brandfragment1.findViewById(R.id.vga_gigabyte);
        Button vga_asus = (Button)brandfragment1.findViewById(R.id.vga_asus);
        Button vga_evga = (Button)brandfragment1.findViewById(R.id.vga_evga);
        Button vga_msi = (Button)brandfragment1.findViewById(R.id.vga_msi);
        Button vga_galax = (Button)brandfragment1.findViewById(R.id.vga_galax);
        Button vga_inno3d = (Button)brandfragment1.findViewById(R.id.vga_inno3d);
        Button vga_leadtek = (Button)brandfragment1.findViewById(R.id.vga_leadtek);
        Button vga_nvidia = (Button)brandfragment1.findViewById(R.id.vga_nvidia);
        Button vga_tul = (Button)brandfragment1.findViewById(R.id.vga_tul);
        Button vga_amd = (Button)brandfragment1.findViewById(R.id.vga_amd);
        Button ram_kingston = (Button)brandfragment1.findViewById(R.id.ram_kingston);
        Button ram_vcolor = (Button)brandfragment1.findViewById(R.id.ram_vcolor);
        Button ram_gskill = (Button)brandfragment1.findViewById(R.id.ram_gskill);
        Button ram_transcend = (Button)brandfragment1.findViewById(R.id.ram_transcend);
        Button ram_umax = (Button)brandfragment1.findViewById(R.id.ram_umax);
        Button ram_micron = (Button)brandfragment1.findViewById(R.id.ram_micron);
        Button ram_adata = (Button)brandfragment1.findViewById(R.id.ram_adata);
        Button shd_umax = (Button)brandfragment1.findViewById(R.id.shd_umax);
        Button shd_galax = (Button)brandfragment1.findViewById(R.id.shd_galax);
        Button shd_samsung = (Button)brandfragment1.findViewById(R.id.shd_samsung);
        Button shd_intel = (Button)brandfragment1.findViewById(R.id.shd_intel);
        Button shd_adata = (Button)brandfragment1.findViewById(R.id.shd_adata);
        Button shd_hikvision = (Button)brandfragment1.findViewById(R.id.shd_hikvision);
        Button shd_pioneer = (Button)brandfragment1.findViewById(R.id.shd_pioneer);
        Button shd_kingston = (Button)brandfragment1.findViewById(R.id.shd_kingston);
        Button shd_sandisk = (Button)brandfragment1.findViewById(R.id.shd_sandisk);
        Button shd_transcend = (Button)brandfragment1.findViewById(R.id.shd_transcend);
        Button shd_liteon = (Button)brandfragment1.findViewById(R.id.shd_liteon);
        Button shd_plextor = (Button)brandfragment1.findViewById(R.id.shd_plextor);
        Button shd_micron = (Button)brandfragment1.findViewById(R.id.shd_micron);
        Button shd_wd = (Button)brandfragment1.findViewById(R.id.shd_wd);
        Button shd_vcolor = (Button)brandfragment1.findViewById(R.id.shd_vcolor);
        Button shd_toshiba = (Button)brandfragment1.findViewById(R.id.shd_toshiba);
        Button shd_seagate = (Button)brandfragment1.findViewById(R.id.shd_seagate);
        Button shd_hgst = (Button)brandfragment1.findViewById(R.id.shd_hgst);
        cpu_intel.setOnClickListener(this);
        cpu_AMD.setOnClickListener(this);
        power_elt.setOnClickListener(this);
        power_antec.setOnClickListener(this);
        power_bit.setOnClickListener(this);
        power_sama.setOnClickListener(this);
        power_gigabyte.setOnClickListener(this);
        power_evga.setOnClickListener(this);
        power_andyson.setOnClickListener(this);
        power_xfx.setOnClickListener(this);
        power_cyberslim.setOnClickListener(this);
        power_thermaltake.setOnClickListener(this);
        power_superflower.setOnClickListener(this);
        power_corsair.setOnClickListener(this);
        power_zumax.setOnClickListener(this);
        power_silverstone.setOnClickListener(this);
        power_seventeam.setOnClickListener(this);
        power_coolermaster.setOnClickListener(this);
        power_seasonic.setOnClickListener(this);
        power_seed.setOnClickListener(this);
        power_enermax.setOnClickListener(this);
        mb_asus.setOnClickListener(this);
        mb_nvidia.setOnClickListener(this);
        mb_gigabyte.setOnClickListener(this);
        mb_asrock.setOnClickListener(this);
        mb_supermicro.setOnClickListener(this);
        mb_msi.setOnClickListener(this);
        vga_gigabyte.setOnClickListener(this);
        vga_asus.setOnClickListener(this);
        vga_evga.setOnClickListener(this);
        vga_msi.setOnClickListener(this);
        vga_galax.setOnClickListener(this);
        vga_inno3d.setOnClickListener(this);
        vga_leadtek.setOnClickListener(this);
        vga_nvidia.setOnClickListener(this);
        vga_tul.setOnClickListener(this);
        vga_amd.setOnClickListener(this);
        ram_kingston.setOnClickListener(this);
        ram_vcolor.setOnClickListener(this);
        ram_gskill.setOnClickListener(this);
        ram_transcend.setOnClickListener(this);
        ram_umax.setOnClickListener(this);
        ram_micron.setOnClickListener(this);
        ram_adata.setOnClickListener(this);
        shd_umax.setOnClickListener(this);
        shd_galax.setOnClickListener(this);
        shd_samsung.setOnClickListener(this);
        shd_intel.setOnClickListener(this);
        shd_adata.setOnClickListener(this);
        shd_hikvision.setOnClickListener(this);
        shd_pioneer.setOnClickListener(this);
        shd_kingston.setOnClickListener(this);
        shd_sandisk.setOnClickListener(this);
        shd_transcend.setOnClickListener(this);
        shd_liteon.setOnClickListener(this);
        shd_plextor.setOnClickListener(this);
        shd_micron.setOnClickListener(this);
        shd_wd.setOnClickListener(this);
        shd_vcolor.setOnClickListener(this);
        shd_toshiba.setOnClickListener(this);
        shd_seagate.setOnClickListener(this);
        shd_hgst.setOnClickListener(this);
    }
    public void intel(){
        Uri uri= Uri.parse("https://www.intel.com.tw/content/www/tw/zh/homepage.html");
        go(uri);
    }
    public void AMD(){
        Uri uri= Uri.parse("http://www.amd.com/zh-hant/home");
        go(uri);
    }
    public void elt(){
        Uri uri= Uri.parse("http://www.deltaww.com/");
        go(uri);
    }
    public void antec(){
        Uri uri= Uri.parse("http://www.antec.com/index.php?page=cookieSet");
        go(uri);
    }
    public void bit(){
        Uri uri= Uri.parse("https://www.bitfenix.com/global/ct/about");
        go(uri);
    }
    public void sama(){
        Uri uri= Uri.parse("http://www.sama.cn/");
        go(uri);
    }
    public void gigabyte(){
        Uri uri= Uri.parse("http://www.gigabyte.tw/");
        go(uri);
    }
    public void evga(){
        Uri uri= Uri.parse("https://tw.evga.com/");
        go(uri);
    }
    public void andyson(){
        Uri uri= Uri.parse("http://www.andysonpower.com/");
        go(uri);
    }
    public void xfx(){
        Uri uri= Uri.parse("http://www.xfx.com.cn/");
        go(uri);
    }
    public void cyberslim(){
        Uri uri= Uri.parse("http://www.cyberslim.com.tw/");
        go(uri);
    }
    public void thermaltake(){
        Uri uri= Uri.parse("http://tw.thermaltake.com/");
        go(uri);
    }
    public void superflower(){
        Uri uri= Uri.parse("http://www.super-flower.com.tw/");
        go(uri);
    }
    public void corsair(){
        Uri uri= Uri.parse("http://www.corsair.com/zh-tw");
        go(uri);
    }
    public void zumax(){
        Uri uri= Uri.parse("http://zumaxpower.com/taiwan/news.html");
        go(uri);
    }
    public void seventeam(){
        Uri uri= Uri.parse("http://www.seventeam.com.tw/auto_page.aspx?id=qqkvev7cmvc7x");
        go(uri);
    }
    public void silverstone(){
        Uri uri= Uri.parse("http://www.silverstonetek.com.tw");
        go(uri);
    }
    public void coolermaster(){
        Uri uri= Uri.parse("http://apac.coolermaster.com/tw/");
        go(uri);
    }
    public void seasonic(){
        Uri uri= Uri.parse("https://seasonic.com/zht/");
        go(uri);
    }
    public void seed(){
        Uri uri= Uri.parse("http://www.myseed.com.tw/");
        go(uri);
    }
    public void enermax(){
        Uri uri= Uri.parse("http://www.enermax.com.tw/home.php?fn=chi/main");
        go(uri);
    }
    public void asus(){
        Uri uri= Uri.parse("https://www.asus.com/tw/");
        go(uri);
    }
    public void nvidia(){
        Uri uri= Uri.parse("https://www.nvidia.com.tw/");
        go(uri);
    }
    public void asrock(){
        Uri uri= Uri.parse("http://www.asrock.com.tw/index.tw.asp");
        go(uri);
    }
    public void supermicro(){
        Uri uri= Uri.parse("https://www.supermicro.com/index_home.cfm");
        go(uri);
    }
    public void msi(){
        Uri uri= Uri.parse("https://tw.msi.com/");
        go(uri);
    }
    public void galax(){
        Uri uri= Uri.parse("http://www.galax.com/");
        go(uri);
    }
    public void inno3d(){
        Uri uri= Uri.parse("http://www.inno3d.com/");
        go(uri);
    }
    public void leadtek(){
        Uri uri= Uri.parse("https://www.leadtek.com/cht/");
        go(uri);
    }
    public void tul(){
        Uri uri= Uri.parse("http://www.tul.com.tw/tw/");
        go(uri);
    }
    public void kingston(){
        Uri uri= Uri.parse("https://www.kingston.com/tw");
        go(uri);
    }
    public void vcolor(){
        Uri uri= Uri.parse("http://www.v-color.com.tw/zh_TW/");
        go(uri);
    }
    public void gskill(){
        Uri uri= Uri.parse("https://www.gskill.com/tw/");
        go(uri);
    }
    public void transcend(){
        Uri uri= Uri.parse("https://tw.transcend-info.com/");
        go(uri);
    }
    public void umax(){
        Uri uri= Uri.parse("http://www.umax.com/");
        go(uri);
    }
    public void micron(){
        Uri uri= Uri.parse("https://tw.micron.com/micron-in-taiwan");
        go(uri);
    }
    public void adata(){
        Uri uri= Uri.parse("http://www.adata.com/tw/");
        go(uri);
    }
    public void samsung(){
        Uri uri= Uri.parse("https://www.samsung.com/semiconductor/minisite/ssd/");
        go(uri);
    }
    public void hikvision(){
        Uri uri= Uri.parse("http://www1.hikvision.com/cn/index.html");
        go(uri);
    }
    public void pioneer(){
        Uri uri= Uri.parse("http://www.pioneer-twn.com.tw/");
        go(uri);
    }
    public void sandisk(){
        Uri uri= Uri.parse("https://www.sandisk.com.tw/");
        go(uri);
    }
    public void liteon(){
        Uri uri= Uri.parse("https://www.liteon.com/");
        go(uri);
    }
    public void plextor(){
        Uri uri= Uri.parse("http://www.goplextor.com/tw");
        go(uri);
    }
    public void wd(){
        Uri uri= Uri.parse("https://www.wdc.com/zh-tw/");
        go(uri);
    }
    public void toshiba(){
        Uri uri= Uri.parse("http://www.grainew.com.tw/index.asp");
        go(uri);
    }
    public void seagate(){
        Uri uri= Uri.parse("https://www.seagate.com/tw/zh");
        go(uri);
    }
    public void hgst(){
        Uri uri= Uri.parse("https://www.hgst.com/zh-hant");
        go(uri);
    }

    public void go(Uri uri){
        it = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(it);
    }

}

