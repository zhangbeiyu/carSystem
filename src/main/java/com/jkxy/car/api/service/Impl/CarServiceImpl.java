package com.jkxy.car.api.service.Impl;

import com.jkxy.car.api.dao.CarDao;
import com.jkxy.car.api.pojo.Car;
import com.jkxy.car.api.pojo.Purchase;
import com.jkxy.car.api.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("carService")
public class CarServiceImpl implements CarService {
    @Autowired
    private CarDao carDao;

    @Override
    public List<Car> findAll() {
        return carDao.findAll();
    }

    @Override
    public Car findById(int id) {
        return carDao.findById(id);
    }

    @Override
    public List<Car> findByCarName(String carName) {
        return carDao.findByCarName(carName);
    }

    @Override
    public void deleteById(int id) {
        carDao.deleteById(id);
    }

    @Override
    public void updateById(Car car) {
        carDao.updateById(car);
    }

    @Override
    public void insertCar(Car car) {
        carDao.insertCar(car);
    }

    @Override
    public String PurchaseCar(Purchase purchase) {
        String result;
        int num=0;
        if(purchase != null){
            if(purchase.getCarName()!=null && purchase.getCarName()!=""){
                num=carDao.selectNumByName(purchase.getCarName());
            }
        }
        int newNum=num-purchase.getBuyNum();
        if(newNum >=0){
            carDao.updateNum(newNum,purchase.getCarName());
            result="尊贵的上汽通用车主"+'~'+"您已成功购买"+purchase.getBuyNum()+"辆"+purchase.getCarName()+"-"+purchase.getCarType()+"-"+purchase.getCarSeries();
        }else {
            result="库存不足，请预定";
        }
        return result;
    }

    @Override
    public List<Car> findLikeCarName(String carName) {
        return carDao.findLikeCarName(carName);
    }
}
