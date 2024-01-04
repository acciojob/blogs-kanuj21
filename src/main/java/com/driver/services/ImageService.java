package com.driver.services;
import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    @Autowired
    BlogRepository blogRepository2;

    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions) {
        // Add an image to the blog
        Blog blog = blogRepository2.findById(blogId).orElse(null);
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);
        // imageRepository2.save(image); // Error happen for that line
        blog.getImageList().add(image);
        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id) {
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        // Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image = imageRepository2.findById(id).orElse(null);
        String imageDimensions = image.getDimensions();
        int imageIndexOfX = imageDimensions.indexOf('X');
        String imageX = imageDimensions.substring(0, imageIndexOfX);
        String imageY = imageDimensions.substring(imageIndexOfX + 1);
        int imageWidth = Integer.parseInt(imageX);
        int imageHeight = Integer.parseInt(imageY);
        int screenIndexOfX = screenDimensions.indexOf('X');
        String screenX = screenDimensions.substring(0, screenIndexOfX);
        String screenY = screenDimensions.substring(screenIndexOfX + 1);
        int screenWidth = Integer.parseInt(screenX);
        int screenHeight = Integer.parseInt(screenY);
        int count = (screenWidth / imageWidth) * (screenHeight / imageHeight);
        return count;
    }
}