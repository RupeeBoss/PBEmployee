package com.android.policyboss.core.models;

public class UserEntity {
    /**
     * Emp_Code : 945
     * Emp_Id : 2
     * Emp_Name : Shailendra Tewari
     * IsMasterUpdate : true
     */

    private String Emp_Code;
    private int Emp_Id;
    private String Emp_Name;
    private boolean IsMasterUpdate;
    /**
     * EmpRole : 4
     * Emp_Code : 894
     */

    private int EmpRole;

    public String getEmp_Code() {
        return Emp_Code;
    }

    public void setEmp_Code(String Emp_Code) {
        this.Emp_Code = Emp_Code;
    }

    public int getEmp_Id() {
        return Emp_Id;
    }

    public void setEmp_Id(int Emp_Id) {
        this.Emp_Id = Emp_Id;
    }

    public String getEmp_Name() {
        return Emp_Name;
    }

    public void setEmp_Name(String Emp_Name) {
        this.Emp_Name = Emp_Name;
    }

    public boolean isIsMasterUpdate() {
        return IsMasterUpdate;
    }

    public void setIsMasterUpdate(boolean IsMasterUpdate) {
        this.IsMasterUpdate = IsMasterUpdate;
    }

    public int getEmpRole() {
        return EmpRole;
    }

    public void setEmpRole(int EmpRole) {
        this.EmpRole = EmpRole;
    }
}