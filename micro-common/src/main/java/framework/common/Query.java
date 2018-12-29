package framework.common;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author tb
 * @date 2018/12/27 18:11
 */
@Getter
@Setter
public class Query extends LinkedHashMap<String,Object> {
    private static final String PAGE = "page";
    private static final String LIMIT = "limit";
    private int page = 1;
    private int limit = 10;

    public Query(Map<String, Object> params){
        this.putAll(params);
        if(params.get(PAGE)!=null) {
            this.page = Integer.parseInt(params.get(PAGE).toString());
        }
        if(params.get(LIMIT)!=null) {
            this.limit = Integer.parseInt(params.get(PAGE).toString());
        }
        this.remove(PAGE);
        this.remove(LIMIT);
    }
}
