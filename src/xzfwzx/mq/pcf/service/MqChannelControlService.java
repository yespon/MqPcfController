package xzfwzx.mq.pcf.service;

import com.ibm.mq.MQException;
import com.ibm.mq.constants.MQConstants;
import com.ibm.mq.pcf.PCFException;
import com.ibm.mq.pcf.PCFMessage;
import com.ibm.mq.pcf.PCFMessageAgent;
import com.ibm.mq.pcf.PCFParameter;
import xzfwzx.mq.pcf.MqChannelResult;

import java.io.IOException;

/**
 * @Package ${PACKAGE}
 * @Author liuyp
 * @Date 2017/7/31
 */
public class MqChannelControlService extends MqBaseControlService {

    private String[] dogetChannels(String channelName, int chlTyp) {
        PCFMessage request = new PCFMessage(MQConstants.MQCMD_INQUIRE_CHANNEL_NAMES);
        request.addParameter(MQConstants.MQCACH_CHANNEL_NAME, channelName);
        request.addParameter(MQConstants.MQIACH_CHANNEL_TYPE, chlTyp);
        PCFMessage[] responses = execute(request);
        String[] channels = null;
        try {
            channels = responses[0].getStringListParameterValue(MQConstants.MQCACH_CHANNEL_NAMES);
        } catch (PCFException e) {

        }
        return removeSystemChannels(channels);
    }

    /**
     * remove the system channel
     *
     * @return
     */
    private String[] removeSystemChannels(String[] src) {
        for (int i = 0; i < src.length; i++) {
            if (src[i].contains("SYSTEM")) {
                src=(String[]) ArrayUtils.removeElement(src, src[i]);
            }
        }
        return src;
    }

    public String[] getChannels() throws PCFException {
        return dogetChannels("*", MQConstants.MQCHT_ALL);
    }

    public String[] getSendChannels() throws PCFException {
        return dogetChannels("*", MQConstants.MQCHT_SENDER);
    }

    public String[] getReceiverChannels() throws PCFException {
        return dogetChannels("*", MQConstants.MQCHT_RECEIVER);
    }

    public String[] getClusterSendChannels() throws PCFException {
        return dogetChannels("*", MQConstants.MQCHT_CLUSSDR);
    }

    /**
     * get the channel running state channelName
     */
    public int getChlState(String channelName) {
        int chlStatus = 0;
        try {
            PCFMessageAgent agent;
            PCFMessage request;
            PCFMessage[] responses;
            agent = new PCFMessageAgent(getMqQueueManager());
            request = new PCFMessage(MQConstants.MQCMD_INQUIRE_CHANNEL_STATUS);
            request.addParameter(MQConstants.MQCACH_CHANNEL_NAME, channelName);
            responses = agent.send(request);
            PCFParameter pcf = responses[0].getParameter(MQConstants.MQIACH_CHANNEL_STATUS);
            chlStatus = (Integer) pcf.getValue();
            agent.disconnect();
        } catch (MQException e) {
            if (e.reasonCode == 3065) {
                return MQConstants.MQCHS_DISCONNECTED;
            }
        } catch (Exception e) {

        }
        return chlStatus;

    }

    /**
     * @throws IOException start the channel 
     *
     * @param channelName
     * @throws
     */
    public void startChannel(String channelName) throws IOException {
        PCFMessage request = new PCFMessage(MQConstants.MQCMD_START_CHANNEL);
        request.addParameter(MQConstants.MQCACH_CHANNEL_NAME, channelName);
        execute(request);
    }

    /**
     * stop the channel
     *
     * @param channelName
     * @throws IOException
     */
    public void stopChannel(String channelName) throws IOException {
        PCFMessage request = new PCFMessage(MQConstants.MQCMD_STOP_CHANNEL);
        request.addParameter(MQConstants.MQCACH_CHANNEL_NAME, channelName);
        execute(request);
    }

    private PCFMessage[] getChannelStatus(String channelName) {
        PCFMessage request = new PCFMessage(MQConstants.MQCMD_INQUIRE_CHANNEL_STATUS);
        request.addParameter(MQConstants.MQCACH_CHANNEL_NAME, channelName);
        return execute(request);
    }

    /**
     * get the detail informations of the channel
     *
     * @param channelName
     * @return
     */
    public MqChannelResult getChannelResult(String channelName) {
        MqChannelResult mqChannelResult = new MqChannelResult();
        int channelState = getChlState(channelName);
        mqChannelResult.setChannelStatus(MQConstants.lookup(channelState, "MQCHS_.*"));
        if (channelState != MQConstants.MQCHS_DISCONNECTED) {
            PCFMessage[] responses = getChannelStatus(channelName);
            if (null != responses[0].getParameter(MQConstants.MQIACH_BYTES_RCVD)) {
                mqChannelResult.setBytesReceived((String) responses[0].getParameter(MQConstants.MQIACH_BYTES_RCVD).getValue());
            }
            if (null != responses[0].getParameter(MQConstants.MQIACH_BYTES_SENT)) {
                mqChannelResult.setBytesSent( (String)responses[0].getParameter(MQConstants.MQIACH_BYTES_SENT).getValue());
            }
            if (null != responses[0].getParameter(MQConstants.MQIACH_CHANNEL_TYPE)) {
                mqChannelResult.setChannelType(MQConstants.lookup(responses[0].getParameter(MQConstants.MQIACH_CHANNEL_TYPE).getValue(), "MQCHT_.*"));
            }
            if (null != responses[0].getParameter(MQConstants.MQIACH_MSGS)) {
                mqChannelResult.setMsgs((Integer) responses[0].getParameter(MQConstants.MQIACH_MSGS).getValue());
            }
            if (null != responses[0].getParameter(MQConstants.MQIACH_CURRENT_SEQ_NUMBER)) {
                mqChannelResult.setCurrentSeq((String) responses[0].getParameter(MQConstants.MQIACH_CURRENT_SEQ_NUMBER).getValue());
            }
            if (null != responses[0].getParameter(MQConstants.MQCACH_XMIT_Q_NAME)) {
                mqChannelResult.setXmitQueue(((String) responses[0].getParameter(MQConstants.MQCACH_XMIT_Q_NAME).getValue()).trim());
            }
            if (null != responses[0].getParameter(MQConstants.MQCACH_CONNECTION_NAME)) {
                mqChannelResult.setConnectionName((String) responses[0].getParameter(MQConstants.MQCACH_CONNECTION_NAME).getValue());
            }
            if (null != responses[0].getParameter(MQConstants.MQCACH_LAST_MSG_DATE)) {
                mqChannelResult.setLastMsgDate((String) responses[0].getParameter(MQConstants.MQCACH_LAST_MSG_DATE).getValue());
            }
            if (null != responses[0].getParameter(MQConstants.MQCACH_LAST_MSG_TIME)) {
                mqChannelResult.setLastMsgTime((String) responses[0].getParameter(MQConstants.MQCACH_LAST_MSG_TIME).getValue());
            }
        } else {

        }
        return mqChannelResult;

    }
}
