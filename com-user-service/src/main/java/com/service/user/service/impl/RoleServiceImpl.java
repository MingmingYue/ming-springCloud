package com.service.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.core.commons.page.PageBean;
import com.core.commons.page.PageParams;
import com.core.commons.utils.StringHelper;
import com.core.commons.utils.WebUtils;
import com.service.common.jpa.JPAFactoryImpl;
import com.service.common.utils.PageUtils;
import com.service.user.bean.RoleDeptBean;
import com.service.user.entity.QRole;
import com.service.user.entity.Role;
import com.service.user.repository.RoleRepository;
import com.service.user.service.RoleService;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xiaoMing
 * Create on 2020-08-13.
 */
@Service(value = "roleService")
public class RoleServiceImpl extends JPAFactoryImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Cacheable(key = "'role_code_' + #roleCode")
    public Role findRoleByCode(String roleCode) {
        if (StringHelper.isBlank(roleCode)) {
            return null;
        }
        QRole qRole = QRole.role;
        return this.queryFactory.selectFrom(qRole).where(qRole.roleCode.eq(roleCode.trim())).fetchOne();
    }

    @Override
    @Cacheable(key = "'role_list'")
    public List<Role> getRoleList() {
        return roleRepository.findAll();
    }

    @Override
    @Cacheable(key = "'page_role_' + #p0.currentPage + '_' + #p0.pageSize + '_' + #p1.roleName + '_' + #p1.roleCode")
    public PageBean<RoleDeptBean> findAll(PageParams pageParams, Role role) {

        // 复杂SQL举例查询
        // 总记录数
        StringBuilder countSql = new StringBuilder();
        countSql.append("select count(t1.role_id) from " + Role.TABLE_NAME + " t1 ");

        // 查询语句
        StringBuilder querySql = new StringBuilder();
        querySql.append("select ")
                .append("t1.role_id, t1.role_name, t1.role_code, t1.role_desc, t1.create_time, t1.update_time, t1.statu,")
                .append("t3.dept_id, t3.pid, t3.dept_name ")
                .append("from " + Role.TABLE_NAME + " t1 ")
                .append("left join t_sys_role_dept t2 on t1.role_id = t2.role_id ")
                .append("left join t_sys_dept t3 on t3.dept_id = t2.dept_id ");

        // where语句
        StringBuilder whereSql = new StringBuilder("where 1=1 ");
        if (StringHelper.isNotBlank(role.getRoleName())) {
            whereSql.append("and t1.role_name like ")
                    .append("'%" + role.getRoleName().trim() + "%' escape '!' ");
        }

        if (StringHelper.isNotBlank(role.getRoleCode())) {
            whereSql.append("and t1.role_code like ")
                    .append("'%" + role.getRoleCode().trim() + "%' escape '!' ");
        }

        // 结果集列表
        List<RoleDeptBean> rdList = new ArrayList<RoleDeptBean>();

        Object countResult = this.em.createNativeQuery(countSql.append(whereSql).toString()).getSingleResult();
        Long resultCount = WebUtils.parseStrToLong(countResult + "", 0l);
        if (null != resultCount && resultCount > 0) {
            // order 语句
            StringBuilder orderSql = new StringBuilder("order by t1.update_time desc ");

            Query query = this.em.createNativeQuery(querySql.append(whereSql).append(orderSql).toString())
                    .setFirstResult((pageParams.getCurrentPage() - 1) * pageParams.getPageSize())
                    .setMaxResults(pageParams.getPageSize());

            // 下面转换为map （效率相对差一点）, 否则为 object[]
            query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> dList = query.getResultList();
            if (null != dList) {
                dList.forEach(d -> {
                    RoleDeptBean roleDeptBean = JSONObject.parseObject(JSONObject.toJSONString(d), RoleDeptBean.class);
                    rdList.add(roleDeptBean);
                });
            }
        }

        return PageUtils.of(rdList, resultCount, pageParams.getCurrentPage(), pageParams.getPageSize());
    }

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    public boolean delById(Integer roleId) {
        if (null == roleId || roleId <= 0) return Boolean.FALSE;

        QRole qRole = QRole.role;
        long num = this.queryFactory.update(qRole)
                .set(qRole.statu, 1) // 0 正常 1删除
                .where(qRole.roleId.eq(roleId))
                .execute();

        return num > 0;
    }
}
