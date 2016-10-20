package com.lotus.core.sys.task;

/**
 * @author wyy
 */
public enum EnumJobName {

    JobNameOrder("取消过期订单Job名称后缀","JobNameOrder","groupJobOrder","TriggerNameOrder","groupTriggerOrder");
    private String label;
    private String jobNamePostfix;
    private String jobGroupName;
    private String triggerNamePostfix;
    private String triggerGroupName;
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getJobNamePostfix() {
        return jobNamePostfix;
    }

    public void setJobNamePostfix(String jobNamePostfix) {
        this.jobNamePostfix = jobNamePostfix;
    }

    public String getJobGroupName() {
        return jobGroupName;
    }

    public void setJobGroupName(String jobGroupName) {
        this.jobGroupName = jobGroupName;
    }

    public String getTriggerNamePostfix() {
        return triggerNamePostfix;
    }

    public void setTriggerNamePostfix(String triggerNamePostfix) {
        this.triggerNamePostfix = triggerNamePostfix;
    }

    public String getTriggerGroupName() {
        return triggerGroupName;
    }

    public void setTriggerGroupName(String triggerGroupName) {
        this.triggerGroupName = triggerGroupName;
    }

    EnumJobName(String label, String jobNamePostfix, String jobGroupName, String triggerNamePostfix, String triggerGroupName) {
        this.label = label;
        this.jobNamePostfix = jobNamePostfix;
        this.jobGroupName = jobGroupName;
        this.triggerNamePostfix = triggerNamePostfix;
        this.triggerGroupName = triggerGroupName;
    }
}
