package com.Blood.Ware.utils;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static com.google.common.hash.Hashing.sha256;

public class Reference {
    public static final String Name = "BloodWare";
    public static final String Version = "V0.2";

    public static final String Moded = "15";

    public static String visitSiteText(String string) {
        ArrayList<Object> arrayList = new ArrayList<Object>();
        StringBuilder string2 = new StringBuilder();
        try {
            Object object;
            URL url = new URL(string);
            URLConnection uc = url.openConnection();
            uc.addRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            uc.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8"));
            while ((object = bufferedReader.readLine()) != null) {
                arrayList.add(object);
            }
        } catch (Exception exception) {

        }
        for (Object object2 : arrayList) {
            string2.append(object2);
        }
        return string2.toString();
    }


        public static String getHWID() {
            String computerName = System.getenv("COMPUTERNAME");
            String userName = System.getenv("USERNAME");
            String processorIdentifier = System.getenv("PROCESSOR_IDENTIFIER");
            String processorLevel = System.getenv("PROCESSOR_LEVEL");

            String value = computerName + userName + processorIdentifier + processorLevel;

            return sha256(value);
        }
        public static String sha256(String base) {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(base.getBytes(StandardCharsets.UTF_8));
                StringBuilder hexString = new StringBuilder();

                for (byte b : hash) {
                    String hex = Integer.toHexString(0xff & b);
                    if (hex.length() == 1) hexString.append('0');
                    hexString.append(hex);
                }
                return hexString.toString();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);

            }
            }
        }


