package shop.khj745700.backend.domain.image.utils;

import marvin.image.MarvinImage;
import org.marvinproject.image.transform.scale.Scale;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import shop.khj745700.backend.domain.image.domain.ImageSize;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Component
public class ImageCompressor {

    private File resizeAttachment(String fileName, String fileFormatName, MultipartFile multipartFile,
                                  int targetWidth, int targetHeight) {

        try {
            // MultipartFile -> BufferedImage Convert
            BufferedImage image = ImageIO.read(multipartFile.getInputStream());

            // 원하는 px로 Width와 Height 수정
            int originWidth = image.getWidth();
            int originHeight = image.getHeight();

            // origin 이미지가 resizing될 사이즈보다 작을 경우 resizing 작업 안 함
            if (originWidth < targetWidth && originHeight < targetHeight)
                return multipartFile;

            MarvinImage imageMarvin = new MarvinImage(image);

            Scale scale = new Scale();
            scale.load();
            scale.setAttribute("newWidth", targetWidth);
            scale.setAttribute("newHeight", targetHeight);
            scale.process(imageMarvin.clone(), imageMarvin, null, null, false);

            BufferedImage imageNoAlpha = imageMarvin.getBufferedImageNoAlpha();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(imageNoAlpha, fileFormatName, baos);
            baos.flush();

            return new MockMultipartFile(fileName, baos.toByteArray());

        } catch (IOException e) {
            // 파일 리사이징 실패시 예외 처리
            throw new FailResizeAttachment();
        }
    }

    private File resizeImage(File inputFile, File outputFile, ImageSize imageSize) throws IOException {
        BufferedImage image = ImageIO.read(inputFile);

        if(!canResizeable(imageSize, image)) {
            return inputFile;
        }

        MarvinImage imageMarvin = new MarvinImage(image);
        Scale scale = new Scale();
        scale.load();


        scale.setAttribute("newWidth", imageSize.getWidth());
        scale.setAttribute("newHeight", imageSize.getHeight());


    }


    private float sizeScaleDown(int newWidth, int )

    private boolean canResizeable(ImageSize imageSize, BufferedImage image) {
        int originWidth = image.getWidth();
        int originHeight = image.getHeight();

        return !(originWidth < imageSize.getWidth()) || !(originHeight < imageSize.getHeight());
    }
}