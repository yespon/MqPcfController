package xzfwzx.mq.pcf.service;

import com.ibm.mq.constants.MQConstants;
import com.ibm.mq.pcf.PCFMessage;

/**
 * @Package ${PACKAGE}
 * @Author liuyp
 * @Date 2017/7/31
 */
public class MqManagerControlService extends MqBaseControlService {
    /**
     * return the current status of the manger
     *
     * @return
     */
    public String getMangerSts() {
        int result = 0;
        try {
            PCFMessage request = new PCFMessage(MQConstants.MQCMD_INQUIRE_Q_MGR_STATUS);
            PCFMessage[] responses = execute(request);
            result = responses[0].getIntParameterValue(MQConstants.MQIACF_Q_MGR_STATUS);
        } catch (Exception e) {
            return "UNKNOWE";
        }
        return MQConstants.lookup(result, "MQQMSTA_.*");
    }
}
