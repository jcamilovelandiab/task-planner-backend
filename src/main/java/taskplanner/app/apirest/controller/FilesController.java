package taskplanner.app.apirest.controller;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;

@RestController
@RequestMapping("files")
@CrossOrigin(value = "*")
public class FilesController {

    @Autowired
    GridFsTemplate gridFsTemplate;

    @RequestMapping("/{filename}")
    public ResponseEntity<InputStreamResource> getFileByName(@PathVariable String filename) throws IOException {

        GridFSFile file = gridFsTemplate.findOne(new Query().addCriteria(Criteria.where("filename").is(filename)));
        if(file==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        GridFsResource resource = gridFsTemplate.getResource(file.getFilename());
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(resource.getContentType()))
                .body(new InputStreamResource(resource.getInputStream()));
    }

    @CrossOrigin("*")
    @PostMapping("/{userId}")
    public String handleFileUpload(@PathVariable String userId,@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
        //System.out.println("USER ID -> "+userId);
        String fileName = "profileImage_"+userId;
        gridFsTemplate.store(file.getInputStream(), fileName, file.getContentType());
        return fileName;
    }

}
