package com.android.policyboss.core.response;

import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.models.UserEntity;

/**
 * Created by Nilesh Birhade on 13-06-2017.
 */

public class LoginResponse extends APIResponse {

    /**
     * UserResponse : {"Emp_Code":945,"Emp_Id"git:2,"Emp_Name":"Shailendra Tewari","IsMasterUpdate":true}
     */

    private UserEntity UserResponse;

    public UserEntity getUserResponse() {
        return UserResponse;
    }

    public void setUserResponse(UserEntity UserResponse) {
        this.UserResponse = UserResponse;
    }


}
