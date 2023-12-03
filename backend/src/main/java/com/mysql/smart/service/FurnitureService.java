package com.mysql.smart.service;
import com.mysql.smart.domain.Furniture;


import java.util.List;

public interface FurnitureService  {

    Furniture addFurniture(Furniture furniture);
    Furniture delFurniture(Furniture furniture);
    Furniture queryFurniture(Furniture furniture);


}
