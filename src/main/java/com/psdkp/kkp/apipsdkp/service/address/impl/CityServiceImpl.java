package com.psdkp.kkp.apipsdkp.service.address.impl;

import com.psdkp.kkp.apipsdkp.domain.address.City;
import com.psdkp.kkp.apipsdkp.repository.address.CityDao;
import com.psdkp.kkp.apipsdkp.service.address.CityService;
import com.psdkp.kkp.apipsdkp.util.ResponMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService{

    @Autowired
    private CityDao cityDao;

    @Autowired
    private ResponMessage responMessage;

    @Override
    public Page<City> findAll(String name, Pageable pageable) {
        return cityDao.findAllByName(name, pageable);
    }

    @Override
    public Object save(City city) {
        if (city.getName().equals("")||city.getCode().equals("")){
            return responMessage.atributNull();
        } else {
            cityDao.save(city);
            return responMessage.success();
        }
    }

    @Override
    public Object edit(City city) {
        if (city.getId()==null||city.getName().equals("")||city.getCode().equals("")){
            return responMessage.atributNull();
        } else {
            cityDao.save(city);
            return responMessage.success();
        }
    }

    @Override
    public Object del(Integer id) {
        if (id==null){
            return responMessage.atributNull();
        } else{
            if (cityDao.findById(id).isPresent()){
                cityDao.deleteById(id);
                return responMessage.success();
            } else{
                return responMessage.notFound();
            }
        }
    }
}