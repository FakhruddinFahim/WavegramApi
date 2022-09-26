package com.fakhruddin.wavegram;

import com.fakhruddin.mtproto.RsaKey;
import com.fakhruddin.wavegram.tl.ApiScheme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Fakhruddin Fahim on 25/07/2022
 */
public class Config {
    //For client obtain API_ID and API_HASH from https://my.telegram.org
    public static final int API_ID = 0;
    public static final String API_HASH = "";
    public static final String APP_VERSION = "1.0";

    public static final List<RsaKey> RSA_PUBLIC_KEYS = Arrays.asList(
            //Telegram's public key
            new RsaKey("0c150023e2f70db7985ded064759cfecf0af328e69a41daf4d6f01b538135a6f91f8f8b2a0ec9ba9720ce352efcf6c5680ffc424bd634864902de0b4bd6d49f4e580230e3ae97d95c8b19442b3c0a10d8f5633fecedd6926a7f6dab0ddb7d457f9ea81b8465fcd6fffeed114011df91c059caedaf97625f6c96ecc74725556934ef781d866b34f011fce4d835a090196e9a5f0e4449af7eb697ddb9076494ca5f81104a305b6dd27665722c46b60e5df680fb16b210607ef217652e60236c255f6a28315f4083a96791d7214bf64c1df4fd0db1944fb26a2a57031b32eee64ad15a8ba68885cde74a5bfc920f6abf59ba5c75506373e7130f9042da922179251f",
                    "010001", 0xc3b42b026ce86b21L),
            new RsaKey("0c6aeda78b02a251db4b6441031f467fa871faed32526c436524b1fb3b5dca28efb8c089dd1b46d92c895993d87108254951c5f001a0f055f3063dcd14d431a300eb9e29517e359a1c9537e5e87ab1b116faecf5d17546ebc21db234d9d336a693efcb2b6fbcca1e7d1a0be414dca408a11609b9c4269a920b09fed1f9a1597be02761430f09e4bc48fcafbe289054c99dba51b6b5eb7d9c3a2ab4e490545b4676bd620e93804bcac93bf94f73f92c729ca899477ff17625ef14a934d51dc11d5f8650a3364586b3a52fcff2fedec8a8406cac4e751705a472e55707e3c8cd5594342b119c6c3293532d85dbe9271ed54a2fd18b4dc79c04a30951107d5639397",
                    "010001", 0x9a996a1db11c729bL),
            new RsaKey("0b1066749655935f0a5936f517034c943bea7f3365a8931ae52c8bcb14856f004b83d26cf2839be0f22607470d67481771c1ce5ec31de16b20bbaa4ecd2f7d2ecf6b6356f27501c226984263edc046b89fb6d3981546b01d7bd34fedcfcc1058e2d494bda732ff813e50e1c6ae249890b225f82b22b1e55fcb063dc3c0e18e91c28d0c4aa627dec8353eee6038a95a4fd1ca984eb09f94aeb7a2220635a8ceb450ea7e61d915cdb4eecedaa083aa3801daf071855ec1fb38516cb6c2996d2d60c0ecbcfa57e4cf1fb0ed39b2f37e94ab4202ecf595e167b3ca62669a6da520859fb6d6c6203dfdfc79c75ec3ee97da8774b2da903e3435f2cd294670a75a526c1",
                    "010001", 0xb05b2a6f70cdea78L),
            new RsaKey("0c2a8c55b4a62e2b78a19b91cf692bcdc4ba7c23fe4d06f194e2a0c30f6d9996f7d1a2bcc89bc1ac4333d44359a6c433252d1a8402d9970378b5912b75bc8cc3fa76710a025bcb9032df0b87d7607cc53b928712a174ea2a80a8176623588119d42ffce40205c6d72160860d8d80b22a8b8651907cf388effbef29cd7cf2b4eb8a872052da1351cfe7fec214ce48304ea472bd66329d60115b3420d08f6894b0410b6ab9450249967617670c932f7cbdb5d6fbcce1e492c595f483109999b2661fcdeec31b196429b7834c7211a93c6789d9ee601c18c39e521fda9d7264e61e518add6f0712d2d5228204b851e13c4f322e5c5431c3b7f31089668486aadc59f",
                    "010001", 0x71e025b6c76033e3L),
            //own public key
            new RsaKey("""
                    -----BEGIN PUBLIC KEY-----
                    MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmwBmUj4Yvb8PmB4zWGn1
                    8T5r92VUuR8bivoKpa/sleHkFik6zx6buVliPzIqcGjStN9YAVhok0fiZlFj7Wv0
                    QcVR3MdC5Kt7GcEGJMXTRrAgqvus6rYMt3n+MZ+uzH0pAuEm8qB4RSD0x7iyJRQs
                    /gNZ/37iCloN4QMLIKk4h+6zgtuevgRNNbj6KrfXmwJoLQX4tkn+XweibPCpqZcu
                    dADFb2MJKCQoeW8MmDrAach5K5n+B+zD3mbIjWWAUURMt3rEAz6PmXsmhM/o4paB
                    Ne1r8xPthTG9fGiVwyrX0Oud06j5VTdNMvPfnMKX9JGh96oTX5JdtjMXS0wul86y
                    3wIDAQAB
                    -----END PUBLIC KEY-----"""),
            new RsaKey("""
                    -----BEGIN PUBLIC KEY-----
                    MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAng6eUHOUcYOi6nwCa1k0
                    VpDajJmuKYCQJjAg/AkbNRr6EzWVA3Bogeh8Dl9PjNur1Jm8EM8F+ogn368aJq9Q
                    8I8p1zaB1vUWWMoIqFCN1vD78W5wGD9M498ZBnf0U+HOMNPZ4GepsroUrApyuNGZ
                    U/9ngfapR1+Z327xZWfsfOYpCfbMqlFUb+N4G7yfISgALFZrfWY9NKkejBP5KWgO
                    UixRP8wLQw6GWU6mGtIb3FCxkgaM7waL96zAv0697YdGvsfOTGd/sz2CaIGwH/0K
                    pKxzBQ2VC5bZU5Xbv0lxzcDS0hSudQq2S4RqzNfBLwnQjTn/HPszt+W7cL5AZCJY
                    JQIDAQAB
                    -----END PUBLIC KEY-----""")
    );

