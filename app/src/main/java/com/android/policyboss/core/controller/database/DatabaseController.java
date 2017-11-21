package com.android.policyboss.core.controller.database;

import android.content.Context;
import android.util.Log;

import com.android.policyboss.R;
import com.android.policyboss.core.models.MakeMasterEntity;
import com.android.policyboss.core.models.ModelMasterEntity;
import com.android.policyboss.core.models.VariantMasterEntity;
import com.android.policyboss.core.models.VehicleMasterEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Realm;

/**
 * Created by Nilesh Birhade on 30-05-2017.
 */

public class DatabaseController implements IDBController {
    Realm realm;
    Context context;
    RealmDatabaseController dbController;
    HashMap<String, Integer> hashMapInsurence;
    HashMap<String, Integer> hashMapProfession;
    HashMap<String, Integer> hashmapCity;
    HashMap<String, String> hashMapBikeVarient;

    static HashMap<Integer, Integer> hasMapCarInsuranceImage;
    HashMap<String, String> hashMapAddons;


    public DatabaseController(Context context, Realm realm) {
        this.realm = realm;
        this.context = context;
        dbController = new RealmDatabaseController(this.realm);

    }


    //region Addons

    public void MapAddons() {
        hashMapAddons.put("addon_ambulance_charge_cover", "Ambulance Charge Cover");
        hashMapAddons.put("addon_consumable_cover", "Consumable Cover");
        hashMapAddons.put("addon_daily_allowance_cover", "Daily Allowance Cover");
        hashMapAddons.put("addon_engine_protector_cover", "Engine Protection Cover");
        hashMapAddons.put("addon_hospital_cash_cover", "Hospital Cash Cover");
        hashMapAddons.put("addon_hydrostatic_lock_cover", "Hydrostatic Lock Cover");
        hashMapAddons.put("addon_inconvenience_allowance_cover", "Inconvinenience Allowance Cover");
        hashMapAddons.put("addon_invoice_price_cover", "Invoice Price Cover");
        hashMapAddons.put("addon_key_lock_cover", "Key Lock Cover");
        hashMapAddons.put("addon_losstime_protection_cover", "Loss Time Protection");
        hashMapAddons.put("addon_medical_expense_cover", "Medical Expense");
        hashMapAddons.put("addon_ncb_protection_cover", "NCB Protection");
        hashMapAddons.put("addon_passenger_assistance_cover", "Passenger Assistance");
        hashMapAddons.put("addon_personal_belonging_loss_cover", "Personal Belonging-Loss Cover");
        hashMapAddons.put("addon_road_assist_cover", "24X7 RoadSide Assistance");
        hashMapAddons.put("addon_rodent_bite_cover", "Rodent bite Cover");
        hashMapAddons.put("addon_tyre_coverage_cover", "Tyre Coverage");
        hashMapAddons.put("addon_windshield_cover", "Windshield Protection");
        hashMapAddons.put("addon_zero_dep_cover", "Zero Depriciation");

    }

    public String getAddonName(String addonName) {
        hashMapAddons = new HashMap<String, String>();
        MapAddons();
        return hashMapAddons.get(addonName);
    }


    public String getAddonKey(String selectedName) {
        hashMapAddons = new HashMap<String, String>();
        MapAddons();
        String AddOnName = "";
        for (Map.Entry<String, String> item : hashMapAddons.entrySet()) {
            if (item.getValue().matches(selectedName)) {
                AddOnName = item.getKey();
                break;
            }
        }

        return AddOnName;
    }

    //endregion

    //region mapping Insurence and Profession

    public void MapInsurence() {

        hashMapInsurence.put("Bajaj Allianz", 1);
        hashMapInsurence.put("Bharti Axa", 2);
        hashMapInsurence.put("Future Generali India", 4);
        hashMapInsurence.put("HDFC ERGO", 5);
        hashMapInsurence.put("ICICI Lombard", 6);
        hashMapInsurence.put("IFFCO Tokio", 7);
        hashMapInsurence.put("Universal Sompo", 19);
        hashMapInsurence.put("Liberty Videocon", 33);
        hashMapInsurence.put("Tata AIG", 11);
        hashMapInsurence.put("New India Assurance", 12);
        hashMapInsurence.put("Kotak Mahindra", 30);
        hashMapInsurence.put("Reliance General", 9);
        hashMapInsurence.put("Royal Sundaram", 10);
        hashMapInsurence.put("SBI General ", 17);
        hashMapInsurence.put("Shriram General ", 18);
        hashMapInsurence.put("National Insurance ", 8);
        hashMapInsurence.put("L & T General ", 15);
        hashMapInsurence.put("Cholamandalam MS General ", 3);
        hashMapInsurence.put("Raheja QBE General ", 16);
        hashMapInsurence.put("Liberty Videocon General ", 33);
        hashMapInsurence.put("Star Health Insurance", 26);
        hashMapInsurence.put("Magma HDI General ", 35);
        hashMapInsurence.put("The Oriental Insurance", 13);
        hashMapInsurence.put("United India Insurance ", 14);
        hashMapInsurence.put("Religare Health Insurance", 34);


    }

