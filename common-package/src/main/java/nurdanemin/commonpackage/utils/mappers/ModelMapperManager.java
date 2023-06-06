package nurdanemin.commonpackage.utils.mappers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;


@AllArgsConstructor
public class ModelMapperManager implements ModelMapperService {
    private ModelMapper mapper;

    @Override
    public ModelMapper forResponse() {
        mapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.LOOSE); //standard
        // kullandığımız
        return mapper;
        // sıkı olsa bizden obje createrequestler için id gerektirir

    }

    @Override
    public ModelMapper forRequest() {
        mapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.LOOSE); //standard
        // kullandığımız
        return mapper;
    }
}