    public static List<ApiScheme.DcOption2> getTelegramDcs() {
        List<ApiScheme.DcOption2> dcOptionTelegram = new ArrayList<>();
        ApiScheme.DcOption2 dcOption1 = new ApiScheme.DcOption2();
        dcOption1.ipAddress = "149.154.175.53";
        dcOption1.port = 443;
        dcOption1.id = 1;

        ApiScheme.DcOption2 dcOption12 = new ApiScheme.DcOption2();
        dcOption12.ipAddress = "2001:0b28:f23d:f001:0000:0000:0000:000a";
        dcOption12.port = 443;
        dcOption12.id = 1;
        dcOption12.ipv6 = new ApiScheme.True();

        ApiScheme.DcOption2 dcOption2 = new ApiScheme.DcOption2();
        dcOption2.ipAddress = "149.154.167.51";
        dcOption2.port = 443;
        dcOption2.id = 2;

        ApiScheme.DcOption2 dcOption3 = new ApiScheme.DcOption2();
        dcOption3.ipAddress = "149.154.175.100";
        dcOption3.port = 443;
        dcOption3.id = 3;

        ApiScheme.DcOption2 dcOption4 = new ApiScheme.DcOption2();
        dcOption4.ipAddress = "149.154.167.91";
        dcOption4.port = 443;
        dcOption4.id = 4;

        ApiScheme.DcOption2 dcOption5 = new ApiScheme.DcOption2();
        dcOption5.ipAddress = "91.108.56.165";
        dcOption5.port = 443;
        dcOption5.id = 5;

        dcOptionTelegram.add(dcOption1);
        dcOptionTelegram.add(dcOption12);
        dcOptionTelegram.add(dcOption2);
        dcOptionTelegram.add(dcOption3);
        dcOptionTelegram.add(dcOption4);
        dcOptionTelegram.add(dcOption5);
        return dcOptionTelegram;
    }

    public static List<ApiScheme.DcOption2> getTelegramTestDcs() {
        List<ApiScheme.DcOption2> dcOptionLocal = new ArrayList<>();

        ApiScheme.DcOption2 dcOption1 = new ApiScheme.DcOption2();
        dcOption1.ipAddress = "149.154.167.40";
        dcOption1.port = 443;
        dcOption1.id = 2;
        dcOptionLocal.add(dcOption1);

        return dcOptionLocal;
    }

