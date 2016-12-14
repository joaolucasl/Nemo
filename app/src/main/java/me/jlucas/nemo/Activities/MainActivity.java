package me.jlucas.nemo.Activities;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import me.jlucas.nemo.Adapters.SectionsPagerAdapter;
import me.jlucas.nemo.Fragments.MainFragment;
import me.jlucas.nemo.Models.Category;
import me.jlucas.nemo.R;
import me.jlucas.nemo.Utils.ImageUtils;
import me.jlucas.nemo.Utils.IntentCodes;

public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInteractionListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private Uri imageFileURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);
        setupDB();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCameraApp();
            }
        });

        linkTabsWithFragments();
    }

    private void linkTabsWithFragments() {
        final TabLayout tabs = (TabLayout) findViewById(R.id.navbarTabs);
        final ViewPager pager = (ViewPager) findViewById(R.id.container);

        //  We have to add a listener to update the tabs accordingly once the user swipes
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                return;
            }

            @Override
            public void onPageSelected(int position) {
                tabs.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                return;
            }
        });

        //  The opposite is also true

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                return;
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition(), true);
            }
        });

    }

    private void openCameraApp() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        this.imageFileURI = ImageUtils.newImageURI();
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, this.imageFileURI);
        startActivityForResult(cameraIntent, IntentCodes.OPEN_CAMERA);
    }

    private void promptForCategory() {
        Intent i = new Intent(this, CategoryPromptActivity.class);
        i.putExtra("IMG_URI", this.imageFileURI.toString());
        startActivityForResult(i, IntentCodes.PROMPT_CATEGORY);
    }

    private void setupDB() {
        Realm.init(this);
        RealmConfiguration dbConfig = new RealmConfiguration.Builder().initialData(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Category Cat1 = realm.createObject(Category.class);
                Cat1.setId(1).setNome("Feliz");

                Category Cat2 = realm.createObject(Category.class);
                Cat2.setId(2).setNome("Verão");

                Category Cat3 = realm.createObject(Category.class);
                Cat3.setId(3).setNome("TBT");
            }
        }).build();
        Realm.setDefaultConfiguration(dbConfig);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentCodes.OPEN_CAMERA && resultCode == RESULT_OK) {
            System.out.println(this.imageFileURI);
            promptForCategory();
        } else if (requestCode == IntentCodes.PROMPT_CATEGORY && resultCode == RESULT_OK) {
            System.out.println(data.getExtras().getString("category_name"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        return;
    }
}
