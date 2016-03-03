package dist.common.rules.define;


import org.apache.log4j.Logger;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.kie.internal.runtime.StatelessKnowledgeSession;

import java.util.Iterator;

/**
 * Created by ChenYanping on 15-2-2.
 */

public class RuleFactory {

    private static Logger log=Logger.getLogger(RuleFactory.class);

    public static KnowledgeBuilder createKnowledgeBuilder(){
        return KnowledgeBuilderFactory.newKnowledgeBuilder();
    }

    public static KnowledgeBase createKnowledgeBase(String ruleFilePath){
        try {
            KnowledgeBuilder kbuilder=createKnowledgeBuilder();
            RuleEngine.loadEnvironment();
            kbuilder.add(RuleFactory.createRuleFile(ruleFilePath), ResourceType.DRL);
            if (kbuilder.hasErrors()) {
                log.error("---------规则文件中存在错误--------");
                KnowledgeBuilderErrors errors = kbuilder.getErrors();
                Iterator<KnowledgeBuilderError> ites = errors.iterator();
                while (ites.hasNext()) {
                    log.error("规则错误:" + ites.next().getMessage());
                }
            }
            KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase(RuleEngine.KCOF);
            kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
            return kbase;
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public static KieBaseConfiguration createKieBaseConfiguration(){
        return KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
    }

    public static Resource createRuleFile(String filePath){
        if (filePath.matches("^[a-zA-Z]:.*drl$")){
            return ResourceFactory.newFileResource(filePath);
        }else {
            return ResourceFactory.newClassPathResource(filePath,CommonUtil.UTF8);
        }
    }

    public static StatefulKnowledgeSession createStatefulKnowledgeSession(KnowledgeBase kbase){
        return kbase.newStatefulKnowledgeSession();
    }

    public static StatelessKnowledgeSession createStatelessKnowledgeSession(KnowledgeBase kbase){
        return kbase.newStatelessKnowledgeSession();
    }
}
