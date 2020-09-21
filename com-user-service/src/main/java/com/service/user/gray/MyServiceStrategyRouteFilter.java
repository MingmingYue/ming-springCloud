package com.service.user.gray;

import com.nepxion.discovery.plugin.strategy.service.filter.DefaultServiceStrategyRouteFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

/**
 * 适用于A/B Testing或者更根据某业务参数决定灰度路由路径。可以结合配置中心分别配置A/B两条路径，可以动态改变并通知
 * 当Header中传来的用户为张三，执行一条路由路径；为李四，执行另一条路由路径
 *
 * @author xiaoMing
 * Create on 2020-08-07.
 */

public class MyServiceStrategyRouteFilter extends DefaultServiceStrategyRouteFilter {
    private static final String DEFAULT_A_ROUTE_VERSION = "{\"discovery-guide-service-b\":\"1.1\"}";
    private static final String DEFAULT_B_ROUTE_VERSION = "{\"discovery-guide-service-b\":\"1.0\"}";

    @Value("${a.route.version:" + DEFAULT_A_ROUTE_VERSION + "}")
    private String aRouteVersion;

    @Value("${b.route.version:" + DEFAULT_B_ROUTE_VERSION + "}")
    private String bRouteVersion;

    // 自定义全链路条件命中
    @Override
    public String getRouteVersion() {
        String user = strategyContextHolder.getHeader("user");

        if (StringUtils.equals(user, "zhangsan")) {
            return aRouteVersion;
        } else if (StringUtils.equals(user, "lisi")) {
            return bRouteVersion;
        }

        return super.getRouteVersion();
    }

    // 自定义全链路随机权重
    /*@Override
    public String getRouteVersion() {
        List<Pair<String, Double>> weightList = new ArrayList<Pair<String, Double>>();
        weightList.add(new ImmutablePair<String, Double>(aRouteVersion, 30D));
        weightList.add(new ImmutablePair<String, Double>(bRouteVersion, 70D));
        MapWeightRandom<String, Double> weightRandom = new MapWeightRandom<String, Double>(weightList);

        return weightRandom.random();
    }*/
}