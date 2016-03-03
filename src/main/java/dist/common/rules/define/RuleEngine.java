package dist.common.rules.define;


import org.apache.log4j.Logger;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.runtime.conf.TimedRuleExectionOption;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ChenYanping on 15-2-2.
 */
public class RuleEngine {

    private static Logger log=Logger.getLogger(RuleEngine.class);

    public static KieBaseConfiguration KCOF=RuleFactory.createKieBaseConfiguration();

    private static Map<String,String> SYSTEM_ENVIRONMENT=new HashMap();

    static {
        SYSTEM_ENVIRONMENT.put("drools.dateformat", "yyyy-MM-dd");
    }

    public static void addSystemProperty(String key,String value){
        SYSTEM_ENVIRONMENT.put(key,value);
    }


    public static void executeRule(String ruleFilePath,
                                    RuleObject obj,
                                    Map<String,Object> globals,
                                    String groupName,
                                    String filterKey,
                                    EnumFilterType filterType){
        KnowledgeBase kbase=null;
        Object cachePackage=RulePackageCache.getCache(ruleFilePath);
        if (cachePackage==null){
            kbase=RuleFactory.createKnowledgeBase(ruleFilePath);
            if (kbase!=null){
                log.debug("规则文件[" + ruleFilePath + "]对应的编译包在缓存当中未找到，重新编译并添加到缓存中。");
                RulePackageCache.addCache(ruleFilePath,kbase);
            }
        }else {
            log.debug("从缓存当中获取规则文件[" + ruleFilePath + "]对应的编译包。");
            kbase=(KnowledgeBase)cachePackage;
        }
        if (kbase!=null){
            StatefulKnowledgeSession session=RuleFactory.createStatefulKnowledgeSession(kbase);
            if(groupName!=null){
                log.debug("执行规则文件[" + ruleFilePath + "]中[agenda-group]为[" + groupName + "]的规则。");
                session.getAgenda().getAgendaGroup(groupName).setFocus();
            }
            if (globals!=null){
                for(String key:globals.keySet()){
                    session.setGlobal(key,globals.get(key));
                }
            }
            //带参数的 query
            // session.getQueryResults("",new Object[]{new Integer(10),"F"});
            session.insert(obj);
            if (filterKey!=null&&filterType!=null){
                log.debug("执行规则文件[" + ruleFilePath + "]中[filterKey=" + filterKey + "],[filterType=" + filterType + "]的规则。");
                session.fireAllRules(new RuleFilter(filterKey,filterType));
            }else{
                log.debug("执行规则");
                session.fireAllRules();
            }
            session.dispose();
        }
    }


    public static void executeRule(String ruleFilePath,RuleObject obj){
        executeRule(ruleFilePath,obj,null,null,null,null);
    }

    public static void executeRule(String ruleFilePath,RuleObject obj,Map<String,Object> globals){
        executeRule(ruleFilePath,obj,globals,null,null,null);
    }

    public static void executeRule(String ruleFilePath,RuleObject obj,String filterKey,EnumFilterType filterType){
        executeRule(ruleFilePath,obj,null,null,filterKey,filterType);
    }

    public static void executeRule(String ruleFilePath,RuleObject obj,Map<String,Object> globals,String filterKey,EnumFilterType filterType){
        executeRule(ruleFilePath,obj,globals,null,filterKey,filterType);
    }

    public static void executeRule(String ruleFilePath,RuleObject obj,String groupName){
        executeRule(ruleFilePath,obj,null,groupName,null,null);
    }
    public static void executeRule(String ruleFilePath,RuleObject obj,Map<String,Object> globals,String groupName){
        executeRule(ruleFilePath,obj,globals,groupName,null,null);
    }

    public static void loadEnvironment(){
        for(String key:SYSTEM_ENVIRONMENT.keySet()){
            System.setProperty(key,SYSTEM_ENVIRONMENT.get(key));
        }
    }
}
