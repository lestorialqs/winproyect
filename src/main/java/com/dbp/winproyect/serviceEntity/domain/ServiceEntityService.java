package com.dbp.winproyect.serviceEntity.domain;

import com.dbp.winproyect.enterprise.domain.Enterprise;
import com.dbp.winproyect.enterprise.infrastructure.EnterpriseRepository;
import com.dbp.winproyect.freelancer.domain.Freelancer;
import com.dbp.winproyect.freelancer.infrastructure.FreelancerRepository;
import com.dbp.winproyect.provider.domain.ProviderService;
import com.dbp.winproyect.repostitorytests.ProviderRepository;
import com.dbp.winproyect.review.domain.ReviewService;
import com.dbp.winproyect.serviceEntity.dto.ServiceDtoRequest;
import com.dbp.winproyect.serviceEntity.dto.ServiceDtoResponse;
import com.dbp.winproyect.serviceEntity.infrastructure.ServiceEntityRepository;
import com.dbp.winproyect.tag.domain.ServiceTag;
import com.dbp.winproyect.tag.domain.TagService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceEntityService {


    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ServiceEntityRepository repository;
    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private FreelancerRepository freelancerRepository;
    private final ProviderService providerService;

    private final ServiceEntityRepository serviceEntityRepository;
    @Autowired
    private EnterpriseRepository enterpriseRepository;
    @Autowired
    private TagService tagService;
    @Autowired
    private ReviewService reviewService;

    public ServiceEntityService( ServiceEntityRepository serviceEntityRepository, ProviderService providerService ) {
        this.serviceEntityRepository = serviceEntityRepository;
        this.providerService = providerService;
    }

    public List<ServiceDtoResponse> obtenerServiciosPorTag(ServiceTag tagName) {
        // Buscar los servicios que tienen el tag en su conjunto de tags
        List<ServiceEntity> serviceEntities = serviceEntityRepository.findAllByTags_Name(tagName);

        // Mapear las entidades a DTOs
        return serviceEntities.stream()
                .map(serviceEntity -> modelMapper.map(serviceEntity, ServiceDtoResponse.class))
                .collect(Collectors.toList());
    }


    //Permite a un proveedor publicar un servicio.
    public ServiceEntity createService(ServiceDtoRequest serviceDtoRequest) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        // Intentar buscar primero en el repositorio de Freelancer
        Freelancer freelancer = freelancerRepository.findByEmail(email).orElse(null);

        // Si no es un Freelancer, buscar en el repositorio de Enterprise
        Enterprise enterprise = null;
        if (freelancer == null) {
            enterprise = enterpriseRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        }
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setDescription(serviceDtoRequest.getDescription());
        serviceEntity.setName(serviceDtoRequest.getName());
        serviceEntity.setAddress(serviceDtoRequest.getAddress());
        serviceEntity.setSuggestedPrice(serviceDtoRequest.getSuggestedPrice());
        serviceEntity.setTags(serviceDtoRequest.getTagSet());

        // Asociar el proveedor adecuado al servicio
        if (freelancer != null) {
            serviceEntity.setProvider(freelancer);
        } else {
            serviceEntity.setProvider(enterprise);
        }

        // Guardar el nuevo servicio
        return serviceEntityRepository.save(serviceEntity);
    }
    //Obtener todos los servicios en paginas para no mandarlo todo defrente
    public List<ServiceDtoResponse> obtenerTodosLosServicios() {
        // Obtener todos los servicios del repositorio
        List<ServiceEntity> serviceEntities = serviceEntityRepository.findAll();

        // Mapear cada entidad de servicio a DTO y devolver como lista
        List<ServiceDtoResponse> dtoResponses = serviceEntities.stream()
                .map(serviceEntity -> modelMapper.map(serviceEntity, ServiceDtoResponse.class))
                .collect(Collectors.toList());

        return dtoResponses;
    }
    //obtener un servicio por id
    public ServiceDtoResponse obtenerServicio(Long id, Integer pageNo, Integer pageSize, String sort) { //retornar dto

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sort).descending());

        ServiceEntity service = serviceEntityRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No se encontro el servicio"));
        ServiceDtoResponse response = modelMapper.map(service, ServiceDtoResponse.class);
        response.setNameProvider(providerService.getProviderName(service.getProvider()));
        response.setTagsList(tagService.findByServiceId(service.getId()));
        response.setPageableReviews(reviewService.getAllReviewsByServiceId(id, pageable));
        return response;
    }



    public void deleteService(Long id){
        ServiceEntity service = serviceEntityRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No se encontró el servicio con id " + id));
        serviceEntityRepository.delete(service);
    }

    public void updateService(ServiceEntity serviceEntity, Long id) {//dto de patch, por id
        //pasar los atributos del dto a un serviceEntity
        //luego guardar:

        serviceEntityRepository.save(serviceEntity);
    }



}
