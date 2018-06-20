# Tag-view-using-recycler-view


#1. Created a tag shape in Drawable :

<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <solid android:color="#cfcfcf">
    </solid>
    <corners android:radius="20dp">
    </corners>
</shape>


#2. Created a layout for recycler view:

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="horizontal"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:padding="5dp"
    android:layout_margin="4dp"
    android:gravity="center"
    android:background="@drawable/tags_layout">
    <TextView
        android:id="@+id/tag_textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:maxLines="1"
        android:maxLength="25"
        android:ellipsize="end"
        android:padding="2dp"
        android:text="Hello"/>
    <ImageView
        android:id="@+id/tag_closeBtn"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/ic_close"/>
</LinearLayout>


#3. activity_main layout contains:

'''
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.example.file.recyclerviewtags.MainActivity">
<android.support.v4.widget.NestedScrollView
    android:layout_width="match_parent"
    android:layout_height="wrap_content">
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

<android.support.v7.widget.RecyclerView
    android:id="@+id/tagsRecyclerView"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content">
</android.support.v7.widget.RecyclerView>
'''

        <EditText
            android:id="@+id/tagsEditText"
            android:inputType="text"
            android:imeOptions="actionDone"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />

    </LinearLayout>
</android.support.v4.widget.NestedScrollView>

</LinearLayout>


#4. Created a model java class for recycler view:

public class RecyclerModel {
    private String tagText;

    public RecyclerModel(String tagText){
        this.tagText = tagText;
    }
    public String getTagText() {
        return tagText;
    }

    public void setTagText(String tagText) {
        this.tagText = tagText;
    }

}

#5. Adapter class for recycler view:

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerAdapterHolder> {
    Context context;
    ArrayList<RecyclerModel> model = new ArrayList<>(  );

    public RecyclerAdapter(Context context,ArrayList<RecyclerModel> model){
        this.context = context;
        this.model = model;
    }

    @NonNull
    @Override
    public RecyclerAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_layout, parent, false);

        return new RecyclerAdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerAdapterHolder holder, final int position) {
        final RecyclerModel mod = model.get( position );
        holder.tagTextView.setText( mod.getTagText() );
        //remove tag on click x button
        holder.tagImageView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.remove( position );
                notifyDataSetChanged();
            }
        } );
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public static class RecyclerAdapterHolder extends RecyclerView.ViewHolder {
        public TextView tagTextView;
        public ImageView tagImageView;
        public RecyclerAdapterHolder(View itemView) {
            super( itemView );
            tagTextView = itemView.findViewById( R.id.tag_textView );
            tagImageView = itemView.findViewById( R.id.tag_closeBtn );
        }
    }
}


#6: Finally, In our activity, add data to recycler on entering data in edit text and click enter:

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




