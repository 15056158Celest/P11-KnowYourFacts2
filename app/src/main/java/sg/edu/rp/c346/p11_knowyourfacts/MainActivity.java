package sg.edu.rp.c346.p11_knowyourfacts;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    ArrayList<Fragment> al;
    MyFragmentAdapter adapter;
    ViewPager vPager;
    Button btnLater;
    AlarmManager am;
    int randomFragmentNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vPager = (ViewPager) findViewById(R.id.viewpager1);
        btnLater = (Button) findViewById(R.id.btnLater);

        btnLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND,5);

                //Create a new PendingIntent and add it to Alarm Manager
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                int reqCode = 12345;
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, reqCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);

                //Get Alarm Manager instance
                am = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);

                //set the alarm
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);

            }
        });



        FragmentManager fm = getSupportFragmentManager();

        al = new ArrayList<Fragment>();
        al.add(new Frag1());
        al.add(new Frag2());
        al.add(new Frag3());

        adapter = new MyFragmentAdapter(fm, al);

        vPager.setAdapter(adapter);


    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.previous) {
            if (vPager.getCurrentItem() > 0){
                int previousPage = vPager.getCurrentItem() - 2;
                vPager.setCurrentItem(previousPage, true);
            }
            return true;
        }
        if (id == R.id.random) {
            Random rnd = new Random();
            randomFragmentNum = rnd.nextInt(150);
            if (randomFragmentNum < 50) {
                randomFragmentNum = 0;
                vPager.setCurrentItem(randomFragmentNum, true);
            } else if (randomFragmentNum < 100) {
                randomFragmentNum = 1;
                vPager.setCurrentItem(randomFragmentNum, true);
            } else {
                randomFragmentNum = 2;
                vPager.setCurrentItem(randomFragmentNum, true);

            }
        }

        if (id == R.id.next) {
            int max = vPager.getChildCount();
            if (vPager.getCurrentItem() < max-1){
                int nextPage = vPager.getCurrentItem() + 2;
                vPager.setCurrentItem(nextPage, true);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
