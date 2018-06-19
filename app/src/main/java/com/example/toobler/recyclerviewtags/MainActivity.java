package com.example.toobler.recyclerviewtags;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView tagsRecyclerView;
    EditText tagsEditText;
    ArrayList<RecyclerModel> recyclerModels = new ArrayList<>(  );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        tagsRecyclerView = findViewById( R.id.tagsRecyclerView );
        tagsEditText = findViewById( R.id.tagsEditText );
        tagsEditText.setOnEditorActionListener( new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Toast.makeText( MainActivity.this,"hello",Toast.LENGTH_SHORT );
                    String str = tagsEditText.getText().toString();
                    if(str != null && !str.equals( "" )) {
                        getUpdateData( str );
                        tagsEditText.setText( null );
                        RecyclerAdapter adapter = new RecyclerAdapter( MainActivity.this, recyclerModels );
                        FlexboxLayoutManager gridLayout = new FlexboxLayoutManager( MainActivity.this );
                        tagsRecyclerView.setLayoutManager( gridLayout );
                        tagsRecyclerView.setAdapter( adapter );
                    }
                }
                return false;
            }
        } );
    }

    private void getUpdateData(String str) {
        RecyclerModel model = new RecyclerModel( str );
        recyclerModels.add( model );
    }
}
