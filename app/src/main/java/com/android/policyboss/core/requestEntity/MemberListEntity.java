package com.android.policyboss.core.requestEntity;

public  class MemberListEntity {
        /**
         * MemberDOB : 01-01-1989
         * MemberGender : M
         * MemberNumber : 1
         * MemberType : Adult
         * MemberTypeID : 1
         */

        private String MemberDOB;
        private String MemberGender;
        private String MemberNumber;
        private String MemberType;
        private String MemberTypeID;

        public String getMemberDOB() {
            return MemberDOB;
        }

        public void setMemberDOB(String MemberDOB) {
            this.MemberDOB = MemberDOB;
        }

        public String getMemberGender() {
            return MemberGender;
        }

        public void setMemberGender(String MemberGender) {
            this.MemberGender = MemberGender;
        }

        public String getMemberNumber() {
            return MemberNumber;
        }

        public void setMemberNumber(String MemberNumber) {
            this.MemberNumber = MemberNumber;
        }

        public String getMemberType() {
            return MemberType;
        }

        public void setMemberType(String MemberType) {
            this.MemberType = MemberType;
        }

        public String getMemberTypeID() {
            return MemberTypeID;
        }

        public void setMemberTypeID(String MemberTypeID) {
            this.MemberTypeID = MemberTypeID;
        }
    }