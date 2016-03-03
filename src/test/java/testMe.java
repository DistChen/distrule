import dist.common.rules.define.RuleEngine;
import dist.common.rules.define.RuleObject;
import model.Person;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.*;

/**
 * Created by ChenYanping on 15-1-29.
 */
public class testMe {


    @Test
    public void test() {
        try {

            Person m1=new Person("ChenYanping",new Date());
            Person m2=new Person("MaoZedong",new Date());
            Person m3=new Person("PanYanjun",new Date());
            Person m4=new Person("DengXiaoping",new Date());
            List<Person> list=new ArrayList<Person>();
            list.add(m1);
            list.add(m2);
            list.add(m3);
            list.add(m4);
            RuleObject obj =new RuleObject();
            obj.setSource(list);

            Map map=new HashMap();
            map.put("personTest",new Person("testPerson",new Date()));

            for (int i=0;i<1;i++){
                RuleEngine.executeRule("rules/person.drl",obj,map);
            }
            if (obj.getResult()!=null){
                Logger log=Logger.getLogger(testMe.class);
                log.info(obj.getResult().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
