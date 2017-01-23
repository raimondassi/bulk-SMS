package local.com.bulk_smsm;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity {
 //   private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    Button btnSendSMS;
    EditText txtphoneNo;
    EditText txtMessage;
    Button btnAttachFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSendSMS = (Button) findViewById(R.id.btnSendSMS);
        txtphoneNo = (EditText) findViewById(R.id.editTextPhoneNr);
        txtMessage = (EditText) findViewById(R.id.editTextMessage);
        btnAttachFile = (Button) findViewById(R.id.btnAttachFile);
        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMSMessage();
            }
        });
        btnAttachFile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                attachFile();
            }
        });

    }

    protected void attachFile(){
        //Find the directory for the SD Card using the API
//*Don't* hardcode "/sdcard"
        File sdcard = Environment.getExternalStorageDirectory();

//Get the text file
        File file = new File(sdcard,"file.txt");

//Read text from file
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        }
        catch (IOException e) {
            //You'll need to add proper error handling here
        }

//Find the view by its id
        TextView tv = (TextView)findViewById(R.id.text_view);

//Set the text
        tv.setText(text.toString());
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        //Log.i(TAG, "On Start .....");
//        btnSendSMS = (Button) findViewById(R.id.btnSendSMS);
//        txtphoneNo = (EditText) findViewById(R.id.editTextPhoneNr);
//        txtMessage = (EditText) findViewById(R.id.editTextMessage);
//        btnSendSMS.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                sendSMSMessage();
//            }
//        });
//    }

//    @Override
//    protected void onRestart() {
//        super.onRestart();
//       // Log.i(TAG, "On Restart .....");
//        btnSendSMS = (Button) findViewById(R.id.btnSendSMS);
//        txtphoneNo = (EditText) findViewById(R.id.editTextPhoneNr);
//        txtMessage = (EditText) findViewById(R.id.editTextMessage);
//        btnSendSMS.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                sendSMSMessage();
//            }
//        });
//    }

    protected void sendSMSMessage() {
        String phoneNo = txtphoneNo.getText().toString();
        String message = txtMessage.getText().toString();
        List<String> MobNumber = Arrays.asList(phoneNo.split(";"));
        SmsManager smsManager = SmsManager.getDefault();
        if (MobNumber != null) {
            for (int i = 0; i < MobNumber.size(); i++) {
                String tempMobileNumber = MobNumber.get(i).toString();
                smsManager.sendTextMessage(tempMobileNumber, null, message, null, null);
                try {
                    TimeUnit.SECONDS.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }





//    protected void sendSMSMessage() {
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.SEND_SMS)
//                != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                    Manifest.permission.SEND_SMS)) {
//            } else {
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.SEND_SMS},
//                        MY_PERMISSIONS_REQUEST_SEND_SMS);
//            }
//        }
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                     String phoneNo = txtphoneNo.getText().toString();
//                     String message = txtMessage.getText().toString();
//                     List<String> MobNumber = Arrays.asList(phoneNo.split(";"));
//                    SmsManager smsManager = SmsManager.getDefault();
//                    if (MobNumber != null) {
//                        for (int i = 0; i < MobNumber.size(); i++) {
//                            String tempMobileNumber = MobNumber.get(i).toString();
//                            smsManager.sendTextMessage(tempMobileNumber, null, message, null, null);
//                            try {
//                                TimeUnit.SECONDS.sleep(1);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                    Toast.makeText(getApplicationContext(), "SMS sent.",
//                            Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(getApplicationContext(),
//                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
//
//                }
//            }
//        }
//
//    }
}
