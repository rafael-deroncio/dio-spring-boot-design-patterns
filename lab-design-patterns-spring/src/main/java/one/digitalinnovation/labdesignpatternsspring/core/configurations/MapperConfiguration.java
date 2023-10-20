package one.digitalinnovation.labdesignpatternsspring.core.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import one.digitalinnovation.labdesignpatternsspring.core.models.AddressModel;
import one.digitalinnovation.labdesignpatternsspring.domain.responses.AddressResponse;

@Component
public class MapperConfiguration {

    @Bean
    ModelMapper mapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(AddressResponse.class, AddressModel.class)
                .addMapping(AddressResponse::getLogradouro, AddressModel::setStreet)
                .addMapping(AddressResponse::getComplemento, AddressModel::setComplement)
                .addMapping(AddressResponse::getBairro, AddressModel::setNeighborhood)
                .addMapping(AddressResponse::getLocalidade, AddressModel::setLocality)
                .addMapping(AddressResponse::getUf, AddressModel::setState)
                .addMapping(AddressResponse::getIbge, AddressModel::setIbge)
                .addMapping(AddressResponse::getGia, AddressModel::setGia)
                .addMapping(AddressResponse::getDdd, AddressModel::setDdd)
                .addMapping(AddressResponse::getSiafi, AddressModel::setSiafi);

        modelMapper.createTypeMap(AddressModel.class, AddressResponse.class)
                .addMapping(AddressModel::getStreet, AddressResponse::setLogradouro)
                .addMapping(AddressModel::getComplement, AddressResponse::setComplemento)
                .addMapping(AddressModel::getNeighborhood, AddressResponse::setBairro)
                .addMapping(AddressModel::getLocality, AddressResponse::setLocalidade)
                .addMapping(AddressModel::getState, AddressResponse::setUf)
                .addMapping(AddressModel::getIbge, AddressResponse::setIbge)
                .addMapping(AddressModel::getGia, AddressResponse::setGia)
                .addMapping(AddressModel::getDdd, AddressResponse::setDdd)
                .addMapping(AddressModel::getSiafi, AddressResponse::setSiafi);

        return modelMapper;
    }

}