import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NoteMe extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
EditText e1,e2;
    Boolean b;
    int res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_me);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increment();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        int dps=75;
        final float scale = this.getResources().getDisplayMetrics().density;
        int pixels = (int) (dps * scale + 0.5f);


        FileOperations fo = new FileOperations();
        res=Integer.parseInt(fo.read("index"));

        for(int i = 1;i<=res&&res!=0;i++) {
            Button dynamicTextView = new Button(this);
            dynamicTextView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, pixels));
            dynamicTextView.setText(String.valueOf(i));
            final int a = i;
            dynamicTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FileOperations f1 = new FileOperations();
                    f1.readid(a);
                }
            });
            LinearLayout layout = (LinearLayout) findViewById(R.id.ll);
            layout.addView(dynamicTextView);
        }

    }



    //setContentView(R.layout.activity_note_me);
        /*LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));

        TextView titleView = new TextView(this);
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        titleView.setLayoutParams(lparams);
        titleView.setText("Hallo Welt!");
        layout.addView(titleView);

*/
    public void create(View view){
        Intent intent = new Intent(this,NewNote.class);
        startActivity(intent);
    }
    public void dekh(View view){
        Intent intent = new Intent(this,Dekh.class);
        startActivity(intent);
    }

    public void increment(){
        String r;
        FileOperations fo = new FileOperations();
        r=fo.read("index");
        if(!r.equalsIgnoreCase("null"))
            res=Integer.parseInt(fo.read("index"));
        else
        res=0;
        b=fo.write("index",String.valueOf(++res));
        Intent intent = new Intent(this,NewNote.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.note_me, menu);
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

        if (id == R.id.cn) {
            Intent intent = new Intent(this,NewNote.class);
            startActivity(intent);
        } else if (id == R.id.vn) {
            Intent intent = new Intent(this,Dekh.class);
            startActivity(intent);
        } else if (id == R.id.dn) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}