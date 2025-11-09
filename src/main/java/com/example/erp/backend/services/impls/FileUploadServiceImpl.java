package com.example.erp.backend.services.impls;

import com.example.erp.backend.dtos.FileUploadDto;
import com.example.erp.backend.entities.FileUpload;
import com.example.erp.backend.exceptions.DataNotFound;
import com.example.erp.backend.exceptions.FileException;
import com.example.erp.backend.repositories.FileUploadRes;
import com.example.erp.backend.services.FileUploadService;
import com.example.erp.backend.utilizs.FilePath;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {
    private final FileUploadRes fileUploadRes;
    private final FilePath filePathUtilise;
    private final ModelMapper modelMapper;
    @Override
    public List<FileUploadDto> multipleFile(MultipartFile[] files) {
        List<FileUploadDto> result=new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
                FileUploadDto fileUploadDto=this.singleFile(files[i]);
                result.add(fileUploadDto);
        }
        return result;
    }

    @Override
    public FileUploadDto singleFile(MultipartFile file)  {
        try{
            String folderPath=filePathUtilise.getUploadFile()+"/tempFolder/"+ LocalDate.now();
            File folder=new File(folderPath);
            if(!folder.exists()) folder.mkdirs();
            String fileName= UUID.randomUUID()+"_"+file.getOriginalFilename();
            String fileExtension=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            String uploadFilePath=folderPath+"/"+fileName;
            Path filePath=Path.of(uploadFilePath);
            Files.write(filePath,file.getBytes());
            FileUpload fileUpload=FileUpload.builder().fileExtension(fileExtension).fileName(file.getOriginalFilename()).fileUpload(uploadFilePath).build();
            FileUpload savedFile=fileUploadRes.save(fileUpload);
            return modelMapper.map(savedFile, FileUploadDto.class);
        } catch (Exception e) {
            throw new FileException("File Upload Failed:"+e.getMessage());
        }
    }

    @Override
    public FileUpload getById(Long id) {
        return fileUploadRes.findByIdAndIsActiveTrue(id).orElseThrow(()-> new DataNotFound("File not found"));
    }

    @Override
    public void savedDelete(String path,FileUpload fileUpload) {
        if(path != null && !path.isBlank()) fileUpload.setFilePath(path);
        fileUpload.setIsActive(false);
        fileUploadRes.save(fileUpload);
    }

    @Override
    public void createFolderAndMoveFile(String source, String target,String dir) {
        try{
            Files.createDirectories(Path.of(dir));
            Files.move(Path.of(source), Path.of(target), StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e){
            throw new FileException(e.getMessage());
        }
    }
}
