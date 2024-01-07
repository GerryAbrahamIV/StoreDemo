package com.example.storedemo2.dto;

import com.example.storedemo2.projections.HumanProjection;
import com.example.storedemo2.projections.InventoryProjection;
import com.example.storedemo2.projections.StoreProjection;

import java.util.List;

public class HumanDTO {
    public HumanProjection human;
    public List<InventoryProjection> inventory;
}
