package dev.farjana.chat;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;


import dev.farjana.chat.MessageData.model.Messages;
import dev.farjana.chat.MessageData.remote.APIService;
import dev.farjana.chat.MessageData.remote.ApiUtils;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{


    private static final String TAG = "";

    NavigationView navigationView;
    private static APIService mAPIService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TabLayout my_tabs = findViewById(R.id.my_tabs);
        ViewPager my_pager = findViewById(R.id.my_viewPage);
        my_tabs.setTabGravity(TabLayout.GRAVITY_FILL);

        setupViewPager(my_pager);
        my_tabs.setupWithViewPager(my_pager);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab =  findViewById(R.id.fab);

        my_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:
                        fab.show();
                        fab.setOnClickListener(view -> {

                            Snackbar.make(view, "Add new contact", Snackbar.LENGTH_LONG)
                                    .setAction("Add", null).show();
                            initiatePopupWindow();
                        });
                        break;

                    case 1:
                        fab.show();
                        fab.setOnClickListener(view -> Snackbar.make(view, "New Message", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show());
                        break;

                    case 2:
                        fab.show();
                        fab.setOnClickListener(view -> Snackbar.make(view, "Create Group", Snackbar.LENGTH_LONG)
                               .setAction("Create Group", null).show());
                        break;

                    default:
                        fab.hide();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }



    private void initiatePopupWindow() {

        mAPIService = ApiUtils.getAPIService();
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setCancelable(false);
        dialog.setTitle(Html.fromHtml("<font color='#448aff'>New Contact</font>"));

        dialog.setIcon(R.mipmap.dialogicon);

        final EditText phnNum = new EditText(this);

        final EditText Txtinput = new EditText(this);
        final LayoutInflater mapView = LayoutInflater.from(this);

        LinearLayout layout = new LinearLayout(mapView.getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setMinimumHeight(100);
        layout.setMinimumWidth(200);

        phnNum.setHint("Enter Phone Number..");
        phnNum.setHintTextColor(Color.GRAY);
        phnNum.setInputType(InputType.TYPE_CLASS_PHONE);
        phnNum.setTextColor(Color.BLACK);
        layout.addView(phnNum);

        Txtinput.setHint("Enter Message..");
        Txtinput.setHintTextColor(Color.GRAY);
        Txtinput.setTextColor(Color.BLACK);
        layout.addView(Txtinput);

        dialog.setView(layout);

        dialog.setPositiveButton("Send", (dialog1, id) -> {

            // send messages
            String messsage = Txtinput.getText().toString().trim();
            String number = phnNum.getText().toString().trim();
            if (!TextUtils.isEmpty(messsage) && !TextUtils.isEmpty(number)) {
                sendMessage(messsage, number);
            }

        })
                .setNegativeButton("Cancel ", (dialog12, which) -> {
                    //Action for "Cancel".
                });

        final AlertDialog alert = dialog.create();
        alert.show();
    }

   private void sendMessage(final String message, String number) {

        //  Toast.makeText(this,"on sendMessage method",Toast.LENGTH_SHORT).show();
        mAPIService.sendMessage(message, number).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Messages>() {

                    @Override
                    public void onCompleted() {
                        //   Toast.makeText(getBaseContext(),"on oncomplete method",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        // Toast.makeText(getBaseContext(),"on onerror method",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Messages message) {
                        //  Toast.makeText(getBaseContext(),"on ONNEXT method",Toast.LENGTH_LONG).show();
                        showResponse(message.toString());
                    }
                });
    }

    public void showResponse(String response) {
        Toast.makeText(this, response, Toast.LENGTH_LONG).show();
        Log.i(TAG, "message gone....");
    }

    private void setupViewPager(ViewPager my_pager) {
        ViewPagerAdapter vp = new ViewPagerAdapter(getSupportFragmentManager());
        vp.addMyFragment(new PEOPLE(), "CONTACTS");
        vp.addMyFragment(new CHATS(), "CHATS");
        vp.addMyFragment(new GROUPS(), "GROUPS");

        my_pager.setAdapter(vp);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.start, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.login) {
            // Handle the camera action

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_invite) {

        }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}