    public void MapBikeVarient() {
        hashMapBikeVarient.put("BAJAJ , CHETAK , STD", "50001");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 125 KS DRUM", "50002");
        hashMapBikeVarient.put("BAJAJ , PULSAR , 150 AS", "50003");
        hashMapBikeVarient.put("BAJAJ , PULSAR , 200 AS", "50004");
        hashMapBikeVarient.put("BAJAJ , PULSAR , RS 200 NON ABS", "50005");
        hashMapBikeVarient.put("BAJAJ , V , V15", "50006");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 4G 100 CC", "50007");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , DTSI ES 125", "50008");
        hashMapBikeVarient.put("BAJAJ , AVENGER , STD", "50009");
        hashMapBikeVarient.put("BAJAJ , PULSAR , DTSI ESUGIII 180", "50010");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , DRB ES 135", "50011");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , DRB KS 135", "50012");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , ES DSK  BLACK 135", "50013");
        hashMapBikeVarient.put("BAJAJ , PULSAR , DTSI KSUGII 150", "50014");
        hashMapBikeVarient.put("BAJAJ , PULSAR , DTSI ESUGIII 150", "50015");
        hashMapBikeVarient.put("BAJAJ , XCD , XCD-KS W/O SG/LG", "50016");
        hashMapBikeVarient.put("BAJAJ , CALIBER , 110", "50017");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , KS", "50018");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , DTSI KS 125", "50019");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , DTSI ES BLACK 125", "50020");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 100 CC KS", "50021");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 125 ST", "50022");
        hashMapBikeVarient.put("BAJAJ , PULSAR , 200NS", "50023");
        hashMapBikeVarient.put("BAJAJ , CALIBER , STD", "50024");
        hashMapBikeVarient.put("BAJAJ , WAVE , STD", "50025");
        hashMapBikeVarient.put("BAJAJ , CHETAK , SUPER", "50026");
        hashMapBikeVarient.put("BAJAJ , AVENGER , STREET 150", "50027");
        hashMapBikeVarient.put("BAJAJ , AVENGER , STREET 220", "50028");
        hashMapBikeVarient.put("BAJAJ , AVENGER , CRUISE", "50029");
        hashMapBikeVarient.put("BAJAJ , M80 , MAJOR 2S", "50030");
        hashMapBikeVarient.put("BAJAJ , CALIBER , 115", "50031");
        hashMapBikeVarient.put("BAJAJ , V , V15 BSIII", "50032");
        hashMapBikeVarient.put("BAJAJ , CT , 100 B", "50033");
        hashMapBikeVarient.put("BAJAJ , PULSAR , RS 200", "50034");
        hashMapBikeVarient.put("BAJAJ , PULSAR , AS 150", "50035");
        hashMapBikeVarient.put("BAJAJ , PULSAR , RS 200 ABS", "50036");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 125 DISC BRAKE.", "50037");
        hashMapBikeVarient.put("BAJAJ , CHAMPION , 4S", "50038");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 180", "50039");
        hashMapBikeVarient.put("BAJAJ , SPIRIT , STD", "50040");
        hashMapBikeVarient.put("BAJAJ , WIND , 125", "50041");
        hashMapBikeVarient.put("BAJAJ , CT , 100 SW", "50042");
        hashMapBikeVarient.put("BAJAJ , CT , 100 AW", "50043");
        hashMapBikeVarient.put("BAJAJ , PLATINA , 100 KS", "50044");
        hashMapBikeVarient.put("BAJAJ , PULSAR , AS 200", "50045");
        hashMapBikeVarient.put("BAJAJ , BOXER , CT", "50046");
        hashMapBikeVarient.put("BAJAJ , V , V12", "50047");
        hashMapBikeVarient.put("BAJAJ , DOMINAR 400 , STANDARD", "50048");
        hashMapBikeVarient.put("BAJAJ , DOMINAR 400 , ABS", "50049");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 150 F DISC", "50050");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 150 F DRUM", "50051");
        hashMapBikeVarient.put("BAJAJ , XCD , XCD-ES W/O SG/LG", "50052");
        hashMapBikeVarient.put("BAJAJ , PLATINA , 100 CC ES", "50053");
        hashMapBikeVarient.put("BAJAJ , PULSAR , 150 CC ES", "50054");
        hashMapBikeVarient.put("BAJAJ , PULSAR , 150 CC", "50055");
        hashMapBikeVarient.put("BAJAJ , XCD , DRUM 135 CC", "50056");
        hashMapBikeVarient.put("BAJAJ , PULSAR , 200 CC", "50057");
        hashMapBikeVarient.put("BAJAJ , PULSAR , 220 CC", "50058");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 100 CC", "50059");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 125 CC", "50060");
        hashMapBikeVarient.put("BAJAJ , AVENGER , 200 CC", "50061");
        hashMapBikeVarient.put("BAJAJ , PULSAR , 180 CC", "50062");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , DRUM 135 CC", "50063");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , DISC 135 CC", "50064");
        hashMapBikeVarient.put("BAJAJ , KRISTAL , STD", "50065");
        hashMapBikeVarient.put("BAJAJ , KRISTAL , DTSI", "50066");
        hashMapBikeVarient.put("BAJAJ , KRISTAL , ES", "50067");
        hashMapBikeVarient.put("BAJAJ , KRISTAL , GF 01", "50068");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 150 CC", "50069");
        hashMapBikeVarient.put("BAJAJ , BOXER , AT", "50070");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 125 M DISC", "50071");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 100 R DR ES", "50072");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 100 T", "50073");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 100 T DRUM", "50074");
        hashMapBikeVarient.put("BAJAJ , M 80 , STD", "50075");
        hashMapBikeVarient.put("BAJAJ , CT , 100", "50076");
        hashMapBikeVarient.put("BAJAJ , AVENGER , 180CC", "50077");
        hashMapBikeVarient.put("BAJAJ , PLATINA , STD", "50078");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 5G 100CC", "50079");
        hashMapBikeVarient.put("BAJAJ , PULSAR , 200 4V R DISC", "50080");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 150 F", "50081");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 135 ES", "50082");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 135 KS", "50083");
        hashMapBikeVarient.put("BAJAJ , PULSAR , 135 CC", "50084");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , H DISC 150", "50085");
        hashMapBikeVarient.put("BAJAJ , BOXER , BM 150", "50086");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 125 M DRUM", "50087");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 100 M DRUM", "50088");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 150 S DISC", "50089");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 150 S DRUM", "50090");
        hashMapBikeVarient.put("BAJAJ , PLATINA , 100 CC", "50091");
        hashMapBikeVarient.put("BAJAJ , PLATINA , 125 CC", "50092");
        hashMapBikeVarient.put("BAJAJ , XCD , 125 CC ES", "50093");
        hashMapBikeVarient.put("BAJAJ , XCD , DISC 135 CC", "50094");
        hashMapBikeVarient.put("BAJAJ , PULSAR , 150 CC UG", "50095");
        hashMapBikeVarient.put("BAJAJ , PULSAR , 180 CC UG", "50096");
        hashMapBikeVarient.put("BAJAJ , PLATINA , ALLOY WHEELS", "50097");
        hashMapBikeVarient.put("BAJAJ , AVENGER , 220 CC", "50098");
        hashMapBikeVarient.put("BAJAJ , BOXER , 150 CC", "50099");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 100CC ES", "50100");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 100CC KS", "50101");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 125 ES DRUM", "50102");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 125 ES DISC", "50103");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 100 ES SPOKE WH", "50104");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 125 DRUM KS", "50105");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 150 ES DISC", "50106");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 100-KS SPOKE WH", "50107");
        hashMapBikeVarient.put("BAJAJ , PLATINA , 125 CC ES", "50108");
        hashMapBikeVarient.put("BAJAJ , PULSAR , 135 LS", "50109");
        hashMapBikeVarient.put("BAJAJ , PULSAR , 150 UG 4.5", "50110");
        hashMapBikeVarient.put("BAJAJ , PULSAR , 220 S", "50111");
        hashMapBikeVarient.put("BAJAJ , PULSAR , 220 SF", "50112");
        hashMapBikeVarient.put("BAJAJ , PULSAR , 180 NEW DECALS", "50113");
        hashMapBikeVarient.put("BAJAJ , PULSAR , 220 SF NEW DECALS", "50114");
        hashMapBikeVarient.put("BAJAJ , PULSAR , 220 S- NEW DECALS", "50115");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 125 T", "50116");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 125 T DISC", "50117");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 125 T DRUM", "50118");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 100 M", "50119");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 100 M DISC", "50120");
        hashMapBikeVarient.put("BAJAJ , PLATINA , SPOKE", "50121");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 100 T DISC", "50122");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 125 TX", "50123");
        hashMapBikeVarient.put("BAJAJ , DISCOVER , 125 TXT", "50124");
        hashMapBikeVarient.put("DUCATI , DIAVEL , CARBON RED", "50125");
        hashMapBikeVarient.put("EKO , COSMIC , I", "50126");
        hashMapBikeVarient.put("GLOBAL , ROCK , 100", "50127");
        hashMapBikeVarient.put("GLOBAL , EXPRESSION , PLUS 125", "50128");
        hashMapBikeVarient.put("HARLEY DAVIDSON , FXDF FAT BOB , DYNA", "50129");
        hashMapBikeVarient.put("HARLEY DAVIDSON , 883 SUPERLOW , SPORTSTER", "50130");
        hashMapBikeVarient.put("HARLEY DAVIDSON , IRON 883 , SPORTSTER", "50131");
        hashMapBikeVarient.put("HARLEY DAVIDSON , XL883R ROADSTER , SPORTSTER", "50132");
        hashMapBikeVarient.put("HARLEY DAVIDSON , XL 1200X FORTY EIGHT , SPORTSTER", "50133");
        hashMapBikeVarient.put("HARLEY DAVIDSON , XL 1200N NIGHTSTER , SPORTSTER", "50134");
        hashMapBikeVarient.put("HARLEY DAVIDSON , XR 1200X , SPORTSTER", "50135");
        hashMapBikeVarient.put("HARLEY DAVIDSON , FXDB STREET BOB , DYNA", "50136");
        hashMapBikeVarient.put("HARLEY DAVIDSON , FXDC DYNA SUPERGLIDE CUSTOM , DYNA", "50137");
        hashMapBikeVarient.put("HARLEY DAVIDSON , FLSTF FAT BOY , SOFTAIL", "50138");
        hashMapBikeVarient.put("HARLEY DAVIDSON , FLSTF FAT BOY SPECIAL , SOFTAIL", "50139");
        hashMapBikeVarient.put("HARLEY DAVIDSON , FLSTC HERITAGE SOFTAIL CLASSIC , SOFTAIL", "50140");
        hashMapBikeVarient.put("HARLEY DAVIDSON , VRSCDX NIGHTROD SPL. , V-ROD", "50141");
        hashMapBikeVarient.put("HARLEY DAVIDSON , FLHR ROAD KING , TOURING", "50142");
        hashMapBikeVarient.put("HARLEY DAVIDSON , LFHX STREET GLIDE , TOURING", "50143");
        hashMapBikeVarient.put("HARLEY DAVIDSON , FLHT CUSE ULTRA CLASSIC ELECTRA GLIDE , CVO", "50144");
        hashMapBikeVarient.put("HARLEY DAVIDSON , STREET , 750", "50145");
        hashMapBikeVarient.put("HAYABUSA , GSX , RLE", "50146");
        hashMapBikeVarient.put("HARLEY DAVIDSON , STREET , 100", "50147");
        hashMapBikeVarient.put("HERO HONDA , GLAMOUR , FI F1 (DRM)", "50148");
        hashMapBikeVarient.put("HERO HONDA , CDDAWN , STD", "50149");
        hashMapBikeVarient.put("HERO HONDA , GLAMOUR , DRUM SELF", "50150");
        hashMapBikeVarient.put("HERO HONDA , GLAMOUR , DISC SELF", "50151");
        hashMapBikeVarient.put("HERO HONDA , SPLENDOR , STD", "50152");
        hashMapBikeVarient.put("HERO HONDA , PLEASURE , BLACK", "50153");
        hashMapBikeVarient.put("HERO HONDA , CD , 100 SS", "50154");
        hashMapBikeVarient.put("HERO HONDA , CBZ , STAR", "50155");
        hashMapBikeVarient.put("HERO HONDA , PUCH , STD", "50156");
        hashMapBikeVarient.put("HERO HONDA , PASSIONPRO , LIMITED EDITION", "50157");
        hashMapBikeVarient.put("HERO HONDA , PASSIONPRO , SELF CAST", "50158");
        hashMapBikeVarient.put("HERO HONDA , CBZ , XTREME", "50159");
        hashMapBikeVarient.put("HERO HONDA , AMBITION , STD", "50160");
        hashMapBikeVarient.put("HERO HONDA , CD , 100", "50161");
        hashMapBikeVarient.put("HERO HONDA , JOY , STD", "50162");
        hashMapBikeVarient.put("HERO HONDA , PASSIONPLUS , NEW", "50163");
        hashMapBikeVarient.put("HERO HONDA , PASSIONPLUS , DRUM", "50164");
        hashMapBikeVarient.put("HERO HONDA , ACHIEVER , (DRM SELF) VISOR", "50165");
        hashMapBikeVarient.put("HERO HONDA , ACHIEVER , (DSC) VISOR", "50166");
        hashMapBikeVarient.put("HERO HONDA , ACHIEVER , (DSC SELF) VISOR", "50167");
        hashMapBikeVarient.put("HERO HONDA , DAWN , CD DAWN", "50168");
        hashMapBikeVarient.put("HERO HONDA , DAWN , CD DLX", "50169");
        hashMapBikeVarient.put("HERO HONDA , KARIZMA , STD", "50170");
        hashMapBikeVarient.put("HERO HONDA , PASSION , STD", "50171");
        hashMapBikeVarient.put("HERO HONDA , SPLENDER , SPLENDOR + DRUM", "50172");
        hashMapBikeVarient.put("HERO HONDA , SUPERSPLENDOUR , SELF", "50173");
        hashMapBikeVarient.put("HERO HONDA , GLAMOUR , STD", "50174");
        hashMapBikeVarient.put("HERO HONDA , PLEASURE , STD", "50175");
        hashMapBikeVarient.put("HERO HONDA , PLEASURE , NEW", "50176");
        hashMapBikeVarient.put("HERO HONDA , KARIZMA , ZMR", "50177");
        hashMapBikeVarient.put("HERO HONDA , ACHIEVER , (DRM)", "50178");
        hashMapBikeVarient.put("HERO HONDA , ACHIEVER , (DRM) VISOR", "50179");
        hashMapBikeVarient.put("HERO HONDA , GLAMOUR , CCR", "50180");
        hashMapBikeVarient.put("HERO HONDA , SPLENDOR , CAST", "50181");
        hashMapBikeVarient.put("HERO HONDA , SPLENDORNXG , CAST", "50182");
        hashMapBikeVarient.put("HERO HONDA , SPLENDOR , SP EDITION", "50183");
        hashMapBikeVarient.put("HERO HONDA , GLAMOUR , FI F1 (DSC SELF)", "50184");
        hashMapBikeVarient.put("HERO HONDA , CDDLX , MACHWHEEL", "50185");
        hashMapBikeVarient.put("HERO HONDA , CDDLX , SELF", "50186");
        hashMapBikeVarient.put("HERO HONDA , CDDLX , SPOKE", "50187");
        hashMapBikeVarient.put("HERO HONDA , SPLENDORPLUS , SPOKE", "50188");
        hashMapBikeVarient.put("HERO HONDA , SPLENDORPLUS , MACHWHEEL", "50189");
        hashMapBikeVarient.put("HERO HONDA , SPLENDORNXG , KICK", "50190");
        hashMapBikeVarient.put("HERO HONDA , SPLENDORNXG , SELF", "50191");
        hashMapBikeVarient.put("HERO HONDA , PASSIONPLUS , STD", "50192");
        hashMapBikeVarient.put("HERO HONDA , PASSIONPRO , SELF", "50193");
        hashMapBikeVarient.put("HERO HONDA , PASSIONPRO , KICK", "50194");
        hashMapBikeVarient.put("HERO HONDA , SUPER SPLENDOR , STD", "50195");
        hashMapBikeVarient.put("HERO HONDA , HUNK , DSS", "50196");
        hashMapBikeVarient.put("HERO HONDA , CBX , XTREM", "50197");
        hashMapBikeVarient.put("HERO HONDA , ACHIEVER , STD", "50198");
        hashMapBikeVarient.put("HERO HONDA , KARIZMA , BLACKWHEEL", "50199");
        hashMapBikeVarient.put("HERO HONDA , KARIZMA , REDWHEEL", "50200");
        hashMapBikeVarient.put("HERO HONDA , PASSIONPLUS , SPOKE", "50201");
        hashMapBikeVarient.put("HERO HONDA , PASSIONPLUS , CAST", "50202");
        hashMapBikeVarient.put("HERO HONDA , PASSIONPRO , SPOKE", "50203");
        hashMapBikeVarient.put("HERO HONDA , PASSIONPLUSPRO , SPOKE SELF", "50204");
        hashMapBikeVarient.put("HERO HONDA , PASSIONPLUSPRO , CAST SELF", "50205");
        hashMapBikeVarient.put("HERO HONDA , GLAMOUR , DRUM KICK OLD", "50206");
        hashMapBikeVarient.put("HERO HONDA , GLAMOUR , DRUM SELF CAST", "50207");
        hashMapBikeVarient.put("HERO HONDA , GLAMOUR , DISK SELF CAST", "50208");
        hashMapBikeVarient.put("HERO HONDA , ACHIEVER , DISC SELF", "50209");
        hashMapBikeVarient.put("HERO HONDA , CBM , DISC SELF(RED WHEELS)", "50210");
        hashMapBikeVarient.put("HERO HONDA , HUNK , DSS( RED WHEELS)", "50211");
        hashMapBikeVarient.put("HERO HONDA , CDDLX , DRUM KICK SPOKE", "50212");
        hashMapBikeVarient.put("HERO HONDA , CDDLX , DRUM KICK CAST", "50213");
        hashMapBikeVarient.put("HERO HONDA , CDDLX , SELF SPOKE", "50214");
        hashMapBikeVarient.put("HERO HONDA , SPLENDORPRO , SELF SPOK (N)", "50215");
        hashMapBikeVarient.put("HERO HONDA , SPLENDORPRO , KICK SPO (N)", "50216");
        hashMapBikeVarient.put("HERO HONDA , SPLENDORPRO , KICK CAST (N)", "50217");
        hashMapBikeVarient.put("HERO HONDA , SPLENDORPRO , KICK PRO (N)", "50218");
        hashMapBikeVarient.put("HERO HONDA , SPLENDER , PLUS CAST SS", "50219");
        hashMapBikeVarient.put("HERO MOTOR CORP , ACHIVER , 150 DS", "50220");
        hashMapBikeVarient.put("HERO HONDA , PASSION , X PRO DRUM KICK CAST", "50221");
        hashMapBikeVarient.put("HERO HONDA , PASSION , X PRO DRUM SELF SPOKE", "50222");
        hashMapBikeVarient.put("HERO HONDA , PASSION , X PRO DRUM SELF CAST", "50223");
        hashMapBikeVarient.put("HERO HONDA , PASSION , X PRO DRUM DISC SELF", "50224");
        hashMapBikeVarient.put("HERO MOTOR CORP , XTREME , DISK SELF", "50225");
        hashMapBikeVarient.put("HERO MOTOR CORP , XTREME , REAR DISK", "50226");
        hashMapBikeVarient.put("HERO HONDA , PASSIONPRO , TR", "50227");
        hashMapBikeVarient.put("HERO MOTOR CORP , MAESTRO , EDGE VX DRS", "50228");
        hashMapBikeVarient.put("HERO MOTOR CORP , MAESTRO , EDGE LX DRS", "50229");
        hashMapBikeVarient.put("HERO MOTOR CORP , DUET , LX", "50230");
        hashMapBikeVarient.put("HERO MOTOR CORP , DUET , VX", "50231");
        hashMapBikeVarient.put("HERO HONDA , SPLENDER , I SMART 110", "50232");
        hashMapBikeVarient.put("HERO HONDA , SPLENDER , I SMART DSC", "50233");
        hashMapBikeVarient.put("HERO MOTOR CORP , XTREME , SPORTS", "50234");
        hashMapBikeVarient.put("HERO HONDA , SPLENDER , NXG SELF CAST", "50235");
        hashMapBikeVarient.put("HERO HONDA , SPLENDER , PLUS CAST", "50236");
        hashMapBikeVarient.put("HERO HONDA , SPLENDER , PRO SELF CAST", "50237");
        hashMapBikeVarient.put("HERO HONDA , SPLENDOR , NXG SELF CAST", "50238");
        hashMapBikeVarient.put("HERO HONDA , SPLENDOR , PLUS CAST", "50239");
        hashMapBikeVarient.put("HERO HONDA , SPLENDOR , PRO SELF CAST", "50240");
        hashMapBikeVarient.put("HERO HONDA , GLAMOUR , DRS", "50241");
        hashMapBikeVarient.put("HERO HONDA , GLAMOUR , DSS", "50242");
        hashMapBikeVarient.put("HERO HONDA , SPLENDER , PRO CLASSIC", "50243");
        hashMapBikeVarient.put("HERO HONDA , SPLENDER , PLUS SPOKE", "50244");
        hashMapBikeVarient.put("HERO HONDA , SPLENDER , PLUS ALLOY", "50245");
        hashMapBikeVarient.put("HERO HONDA , SPLENDER , PRO KICK SPOKE", "50246");
        hashMapBikeVarient.put("HERO HONDA , SPLENDER , PRO KICK ALLOY", "50247");
        hashMapBikeVarient.put("HERO HONDA , SPLENDER , PRO SELF SPOKE", "50248");
        hashMapBikeVarient.put("HERO HONDA , SPLENDER , PRO SELF ALLOY", "50249");
        hashMapBikeVarient.put("HERO MOTOR CORP , SUPERSPLENDER , ALLOY SELF", "50250");
        hashMapBikeVarient.put("HERO MOTOR CORP , SUPERSPLENDER , SPOKE SELF", "50251");
        hashMapBikeVarient.put("HERO HONDA , SPLENDER , NXG KICK SPOKE", "50252");
        hashMapBikeVarient.put("HERO HONDA , SPLENDER , NXG SELF SPOKE", "50253");
        hashMapBikeVarient.put("HERO HONDA , SPLENDER , NXG KICK ALLOY", "50254");
        hashMapBikeVarient.put("HERO HONDA , SPLENDER , NXG SELF ALLOY", "50255");
        hashMapBikeVarient.put("HERO HONDA , PASSION , X PRO DRUM KICK SPOKE", "50256");
        hashMapBikeVarient.put("HERO HONDA , GLAMOUR , ALLOY DISC", "50257");
        hashMapBikeVarient.put("HERO HONDA , GLAMOUR , ALLOY DRUM", "50258");
        hashMapBikeVarient.put("HERO MOTOR CORP , KARIZAMA , R", "50259");
        hashMapBikeVarient.put("HERO HONDA , PLEASURE , SELF START", "50260");
        hashMapBikeVarient.put("HERO MOTOR CORP , XTREME , REAR DRUM", "50261");
        hashMapBikeVarient.put("HERO HONDA , PASSION , PRO KS", "50262");
        hashMapBikeVarient.put("HERO HONDA , PASSION , PRO ES", "50263");
        hashMapBikeVarient.put("HERO HONDA , PASSION , PRO ES DRUM BRAKE", "50264");
        hashMapBikeVarient.put("HERO HONDA , PASSION , PRO ES DISC BRAKE", "50265");
        hashMapBikeVarient.put("HERO HONDA , PASSION , PRO TR", "50266");
        hashMapBikeVarient.put("HERO MOTOR CORP , MAESTRO , STD", "50267");
        hashMapBikeVarient.put("HERO MOTOR CORP , IGNITOR , 125 CC", "50268");
        hashMapBikeVarient.put("HERO MOTOR CORP , IMPULSE , STD", "50269");
        hashMapBikeVarient.put("HERO MOTOR CORP , HF DLX , CAST SELF", "50270");
        hashMapBikeVarient.put("HERO HONDA , CBZ , XTREME", "50271");
        hashMapBikeVarient.put("HERO MOTOR CORP , HF DLX , ALLOY KICK START", "50272");
        hashMapBikeVarient.put("HERO MOTOR CORP , HF DLX , ALLOY SELF START", "50273");
        hashMapBikeVarient.put("HERO MOTOR CORP , HF DLX , SPOKE SELF START", "50274");
        hashMapBikeVarient.put("HERO MOTOR CORP , HF DLX , SPOKE KICK START", "50275");
        hashMapBikeVarient.put("HERO MOTOR CORP , HF DLX , ECO", "50276");
        hashMapBikeVarient.put("HERO MOTOR CORP , HF DLX , DRS CCR", "50277");
        hashMapBikeVarient.put("HERO MOTOR CORP , XTREME , DDS CCR", "50278");
        hashMapBikeVarient.put("HERO MOTOR CORP , HF DLX , DRUM SELF SPOKE", "50279");
        hashMapBikeVarient.put("HERO HONDA , PASSION , X-PRO DRS", "50280");
        hashMapBikeVarient.put("HERO MOTOR CORP , IGNITOR , DRS", "50281");
        hashMapBikeVarient.put("HERO MOTOR CORP , IGNITOR , DSS", "50282");
        hashMapBikeVarient.put("HERO MOTOR CORP , CDDELUXE , SPOKE", "50283");
        hashMapBikeVarient.put("HERO MOTOR CORP , CDDELUXE , CAST", "50284");
        hashMapBikeVarient.put("HERO MOTOR CORP , CDDELUXE , CAST SELF", "50285");
        hashMapBikeVarient.put("HERO HONDA , CBZ , XTREME DSS", "50286");
        hashMapBikeVarient.put("HERO HONDA , CBZ , XTREME DDS", "50287");
        hashMapBikeVarient.put("HERO HONDA , PASSION , X-PRO DSS", "50288");
        hashMapBikeVarient.put("HERO HONDA , GLAMOUR , DRUM SELF", "50289");
        hashMapBikeVarient.put("HERO HONDA , HUNK , DISC", "50290");
        hashMapBikeVarient.put("HERO HONDA , HUNK , DRUM", "50291");
        hashMapBikeVarient.put("HERO MOTOR CORP , HF DLX , ALLOY KICK", "50292");
        hashMapBikeVarient.put("HERO MOTOR CORP , HF DLX , ALLOY SELF", "50293");
        hashMapBikeVarient.put("HERO MOTOR CORP , HF DLX , SPOKE SELF", "50294");
        hashMapBikeVarient.put("HERO MOTOR CORP , HF DLX , SPOKE KICK", "50295");
        hashMapBikeVarient.put("HERO MOTOR CORP , HF DAWN , KS", "50296");
        hashMapBikeVarient.put("HERO HONDA , SPLENDER , I SMART", "50297");
        hashMapBikeVarient.put("HERO HONDA , CDDAWN , STD.", "50298");
        hashMapBikeVarient.put("HERO MOTOR CORP , CDDELUXE , STD", "50299");
        hashMapBikeVarient.put("HERO HONDA , SPLENDER , PLUS", "50300");
        hashMapBikeVarient.put("HERO HONDA , SPLENDER , PRO", "50301");
        hashMapBikeVarient.put("HERO HONDA , SPLENDER , NXG", "50302");
        hashMapBikeVarient.put("HERO MOTOR CORP , SUPERSPLENDER , STD", "50303");
        hashMapBikeVarient.put("HERO HONDA , PASSION , PRO", "50304");
        hashMapBikeVarient.put("HERO HONDA , GLAMOUR , STD", "50305");
        hashMapBikeVarient.put("HERO HONDA , GLAMOUR , FI STD", "50306");
        hashMapBikeVarient.put("HERO MOTOR CORP , ACHIVER , STD", "50307");
        hashMapBikeVarient.put("HERO HONDA , CBZ , STD", "50308");
        hashMapBikeVarient.put("HERO HONDA , PLEASURE , STD", "50309");
        hashMapBikeVarient.put("HERO HONDA , HUNK , STD", "50310");
        hashMapBikeVarient.put("HERO MOTOR CORP , KARIZAMA , STD", "50311");
        hashMapBikeVarient.put("HERO MOTOR CORP , KARIZAMA , ZMR", "50312");
        hashMapBikeVarient.put("HONDA MOTORS , LIVO , 110 CBF G ID", "50313");
        hashMapBikeVarient.put("HONDA MOTORS , LIVO , 110 CBF G", "50314");
        hashMapBikeVarient.put("HONDA MOTORS , CB HORNET , 160 CBS", "50315");
        hashMapBikeVarient.put("HERO HONDA , CD , 110 DX", "50316");
        hashMapBikeVarient.put("HONDA MOTORS , LIVO , DLX", "50317");
        hashMapBikeVarient.put("HONDA MOTORS , SHINE , CB 125 SP", "50318");
        hashMapBikeVarient.put("HONDA MOTORS , SHINE , CB CBS", "50319");
        hashMapBikeVarient.put("HONDA MOTORS , SHINE , CB SP DISC", "50320");
        hashMapBikeVarient.put("HONDA MOTORS , LIVO , 110 DRUM SELF", "50321");
        hashMapBikeVarient.put("HONDA MOTORS , LIVO , 110 DISC SELF", "50322");
        hashMapBikeVarient.put("HONDA MOTORS , SHINE , CB SP CBS", "50323");
        hashMapBikeVarient.put("HONDA MOTORS , SHINE , CB SP DR", "50324");
        hashMapBikeVarient.put("HONDA MOTORS , CB HORNET , 160R", "50325");
        hashMapBikeVarient.put("HONDA MOTORS , SHINE , CB 125 SP CBF", "50326");
        hashMapBikeVarient.put("HONDA MOTORS , SHINE , SP DR ES", "50327");
        hashMapBikeVarient.put("HONDA MOTORS , SHINE , SP DISC ES", "50328");
        hashMapBikeVarient.put("HONDA MOTORS , SHINE , SP CBF125", "50329");
        hashMapBikeVarient.put("HONDA MOTORS , CBX250 , TWISTER", "50330");
        hashMapBikeVarient.put("HONDA MOTORS , NAVI , SELF", "50331");
        hashMapBikeVarient.put("HONDA MOTORS , ACTIVA , HET", "50332");
        hashMapBikeVarient.put("HONDA MOTORS , SHINE , CB 125 ID", "50333");
        hashMapBikeVarient.put("HONDA MOTORS , SHINE , CB 125 2ID", "50334");
        hashMapBikeVarient.put("HONDA MOTORS , SHINE , CB 125 3ID", "50335");
        hashMapBikeVarient.put("HONDA MOTORS , CBUNICORN , CBS", "50336");
        hashMapBikeVarient.put("HONDA MOTORS , CBUNICORN , 160 STD", "50337");
        hashMapBikeVarient.put("HONDA MOTORS , CBUNICORN , DAZZLER", "50338");
        hashMapBikeVarient.put("HONDA MOTORS , CBUNICORN , 160 ID", "50339");
        hashMapBikeVarient.put("HONDA MOTORS , CBUNICORN , 160 2ID", "50340");
        hashMapBikeVarient.put("HONDA MOTORS , ACTIVA , 3G", "50341");
        hashMapBikeVarient.put("HONDA MOTORS , ACTIVA , SCV 125E", "50342");
        hashMapBikeVarient.put("HONDA MOTORS , ACTIVA , 125 STD", "50343");
        hashMapBikeVarient.put("HONDA MOTORS , ACTIVA , 125 DLX", "50344");
        hashMapBikeVarient.put("HONDA MOTORS , DREAMYUGA , KICK DRUM ALLOY", "50345");
        hashMapBikeVarient.put("HONDA MOTORS , ACTIVA , DIO", "50346");
        hashMapBikeVarient.put("HONDA MOTORS , TWISTER , DRUM", "50347");
        hashMapBikeVarient.put("HONDA MOTORS , NAVI , STD", "50348");
        hashMapBikeVarient.put("HONDA MOTORS , DIO , STD", "50349");
        hashMapBikeVarient.put("HONDA MOTORS , TWISTER , KS", "50350");
        hashMapBikeVarient.put("HONDA MOTORS , SHINE , KICK-DRUM", "50351");
        hashMapBikeVarient.put("HONDA MOTORS , DREAMYUGA , KICK DRUM SPOKE", "50352");
        hashMapBikeVarient.put("HONDA MOTORS , CBR250R , REP", "50353");
        hashMapBikeVarient.put("HERO HONDA , CD , 110 DREAM", "50354");
        hashMapBikeVarient.put("HONDA MOTORS , DREAMYUGA , SELF DISC", "50355");
        hashMapBikeVarient.put("HONDA MOTORS , CBR150 , DLX", "50356");
        hashMapBikeVarient.put("HONDA MOTORS , SHINE , STD", "50357");
        hashMapBikeVarient.put("HONDA MOTORS , DREAMYUGA , SELF DRUM ALLOY", "50358");
        hashMapBikeVarient.put("HONDA MOTORS , ETERNO , STD", "50359");
        hashMapBikeVarient.put("HONDA MOTORS , TWISTER , SS", "50360");
        hashMapBikeVarient.put("HONDA MOTORS , ACTIVA , DLX", "50361");
        hashMapBikeVarient.put("HONDA MOTORS , UNICORN , STD", "50362");
        hashMapBikeVarient.put("HONDA MOTORS , STUNNER , STD", "50363");
        hashMapBikeVarient.put("HONDA MOTORS , TWISTER , DISC", "50364");
        hashMapBikeVarient.put("HONDA MOTORS , SHINE , ETERNO", "50365");
        hashMapBikeVarient.put("HONDA MOTORS , AVIATOR , DRUM", "50366");
        hashMapBikeVarient.put("HONDA MOTORS , AVIATOR , DISC", "50367");
        hashMapBikeVarient.put("HONDA MOTORS , CBR250R , STD", "50368");
        hashMapBikeVarient.put("HONDA MOTORS , CBR250R , ABS", "50369");
        hashMapBikeVarient.put("HONDA MOTORS , DIO , KICK-DRUM", "50370");
        hashMapBikeVarient.put("HONDA MOTORS , ETERNO , ACTIVA", "50371");
        hashMapBikeVarient.put("HONDA MOTORS , ACTIVA , 110 CC", "50372");
        hashMapBikeVarient.put("HONDA MOTORS , STUNNER , CBF KS", "50373");
        hashMapBikeVarient.put("HONDA MOTORS , STUNNER , CBF FI", "50374");
        hashMapBikeVarient.put("HONDA MOTORS , STUNNER , CBF ES", "50375");
        hashMapBikeVarient.put("HONDA MOTORS , CBFSTUNNER , SELF-DISC-ALLOY", "50376");
        hashMapBikeVarient.put("HONDA MOTORS , SHINE , SELF-DISC", "50377");
        hashMapBikeVarient.put("HONDA MOTORS , SHINE , SELF-DRUM", "50378");
        hashMapBikeVarient.put("HONDA MOTORS , SHINE , KICK-DISC", "50379");
        hashMapBikeVarient.put("HONDA MOTORS , CBFSTUNNER , SELF-DRUM-ALLOY", "50380");
        hashMapBikeVarient.put("HONDA MOTORS , CBFSTUNNER , KICK-DRUM-ALLOY", "50381");
        hashMapBikeVarient.put("HONDA MOTORS , SHINE , SELF START", "50382");
        hashMapBikeVarient.put("HONDA MOTORS , STUNNER , CBF DBES", "50383");
        hashMapBikeVarient.put("HONDA MOTORS , ACTIVA , DLX 110 CC", "50384");
        hashMapBikeVarient.put("HONDA MOTORS , NEWDIO , DLX 102CC", "50385");
        hashMapBikeVarient.put("HONDA MOTORS , AVIATOR , DRUM 110 CC", "50386");
        hashMapBikeVarient.put("HONDA MOTORS , AVIATOR , DISC 110 CC", "50387");
        hashMapBikeVarient.put("HONDA MOTORS , CBUNICORN , PREMIUM", "50388");
        hashMapBikeVarient.put("HONDA MOTORS , SHINE , CB SELF DRUM", "50389");
        hashMapBikeVarient.put("HONDA MOTORS , SHINE , CB SELF DISC", "50390");
        hashMapBikeVarient.put("HONDA MOTORS , CBTWISTER , SELF DRUM", "50391");
        hashMapBikeVarient.put("HONDA MOTORS , CBTWISTER , SELF DISC", "50392");
        hashMapBikeVarient.put("HONDA MOTORS , CBR250R , STD TRICOLOR", "50393");
        hashMapBikeVarient.put("HONDA MOTORS , CBR250R , ABS TRICOLOR", "50394");
        hashMapBikeVarient.put("HONDA MOTORS , DIO , DLX", "50395");
        hashMapBikeVarient.put("HONDA MOTORS , AVIATOR , DELUXE", "50396");
        hashMapBikeVarient.put("HONDA MOTORS , AVIATOR , STD", "50397");
        hashMapBikeVarient.put("HONDA MOTORS , STUNNER , SELF DISC ALLOY", "50398");
        hashMapBikeVarient.put("HONDA MOTORS , CBUNICORN , DAZZLER 5ID", "50399");
        hashMapBikeVarient.put("HONDA MOTORS , CBUNICORN , DAZZLER 6ID", "50400");
        hashMapBikeVarient.put("HONDA MOTORS , SHINE , CB KICK DRUM", "50401");
        hashMapBikeVarient.put("HONDA MOTORS , CBTWISTER , KICK DRUM ALLOY", "50402");
        hashMapBikeVarient.put("HONDA MOTORS , CBTWISTER , SELF DISC ALLOY", "50403");
        hashMapBikeVarient.put("HONDA MOTORS , CBTWISTER , SELF DRUM ALLOY", "50404");
        hashMapBikeVarient.put("HONDA MOTORS , CBR150 , STD", "50405");
        hashMapBikeVarient.put("HONDA MOTORS , CBUNICORN , STD", "50406");
        hashMapBikeVarient.put("HONDA MOTORS , ETERNO , DELUXE", "50407");
        hashMapBikeVarient.put("HONDA MOTORS , CB TRIGGER , STD", "50408");
        hashMapBikeVarient.put("HONDA MOTORS , CB TRIGGER , DLX", "50409");
        hashMapBikeVarient.put("HONDA MOTORS , CB TRIGGER , CBS", "50410");
        hashMapBikeVarient.put("HONDA MOTORS , DREAM NEO , KICK DRUM SPOKE", "50411");
        hashMapBikeVarient.put("HONDA MOTORS , DREAM NEO , KICK DRUM ALLOY", "50412");
        hashMapBikeVarient.put("HONDA MOTORS , DREAM NEO , SELF DRUM ALLOY", "50413");
        hashMapBikeVarient.put("HONDA MOTORS , ACTIVA , I", "50414");
        hashMapBikeVarient.put("HONDA MOTORS , DREAM NEO , SPOKE DRUM ALLOY", "50415");
        hashMapBikeVarient.put("HONDA MOTORS , ACTIVA , DRUM 125", "50416");
        hashMapBikeVarient.put("HONDA MOTORS , ACTIVA , DISC 125", "50417");
        hashMapBikeVarient.put("HONDA MOTORS , SHINE , CB DX", "50418");
        hashMapBikeVarient.put("HONDA MOTORS , CBFSTUNNER , KICK-DRUM-ALLOY", "50419");
        hashMapBikeVarient.put("HONDA MOTORS , CBFSTUNNER , SELF-DISC-ALLOY", "50420");
        hashMapBikeVarient.put("HONDA MOTORS , CBFSTUNNER , SELF-DRUM-ALLOY", "50421");
        hashMapBikeVarient.put("HONDA MOTORS , DIO , SCV 110", "50422");
        hashMapBikeVarient.put("HONDA MOTORS , DREAMYUGA , KICK DRUM SPOKE", "50423");
        hashMapBikeVarient.put("HONDA MOTORS , DREAMYUGA , KICK DRUM ALLOY", "50424");
        hashMapBikeVarient.put("HONDA MOTORS , DREAMYUGA , SELF DRUM ALLOY", "50425");
        hashMapBikeVarient.put("HONDA MOTORS , CBUNICORN , DAZZLER", "50426");
        hashMapBikeVarient.put("HONDA MOTORS , DIO , DELUXE", "50427");
        hashMapBikeVarient.put("HONDA MOTORS , DIO , STD", "50428");
        hashMapBikeVarient.put("HONDA MOTORS , SHINE , STD", "50429");
        hashMapBikeVarient.put("HONDA MOTORS , ETERNO , STD", "50430");
        hashMapBikeVarient.put("HONDA MOTORS , ETERNO , DELUXE", "50431");
        hashMapBikeVarient.put("HONDA MOTORS , CBR150 , DLX", "50432");
        hashMapBikeVarient.put("HONDA MOTORS , TWISTER , KS", "50433");
        hashMapBikeVarient.put("HONDA MOTORS , TWISTER , SS", "50434");
        hashMapBikeVarient.put("HONDA MOTORS , TWISTER , DISC", "50435");
        hashMapBikeVarient.put("HONDA MOTORS , STUNNER , CBF FI", "50436");
        hashMapBikeVarient.put("HONDA MOTORS , STUNNER , CBF KS", "50437");
        hashMapBikeVarient.put("HONDA SCOOTER , CB , UNICORN", "50438");
        hashMapBikeVarient.put("HONDA SCOOTER , CB , SHINE", "50439");
        hashMapBikeVarient.put("HONDA MOTORS , CBR250R , STD", "50440");
        hashMapBikeVarient.put("HONDA MOTORS , CBR250R , ABS", "50441");
        hashMapBikeVarient.put("HONDA MOTORS , SHINE , SELF START", "50442");
        hashMapBikeVarient.put("HONDA MOTORS , SHINE , SELF-DISC", "50443");
        hashMapBikeVarient.put("HONDA MOTORS , SHINE , SELF-DRUM", "50444");
        hashMapBikeVarient.put("HONDA MOTORS , SHINE , KICK-DISC", "50445");
        hashMapBikeVarient.put("HONDA MOTORS , DIO , KICK-DRUM", "50446");
        hashMapBikeVarient.put("HONDA MOTORS , ACTIVA , DIO", "50447");
        hashMapBikeVarient.put("HONDA MOTORS , ETERNO , ACTIVA", "50448");
        hashMapBikeVarient.put("HONDA MOTORS , SHINE , ETERNO", "50449");
        hashMapBikeVarient.put("HONDA MOTORS , UNICORN , STD", "50450");
        hashMapBikeVarient.put("HONDA MOTORS , ACTIVA , 110 CC", "50451");
        hashMapBikeVarient.put("HONDA MOTORS , AVIATOR , DRUM", "50452");
        hashMapBikeVarient.put("HONDA MOTORS , AVIATOR , DISC", "50453");
        hashMapBikeVarient.put("HONDA MOTORS , STUNNER , STD", "50454");
        hashMapBikeVarient.put("HONDA MOTORS , STUNNER , CBF ES", "50455");
        hashMapBikeVarient.put("HONDA MOTORS , STUNNER , CBF DBES", "50456");
        hashMapBikeVarient.put("HONDA MOTORS , ACTIVA , DLX 110 CC", "50457");
        hashMapBikeVarient.put("HONDA MOTORS , NEWDIO , DLX 102CC", "50458");
        hashMapBikeVarient.put("HONDA MOTORS , AVIATOR , DRUM 110 CC", "50459");
        hashMapBikeVarient.put("HONDA MOTORS , AVIATOR , DISC 110 CC", "50460");
        hashMapBikeVarient.put("HONDA MOTORS , DIO , SCV110FBC", "50461");
        hashMapBikeVarient.put("HONDA MOTORS , ACTIVA , I", "50462");
        hashMapBikeVarient.put("HONDA MOTORS , ACTIVA , DLX", "50463");
        hashMapBikeVarient.put("LML , SELECT 4 , 2S KICK", "50464");
        hashMapBikeVarient.put("LML , SELECT 4 , 2S SELF", "50465");
        hashMapBikeVarient.put("LML , SELECT II , 2S KICK", "50466");
        hashMapBikeVarient.put("LML , FREEDOM , DX", "50467");
        hashMapBikeVarient.put("LML , NVSPL , 4S NEW GEN DLX", "50468");
        hashMapBikeVarient.put("LML , NVSPL , 4S NEW GEN STD", "50469");
        hashMapBikeVarient.put("LML , NVSPL , 2S NV SPL E.S.", "50470");
        hashMapBikeVarient.put("LML , NVSPL , 2S NV SPL", "50471");
        hashMapBikeVarient.put("LML , NV , 2S KICK", "50472");
        hashMapBikeVarient.put("LML , NV , 2S SELF", "50473");
        hashMapBikeVarient.put("LML , NV , 4S KICK", "50474");
        hashMapBikeVarient.put("LML , NV , 4S SELF", "50475");
        hashMapBikeVarient.put("LML , SELECT , 4 ES", "50476");
        hashMapBikeVarient.put("LML , FREEDOM , LS", "50477");
        hashMapBikeVarient.put("LML , STAR EURO , 200 SELF", "50478");
        hashMapBikeVarient.put("LML , STAR EURO , 150 ES", "50479");
        hashMapBikeVarient.put("MAHINDRA , CENTURO , NXT", "50480");
        hashMapBikeVarient.put("MAHINDRA , CENTURO , XT", "50481");
        hashMapBikeVarient.put("MAHINDRA , CENTURO , DISC", "50482");
        hashMapBikeVarient.put("MAHINDRA , MOJO , STD", "50483");
        hashMapBikeVarient.put("MAHINDRA , RODEO , RZ", "50484");
        hashMapBikeVarient.put("MAHINDRA , MOJO , 300", "50485");
        hashMapBikeVarient.put("MAHINDRA , MOJO , ABS", "50486");
        hashMapBikeVarient.put("MAHINDRA , GUSTO , DX 125", "50487");
        hashMapBikeVarient.put("MAHINDRA , GUSTO , VX 125", "50488");
        hashMapBikeVarient.put("MAHINDRA , CENTURO , N1 DISC", "50489");
        hashMapBikeVarient.put("MAHINDRA , GUSTO , DX", "50490");
        hashMapBikeVarient.put("MAHINDRA , GUSTO , VX", "50491");
        hashMapBikeVarient.put("MAHINDRA , CENTURO , DB", "50492");
        hashMapBikeVarient.put("MAHINDRA , RODEO , UZO", "50493");
        hashMapBikeVarient.put("MAHINDRA , CENTURO , O3", "50494");
        hashMapBikeVarient.put("MAHINDRA , PANTERO , V3", "50495");
        hashMapBikeVarient.put("MAHINDRA , PANTERO , V4", "50496");
        hashMapBikeVarient.put("MAHINDRA , GUSTO , HX", "50497");
        hashMapBikeVarient.put("MAHINDRA , RODEO , BASE", "50498");
        hashMapBikeVarient.put("MAHINDRA , STALLIO , V1", "50499");
        hashMapBikeVarient.put("MAHINDRA , STALLIO , V2", "50500");
        hashMapBikeVarient.put("MAHINDRA , CENTURO , O1", "50501");
        hashMapBikeVarient.put("MAHINDRA , CENTURO , N1", "50502");
        hashMapBikeVarient.put("MAHINDRA , PANTERO , T3", "50503");
        hashMapBikeVarient.put("MAHINDRA , KINE , STD", "50504");
        hashMapBikeVarient.put("MAHINDRA , FLYTE , STD", "50505");
        hashMapBikeVarient.put("MAHINDRA , DURO , STD", "50506");
        hashMapBikeVarient.put("MAHINDRA , RODEO , STD", "50507");
        hashMapBikeVarient.put("MAHINDRA , PANTERO , V2", "50508");
        hashMapBikeVarient.put("MAHINDRA , DURO , DZ 125", "50509");
        hashMapBikeVarient.put("MAHINDRA , CENTURO , ROCKSTAR", "50510");
        hashMapBikeVarient.put("MAHINDRA , CENTURO , 110", "50511");
        hashMapBikeVarient.put("MAHINDRA , PANTERO , T1", "50512");
        hashMapBikeVarient.put("MAHINDRA , PANTERO , T2", "50513");
        hashMapBikeVarient.put("MAHINDRA , PANTERO , T4", "50514");
        hashMapBikeVarient.put("MAHINDRA , PANTERO , V1", "50515");
        hashMapBikeVarient.put("PIAGGIO , APRILIA , SR 150 RACE EDTN", "50516");
        hashMapBikeVarient.put("PIAGGIO , VESPA , VXL ELEGANTE", "50517");
        hashMapBikeVarient.put("PIAGGIO , APRILIA , SR 125", "50518");
        hashMapBikeVarient.put("PIAGGIO , APRILIA , SR 150", "50519");
        hashMapBikeVarient.put("PIAGGIO , VESPA , VXL 150", "50520");
        hashMapBikeVarient.put("PIAGGIO , VESPA , SXL 150", "50521");
        hashMapBikeVarient.put("PIAGGIO , VESPA , VXL", "50522");
        hashMapBikeVarient.put("PIAGGIO , VESPA , SXL", "50523");
        hashMapBikeVarient.put("PIAGGIO , VESPA , 125", "50524");
        hashMapBikeVarient.put("PIAGGIO , VESPA , VX ELEGANTE", "50525");
        hashMapBikeVarient.put("PIAGGIO , VESPA , VX SPL EDTN", "50526");
        hashMapBikeVarient.put("PIAGGIO , VESPA , LX 125", "50527");
        hashMapBikeVarient.put("PIAGGIO , VESPA , VX 125", "50528");
        hashMapBikeVarient.put("PIAGGIO , VESPA , S", "50529");
        hashMapBikeVarient.put("ROYAL ENFIELD , CLASSIC , DOM", "50530");
        hashMapBikeVarient.put("ROYAL ENFIELD , HIMALAYAN , STD", "50531");
        hashMapBikeVarient.put("ROYAL ENFIELD , CLASSIC , 350 CC", "50532");
        hashMapBikeVarient.put("ROYAL ENFIELD , HIMALAYAN , GRANITE 410", "50533");
        hashMapBikeVarient.put("ROYAL ENFIELD , SQUADRON BLUE , STD", "50534");
        hashMapBikeVarient.put("ROYAL ENFIELD , BULLET ELECTRA , 350 UCE ES", "50535");
        hashMapBikeVarient.put("ROYAL ENFIELD , BULLET , 350 UCE", "50536");
        hashMapBikeVarient.put("ROYAL ENFIELD , BULLET ELECTRA , 350 UCE", "50537");
        hashMapBikeVarient.put("ROYAL ENFIELD , BULLET , 500 UCE", "50538");
        hashMapBikeVarient.put("ROYAL ENFIELD , CLASSIC , 350 UCE", "50539");
        hashMapBikeVarient.put("ROYAL ENFIELD , CLASSIC , 500 EFI UCE", "50540");
        hashMapBikeVarient.put("ROYAL ENFIELD , THUNDERBIRD , 350 UCE", "50541");
        hashMapBikeVarient.put("ROYAL ENFIELD , THUNDERBIRD , 500 EFI UCE", "50542");
        hashMapBikeVarient.put("ROYAL ENFIELD , CONTINENTAL , GT 535 EFI UCE", "50543");
        hashMapBikeVarient.put("ROYAL ENFIELD , BULLET , 350", "50544");
        hashMapBikeVarient.put("ROYAL ENFIELD , CLASSIC , 500 UCE", "50545");
        hashMapBikeVarient.put("ROYAL ENFIELD , BULLET ELECTRA , 350", "50546");
        hashMapBikeVarient.put("ROYAL ENFIELD , THUNDERBIRD , 500", "50547");
        hashMapBikeVarient.put("ROYAL ENFIELD , THUNDERBIRD , 350", "50548");
        hashMapBikeVarient.put("ROYAL ENFIELD , THUNDERBIRD , 500 EFI", "50549");
        hashMapBikeVarient.put("ROYAL ENFIELD , STANDARD , 500", "50550");
        hashMapBikeVarient.put("ROYAL ENFIELD , ELECTRA , 5S FLUCE", "50551");
        hashMapBikeVarient.put("ROYAL ENFIELD , BULLET ELECTRA , STD", "50552");
        hashMapBikeVarient.put("ROYAL ENFIELD , THUNDERBIRD , KICKSTART", "50553");
        hashMapBikeVarient.put("ROYAL ENFIELD , BULLET ELECTRA , 550", "50554");
        hashMapBikeVarient.put("ROYAL ENFIELD , BULLET , MACHISMO 350", "50555");
        hashMapBikeVarient.put("ROYAL ENFIELD , BULLET , MACHISMO 500", "50556");
        hashMapBikeVarient.put("ROYAL ENFIELD , THUNDERBIRD , SELF", "50557");
        hashMapBikeVarient.put("ROYAL ENFIELD , CLASSIC , 350", "50558");
        hashMapBikeVarient.put("ROYAL ENFIELD , THUNDERBIRD , TWINSPARK", "50559");
        hashMapBikeVarient.put("ROYAL ENFIELD , CLASSIC , 500", "50560");
        hashMapBikeVarient.put("ROYAL ENFIELD , CLASSIC , DESERT STORM", "50561");
        hashMapBikeVarient.put("ROYAL ENFIELD , BULLET , 500", "50562");
        hashMapBikeVarient.put("ROYAL ENFIELD , CLASSIC , CHROME", "50563");
        hashMapBikeVarient.put("ROYAL ENFIELD , THUNDERBIRD , NT51", "50564");
        hashMapBikeVarient.put("ROYAL ENFIELD , BULLET , UCE STD", "50565");
        hashMapBikeVarient.put("ROYAL ENFIELD , BULLET , CONTINENTAL GT", "50566");
        hashMapBikeVarient.put("ROYAL ENFIELD , CONTINENTAL , GT", "50567");
        hashMapBikeVarient.put("ROYAL ENFIELD , ELECTRA , 350 CC", "50568");
        hashMapBikeVarient.put("ROYAL ENFIELD , CLASSIC , 500 CC", "50569");
        hashMapBikeVarient.put("ROYAL ENFIELD , STANDARD , 350 CC", "50570");
        hashMapBikeVarient.put("ROYAL ENFIELD , STANDARD , UCE", "50571");
        hashMapBikeVarient.put("ROYAL ENFIELD , ELECTRA , UCE ES DISC", "50572");
        hashMapBikeVarient.put("ROYAL ENFIELD , THUNDERBIRD , DISC ES 5S", "50573");
        hashMapBikeVarient.put("ROYAL ENFIELD , CLASSIC , 500 EFI", "50574");
        hashMapBikeVarient.put("ROYAL ENFIELD , THUNDERBIRD , CLS 500", "50575");
        hashMapBikeVarient.put("SUZUKI , GS , 150 R", "50576");
        hashMapBikeVarient.put("SUZUKI , GIXXER , 155 SINGLE DISC", "50577");
        hashMapBikeVarient.put("SUZUKI , SAMURAI , STD", "50578");
        hashMapBikeVarient.put("SUZUKI , GIXXER , 150", "50579");
        hashMapBikeVarient.put("SUZUKI , HAYATE , EP", "50580");
        hashMapBikeVarient.put("SUZUKI , FIERO , STD", "50581");
        hashMapBikeVarient.put("SUZUKI , GIXXER , DUAL TONE EDTN", "50582");
        hashMapBikeVarient.put("HAYABUSA , GSX , 150", "50583");
        hashMapBikeVarient.put("SUZUKI , ACCESS , SE", "50584");
        hashMapBikeVarient.put("SUZUKI , HAYATE , 110 CC", "50585");
        hashMapBikeVarient.put("SUZUKI , ACCESS , 125", "50586");
        hashMapBikeVarient.put("SUZUKI , ZEUS , GT 125XCD", "50587");
        hashMapBikeVarient.put("SUZUKI , ZEUS , GT 125XEU", "50588");
        hashMapBikeVarient.put("SUZUKI , ZEUS , GT 125XDU", "50589");
        hashMapBikeVarient.put("SUZUKI , ZEUS , GT 125U", "50590");
        hashMapBikeVarient.put("SUZUKI , ACCESS , 125 Z", "50591");
        hashMapBikeVarient.put("SUZUKI , HEAT , ALLOY", "50592");
        hashMapBikeVarient.put("SUZUKI , HEAT , SPOKE", "50593");
        hashMapBikeVarient.put("SUZUKI , SLINGSHOT , EU", "50594");
        hashMapBikeVarient.put("SUZUKI , SLINGSHOT , DU", "50595");
        hashMapBikeVarient.put("SUZUKI , INAZUMA , 250", "50596");
        hashMapBikeVarient.put("SUZUKI , SWISH , 125", "50597");
        hashMapBikeVarient.put("SUZUKI , SLINGSHOT , SCU", "50598");
        hashMapBikeVarient.put("SUZUKI , HAYATE , KS", "50599");
        hashMapBikeVarient.put("SUZUKI , HAYATE , SS", "50600");
        hashMapBikeVarient.put("SUZUKI , LETS , 113", "50601");
        hashMapBikeVarient.put("SUZUKI , SLINGSHOT , SEU", "50602");
        hashMapBikeVarient.put("SUZUKI , SLINGSHOT , SCD", "50603");
        hashMapBikeVarient.put("SUZUKI , GIXXER , SFZ", "50604");
        hashMapBikeVarient.put("SUZUKI , GIXXER , SF", "50605");
        hashMapBikeVarient.put("TRIUMPH , TIGER , 800 XCA", "50606");
        hashMapBikeVarient.put("TRIUMPH , TIGER , 800 XRX", "50607");
        hashMapBikeVarient.put("TRIUMPH , TIGER , 800 XCX", "50608");
        hashMapBikeVarient.put("TRIUMPH , ROCKET , X LIMITED EDITION", "50609");
        hashMapBikeVarient.put("TRIUMPH , TIGER , 800 XR", "50610");
        hashMapBikeVarient.put("TRIUMPH , THUDERBIRD , LT ABS A1", "50611");
        hashMapBikeVarient.put("TRIUMPH , THUDERBIRD , LT", "50612");
        hashMapBikeVarient.put("TRIUMPH , TIGER , 800 XC", "50613");
        hashMapBikeVarient.put("TRIUMPH , BONNEVILLE , A3", "50614");
        hashMapBikeVarient.put("TRIUMPH , BONNEVILLE , T100", "50615");
        hashMapBikeVarient.put("TRIUMPH , THURXTON , A2", "50616");
        hashMapBikeVarient.put("TRIUMPH , STREET TRIPLE , ABS A2", "50617");
        hashMapBikeVarient.put("TRIUMPH , SPEED TRIPLE , ABS A1", "50618");
        hashMapBikeVarient.put("TRIUMPH , DAYTONA , R ABS", "50619");
        hashMapBikeVarient.put("TRIUMPH , TIGER , EXPLORER ABS 1A", "50620");
        hashMapBikeVarient.put("TRIUMPH , TIGER , EXPLORER XC ABS SPOKE", "50621");
        hashMapBikeVarient.put("TRIUMPH , THUDERBIRD , STORM", "50622");
        hashMapBikeVarient.put("TRIUMPH , ROCKET , ROADSTER ABS A2 III", "50623");
        hashMapBikeVarient.put("TVS , XL , 100", "50624");
        hashMapBikeVarient.put("TVS , SCOOTY , ZEST LE", "50625");
        hashMapBikeVarient.put("TVS , APACHE , RTR 200", "50626");
        hashMapBikeVarient.put("TVS , FLAME , ES 125 CC DRUM", "50627");
        hashMapBikeVarient.put("TVS , SPORT , ES", "50628");
        hashMapBikeVarient.put("TVS , FLAME , KS 125 CC DRUM", "50629");
        hashMapBikeVarient.put("TVS , STAR , STD", "50630");
        hashMapBikeVarient.put("TVS , STAR , SPORT CVTI", "50631");
        hashMapBikeVarient.put("TVS , APACHE , RTR EFI", "50632");
        hashMapBikeVarient.put("TVS , APACHE , 150", "50633");
        hashMapBikeVarient.put("TVS , APACHE , 160 CC", "50634");
        hashMapBikeVarient.put("TVS , FLAME , STD", "50635");
        hashMapBikeVarient.put("TVS , STARCITY , STAR", "50636");
        hashMapBikeVarient.put("TVS , STARCITY , MAG ES", "50637");
        hashMapBikeVarient.put("TVS , SPORT , CVTI STD", "50638");
        hashMapBikeVarient.put("TVS , SCOOTY , PEP PLUS FASHION", "50639");
        hashMapBikeVarient.put("TVS , SCOOTY , PEP PLUS ME", "50640");
        hashMapBikeVarient.put("TVS , SCOOTY , PEP PLUS WIMBELDON", "50641");
        hashMapBikeVarient.put("TVS , FLAME , ES 125 CC DISC", "50642");
        hashMapBikeVarient.put("TVS , VICTOR , SELF", "50643");
        hashMapBikeVarient.put("TVS , APACHE , RTR 160 R.D", "50644");
        hashMapBikeVarient.put("TVS , APACHE , RTR 180 R.D", "50645");
        hashMapBikeVarient.put("TVS , STREAK , AP RED", "50646");
        hashMapBikeVarient.put("TVS , SUZUKI , STD", "50647");
        hashMapBikeVarient.put("TVS , FLAME , DS", "50648");
        hashMapBikeVarient.put("TVS , STARCITY , PLUS GOLD SPL ED", "50649");
        hashMapBikeVarient.put("TVS , SPORT , CVTI ES", "50650");
        hashMapBikeVarient.put("TVS , SCOOTY , STD", "50651");
        hashMapBikeVarient.put("TVS , SPORT , ES MAG", "50652");
        hashMapBikeVarient.put("TVS , STAR , SPORT ES", "50653");
        hashMapBikeVarient.put("TVS , VICTOR , DRUM", "50654");
        hashMapBikeVarient.put("TVS , VICTOR , DISC", "50655");
        hashMapBikeVarient.put("TVS , WEGO , V4", "50656");
        hashMapBikeVarient.put("TVS , JUPITER , DISC M.EDITION", "50657");
        hashMapBikeVarient.put("TVS , VICTOR , ES", "50658");
        hashMapBikeVarient.put("TVS , JUPITER , ZX", "50659");
        hashMapBikeVarient.put("TVS , XL , HD", "50660");
        hashMapBikeVarient.put("TVS , SCOOTY , ZEST", "50661");
        hashMapBikeVarient.put("TVS , MAX , 100", "50662");
        hashMapBikeVarient.put("TVS , STARCITY , PLUSE ES MAG", "50663");
        hashMapBikeVarient.put("TVS , STARCITY , SPOKE ES", "50664");
        hashMapBikeVarient.put("TVS , STARCITY , ES", "50665");
        hashMapBikeVarient.put("TVS , JIVE , STD", "50666");
        hashMapBikeVarient.put("TVS , APACHE , RTR 160 S.D", "50667");
        hashMapBikeVarient.put("TVS , APACHE , 180 CC", "50668");
        hashMapBikeVarient.put("TVS , APACHE , RTR 180 ABS", "50669");
        hashMapBikeVarient.put("TVS , WEGO , STD", "50670");
        hashMapBikeVarient.put("TVS , SCOOTY , TEENZ", "50671");
        hashMapBikeVarient.put("TVS , SCOOTY , PEP PLUS", "50672");
        hashMapBikeVarient.put("TVS , SCOOTY , PEP PLUS BABLICIOUS", "50673");
        hashMapBikeVarient.put("TVS , JUPITER , MATT BEIGE", "50674");
        hashMapBikeVarient.put("TVS , JUPITER , VALCANO", "50675");
        hashMapBikeVarient.put("TVS , WEGO , V3", "50676");
        hashMapBikeVarient.put("TVS , XLSUPER , HD", "50677");
        hashMapBikeVarient.put("TVS , STARCITY , SPORT", "50678");
        hashMapBikeVarient.put("SUZUKI , FIERO , F2 DISC", "50679");
        hashMapBikeVarient.put("SUZUKI , FIERO , F2 DRUM", "50680");
        hashMapBikeVarient.put("TVS , SPORT , CVTI", "50681");
        hashMapBikeVarient.put("TVS , MAX , 4R", "50682");
        hashMapBikeVarient.put("TVS , STAR , SPORT", "50683");
        hashMapBikeVarient.put("TVS , JUPITER , LE", "50684");
        hashMapBikeVarient.put("TVS , JUPITER , STALLION", "50685");
        hashMapBikeVarient.put("TVS , WEGO , V3 DUAL", "50686");
        hashMapBikeVarient.put("TVS , PHOENIX , 125 DRUM BRAKE", "50687");
        hashMapBikeVarient.put("TVS , PHOENIX , 125 DISC BRAKE", "50688");
        hashMapBikeVarient.put("TVS , SCOOTY , PEP PLUS REFRESH", "50689");
        hashMapBikeVarient.put("TVS , STREAK , REFRESH", "50690");
        hashMapBikeVarient.put("TVS , SPORT , KS", "50691");
        hashMapBikeVarient.put("TVS , WEGO , REFRESH", "50692");
        hashMapBikeVarient.put("TVS , STARCITY , PLUSE ES", "50693");
        hashMapBikeVarient.put("TVS , STARCITY , PLUSE 110 KS", "50694");
        hashMapBikeVarient.put("TVS , JIVE , MAG ES", "50695");
        hashMapBikeVarient.put("TVS , STARCITY , PLUS", "50696");
        hashMapBikeVarient.put("SUZUKI , SAMURAI , STD", "50697");
        hashMapBikeVarient.put("SUZUKI , FIERO , FX DISC", "50698");
        hashMapBikeVarient.put("SUZUKI , FIERO , FX DRUM", "50699");
        hashMapBikeVarient.put("TVS , CENTRA , STD", "50700");
        hashMapBikeVarient.put("TVS , SCOOTY , ES", "50701");
        hashMapBikeVarient.put("TVS , SCOOTY , PEP", "50702");
        hashMapBikeVarient.put("TVS , STARCITY , STD", "50703");
        hashMapBikeVarient.put("TVS , STARCITY , ALLOY", "50704");
        hashMapBikeVarient.put("TVS , VICTOR , GX", "50705");
        hashMapBikeVarient.put("TVS , XLSUPER , STD", "50706");
        hashMapBikeVarient.put("TVS , STARCITY , SPOKE", "50707");
        hashMapBikeVarient.put("TVS , STARCITY , MAG", "50708");
        hashMapBikeVarient.put("TVS , STARCITY , SPOKE REF", "50709");
        hashMapBikeVarient.put("TVS , PHOENIX , 125", "50710");
        hashMapBikeVarient.put("TVS , STARCITY , MAG REF", "50711");
        hashMapBikeVarient.put("TVS , STARCITY , SPOKE REF ES", "50712");
        hashMapBikeVarient.put("TVS , STARCITY , MAG REF ES", "50713");
        hashMapBikeVarient.put("TVS , FLAME , SR 125 ES DISC", "50714");
        hashMapBikeVarient.put("TVS , APACHE , RTR 160 CC", "50715");
        hashMapBikeVarient.put("TVS , APACHE , RTR 160 CC YELLOW", "50716");
        hashMapBikeVarient.put("TVS , APACHE , RTR 160 CC R.R YELLOW", "50717");
        hashMapBikeVarient.put("TVS , APACHE , RTR 160 CC FI", "50718");
        hashMapBikeVarient.put("TVS , APACHE , RTR 180 CC", "50719");
        hashMapBikeVarient.put("TVS , SCOOTY , TEENZ", "50720");
        hashMapBikeVarient.put("TVS , STREAK , BASE", "50721");
        hashMapBikeVarient.put("TVS , STREAK , FASHION", "50722");
        hashMapBikeVarient.put("TVS , VICTOR , GLX", "50723");
        hashMapBikeVarient.put("TVS , JIVE , 110 ES", "50724");
        hashMapBikeVarient.put("TVS , WEGO , DRUM", "50725");
        hashMapBikeVarient.put("TVS , WEGO , BASIC", "50726");
        hashMapBikeVarient.put("TVS , WEGO , DISC", "50727");
        hashMapBikeVarient.put("TVS , JUPITER , STD", "50728");
        hashMapBikeVarient.put("TVS , SPORT , STD 100 C", "50729");
        hashMapBikeVarient.put("VIBGYOR , GALLOP , 97", "50730");
        hashMapBikeVarient.put("VIBGYOR , HUNTER , VV124", "50731");
        hashMapBikeVarient.put("VIBGYOR , SHARK , VV142", "50732");
        hashMapBikeVarient.put("VIBGYOR , HUNTER , W125", "50733");
        hashMapBikeVarient.put("YAMAHA , ENTICER , 125", "50734");
        hashMapBikeVarient.put("YAMAHA , R15 , S", "50735");
        hashMapBikeVarient.put("YAMAHA , FAZER , LE", "50736");
        hashMapBikeVarient.put("YAMAHA , FAZER , STD", "50737");
        hashMapBikeVarient.put("YAMAHA , YBR(125) , 125", "50738");
        hashMapBikeVarient.put("YAMAHA , SZ , X", "50739");
        hashMapBikeVarient.put("YAMAHA , SZ , STD", "50740");
        hashMapBikeVarient.put("YAMAHA , FZ , S", "50741");
        hashMapBikeVarient.put("YAMAHA , GLADIATOR , SS BLUE", "50742");
        hashMapBikeVarient.put("YAMAHA , SZ , 16", "50743");
        hashMapBikeVarient.put("YAMAHA , YZF-R15 , STD", "50744");
        hashMapBikeVarient.put("YAMAHA , CRUX , STD", "50745");
        hashMapBikeVarient.put("YAMAHA , GLADIATOR , DX", "50746");
        hashMapBikeVarient.put("YAMAHA , GLADIATOR , STD", "50747");
        hashMapBikeVarient.put("YAMAHA , LIBERO , G5 A/W", "50748");
        hashMapBikeVarient.put("YAMAHA , LIBERO , G5 S/W", "50749");
        hashMapBikeVarient.put("YAMAHA , ALBA , A/W", "50750");
        hashMapBikeVarient.put("YAMAHA , ALBA , S/W", "50751");
        hashMapBikeVarient.put("YAMAHA , SS , 125", "50752");
        hashMapBikeVarient.put("YAMAHA , RAY , STD", "50753");
        hashMapBikeVarient.put("YAMAHA , ALPHA , STD", "50754");
        hashMapBikeVarient.put("YAMAHA , YBR , 110", "50755");
        hashMapBikeVarient.put("YAMAHA , ALBA , E/S", "50756");
        hashMapBikeVarient.put("YAMAHA , ALBA , Spoke", "50757");
        hashMapBikeVarient.put("YAMAHA , G5 , E/S", "50758");
        hashMapBikeVarient.put("YAMAHA , FZ , 16", "50759");
        hashMapBikeVarient.put("YAMAHA , SZ , S", "50760");
        hashMapBikeVarient.put("YAMAHA , SZ , RR", "50761");
        hashMapBikeVarient.put("YAMAHA , RAY , Z", "50762");
        hashMapBikeVarient.put("YAMAHA , SZ , R", "50763");
        hashMapBikeVarient.put("YAMAHA , R15 , V2", "50764");
        hashMapBikeVarient.put("YAMAHA , R15 , STD", "50765");
        hashMapBikeVarient.put("YAMAHA , FZ , S V2", "50766");
        hashMapBikeVarient.put("YAMAHA , FZ , V2", "50767");
        hashMapBikeVarient.put("YAMAHA , FZ , FI", "50768");
        hashMapBikeVarient.put("YAMAHA , FZ , S FI", "50769");
        hashMapBikeVarient.put("YAMAHA , FZ , RG", "50770");
        hashMapBikeVarient.put("YAMAHA , FZ , S RG", "50771");
        hashMapBikeVarient.put("YAMAHA , FAZER , FI", "50772");
        hashMapBikeVarient.put("YAMAHA , FAZER , FI V2.0", "50773");
        hashMapBikeVarient.put("YAMAHA , FAZER , V2", "50774");
        hashMapBikeVarient.put("YAMAHA , SZ , RR V2", "50775");
        hashMapBikeVarient.put("YAMAHA , FAZER , RG", "50776");
        hashMapBikeVarient.put("YAMAHA , FASCINO , STD", "50777");
        hashMapBikeVarient.put("YAMAHA , SALUTO , STD", "50778");
        hashMapBikeVarient.put("YAMAHA , YZF , R3", "50779");
        hashMapBikeVarient.put("YAMAHA , RX135 , STD", "50780");
        hashMapBikeVarient.put("YAMAHA , FAZER , DLX", "50781");
        hashMapBikeVarient.put("YAMAHA , FZ , FI V2.0", "50782");
        hashMapBikeVarient.put("YAMAHA , GLADIATOR , GRAFFITI", "50783");
        hashMapBikeVarient.put("YAMAHA , RX100 , STD", "50784");
        hashMapBikeVarient.put("YAMAHA , RAJDOOT , STD", "50785");
        hashMapBikeVarient.put("YAMAHA , ALPHA , CYGNUS BSIII", "50786");
        hashMapBikeVarient.put("YAMAHA , RAY , CYGNUS ZR", "50787");
        hashMapBikeVarient.put("YAMAHA , SALUTO , RX", "50788");
        hashMapBikeVarient.put("YAMAHA , ALPHA , SCPL", "50789");
        hashMapBikeVarient.put("YAMAHA , ALPHA , NEW", "50790");
        hashMapBikeVarient.put("YAMAHA , SALUTO , DISC", "50791");
        hashMapBikeVarient.put("YAMAHA , R15 , V3", "50792");
        hashMapBikeVarient.put("YAMAHA , RAY , ZR DRUM", "50793");
        hashMapBikeVarient.put("YAMAHA , RAY , ZR DISC", "50794");
        hashMapBikeVarient.put("YAMAHA , RAJDOOT , 154", "50795");
        hashMapBikeVarient.put("HONDA MOTORS , CBUNICORN , DISC", "50796");
        hashMapBikeVarient.put("KAWASAKI , 4S , CHAMPION", "50797");
        hashMapBikeVarient.put("KAWASAKI , NINJA , 300", "50798");
        hashMapBikeVarient.put("KAWASAKI , NINJA , 650", "50799");
        hashMapBikeVarient.put("KAWASAKI , NINJA , 250R", "50800");
        hashMapBikeVarient.put("KINETIC , BOSS , STD", "50801");
        hashMapBikeVarient.put("KINETIC , ZINGKINE , 80", "50802");
        hashMapBikeVarient.put("KINETIC , ZINGKINE , 80", "50803");
        hashMapBikeVarient.put("KINETIC , ZINGKINE , 80", "50804");
        hashMapBikeVarient.put("MAHINDRA , FLYTE , STD", "50805");
        hashMapBikeVarient.put("KINETIC , ZINGKINE , STD", "50806");
        hashMapBikeVarient.put("KAWASAKI , 4S , STD", "50807");
        hashMapBikeVarient.put("KINETIC , NOVA , STD", "50808");
        hashMapBikeVarient.put("KINETIC , BLAZE , STD", "50809");
        hashMapBikeVarient.put("KINETIC , BLAZE , DISK", "50810");
        hashMapBikeVarient.put("KTM , DUKE , 200", "50811");
        hashMapBikeVarient.put("KTM , DUKE , 390", "50812");
        hashMapBikeVarient.put("KTM , RC , 200", "50813");
        hashMapBikeVarient.put("KTM , RC , 390", "50814");
        hashMapBikeVarient.put("LML , BEAMER , STD", "50815");
        hashMapBikeVarient.put("LML , ENERGY , FX", "50816");
        hashMapBikeVarient.put("LML , CRD , SPPL", "50817");
        hashMapBikeVarient.put("LML , FREEDOM , PRIMA", "50818");
        hashMapBikeVarient.put("LML , FREEDOM , PRIMA 110", "50819");
        hashMapBikeVarient.put("TVS , STAR , XPRESS ES", "50820");
        hashMapBikeVarient.put("TVS , STAR , XPRESS KS", "50821");
        hashMapBikeVarient.put("LML , STAR EURO , 150 GEARED", "50822");
        hashMapBikeVarient.put("TVS , STAR , 4 KS GEARED", "50823");
        hashMapBikeVarient.put("TVS , STAR , 4 ES GEARED", "50824");
        hashMapBikeVarient.put("TVS , STAR , ES GEARLESS", "50825");
        hashMapBikeVarient.put("TVS , STAR , VIB GEARLESS", "50826");
        hashMapBikeVarient.put("TVS , STAR , VIB DB GEARLESS", "50827");
        hashMapBikeVarient.put("LML , STAR EURO , 200 KS", "50828");
        hashMapBikeVarient.put("LML , STAR EURO , 200 ES", "50829");
        hashMapBikeVarient.put("LML , STAR EURO , 200 VIB", "50830");
        hashMapBikeVarient.put("LML , STAR EURO , 200 VIB DB", "50831");
        hashMapBikeVarient.put("LML , NV , LS 2S", "50832");
        hashMapBikeVarient.put("LML , NV , LS 4S", "50833");
        hashMapBikeVarient.put("LML , SELECT II , 2S SELF", "50834");
    }

    public List<String> getBikeVarientList() {
        hashMapBikeVarient = new HashMap<String, String>();
        MapBikeVarient();
        return new ArrayList<String>(hashMapBikeVarient.keySet());

    }

    public String getBikeVarientID(String bikeName) {
        hashMapBikeVarient = new HashMap<String, String>();
        MapBikeVarient();
        return hashMapBikeVarient.get(bikeName);
    }

    public int getInsurenceID(String insurenceName) {
        hashMapInsurence = new HashMap<String, Integer>();
        MapInsurence();
        return hashMapInsurence.get(insurenceName);
    }

    public List<String> getInsurerList() {
        hashMapInsurence = new HashMap<String, Integer>();
        MapInsurence();
        return new ArrayList<String>(hashMapInsurence.keySet());

    }

    public void MapProfession() {

        hashMapProfession.put("Practicing Chartered Accountant", 1);
        hashMapProfession.put("Teacher in Govt.recognized Institutions", 2);
        hashMapProfession.put("Doctors registered with Government", 3);
        hashMapProfession.put("Defense and Para Military Service", 4);
        hashMapProfession.put("Central / State Government Employees", 5);
        hashMapProfession.put("Other", 6);

    }

    public int getProfessionalID(String professionName) {
        hashMapProfession = new HashMap<String, Integer>();
        MapProfession();
        return hashMapProfession.get(professionName);
    }


    //endregion

    //region health city

    public List<String> getHealthCity() {
        hashmapCity = new HashMap<String, Integer>();
        MapHealthCity();
        return new ArrayList<String>(hashmapCity.keySet());
    }

    public int getHealthCityID(String cityName) {
        hashmapCity = new HashMap<String, Integer>();
        MapHealthCity();
        if (hashmapCity.get(cityName) != null) {
            return hashmapCity.get(cityName);
        } else {
            return 0;
        }

    }

    public void MapHealthCity() {

        hashmapCity.put("ADILABAD", 3);
        hashmapCity.put("AGARTALA", 6);
        hashmapCity.put("AGRA", 7);
        hashmapCity.put("AHMEDABAD", 9);
        hashmapCity.put("AHMEDNAGAR", 10);
        hashmapCity.put("AIZAWL", 13);
        hashmapCity.put("AJMER", 14);
        hashmapCity.put("AKOLA", 16);
        hashmapCity.put("ALIGARH", 20);
        hashmapCity.put("ALLAHABAD", 23);
        hashmapCity.put("ALMORA", 24);
        hashmapCity.put("ALWAR", 27);
        hashmapCity.put("AMBALA", 31);
        hashmapCity.put("AMBASSA", 32);
        hashmapCity.put("AMRAVATI", 37);
        hashmapCity.put("AMRITSAR", 39);
        hashmapCity.put("ANAND", 42);
        hashmapCity.put("ANANTAPUR", 44);
        hashmapCity.put("ANANTNAG", 45);
        hashmapCity.put("ANGUL", 48);
        hashmapCity.put("ARARIA", 55);
        hashmapCity.put("AURANGABAD", 65);
        hashmapCity.put("AZAMGARH", 67);
        hashmapCity.put("BAGESHWAR", 71);
        hashmapCity.put("BAGPAT", 74);
        hashmapCity.put("BAHRAICH", 78);
        hashmapCity.put("BALAGHAT", 83);
        hashmapCity.put("BALANGIR", 84);
        hashmapCity.put("BALASORE", 85);
        hashmapCity.put("BALRAMPUR", 88);
        hashmapCity.put("BANASKANTHA", 90);
        hashmapCity.put("BANDA", 91);
        hashmapCity.put("BANGALORE", 93);
        hashmapCity.put("BANKA", 95);
        hashmapCity.put("BANKURA", 96);
        hashmapCity.put("BANSWARA", 99);
        hashmapCity.put("BARABANKI", 100);
        hashmapCity.put("BARAMULA", 103);
        hashmapCity.put("BARAN", 104);
        hashmapCity.put("BARDHAMAN", 106);
        hashmapCity.put("BAREILLY", 107);
        hashmapCity.put("BARMER", 109);
        hashmapCity.put("BARPETA", 111);
        hashmapCity.put("BASTAR", 116);
        hashmapCity.put("BASTI", 117);
        hashmapCity.put("BATHINDA", 119);
        hashmapCity.put("BEED", 122);
        hashmapCity.put("BEGUSARAI", 123);
        hashmapCity.put("BELGAUM", 124);
        hashmapCity.put("BELLARY", 125);
        hashmapCity.put("BETUL", 128);
        hashmapCity.put("BHADRAK", 130);
        hashmapCity.put("BHAGALPUR", 132);
        hashmapCity.put("BHANDARA", 134);
        hashmapCity.put("BHARATPUR", 135);
        hashmapCity.put("BHARUCH", 137);
        hashmapCity.put("BHAVNAGAR", 139);
        hashmapCity.put("BHILWARA", 141);
        hashmapCity.put("BHIND", 143);
        hashmapCity.put("BHIWANI", 144);
        hashmapCity.put("BHOJPUR", 145);
        hashmapCity.put("BHOPAL", 147);
        hashmapCity.put("BHUBANESWAR", 148);
        hashmapCity.put("BIDAR", 152);
        hashmapCity.put("BIJAPUR", 153);
        hashmapCity.put("BIJNOR", 154);
        hashmapCity.put("BIKANER", 155);
        hashmapCity.put("BILASPUR", 156);
        hashmapCity.put("BILASPUR", 157);
        hashmapCity.put("BIRBHUM", 158);
        hashmapCity.put("BISHNUPUR", 159);
        hashmapCity.put("BOKARO", 161);
        hashmapCity.put("BOMDILA", 162);
        hashmapCity.put("BONGAIGAON", 163);
        hashmapCity.put("BUDAUN", 165);
        hashmapCity.put("BULDHANA", 169);
        hashmapCity.put("BUNDI", 170);
        hashmapCity.put("BURHANPUR", 172);
        hashmapCity.put("BUXAR", 173);
        hashmapCity.put("CHAIBASA", 176);
        hashmapCity.put("CHAMBA", 179);
        hashmapCity.put("CHAMOLI", 181);
        hashmapCity.put("CHAMPAWAT", 182);
        hashmapCity.put("CHAMPHAI", 183);
        hashmapCity.put("CHANDAULI", 185);
        hashmapCity.put("CHANDEL", 186);
        hashmapCity.put("CHANDIGARH", 187);
        hashmapCity.put("CHANDRAPUR", 188);
        hashmapCity.put("CHATRA", 192);
        hashmapCity.put("CHENNAI", 196);
        hashmapCity.put("CHHINDWARA", 202);
        hashmapCity.put("CHITRADURGA", 210);
        hashmapCity.put("CHURACHANDPUR", 215);
        hashmapCity.put("CHURU", 218);
        hashmapCity.put("COIMBATORE", 220);
        hashmapCity.put("CUDDAPAH", 224);
        hashmapCity.put("CUTTACK", 226);
        hashmapCity.put("DAHOD", 231);
        hashmapCity.put("DAKSHIN DINAJPUR", 232);
        hashmapCity.put("DAKSHINA KANNADA", 233);
        hashmapCity.put("DAMOH", 237);
        hashmapCity.put("DANTEWADA", 239);
        hashmapCity.put("DAPORIJO", 240);
        hashmapCity.put("DARBHANGA", 241);
        hashmapCity.put("DARRANG", 243);
        hashmapCity.put("DATIA", 246);
        hashmapCity.put("DAUSA", 247);
        hashmapCity.put("DAVANAGERE", 248);
        hashmapCity.put("DEHRADUN", 251);
        hashmapCity.put("DEOGARH", 254);
        hashmapCity.put("DEOGHAR", 255);
        hashmapCity.put("DEORIA", 256);
        hashmapCity.put("DEWAS", 260);
        hashmapCity.put("DHALAI", 261);
        hashmapCity.put("DHAMTARI", 262);
        hashmapCity.put("DHANBAD", 263);
        hashmapCity.put("DHAR", 264);
        hashmapCity.put("DHARMAPURI", 269);
        hashmapCity.put("DHARWAD", 270);
        hashmapCity.put("DHEMAJI", 271);
        hashmapCity.put("DHENKANAL", 272);
        hashmapCity.put("DHOLPUR", 273);
        hashmapCity.put("DHUBRI", 274);
        hashmapCity.put("DHULE", 275);
        hashmapCity.put("DIBANG VALLEY", 277);
        hashmapCity.put("DIBRUGARH", 278);
        hashmapCity.put("DIMAPUR", 281);
        hashmapCity.put("DINDIGUL", 282);
        hashmapCity.put("DINDORI", 283);
        hashmapCity.put("DODA", 287);
        hashmapCity.put("DUMKA", 291);
        hashmapCity.put("DUNGARPUR", 292);
        hashmapCity.put("DURG", 293);
        hashmapCity.put("EAST CHAMPARAN", 295);
        hashmapCity.put("EAST GODAVARI", 297);
        hashmapCity.put("EAST SIANG", 300);
        hashmapCity.put("ERNAKULAM", 305);
        hashmapCity.put("ERODE", 306);
        hashmapCity.put("ETAH", 307);
        hashmapCity.put("ETAWAH", 308);
        hashmapCity.put("FAIZABAD", 310);
        hashmapCity.put("FARIDABAD", 311);
        hashmapCity.put("FARIDKOT", 312);
        hashmapCity.put("FARRUKHABAD", 313);
        hashmapCity.put("FATEHABAD", 314);
        hashmapCity.put("FATEHPUR", 317);
        hashmapCity.put("FIROZABAD", 320);
        hashmapCity.put("GADCHIROLI", 323);
        hashmapCity.put("GAJAPATI", 325);
        hashmapCity.put("GANDHINAGAR", 331);
        hashmapCity.put("GANGANAGAR", 332);
        hashmapCity.put("GANGTOK", 333);
        hashmapCity.put("GANJAM", 334);
        hashmapCity.put("GARHWA", 336);
        hashmapCity.put("GAUTAM BUDH NAGAR", 338);
        hashmapCity.put("GAYA", 339);
        hashmapCity.put("GEYZING", 340);
        hashmapCity.put("GHAZIABAD", 342);
        hashmapCity.put("GIRIDIH", 346);
        hashmapCity.put("GOALPARA", 347);
        hashmapCity.put("GODDA", 349);
        hashmapCity.put("GOLAGHAT", 353);
        hashmapCity.put("GONDA", 354);
        hashmapCity.put("GONDIA", 355);
        hashmapCity.put("GOPALGANJ", 356);
        hashmapCity.put("GORAKHPUR", 357);
        hashmapCity.put("GULBARGA", 363);
        hashmapCity.put("GUMLA", 364);
        hashmapCity.put("GUNA", 365);
        hashmapCity.put("GUNTUR", 366);
        hashmapCity.put("GURDASPUR", 367);
        hashmapCity.put("GURGAON", 368);
        hashmapCity.put("GUWAHATI", 370);
        hashmapCity.put("GWALIOR", 371);
        hashmapCity.put("HAMIRPUR", 376);
        hashmapCity.put("HAMIRPUR", 377);
        hashmapCity.put("HANUMANGARH", 380);
        hashmapCity.put("HARDA", 382);
        hashmapCity.put("HARDOI", 383);
        hashmapCity.put("HARIDWAR", 384);
        hashmapCity.put("HASSAN", 385);
        hashmapCity.put("HINGOLI", 392);
        hashmapCity.put("HISSAR", 393);
        hashmapCity.put("HOOGHLY", 396);
        hashmapCity.put("HOSHANGABAD", 397);
        hashmapCity.put("HOWRAH", 400);
        hashmapCity.put("HYDERABAD", 404);
        hashmapCity.put("IDUKKI", 407);
        hashmapCity.put("INDORE", 409);
        hashmapCity.put("ITANAGAR", 411);
        hashmapCity.put("JABALPUR", 412);
        hashmapCity.put("JAGATSINGHPUR", 414);
        hashmapCity.put("JAIPUR", 419);
        hashmapCity.put("JAISALMER", 420);
        hashmapCity.put("JAJPUR", 423);
        hashmapCity.put("JALANDHAR", 425);
        hashmapCity.put("JALAUN", 426);
        hashmapCity.put("JALGAON", 427);
        hashmapCity.put("JALNA", 428);
        hashmapCity.put("JALOR", 429);
        hashmapCity.put("JALPAIGURI", 430);
        hashmapCity.put("JAMMU", 432);
        hashmapCity.put("JAMNAGAR", 433);
        hashmapCity.put("JAMSHEDPUR", 434);
        hashmapCity.put("JAMTARA", 435);
        hashmapCity.put("JAMUI", 436);
        hashmapCity.put("JANJGIR-CHAMPA", 439);
        hashmapCity.put("JASHPUR", 441);
        hashmapCity.put("JAUNPUR", 442);
        hashmapCity.put("JHABUA", 445);
        hashmapCity.put("JHAJJAR", 446);
        hashmapCity.put("JHALAWAR", 447);
        hashmapCity.put("JHANSI", 448);
        hashmapCity.put("JHARSUGUDA", 449);
        hashmapCity.put("JHUNJHUNU", 451);
        hashmapCity.put("JIND", 452);
        hashmapCity.put("JODHPUR", 453);
        hashmapCity.put("JORHAT", 455);
        hashmapCity.put("JUNAGADH", 457);
        hashmapCity.put("KAILASAHAR", 463);
        hashmapCity.put("KAITHAL", 465);
        hashmapCity.put("KAKINADA", 466);
        hashmapCity.put("KANGRA", 480);
        hashmapCity.put("KANKER", 484);
        hashmapCity.put("KANPUR", 487);
        hashmapCity.put("KAPURTHALA", 490);
        hashmapCity.put("KARAULI", 492);
        hashmapCity.put("KARGIL", 494);
        hashmapCity.put("KARIMNAGAR", 496);
        hashmapCity.put("KARNAL", 498);
        hashmapCity.put("KARUR", 502);
        hashmapCity.put("KARWAR", 503);
        hashmapCity.put("KASARAGOD", 504);
        hashmapCity.put("KATHUA", 507);
        hashmapCity.put("KATIHAR", 508);
        hashmapCity.put("KATNI", 509);
        hashmapCity.put("KAUSHAMBI", 510);
        hashmapCity.put("KAWARDHA", 513);
        hashmapCity.put("KENDRAPARA", 517);
        hashmapCity.put("KEONJHAR", 518);
        hashmapCity.put("KHAGARIA", 521);
        hashmapCity.put("KHAMMAM", 523);
        hashmapCity.put("KHARAR", 527);
        hashmapCity.put("KHARGONE", 529);
        hashmapCity.put("KHEDA", 530);
        hashmapCity.put("KHONSA", 531);
        hashmapCity.put("KINNAUR", 535);
        hashmapCity.put("KISHANGANJ", 538);
        hashmapCity.put("KODERMA", 542);
        hashmapCity.put("KOHIMA", 545);
        hashmapCity.put("KOKRAJHAR", 546);
        hashmapCity.put("KOLAR", 547);
        hashmapCity.put("KOLASIB", 548);
        hashmapCity.put("KOLHAPUR", 549);
        hashmapCity.put("KOLKATA", 550);
        hashmapCity.put("KOPPAL", 553);
        hashmapCity.put("KORAPUT", 555);
        hashmapCity.put("KORBA", 556);
        hashmapCity.put("KORIYA", 557);
        hashmapCity.put("KOTA", 560);
        hashmapCity.put("KOTTAYAM", 565);
        hashmapCity.put("KOZHIKODE", 569);
        hashmapCity.put("KRISHNA", 570);
        hashmapCity.put("KUPWARA", 577);
        hashmapCity.put("KURNOOL", 579);
        hashmapCity.put("KURUKSHETRA", 580);
        hashmapCity.put("LAKHIMPUR", 583);
        hashmapCity.put("LAKHISARAI", 585);
        hashmapCity.put("LALITPUR", 587);
        hashmapCity.put("LATEHAR", 589);
        hashmapCity.put("LATUR", 590);
        hashmapCity.put("LAWNGTLAI", 591);
        hashmapCity.put("LEH", 593);
        hashmapCity.put("LOHARDAGA", 595);
        hashmapCity.put("LOHIT", 597);
        hashmapCity.put("LOWER SUBANSIRI", 599);
        hashmapCity.put("LUCKNOW", 600);
        hashmapCity.put("LUDHIANA", 601);
        hashmapCity.put("MADHEPURA", 605);
        hashmapCity.put("MADHUBANI", 606);
        hashmapCity.put("MADIKERI", 607);
        hashmapCity.put("MADURAI", 608);
        hashmapCity.put("MAHARAJGANJ", 610);
        hashmapCity.put("MAHASAMUND", 611);
        hashmapCity.put("MAHENDRAGARH", 614);
        hashmapCity.put("MAHOBA", 616);
        hashmapCity.put("MAINPURI", 617);
        hashmapCity.put("MALEGAON", 622);
        hashmapCity.put("MALKANGIRI", 624);
        hashmapCity.put("MAMIT", 627);
        hashmapCity.put("MANDI", 631);
        hashmapCity.put("MANDLA", 632);
        hashmapCity.put("MANDSAUR", 633);
        hashmapCity.put("MANDYA", 634);
        hashmapCity.put("MANGALORE", 636);
        hashmapCity.put("MANGAN", 637);
        hashmapCity.put("MANSA", 641);
        hashmapCity.put("MATHURA", 647);
        hashmapCity.put("MAU", 649);
        hashmapCity.put("MAYURBHANJ", 652);
        hashmapCity.put("MEDAK", 653);
        hashmapCity.put("MEERUT", 656);
        hashmapCity.put("MEHSANA", 659);
        hashmapCity.put("MIRZAPUR", 662);
        hashmapCity.put("MOGA", 664);
        hashmapCity.put("MOHALI", 665);
        hashmapCity.put("MOKOKCHUNG", 666);
        hashmapCity.put("MON", 668);
        hashmapCity.put("MORADABAD", 670);
        hashmapCity.put("MORENA", 671);
        hashmapCity.put("MUMBAI", 677);
        hashmapCity.put("MUNGER", 678);
        hashmapCity.put("MURSHIDABAD", 679);
        hashmapCity.put("MUZAFFARPUR", 682);
        hashmapCity.put("MYSORE", 683);
        hashmapCity.put("NADIA", 687);
        hashmapCity.put("NAGAON", 690);
        hashmapCity.put("NAGAPATTINAM", 691);
        hashmapCity.put("NAGAUR", 693);
        hashmapCity.put("NAGPUR", 695);
        hashmapCity.put("NAINITAL", 697);
        hashmapCity.put("NALANDA", 701);
        hashmapCity.put("NALBARI", 702);
        hashmapCity.put("NALGONDA", 703);
        hashmapCity.put("NAMAKKAL", 704);
        hashmapCity.put("NAMCHI", 705);
        hashmapCity.put("NANDED", 706);
        hashmapCity.put("NANDURBAR", 708);
        hashmapCity.put("NARMADA", 714);
        hashmapCity.put("NARSINGHPUR", 716);
        hashmapCity.put("NASHIK", 718);
        hashmapCity.put("NAVI MUMBAI", 720);
        hashmapCity.put("NAVSARI", 721);
        hashmapCity.put("NAWADA", 722);
        hashmapCity.put("NAYAGARH", 723);
        hashmapCity.put("NEEMUCH", 725);
        hashmapCity.put("NELLORE", 727);
        hashmapCity.put("NIZAMABAD", 738);
        hashmapCity.put("NOIDA", 739);
        hashmapCity.put("NORTH 24 PARGANAS", 742);
        hashmapCity.put("OSMANABAD", 753);
        hashmapCity.put("PALAMU", 762);
        hashmapCity.put("PALI", 765);
        hashmapCity.put("PANAJI", 767);
        hashmapCity.put("PANCHKULA", 768);
        hashmapCity.put("PANIPAT", 773);
        hashmapCity.put("PANNA", 774);
        hashmapCity.put("PARBHANI", 779);
        hashmapCity.put("PATAN", 784);
        hashmapCity.put("PATHANAMTHITTA", 787);
        hashmapCity.put("PATIALA", 789);
        hashmapCity.put("PATNA", 790);
        hashmapCity.put("PERAMBALUR", 799);
        hashmapCity.put("PHEK", 805);
        hashmapCity.put("PHULBANI", 807);
        hashmapCity.put("PILIBHIT", 809);
        hashmapCity.put("PONDICHERRY", 814);
        hashmapCity.put("PRAKASAM", 819);
        hashmapCity.put("PRATAPGARH", 820);
        hashmapCity.put("PUDUKKOTTAI", 824);
        hashmapCity.put("PULWAMA", 825);
        hashmapCity.put("PUNE", 828);
        hashmapCity.put("PURI", 831);
        hashmapCity.put("PURNIA", 832);
        hashmapCity.put("RAICHUR", 837);
        hashmapCity.put("RAIGAD", 838);
        hashmapCity.put("RAIGARH", 841);
        hashmapCity.put("RAIPUR", 843);
        hashmapCity.put("RAISEN", 844);
        hashmapCity.put("RAJAHMUNDRY", 845);
        hashmapCity.put("RAJAURI", 849);
        hashmapCity.put("RAJGARH", 850);
        hashmapCity.put("RAJKOT", 852);
        hashmapCity.put("RAJNANDGAON", 853);
        hashmapCity.put("RAJSAMAND", 856);
        hashmapCity.put("RAMANATHAPURAM", 859);
        hashmapCity.put("RAMPUR", 863);
        hashmapCity.put("RANCHI", 866);
        hashmapCity.put("RATLAM", 871);
        hashmapCity.put("RATNAGIRI", 872);
        hashmapCity.put("RAYAGADA", 874);
        hashmapCity.put("REWA", 879);
        hashmapCity.put("REWARI", 880);
        hashmapCity.put("ROHTAK", 887);
        hashmapCity.put("ROHTAS", 888);
        hashmapCity.put("RUDRAPRAYAG", 890);
        hashmapCity.put("SABARKANTHA", 893);
        hashmapCity.put("SAGAR", 896);
        hashmapCity.put("SAHARANPUR", 898);
        hashmapCity.put("SAHARSA", 899);
        hashmapCity.put("SALEM", 904);
        hashmapCity.put("SAMASTIPUR", 906);
        hashmapCity.put("SAMBALPUR", 908);
        hashmapCity.put("SANGLI", 911);
        hashmapCity.put("SANGRUR", 912);
        hashmapCity.put("SANT KABIR NAGAR", 914);
        hashmapCity.put("SATARA", 921);
        hashmapCity.put("SATNA", 922);
        hashmapCity.put("SAWAI MADHOPUR", 924);
        hashmapCity.put("SECUNDERABAD", 925);
        hashmapCity.put("SEHORE", 928);
        hashmapCity.put("SENAPATI", 929);
        hashmapCity.put("SEONI", 930);
        hashmapCity.put("SERCHHIP", 933);
        hashmapCity.put("SHAHDOL", 935);
        hashmapCity.put("SHAHJAHANPUR", 937);
        hashmapCity.put("SHAJAPUR", 939);
        hashmapCity.put("SHEOPUR", 942);
        hashmapCity.put("SHIMLA", 945);
        hashmapCity.put("SHIMOGA", 946);
        hashmapCity.put("SHIVPURI", 950);
        hashmapCity.put("SIBSAGAR", 954);
        hashmapCity.put("SIKAR", 958);
        hashmapCity.put("SIMDEGA", 961);
        hashmapCity.put("SINGRAULI", 963);
        hashmapCity.put("SIROHI", 967);
        hashmapCity.put("SIRSA", 968);
        hashmapCity.put("SITAMARHI", 970);
        hashmapCity.put("SITAPUR", 971);
        hashmapCity.put("SIVAGANGA", 972);
        hashmapCity.put("SIWAN", 973);
        hashmapCity.put("SOLAN", 975);
        hashmapCity.put("SOLAPUR", 976);
        hashmapCity.put("SONEPUR", 979);
        hashmapCity.put("SONITPUR", 980);
        hashmapCity.put("SOUTH 24 PARGANAS", 981);
        hashmapCity.put("SOUTH GARO HILLS", 982);
        hashmapCity.put("SRIKAKULAM", 985);
        hashmapCity.put("SRINAGAR", 986);
        hashmapCity.put("SULTANPUR", 990);
        hashmapCity.put("SUNDERGARH", 993);
        hashmapCity.put("SUPAUL", 995);
        hashmapCity.put("SURAT", 996);
        hashmapCity.put("SURENDRANAGAR", 997);
        hashmapCity.put("SURGUJA", 998);
        hashmapCity.put("TAMENGLONG", 1005);
        hashmapCity.put("TAWANG", 1013);
        hashmapCity.put("THANE", 1020);
        hashmapCity.put("THANJAVUR", 1021);
        hashmapCity.put("THENI", 1023);
        hashmapCity.put("THIRUVANANTHAPURAM", 1030);
        hashmapCity.put("THOUBAL", 1036);
        hashmapCity.put("THRISSUR", 1038);
        hashmapCity.put("TIKAMGARH", 1039);
        hashmapCity.put("TINSUKIA", 1040);
        hashmapCity.put("TIRUNELVELI", 1046);
        hashmapCity.put("TIRUVALLUR", 1049);
        hashmapCity.put("TIRUVANNAMALAI", 1050);
        hashmapCity.put("TOHANA", 1054);
        hashmapCity.put("TONK", 1056);
        hashmapCity.put("TRIVANDRUM", 1058);
        hashmapCity.put("TUENSANG", 1059);
        hashmapCity.put("TUMKUR", 1060);
        hashmapCity.put("UDAIPUR", 1066);
        hashmapCity.put("UDAIPUR", 1067);
        hashmapCity.put("UDAIPUR", 1068);
        hashmapCity.put("UDHAMPUR", 1070);
        hashmapCity.put("UDUPI", 1071);
        hashmapCity.put("UJJAIN", 1072);
        hashmapCity.put("UKHRUL", 1073);
        hashmapCity.put("UMARIA", 1076);
        hashmapCity.put("UNA", 1077);
        hashmapCity.put("UTTAR DINAJPUR", 1082);
        hashmapCity.put("UTTARKASHI", 1084);
        hashmapCity.put("VADODARA", 1085);
        hashmapCity.put("VAISHALI", 1087);
        hashmapCity.put("VALSAD", 1089);
        hashmapCity.put("VARANASI", 1091);
        hashmapCity.put("VELLORE", 1097);
        hashmapCity.put("VIDISHA", 1098);
        hashmapCity.put("VIRUDHUNAGAR", 1105);
        hashmapCity.put("VIZIANAGARAM", 1108);
        hashmapCity.put("WARANGAL", 1114);
        hashmapCity.put("WARDHA", 1115);
        hashmapCity.put("WASHIM", 1116);
        hashmapCity.put("WAYANAD", 1117);
        hashmapCity.put("WEST CHAMPARAN", 1118);
        hashmapCity.put("WEST GODAVARI", 1120);
        hashmapCity.put("WOKHA", 1127);
        hashmapCity.put("YADGIR", 1129);
        hashmapCity.put("YAVATMAL", 1132);
        hashmapCity.put("ZUNHEBOTO", 1139);
        hashmapCity.put("ANDAMAN & NICOBAR", 1140);
        hashmapCity.put("ANANTPUR", 1141);
        hashmapCity.put("CHITTOR", 1142);
        hashmapCity.put("KARIM NAGAR", 1143);
        hashmapCity.put("MEHBOOBNAGAR", 1144);
        hashmapCity.put("RAMAGUNDAM", 1145);
        hashmapCity.put("RANGA REDDY", 1146);
        hashmapCity.put("TIRUPATI", 1147);
        hashmapCity.put("VISHAKAPATNAM", 1148);
        hashmapCity.put("ALOG", 1149);
        hashmapCity.put("CHANGALANG", 1150);
        hashmapCity.put("DIBAN VALLEY", 1151);
        hashmapCity.put("EAST KAMENG SEPPA", 1152);
        hashmapCity.put("ROIN", 1153);
        hashmapCity.put("CACHAR", 1154);
        hashmapCity.put("HAILAKANDI", 1155);
        hashmapCity.put("KAMRUP", 1156);
        hashmapCity.put("KARBI-ANGLONG", 1157);
        hashmapCity.put("KARIMGANJ", 1158);
        hashmapCity.put("MORIGAON", 1159);
        hashmapCity.put("N.C.HILLS", 1160);
        hashmapCity.put("ARRAH", 1161);
        hashmapCity.put("BHABUA", 1162);
        hashmapCity.put("BIHAR SHARIF", 1163);
        hashmapCity.put("CHAPRA", 1164);
        hashmapCity.put("JEHANABAD", 1165);
        hashmapCity.put("PURNEA", 1166);
        hashmapCity.put("SEKHPURA", 1167);
        hashmapCity.put("SEOHAR", 1168);
        hashmapCity.put("BHILAI", 1169);
        hashmapCity.put("DAMAN & DIU", 1170);
        hashmapCity.put("NEW DELHI", 1171);
        hashmapCity.put("GOA", 1172);
        hashmapCity.put("AMRELA", 1173);
        hashmapCity.put("DANGS", 1174);
        hashmapCity.put("KUTCH", 1175);
        hashmapCity.put("PANCHMAHALS", 1176);
        hashmapCity.put("PORBANDER", 1177);
        hashmapCity.put("SONIPAT", 1180);
        hashmapCity.put("YAMUNANAGAR", 1181);
        hashmapCity.put("KULLU", 1182);
        hashmapCity.put("LAHAUL-SPITI", 1183);
        hashmapCity.put("SIRMOUR", 1184);
        hashmapCity.put("BADGAN", 1185);
        hashmapCity.put("DADRA & NAGAR", 1186);
        hashmapCity.put("POONCH", 1187);
        hashmapCity.put("HAZARIBAGH", 1188);
        hashmapCity.put("PAKUR", 1189);
        hashmapCity.put("SAHEBGANJ", 1190);
        hashmapCity.put("SARAIKELA", 1191);
        hashmapCity.put("BENGALURU", 1192);
        hashmapCity.put("CHAMRAJNAGAR", 1193);
        hashmapCity.put("CHICKMAGALUR", 1194);
        hashmapCity.put("DAVANAGARE", 1195);
        hashmapCity.put("GADAK", 1196);
        hashmapCity.put("GULBERGA", 1197);
        hashmapCity.put("HAVERI", 1198);
        hashmapCity.put("HOSAPETE", 1199);
        hashmapCity.put("ALAPUZZHA", 1200);
        hashmapCity.put("CALICUT", 1201);
        hashmapCity.put("CANNANORE", 1202);
        hashmapCity.put("KOCHI", 1203);
        hashmapCity.put("KOLLAM", 1204);
        hashmapCity.put("MALLAPURAM", 1205);
        hashmapCity.put("PALGHAT", 1206);
        hashmapCity.put("QUILON", 1207);
        hashmapCity.put("TRICHUR", 1208);
        hashmapCity.put("LAKSHDWEEP", 1209);
        hashmapCity.put("ANOOPPUR", 1210);
        hashmapCity.put("ASHOKNAGAR", 1211);
        hashmapCity.put("BADWANI", 1212);
        hashmapCity.put("CHHATTARPUR", 1213);
        hashmapCity.put("KHANDWA", 1214);
        hashmapCity.put("SINDI", 1215);
        hashmapCity.put("ICHALKARANJI", 1216);
        hashmapCity.put("KOLHPUR", 1217);
        hashmapCity.put("MIRA-BHAYANDAR", 1218);
        hashmapCity.put("PIMRI-CHINCWAD", 1220);
        hashmapCity.put("SHOLAPUR", 1221);
        hashmapCity.put("SINDUDURG", 1222);
        hashmapCity.put("IMPHAL", 1223);
        hashmapCity.put("IMPHAL EAST", 1224);
        hashmapCity.put("IMPHAL WEST", 1225);
        hashmapCity.put("EAST GARO HILL", 1226);
        hashmapCity.put("EAST KHASI HILL", 1227);
        hashmapCity.put("JAINTIA HILL", 1228);
        hashmapCity.put("RI-BHOI DISTRICT", 1229);
        hashmapCity.put("WEST GARO HILL", 1230);
        hashmapCity.put("WEST KHASI HILL", 1231);
        hashmapCity.put("AIZAWAL", 1232);
        hashmapCity.put("CHIMTIPUI DISTRICT", 1233);
        hashmapCity.put("LUGLEI DISTRICT", 1234);
        hashmapCity.put("BARGAH", 1235);
        hashmapCity.put("BOUDH", 1236);
        hashmapCity.put("BRAHMAPUR", 1237);
        hashmapCity.put("CUTTAK", 1238);
        hashmapCity.put("KALHANDI", 1239);
        hashmapCity.put("KHURDA", 1240);
        hashmapCity.put("NAVAPADA", 1241);
        hashmapCity.put("NAVARAGPUR", 1242);
        hashmapCity.put("BHATINDA", 1243);
        hashmapCity.put("FATEHGARH SAHEB", 1244);
        hashmapCity.put("FEROZEPUR", 1245);
        hashmapCity.put("HOSIARPUR", 1246);
        hashmapCity.put("MUKTSAR", 1247);
        hashmapCity.put("NAVANSAHAR", 1248);
        hashmapCity.put("ROPAR", 1249);
        hashmapCity.put("CHITTORGARH", 1250);
        hashmapCity.put("SRI GANGANAGAR", 1251);
        hashmapCity.put("AMBATTUR", 1252);
        hashmapCity.put("AVADI", 1253);
        hashmapCity.put("CUDDALOREI", 1254);
        hashmapCity.put("KANCHEEPURAM", 1255);
        hashmapCity.put("KANNIYAKUMARI", 1256);
        hashmapCity.put("NILGIRIS", 1257);
        hashmapCity.put("THOOTHKUDI", 1258);
        hashmapCity.put("TIRUCHIORAPPALLI", 1259);
        hashmapCity.put("TIRUPUR", 1260);
        hashmapCity.put("TIRUVARUR", 1261);
        hashmapCity.put("TIRUVOTTIYUR", 1262);
        hashmapCity.put("VILLUPURAM", 1263);
        hashmapCity.put("NORTH DISTRICT", 1264);
        hashmapCity.put("SOUTH DISTRICT", 1265);
        hashmapCity.put("WEST DISTRICT", 1266);
        hashmapCity.put("AMBEDKARNAGAR", 1267);
        hashmapCity.put("BALIA", 1268);
        hashmapCity.put("BHADOI", 1269);
        hashmapCity.put("BULANDSHEHAR", 1270);
        hashmapCity.put("GAZIPUR", 1271);
        hashmapCity.put("GORAKPUR", 1272);
        hashmapCity.put("HAPUR", 1273);
        hashmapCity.put("JYOTIBA PHOOLE NAGAR", 1274);
        hashmapCity.put("KANOOJ", 1275);
        hashmapCity.put("KANPUR DEHAT", 1276);
        hashmapCity.put("LAKHIMPUR-KHEDI", 1277);
        hashmapCity.put("LONI", 1278);
        hashmapCity.put("MAHA MAYA NAGAR", 1279);
        hashmapCity.put("MUZAFFAR NAGAR", 1280);
        hashmapCity.put("ORAIYYA", 1281);
        hashmapCity.put("PADRAUNA", 1282);
        hashmapCity.put("RAEBAREILI", 1283);
        hashmapCity.put("SHOOJI MAHARAJ", 1284);
        hashmapCity.put("SHRAVATI", 1285);
        hashmapCity.put("SIDDHARTH NAGAR", 1286);
        hashmapCity.put("SUNBHADRA", 1287);
        hashmapCity.put("UNNAV", 1288);
        hashmapCity.put("GARHWAL", 1289);
        hashmapCity.put("PITORAGARH", 1290);
        hashmapCity.put("TEHRI-GARHWAL", 1291);
        hashmapCity.put("UDHAMSINGH NAGAR", 1292);
        hashmapCity.put("ASANSOL", 1293);
        hashmapCity.put("BALLY", 1294);
        hashmapCity.put("BARANAGAR", 1295);
        hashmapCity.put("BHATPARA", 1296);
        hashmapCity.put("BIDHAN NAGAR", 1297);
        hashmapCity.put("CALCUTTA", 1298);
        hashmapCity.put("COOCHBEHAR", 1299);
        hashmapCity.put("DARJEELING", 1300);
        hashmapCity.put("DURGAPUR", 1301);
        hashmapCity.put("GOPALPUR", 1302);
        hashmapCity.put("KAMARHATI", 1303);
        hashmapCity.put("KULTI", 1305);
        hashmapCity.put("MAHESHTALA", 1306);
        hashmapCity.put("MALDA", 1307);
        hashmapCity.put("MEDINIPUR", 1308);
        hashmapCity.put("NAIHATI", 1309);
        hashmapCity.put("NORTH DUMDUM", 1310);
        hashmapCity.put("PANIHATI", 1311);
        hashmapCity.put("PURULIA", 1312);
        hashmapCity.put("SERAMPORE", 1313);
        hashmapCity.put("SILIGURI", 1314);
        hashmapCity.put("ULUBERIA", 1315);
        hashmapCity.put("ANJAW", 1316);
        hashmapCity.put("PAPUM PARE", 1317);
        hashmapCity.put("TIRAP", 1318);
        hashmapCity.put("UPPER SIANG", 1319);
        hashmapCity.put("UPPER SUBANSIRI", 1320);
        hashmapCity.put("KURUNG KUMEY", 1321);
        hashmapCity.put("WEST SIANG", 1322);
        hashmapCity.put("WEST KAMENG", 1323);
        hashmapCity.put("UPPER SUBANSIRI", 1324);
        hashmapCity.put("UPPER SIANG", 1325);
        hashmapCity.put("NARAYANPUR", 1326);
        hashmapCity.put("EAST SINGHBHUM", 1327);
        hashmapCity.put("RAMGARH", 1328);
        hashmapCity.put("WEST SINGHBHUM", 1329);
        hashmapCity.put("BAGALKOT", 1330);
        hashmapCity.put("KODAGU", 1331);
        hashmapCity.put("RAMANAGAR", 1332);
        hashmapCity.put("UTTARA KANNADA", 1333);
        hashmapCity.put("KANNUR", 1334);
        hashmapCity.put("PALAKKAD", 1335);
        hashmapCity.put("SAIHA", 1336);
        hashmapCity.put("KIPHIRE", 1337);
        hashmapCity.put("LONGLENG", 1338);
        hashmapCity.put("PEREN", 1339);
        hashmapCity.put("BALESWAR", 1340);
        hashmapCity.put("DEBAGARH", 1341);
        hashmapCity.put("KANDHAMAL", 1342);
        hashmapCity.put("BARNALA", 1343);
        hashmapCity.put("ARIYALUR", 1344);
        hashmapCity.put("KRISHNAGIRI", 1345);
        hashmapCity.put("TUTICORIN", 1346);
        hashmapCity.put("AURAIYA", 1347);
        hashmapCity.put("CHITRAKOOT", 1348);
        hashmapCity.put("HATHRAS", 1349);
        hashmapCity.put("KUSHINAGAR", 1350);
        hashmapCity.put("SANT RAVIDAS NAGAR", 1351);
        hashmapCity.put("EAST MIDNAPORE", 1352);
        hashmapCity.put("SOUTH DINAJPUR", 1353);
        hashmapCity.put("WEST MIDNAPORE", 1354);


    }
    //endregion

    //region Makedata

    @Override
    public List<String> getMakeList() {
        List<String> listMake = new ArrayList<>();
        List<MakeMasterEntity> listMakeMaster = dbController.getMasterMake();

        for (int i = 0; i < listMakeMaster.size(); i++) {
            listMake.add(listMakeMaster.get(i).getMake_Name());
        }
        return listMake;
    }

    @Override
    public int getMakeID(String makeName) {

        List<MakeMasterEntity> listMakeMaster = dbController.getMasterMake();
        for (int i = 0; i < listMakeMaster.size(); i++) {
            if (listMakeMaster.get(i).getMake_Name().equals(makeName)) {
                return listMakeMaster.get(i).getMake_ID();
            }
        }
        return 0;
    }

    //endregion

    //region Model

    @Override
    public List<String> getModelList(int makeID) {
        List<String> listModel = new ArrayList<>();
        // List<ModelMasterEntity> listModelMaster = dbController.getMasterModel();
        List<ModelMasterEntity> list = realm.where(ModelMasterEntity.class).equalTo("Make_ID", makeID).findAll();

        for (int i = 0; i < list.size(); i++) {
            listModel.add(list.get(i).getModel_Name());
        }
        return listModel;
    }

    @Override
    public int getModelID(int makeID, String modelName) {
        // List<ModelMasterEntity> listModelMaster = dbController.getMasterModel();

        List<ModelMasterEntity> list = realm.where(ModelMasterEntity.class).equalTo("Make_ID", makeID).findAll();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getModel_Name().equals(modelName)) {
                return list.get(i).getModel_ID();
            }
        }
        return 0;
    }

    //endregion


    //region fuel types
    @Override
    public List<String> getFuelType(int modelID) {
        List<String> listFuelType = new ArrayList<>();
        // List<VariantMasterEntity> listVariantMaster = dbController.getMasterVariant();

        List<VariantMasterEntity> list = realm.where(VariantMasterEntity.class).equalTo("Model_ID", modelID).distinct("Fuel_Name");

        for (int i = 0; i < list.size(); i++) {
            listFuelType.add(list.get(i).getFuel_Name());
        }
        return listFuelType;
    }

    @Override
    public int getFuelID(String fuelType, int modelID) {
        VariantMasterEntity list = realm.where(VariantMasterEntity.class)
                .equalTo("Fuel_Name", "" + fuelType)
                .equalTo("Model_ID", modelID).findFirst();

        return list.getFuel_ID();
    }


    //endregion

    //region variant

    @Override
    public List<String> getVariantList(int modelID) {
        List<String> listVarient = new ArrayList<>();
        // List<VariantMasterEntity> listVariantMaster = dbController.getMasterVariant();

        List<VariantMasterEntity> list = realm.where(VariantMasterEntity.class).equalTo("Model_ID", modelID).findAll();

        for (int i = 0; i < list.size(); i++) {
            listVarient.add(list.get(i).getVariant_Name());
        }
        return listVarient;
    }

    @Override
    public int getVariantID(String variantName) {

        Log.d("SS", variantName);
        VariantMasterEntity entity = realm.where(VariantMasterEntity.class).equalTo("Variant_Name", variantName)
                .findFirst();

        Log.d("SSs", entity.getVariant_Name() + " " + entity.getVariant_ID());
        return entity.getVariant_ID();
    }


    //endregion

    //region city

    @Override
    public List<String> getCity() {
        List<String> listCity = new ArrayList<>();
        List<VehicleMasterEntity> list = dbController.getMasterVehicle();

        for (int i = 0; i < list.size(); i++) {
            listCity.add(list.get(i).getRTO_CodeDiscription());
        }

        return listCity;
    }

    @Override
    public int getCityID(String cityName) {
        List<VehicleMasterEntity> listVehicleMaster = dbController.getMasterVehicle();
        for (int i = 0; i < listVehicleMaster.size(); i++) {
            if (listVehicleMaster.get(i).getRTO_CodeDiscription().equals(cityName)) {
                return listVehicleMaster.get(i).getVehicleCity_Id();
            }
        }
        return 0;
    }

    //endregion

    //region Car Insurance

    public static void MapCarInsuranceImage() {

        hasMapCarInsuranceImage.put(1, R.drawable.carins1);
        hasMapCarInsuranceImage.put(2, R.drawable.carins2);
        hasMapCarInsuranceImage.put(3, R.drawable.carins3);
        hasMapCarInsuranceImage.put(4, R.drawable.carins4);
        hasMapCarInsuranceImage.put(5, R.drawable.carins5);
        hasMapCarInsuranceImage.put(6, R.drawable.carins6);
        hasMapCarInsuranceImage.put(7, R.drawable.carins7);
        hasMapCarInsuranceImage.put(8, R.drawable.carins8);
        hasMapCarInsuranceImage.put(9, R.drawable.carins9);
        hasMapCarInsuranceImage.put(10, R.drawable.carins10);
        hasMapCarInsuranceImage.put(11, R.drawable.carins11);
        hasMapCarInsuranceImage.put(12, R.drawable.carins12);
        hasMapCarInsuranceImage.put(14, R.drawable.carins14);
        hasMapCarInsuranceImage.put(15, R.drawable.carins15);
        hasMapCarInsuranceImage.put(16, R.drawable.carins16);
        hasMapCarInsuranceImage.put(17, R.drawable.carins17);
        hasMapCarInsuranceImage.put(18, R.drawable.carins18);
        hasMapCarInsuranceImage.put(19, R.drawable.carins19);
        hasMapCarInsuranceImage.put(26, R.drawable.carins26);
        hasMapCarInsuranceImage.put(30, R.drawable.carins30);
        hasMapCarInsuranceImage.put(33, R.drawable.carins33);
        hasMapCarInsuranceImage.put(34, R.drawable.carins34);
        hasMapCarInsuranceImage.put(35, R.drawable.carins35);

    }

    public static int getProfessionalID1(int pic) {

        hasMapCarInsuranceImage = new HashMap<Integer, Integer>();
        MapCarInsuranceImage();
        return hasMapCarInsuranceImage.get(pic);
    }

    //endregion
}
