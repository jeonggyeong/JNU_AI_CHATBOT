package com.bae.dialogflowbot.models;

import android.app.FragmentManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bae.dialogflowbot.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class map_activity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_activity);

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment)fragmentManager
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap map) {

        LatLng SEOUL = new LatLng(37.56, 126.97);
        LatLng GWANGJU = new LatLng(35.157, 126.837);
        LatLng Jnu = new LatLng(35.176068, 126.905660);
        LatLng gong5 = new LatLng(35.17827946146618, 126.91107802601212);
        LatLng gong6 = new LatLng(35.1797675801189, 126.90862041497837);
        LatLng gong7 = new LatLng(35.17827223932976, 126.90934870576018);
        LatLng sab4 = new LatLng(35.17786504522472, 126.9095938586981);
        LatLng nong2 = new LatLng(35.17652725800159, 126.90159534479939);
        LatLng hong = new LatLng(35.17700893844574, 126.90586306168629);
        LatLng baek = new LatLng(35.17806304300721, 126.90689946100932);
        LatLng jin = new LatLng(35.177090649183626, 126.90325618204258);
        LatLng gyeong1 = new LatLng(35.17658184604899, 126.90411890378077);
        LatLng inmoon1 = new LatLng(35.17739282686363, 126.90471484426976);
        LatLng lifesci = new LatLng(35.17387227535789, 126.9112032474336);
        LatLng BTL = new LatLng(35.17387227535789, 126.9112032474336);
        LatLng busyb = new LatLng(35.18109154695949, 126.90430091327045);
        LatLng busds = new LatLng(35.18230940682342, 126.90891642203533);
        LatLng busms = new LatLng(35.181267422256184, 126.91153031580735);
        LatLng engine = new LatLng(35.178828653450736, 126.91194168022544);
        LatLng eastdoor = new LatLng(35.17688513108259, 126.91248807470838);
        LatLng ybtop = new LatLng(35.17503387541646, 126.9059606564043);
        LatLng jnubus = new LatLng(35.17012058109242, 126.90384144549921);
        LatLng sport = new LatLng(35.175023394345004, 126.91248232746405);
  /*
        정류장
        "용봉한화꿈에그린", 35.18109154695949, 126.90430091327045
        "도로교통공단/대신파크", 35.18230940682342, 126.90891642203533
        "성산맨션", 35.181267422256184, 126.91153031580735
         "전남대공과대학", 35.178828653450736, 126.91194168022544
        "전남대동문", 35.17688513108259, 126.91248807470838
        "전남대스포츠센터", 35.175023394345004, 126.91248232746405
         "북구청", 35.17349481520497, 126.91449390324848
        "북구보건소", 35.17302920211987, 126.91048865691161
        "전남대", 35.17012058109242, 126.90384144549921
        "전대치과병원", 35.17178525974846, 126.90233002022514
        "용봉2휴먼시아아파트", 35.1717117322158, 126.89825671828198
        "용봉우미아파트", 35.17768749017917, 126.90084131525892
        "유창허니문", 35.177451050153756, 126.8989270852914
        "유창아파트", 35.17696820044486, 126.89729650182416
        "전남대용봉탑", 35.17503387541646, 126.9059606564043
         */
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("서울");
        markerOptions.snippet("한국의 수도");
        map.addMarker(markerOptions);

        MarkerOptions gj = new MarkerOptions();
        gj.position(GWANGJU);
        gj.title("광주");
        gj.snippet("광주 광역시");
        map.addMarker(gj);

        MarkerOptions jnu = new MarkerOptions();
        jnu.position(Jnu);
        jnu.title("전남대학교");
        jnu.snippet("전남대 광주 캠퍼스");
        map.addMarker(jnu);

        MarkerOptions g5 = new MarkerOptions();
        g5.position(gong5);
        g5.title("공대 5호");
        g5.snippet("1층에 복사기있음");
        map.addMarker(g5);

        MarkerOptions g6 = new MarkerOptions();
        g6.position(gong6);
        g6.title("공대 6호");
        g6.snippet("1층에 복사기");
        map.addMarker(g6);

        MarkerOptions g7 = new MarkerOptions();
        g7.position(gong7);
        g7.title("공대 7호");
        g7.snippet("1층에 복사기있음");
        map.addMarker(g7);

        MarkerOptions s4 = new MarkerOptions();
        s4.position(sab4);
        s4.title("사대 4호");
        s4.snippet("1층에 복사기있음");
        map.addMarker(s4);

        MarkerOptions n2 = new MarkerOptions();
        n2.position(nong2);
        n2.title("농대 2호");
        n2.snippet("1층에 복사기있음");
        map.addMarker(n2);

        MarkerOptions lib = new MarkerOptions();
        lib.position(hong);
        lib.title("중앙도서관");
        lib.snippet("홍도 1층, 4층에 복사기있음");
        map.addMarker(lib);

        MarkerOptions lib2 = new MarkerOptions();
        lib2.position(baek);
        lib2.title("도서관 별관");
        lib2.snippet("백도 2층에 복사기있음");

        MarkerOptions j = new MarkerOptions();
        j.position(jin);
        j.title("진리관");
        j.snippet("3층에 복사기있음");
        map.addMarker(j);

        MarkerOptions gy1 = new MarkerOptions();
        gy1.position(gyeong1);
        gy1.title("경영 1호");
        gy1.snippet("1층에 복사기있음");
        map.addMarker(gy1);

        MarkerOptions in1 = new MarkerOptions();
        in1.position(inmoon1);
        in1.title("인문 1호");
        in1.snippet("1층에 복사기있음");
        map.addMarker(in1);

        MarkerOptions sci = new MarkerOptions();
        sci.position(lifesci);
        sci.title("생활과학대학");
        sci.snippet("1층에 복사기있음");
        map.addMarker(sci);

        MarkerOptions btl = new MarkerOptions();
        btl.position(BTL);
        btl.title("생활관");
        btl.snippet("BTL에 복사기있음");
        map.addMarker(btl);

        MarkerOptions yb = new MarkerOptions();
        yb.position(busyb);
        yb.title("용봉한화꿈에그린");
        yb.snippet("용봉83 419");
        map.addMarker(yb);

        MarkerOptions ds = new MarkerOptions();
        ds.position(busds);
        ds.title("도로교통공단/대신파크");
        ds.snippet("07 15 19 28 35 38 54 80 83 419 184 160");
        map.addMarker(ds);

        MarkerOptions ms = new MarkerOptions();
        ms.position(busms);
        ms.title("성산멘션");
        ms.snippet("15 19 28 35 38 54 80 83 160 184 419");
        map.addMarker(ms);

        MarkerOptions eg = new MarkerOptions();
        eg.position(engine);
        eg.title("전남대학교 공과대학");
        eg.snippet("18 19 28 38 80 83 160 184 419");
        map.addMarker(eg);

        MarkerOptions east = new MarkerOptions();
        east.position(eastdoor);
        east.title("전남대동문");
        east.snippet("송정19 일곡28 일곡38 문흥80 용봉83 나주160 419");
        map.addMarker(east);

        MarkerOptions yt = new MarkerOptions();
        yt.position(ybtop);
        yt.title("전남대동문");
        yt.snippet("마을777");
        map.addMarker(yt);

        MarkerOptions jn = new MarkerOptions();
        jn.position(jnubus);
        jn.title("전남대 정류장");
        jn.snippet("26 30 57 64 81 152 518 777");
        map.addMarker(jn);

        MarkerOptions sc = new MarkerOptions();
        sc.position(sport);
        sc.title("전남대스포츠센터");
        sc.snippet("진월07 문흥18 용전184");
        map.addMarker(sc);

        map.addMarker(jnu);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(Jnu, 15));
        map.animateCamera(CameraUpdateFactory.zoomTo(15));
    }

}