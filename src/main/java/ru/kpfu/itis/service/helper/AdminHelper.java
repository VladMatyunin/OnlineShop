package ru.kpfu.itis.service.helper;

import org.springframework.stereotype.Component;
import ru.kpfu.itis.model.Brand;
import ru.kpfu.itis.model.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vladislav on 12.05.17.
 */
@Component
public class AdminHelper {
    public List<String> brandsToString(List<Brand> brands){
        List<String> items = new ArrayList<>();
        for (Brand brand: brands){
            items.add(brand.getName());
        }
        return items;
    }
    public List<String> categoriesToString(List<Category> brands){
        List<String> items = new ArrayList<>();
        for (Category brand: brands){
            items.add(brand.getName());
        }
        return items;
    }
}
