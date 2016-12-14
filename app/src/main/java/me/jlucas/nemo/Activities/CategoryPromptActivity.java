package me.jlucas.nemo.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;


import io.realm.Realm;
import me.jlucas.nemo.R;

public class CategoryPromptActivity extends AppCompatActivity {
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_prompt);
        Intent i = getIntent();
        String imgURI = i.getStringExtra("IMG_URI");
        System.out.println(imgURI);

        SimpleDraweeView preview = (SimpleDraweeView) findViewById(R.id.imgPreview);
        preview.setImageURI(Uri.parse(imgURI));

        Button btnOk = (Button) findViewById(R.id.btnOk);
        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        final EditText txtCategoryName = (EditText) findViewById(R.id.categoryName);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = getIntent();
                Bundle b = new Bundle();
                b.putString("CATEGORY_NAME", txtCategoryName.getText().toString());
                i.putExtras(b);
                setResult(RESULT_OK, i);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
