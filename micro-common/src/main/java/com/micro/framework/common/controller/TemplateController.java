package com.micro.framework.common.controller;

import com.micro.framework.common.service.TemplateBizService;
import com.micro.framework.common.Query;
import com.micro.framework.common.handler.ContextHandler;
import com.micro.framework.common.response.ObjectResponse;
import com.micro.framework.common.response.TableResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author tb
 * @date 2019/1/2 11:30
 */
@Slf4j
public class TemplateController<BizService extends TemplateBizService, Entity> {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected BizService bizService;

    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ObjectResponse<Entity> add(@RequestBody Entity entity){
        bizService.insertSelective(entity);
        return new ObjectResponse<Entity>();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ObjectResponse<Entity> get(@PathVariable int id){
        ObjectResponse<Entity> entityObjectRestResponse = new ObjectResponse<>();
        Object o = bizService.findById(id);
        entityObjectRestResponse.data((Entity)o);
        return entityObjectRestResponse;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public ObjectResponse<Entity> update(@RequestBody Entity entity){
        bizService.updateSelective(entity);
        return new ObjectResponse<Entity>();
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectResponse<Entity> remove(@PathVariable int id){
        bizService.deleteById(id);
        return new ObjectResponse<Entity>();
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public List<Entity> all(){
        return bizService.findAll();
    }
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    public TableResponse<Entity> list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        return bizService.selectByQuery(query);
    }
    public String getCurrentUserName(){
        return ContextHandler.getUsername();
    }
}
