package com.example.instagramclone;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.UUID;

import javax.xml.transform.Result;

public class CropperActivity extends AppCompatActivity {

    String result;
    Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cropper);
        Log.d("status","i am here");

        readIntent();

        String destUri = new StringBuilder(UUID.randomUUID().toString()).append(".jpg").toString();

        // Enable some options for Cropping
        UCrop.Options options = new UCrop.Options();


        // set image for cropping
        UCrop.of(fileUri, Uri.fromFile(new File(getCacheDir(),destUri)))
                .withOptions(options)
                .withAspectRatio(4,3)
                .start(CropperActivity.this);
    }

    //finding Image uri and cropped that image means set image uri for cropping
    private void readIntent() {
        Intent intent = getIntent();
        if(intent.getExtras()!=null)
        {
            result = intent.getStringExtra("DATA");
            fileUri = Uri.parse(result);
        }
    }

    // Getting cropped image with help of onActivityResult,
    // data contain result image uri
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode==UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
            Intent returnIntent = new Intent();
            returnIntent.putExtra("RESULT", resultUri+"");
            setResult(-1, returnIntent);
            finish();
        }
        else if(resultCode==UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
        }
        else if(resultCode == RESULT_CANCELED) {
            finish();
        }
    }
}














