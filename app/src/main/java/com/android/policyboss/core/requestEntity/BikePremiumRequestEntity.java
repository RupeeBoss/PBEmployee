package com.android.policyboss.core.requestEntity;

/**
 * Created by Nilesh Birhade on 03-07-2017.
 */

public class BikePremiumRequestEntity {


    /**
     * search_reference_number : SRN-MQEQEEY5-GXDT-SFTT-KWTT-ACOPJBG3FCQZ
     * secret_key : SECRET-ODARQ6JP-9V2Q-7BIM-0NNM-DNRTXRWMRTAL
     * client_key : CLIENT-GLF2SRA5-CFIF-4X2T-HC1Z-CXV4ZWQTFQ3T
     * response_version : 2.0
     */

    private String search_reference_number;
    private String secret_key;
    private String client_key;
    private String response_version;

    public String getSearch_reference_number() {
        return search_reference_number;
    }

    public void setSearch_reference_number(String search_reference_number) {
        this.search_reference_number = search_reference_number;
    }

    public String getSecret_key() {
        return secret_key;
    }

    public void setSecret_key(String secret_key) {
        this.secret_key = secret_key;
    }

    public String getClient_key() {
        return client_key;
    }

    public void setClient_key(String client_key) {
        this.client_key = client_key;
    }

    public String getResponse_version() {
        return response_version;
    }

    public void setResponse_version(String response_version) {
        this.response_version = response_version;
    }
}
