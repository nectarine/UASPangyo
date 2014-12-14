package kr.nectarine.uaspangyo.kr.nectarine.uaspangyo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nectarine on 2014-12-14.
 */
public class Member implements Parcelable {

    public String name;
    public String photo;

    @SerializedName("photo_origin")
    public String photoOrigin;

    public String comment;

    @SerializedName("is_helper")
    public boolean isHelper = false;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.photo);
        dest.writeString(this.photoOrigin);
        dest.writeString(this.comment);
        dest.writeByte(isHelper ? (byte) 1 : (byte) 0);
    }

    public Member() {
    }

    private Member(Parcel in) {
        this.name = in.readString();
        this.photo = in.readString();
        this.photoOrigin = in.readString();
        this.comment = in.readString();
        this.isHelper = in.readByte() != 0;
    }

    public static final Creator<Member> CREATOR = new Creator<Member>() {
        public Member createFromParcel(Parcel source) {
            return new Member(source);
        }

        public Member[] newArray(int size) {
            return new Member[size];
        }
    };
}
