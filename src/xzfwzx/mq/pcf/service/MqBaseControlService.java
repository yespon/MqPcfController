package xzfwzx.mq.pcf.service;

import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.pcf.PCFMessage;
import com.ibm.mq.pcf.PCFMessageAgent;
import xzfwzx.mq.pcf.MqInfo;
import xzfwzx.mq.pcf.PCFCallback;

import java.io.IOException;
import java.util.Hashtable;

/**
 * @Package ${PACKAGE}
 * @Author liuyp
 * @Date 2017/7/30
 */
public abstract class MqBaseControlService {
    private MQQueueManager mqQueueManager;

    public MQQueueManager getMqQueueManager() {
        return mqQueueManager;
    }

    public void setMqQueueManager(MQQueueManager mqQueueManager) {
        this.mqQueueManager = mqQueueManager;
    }

    /**
     * connect to QM
     * @param mqInfo
     * @return true if success, else false. you can do anything when you connect to QM
     */
    public boolean connect(MqInfo mqInfo) {
        try {
            Hashtable hashTable = new Hashtable();
            if (mqInfo.getCcsid() != null)
                hashTable.put("CCSID", new Integer(mqInfo.getCcsid()));
            if (mqInfo.getHostname() != null)
                hashTable.put("hostname", mqInfo.getHostname().trim());
            if (mqInfo.getPort() != null)
                hashTable.put("port", new Integer(mqInfo.getPort()));
            if (mqInfo.getChannel() != null)
                hashTable.put("channel", mqInfo.getChannel().trim());
            if (mqInfo.getConnectOptions() != null)
                hashTable.put("connectOptions", mqInfo.getConnectOptions().trim());
            if (mqInfo.getUserId() != null)
                hashTable.put("userId", mqInfo.getUserId().trim());
            if (mqInfo.getPassword() != null)
                hashTable.put("password", mqInfo.getPassword().trim());

            this.mqQueueManager = new MQQueueManager(mqInfo.getQueueManagerName(), hashTable);
        } catch (MQException e) {
//            e.printStackTrace();
            return false;
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
        return true;
    }

    protected PCFMessage[] execute(PCFMessage request) {
        PCFMessageAgent agent;
        PCFMessage[] responses = null;
        try {
            agent = new PCFMessageAgent(getMqQueueManager());
            responses = agent.send(request);
            agent.disconnect();
        } catch (MQException e) {
//            e.printStackTrace();
        } catch (IOException e) {
//            e.printStackTrace();
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return responses;
    }

    /**
     *
     * @param cmd such as MQConstants.MQCMD_INQUIRE_Q_STATUS, MQConstants.MQCMD_INQUIRE_CHANNEL_STATUS etc.
     * @param callback
     * @return
     */
    protected PCFMessage[] execute(int cmd, PCFCallback callback) {
        PCFMessage request = new PCFMessage(cmd);
        callback.callBack(request);
        return execute(request);
    }

    /**
     *
     * @param cmd
     * @param parameter
     * @param operation
     */
    protected void execute(int cmd, final int[] parameter, final int[] operation) {
        execute(cmd, new PCFCallback() {
            @Override
            public void callBack(PCFMessage request) {
                for (int i = 0; i < parameter.length; i++) {
                    request.addParameter(parameter[i], operation[i]);
                }
            }
        });
    }

    /**
     *
     * @param cmd
     * @param parameter
     * @param operation
     */
    protected void execute(int cmd, final int parameter, final int operation) {
        execute(cmd, new int[]{parameter}, new int[]{operation});
    }



}
