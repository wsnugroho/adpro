package id.ac.ui.id.cs.adprog.eshop.service;

import id.ac.ui.id.cs.adprog.eshop.model.Car;

import java.util.List;

public interface CarService {
    public Car create(Car car);

    public List<Car> findAll();

    public Car findById(String carId);

    public void updateById(String carId, Car car);

    public void deleteById(String carId);
}
