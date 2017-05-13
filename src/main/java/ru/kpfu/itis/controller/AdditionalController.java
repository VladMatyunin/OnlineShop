package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.itis.config.ConstantsConfig;
import ru.kpfu.itis.service.repository.ProductRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by vladislav on 08.05.17.
 */
@Controller
public class AdditionalController {
    @Autowired
    private ProductRepository productRepository;
    @RequestMapping(value = "/load_image/{id}")
    @ResponseBody
    public byte[] loadImage(@PathVariable("id") Long productId){
        File file = new File(ConstantsConfig.filePath + "/" + productRepository.findOne(productId).getImagePath());
        byte[] bytesArray = new byte[(int) file.length()];
        try {
            FileInputStream fis = new FileInputStream(file);
            fis.read(bytesArray); //read file into bytes[]
            fis.close();
        } catch (IOException e) {
            return new byte[0];

        }
        return bytesArray;
    }
    @RequestMapping(value = "/contact")
    public String loadContactPage(){
        return "contact";
    }
}
