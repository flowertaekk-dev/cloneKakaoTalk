package com.example.clonekakaotalk.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Profile {
    // profile image
    private int profileImageId;

    // user's nickname
    private final String nickname;

    // user's profile name. (Sorry. Kind of confusing)
    private final String profileName;
}
