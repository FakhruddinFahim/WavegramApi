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
            new RsaKey("-----BEGIN RSA PUBLIC KEY-----\n"+
                    "MIIBCgKCAQEA6LszBcC1LGzyr992NzE0ieY+BSaOW622Aa9Bd4ZHLl+TuFQ4lo4g\n"+
                    "5nKaMBwK/BIb9xUfg0Q29/2mgIR6Zr9krM7HjuIcCzFvDtr+L0GQjae9H0pRB2OO\n"+
                    "62cECs5HKhT5DZ98K33vmWiLowc621dQuwKWSQKjWf50XYFw42h21P2KXUGyp2y/\n"+
                    "+aEyZ+uVgLLQbRA1dEjSDZ2iGRy12Mk5gpYc397aYp438fsJoHIgJ2lgMv5h7WY9\n"+
                    "t6N/byY9Nw9p21Og3AoXSL2q/2IJ1WRUhebgAdGVMlV1fkuOQoEzR7EdpqtQD9Cs\n"+
                    "5+bfo3Nhmcyvk5ftB0WkJ9z6bNZ7yxrP8wIDAQAB\n"+
                    "-----END RSA PUBLIC KEY-----"),
            //Telegram's test public key
            new RsaKey("-----BEGIN RSA PUBLIC KEY-----\n" +
                    "MIIBCgKCAQEAyMEdY1aR+sCR3ZSJrtztKTKqigvO/vBfqACJLZtS7QMgCGXJ6XIR\n" +
                    "yy7mx66W0/sOFa7/1mAZtEoIokDP3ShoqF4fVNb6XeqgQfaUHd8wJpDWHcR2OFwv\n" +
                    "plUUI1PLTktZ9uW2WE23b+ixNwJjJGwBDJPQEQFBE+vfmH0JP503wr5INS1poWg/\n" +
                    "j25sIWeYPHYeOrFp/eXaqhISP6G+q2IeTaWTXpwZj4LzXq5YOpk4bYEQ6mvRq7D1\n" +
                    "aHWfYmlEGepfaYR8Q0YqvvhYtMte3ITnuSJs171+GDqpdKcSwHnd6FudwGO4pcCO\n" +
                    "j4WcDuXc2CTHgH8gFTNhp/Y8/SpDOhvn9QIDAQAB\n" +
                    "-----END RSA PUBLIC KEY-----"),
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
        List<ApiScheme.DcOption2> dcOptions = new ArrayList<>();

        ApiScheme.DcOption2 dcOption1 = new ApiScheme.DcOption2();
        dcOption1.ipAddress = "149.154.167.40";
        dcOption1.port = 443;
        dcOption1.id = 2;
        dcOptions.add(dcOption1);

        return dcOptions;
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
