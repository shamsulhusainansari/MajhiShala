package com.knoxtech.majhishala.ui.Interface;

import com.knoxtech.majhishala.ui.Model.ItemGroup;

import java.util.List;

public interface IFirebaseLoadListener {
    void onFirebaseLoadSuccess(List<ItemGroup> itemGroupList);
    void onFirebaseLoadFailed(String message);

}
