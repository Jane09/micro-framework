package framework.common;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import framework.common.response.TableResponse;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.List;
/**
 * @author tb
 * @date 2018/12/27 16:21
 */
public abstract class AbstractBizService<M extends Mapper<T>,T> implements BizService<T> {


    @Setter
    @Autowired
    protected M mapper;

    @Override
    public T selectOne(T entity) {
        return mapper.selectOne(entity);
    }
    @Override
    public T findById(Object id) {
        return mapper.selectByPrimaryKey(id);
    }
    @Override
    public List<T> findByEntity(T entity) {
        return mapper.select(entity);
    }
    @Override
    public List<T> findAll() {
        return mapper.selectAll();
    }
    @Override
    public Long count(T entity) {
        return (long) mapper.selectCount(entity);
    }

    @Override
    public void insert(T entity) {
        mapper.insert(entity);
    }

    @Override
    public void insertSelective(T entity) {
        mapper.insertSelective(entity);
    }

    @Override
    public void update(T entity) {
        mapper.updateByPrimaryKey(entity);
    }

    @Override
    public void updateSelective(T entity) {
        mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public void delete(T entity) {
        mapper.delete(entity);
    }

    @Override
    public void deleteById(Object id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    @Override
    public int countByExample(Object example) {
        return mapper.selectCountByExample(example);
    }

    @SuppressWarnings("unchecked")
    public TableResponse<T> selectByQuery(Query query) {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        Example example = new Example(clazz);
        if(query.entrySet().size()>0) {
            Example.Criteria criteria = example.createCriteria();
            query.forEach((key,value) -> criteria.andLike(key, "%" + value + "%"));
        }
        Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<T> list = mapper.selectByExample(example);
        return new TableResponse<>(result.getTotal(), list);
    }
}