    public static List<ApiScheme.DcOption2> getLocalDcs() {
        List<ApiScheme.DcOption2> dcOptionLocal = new ArrayList<>();
        ApiScheme.DcOption2 dcOption1 = new ApiScheme.DcOption2();
        dcOption1.ipAddress = "127.0.0.1";
        dcOption1.port = SERVER_PORT;
        dcOption1.id = 1;
        dcOptionLocal.add(dcOption1);
        return dcOptionLocal;
    }

    //For server set up a mysql server (e.g. wamp, xampp) and
    //replace MYSQL_USERNAME and MYSQL_PASSWORD with your details
    public static final int SERVER_PORT = 443;
    public static final int SERVER_DC_ID = 1;
    public static final String MYSQL_USERNAME = "root";
    public static final String MYSQL_PASSWORD = "12345678";
    public static final String MYSQL_DBNAME = "wavegram";


    public static final List<RsaKey> RSA_PRIVATE_KEYS = List.of(
            new RsaKey("""
                    -----BEGIN PRIVATE KEY-----
                    MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCbAGZSPhi9vw+Y
                    HjNYafXxPmv3ZVS5HxuK+gqlr+yV4eQWKTrPHpu5WWI/MipwaNK031gBWGiTR+Jm
                    UWPta/RBxVHcx0Lkq3sZwQYkxdNGsCCq+6zqtgy3ef4xn67MfSkC4SbyoHhFIPTH
                    uLIlFCz+A1n/fuIKWg3hAwsgqTiH7rOC256+BE01uPoqt9ebAmgtBfi2Sf5fB6Js
                    8Kmply50AMVvYwkoJCh5bwyYOsBpyHkrmf4H7MPeZsiNZYBRREy3esQDPo+ZeyaE
                    z+jiloE17WvzE+2FMb18aJXDKtfQ653TqPlVN00y89+cwpf0kaH3qhNfkl22MxdL
                    TC6XzrLfAgMBAAECggEACC7PYrnJgkGTGAOcJVJEEXjV/VO5qHjrx3ynfD4bM4HJ
                    50uaQbNKzaWvifONia1XAQUmIb1iQ/Llu7jB7uz7PPpRoIyvy4pWr4eLr1GhnkKP
                    xEcQxrwBVDGvXuegXvD1QyQnc2P6Ws1AbvIhYX4SWVShods3zAvGCMzUKmS/ScRr
                    ZXWmDNLiois71NcIxUd+EQesIMz3cH0Ai1BSftBfwNGGlo1dlAq7IbB6Ba3A/iMJ
                    3tjtH2ilD3OKUyAxBtgbGQlyl6ouLu/1EMzw62bkqd6s/DdQ8l4r+Z6GrDo6YXAa
                    MiITiqecf5ZaZ5QSiJFrGxjN/HywCzAs1xerHvVbqQKBgQDNzI0yYKDeoxZHKMUL
                    EGM3zqQR64OGyggq5pABOGZf62N3tO39SKUo1L5A7tumK8v8Kwx109eRSTzwHnLV
                    PX297V8Wb5v1mzz7AFZSaIgxUBF8++UwSTrD0yk9Y767xd+lfOmXxb8rH6WyCrGK
                    Ov86Q34JTYXpsFBVWC7lKKg62QKBgQDAz7gh6yZH28+mPRDOkL5jUqY72Q+goFKx
                    cxmm8J1Syt2yJ4Zc+aEe6Syt9GQrrOK55/DeV6QNw7i9li/o+Asgw+CJWWuWXwOU
                    l3piq1kzEiSUZR/twmZCLSmrKdKLIT7hBrrRUhG6q1ITVUiszBxGvT4fzXUf/VX5
                    MIbxqG8YdwKBgBDvYTshCnvnrcCjUTus2I1SGtxCpiskWvG00WrYSkxgYWoLYH40
                    SSsH/rP3M+oDtqCEplzX1uhkv3f++XIkwkEr1GRTQOFAKg95oqDba0GrlhTT4bNM
                    g2Jz8PPVTm/DQ7kpg0tzAV06GHeDO8LlQfV4clmvoHJviNzNCebfpqBZAoGBAJ6s
                    Er8vEiC+vyLyTBuCOHvqb0z/M4Ifjg3kR+7/Qqj4s/tB1aw77qFOiOtbHPhoXWla
                    e/muLB77u9Ohfin9vsKnD+hYQ1bLOFoXUqtSkaSawKmyktUKTzcUjZ8DWvYOVP3A
                    mQtbu6VgCbpJS/aQPpDkefSYE1RRGmhozBShZVO/AoGAUODxQSbDQhng9hZ9UldO
                    EP6wjEGUC1gWXP6YvIY+Sc0NfqporrWL3yMreiKyBwy8m/l4FZnFK+zB0UrQATzL
                    EHZ4JkqiKZ098B7ELMOgYhtdWno6n4sMX3gY4QAHpvQDTE8Dsz4reLjrrQZ737KO
                    TD/kmI18ceDAkV1C6qV7aCg=
                    -----END PRIVATE KEY-----"""),
            new RsaKey("""
                    -----BEGIN PRIVATE KEY-----
                    MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCeDp5Qc5Rxg6Lq
                    fAJrWTRWkNqMma4pgJAmMCD8CRs1GvoTNZUDcGiB6HwOX0+M26vUmbwQzwX6iCff
                    rxomr1DwjynXNoHW9RZYygioUI3W8PvxbnAYP0zj3xkGd/RT4c4w09ngZ6myuhSs
                    CnK40ZlT/2eB9qlHX5nfbvFlZ+x85ikJ9syqUVRv43gbvJ8hKAAsVmt9Zj00qR6M
                    E/kpaA5SLFE/zAtDDoZZTqYa0hvcULGSBozvBov3rMC/Tr3th0a+x85MZ3+zPYJo
                    gbAf/QqkrHMFDZULltlTldu/SXHNwNLSFK51CrZLhGrM18EvCdCNOf8c+zO35btw
                    vkBkIlglAgMBAAECggEACXY4EXpdzk7SS6W5UlpQeQcqYiTYCXl+B581dkkIRS1U
                    f55jSYWq5+32MTkQ7s5+spcybDIsejM46dnWX676kjAL0eucKbVZzV5o1sSdvRqH
                    how1Lypo7LJAEgRPzs6zdHZWMmoaPU4I60Sj2f8IJgBh3qSYvSJSuI6wakDmphJc
                    Eu8JKo5cGuEnfONuGg5uo8FoCOBFXanaQA0nv/uzHbELfJC3cPWgZaHj4ohI8wyx
                    x/wL1M2dSQrhOAhnqKc5qjn86rpfew1kNwYigcNKTm1/z18o5DRQJA0juzzAPuuE
                    O7UR0khenFe+ladzJbL8g/04jQ828ButIyg4B38lcQKBgQDR//CRUYlfA/cGgUDN
                    t7i8SiBLSgrMM+OHxAo2Gwq1VjdmY1m0X7ul5INP+6ghho1ozVgza5NP3ojHHbiN
                    4aL/vCjQipjgT1zpHE0vIgP8BU1wA5WzZh857RfY1a6LYKO45rmIYQBtshn53/m+
                    K0ZdKk9rVYaPo9++1QrljzxQ8QKBgQDAren4nuBaOGgSh0nicYo77U0f30rqt/gM
                    /eCReMtCFnzLCydhdrDl40vRB2rfSFPtKLlCPqENXhW3gQSQtqnwCE3n/2sykipp
                    /tehxYbcj11Mt2Bn+fzo9uz0OPgQlY2FPoyGvhMRIzRzJoPhCpLXHAErDDKW+WB1
                    f5pJLub6dQKBgC89q0mVlRiZ1L1S4Fgm51j8N+2X93vehGXrVPy88pi+j3HqEakB
                    DD6SfHPGwhjgovkbkKbooVgLUKuyZO0OAFQVaP0Y4LE9SUJrinV66niMXNJBDDEy
                    XP3z9zRhRYW3NL2Isd4bLDSBJ1TN7Om55Hda6EjWEZJIpDHSzUO9CC2RAoGBALVq
                    iqOmo8MO3R6k9zIYiXlHQ5jA238I3KsO10ThITAGkFV1GQ0xffnV+2rCuyOkrDDe
                    aSAwa990OKpg9fVzp6nzh7bqzPn+maTXaBxPKV2+NrCm8ES2IiUPaafBg//JcEiG
                    zjWH/dfi99175CwdkoirO4ZWo8N25hV7ob9KQ/yJAoGASLcUia+XZEsT8PN/lPxQ
                    3RoIAs6dZyEH9pLe0MEUvv2+58vgdmuCYK27NWKAZ27e4GD+6bHkeqseqhUErQ06
                    H/Qam46oFeJCIgzPBn8gqTlm7zRNTMNSvJXHrkLXGljadNrtLzL/ohw66zuHHa3g
                    C1gBrUAeoJ190IPqptA9GSA=
                    -----END PRIVATE KEY-----""")
    );
}
