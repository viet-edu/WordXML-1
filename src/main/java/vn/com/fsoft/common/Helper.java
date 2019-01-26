package vn.com.fsoft.common;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.text.Normalizer;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
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

    public static String covertStringToURL(String str) throws Exception {
        String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll(" ", "-").replaceAll("đ", "d");
    }

    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    private static String[] getNullPropertyNames (Object source) {

        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] propDesList = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();

        for(PropertyDescriptor propDesc : propDesList) {
            Object srcValue = src.getPropertyValue(propDesc.getName());

            if (srcValue == null || (srcValue instanceof String && StringUtils.isBlank(srcValue.toString()))) {
                emptyNames.add(propDesc.getName());
            }
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
