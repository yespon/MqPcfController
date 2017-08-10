package xzfwzx.mq.pcf;

/**
 * @Package ${PACKAGE}
 * @Author liuyp
 * @Date 2017/7/30
 */
public class MqInfo {
    private String ccsid;

    private String hostname;

    private String port;

    private String queueManagerName;

    private String channel;

    private String connectOptions;

    private String userId;

    private String password;

    public MqInfo(String ccsid, String hostname, String port, String queueManagerName, String channel, String userId, String password) {
        this.ccsid = ccsid;
        this.hostname = hostname;
        this.port = port;
        this.queueManagerName = queueManagerName;
        this.channel = channel;
        this.userId = userId;
        this.password = password;
    }

    public MqInfo(String ccsid, String hostname, String port, String queueManagerName, String channel) {
        this.ccsid = ccsid;
        this.hostname = hostname;
        this.port = port;
        this.queueManagerName = queueManagerName;
        this.channel = channel;
    }

    public MqInfo(String ccsid, String hostname, String port, String channel) {
        this.ccsid = ccsid;
        this.hostname = hostname;
        this.port = port;
        this.channel = channel;
    }

    public MqInfo(String ccsid, String queueManagerName) {
        this.ccsid = ccsid;
        this.queueManagerName = queueManagerName;
    }

    public MqInfo() {
    }

    public String getCcsid() {
        return ccsid;
    }

    public void setCcsid(String ccsid) {
        this.ccsid = ccsid;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getQueueManagerName() {
        return queueManagerName;
    }

    public void setQueueManagerName(String queueManagerName) {
        this.queueManagerName = queueManagerName;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getConnectOptions() {
        return connectOptions;
    }

    public void setConnectOptions(String connectOptions) {
        this.connectOptions = connectOptions;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "MqInfo{" +
                "ccsid='" + ccsid + '\'' +
                ", hostname='" + hostname + '\'' +
                ", port=" + port +
                ", queueManagerName='" + queueManagerName + '\'' +
                ", channel='" + channel + '\'' +
                ", connectOptions='" + connectOptions + '\'' +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
