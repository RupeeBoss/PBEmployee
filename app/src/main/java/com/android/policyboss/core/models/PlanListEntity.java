package com.android.policyboss.core.models;

public class PlanListEntity {
            /**
             * Plan_Id : 10
             * Plan_Name : All-Addon
             * Service_Log_Id : 9514
             * Service_Log_Unique_Id : ARN-DHADUL2V-P646-YQMD-DVAV-ZW60SO885OFU
             * Insurer_Transaction_Identifier :
             * Plan_Addon_Breakup : {"addon_zero_dep_cover":234}
             */

            private int Plan_Id;
            private String Plan_Name;
            private int Service_Log_Id;
            private String Service_Log_Unique_Id;
            private String Insurer_Transaction_Identifier;
            private PlanAddonBreakupEntity Plan_Addon_Breakup;

            public int getPlan_Id() {
                return Plan_Id;
            }

            public void setPlan_Id(int Plan_Id) {
                this.Plan_Id = Plan_Id;
            }

            public String getPlan_Name() {
                return Plan_Name;
            }

            public void setPlan_Name(String Plan_Name) {
                this.Plan_Name = Plan_Name;
            }

            public int getService_Log_Id() {
                return Service_Log_Id;
            }

            public void setService_Log_Id(int Service_Log_Id) {
                this.Service_Log_Id = Service_Log_Id;
            }

            public String getService_Log_Unique_Id() {
                return Service_Log_Unique_Id;
            }

            public void setService_Log_Unique_Id(String Service_Log_Unique_Id) {
                this.Service_Log_Unique_Id = Service_Log_Unique_Id;
            }

            public String getInsurer_Transaction_Identifier() {
                return Insurer_Transaction_Identifier;
            }

            public void setInsurer_Transaction_Identifier(String Insurer_Transaction_Identifier) {
                this.Insurer_Transaction_Identifier = Insurer_Transaction_Identifier;
            }

            public PlanAddonBreakupEntity getPlan_Addon_Breakup() {
                return Plan_Addon_Breakup;
            }

            public void setPlan_Addon_Breakup(PlanAddonBreakupEntity Plan_Addon_Breakup) {
                this.Plan_Addon_Breakup = Plan_Addon_Breakup;
            }

            public static class PlanAddonBreakupEntity {
                /**
                 * addon_zero_dep_cover : 234
                 */

                private int addon_zero_dep_cover;

                public int getAddon_zero_dep_cover() {
                    return addon_zero_dep_cover;
                }

                public void setAddon_zero_dep_cover(int addon_zero_dep_cover) {
                    this.addon_zero_dep_cover = addon_zero_dep_cover;
                }
            }
        }