import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class NewNote extends AppCompatActivity {
    EditText e1,e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        e1=(EditText)findViewById(R.id.fname);
        e2=(EditText)findViewById(R.id.fcontent);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void Next(View view){
        FileOperations fn = new FileOperations();
        String fname=e1.getText().toString();
        String fcontent=e2.getText().toString();

        if(fn.write(fname,fcontent)){
            Toast.makeText(getApplicationContext(), "Created", Toast.LENGTH_SHORT).show();
            File file = new File("/sdcard/files.txt");
            String s="";
            if(file.exists())
                s = fn.read("files");
            else
                fn.write("files",fname+"\n");
            fn.write("files",s+"\n"+fname);
            Intent intent = new Intent(this,NoteMe.class);
            startActivity(intent);
        }
        else
            Toast.makeText(getApplicationContext(),"Not Created",Toast.LENGTH_SHORT).show();
    }


}