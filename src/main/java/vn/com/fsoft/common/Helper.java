package vn.com.fsoft.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;

import org.springframework.web.multipart.MultipartFile;

/**
 * <b>Helper</b>.
 *
 * <p>Version 1.0</p>
 *
 * <p>Date: 27-10-2018</p>
 *
 * <p>Copyright</p>
 *
 * <p>Modification Logs:</p>
 * <p>DATE             AUTHOR      DESCRIPTION</p>
 * ----------------------------------------
 * <p>27-10-2018       ABC123      Create</p>
 */
public class Helper {

    /**
     * Randrom String with length from input.
     * @param len : int
     * @return String
     */
    public static String randomString(int len) {
        String strAlphanumberic = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(strAlphanumberic.charAt(rnd.nextInt(strAlphanumberic.length())));
        }
        return sb.toString();
    }

    /**
     * Check string is null or empty.
     * @param str : String
     * @return true if str is null or empty, false in else case.
     */
    public static boolean isNullOrEmpty(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        }
        return false;
    }

    public static int randomInRange(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    public static File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
