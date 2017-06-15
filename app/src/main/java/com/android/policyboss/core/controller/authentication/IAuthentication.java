package com.android.policyboss.core.controller.authentication;

import com.android.policyboss.core.IResponseSubcriber;

/**
 * Created by Nilesh Birhade on 29-05-2017.
 */

public interface IAuthentication {
    void login(String empCode, String pass, IResponseSubcriber iResponseSubcriber);
}
