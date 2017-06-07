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

    static HashMap<Integer, Integer> hasMapCarInsuranceImage;

    public DatabaseController(Context context, Realm realm) {
        this.realm = realm;
        this.context = context;
        dbController = new RealmDatabaseController(this.realm);

    }

    //region mapping Insurence and Profession

    public void MapInsurence() {

        hashMapInsurence.put("Bajaj Allianz General Insurance Co. Ltd.", 1);
        hashMapInsurence.put("Bharti Axa General Insurance Co.Ltd.", 2);
        hashMapInsurence.put("Cholamandalam MS General Insurance Co.Ltd.", 3);
        hashMapInsurence.put("Future Generali India Insurance Co.Ltd.", 4);
        hashMapInsurence.put("HDFC ERGO General Insurance Co.Ltd.", 5);
        hashMapInsurence.put("ICICI Lombard General Insurance Co.Ltd.", 6);
        hashMapInsurence.put("IFFCO Tokio General Insurance Co.Ltd.", 7);
        hashMapInsurence.put("L & T General Insurance Co.Ltd.", 15);
        hashMapInsurence.put("Liberty Videocon General Insurance Co.Ltd.", 33);
        hashMapInsurence.put("Magma HDI General Insurance Co.Ltd", 35);
        hashMapInsurence.put("National Insurance Co.Ltd.", 8);
        hashMapInsurence.put("Raheja QBE General Insurance Co.Ltd.", 16);
        hashMapInsurence.put("Reliance General Insurance Co.Ltd.", 9);
        hashMapInsurence.put("Royal Sundaram Alliance Insurance Co.Ltd", 10);
        hashMapInsurence.put("SBI General Insurance Co.Ltd.", 17);
        hashMapInsurence.put("Shriram General Insurance Co.Ltd.", 18);
        hashMapInsurence.put("Tata AIG General Insurance Co.Ltd.", 11);
        hashMapInsurence.put("The New India Assurance Co.Ltd.", 12);
        hashMapInsurence.put("The Oriental Insurance Co.Ltd.", 13);
        hashMapInsurence.put("United India Insurance Co.Ltd.", 14);
        hashMapInsurence.put("Universal Sompo General Insurance Co.Ltd.", 19);

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
        hasMapCarInsuranceImage.put(33, R.drawable.carins33);
        hasMapCarInsuranceImage.put(35, R.drawable.carins35);

    }

    public static int getProfessionalID1(int pic) {

        hasMapCarInsuranceImage = new HashMap<Integer, Integer>();
        MapCarInsuranceImage();
        return hasMapCarInsuranceImage.get(pic);
    }

    //endregion
}
