package com.example.BlogApplication.Repository;

import com.example.BlogApplication.entities.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageData,Integer> {


    ImageData findByImageName(String imageName);
}
