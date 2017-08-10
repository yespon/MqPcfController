package xzfwzx.mq.pcf;

/**
 * @Package ${PACKAGE}
 * @Author liuyp
 * @Date 2017/7/30
 */
public class MqQueueResult {
    private long currentDepth;

    private long maxDepth;

    private String localQName;

    private String lastPutDate;

    private String lastPutTime;

    //禁止取出属性 <li>PCFConstants.MQQA_GET_ALLOWED 允许取出 <li>PCFConstants.MQQA_GET_INHIBITED 禁止取出
    private int inhibitGet;

    //禁止放入属性 <li>PCFConstants.MQQA_PUT_ALLOWED 允许放入 <li>PCFConstants.MQQA_PUT_INHIBITED 禁止放入
    private int inhibitPut;

    public long getCurrentDepth() {
        return currentDepth;
    }

    public void setCurrentDepth(long currentDepth) {
        this.currentDepth = currentDepth;
    }

    public long getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(long maxDepth) {
        this.maxDepth = maxDepth;
    }

    public String getLocalQName() {
        return localQName;
    }

    public void setLocalQName(String localQName) {
        this.localQName = localQName;
    }

    public String getLastPutDate() {
        return lastPutDate;
    }

    public void setLastPutDate(String lastPutDate) {
        this.lastPutDate = lastPutDate;
    }

    public String getLastPutTime() {
        return lastPutTime;
    }

    public void setLastPutTime(String lastPutTime) {
        this.lastPutTime = lastPutTime;
    }

    public int getInhibitGet() {
        return inhibitGet;
    }

    public void setInhibitGet(int inhibitGet) {
        this.inhibitGet = inhibitGet;
    }

    public int getInhibitPut() {
        return inhibitPut;
    }

    public void setInhibitPut(int inhibitPut) {
        this.inhibitPut = inhibitPut;
    }

    @Override
    public String toString() {
        return "MqQueueResult{" +
                "currentDepth=" + currentDepth +
                ", maxDepth=" + maxDepth +
                ", localQName='" + localQName + '\'' +
                ", lastPutDate='" + lastPutDate + '\'' +
                ", lastPutTime='" + lastPutTime + '\'' +
                ", inhibitGet=" + inhibitGet +
                ", inhibitPut=" + inhibitPut +
                '}';
    }
}
