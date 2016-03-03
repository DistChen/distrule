package dist.common.rules.define;

import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.Match;

/**
 * Created by ChenYanping on 15-2-2.
 */
public class RuleFilter implements AgendaFilter {
    private String targetName;
    private EnumFilterType filterType;

    public RuleFilter(String targetName){
        this.targetName=targetName;
        this.filterType=EnumFilterType.START;
    }
    public RuleFilter(String targetName,EnumFilterType filterType){
        this(targetName);
        this.filterType=filterType;
    }

    @Override
    public boolean accept(Match arg0) {
        String ruleName=arg0.getRule().getName();
        switch (this.filterType) {
            case START:
                return ruleName.startsWith(this.targetName);
            case END:
                return ruleName.endsWith(this.targetName);
            case EQUAL:
                return ruleName.equals(this.targetName);
            case EQUALIGNORECASE:
                return ruleName.equalsIgnoreCase(this.targetName);
            case REGEX:
                return ruleName.matches(this.targetName);
            case CONTAIN:
                return ruleName.contains(this.targetName);
            default:
                break;
        }
        return false;
    }
    public String getTargetName() {
        return targetName;
    }
    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }
    public EnumFilterType getFilterType() {
        return filterType;
    }
    public void setFilterType(EnumFilterType filterType) {
        this.filterType = filterType;
    }
}
