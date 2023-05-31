package com.example.sociallo.payloads.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateUserReq {
    private String firstname;
    private String lastname;
    private String profilePicture;
}
