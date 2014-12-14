package kr.nectarine.uaspangyo.kr.nectarine.uaspangyo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nectarine on 2014-12-14.
 */
public class Member {
    public String name;
    public String photo;

    @SerializedName("is_helper")
    public boolean isHelper = false;
}
