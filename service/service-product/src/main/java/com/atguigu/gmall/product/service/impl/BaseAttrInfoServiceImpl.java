package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.product.mapper.BaseAttrInfoMapper;
import com.atguigu.gmall.product.mapper.BaseAttrValueMapper;
import com.atguigu.gmall.product.service.BaseAttrInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BaseAttrInfoServiceImpl extends ServiceImpl<BaseAttrInfoMapper, BaseAttrInfo>
        implements BaseAttrInfoService {


    @Autowired
    BaseAttrInfoMapper baseAttrInfoMapper;

    @Autowired
    BaseAttrValueMapper baseAttrValueMapper;

    @Override
    public List<BaseAttrInfo> getAttrInfoAndValueByCategoryId(Long c1Id, Long c2Id, Long c3Id) {
        List<BaseAttrInfo> infos = baseAttrInfoMapper.getAttrInfoAndValueByCategoryId(c1Id, c2Id, c3Id);
        return infos;
    }

    @Override
    public void saveAttrInfo(BaseAttrInfo info) {
        if (info.getId() == null) {
            addBaseAttrInfo(info);
        } else {
            updateBaseAttrInfo(info);
        }

    }

    private void updateBaseAttrInfo(BaseAttrInfo info) {
        baseAttrInfoMapper.updateById(info);
        List<BaseAttrValue> valueList = info.getAttrValueList();
        List<Long> vids = new ArrayList<>();
        for (BaseAttrValue attrValue : valueList) {
            Long id = attrValue.getId();
            if (id != null) {
                vids.add(id);
            }
        }
        if(vids.size()>0){
            QueryWrapper<BaseAttrValue> deleteWrapper = new QueryWrapper<>();
            deleteWrapper.eq("attr_id", info.getId());
            deleteWrapper.notIn("id", vids);
            baseAttrValueMapper.delete(deleteWrapper);
        }else {
            QueryWrapper<BaseAttrValue> deleteWrapper = new QueryWrapper<>();
            deleteWrapper.eq("attr_id", info.getId());
            baseAttrValueMapper.delete(deleteWrapper);
        }
        for (BaseAttrValue attrValue : valueList) {
            if (attrValue.getId() != null) {
                baseAttrValueMapper.updateById(attrValue);
            }

            if (attrValue.getId() == null) {
                attrValue.setAttrId(info.getId());
                baseAttrValueMapper.insert(attrValue);
            }
        }
    }

    private void addBaseAttrInfo(BaseAttrInfo info) {
        baseAttrInfoMapper.insert(info);
        Long id = info.getId();

        List<BaseAttrValue> valueList = info.getAttrValueList();
        for (BaseAttrValue value : valueList) {
            value.setAttrId(id);
            baseAttrValueMapper.insert(value);
        }
    }
}




