package shop.khj745700.backend.domain.image.utils;

import lombok.extern.slf4j.Slf4j;
import marvin.image.MarvinImage;
import org.marvinproject.image.transform.scale.Scale;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import shop.khj745700.backend.domain.image.domain.ImageSize;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Component
public class ImageCompressor {


    public File resizeImage(MultipartFile multipartFile, File outputFile, String fileFormatName, ImageSize imageSize) throws IOException {
        BufferedImage image = ImageIO.read(multipartFile.getInputStream());

        if(!canResizeable(imageSize, image)) {
            ImageIO.write(image, fileFormatName, outputFile);
            return outputFile;
        }

        MarvinImage imageMarvin = new MarvinImage(image);
        Scale scale = new Scale();
        scale.load();

        setScaleDown(scale, image, imageSize);

        scale.process(imageMarvin.clone(), imageMarvin, null, null, false);
        BufferedImage imageNoAlpha = imageMarvin.getBufferedImageNoAlpha();
        ImageIO.write(imageNoAlpha, fileFormatName, outputFile);

        return outputFile;
    }


    private void setScaleDown(Scale scale, BufferedImage image, ImageSize imageSize) {
        int originWidth = image.getWidth();
        int originHeight = image.getHeight();

        if(originWidth > imageSize.getWidth()) {
            scale.setAttribute("newWidth", imageSize.getWidth());
            scale.setAttribute("newHeight", originHeight * (1 - (originWidth / imageSize.getWidth())));

        }else {
            scale.setAttribute("newWidth", originWidth * (1 - (originHeight / imageSize.getHeight())));
            scale.setAttribute("newHeight", imageSize.getHeight());
        }
        log.info("new Scale : {}", Arrays.toString(scale.getAttributes().toStringArray()));
        log.info("Scale : {}", image.getHeight() + "x" + image.getWidth());
    }

    private boolean canResizeable(ImageSize imageSize, BufferedImage image) {
        int originWidth = image.getWidth();
        int originHeight = image.getHeight();

        return originWidth > imageSize.getWidth() || originHeight > imageSize.getHeight();
    }
}