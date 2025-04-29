package com.stupm.core.loadbalancer;

import com.stupm.core.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ConsistentHashLoadBalancer implements LoadBalancer {

    private final TreeMap<Integer , ServiceMetaInfo> virtualNodes = new TreeMap<>();

    private static final int VIRTUAL_NODE_NUM = 100;

    @Override
    public ServiceMetaInfo select(Map<String, Object> requiredParams, List<ServiceMetaInfo> serviceMetaInfoList) {
        if(serviceMetaInfoList.isEmpty())
            return null;
        for (ServiceMetaInfo serviceMetaInfo : serviceMetaInfoList) {
            for(int i = 0;i < VIRTUAL_NODE_NUM;i++){
                int hash = getHash(serviceMetaInfo + "#" + i);
                virtualNodes.put(hash, serviceMetaInfo);
            }
        }
        int hash = getHash(requiredParams);
        Map.Entry<Integer , ServiceMetaInfo> entry = virtualNodes.ceilingEntry(hash);
        if (entry == null){
            entry = virtualNodes.firstEntry();
        }
        return entry.getValue();
    }

    public int getHash(Object key){
        return key.hashCode();
    }
}
