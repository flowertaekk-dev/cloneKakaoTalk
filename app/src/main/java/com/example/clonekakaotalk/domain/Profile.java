package com.example.clonekakaotalk.domain;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Profile implements Parcelable {
    // profile image
    private int profileImageId;

    // user's nickname
    private String nickname;

    // user's profile name. (Sorry. Kind of confusing)
    private String profileName;

    @Builder
    public Profile(int profileImageId, String nickname, String profileName) {
        this.profileImageId = profileImageId;
        this.nickname = nickname;
        this.profileName = profileName;
    }


    // ---------------------------------------------------------------------
    // Parcelable

    public Profile(Parcel parcel) {
        this.profileImageId = parcel.readInt();
        this.nickname = parcel.readString();
        this.profileName = parcel.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        @Override
        public Profile createFromParcel(Parcel parcel) {
            return new Profile(parcel);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this.profileImageId);
        parcel.writeString(this.nickname);
        parcel.writeString(this.profileName);
    }
}
