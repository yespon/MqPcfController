package xzfwzx.mq.pcf;

/**
 * @Package ${PACKAGE}
 * @Author liuyp
 * @Date 2017/7/31
 */
public class MqChannelResult {
    private String channelName;

    private String channelStatus;

    private String bytesReceived;

    private String bytesSent;

    private String currentSeq;

    private String channelType;

    private int msgs;

    private String xmitQueue;

    private String connectionName;

    private String lastMsgDate;

    private String lastMsgTime;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelStatus() {
        return channelStatus;
    }

    public void setChannelStatus(String channelStatus) {
        this.channelStatus = channelStatus;
    }

    public String getBytesReceived() {
        return bytesReceived;
    }

    public void setBytesReceived(String bytesReceived) {
        this.bytesReceived = bytesReceived;
    }

    public String getBytesSent() {
        return bytesSent;
    }

    public void setBytesSent(String bytesSent) {
        this.bytesSent = bytesSent;
    }

    public String getCurrentSeq() {
        return currentSeq;
    }

    public void setCurrentSeq(String currentSeq) {
        this.currentSeq = currentSeq;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public int getMsgs() {
        return msgs;
    }

    public void setMsgs(int msgs) {
        this.msgs = msgs;
    }

    public String getXmitQueue() {
        return xmitQueue;
    }

    public void setXmitQueue(String xmitQueue) {
        this.xmitQueue = xmitQueue;
    }

    public String getConnectionName() {
        return connectionName;
    }

    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    public String getLastMsgDate() {
        return lastMsgDate;
    }

    public void setLastMsgDate(String lastMsgDate) {
        this.lastMsgDate = lastMsgDate;
    }

    public String getLastMsgTime() {
        return lastMsgTime;
    }

    public void setLastMsgTime(String lastMsgTime) {
        this.lastMsgTime = lastMsgTime;
    }

    @Override
    public String toString() {
        return "MqChannelResult{" +
                "channelName='" + channelName + '\'' +
                ", channelStatus='" + channelStatus + '\'' +
                ", bytesReceived='" + bytesReceived + '\'' +
                ", bytesSent='" + bytesSent + '\'' +
                ", currentSeq='" + currentSeq + '\'' +
                ", channelType='" + channelType + '\'' +
                ", msgs=" + msgs +
                ", xmitQueue='" + xmitQueue + '\'' +
                ", connectionName='" + connectionName + '\'' +
                ", lastMsgDate='" + lastMsgDate + '\'' +
                ", lastMsgTime='" + lastMsgTime + '\'' +
                '}';
    }
}
