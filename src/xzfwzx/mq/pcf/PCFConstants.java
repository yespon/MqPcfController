package xzfwzx.mq.pcf;

/**
 * @Package ${PACKAGE}
 * @Author liuyp
 * @Date 2017/7/31
 */
public class PCFConstants extends com.ibm.mq.pcf.PCFConstants {
    public static String lookup(Object value, String filter) {
        return StringUtils.split(com.ibm.mq.pcf.PCFConstants.lookup(value, filter), "_")[1];

    }

    public static String lookup(int value, String filter) {
        return StringUtils.split(com.ibm.mq.pcf.PCFConstants.lookup(value, filter), "_")[1];
    }
}
