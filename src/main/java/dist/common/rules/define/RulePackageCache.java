package dist.common.rules.define;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ChenYanping on 15-2-4.
 */
public class RulePackageCache {

    private static Logger log=Logger.getLogger(RulePackageCache.class);

    private static Map<String,Object> caches=null;

    private static Map<String,Long> times=null;

    static {
        caches=new HashMap<String, Object>();
        times=new HashMap<String, Long>();
    }

    /**
     *
     * @param key 规则名称加上时间戳
     * @param obj
     */
    public synchronized static void addCache(String key,Object obj){
        caches.put(key,obj);
        times.put(key, getTime(key));
    }

    public static void deleteCache(String key){
        caches.remove(key);
        times.remove(key);
    } 
    public static void clearCache(){
        caches.clear();
        times.clear();
    }

    public static Object getCache(String key){
        try {
            if (times.get(key)!=null && times.get(key) == getTime(key)){
                return caches.get(key);
            }else {
                return null;
            }
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    private static long getTime(String key){
        try {
            String path=key;
            if (!path.matches("^[a-zA-Z]:.*$")){
                path= URLDecoder.decode(Thread.currentThread().getContextClassLoader().getResource(path).getPath());
            }
            File file= new File(path);
            return file.lastModified();
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0;
        }
    }
}
