package com.example.erp.backend.services.impls;

import com.example.erp.backend.dtos.PageResponseDto;
import com.example.erp.backend.dtos.TechnologyDTO;
import com.example.erp.backend.dtos.resource_dto.*;
import com.example.erp.backend.dtos.vendor_dtos.GetAllVendorDto;
import com.example.erp.backend.dtos.vendor_dtos.VendorDTO;
import com.example.erp.backend.entities.Resource;
import com.example.erp.backend.entities.ResourceSkill;
import com.example.erp.backend.entities.Technology;
import com.example.erp.backend.entities.Vendor;
import com.example.erp.backend.enums.ResumeType;
import com.example.erp.backend.exceptions.AlreadyPresent;
import com.example.erp.backend.exceptions.BadRequest;
import com.example.erp.backend.exceptions.DataNotFound;
import com.example.erp.backend.exceptions.NoData;
import com.example.erp.backend.mapper.ResourceMapper;
import com.example.erp.backend.repositories.ResourceRep;
import com.example.erp.backend.services.FileUploadService;
import com.example.erp.backend.services.ResourceService;
import com.example.erp.backend.services.TechnologyService;
import com.example.erp.backend.services.VendorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {
    private final ResourceRep resourceRep;
    private final TechnologyService technologyService;
    private final ResourceMapper resourceMapper;
    private final VendorService vendorService;
    private final FileUploadService fileUploadService;
    private final ModelMapper modelMapper;
    @Override
    public String addResource(AddResourceDto addResourceDto, MultipartFile file) {
        String uploadResume=null;
        this.validContractDate(addResourceDto.getJoiningDate(),addResourceDto.getContractEndDate());
        if(addResourceDto.getResumeType().equals(ResumeType.LINK.name())){
            if(addResourceDto.getResume() == null || addResourceDto.getResume().isBlank()) throw  new DataNotFound("Please add the link of resume ");
            uploadResume="link";
        }
        if(addResourceDto.getResumeType().equals(ResumeType.PATH.name())){
            if( file == null || file.isEmpty()) throw  new DataNotFound("Upload the Resume");
            uploadResume="path";
        }
        if(resourceRep.existsByMobileNumber(addResourceDto.getMobileNumber())) throw new AlreadyPresent("Mobile No is exists");
        if(resourceRep.existsByPersonalEmail(addResourceDto.getPersonalEmail())) throw new AlreadyPresent("Personal Email No is exists");
        if(addResourceDto.getCompanyEmail() != null && resourceRep.existsByCompanyEmail(addResourceDto.getCompanyEmail())) throw new AlreadyPresent("Company Email is exists");
        Resource resource=resourceMapper.dtoToEntity(addResourceDto);
        if(addResourceDto.getVendorId() != null){
            Vendor vendor=vendorService.getById(addResourceDto.getVendorId());
            resource.setVendor(vendor);
        }
        if(addResourceDto.getSkills() !=null && !addResourceDto.getSkills().isEmpty()){
            List<Technology> technologies= technologyService.allTechnologyPresent(addResourceDto.getSkills());
            List<ResourceSkill> resourceSkills=technologies.stream().map( t -> ResourceSkill.builder().resource(resource).technology(t).build()).toList();
            resource.setSkills(resourceSkills);
        }
        if(uploadResume.equals("path")){
            String path="/resumes";
            uploadResume= fileUploadService.uploadFileFromApi(path,file);
        }else {
            uploadResume=addResourceDto.getResume();
        }
        resource.setResume(uploadResume);
        resourceRep.save(resource);
        return "Resource created successfully";
    }

    @Override
    @Transactional
    public String deleteResource(Long id) {
        int updateResource = resourceRep.deleteResourceSoft(id);
        if(updateResource == 0){
            throw  new DataNotFound("Resource not found");
        }
        return "Resource deleted successfully.";
    }

    @Override
    @Transactional
    public String updateResource(Long id, UpdateResourceDto updateResourceDto, MultipartFile file) {
        String uploadResume=null;
        if(updateResourceDto.getJoiningDate() != null && updateResourceDto.getContractEndDate() != null){
            if(updateResourceDto.getContractEndDate().isBefore(updateResourceDto.getJoiningDate())){
                throw  new BadRequest("Contract end date should be greater than joining date.");
            }
        }
        if(updateResourceDto.getResumeType() != null && !updateResourceDto.getResumeType().isBlank()){
            if(updateResourceDto.getResumeType().equals(ResumeType.LINK.name())){
                if(updateResourceDto.getResume() == null || updateResourceDto.getResume().isBlank()) throw  new DataNotFound("Please add the link of resume ");
                uploadResume="link";
            }
            if(updateResourceDto.getResumeType().equals(ResumeType.PATH.name())){
                if( file == null || file.isEmpty()) throw  new DataNotFound("Upload the Resume");
                uploadResume="path";
            }
        }
        Resource resource=resourceRep.findByIdAndIsActiveTrue(id).orElseThrow(() -> new DataNotFound("Resource not found"));
        if(updateResourceDto.getJoiningDate() == null && updateResourceDto.getContractEndDate() != null){
//            updateResourceDto.getContractEndDate().isBefore(resource.getJoiningDate()
            if(resource.getJoiningDate() == null){
                throw  new BadRequest("Enter the Joining Date");
            }else{
                if(updateResourceDto.getContractEndDate().isBefore(resource.getJoiningDate())){
                    throw  new BadRequest("Contract end date should be greater than joining date.");
                }
            }
        }
        resourceMapper.updateDtoEntity(updateResourceDto,resource);
        if(updateResourceDto.getVendorId() != null){
            Vendor vendor= vendorService.getById(updateResourceDto.getVendorId());
            resource.setVendor(vendor);
        }
        if(updateResourceDto.getSkills() != null && !updateResourceDto.getSkills().isEmpty()){
            if(resource.getSkills().isEmpty()){
                List<Technology> technologies= technologyService.allTechnologyPresent(updateResourceDto.getSkills());
                List<ResourceSkill> resourceSkills=technologies.stream().map( t -> ResourceSkill.builder().resource(resource).technology(t).build()).toList();
                resource.setSkills(resourceSkills);
            }else{
                List<Long> getTechnologyId=resource.getSkills().stream().map( skill -> skill.getTechnology().getId()).toList();
                List<Long> isAddTechnology=updateResourceDto.getSkills().stream().filter( skill -> !getTechnologyId.contains(skill)).toList();
                List<Long> isActiveTechnology=getTechnologyId.stream().filter(skill -> !updateResourceDto.getSkills().contains(skill)).toList();
                technologyService.deleteTechnologyBulk(isActiveTechnology);
                 List<Technology> technologies=  technologyService.allTechnologyPresent(isAddTechnology);
                 List<ResourceSkill> resourceSkillList= technologies.stream().map(t -> ResourceSkill.builder().technology(t).resource(resource).build()).toList();
                resource.setSkills(resourceSkillList);
            }

        }
        if(uploadResume != null){
            if(uploadResume.equals("path")){
                String path="/resumes";
                uploadResume= fileUploadService.uploadFileFromApi(path,file);
            }else {
                uploadResume=updateResourceDto.getResume();
            }
            resource.setResume(uploadResume);
        }
        resourceRep.save(resource);

        return "Vendor updated successfully";
    }

    @Override
    public ResourceDetailDTO getById(Long id) {
        Optional<Resource> resource=resourceRep.findByIdWithVendorAndSkills(id);
        if(resource.isEmpty()) throw  new DataNotFound("Resource not found");
        return resourceMapper.toResourceDetailDTO(resource.get());
    }

    @Override
    public PageResponseDto<AllResourceDTO> getAllResource(String search, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Resource> resources=resourceRep.findAllWithTechnologiesAndVendor("%"+search+"%",pageable);
        if(resources.isEmpty()) throw  new NoData("No Data");
        List<AllResourceDTO> allResourceDTOPage=resourceMapper.allResourceData(resources.getContent());
        PageResponseDto<AllResourceDTO> response = new PageResponseDto<>();
        response.setTotalPages(resources.getTotalPages());
        response.setCurrentPage(page);
        response.setTotalRecords(resources.getTotalElements());
        response.setResults(allResourceDTOPage);
        return response;
    }



    private void validContractDate(LocalDate joiningDate,LocalDate contractEndDate){
        if(joiningDate == null && contractEndDate != null){
            throw  new BadRequest("Enter the joining date.");
        }
        if(joiningDate != null && contractEndDate != null){
            if(contractEndDate.isBefore(joiningDate)){
                throw  new BadRequest("Contract end date should be greater than joining date.");
            }
        }
    }

}
