package xzfwzx.mq.pcf.service;

import com.ibm.mq.constants.MQConstants;
import com.ibm.mq.pcf.PCFMessage;
import xzfwzx.mq.pcf.MqQueueResult;
import xzfwzx.mq.pcf.PCFCallback;
import xzfwzx.mq.pcf.PCFConstants;

/**
 * control & achieve information of Queue
 * <p>
 *     based on {@link MqBaseControlService}
 * </p>
 * @Package ${PACKAGE}
 * @Author liuyp
 * @Date 2017/7/31
 */
public class MqQueueControlService extends MqBaseControlService {

    // # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
    // control
    // # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
    /**
     * inhibit of put message into the queue
     * @param queueName
     */
    public void inhibitPut(String queueName) {
        doGetOrPut(queueName, MQConstants.MQIA_INHIBIT_PUT, MQConstants.MQQA_PUT_INHIBITED);
    }

    /**
     * allow to put message into the queue
     * @param queueName
     */
    public void allowPut(String queueName) {
        doGetOrPut(queueName, MQConstants.MQIA_INHIBIT_PUT, MQConstants.MQQA_PUT_ALLOWED);
    }

    /**
     * inhibit of get message from the queue
     * @param queueName
     */
    public void inhibitGet(String queueName) {
        doGetOrPut(queueName, MQConstants.MQIA_INHIBIT_GET, MQConstants.MQQA_GET_INHIBITED);
    }

    /**
     * allow to get message from the queue
     * @param queueName
     */
    public void allowGet(String queueName) {
        doGetOrPut(queueName, MQConstants.MQIA_INHIBIT_GET, MQConstants.MQQA_GET_ALLOWED);
    }

    /**
     *
     * @param queueName
     * @param parameter
     * @param value
     */
    private void doGetOrPut(final String queueName, final int parameter, final int value) {
        execute(MQConstants.MQCMD_CHANGE_Q, new PCFCallback() {
            @Override
            public void callBack(PCFMessage request) {
                request.addParameter(MQConstants.MQCA_Q_NAME, queueName);
                request.addParameter(MQConstants.MQIA_Q_TYPE, MQConstants.MQQT_LOCAL);
                request.addParameter(parameter, value);
            }
        });
    }

    /**
     * clear the queue
     * @param queueName
     */
    public void clearQueue(final String queueName) {
        execute(MQConstants.MQCMD_CLEAR_Q, new PCFCallback() {
            @Override
            public void callBack(PCFMessage request) {
                request.addParameter(PCFConstants.MQCA_Q_NAME, queueName);
            }
        });
    }

    // # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
    // inquire
    // # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #

    /**
     * get the current depth of the queue
     * @param queueName
     * @return
     * @throws Exception
     */
    public int getCurrentDepth(final String queueName) throws Exception {
        return inquireQueue(queueName)[0].getIntParameterValue(MQConstants.MQIA_CURRENT_Q_DEPTH);
    }

    /**
     * get the max depth of the queue
     * @param queueName
     * @return
     * @throws Exception
     */
    public int getMaxDepth(final String queueName) throws Exception {
        return inquireQueue(queueName)[0].getIntParameterValue(MQConstants.MQIA_MAX_Q_DEPTH);
    }

    /**
     *
     * @param queueName
     * @return
     */
    private PCFMessage[] inquireQueue(final String queueName) {
        return execute(MQConstants.MQCMD_INQUIRE_Q, new PCFCallback() {
            @Override
            public void callBack(PCFMessage request) {
                request.addParameter(MQConstants.MQCA_Q_NAME, queueName);
            }
        });
    }

    /**
     * get the detail message of the queue
     * @param localQueue
     * @return
     * @throws Exception
     */
    public MqQueueResult getQueueResult(final String localQueue) throws Exception {
        PCFMessage[] responsesLocal = execute(MQConstants.MQCMD_INQUIRE_Q_STATUS, new PCFCallback() {
            @Override
            public void callBack(PCFMessage request) {
                request.addParameter(MQConstants.MQCA_Q_NAME, localQueue);
            }
        });

        PCFMessage[] responsesInhibit = execute(PCFConstants.MQCMD_INQUIRE_Q, new PCFCallback() {
            @Override
            public void callBack(PCFMessage request) {
                request.addParameter(PCFConstants.MQCA_Q_NAME, localQueue);
            }
        });

        MqQueueResult result = new MqQueueResult();
        result.setCurrentDepth(getCurrentDepth(localQueue));
        result.setMaxDepth(getMaxDepth(localQueue));
        if (null != responsesLocal[0].getStringParameterValue(PCFConstants.MQCACF_LAST_PUT_DATE)) {
            result.setLastPutDate(responsesLocal[0].getStringParameterValue(PCFConstants.MQCACF_LAST_PUT_DATE).trim());
        }
        if (null != responsesLocal[0].getStringParameterValue(PCFConstants.MQCACF_LAST_PUT_TIME)) {
            result.setLastPutTime(responsesLocal[0].getStringParameterValue(PCFConstants.MQCACF_LAST_PUT_TIME).trim());
        }
        result.setInhibitPut(responsesInhibit[0].getIntParameterValue(PCFConstants.MQIA_INHIBIT_PUT));
        result.setInhibitGet(responsesInhibit[0].getIntParameterValue(PCFConstants.MQIA_INHIBIT_GET));
        return result;
    }
}
