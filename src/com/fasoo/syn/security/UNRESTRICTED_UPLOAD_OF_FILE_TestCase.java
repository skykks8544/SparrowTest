package com.fasoo.syn.security;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class UNRESTRICTED_UPLOAD_OF_FILE_TestCase {

    public String gFileName = "dummy";

    public void upload(HttpServletRequest request) throws ServletException {
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest)request;
        String next = (String)mRequest.getFileNames().next();
        MultipartFile file = mRequest.getFile(next);

        // MultipartFile 로부터 file 을 얻음
        String fileName = file.getOriginalFilename(); // getOriginalFilename() 함수는 위험하다고 가정한다.

        // upload 파일에 대한 확장자 체크를 하지 않음
        File uploadDir = new File("/app/webapp/data/upload/notice");
        String uploadFilePath = uploadDir.getAbsolutePath() + "/" + fileName; /* BUG */

        // 아래의 경우는 업로드된 파일의 확장자를 체크하고 있지 않으므로 취약하다.
        if(".doc".equals(fileName.substring(fileName.lastIndexOf(".")))) {
            uploadFilePath = uploadDir.getAbsolutePath() + "/" + fileName; /* SAFE */
            // 파일 업로드 루틴
        }
        // 이하 file upload 루틴
    }

    public void upload2(HttpServletRequest request) throws ServletException {
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest)request;
        String next = (String)mRequest.getFileNames().next();
        MultipartFile file = mRequest.getFile(next);

        // MultipartFile 로부터 file 을 얻음
        String fileName = file.getOriginalFilename();
        File uploadDir = new File("/app/webapp/data/upload/notice");

        if(!"".equals(fileName)) { /* BUG */
            // ...
        }

        if(!"".equals(file.getOriginalFilename())) { /* SAFE */
            // ...
        }

        // upload 파일에 대한 확장자 체크를 수행
        if(fileName.length() > 0) {
            if(fileName.endsWith(".doc")) {
                String uploadFilePath = uploadDir.getAbsolutePath() + "/" + fileName; /* SAFE */
            }

            while(fileName.endsWith(".doc")) {
                String uploadFilePath = uploadDir.getAbsolutePath() + "/" + fileName; /* SAFE */
                break;
            }

            String uploadFilePath = uploadDir.getAbsolutePath() + "/" + fileName; /* BUG */
            uploadFilePath = fileName.endsWith(".doc") ? uploadDir.getAbsolutePath() + "/" + fileName : ""; /* SAFE */

            String emptyString = "";

            uploadFilePath = emptyString.equals(fileName) ? uploadDir.getAbsolutePath() + "/" + fileName : ""; /* BUG */
        }

        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        if("doc".equals(ext)) {
            String uploadFilePath = uploadDir.getAbsolutePath() + "/" + fileName; /* SAFE */
        }
    }

    public void upload3(HttpServletRequest request) throws ServletException {
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest)request;
        String next = (String)mRequest.getFileNames().next();
        MultipartFile file = mRequest.getFile(next);

        // MultipartFile 로부터 file 을 얻음
        String fileName = file.getOriginalFilename();
        File uploadDir = new File("/app/webapp/data/upload/notice");

        // upload 파일에 대한 확장자 체크를 수행
        if (!(fileName.matches(".*\\.doc"))) {
            // file 업로드 루틴
            throw new ServletException();
        }

        String uploadFilePath = uploadDir.getAbsolutePath() + "/" + fileName; /* SAFE */
    }

    public void upload4(FileItem fileItem, File file) throws Exception {
        String fileName = fileItem.getName();

        fileItem.write(file); /* BUG */

        if(fileName.matches(".*\\.doc")) {
            fileItem.write(file); /* SAFE */
        }
    }

    public void upload5(HttpServletRequest request) throws ServletException {
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest)request;
        String next = (String)mRequest.getFileNames().next();
        MultipartFile file = mRequest.getFile(next);

        // MultipartFile 로부터 file 을 얻음
        gFileName = file.getOriginalFilename();
        File uploadDir = new File("/app/webapp/data/upload/notice");

        // upload 파일에 대한 확장자 체크를 수행
        if (!(gFileName.matches(".*\\.doc"))) {
            String uploadFilePath = uploadDir.getAbsolutePath() + "/" + gFileName; /* SAFE */
        }
    }

    public void upload6(HttpServletRequest request) throws ServletException {
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest)request;
        String next = (String)mRequest.getFileNames().next();
        MultipartFile file = mRequest.getFile(next);

        // MultipartFile 로부터 file 을 얻음
        gFileName = file.getOriginalFilename();
        File uploadDir = new File("/app/webapp/data/upload/notice");

        String uploadFilePath = uploadDir.getAbsolutePath() + "/" + gFileName; /* BUG */
    }

    public void upload7(HttpServletRequest request) throws ServletException {
        //String uploadFilePath = uploadDir.getAbsolutePath() + "/" + gFileName; /* SAFE: gFileName is clean */
    }
}