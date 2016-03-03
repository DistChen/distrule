package dist.common.rules.define;

/**
 * Created by ChenYanping on 15-1-22.
 * 规则对象
 * 用于封装原始数据并保存执行规则后的数据
 */
public class RuleObject {

    private Object source;

    private Object result;

    public RuleObject(){}

    public RuleObject(Object source){
        this.source=source;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public Object getResult() {
        if (this.result==null){
            return source;
        }else{
            return result;
        }
    }

    public void setResult(Object result) {
        this.result = result;
    }

}
