package com.hadenwatne.realrockets.storage;

import com.google.gson.annotations.Expose;
import com.hadenwatne.realrockets.storage.UIBlockData;

import java.util.ArrayList;
import java.util.List;

/*
This class is serialized by Gson to store Rocket block data.
 */
public class UIMapperData {
    @Expose
    public List<UIBlockData> blockData;

    public UIMapperData(){
        blockData = new ArrayList<UIBlockData>();
    }

    public List<UIBlockData> getBlockData(){
        return blockData;
    }
}
