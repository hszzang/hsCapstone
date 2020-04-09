package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ViewPager mViewPager;
    MenuItem search;
    SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
    final Context context = this;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0,1,0,"지역 삭제");
        menu.add(0,2,0,"지도");

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        search=menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        searchView.setQueryHint("검색어를 입력하세요.");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(getApplicationContext(),location_listActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "[검색버튼클릭] 검색어 = "+query, Toast.LENGTH_LONG).show();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(MainActivity.this, "입력하고있는 단어 = "+newText, Toast.LENGTH_LONG).show();
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);

    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()) {

            case 1:
                final CharSequence[] items = {"대구","서울"};
                final boolean[] checkeditems = new boolean[]{false, false};
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                alertDialogBuilder.setTitle("삭제할 지역을 선택하세요");
                alertDialogBuilder.setMultiChoiceItems(items,checkeditems ,new DialogInterface.OnMultiChoiceClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id, boolean isChecked) {
                        checkeditems[id] = isChecked;
                    }
                });


                alertDialogBuilder.setNegativeButton("취소", new DialogInterface.OnClickListener(){// 취소 버튼 클릭시 설정

                    public void onClick(DialogInterface dialog, int whichButton){

                        dialog.cancel();

                    }

                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                break;

            case 2:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override // 위에서 상속을 받는다는 뜻
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mViewPager = findViewById(R.id.viewpager);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(mViewPager);


    }
    public void setupViewPager(ViewPager viewPager) {
        adapter.addFragment(new dust_fragmentActivity1(), "현재 위치");
        adapter.addFragment(new dust_fragmentActivity2(), "대구");
        adapter.addFragment(new dust_fragmentActivity3(), "서울");
        viewPager.setAdapter(adapter);
    }


}

