    package com.example.erp.backend.entities;

    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
    import jakarta.persistence.Table;
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @Table(name = "file_uploads")
    @Entity
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public class FileUpload extends DBCommon{
        @Column(name = "file_name")
        private String fileName;

        @Column(name = "file_path")
        private String filePath;

        @Column(name = "file_upload")
        private String fileUpload;

        @Column(name = "file_extension")
        private String fileExtension;
    }
