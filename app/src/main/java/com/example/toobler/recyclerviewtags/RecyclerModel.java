package com.example.toobler.recyclerviewtags;

import android.widget.ImageView;

/**
 * Created by toobler on 19/6/18.
 */

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
