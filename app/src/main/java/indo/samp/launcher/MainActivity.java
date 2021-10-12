package indo.samp.launcher;
 
import android.app.Activity;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;
import java.nio.file.*;
import java.nio.file.Files;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.Manifest;
import android.content.pm.PackageManager;


public class MainActivity extends Activity 
{ 
    private Button connect;
	private EditText name;
	private Intent i = new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) 
	{
		if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
			||checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.MANAGE_DOCUMENTS}, 1000);
		}
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		connect = findViewById(R.id.connect);
		name = findViewById(R.id.Name);
		
	    connect.setOnClickListener(new View.OnClickListener()
	    {
		    @Override
		    public void onClick(View _view) 
			{
				writeFile("/storage/emulated/0/android/data/com.rockstargames.gtasa/files/rilapk.name", name.getText().toString());
			    i = getPackageManager().getLaunchIntentForPackage("com.rockstargames.gtasa");
				startActivity(i);
	        }
        });
    }
	public static void writeFile(String path, String str) {
        createNewFile(path);
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(new File(path), false);
            fileWriter.write(str);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null)
                    fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	private static void createNewFile(String path) {
        int lastSep = path.lastIndexOf(File.separator);
        if (lastSep > 0) {
            String dirPath = path.substring(0, lastSep);
            makeDir(dirPath);
        }

        File file = new File(path);

        try {
            if (!file.exists()) file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public static void makeDir(String path) {
        if (!isExistFile(path)) {
            File file = new File(path);
            file.mkdirs();
        }
    }
	public static boolean isExistFile(String path) {
        File file = new File(path);
        return file.exists();
    }
}
