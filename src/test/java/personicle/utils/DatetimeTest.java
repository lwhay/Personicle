package personicle.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class DatetimeTest {
    /*
    Etc/GMT+12
    Etc/GMT+11
    MIT
    Pacific/Apia
    Pacific/Midway
    Pacific/Niue
    Pacific/Pago_Pago
    Pacific/Samoa
    US/Samoa
    America/Adak
    America/Atka
    Etc/GMT+10
    HST
    Pacific/Fakaofo
    Pacific/Honolulu
    Pacific/Johnston
    Pacific/Rarotonga
    Pacific/Tahiti
    SystemV/HST10
    US/Aleutian
    US/Hawaii
    Pacific/Marquesas
    AST
    America/Anchorage
    America/Juneau
    America/Nome
    America/Yakutat
    Etc/GMT+9
    Pacific/Gambier
    SystemV/YST9
    SystemV/YST9YDT
    US/Alaska
    America/Dawson
    America/Ensenada
    America/Los_Angeles
    America/Tijuana
    America/Vancouver
    America/Whitehorse
    Canada/Pacific
    Canada/Yukon
    Etc/GMT+8
    Mexico/BajaNorte
    PST
    PST8PDT
    Pacific/Pitcairn
    SystemV/PST8
    SystemV/PST8PDT
    US/Pacific
    US/Pacific-New
    America/Boise
    America/Cambridge_Bay
    America/Chihuahua
    America/Dawson_Creek
    America/Denver
    America/Edmonton
    America/Hermosillo
    America/Inuvik
    America/Mazatlan
    America/Phoenix
    America/Shiprock
    America/Yellowknife
    Canada/Mountain
    Etc/GMT+7
    MST
    MST7MDT
    Mexico/BajaSur
    Navajo
    PNT
    SystemV/MST7
    SystemV/MST7MDT
    US/Arizona
    US/Mountain
    America/Belize
    America/Cancun
    America/Chicago
    America/Costa_Rica
    America/El_Salvador
    America/Guatemala
    America/Indiana/Knox
    America/Indiana/Tell_City
    America/Knox_IN
    America/Managua
    America/Menominee
    America/Merida
    America/Mexico_City
    America/Monterrey
    America/North_Dakota/Center
    America/North_Dakota/New_Salem
    America/Rainy_River
    America/Rankin_Inlet
    America/Regina
    America/Swift_Current
    America/Tegucigalpa
    America/Winnipeg
    CST
    CST6CDT
    Canada/Central
    Canada/East-Saskatchewan
    Canada/Saskatchewan
    Chile/EasterIsland
    Etc/GMT+6
    Mexico/General
    Pacific/Easter
    Pacific/Galapagos
    SystemV/CST6
    SystemV/CST6CDT
    US/Central
    US/Indiana-Starke
    America/Atikokan
    America/Bogota
    America/Cayman
    America/Coral_Harbour
    America/Detroit
    America/Eirunepe
    America/Fort_Wayne
    America/Grand_Turk
    America/Guayaquil
    America/Havana
    America/Indiana/Indianapolis
    America/Indiana/Marengo
    America/Indiana/Petersburg
    America/Indiana/Vevay
    America/Indiana/Vincennes
    America/Indiana/Winamac
    America/Indianapolis
    America/Iqaluit
    America/Jamaica
    America/Kentucky/Louisville
    America/Kentucky/Monticello
    America/Lima
    America/Louisville
    America/Montreal
    America/Nassau
    America/New_York
    America/Nipigon
    America/Panama
    America/Pangnirtung
    America/Port-au-Prince
    America/Porto_Acre
    America/Resolute
    America/Rio_Branco
    America/Thunder_Bay
    America/Toronto
    Brazil/Acre
    Canada/Eastern
    Cuba
    EST
    EST5EDT
    Etc/GMT+5
    IET
    Jamaica
    SystemV/EST5
    SystemV/EST5EDT
    US/East-Indiana
    US/Eastern
    US/Michigan
    America/Caracas
    America/Anguilla
    America/Antigua
    America/Aruba
    America/Asuncion
    America/Barbados
    America/Blanc-Sablon
    America/Boa_Vista
    America/Campo_Grande
    America/Cuiaba
    America/Curacao
    America/Dominica
    America/Glace_Bay
    America/Goose_Bay
    America/Grenada
    America/Guadeloupe
    America/Guyana
    America/Halifax
    America/La_Paz
    America/Manaus
    America/Marigot
    America/Martinique
    America/Moncton
    America/Montserrat
    America/Port_of_Spain
    America/Porto_Velho
    America/Puerto_Rico
    America/Santiago
    America/Santo_Domingo
    America/St_Barthelemy
    America/St_Kitts
    America/St_Lucia
    America/St_Thomas
    America/St_Vincent
    America/Thule
    America/Tortola
    America/Virgin
    Antarctica/Palmer
    Atlantic/Bermuda
    Atlantic/Stanley
    Brazil/West
    Canada/Atlantic
    Chile/Continental
    Etc/GMT+4
    PRT
    SystemV/AST4
    SystemV/AST4ADT
    America/St_Johns
    CNT
    Canada/Newfoundland
    AGT
    America/Araguaina
    America/Argentina/Buenos_Aires
    America/Argentina/Catamarca
    America/Argentina/ComodRivadavia
    America/Argentina/Cordoba
    America/Argentina/Jujuy
    America/Argentina/La_Rioja
    America/Argentina/Mendoza
    America/Argentina/Rio_Gallegos
    America/Argentina/San_Juan
    America/Argentina/San_Luis
    America/Argentina/Tucuman
    America/Argentina/Ushuaia
    America/Bahia
    America/Belem
    America/Buenos_Aires
    America/Catamarca
    America/Cayenne
    America/Cordoba
    America/Fortaleza
    America/Godthab
    America/Jujuy
    America/Maceio
    America/Mendoza
    America/Miquelon
    America/Montevideo
    America/Paramaribo
    America/Recife
    America/Rosario
    America/Sao_Paulo
    Antarctica/Rothera
    BET
    Brazil/East
    Etc/GMT+3
    America/Noronha
    Atlantic/South_Georgia
    Brazil/DeNoronha
    Etc/GMT+2
    America/Scoresbysund
    Atlantic/Azores
    Atlantic/Cape_Verde
    Etc/GMT+1
    Africa/Abidjan
    Africa/Accra
    Africa/Bamako
    Africa/Banjul
    Africa/Bissau
    Africa/Casablanca
    Africa/Conakry
    Africa/Dakar
    Africa/El_Aaiun
    Africa/Freetown
    Africa/Lome
    Africa/Monrovia
    Africa/Nouakchott
    Africa/Ouagadougou
    Africa/Sao_Tome
    Africa/Timbuktu
    America/Danmarkshavn
    Atlantic/Canary
    Atlantic/Faeroe
    Atlantic/Faroe
    Atlantic/Madeira
    Atlantic/Reykjavik
    Atlantic/St_Helena
    Eire
    Etc/GMT
    Etc/GMT+0
    Etc/GMT-0
    Etc/GMT0
    Etc/Greenwich
    Etc/UCT
    Etc/UTC
    Etc/Universal
    Etc/Zulu
    Europe/Belfast
    Europe/Dublin
    Europe/Guernsey
    Europe/Isle_of_Man
    Europe/Jersey
    Europe/Lisbon
    Europe/London
    GB
    GB-Eire
    GMT
    GMT0
    Greenwich
    Iceland
    Portugal
    UCT
    UTC
    Universal
    WET
    Zulu
    Africa/Algiers
    Africa/Bangui
    Africa/Brazzaville
    Africa/Ceuta
    Africa/Douala
    Africa/Kinshasa
    Africa/Lagos
    Africa/Libreville
    Africa/Luanda
    Africa/Malabo
    Africa/Ndjamena
    Africa/Niamey
    Africa/Porto-Novo
    Africa/Tunis
    Africa/Windhoek
    Arctic/Longyearbyen
    Atlantic/Jan_Mayen
    CET
    ECT
    Etc/GMT-1
    Europe/Amsterdam
    Europe/Andorra
    Europe/Belgrade
    Europe/Berlin
    Europe/Bratislava
    Europe/Brussels
    Europe/Budapest
    Europe/Copenhagen
    Europe/Gibraltar
    Europe/Ljubljana
    Europe/Luxembourg
    Europe/Madrid
    Europe/Malta
    Europe/Monaco
    Europe/Oslo
    Europe/Paris
    Europe/Podgorica
    Europe/Prague
    Europe/Rome
    Europe/San_Marino
    Europe/Sarajevo
    Europe/Skopje
    Europe/Stockholm
    Europe/Tirane
    Europe/Vaduz
    Europe/Vatican
    Europe/Vienna
    Europe/Warsaw
    Europe/Zagreb
    Europe/Zurich
    MET
    Poland
    ART
    Africa/Blantyre
    Africa/Bujumbura
    Africa/Cairo
    Africa/Gaborone
    Africa/Harare
    Africa/Johannesburg
    Africa/Kigali
    Africa/Lubumbashi
    Africa/Lusaka
    Africa/Maputo
    Africa/Maseru
    Africa/Mbabane
    Africa/Tripoli
    Asia/Amman
    Asia/Beirut
    Asia/Damascus
    Asia/Gaza
    Asia/Istanbul
    Asia/Jerusalem
    Asia/Nicosia
    Asia/Tel_Aviv
    CAT
    EET
    Egypt
    Etc/GMT-2
    Europe/Athens
    Europe/Bucharest
    Europe/Chisinau
    Europe/Helsinki
    Europe/Istanbul
    Europe/Kaliningrad
    Europe/Kiev
    Europe/Mariehamn
    Europe/Minsk
    Europe/Nicosia
    Europe/Riga
    Europe/Simferopol
    Europe/Sofia
    Europe/Tallinn
    Europe/Tiraspol
    Europe/Uzhgorod
    Europe/Vilnius
    Europe/Zaporozhye
    Israel
    Libya
    Turkey
    Africa/Addis_Ababa
    Africa/Asmara
    Africa/Asmera
    Africa/Dar_es_Salaam
    Africa/Djibouti
    Africa/Kampala
    Africa/Khartoum
    Africa/Mogadishu
    Africa/Nairobi
    Antarctica/Syowa
    Asia/Aden
    Asia/Baghdad
    Asia/Bahrain
    Asia/Kuwait
    Asia/Qatar
    Asia/Riyadh
    EAT
    Etc/GMT-3
    Europe/Moscow
    Europe/Volgograd
    Indian/Antananarivo
    Indian/Comoro
    Indian/Mayotte
    W-SU
    Asia/Riyadh87
    Asia/Riyadh88
    Asia/Riyadh89
    Mideast/Riyadh87
    Mideast/Riyadh88
    Mideast/Riyadh89
    Asia/Tehran
    Iran
    Asia/Baku
    Asia/Dubai
    Asia/Muscat
    Asia/Tbilisi
    Asia/Yerevan
    Etc/GMT-4
    Europe/Samara
    Indian/Mahe
    Indian/Mauritius
    Indian/Reunion
    NET
    Asia/Kabul
    Asia/Aqtau
    Asia/Aqtobe
    Asia/Ashgabat
    Asia/Ashkhabad
    Asia/Dushanbe
    Asia/Karachi
    Asia/Oral
    Asia/Samarkand
    Asia/Tashkent
    Asia/Yekaterinburg
    Etc/GMT-5
    Indian/Kerguelen
    Indian/Maldives
    PLT
    Asia/Calcutta
    Asia/Colombo
    Asia/Kolkata
    IST
    Asia/Katmandu
    Antarctica/Mawson
    Antarctica/Vostok
    Asia/Almaty
    Asia/Bishkek
    Asia/Dacca
    Asia/Dhaka
    Asia/Novosibirsk
    Asia/Omsk
    Asia/Qyzylorda
    Asia/Thimbu
    Asia/Thimphu
    BST
    Etc/GMT-6
    Indian/Chagos
    Asia/Rangoon
    Indian/Cocos
    Antarctica/Davis
    Asia/Bangkok
    Asia/Ho_Chi_Minh
    Asia/Hovd
    Asia/Jakarta
    Asia/Krasnoyarsk
    Asia/Phnom_Penh
    Asia/Pontianak
    Asia/Saigon
    Asia/Vientiane
    Etc/GMT-7
    Indian/Christmas
    VST
    Antarctica/Casey
    Asia/Brunei
    Asia/Choibalsan
    Asia/Chongqing
    Asia/Chungking
    Asia/Harbin
    Asia/Hong_Kong
    Asia/Irkutsk
    Asia/Kashgar
    Asia/Kuala_Lumpur
    Asia/Kuching
    Asia/Macao
    Asia/Macau
    Asia/Makassar
    Asia/Manila
    Asia/Shanghai
    Asia/Singapore
    Asia/Taipei
    Asia/Ujung_Pandang
    Asia/Ulaanbaatar
    Asia/Ulan_Bator
    Asia/Urumqi
    Australia/Perth
    Australia/West
    CTT
    Etc/GMT-8
    Hongkong
    PRC
    Singapore
    Australia/Eucla
    Asia/Dili
    Asia/Jayapura
    Asia/Pyongyang
    Asia/Seoul
    Asia/Tokyo
    Asia/Yakutsk
    Etc/GMT-9
    JST
    Japan
    Pacific/Palau
    ROK
    ACT
    Australia/Adelaide
    Australia/Broken_Hill
    Australia/Darwin
    Australia/North
    Australia/South
    Australia/Yancowinna
    AET
    Antarctica/DumontDUrville
    Asia/Sakhalin
    Asia/Vladivostok
    Australia/ACT
    Australia/Brisbane
    Australia/Canberra
    Australia/Currie
    Australia/Hobart
    Australia/Lindeman
    Australia/Melbourne
    Australia/NSW
    Australia/Queensland
    Australia/Sydney
    Australia/Tasmania
    Australia/Victoria
    Etc/GMT-10
    Pacific/Guam
    Pacific/Port_Moresby
    Pacific/Saipan
    Pacific/Truk
    Pacific/Yap
    Australia/LHI
    Australia/Lord_Howe
    Asia/Magadan
    Etc/GMT-11
    Pacific/Efate
    Pacific/Guadalcanal
    Pacific/Kosrae
    Pacific/Noumea
    Pacific/Ponape
    SST
    Pacific/Norfolk
    Antarctica/McMurdo
    Antarctica/South_Pole
    Asia/Anadyr
    Asia/Kamchatka
    Etc/GMT-12
    Kwajalein
    NST
    NZ
    Pacific/Auckland
    Pacific/Fiji
    Pacific/Funafuti
    Pacific/Kwajalein
    Pacific/Majuro
    Pacific/Nauru
    Pacific/Tarawa
    Pacific/Wake
    Pacific/Wallis
    NZ-CHAT
    Pacific/Chatham
    Etc/GMT-13
    Pacific/Enderbury
    Pacific/Tongatapu
    Etc/GMT-14
    Pacific/Kiritimati
    */

    // date类型转换为String类型
    // formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    // data Date类型的时间
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    // long类型转换为String类型
    // currentTime要转换的long类型的时间
    // formatType要转换的string类型的时间格式
    public static String longToString(long currentTime, String formatType) throws ParseException {
        Date date = longToDate(currentTime, formatType); // long类型转成Date类型
        String strTime = dateToString(date, formatType); // date类型转成String
        return strTime;
    }

    // string类型转换为date类型
    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    // long转换为Date类型
    // currentTime要转换的long类型的时间
    // formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    public static Date longToDate(long currentTime, String formatType) throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    // string类型转换为long类型
    // strTime要转换的String类型的时间
    // formatType时间格式
    // strTime的时间格式和formatType的时间格式必须相同
    public static long stringToLong(String strTime, String formatType) throws ParseException {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    // date类型转换为long类型
    // date要转换的date类型的时间
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    public static void main(String[] args) throws ParseException {
        System.out.println("msTOstring: " + DatetimeUtil.convertTimeMilliSecondToString(Instant.now().toEpochMilli()));
        System.out.println("msTOlong: " + DatetimeUtil
                .convertTimeMilliSecondToLong(DatetimeUtil.convertTimeMilliSecondToString(Instant.now().toEpochMilli()))
                + " " + System.currentTimeMillis());
        System.out.println("sTOstamp: " + DatetimeUtil.convertTimeSecondToTimeStamp(Instant.now().getEpochSecond()));
        System.out.println(DatetimeUtil
                .convertTimeSecondToLong(DatetimeUtil.convertTimeSecondToString(Instant.now().getEpochSecond())) + " "
                + System.currentTimeMillis());
        System.out.println(DatetimeUtil.convertTimeMilliSecondToTimeStamp(Instant.now().toEpochMilli()));
        System.out.println(
                "sTOlocal: " + DatetimeUtil.convertTimeMilliSecondToLocalDateTime(Instant.now().toEpochMilli()));
        // { "t": "2020-06-29T04:53:50.629Z", "o": 1593406430629, "d": 28.07, "r": -56 }
        System.out.println("2020-06-29T04:53:50.629Z\t" + 1593406430629L + "\t" + longToString(1593406430629L,
                "yyyy-MM-dd HH:mm:ss"));
        Instant nowUtc = Instant.now();
        ZoneId zone = ZoneId.of("Greenwich");
        ZonedDateTime time = ZonedDateTime.ofInstant(nowUtc, zone);
        System.out.println(time + "\t" + ZonedDateTime.ofInstant(Instant.ofEpochMilli(1593406430629L), zone));
    }
}
