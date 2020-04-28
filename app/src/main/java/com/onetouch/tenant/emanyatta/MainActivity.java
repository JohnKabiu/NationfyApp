package com.onetouch.tenant.emanyatta;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.apps.nationfy.Constants.BaseApp;
import com.apps.nationfy.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.onetouch.tenant.emanyatta.data.local.AppDatabase;

import com.onetouch.tenant.emanyatta.fragments.main.ui.ComplainFragment;
import com.onetouch.tenant.emanyatta.fragments.main.ui.HistoryFragment;
import com.onetouch.tenant.emanyatta.fragments.main.ui.HomeFragment;

import com.onetouch.tenant.emanyatta.utils.CustomTypefaceSpan;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static String token;
   // @BindView(R.id.mainContent)
   // ViewPager mainContent;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.houseBtn)
    ImageView houseBtn;
   // @BindView(R.id.tabs)
   // TabLayout tabs;
    private AppDatabase appDatabase;
    private BaseApp app;
    private PopupMenu popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_emanyatta);

        BottomNavigationView bottomNavigationBar = findViewById(R.id.BottomNavBar);
        bottomNavigationBar.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                new HomeFragment()).commit();

        ButterKnife.bind(this);

        appDatabase = AppDatabase.getDatabase(getApplicationContext());
        app = (BaseApp) getApplicationContext();

//        setupViewPager(mainContent);
//        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
//        tabs.setupWithViewPager(mainContent);
//        changeTabsFont(tabs);

        houseBtn.setOnClickListener(v -> {

            popup = new PopupMenu(MainActivity.this, v);
            popup.getMenuInflater()
                    .inflate(R.menu.menu_main, popup.getMenu());

            Menu m = popup.getMenu();
            for (int i = 0; i < m.size(); i++) {
                MenuItem mi = m.getItem(i);
                applyFontToMenuItem(mi);
            }

            popup.setOnMenuItemClickListener(item -> {

                if (item.getItemId() == R.id.action_viewHouses) {
                    Intent i = new Intent(MainActivity.this, HouseListActivity.class);
                    startActivity(i);
                } else if (item.getItemId() == R.id.action_service_providers) {
                    Toast.makeText(this, "You have Clicked On Service Providers", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.action_logout) {
                    app.settings.SetIsloggedIn(false);
                    Intent intent = new Intent(MainActivity.this, PreloginActivity.class);
                    startActivity(intent);
                }
                return true;
            });

            popup.show(); //showing popup menu
        });

    }


    //BottomNav
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.navBill:
                            selectedFragment = new HomeFragment();
                            break;

                        case R.id.navHistory:
                            selectedFragment = new HistoryFragment();
                            break;

                        case R.id.navComplains:
                            selectedFragment = new ComplainFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                            selectedFragment).commit();

                    return true;
                }
            };



    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/oxygen_light.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        //mNewTitle.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, mNewTitle.length(), 0); Use this if you want to center the items
        mi.setTitle(mNewTitle);
    }

/*

    private void changeTabsFont(TabLayout tabLayout) {
        ViewGroup childTabLayout = (ViewGroup) tabLayout.getChildAt(0);
        for (int i = 0; i < childTabLayout.getChildCount(); i++) {
            ViewGroup viewTab = (ViewGroup) childTabLayout.getChildAt(i);
            for (int j = 0; j < viewTab.getChildCount(); j++) {
                View tabTextView = viewTab.getChildAt(j);
                if (tabTextView instanceof TextView) {
                    Typeface typeface = Typeface.createFromAsset(Objects.requireNonNull(this).getAssets(), "fonts/oxygen_light.ttf");
                    ((TextView) tabTextView).setTypeface(typeface);
                    ((TextView) tabTextView).setTextSize(TypedValue.COMPLEX_UNIT_DIP, getResources().getDimension(R.dimen.text_small));
                }
            }
        }
    }

    private void setupViewPager(ViewPager viewPager) {

        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), "Bills");
        adapter.addFragment(new HistoryFragment(), "History");
        viewPager.setAdapter(adapter);

    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
*/

}
