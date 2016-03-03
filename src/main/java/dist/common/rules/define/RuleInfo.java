package dist.common.rules.define;

import java.util.Map;

/**
 * Created by ChenYanping on 15-2-4.
 */
public class RuleInfo {

    private String ruleFilePath;
    private String ruleGroup;
    private String filterKey;
    private EnumFilterType filterType=EnumFilterType.EQUAL;
    private Map<String,Object> globals;

    public String getRuleFilePath() {
        return ruleFilePath;
    }

    public void setRuleFilePath(String ruleFilePath) {
        this.ruleFilePath = ruleFilePath;
    }

    public String getRuleGroup() {
        return ruleGroup;
    }

    public void setRuleGroup(String ruleGroup) {
        this.ruleGroup = ruleGroup;
    }

    public String getFilterKey() {
        return filterKey;
    }

    public void setFilterKey(String filterKey) {
        this.filterKey = filterKey;
    }

    public EnumFilterType getFilterType() {
        return filterType;
    }

    public void setFilterType(EnumFilterType filterType) {
        this.filterType = filterType;
    }

    public Map<String, Object> getGlobals() {
        return globals;
    }

    public void setGlobals(Map<String, Object> globals) {
        this.globals = globals;
    }

}
