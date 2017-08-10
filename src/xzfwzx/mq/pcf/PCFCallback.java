package xzfwzx.mq.pcf;

import com.ibm.mq.pcf.PCFMessage;

/**
 * @Package ${PACKAGE}
 * @Author liuyp
 * @Date 2017/7/30
 */
public interface PCFCallback {
   void callBack(PCFMessage request);
}
